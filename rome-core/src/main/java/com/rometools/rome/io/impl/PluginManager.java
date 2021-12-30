/*
 * Copyright 2004 Sun Microsystems, Inc.
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
 *
 */
package com.rometools.rome.io.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rometools.rome.feed.impl.ConfigurableClassLoader;
import com.rometools.rome.io.DelegatingModuleGenerator;
import com.rometools.rome.io.DelegatingModuleParser;
import com.rometools.rome.io.WireFeedGenerator;
import com.rometools.rome.io.WireFeedParser;

public abstract class PluginManager<T> {

    private final String[] propertyValues;
    private final List<String> keys;
    private final WireFeedParser parentParser;
    private final WireFeedGenerator parentGenerator;

    private Map<String, T> pluginsMap;
    private List<T> pluginsList;

    /**
     * Creates a PluginManager
     *
     * @param propertyKey property key defining the plugins classes
     *
     */
    protected PluginManager(final String propertyKey) {
        this(propertyKey, null, null);
    }

    protected PluginManager(final String propertyKey, final WireFeedParser parentParser, final WireFeedGenerator parentGenerator) {
        this.parentParser = parentParser;
        this.parentGenerator = parentGenerator;
        propertyValues = PropertiesLoader.getPropertiesLoader().getTokenizedProperty(propertyKey, ", ");
        loadPlugins();
        pluginsMap = Collections.unmodifiableMap(pluginsMap);
        pluginsList = Collections.unmodifiableList(pluginsList);
        keys = Collections.unmodifiableList(new ArrayList<String>(pluginsMap.keySet()));
    }

    protected abstract String getKey(T obj);

    protected List<String> getKeys() {
        return keys;
    }

    protected List<T> getPlugins() {
        return pluginsList;
    }

    protected Map<String, T> getPluginMap() {
        return pluginsMap;
    }

    protected T getPlugin(final String key) {
        return pluginsMap.get(key);
    }

    // PRIVATE - LOADER PART

    private void loadPlugins() {

        final List<T> finalPluginsList = new ArrayList<T>();
        pluginsList = new ArrayList<T>();
        pluginsMap = new HashMap<String, T>();
        String className = null;

        try {
            final Class<T>[] classes = getClasses();
            for (final Class<T> clazz : classes) {

                className = clazz.getName();
                final T plugin = clazz.newInstance();

                if (plugin instanceof DelegatingModuleParser) {
                    ((DelegatingModuleParser) plugin).setFeedParser(parentParser);
                }

                if (plugin instanceof DelegatingModuleGenerator) {
                    ((DelegatingModuleGenerator) plugin).setFeedGenerator(parentGenerator);
                }

                pluginsMap.put(getKey(plugin), plugin);

                // to preserve the order of definition in the rome.properties files
                pluginsList.add(plugin);

            }

            final Collection<T> plugins = pluginsMap.values();
            for (final T plugin : plugins) {
                // to remove overridden plugin impls
                finalPluginsList.add(plugin);
            }

            final Iterator<T> iterator = pluginsList.iterator();
            while (iterator.hasNext()) {
                final T plugin = iterator.next();
                if (!finalPluginsList.contains(plugin)) {
                    iterator.remove();
                }
            }

        } catch (final Exception ex) {
            throw new RuntimeException("could not instantiate plugin " + className, ex);
        } catch (final ExceptionInInitializerError er) {
            throw new RuntimeException("could not instantiate plugin " + className, er);
        }

    }

    /**
     * Loads and returns the classes defined in the properties files. If the system property
     * "rome.pluginmanager.useloadclass" is set to true then classLoader.loadClass will be used to
     * load classes (instead of Class.forName). This is designed to improve OSGi compatibility.
     * Further information can be found in https://rome.dev.java.net/issues/show_bug.cgi?id=118
     * <p>
     *
     * @return array containing the classes defined in the properties files.
     * @throws java.lang.ClassNotFoundException thrown if one of the classes defined in the
     *             properties file cannot be loaded and hard failure is ON.
     *
     */
    @SuppressWarnings("unchecked")
    private Class<T>[] getClasses() throws ClassNotFoundException {

        final ClassLoader classLoader = ConfigurableClassLoader.INSTANCE.getClassLoader();

        final List<Class<T>> classes = new ArrayList<Class<T>>();

        final boolean useLoadClass = Boolean.valueOf(System.getProperty("rome.pluginmanager.useloadclass", "false")).booleanValue();

        for (final String propertyValue : propertyValues) {
            final Class<T> mClass;
            if (useLoadClass) {
                mClass = (Class<T>) classLoader.loadClass(propertyValue);
            } else {
                mClass = (Class<T>) Class.forName(propertyValue, true, classLoader);
            }
            classes.add(mClass);
        }

        final Class<T>[] array = new Class[classes.size()];
        classes.toArray(array);
        return array;
    }

}
