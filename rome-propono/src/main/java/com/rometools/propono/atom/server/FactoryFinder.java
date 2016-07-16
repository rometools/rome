/*
 * Copyright 2007 Sun Microsystems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rometools.propono.atom.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Find {@link com.rometools.rome.propono.atom.server.AtomHandlerFactory} based on properties
 * files.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
class FactoryFinder {

    private static boolean debug = false;
    private static Properties cacheProps = new Properties();
    private static SecuritySupport ss = new SecuritySupport();
    private static boolean firstTime = true;

    private FactoryFinder() {
    }

    private static void dPrint(final String msg) {
        if (debug) {
            System.err.println("Propono: " + msg);
        }
    }

    /**
     * Create an instance of a class using the specified ClassLoader and optionally fall back to the
     * current ClassLoader if not found.
     *
     * @param className Name of the concrete class corresponding to the service provider
     *
     * @param cl ClassLoader to use to load the class, null means to use the bootstrap ClassLoader
     *
     * @param doFallback true if the current ClassLoader should be tried as a fallback if the class
     *            is not found using cl
     */
    private static Object newInstance(final String className, ClassLoader cl, final boolean doFallback) throws ConfigurationError {

        try {
            Class<?> providerClass;
            if (cl == null) {
                // If classloader is null Use the bootstrap ClassLoader.
                // Thus Class.forName(String) will use the current
                // ClassLoader which will be the bootstrap ClassLoader.
                providerClass = Class.forName(className);
            } else {
                try {
                    providerClass = cl.loadClass(className);
                } catch (final ClassNotFoundException x) {
                    if (doFallback) {
                        // Fall back to current classloader
                        cl = FactoryFinder.class.getClassLoader();
                        providerClass = cl.loadClass(className);
                    } else {
                        throw x;
                    }
                }
            }

            final Object instance = providerClass.newInstance();
            dPrint("created new instance of " + providerClass + " using ClassLoader: " + cl);
            return instance;
        } catch (final ClassNotFoundException x) {
            throw new ConfigurationError("Provider " + className + " not found", x);
        } catch (final Exception x) {
            throw new ConfigurationError("Provider " + className + " could not be instantiated: " + x, x);
        }
    }

    /**
     * Finds the implementation Class object in the specified order. Main entry point.
     *
     * @return Class object of factory, never null
     *
     * @param factoryId Name of the factory to find, same as a property name
     * @param fallbackClassName Implementation class name, if nothing else is found. Use null to
     *            mean no fallback.
     *
     *            Package private so this code can be shared.
     */
    static Object find(final String factoryId, final String fallbackClassName) throws ConfigurationError {

        // Figure out which ClassLoader to use for loading the provider
        // class. If there is a Context ClassLoader then use it.

        ClassLoader classLoader = ss.getContextClassLoader();

        if (classLoader == null) {
            // if we have no Context ClassLoader
            // so use the current ClassLoader
            classLoader = FactoryFinder.class.getClassLoader();
        }

        dPrint("find factoryId =" + factoryId);

        // Use the system property first
        try {
            final String systemProp = ss.getSystemProperty(factoryId);
            if (systemProp != null) {
                dPrint("found system property, value=" + systemProp);
                return newInstance(systemProp, classLoader, true);
            }
        } catch (final SecurityException se) {
            // if first option fails due to any reason we should try next option in the
            // look up algorithm.
        }

        // try to read from /propono.properties
        try {
            final String configFile = "/propono.properties";
            String factoryClassName = null;
            if (firstTime) {
                synchronized (cacheProps) {
                    if (firstTime) {
                        try {
                            final InputStream is = FactoryFinder.class.getResourceAsStream(configFile);
                            firstTime = false;
                            if (is != null) {
                                dPrint("Read properties file: " + configFile);
                                cacheProps.load(is);
                            }
                        } catch (final Exception intentionallyIgnored) {
                        }
                    }
                }
            }
            factoryClassName = cacheProps.getProperty(factoryId);

            if (factoryClassName != null) {
                dPrint("found in $java.home/propono.properties, value=" + factoryClassName);
                return newInstance(factoryClassName, classLoader, true);
            }
        } catch (final Exception ex) {
            if (debug) {
                ex.printStackTrace();
            }
        }

        // Try Jar Service Provider Mechanism
        final Object provider = findJarServiceProvider(factoryId);
        if (provider != null) {
            return provider;
        }
        if (fallbackClassName == null) {
            throw new ConfigurationError("Provider for " + factoryId + " cannot be found", null);
        }

        dPrint("loaded from fallback value: " + fallbackClassName);
        return newInstance(fallbackClassName, classLoader, true);
    }

    /*
     * Try to find provider using Jar Service Provider Mechanism
     * @return instance of provider class if found or null
     */
    private static Object findJarServiceProvider(final String factoryId) throws ConfigurationError {

        final String serviceId = "META-INF/services/" + factoryId;
        InputStream is = null;

        // First try the Context ClassLoader
        ClassLoader cl = ss.getContextClassLoader();
        if (cl != null) {
            is = ss.getResourceAsStream(cl, serviceId);

            // If no provider found then try the current ClassLoader
            if (is == null) {
                cl = FactoryFinder.class.getClassLoader();
                is = ss.getResourceAsStream(cl, serviceId);
            }
        } else {
            // No Context ClassLoader, try the current
            // ClassLoader
            cl = FactoryFinder.class.getClassLoader();
            is = ss.getResourceAsStream(cl, serviceId);
        }

        if (is == null) {
            // No provider found
            return null;
        }

        dPrint("found jar resource=" + serviceId + " using ClassLoader: " + cl);

        // Read the service provider name in UTF-8 as specified in
        // the jar spec. Unfortunately this fails in Microsoft
        // VJ++, which does not implement the UTF-8
        // encoding. Theoretically, we should simply let it fail in
        // that case, since the JVM is obviously broken if it
        // doesn't support such a basic standard. But since there
        // are still some users attempting to use VJ++ for
        // development, we have dropped in a fallback which makes a
        // second attempt using the platform's default encoding. In
        // VJ++ this is apparently ASCII, which is a subset of
        // UTF-8... and since the strings we'll be reading here are
        // also primarily limited to the 7-bit ASCII range (at
        // least, in English versions), this should work well
        // enough to keep us on the air until we're ready to
        // officially decommit from VJ++. [Edited comment from
        // jkesselm]
        BufferedReader rd;
        try {
            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (final java.io.UnsupportedEncodingException e) {
            rd = new BufferedReader(new InputStreamReader(is));
        }

        String factoryClassName = null;
        try {
            // XXX Does not handle all possible input as specified by the
            // Jar Service Provider specification
            factoryClassName = rd.readLine();
            rd.close();
        } catch (final IOException x) {
            // No provider found
            return null;
        }

        if (factoryClassName != null && !"".equals(factoryClassName)) {
            dPrint("found in resource, value=" + factoryClassName);

            // Note: here we do not want to fall back to the current
            // ClassLoader because we want to avoid the case where the
            // resource file was found using one ClassLoader and the
            // provider class was instantiated using a different one.
            return newInstance(factoryClassName, cl, false);
        }

        // No provider found
        return null;
    }

}
