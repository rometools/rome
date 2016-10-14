/*
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

package com.rometools.modules.atom.io;

import com.rometools.modules.atom.modules.AtomLinkModule;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AtomModuleGenerator implements ModuleGenerator {

    static final Namespace NS = Namespace.getNamespace("atom", AtomLinkModule.URI);
    private static final Set<Namespace> NAMESPACES;

    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    @Override
    public final String getNamespaceUri() {
        return AtomLinkModule.URI;
    }

    @Override
    public final Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(Module module, Element element) {
        if (module instanceof AtomLinkModule) {
            AtomLinkModule m = (AtomLinkModule) module;
            generateLinks(m.getLinks(), element);
        }
    }

    private Element generateLink(Link link) {
        Element linkElement = new Element("link", NS);

        if (link.getHref() != null) {
            Attribute href = new Attribute(AtomLinkAttribute.HREF, link.getHref());
            linkElement.setAttribute(href);
        }
        if (link.getType() != null) {
            Attribute type = new Attribute(AtomLinkAttribute.TYPE, link.getType());
            linkElement.setAttribute(type);
        }
        if (link.getRel() != null) {
            Attribute rel = new Attribute(AtomLinkAttribute.REL, link.getRel());
            linkElement.setAttribute(rel);
        }

        if (link.getHreflang() != null) {
            final Attribute hreflangAttribute = new Attribute(AtomLinkAttribute.HREF_LANG, link.getHreflang());
            linkElement.setAttribute(hreflangAttribute);
        }

        if (link.getTitle() != null) {
            final Attribute title = new Attribute(AtomLinkAttribute.TITLE, link.getTitle());
            linkElement.setAttribute(title);
        }

        if (link.getLength() != 0) {
            final Attribute length = new Attribute(AtomLinkAttribute.LENGTH, Long.toString(link.getLength()));
            linkElement.setAttribute(length);
        }

        return linkElement;
    }

    private void generateLinks(List<Link> links, Element element) {
        for (Link link : links) {
            element.addContent(generateLink(link));
        }

    }

}
