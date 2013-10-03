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

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleParser;
import com.sun.syndication.io.WireFeedParser;

/**
 */
public class ModuleParsers extends PluginManager<ModuleParser> {
    public ModuleParsers(final String propertyKey, final WireFeedParser parentParser) {
        super(propertyKey, parentParser, null);
    }

    @Override
    public String getKey(final Object obj) {
        return ((ModuleParser) obj).getNamespaceUri();
    }

    public List<String> getModuleNamespaces() {
        return getKeys();
    }

    public List<Module> parseModules(final Element root) {
        final List<ModuleParser> parsers = getPlugins();
        List<Module> modules = null;
        for (int i = 0; i < parsers.size(); i++) {
            final ModuleParser parser = parsers.get(i);
            final String namespaceUri = parser.getNamespaceUri();
            final Namespace namespace = Namespace.getNamespace(namespaceUri);
            if (hasElementsFrom(root, namespace)) {
                final Module module = parser.parse(root);
                if (module != null) {
                    if (modules == null) {
                        modules = new ArrayList<Module>();
                    }
                    modules.add(module);
                }
            }
        }
        return modules;
    }

    private boolean hasElementsFrom(final Element root, final Namespace namespace) {
        boolean hasElements = false;
        // boolean hasElements = namespace.equals(root.getNamespace());

        if (!hasElements) {
            final List<Element> children = root.getChildren();
            for (int i = 0; !hasElements && i < children.size(); i++) {
                final Element child = children.get(i);
                hasElements = namespace.equals(child.getNamespace());
            }
        }
        return hasElements;
    }
}
