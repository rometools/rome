/*
 * Opml10Generator.java
 *
 * Created on April 24, 2006, 11:35 PM
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.jdom2.Document;
import org.jdom2.Element;

import com.rometools.opml.feed.opml.Attribute;
import com.rometools.opml.feed.opml.Opml;
import com.rometools.opml.feed.opml.Outline;
import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.WireFeedGenerator;
import com.rometools.rome.io.impl.BaseWireFeedGenerator;
import com.rometools.rome.io.impl.DateParser;

public class OPML10Generator extends BaseWireFeedGenerator implements WireFeedGenerator {

    public OPML10Generator() {
        super("opml_1.0");
    }

    public OPML10Generator(final String type) {
        super(type);
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

        if (!(feed instanceof Opml)) {
            throw new IllegalArgumentException("Not an OPML file");
        }

        final Opml opml = (Opml) feed;
        final Document doc = new Document();
        final Element root = new Element("opml");
        root.setAttribute("version", "1.0");
        doc.addContent(root);

        final Element head = generateHead(opml);

        if (head != null) {
            root.addContent(head);
        }

        final Element body = new Element("body");
        root.addContent(body);
        super.generateFeedModules(opml.getModules(), root);
        body.addContent(generateOutlines(opml.getOutlines()));

        return doc;
    }

    protected boolean addNotNullAttribute(final Element target, final String name, final Object value) {
        if (target == null || name == null || value == null) {
            return false;
        }
        target.setAttribute(name, value.toString());
        return true;
    }

    protected boolean addNotNullSimpleElement(final Element target, final String name, final Object value) {
        if (target == null || name == null || value == null) {
            return false;
        }

        final Element e = new Element(name);
        e.addContent(value.toString());
        target.addContent(e);

        return true;
    }

    protected Element generateHead(final Opml opml) {
        final Element head = new Element("head");
        boolean hasHead = false;

        if (opml.getCreated() != null) {
            hasHead |= addNotNullSimpleElement(head, "dateCreated", DateParser.formatRFC822(opml.getCreated(), Locale.US));
        }

        hasHead |= addNotNullSimpleElement(head, "expansionState", intArrayToCsvString(opml.getExpansionState()));

        if (opml.getModified() != null) {
            hasHead |= addNotNullSimpleElement(head, "dateModified", DateParser.formatRFC822(opml.getModified(), Locale.US));
        }

        hasHead |= addNotNullSimpleElement(head, "ownerEmail", opml.getOwnerEmail());
        hasHead |= addNotNullSimpleElement(head, "ownerName", opml.getOwnerName());
        hasHead |= addNotNullSimpleElement(head, "title", opml.getTitle());
        hasHead |= addNotNullSimpleElement(head, "vertScrollState", opml.getVerticalScrollState());
        hasHead |= addNotNullSimpleElement(head, "windowBottom", opml.getWindowBottom());
        hasHead |= addNotNullSimpleElement(head, "windowLeft", opml.getWindowLeft());
        hasHead |= addNotNullSimpleElement(head, "windowRight", opml.getWindowRight());
        hasHead |= addNotNullSimpleElement(head, "windowTop", opml.getWindowTop());

        if (hasHead) {
            return head;
        } else {
            return null;
        }
    }

    protected Element generateOutline(final Outline outline) {
        final Element e = new Element("outline");
        addNotNullAttribute(e, "text", outline.getText());
        addNotNullAttribute(e, "type", outline.getType());
        addNotNullAttribute(e, "title", outline.getTitle());

        if (outline.isBreakpoint()) {
            addNotNullAttribute(e, "isBreakpoint", "true");
        }

        if (outline.isComment()) {
            addNotNullAttribute(e, "isComment", "true");
        }

        final List<Attribute> atts = Collections.synchronizedList(outline.getAttributes());

        for (int i = 0; i < atts.size(); i++) {
            final Attribute att = atts.get(i);
            addNotNullAttribute(e, att.getName(), att.getValue());
        }

        super.generateItemModules(outline.getModules(), e);
        e.addContent(generateOutlines(outline.getChildren()));

        return e;
    }

    protected List<Element> generateOutlines(final List<Outline> outlines) {
        final ArrayList<Element> elements = new ArrayList<Element>();
        for (int i = 0; outlines != null && i < outlines.size(); i++) {
            elements.add(generateOutline(outlines.get(i)));
        }
        return elements;
    }

    protected String intArrayToCsvString(final int[] value) {
        if (value == null || value.length == 0) {
            return null;
        }

        final StringBuffer sb = new StringBuffer();
        sb.append(value[0]);

        for (int i = 1; i < value.length; i++) {
            sb.append(",");
            sb.append(value[i]);
        }

        return sb.toString();
    }
}
