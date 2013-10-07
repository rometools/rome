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
package com.sun.syndication.io.impl;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.opml.Attribute;
import com.sun.syndication.feed.opml.Opml;
import com.sun.syndication.feed.opml.Outline;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.impl.DateParser;

import org.jdom2.Document;
import org.jdom2.Element;

import java.util.List;

/**
 * 
 * @author cooper
 */
public class OPML20Parser extends OPML10Parser {
    /** Creates a new instance of Opml20Parser */
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
    public boolean isMyType(Document document) {
        Element e = document.getRootElement();

        if (e.getName().equals("opml")
                && (((e.getChild("head") != null) && (e.getChild("head").getChild("docs") != null))
                        || ((e.getAttributeValue("version") != null) && e.getAttributeValue("version").equals("2.0")) || ((e.getChild("head") != null) && (e
                        .getChild("head").getChild("ownerId") != null)))) {
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
    public WireFeed parse(Document document, boolean validate) throws IllegalArgumentException, FeedException {
        Opml opml;
        opml = (Opml) super.parse(document, validate);

        Element head = document.getRootElement().getChild("head");

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
    protected Outline parseOutline(Element e, boolean validate) throws FeedException {
        Outline retValue;

        retValue = super.parseOutline(e, validate);

        if (e.getAttributeValue("created") != null) {
            retValue.setCreated(DateParser.parseRFC822(e.getAttributeValue("created")));
        }

        List atts = retValue.getAttributes();

        for (int i = 0; i < atts.size(); i++) {
            Attribute a = (Attribute) atts.get(i);

            if (a.getName().equals("created")) {
                retValue.getAttributes().remove(a);

                break;
            }
        }

        return retValue;
    }
}
