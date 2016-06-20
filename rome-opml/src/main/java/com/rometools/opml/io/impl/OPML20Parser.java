/*
 * Opml20Parser.java
 *
 * Created on April 25, 2006, 1:04 AM
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
package com.rometools.opml.io.impl;

import java.util.List;
import java.util.Locale;

import org.jdom2.Document;
import org.jdom2.Element;

import com.rometools.opml.feed.opml.Attribute;
import com.rometools.opml.feed.opml.Opml;
import com.rometools.opml.feed.opml.Outline;
import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.impl.DateParser;

public class OPML20Parser extends OPML10Parser {

    public OPML20Parser() {
        super("opml_2.0");
    }

    /**
     * Inspects an XML Document (JDOM) to check if it can parse it.
     * <p>
     * It checks if the given document if the type of feeds the parser understands.
     * <p>
     *
     * @param document XML Document (JDOM) to check if it can be parsed by this parser.
     * @return <b>true</b> if the parser know how to parser this feed, <b>false</b> otherwise.
     */
    @Override
    public boolean isMyType(final Document document) {
        final Element e = document.getRootElement();

        if (e.getName().equals("opml")
                && (e.getChild("head") != null && e.getChild("head").getChild("docs") != null || e.getAttributeValue("version") != null
                        && e.getAttributeValue("version").equals("2.0") || e.getChild("head") != null && e.getChild("head").getChild("ownerId") != null)) {
            return true;
        }

        return false;
    }

    /**
     * Parses an XML document (JDOM Document) into a feed bean.
     * <p>
     *
     * @param document XML document (JDOM) to parse.
     * @param validate indicates if the feed should be strictly validated (NOT YET IMPLEMENTED).
     * @return the resulting feed bean.
     * @throws IllegalArgumentException thrown if the parser cannot handle the given feed type.
     * @throws FeedException thrown if a feed bean cannot be created out of the XML document (JDOM).
     */
    @Override
    public WireFeed parse(final Document document, final boolean validate, final Locale locale) throws IllegalArgumentException, FeedException {
        Opml opml;
        opml = (Opml) super.parse(document, validate, locale);

        final Element head = document.getRootElement().getChild("head");

        if (head != null) {
            opml.setOwnerId(head.getChildTextTrim("ownerId"));
            opml.setDocs(head.getChildTextTrim("docs"));

            if (opml.getDocs() == null) {
                opml.setDocs("http://www.opml.org/spec2");
            }
        }

        opml.setFeedType("opml_2.0");

        return opml;
    }

    @Override
    protected Outline parseOutline(final Element e, final boolean validate, final Locale locale) throws FeedException {
        Outline retValue;

        retValue = super.parseOutline(e, validate, locale);

        if (e.getAttributeValue("created") != null) {
            retValue.setCreated(DateParser.parseRFC822(e.getAttributeValue("created"), locale));
        }

        final List<Attribute> atts = retValue.getAttributes();

        for (int i = 0; i < atts.size(); i++) {
            final Attribute a = atts.get(i);

            if (a.getName().equals("created")) {
                retValue.getAttributes().remove(a);

                break;
            }
        }

        return retValue;
    }
}
