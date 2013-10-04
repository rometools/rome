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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleGenerator;

/**
 */
public class ModuleGenerators extends PluginManager<ModuleGenerator> {
    private Set<Namespace> allNamespaces;

    public ModuleGenerators(final String propertyKey, final BaseWireFeedGenerator parentGenerator) {
        super(propertyKey, null, parentGenerator);
    }

    public ModuleGenerator getGenerator(final String uri) {
        return (ModuleGenerator) getPlugin(uri);
    }

    @Override
    protected String getKey(final ModuleGenerator obj) {
        return obj.getNamespaceUri();
    }

    public List<String> getModuleNamespaces() {
        return getKeys();
    }

    public void generateModules(final List<Module> modules, final Element element) {
        final Map<String, ModuleGenerator> generators = getPluginMap();
        for (int i = 0; i < modules.size(); i++) {
            final Module module = modules.get(i);
            final String namespaceUri = module.getUri();
            final ModuleGenerator generator = generators.get(namespaceUri);
            if (generator != null) {
                generator.generate(module, element);
            }
        }
    }

    public Set<Namespace> getAllNamespaces() {
        if (allNamespaces == null) {
            allNamespaces = new HashSet<Namespace>();
            final List<String> mUris = getModuleNamespaces();
            for (int i = 0; i < mUris.size(); i++) {
                final ModuleGenerator mGen = getGenerator(mUris.get(i));
                allNamespaces.addAll(mGen.getNamespaces());
            }
        }
        return allNamespaces;
    }
}
