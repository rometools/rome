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

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.SyModule;
import com.rometools.rome.io.ModuleGenerator;

/**
 * Feed Generator for SY ModuleImpl
 * <p/>
 */
public class SyModuleGenerator implements ModuleGenerator {

    private static final String SY_URI = "http://purl.org/rss/1.0/modules/syndication/";
    private static final Namespace SY_NS = Namespace.getNamespace("sy", SY_URI);

    private static final Set<Namespace> NAMESPACES;

    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(SY_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    @Override
    public String getNamespaceUri() {
        return SY_URI;
    }

    /**
     * Returns a set with all the URIs (JDOM Namespace elements) this module generator uses.
     * <p/>
     * It is used by the the feed generators to add their namespace definition in the root element
     * of the generated document (forward-missing of Java 5.0 Generics).
     * <p/>
     *
     * @return a set with all the URIs (JDOM Namespace elements) this module generator uses.
     */
    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(final Module module, final Element element) {

        final SyModule syModule = (SyModule) module;

        final String updatePeriod = syModule.getUpdatePeriod();
        if (updatePeriod != null) {
            final Element updatePeriodElement = new Element("updatePeriod", SY_NS);
            updatePeriodElement.addContent(updatePeriod);
            element.addContent(updatePeriodElement);
        }

        final Element updateFrequencyElement = new Element("updateFrequency", SY_NS);
        updateFrequencyElement.addContent(String.valueOf(syModule.getUpdateFrequency()));
        element.addContent(updateFrequencyElement);

        final Date updateBase = syModule.getUpdateBase();
        if (updateBase != null) {
            final Element updateBaseElement = new Element("updateBase", SY_NS);
            updateBaseElement.addContent(DateParser.formatW3CDateTime(updateBase, Locale.US));
            element.addContent(updateBaseElement);
        }

    }

}
