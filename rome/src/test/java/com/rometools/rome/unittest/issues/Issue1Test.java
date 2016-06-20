/*
 *  Copyright 2011 robert.cooper.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package com.rometools.rome.unittest.issues;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

import org.jdom2.input.SAXBuilder;

import com.rometools.rome.io.XmlReader;
import com.rometools.rome.io.impl.XmlFixerReader;
import com.rometools.rome.unittest.SyndFeedTest;

public class Issue1Test extends SyndFeedTest {
    private static final String XML_PROLOG = "<?xml version=\"1.0\" ?>";

    public Issue1Test() {
        super("rss_2.0", "jira_issue1.xml");
    }

    public void testAmpHandling() throws Exception {
        final String input = "&amp; &aa &";
        final BufferedReader reader = new BufferedReader(new XmlFixerReader(new StringReader(input)));
        final String output = reader.readLine();
        reader.close();
        assertEquals("&amp; &amp;aa &amp;", output);
    }

    public void testHtmlEntities() throws Exception {
        testValidEntities("<hello></hello>");
        testValidEntities(XML_PROLOG + "<hello></hello>");
        testValidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello></hello>");

        testValidEntities("<hello>&apos;&yen;&#250;&yen;</hello>");
        testValidEntities(XML_PROLOG + "<hello>&apos;&yen;&#250;&yen;</hello>");
        testValidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello>&apos;&yen;&#250;&yen;</hello>");

        testValidEntities("<hello>&Pi;&Rho;#913;&Rho;</hello>");
        testValidEntities(XML_PROLOG + "<hello>&Pi;&Rho;&#913;&Rho;</hello>");
        testValidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello>&Pi;&Rho;&#913;&Rho;</hello>");

        testValidEntities("<hello>&OElig;&mdash;&#8211;&mdash;</hello>");
        testValidEntities(XML_PROLOG + "<hello>&OElig;&mdash;&#8211;&mdash;</hello>");
        testValidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello>&OElig;&mdash;&#8211;&mdash;</hello>");

        testInvalidEntities("<hello>&apos;&yexn;&#250;&yen;</hello>");
        testInvalidEntities(XML_PROLOG + "<hello>&apos;&yexn;&#250;&yen;</hello>");
        testInvalidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello>&apos;&yexn;&#250;&yen;</hello>");

        testInvalidEntities("<hello>&Pi;&Rhox;#913;&Rho;</hello>");
        testInvalidEntities(XML_PROLOG + "<hello>&Pi;&Rhox;&#913;&Rho;</hello>");
        testInvalidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello>&Pi;&Rhox;&#913;&Rho;</hello>");

        testInvalidEntities("<hello>&apos;&yen;&#2x50;&yen;</hello>");
        testInvalidEntities(XML_PROLOG + "<hello>&apos;&yen;&#2x50;&yen;</hello>");
        testInvalidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello>&apos;&yen;&#2x50;&yen;</hello>");

        testInvalidEntities("<hello>&Pi;&Rho;&#9x13;&Rho;</hello>");
        testInvalidEntities(XML_PROLOG + "<hello>&Pi;&Rho;&#9x13;&Rho;</hello>");
        testInvalidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello>&Pi;&Rho;&#9x13;&Rho;</hello>");
    }

    public void testTrim() throws Exception {
        testValidTrim("", "<hello></hello>");
        testValidTrim("", XML_PROLOG + "<hello></hello>");
        testValidTrim(" ", "<hello></hello>");
        testValidTrim(" ", XML_PROLOG + "<hello></hello>");
        testValidTrim(" \n", "<hello></hello>");
        testValidTrim(" \n", XML_PROLOG + "<hello></hello>");
        testValidTrim("<!-- - -- -->", "<hello></hello>");
        testValidTrim("<!-- - -- -->", XML_PROLOG + "<hello></hello>");
        testValidTrim(" <!-- - -- -->", "<hello></hello>");
        testValidTrim(" <!-- - -- -->", XML_PROLOG + "<hello></hello>");
        testValidTrim(" <!-- - -- --> ", "<hello></hello>");
        testValidTrim(" <!-- - -- --> ", XML_PROLOG + "<hello></hello>");
        testValidTrim(" <!-- - -- --> <!-- - -- --> ", "<hello></hello>");
        testValidTrim(" <!-- - -- --> <!-- - -- --> ", XML_PROLOG + "<hello></hello>");
        testValidTrim(" <!-- - -- --> \n <!-- - -- --> ", "<hello></hello>");
        testValidTrim(" <!-- - -- --> \n <!-- - -- --> ", XML_PROLOG + "<hello></hello>");

        // TODO lorenzo.sm: This test was added to trim \r char (as with \n).
        // Source of "bad" RSS http://www.diariohorizonte.com/rss/71/deportes
        testValidTrim("\r\n<!-- hackedString Clean-->", XML_PROLOG + "<hello></hello>");

        testInvalidTrim("x", "<hello></hello>");
        testInvalidTrim("x", XML_PROLOG + "<hello></hello>");
        testInvalidTrim(" x", "<hello></hello>");
        testInvalidTrim(" x", XML_PROLOG + "<hello></hello>");
        testInvalidTrim(" x\n", "<hello></hello>");
        testInvalidTrim(" x\n", XML_PROLOG + "<hello></hello>");
        testInvalidTrim("<!-- - -- - ->", "<hello></hello>");
        testInvalidTrim("<!-- - -- - ->", XML_PROLOG + "<hello></hello>");
        testInvalidTrim(" <!-- - -- -- >", "<hello></hello>");
        testInvalidTrim(" <!-- - -- -- >", XML_PROLOG + "<hello></hello>");
        testInvalidTrim(" <!-- - -- -->x ", "<hello></hello>");
        testInvalidTrim(" <!-- - -- -->x ", XML_PROLOG + "<hello></hello>");
        testInvalidTrim(" <!-- - -- --> x <!-- - -- --> ", "<hello></hello>");
        testInvalidTrim(" <!-- - -- --> x <!-- - -- --> ", XML_PROLOG + "<hello></hello>");
        testInvalidTrim(" <!-- - -- --> x\n <!-- - -- --> ", "<hello></hello>");
        testInvalidTrim(" <!-- - -- --> x\n <!-- - -- --> ", XML_PROLOG + "<hello></hello>");
    }

    // XML Stream generator
    protected InputStream getStream(final String garbish, final String xmlDoc) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        final Writer writer = new OutputStreamWriter(baos);
        writer.write(garbish);
        writer.write(xmlDoc);
        writer.close();

        return new ByteArrayInputStream(baos.toByteArray());
    }

    protected void testInvalidEntities(final String xmlDoc) throws Exception {
        try {
            testXmlParse("", xmlDoc);
            assertTrue(false);
        } catch (final Exception ex) {
        }
    }

    protected void testInvalidTrim(final String garbish, final String xmlDoc) throws Exception {
        try {
            testXmlParse(garbish, xmlDoc);
            assertTrue(false);
        } catch (final Exception ex) {
        }
    }

    protected void testValidEntities(final String xmlDoc) throws Exception {
        testXmlParse("", xmlDoc);
    }

    protected void testValidTrim(final String garbish, final String xmlDoc) throws Exception {
        testXmlParse(garbish, xmlDoc);
    }

    protected void testXmlParse(final String garbish, final String xmlDoc) throws Exception {
        final InputStream is = getStream(garbish, xmlDoc);
        Reader reader = new XmlReader(is);
        reader = new XmlFixerReader(reader);

        final SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.build(reader);
    }
}
