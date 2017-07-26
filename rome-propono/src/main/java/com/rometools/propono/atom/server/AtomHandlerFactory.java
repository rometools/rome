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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a factory that enables the {@link com.rometools.rome.propono.atom.server.AtomServlet} to
 * obtain an {@link com.rometools.rome.propono.atom.server.AtomHandler} that handles an Atom
 * request.
 *
 * <p>
 * To create your own Atom protocol implementation you must sub-class this class with your own
 * factory that is capable of creating instances of your
 * {@link com.rometools.rome.propono.atom.server.AtomHandler} impementation.
 * </p>
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public abstract class AtomHandlerFactory {

    private static final Logger LOG = LoggerFactory.getLogger(AtomHandlerFactory.class);

    private static final String DEFAULT_PROPERTY_NAME = "com.rometools.propono.atom.server.AtomHandlerFactory";
    private static final String FALLBACK_IMPL_NAME = "com.rometools.propono.atom.server.impl.FileBasedAtomHandlerFactory";

    /*
     * <p>Protected constructor to prevent instantiation. Use {@link #newInstance()}.</p>
     */
    protected AtomHandlerFactory() {
    }

    /**
     * Obtain a new instance of a <code>AtomHandlerFactory</code>. This static method creates a new
     * factory instance. This method uses the following ordered lookup procedure to determine the
     * <code>AtomHandlerFactory</code> implementation class to load:
     * <ul>
     * <li>
     * Use the <code>com.rometools.rome.propono.atom.server.AtomHandlerFactory</code> system
     * property.</li>
     * <li>
     * Use the properties file "/propono.properties" in the classpath. This configuration file is in
     * standard <code>java.util.Properties</code> format and contains the fully qualified name of
     * the implementation class with the key being the system property defined above.
     *
     * The propono.properties file is read only once by Propono and it's values are then cached for
     * future use. If the file does not exist when the first attempt is made to read from it, no
     * further attempts are made to check for its existence. It is not possible to change the value
     * of any property in propono.properties after it has been read for the first time.</li>
     * <li>
     * If not available, to determine the classname. The Services API will look for a classname in
     * the file: <code>META-INF/services/com.rometools.rome.AtomHandlerFactory</code> in jars
     * available to the runtime.</li>
     * <li>
     * Platform default <code>AtomHandlerFactory</code> instance.</li>
     * </ul>
     *
     * Once an application has obtained a reference to a <code>AtomHandlerFactory</code> it can use
     * the factory to configure and obtain parser instances.
     *
     * @return New instance of a <code>AtomHandlerFactory</code>
     *
     * @throws FactoryConfigurationError if the implementation is not available or cannot be
     *             instantiated.
     */
    public static AtomHandlerFactory newInstance() {
        try {
            return (AtomHandlerFactory) FactoryFinder.find(DEFAULT_PROPERTY_NAME, FALLBACK_IMPL_NAME);
        } catch (final ConfigurationError e) {
            LOG.error("An error occured while finding factory", e);
            throw new FactoryConfigurationError(e.getException(), e.getMessage());
        }
    }

    /**
     * Creates a new instance of a {@link com.rometools.rome.propono.atom.server.AtomHandler} using
     * the currently configured parameters.
     *
     * @return A new instance of a AtomHandler.
     *
     * @throws AtomConfigurationException if a AtomHandler cannot be created which satisfies the
     *             configuration requested.
     */
    public abstract AtomHandler newAtomHandler(HttpServletRequest req, HttpServletResponse res);
}
