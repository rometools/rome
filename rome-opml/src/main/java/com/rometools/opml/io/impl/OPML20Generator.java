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

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.jdom2.Document;
import org.jdom2.Element;

import com.rometools.opml.feed.opml.Opml;
import com.rometools.opml.feed.opml.Outline;
import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.impl.DateParser;

/**
 * Generator for OPML 2.0 documents.
 *
 * @see <a href="http://dev.opml.org/spec2.html">http://dev.opml.org/spec2.html</a>
 */
public class OPML20Generator extends OPML10Generator {

    public OPML20Generator() {
    }

    /**
     * Returns the type of feed the generator creates.
     *
     * @return the type of feed the generator creates.
     * @see WireFeed for details on the format of this string.
     */
    @Override
    public String getType() {
        return "opml_2.0";
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
        document.getRootElement().setAttribute("version", "2.0");
        return document;
    }

    @Override
    protected Element generateHead(final Opml opml) {

        final Element docsElement = new Element("docs");
        docsElement.setText(opml.getDocs());

        final Element headElement = super.generateHead(opml);
        headElement.addContent(docsElement);
        return headElement;

    }

    @Override
    protected Element generateOutline(final Outline outline) {

        final Element outlineElement = super.generateOutline(outline);

        if (outline.getCreated() != null) {
            outlineElement.setAttribute("created", DateParser.formatRFC822(outline.getCreated(), Locale.US));
        }

        final List<String> categories = outline.getCategories();
        final String categoryValue = generateCategoryValue(categories);
        addNotNullAttribute(outlineElement, "category", categoryValue);

        return outlineElement;

    }

    private String generateCategoryValue(final Collection<String> categories) {

        final StringBuilder builder = new StringBuilder();

        boolean first = true;
        for (final String category : categories) {
            if (category != null && !category.trim().isEmpty()) {
                if (first) {
                    first = false;
                } else {
                    builder.append(",");
                }
                builder.append(category.trim());
            }
        }

        if (builder.length() > 0) {
            return builder.toString();
        } else {
            return null;
        }

    }

}
