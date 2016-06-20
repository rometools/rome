/*
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

package com.rometools.modules.opensearch.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.Parent;

import com.rometools.modules.opensearch.OpenSearchModule;
import com.rometools.modules.opensearch.entity.OSQuery;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;

public class OpenSearchModuleParser implements ModuleParser {

    private static final Namespace OS_NS = Namespace.getNamespace("opensearch", OpenSearchModule.URI);

    @Override
    public String getNamespaceUri() {
        return OpenSearchModule.URI;
    }

    @Override
    public Module parse(final Element dcRoot, final Locale locale) {

        final URL baseURI = findBaseURI(dcRoot);

        boolean foundSomething = false;
        final OpenSearchModule osm = new OpenSearchModuleImpl();

        Element e = dcRoot.getChild("totalResults", OS_NS);

        if (e != null) {
            try {
                osm.setTotalResults(Integer.parseInt(e.getText()));
                foundSomething = true;
            } catch (final NumberFormatException ex) {
                // Ignore setting the field and post a warning
                System.err.println("Warning: The element totalResults must be an integer value: " + ex.getMessage());
            }
        }

        e = dcRoot.getChild("itemsPerPage", OS_NS);
        if (e != null) {
            try {
                osm.setItemsPerPage(Integer.parseInt(e.getText()));
                foundSomething = true;
            } catch (final NumberFormatException ex) {
                // Ignore setting the field and post a warning
                System.err.println("Warning: The element itemsPerPage must be an integer value: " + ex.getMessage());
            }
        }

        e = dcRoot.getChild("startIndex", OS_NS);
        if (e != null) {
            try {
                osm.setStartIndex(Integer.parseInt(e.getText()));
                foundSomething = true;
            } catch (final NumberFormatException ex) {
                // Ignore setting the field and post a warning
                System.err.println("Warning: The element startIndex must be an integer value: " + ex.getMessage());
            }
        }

        final List<Element> queries = dcRoot.getChildren("Query", OS_NS);

        if (queries != null && !queries.isEmpty()) {

            // Create the OSQuery list
            final List<OSQuery> osqList = new LinkedList<OSQuery>();

            for (final Iterator<Element> iter = queries.iterator(); iter.hasNext();) {
                e = iter.next();
                osqList.add(parseQuery(e));
            }

            osm.setQueries(osqList);
        }

        e = dcRoot.getChild("link", OS_NS);

        if (e != null) {
            osm.setLink(parseLink(e, baseURI));
        }

        return foundSomething ? osm : null;
    }

    private static OSQuery parseQuery(final Element e) {

        final OSQuery query = new OSQuery();

        String att = e.getAttributeValue("role");
        query.setRole(att);

        att = e.getAttributeValue("osd");
        query.setOsd(att);

        att = e.getAttributeValue("searchTerms");
        query.setSearchTerms(att);

        att = e.getAttributeValue("title");
        query.setTitle(att);

        try {

            // someones mistake should not cause the parser to fail, since these
            // are only optional attributes

            att = e.getAttributeValue("totalResults");
            if (att != null) {
                query.setTotalResults(Integer.parseInt(att));
            }

            att = e.getAttributeValue("startPage");
            if (att != null) {
                query.setStartPage(Integer.parseInt(att));
            }

        } catch (final NumberFormatException ex) {
            System.err.println("Warning: Exception caught while trying to parse a non-numeric Query attribute " + ex.getMessage());
        }

        return query;
    }

    private static Link parseLink(final Element e, final URL baseURI) {

        final Link link = new Link();

        String att = e.getAttributeValue("rel");// getAtomNamespace()); DONT
        // KNOW WHY DOESN'T WORK

        if (att != null) {
            link.setRel(att);
        }

        att = e.getAttributeValue("type");// getAtomNamespace()); DONT KNOW WHY
        // DOESN'T WORK

        if (att != null) {
            link.setType(att);
        }

        att = e.getAttributeValue("href");// getAtomNamespace()); DONT KNOW WHY
        // DOESN'T WORK

        if (att != null) {

            if (isRelativeURI(att)) { //
                link.setHref(resolveURI(baseURI, e, ""));
            } else {
                link.setHref(att);
            }
        }

        att = e.getAttributeValue("hreflang");// getAtomNamespace()); DONT KNOW
        // WHY DOESN'T WORK

        if (att != null) {
            link.setHreflang(att);
        }

        att = e.getAttributeValue("length");// getAtomNamespace()); DONT KNOW
        // WHY DOESN'T WORK

        return link;
    }

    private static boolean isRelativeURI(final String uri) {
        if (uri.startsWith("http://") || uri.startsWith("https://") || uri.startsWith("/")) {
            return false;
        }
        return true;
    }

    /**
     * Use xml:base attributes at feed and entry level to resolve relative links
     */
    private static String resolveURI(final URL baseURI, final Parent parent, String url) {
        url = url.equals(".") || url.equals("./") ? "" : url;
        if (isRelativeURI(url) && parent != null && parent instanceof Element) {
            final Attribute baseAtt = ((Element) parent).getAttribute("base", Namespace.XML_NAMESPACE);
            String xmlBase = baseAtt == null ? "" : baseAtt.getValue();
            if (!isRelativeURI(xmlBase) && !xmlBase.endsWith("/")) {
                xmlBase = xmlBase.substring(0, xmlBase.lastIndexOf("/") + 1);
            }
            return resolveURI(baseURI, parent.getParent(), xmlBase + url);
        } else if (isRelativeURI(url) && parent == null) {
            return baseURI + url;
        } else if (baseURI != null && url.startsWith("/")) {
            String hostURI = baseURI.getProtocol() + "://" + baseURI.getHost();
            if (baseURI.getPort() != baseURI.getDefaultPort()) {
                hostURI = hostURI + ":" + baseURI.getPort();
            }
            return hostURI + url;
        }
        return url;
    }

    /** Use feed links and/or xml:base attribute to determine baseURI of feed */
    private static URL findBaseURI(final Element root) {
        URL baseURI = null;
        final List<Element> linksList = root.getChildren("link", OS_NS);
        if (linksList != null) {
            for (final Element element : linksList) {
                final Element link = element;
                if (!root.equals(link.getParent())) {
                    break;
                }
                String href = link.getAttribute("href").getValue();
                if (link.getAttribute("rel", OS_NS) == null || link.getAttribute("rel", OS_NS).getValue().equals("alternate")) {
                    href = resolveURI(null, link, href);
                    try {
                        baseURI = new URL(href);
                        break;
                    } catch (final MalformedURLException e) {
                        System.err.println("Base URI is malformed: " + href);
                    }
                }
            }
        }
        return baseURI;
    }
}
