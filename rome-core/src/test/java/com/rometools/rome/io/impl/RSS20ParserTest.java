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
package com.rometools.rome.io.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.rometools.rome.io.WireFeedParser;

import org.jdom2.Document;
import org.jdom2.Element;
import org.junit.Before;
import org.junit.Test;

public class RSS20ParserTest {

    private WireFeedParser parser;
    private Document document;

    @Before
    public void setUp() throws Exception {
        parser = new RSS20Parser();
        document = new Document();
    }

    @Test
    public void testIsMyType() {
        document.setRootElement(new Element("rss").setAttribute("version", "2.0"));
        assertTrue(parser.isMyType(document));
    }

    @Test
    public void testIsMyTypeNotMyType() {
        document.setRootElement(new Element("rss").setAttribute("version", "1.0"));
        assertFalse(parser.isMyType(document));
    }

    @Test
    public void testIsMyTypeVersionWithSpaces() {
        document.setRootElement(new Element("rss").setAttribute("version", " 2.0 "));
        assertTrue(parser.isMyType(document));
    }

    @Test
    public void testIsMyTypeVersionWithTrailingText() {
        document.setRootElement(new Element("rss").setAttribute("version", "2.0test"));
        assertTrue(parser.isMyType(document));
    }

    @Test
    public void testIsMyTypeVersionAbsent() {
        document.setRootElement(new Element("rss"));
        assertTrue(parser.isMyType(document));
    }
}
