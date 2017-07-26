/*
 * Copyright 2004 Sun Microsystems, Inc.
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
package com.rometools.opml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import junit.framework.TestCase;

import org.jdom2.input.SAXBuilder;

import com.rometools.rome.io.XmlReader;
import com.rometools.rome.io.impl.XmlFixerReader;

public class TestXmlFixerReader extends TestCase {
    private static final String XML_PROLOG = "<?xml version=\"1.0\" ?>";

    public void testTrim() throws Exception {
        _testValidTrim("", "<hello></hello>");
        _testValidTrim("", XML_PROLOG + "<hello></hello>");
        _testValidTrim(" ", "<hello></hello>");
        _testValidTrim(" ", XML_PROLOG + "<hello></hello>");
        _testValidTrim(" \n", "<hello></hello>");
        _testValidTrim(" \n", XML_PROLOG + "<hello></hello>");
        _testValidTrim("<!-- - -- -->", "<hello></hello>");
        _testValidTrim("<!-- - -- -->", XML_PROLOG + "<hello></hello>");
        _testValidTrim(" <!-- - -- -->", "<hello></hello>");
        _testValidTrim(" <!-- - -- -->", XML_PROLOG + "<hello></hello>");
        _testValidTrim(" <!-- - -- --> ", "<hello></hello>");
        _testValidTrim(" <!-- - -- --> ", XML_PROLOG + "<hello></hello>");
        _testValidTrim(" <!-- - -- --> <!-- - -- --> ", "<hello></hello>");
        _testValidTrim(" <!-- - -- --> <!-- - -- --> ", XML_PROLOG + "<hello></hello>");
        _testValidTrim(" <!-- - -- --> \n <!-- - -- --> ", "<hello></hello>");
        _testValidTrim(" <!-- - -- --> \n <!-- - -- --> ", XML_PROLOG + "<hello></hello>");

        _testInvalidTrim("x", "<hello></hello>");
        _testInvalidTrim("x", XML_PROLOG + "<hello></hello>");
        _testInvalidTrim(" x", "<hello></hello>");
        _testInvalidTrim(" x", XML_PROLOG + "<hello></hello>");
        _testInvalidTrim(" x\n", "<hello></hello>");
        _testInvalidTrim(" x\n", XML_PROLOG + "<hello></hello>");
        _testInvalidTrim("<!-- - -- - ->", "<hello></hello>");
        _testInvalidTrim("<!-- - -- - ->", XML_PROLOG + "<hello></hello>");
        _testInvalidTrim(" <!-- - -- -- >", "<hello></hello>");
        _testInvalidTrim(" <!-- - -- -- >", XML_PROLOG + "<hello></hello>");
        _testInvalidTrim(" <!-- - -- -->x ", "<hello></hello>");
        _testInvalidTrim(" <!-- - -- -->x ", XML_PROLOG + "<hello></hello>");
        _testInvalidTrim(" <!-- - -- --> x <!-- - -- --> ", "<hello></hello>");
        _testInvalidTrim(" <!-- - -- --> x <!-- - -- --> ", XML_PROLOG + "<hello></hello>");
        _testInvalidTrim(" <!-- - -- --> x\n <!-- - -- --> ", "<hello></hello>");
        _testInvalidTrim(" <!-- - -- --> x\n <!-- - -- --> ", XML_PROLOG + "<hello></hello>");
    }

    public void testHtmlEntities() throws Exception {
        _testValidEntities("<hello></hello>");
        _testValidEntities(XML_PROLOG + "<hello></hello>");
        _testValidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello></hello>");

        _testValidEntities("<hello>&apos;&yen;&#250;&yen;</hello>");
        _testValidEntities(XML_PROLOG + "<hello>&apos;&yen;&#250;&yen;</hello>");
        _testValidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello>&apos;&yen;&#250;&yen;</hello>");

        _testInvalidEntities("<hello>&apos;&yexn;&#250;&yen;</hello>");
        _testInvalidEntities(XML_PROLOG + "<hello>&apos;&yexn;&#250;&yen;</hello>");
        _testInvalidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello>&apos;&yexn;&#250;&yen;</hello>");

        _testInvalidEntities("<hello>&apos;&yen;&#2x50;&yen;</hello>");
        _testInvalidEntities(XML_PROLOG + "<hello>&apos;&yen;&#2x50;&yen;</hello>");
        _testInvalidEntities(" <!-- just in case -->\n" + XML_PROLOG + "<hello>&apos;&yen;&#2x50;&yen;</hello>");

    }

    protected void _testXmlParse(final String garbish, final String xmlDoc) throws Exception {
        final InputStream is = getStream(garbish, xmlDoc);
        Reader reader = new XmlReader(is);
        reader = new XmlFixerReader(reader);
        final SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.build(reader);
    }

    protected void _testValidTrim(final String garbish, final String xmlDoc) throws Exception {
        _testXmlParse(garbish, xmlDoc);
    }

    protected void _testInvalidTrim(final String garbish, final String xmlDoc) throws Exception {
        try {
            _testXmlParse(garbish, xmlDoc);
            assertTrue(false);
        } catch (final Exception ex) {
        }
    }

    protected void _testValidEntities(final String xmlDoc) throws Exception {
        _testXmlParse("", xmlDoc);
    }

    protected void _testInvalidEntities(final String xmlDoc) throws Exception {
        try {
            _testXmlParse("", xmlDoc);
            assertTrue(false);
        } catch (final Exception ex) {
        }
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

}
