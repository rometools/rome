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
import com.sun.syndication.feed.module.SyModule;
import com.sun.syndication.io.ModuleGenerator;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * Feed Generator for SY ModuleImpl
 * <p/>
 *
 * @author Elaine Chien
 *
 */

public class SyModuleGenerator implements ModuleGenerator {

    private static final String SY_URI  = "http://purl.org/rss/1.0/modules/syndication/";
    private static final Namespace SY_NS  = Namespace.getNamespace("sy", SY_URI);

    private static final Set NAMESPACES;

    static {
        Set nss = new HashSet();
        nss.add(SY_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    public String getNamespaceUri() {
        return SY_URI;
    }

    /**
     * Returns a set with all the URIs (JDOM Namespace elements) this module generator uses.
     * <p/>
     * It is used by the the feed generators to add their namespace definition in
     * the root element of the generated document (forward-missing of Java 5.0 Generics).
     * <p/>
     *
     * @return a set with all the URIs (JDOM Namespace elements) this module generator uses.
     */
    public Set getNamespaces() {
        return NAMESPACES;
    }

    public void generate(Module module, Element element) {

        SyModule syModule = (SyModule)module;

        if (syModule.getUpdatePeriod() != null) {
            Element updatePeriodElement = new Element("updatePeriod", SY_NS);
            updatePeriodElement.addContent(syModule.getUpdatePeriod());
            element.addContent(updatePeriodElement);
        }

        Element updateFrequencyElement = new Element("updateFrequency", SY_NS);
        updateFrequencyElement.addContent(String.valueOf(syModule.getUpdateFrequency()));
        element.addContent(updateFrequencyElement);

        if (syModule.getUpdateBase() != null) {
            Element updateBaseElement = new Element("updateBase", SY_NS);
            updateBaseElement.addContent(DateParser.formatW3CDateTime(syModule.getUpdateBase()));
            element.addContent(updateBaseElement);
        }
    }
}
