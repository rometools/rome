/*
 * CCModuleParser.java
 *
 * Created on November 20, 2005, 5:23 PM
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rometools.modules.cc.io;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.cc.CreativeCommonsImpl;
import com.rometools.modules.cc.types.License;
import com.rometools.modules.cc.types.License.Behaviour;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;

/**
 * @version $Revision: 1.3 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ModuleParserRSS1 implements ModuleParser {

    private static final Namespace NS = Namespace.getNamespace(CreativeCommonsImpl.RSS1_URI);
    static final Namespace RDF = Namespace.getNamespace(CreativeCommonsImpl.RDF_URI);

    /**
     * Creates a new instance of ModuleParserRSS1
     */
    public ModuleParserRSS1() {
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        final CreativeCommonsImpl module = new CreativeCommonsImpl();
        {
            // Parsing Channel level.
            Element root = element;
            while (root.getParentElement() != null) {
                root = root.getParentElement();
            }
            final List<Element> licenseList = root.getChildren("License", NS);
            final ArrayList<License> licenses = new ArrayList<License>();
            final Iterator<Element> it = licenseList.iterator();
            while (it.hasNext()) {
                final Element licenseTag = it.next();
                final String licenseURI = licenseTag.getAttributeValue("about", RDF);
                if (licenseURI == null) {
                    continue;
                }
                License license = License.findByValue(licenseURI);
                {
                    final ArrayList<Behaviour> permitsValues = new ArrayList<Behaviour>();
                    final ArrayList<Behaviour> requiresValues = new ArrayList<Behaviour>();
                    final List<Element> permitsTags = licenseTag.getChildren("permits", NS);
                    Iterator<Element> sit = permitsTags.iterator();
                    while (sit.hasNext()) {
                        final Element permitTag = sit.next();
                        permitsValues.add(License.Behaviour.findByValue(permitTag.getAttributeValue("resource", RDF)));
                    }
                    final List<Element> requiresTags = licenseTag.getChildren("requires", NS);
                    sit = requiresTags.iterator();
                    while (sit.hasNext()) {
                        final Element requireTag = sit.next();
                        requiresValues.add(License.Behaviour.findByValue(requireTag.getAttributeValue("resource", RDF)));
                    }
                    license = new License(licenseURI, requiresValues.toArray(new License.Behaviour[requiresValues.size()]),
                            permitsValues.toArray(new License.Behaviour[permitsValues.size()]));

                }

                licenses.add(license);
            }
            module.setAllLicenses(licenses.toArray(new License[0]));
        }
        final ArrayList<License> licenses = new ArrayList<License>();
        final List<Element> licenseTags = element.getChildren("license", NS);
        final Iterator<Element> lit = licenseTags.iterator();
        while (lit.hasNext()) {
            final Element licenseTag = lit.next();
            licenses.add(License.findByValue(licenseTag.getAttributeValue("resource", RDF)));
        }

        if (!licenses.isEmpty()) {
            module.setLicenses(licenses.toArray(new License[licenses.size()]));
        }

        if (module.getLicenses() != null || module.getAllLicenses() != null) {
            return module;
        } else {
            return null;
        }
    }

    @Override
    public String getNamespaceUri() {
        return CreativeCommonsImpl.RSS1_URI;
    }

}
