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
package com.sun.syndication.io.impl;

import com.sun.syndication.io.DelegatingModuleGenerator;
import com.sun.syndication.io.DelegatingModuleParser;
import com.sun.syndication.io.WireFeedGenerator;
import com.sun.syndication.io.WireFeedParser;

import java.util.*;

/**
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public abstract class PluginManager {
    private String[] _propertyValues;
    private Map _pluginsMap;
    private List _pluginsList;
    private List _keys;
    private WireFeedParser _parentParser;
    private WireFeedGenerator _parentGenerator;

    /**
     * Creates a PluginManager
     * <p>
     * @param propertyKey property key defining the plugins classes
     *
     */
    protected PluginManager(String propertyKey) {
        this(propertyKey, null, null);
    }

    protected PluginManager(String propertyKey, WireFeedParser parentParser,
                            WireFeedGenerator parentGenerator)
    {
        _parentParser = parentParser;
        _parentGenerator = parentGenerator;
        _propertyValues = PropertiesLoader.getPropertiesLoader().getTokenizedProperty(propertyKey,", ");
        loadPlugins();
        _pluginsMap = Collections.unmodifiableMap(_pluginsMap);
        _pluginsList = Collections.unmodifiableList(_pluginsList);
        _keys = Collections.unmodifiableList(new ArrayList(_pluginsMap.keySet()));
    }

    protected abstract String getKey(Object obj);

    protected List getKeys() {
        return _keys;
    }

    protected List getPlugins() {
        return _pluginsList;
    }

    protected Map getPluginMap() {
        return _pluginsMap;
    }

    protected Object getPlugin(String key) {
        return _pluginsMap.get(key);
    }

    // PRIVATE - LOADER PART

    private void loadPlugins() {
        List finalPluginsList = new ArrayList();
        _pluginsList = new ArrayList();
        _pluginsMap = new HashMap();
        String className = null;
        try {
            Class[] classes = getClasses();
            for (int i=0;i<classes.length;i++) {
                className = classes[i].getName();
                Object plugin  = classes[i].newInstance();
                if (plugin instanceof DelegatingModuleParser) {
                    ((DelegatingModuleParser) plugin).setFeedParser(_parentParser);
                }
                if (plugin instanceof DelegatingModuleGenerator) {
                    ((DelegatingModuleGenerator) plugin).setFeedGenerator(_parentGenerator);
                }

                _pluginsMap.put(getKey(plugin), plugin);
                _pluginsList.add(plugin); // to preserve the order of definition in the rome.properties files
            }
            Iterator i = _pluginsMap.values().iterator();
            while (i.hasNext()) {
                finalPluginsList.add(i.next()); // to remove overridden plugin impls
            }

            i = _pluginsList.iterator();
            while (i.hasNext()) {
                Object plugin = i.next();
                if (!finalPluginsList.contains(plugin)) {
                    i.remove();
                }
            }
        }
        catch (Exception ex) {
            throw new RuntimeException("could not instantiate plugin "+className,ex);
        }catch (ExceptionInInitializerError er) {
            throw new RuntimeException("could not instantiate plugin "+className,er);
        }
    }

    /**
     * Loads and returns the classes defined in the properties files. If the system property "rome.pluginmanager.useloadclass" is
     * set to true then classLoader.loadClass will be used to load classes (instead of Class.forName). This is designed to improve
     * OSGi compatibility. Further information can be found in https://rome.dev.java.net/issues/show_bug.cgi?id=118 
     * <p>
     * @return array containing the classes defined in the properties files.
     * @throws java.lang.ClassNotFoundException thrown if one of the classes defined in the properties file cannot be loaded
     *         and hard failure is ON.
     *
     */
    private Class[] getClasses() throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List classes = new ArrayList();
        boolean useLoadClass = Boolean.valueOf(System.getProperty("rome.pluginmanager.useloadclass", "false")).booleanValue();
        for (int i = 0; i <_propertyValues.length; i++) {
        	Class mClass = (useLoadClass ?  classLoader.loadClass(_propertyValues[i]) : Class.forName(_propertyValues[i], true, classLoader));
            classes.add(mClass);
        }
        Class[] array = new Class[classes.size()];
        classes.toArray(array);
        return array;
    }

}
