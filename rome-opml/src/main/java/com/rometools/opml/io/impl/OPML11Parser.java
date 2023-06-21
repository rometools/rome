/*
 * Opml11Parser.java
 *
 * Created on April 19, 2023, 11:49 PM
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

import java.util.Locale;

import org.jdom2.Document;
import org.jdom2.Element;

import com.rometools.opml.feed.opml.Cloud;
import com.rometools.opml.feed.opml.Opml;
import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.io.FeedException;

public class OPML11Parser extends OPML10Parser {

    public OPML11Parser() {
        super("opml_1.1");
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

        return e.getName().equals("opml")
                && (null != e.getChild("head") && 
                    null != e.getChild("head").getChild("cloud") ||
                    null != e.getAttributeValue("version") &&
                    "1.1".equals(e.getAttributeValue("version")));
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
        opml.setFeedType("opml_1.1");

        final Element root = document.getRootElement();
        final Element head = root.getChild("head");
        final Element cloud = head.getChild("cloud");

        if (null != cloud) {
            if (null != cloud.getAttribute("domain")) {
                checkNullCloud(opml);
                opml.getCloud().setDomain(cloud.getAttributeValue("domain"));
            }
            if (null != cloud.getAttribute("port")) {
                checkNullCloud(opml);
                opml.getCloud().setPort(cloud.getAttributeValue("port"));
            }
            if (null != cloud.getAttribute("path")) {
                checkNullCloud(opml);
                opml.getCloud().setPath(cloud.getAttributeValue("path"));
            }
            if (null != cloud.getAttribute("registerProcedure")) {
                checkNullCloud(opml);
                opml.getCloud().setRegisterProcedure(cloud.getAttributeValue("registerProcedure"));
            }
            if (null != cloud.getAttribute("protocol")) {
                checkNullCloud(opml);
                opml.getCloud().setProtocol(cloud.getAttributeValue("protocol"));
            }
        }

        return opml;
    }

    private void checkNullCloud(Opml opml) {
        if (null == opml.getCloud()) {
            opml.setCloud(new Cloud());
        }
    }
}
