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
package com.rometools.opml.io.impl;

import org.jdom2.Document;
import org.jdom2.Element;

import com.rometools.opml.feed.opml.Opml;
import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.io.FeedException;

/**
 * Generator for OPML 1.1 documents.
 *
 * @see <a href="http://dev.opml.org/spec2.html">http://dev.opml.org/spec2.html</a>
 */
public class OPML11Generator extends OPML10Generator {

    public OPML11Generator() {
    }

    /**
     * Returns the type of feed the generator creates.
     *
     * @return the type of feed the generator creates.
     * @see WireFeed for details on the format of this string.
     */
    @Override
    public String getType() {
        return "opml_1.1";
    }

    /**
     * Creates an XML document (JDOM) for the given feed bean.
     *
     * @param feed the feed bean to generate the XML document from.
     * @return the generated XML document (JDOM).
     * @throws IllegalArgumentException thrown if the type of the given feed bean does not match with the type of the
     *             WireFeedGenerator.
     * @throws FeedException thrown if the XML Document could not be created.
     */
    @Override
    public Document generate(final WireFeed feed) throws IllegalArgumentException, FeedException {
        final Document document = super.generate(feed);
        document.getRootElement().setAttribute("version", "1.1");
        return document;
    }

    @Override
    protected Element generateHead(final Opml opml) {

        final Element headElement = super.generateHead(opml);

        if (null != opml && null != opml.getCloud()) {
            final Element cloudElement = new Element("cloud");
            addNotNullAttribute(cloudElement, "domain", opml.getCloud().getDomain());
            addNotNullAttribute(cloudElement, "port", opml.getCloud().getPort());
            addNotNullAttribute(cloudElement, "path", opml.getCloud().getPath());
            addNotNullAttribute(cloudElement, "registerProcedure", opml.getCloud().getRegisterProcedure());
            addNotNullAttribute(cloudElement, "protocol", opml.getCloud().getProtocol());
            headElement.addContent(cloudElement);
        }
        
        return headElement;

    }

}
