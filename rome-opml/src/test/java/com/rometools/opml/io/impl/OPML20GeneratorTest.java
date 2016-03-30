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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.rometools.opml.feed.opml.Opml;
import com.rometools.opml.feed.opml.Outline;
import com.rometools.rome.io.WireFeedOutput;

public class OPML20GeneratorTest {

    @Test
    public void testOutputOfNullCategory() {
        assertThat(categoryOf((String) null).getLength(), is(equalTo(0)));
    }

    @Test
    public void testOutputOfEmptyCategory() {
        assertThat(categoryOf("").getLength(), is(equalTo(0)));
    }

    @Test
    public void testOutputOfBlankCategory() {
        assertThat(categoryOf(" ").getLength(), is(equalTo(0)));
    }

    @Test
    public void testOutputOfOneCategory() {
        assertThat(categoryValueOf("category1"), is(equalTo("category1")));
    }

    @Test
    public void testOutputOfMultipleCategories() {
        assertThat(categoryValueOf("category1", "category2"), is(equalTo("category1,category2")));
    }

    private NodeList categoryOf(final String... categories) {

        try {

            final Outline outline = new Outline("outline1", null);
            outline.setCategories(Arrays.asList(categories));

            final Opml opml = new Opml();
            opml.setFeedType("opml_2.0");
            opml.setTitle("title");
            opml.setOutlines(Arrays.asList(outline));

            final WireFeedOutput output = new WireFeedOutput();
            final String xml = output.outputString(opml);

            final Document document = XMLUnit.buildControlDocument(xml);
            return XMLUnit.newXpathEngine().getMatchingNodes("/opml/body/outline/@category", document);

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String categoryValueOf(final String... categories) {
        return categoryOf(categories).item(0).getNodeValue();
    }

}
