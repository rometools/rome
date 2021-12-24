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
import com.rometools.modules.atom.modules.AtomLinkModuleImpl;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.feed.synd.SyndPersonImpl;
import com.rometools.rome.io.ModuleParser;
import com.rometools.rome.io.impl.NumberParser;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class AtomModuleParser implements ModuleParser {

    private static final Namespace NS = Namespace.getNamespace(AtomLinkModule.URI);

    @Override
    public String getNamespaceUri() {
        return AtomLinkModule.URI;
    }

    @Override
    public Module parse(Element element, Locale locale) {
        AtomLinkModuleImpl mod = null;
        if (element.getName().equals("channel") || element.getName().equals("item")) {
            mod = new AtomLinkModuleImpl();
            mod.setLinks(parseLinks(element));
            mod.setAuthors(parseAuthors(element));
            mod.setContributors(parseContributor(element));
        }
        return mod;
    }

    private List<Link> parseLinks(Element parent) {
        final List<Link> result = new LinkedList<Link>();
        final List<Element> links = parent.getChildren("link", NS);
        for (Element link : links) {
            Link l = parseLink(link);
            result.add(l);
        }
        return result;
    }

    private List<SyndPerson> parseAuthors(Element parent) {
        final List<SyndPerson> result = new LinkedList<SyndPerson>();
        final List<Element> authors = parent.getChildren("author", NS);
        for (Element author : authors) {
            result.add(parsePerson(author));
        }
        return result;
    }

    private List<SyndPerson> parseContributor(Element parent) {
        final List<SyndPerson> result = new LinkedList<SyndPerson>();
        final List<Element> contributors = parent.getChildren("contributor", NS);
        for (Element contributor : contributors) {
            result.add(parsePerson(contributor));
        }
        return result;
    }

    private Link parseLink(final Element eLink) {

        final Link link = new Link();

        final String rel = getAttributeValue(eLink, AtomLinkAttribute.REL);
        if (rel != null) {
            link.setRel(rel);
        }

        final String type = getAttributeValue(eLink, AtomLinkAttribute.TYPE);
        if (type != null) {
            link.setType(type);
        }

        final String href = getAttributeValue(eLink, AtomLinkAttribute.HREF);
        if (href != null) {
            link.setHref(href);
        }

        final String title = getAttributeValue(eLink, AtomLinkAttribute.TITLE);
        if (title != null) {
            link.setTitle(title);
        }

        final String hrefLang = getAttributeValue(eLink, AtomLinkAttribute.HREF_LANG);
        if (hrefLang != null) {
            link.setHreflang(hrefLang);
        }

        final String length = getAttributeValue(eLink, AtomLinkAttribute.LENGTH);
        if (length != null) {
            final Long val = NumberParser.parseLong(length);
            if (val != null) {
                link.setLength(val.longValue());
            }
        }

        return link;

    }

    protected String getAttributeValue(final Element e, final String attributeName) {
        Attribute attr = e.getAttribute(attributeName);
        if (attr == null) {
            attr = e.getAttribute(attributeName, NS);
        }
        if (attr != null) {
            return attr.getValue();
        } else {
            return null;
        }
    }

    private SyndPerson parsePerson(Element element) {
        final SyndPerson person = new SyndPersonImpl();

        final Element name = element.getChild("name", NS);
        if (name != null && name.getValue() != null) {
            person.setName(name.getValue().trim());
        }

        final Element email = element.getChild("email", NS);
        if (email != null && email.getValue() != null) {
            person.setEmail(email.getValue().trim());
        }

        final Element uri = element.getChild("uri", NS);
        if (uri != null && uri.getValue() != null) {
            person.setUri(uri.getValue().trim());
        }

        return person;
    }

}
