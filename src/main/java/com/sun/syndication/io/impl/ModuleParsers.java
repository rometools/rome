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

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleParser;
import com.sun.syndication.io.WireFeedParser;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class ModuleParsers extends PluginManager {
    public ModuleParsers(String propertyKey, WireFeedParser parentParser) {
        super(propertyKey, parentParser, null);
    }

    public String getKey(Object obj) {
        return ((ModuleParser)obj).getNamespaceUri();
    }

    public List getModuleNamespaces() {
        return getKeys();
    }

    public List parseModules(Element root) {
        List parsers = getPlugins();
        List modules = null;
        for (int i=0;i<parsers.size();i++) {
            ModuleParser parser = (ModuleParser) parsers.get(i);
            String namespaceUri = parser.getNamespaceUri();
            Namespace namespace = Namespace.getNamespace(namespaceUri);
            if (hasElementsFrom(root, namespace)) {
                Module module = parser.parse(root);
                if (module != null) {
                    if (modules == null) {
                        modules = new ArrayList();
                    }
                    modules.add(module);
                }
            }
        }
        return modules;
    }

    private boolean hasElementsFrom(Element root, Namespace namespace) {
        boolean hasElements = false;
//        boolean hasElements = namespace.equals(root.getNamespace());

        if (!hasElements) {
            List children = root.getChildren();
            for (int i=0;!hasElements && i < children.size();i++) {
                Element child = (Element) children.get(i);
                hasElements = namespace.equals(child.getNamespace());
            }
        }
        return hasElements;
    }
}
