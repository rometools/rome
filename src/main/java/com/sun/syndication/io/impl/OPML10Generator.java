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
package com.sun.syndication.io.impl;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.opml.Attribute;
import com.sun.syndication.feed.opml.Opml;
import com.sun.syndication.feed.opml.Outline;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedGenerator;

import org.jdom.Document;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class OPML10Generator extends BaseWireFeedGenerator implements WireFeedGenerator {
    /** Creates a new instance of Opml10Generator */
    public OPML10Generator() {
        super("opml_1.0");
    }

    public OPML10Generator(String type) {
        super(type);
    }

    /**
     * Creates an XML document (JDOM) for the given feed bean.
     * <p>
     *
     * @param feed the feed bean to generate the XML document from.
     * @return the generated XML document (JDOM).
     * @throws IllegalArgumentException thrown if the type of the given feed bean does not
     *         match with the type of the WireFeedGenerator.
     * @throws FeedException thrown if the XML Document could not be created.
     */
    public Document generate(WireFeed feed) throws IllegalArgumentException, FeedException {
        if (!(feed instanceof Opml)) {
            throw new IllegalArgumentException("Not an OPML file");
        }

        Opml opml = (Opml) feed;
        Document doc = new Document();
        Element root = new Element("opml");
        doc.addContent(root);

        Element head = generateHead(opml);

        if (head != null) {
            root.addContent(head);
        }

        Element body = new Element("body");
        root.addContent(body);
        super.generateFeedModules(opml.getModules(), root);
        body.addContent(generateOutlines(opml.getOutlines()));

        return doc;
    }

    protected boolean addNotNullAttribute(Element target, String name, Object value) {
        if ((target == null) || (name == null) || (value == null)) {
            return false;
        }

        target.setAttribute(name, value.toString());

        return true;
    }

    protected boolean addNotNullSimpleElement(Element target, String name, Object value) {
        if ((target == null) || (name == null) || (value == null)) {
            return false;
        }

        Element e = new Element(name);
        e.addContent(value.toString());
        target.addContent(e);

        return true;
    }

    protected Element generateHead(Opml opml) {
        Element head = new Element("head");
        boolean hasHead = false;

        if (opml.getCreated() != null) {
            hasHead = addNotNullSimpleElement(head, "dateCreated", DateParser.formatRFC822(opml.getCreated()));
        }

        hasHead = addNotNullSimpleElement(head, "expansionState", intArrayToCsvString(opml.getExpansionState()));

        if (opml.getModified() != null) {
            hasHead = addNotNullSimpleElement(head, "dateModified", DateParser.formatRFC822(opml.getModified()));
        }

        hasHead = addNotNullSimpleElement(head, "ownerEmail", opml.getOwnerEmail());
        hasHead = addNotNullSimpleElement(head, "ownerName", opml.getOwnerName());
        hasHead = addNotNullSimpleElement(head, "title", opml.getTitle());
        hasHead = addNotNullSimpleElement(head, "vertScrollState", opml.getVerticalScrollState());
        hasHead = addNotNullSimpleElement(head, "windowBottom", opml.getWindowBottom());
        hasHead = addNotNullSimpleElement(head, "windowLeft", opml.getWindowLeft());
        hasHead = addNotNullSimpleElement(head, "windowRight", opml.getWindowRight());
        hasHead = addNotNullSimpleElement(head, "windowTop", opml.getWindowTop());

        if (hasHead) {
            return head;
        } else {
            return null;
        }
    }

    protected Element generateOutline(Outline outline) {
        Element e = new Element("outline");
        addNotNullAttribute(e, "text", outline.getText());
        addNotNullAttribute(e, "type", outline.getType());
        addNotNullAttribute(e, "title", outline.getTitle());

        if (outline.isBreakpoint()) {
            addNotNullAttribute(e, "isBreakpoint", "true");
        }

        if (outline.isComment()) {
            addNotNullAttribute(e, "isComment", "true");
        }

        List atts = Collections.synchronizedList(outline.getAttributes());

        for (int i = 0; i < atts.size(); i++) {
            Attribute att = (Attribute) atts.get(i);
            addNotNullAttribute(e, att.getName(), att.getValue());
        }

        super.generateItemModules(outline.getModules(), e);
        e.addContent(generateOutlines(outline.getChildren()));

        return e;
    }

    protected List generateOutlines(List outlines) {
        ArrayList elements = new ArrayList();

        for (int i = 0; (outlines != null) && (i < outlines.size()); i++) {
            elements.add(generateOutline((Outline) outlines.get(i)));
        }

        return elements;
    }

    protected String intArrayToCsvString(int[] value) {
        if ((value == null) || (value.length == 0)) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(value[0]);

        for (int i = 1; i < value.length; i++) {
            sb.append(",");
            sb.append(value[i]);
        }

        return sb.toString();
    }
}
