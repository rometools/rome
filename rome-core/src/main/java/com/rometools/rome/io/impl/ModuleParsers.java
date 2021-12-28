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

import java.util.List;
import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import com.rometools.rome.io.WireFeedParser;
import com.rometools.utils.Lists;

public class ModuleParsers extends PluginManager<ModuleParser> {

    public ModuleParsers(final String propertyKey, final WireFeedParser parentParser) {
        super(propertyKey, parentParser, null);
    }

    @Override
    public String getKey(final ModuleParser obj) {
        return obj.getNamespaceUri();
    }

    public List<String> getModuleNamespaces() {
        return getKeys();
    }

    public List<Module> parseModules(final Element root, final Locale locale) {
        final List<ModuleParser> parsers = getPlugins();
        List<Module> modules = null;
        for (final ModuleParser parser : parsers) {
            final String namespaceUri = parser.getNamespaceUri();
            final Namespace namespace = Namespace.getNamespace(namespaceUri);
            if (hasElementsFrom(root, namespace)) {
                final Module module = parser.parse(root, locale);
                if (module != null) {
                    modules = Lists.createWhenNull(modules);
                    modules.add(module);
                }
            }
        }
        return modules;
    }

    private boolean hasElementsFrom(final Element root, final Namespace namespace) {
        boolean hasElements = false;
        for (final Element child : root.getChildren()) {
            final Namespace childNamespace = child.getNamespace();
            if (namespace.equals(childNamespace)) {
                hasElements = true;
                break;
            }
        }
        return hasElements;
    }

}
