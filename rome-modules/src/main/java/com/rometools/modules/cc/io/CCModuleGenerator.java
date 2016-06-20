/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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

package com.rometools.modules.cc.io;

import java.util.HashSet;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.cc.CreativeCommons;
import com.rometools.modules.cc.CreativeCommonsImpl;
import com.rometools.modules.cc.types.License;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

public class CCModuleGenerator implements ModuleGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(CCModuleGenerator.class);

    private static final Namespace RSS1 = Namespace.getNamespace("cc", CreativeCommonsImpl.RSS1_URI);
    private static final Namespace RSS2 = Namespace.getNamespace("creativeCommons", CreativeCommonsImpl.RSS2_URI);
    private static final Namespace RSS = Namespace.getNamespace("http://purl.org/rss/1.0/");
    private static final Namespace RDF = Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    private static final HashSet<Namespace> NAMESPACES = new HashSet<Namespace>();
    static {
        NAMESPACES.add(RSS1);
        NAMESPACES.add(RSS2);
        NAMESPACES.add(RDF);
    }

    public CCModuleGenerator() {
        super();
    }

    @Override
    public void generate(final Module module, final Element element) {
        Element root = element;
        while (root.getParentElement() != null) {
            root = root.getParentElement();
        }
        if (root.getNamespace().equals(RDF) || root.getNamespace().equals(RSS)) {
            generateRSS1((CreativeCommons) module, element);
        } else {
            generateRSS2((CreativeCommons) module, element);
        }
    }

    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public String getNamespaceUri() {
        return CreativeCommons.URI;
    }

    private void generateRSS1(final CreativeCommons module, final Element element) {
        // throw new RuntimeException( "Generating RSS1 Feeds not currently Supported.");

        LOG.debug(element.getName());
        if (element.getName().equals("channel")) {
            // Do all licenses list.
            final License[] all = module.getAllLicenses();
            for (final License element2 : all) {
                final Element license = new Element("License", RSS1);
                license.setAttribute("about", element2.getValue(), RDF);
                final License.Behaviour[] permits = element2.getPermits();
                for (int j = 0; permits != null && j < permits.length; j++) {
                    final Element permit = new Element("permits", RSS1);
                    permit.setAttribute("resource", permits[j].toString(), RDF);
                    license.addContent(permit);
                }
                final License.Behaviour[] requires = element2.getPermits();
                for (int j = 0; requires != null && j < requires.length; j++) {
                    final Element permit = new Element("requires", RSS1);
                    permit.setAttribute("resource", permits[j].toString(), RDF);
                    license.addContent(permit);
                }
                LOG.debug("Is Root? {}", element.getParentElement());
                element.getParentElement().addContent(license);
            }
        }

        // Do local licenses
        final License[] licenses = module.getLicenses();
        for (final License license2 : licenses) {
            final Element license = new Element("license", RSS1);
            license.setAttribute("resource", license2.getValue(), RDF);
            element.addContent(license);
        }

    }

    private void generateRSS2(final CreativeCommons module, final Element element) {
        final License[] licenses = module.getLicenses();
        for (int i = 0; licenses != null && i < licenses.length; i++) {
            final Element license = new Element("license", RSS2);
            license.setText(licenses[i].getValue());
            element.addContent(license);
        }
    }
}
