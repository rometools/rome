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
import java.io.Writer;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.rometools.rome.io.XmlReader;

public class TestXmlReader extends TestCase {

    public static void main(final String[] args) throws Exception {
        final TestXmlReader test = new TestXmlReader();
        test.testRawBom();
        test.testRawNoBom();
        test.testHttp();
    }

    protected void _testRawNoBomValid(final String encoding) throws Exception {

        // TODO review this test (XmlReader is never closed, test fails when using a new XmlReader
        // for each Assert)

        InputStream is = getXmlStream("no-bom", "xml", encoding, encoding);
        XmlReader xmlReader = new XmlReader(is, false);
        assertEquals(xmlReader.getEncoding(), "UTF-8");
        xmlReader.close();

        is = getXmlStream("no-bom", "xml-prolog", encoding, encoding);
        xmlReader = new XmlReader(is);
        assertEquals(xmlReader.getEncoding(), "UTF-8");
        xmlReader.close();

        is = getXmlStream("no-bom", "xml-prolog-encoding", encoding, encoding);
        xmlReader = new XmlReader(is);
        assertEquals(xmlReader.getEncoding(), encoding);
        xmlReader.close();

    }

    protected void _testRawNoBomInvalid(final String encoding) throws Exception {
        final InputStream is = getXmlStream("no-bom", "xml-prolog-encoding", encoding, encoding);
        try {
            final XmlReader xmlReader = new XmlReader(is, false);
            fail("It should have failed");
            xmlReader.close();
        } catch (final IOException ex) {
            assertTrue(ex.getMessage().indexOf("Invalid encoding,") > -1);
        }
    }

    public void testRawNoBom() throws Exception {
        _testRawNoBomValid("US-ASCII");
        _testRawNoBomValid("UTF-8");
        _testRawNoBomValid("ISO-8859-1");
    }

    protected void _testRawBomValid(final String encoding) throws Exception {
        final InputStream is = getXmlStream(encoding + "-bom", "xml-prolog-encoding", encoding, encoding);
        final XmlReader xmlReader = new XmlReader(is, false);
        if (!encoding.equals("UTF-16")) {
            assertEquals(xmlReader.getEncoding(), encoding);
        } else {
            assertEquals(xmlReader.getEncoding().substring(0, encoding.length()), encoding);
        }
        xmlReader.close();
    }

    protected void _testRawBomInvalid(final String bomEnc, final String streamEnc, final String prologEnc) throws Exception {
        final InputStream is = getXmlStream(bomEnc, "xml-prolog-encoding", streamEnc, prologEnc);
        try {
            final XmlReader xmlReader = new XmlReader(is, false);
            fail("It should have failed for BOM " + bomEnc + ", streamEnc " + streamEnc + " and prologEnc " + prologEnc);
            xmlReader.close();
        } catch (final IOException ex) {
            assertTrue(ex.getMessage().indexOf("Invalid encoding,") > -1);
        }
    }

    public void testRawBom() throws Exception {
        _testRawBomValid("UTF-8");
        _testRawBomValid("UTF-16BE");
        _testRawBomValid("UTF-16LE");
        _testRawBomValid("UTF-16");

        _testRawBomInvalid("UTF-8-bom", "US-ASCII", "US-ASCII");
        _testRawBomInvalid("UTF-8-bom", "ISO-8859-1", "ISO-8859-1");
        _testRawBomInvalid("UTF-8-bom", "UTF-8", "UTF-16");
        _testRawBomInvalid("UTF-8-bom", "UTF-8", "UTF-16BE");
        _testRawBomInvalid("UTF-8-bom", "UTF-8", "UTF-16LE");
        _testRawBomInvalid("UTF-16BE-bom", "UTF-16BE", "UTF-16LE");
        _testRawBomInvalid("UTF-16LE-bom", "UTF-16LE", "UTF-16BE");
        _testRawBomInvalid("UTF-16LE-bom", "UTF-16LE", "UTF-8");
    }

    public void testHttp() throws Exception {
        _testHttpValid("application/xml", "no-bom", "US-ASCII", null);
        _testHttpValid("application/xml", "UTF-8-bom", "US-ASCII", null);
        _testHttpValid("application/xml", "UTF-8-bom", "UTF-8", null);
        _testHttpValid("application/xml", "UTF-8-bom", "UTF-8", "UTF-8");
        _testHttpValid("application/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", null);
        _testHttpValid("application/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", "UTF-8");
        _testHttpValid("application/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", null);
        _testHttpValid("application/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16");
        _testHttpValid("application/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE");

        _testHttpInvalid("application/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", null);
        _testHttpInvalid("application/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16");
        _testHttpInvalid("application/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE");
        _testHttpInvalid("application/xml", "UTF-8-bom", "US-ASCII", "US-ASCII");
        _testHttpInvalid("application/xml;charset=UTF-16", "UTF-16LE", "UTF-8", "UTF-8");
        _testHttpInvalid("application/xml;charset=UTF-16", "no-bom", "UTF-16BE", "UTF-16BE");

        _testHttpValid("text/xml", "no-bom", "US-ASCII", null);
        _testHttpValid("text/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", "UTF-8");
        _testHttpValid("text/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", null);
        _testHttpValid("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", null);
        _testHttpValid("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16");
        _testHttpValid("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE");
        _testHttpValid("text/xml", "UTF-8-bom", "US-ASCII", null);

        _testHttpInvalid("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", null);
        _testHttpInvalid("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16");
        _testHttpInvalid("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE");
        _testHttpInvalid("text/xml;charset=UTF-16", "no-bom", "UTF-16BE", "UTF-16BE");
        _testHttpInvalid("text/xml;charset=UTF-16", "no-bom", "UTF-16BE", null);

        _testHttpLenient("text/xml", "no-bom", "US-ASCII", null, "US-ASCII");
        _testHttpLenient("text/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", "UTF-8", "UTF-8");
        _testHttpLenient("text/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", null, "UTF-8");
        _testHttpLenient("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", null, "UTF-16BE");
        _testHttpLenient("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16", "UTF-16");
        _testHttpLenient("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE", "UTF-16BE");
        _testHttpLenient("text/xml", "UTF-8-bom", "US-ASCII", null, "US-ASCII");

        _testHttpLenient("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", null, "UTF-16BE");
        _testHttpLenient("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16", "UTF-16");
        _testHttpLenient("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE", "UTF-16BE");
        _testHttpLenient("text/xml;charset=UTF-16", "no-bom", "UTF-16BE", "UTF-16BE", "UTF-16BE");
        _testHttpLenient("text/xml;charset=UTF-16", "no-bom", "UTF-16BE", null, "UTF-16");

        _testHttpLenient("text/html", "no-bom", "US-ASCII", "US-ASCII", "US-ASCII");
        _testHttpLenient("text/html", "no-bom", "US-ASCII", null, "US-ASCII");
        _testHttpLenient("text/html;charset=UTF-8", "no-bom", "US-ASCII", "UTF-8", "UTF-8");
        _testHttpLenient("text/html;charset=UTF-16BE", "no-bom", "US-ASCII", "UTF-8", "UTF-8");
    }

    public void _testHttpValid(final String cT, final String bomEnc, final String streamEnc, final String prologEnc) throws Exception {
        final InputStream is = getXmlStream(bomEnc, prologEnc == null ? "xml" : "xml-prolog-encoding", streamEnc, prologEnc);
        final XmlReader xmlReader = new XmlReader(is, cT, false);
        if (!streamEnc.equals("UTF-16")) {
            // we can not assert things here becuase UTF-8, US-ASCII and ISO-8859-1 look alike for
            // the chars used for detection
        } else {
            assertEquals(xmlReader.getEncoding().substring(0, streamEnc.length()), streamEnc);
        }
        xmlReader.close();
    }

    protected void _testHttpInvalid(final String cT, final String bomEnc, final String streamEnc, final String prologEnc) throws Exception {
        final InputStream is = getXmlStream(bomEnc, prologEnc == null ? "xml-prolog" : "xml-prolog-encoding", streamEnc, prologEnc);
        try {
            final XmlReader xmlReader = new XmlReader(is, cT, false);
            fail("It should have failed for HTTP Content-type " + cT + ", BOM " + bomEnc + ", streamEnc " + streamEnc + " and prologEnc " + prologEnc);
            xmlReader.close();
        } catch (final IOException ex) {
            assertTrue(ex.getMessage().indexOf("Invalid encoding,") > -1);
        }
    }

    protected void _testHttpLenient(final String cT, final String bomEnc, final String streamEnc, final String prologEnc, final String shouldbe)
            throws Exception {
        final InputStream is = getXmlStream(bomEnc, prologEnc == null ? "xml-prolog" : "xml-prolog-encoding", streamEnc, prologEnc);
        final XmlReader xmlReader = new XmlReader(is, cT, true);
        assertEquals(xmlReader.getEncoding(), shouldbe);
        xmlReader.close();
    }

    // XML Stream generator

    private static final int[] NO_BOM_BYTES = {};
    private static final int[] UTF_16BE_BOM_BYTES = { 0xFE, 0xFF };
    private static final int[] UTF_16LE_BOM_BYTES = { 0xFF, 0XFE };
    private static final int[] UTF_8_BOM_BYTES = { 0xEF, 0xBB, 0xBF };

    private static final Map<String, int[]> BOMs = new HashMap<String, int[]>();

    static {
        BOMs.put("no-bom", NO_BOM_BYTES);
        BOMs.put("UTF-16BE-bom", UTF_16BE_BOM_BYTES);
        BOMs.put("UTF-16LE-bom", UTF_16LE_BOM_BYTES);
        BOMs.put("UTF-16-bom", NO_BOM_BYTES); // it's added by the writer
        BOMs.put("UTF-8-bom", UTF_8_BOM_BYTES);
    }

    private static final MessageFormat XML = new MessageFormat("<root>{2}</root>");
    private static final MessageFormat XML_WITH_PROLOG = new MessageFormat("<?xml version=\"1.0\"?>\n<root>{2}</root>");
    private static final MessageFormat XML_WITH_PROLOG_AND_ENCODING = new MessageFormat("<?xml version=\"1.0\" encoding=\"{1}\"?>\n<root>{2}</root>");

    private static final MessageFormat INFO = new MessageFormat("\nBOM : {0}\nDoc : {1}\nStream Enc : {2}\nProlog Enc : {3}\n");

    private static final Map<String, MessageFormat> XMLs = new HashMap<String, MessageFormat>();

    static {
        XMLs.put("xml", XML);
        XMLs.put("xml-prolog", XML_WITH_PROLOG);
        XMLs.put("xml-prolog-encoding", XML_WITH_PROLOG_AND_ENCODING);
    }

    /**
     *
     * @param bomType no-bom, UTF-16BE-bom, UTF-16LE-bom, UTF-8-bom
     * @param xmlType xml, xml-prolog, xml-prolog-charset
     * @return XML stream
     */
    protected InputStream getXmlStream(final String bomType, final String xmlType, final String streamEnc, final String prologEnc) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        int[] bom = BOMs.get(bomType);
        if (bom == null) {
            bom = new int[0];
        }
        final MessageFormat xml = XMLs.get(xmlType);
        for (final int element : bom) {
            baos.write(element);
        }
        final Writer writer = new OutputStreamWriter(baos, streamEnc);
        final String info = INFO.format(new Object[] { bomType, xmlType, prologEnc });
        final String xmlDoc = xml.format(new Object[] { streamEnc, prologEnc, info });
        writer.write(xmlDoc);

        // PADDDING TO TEST THINGS WORK BEYOND PUSHBACK_SIZE
        writer.write("<da>\n");
        for (int i = 0; i < 10000; i++) {
            writer.write("<do/>\n");
        }
        writer.write("</da>\n");

        writer.close();
        return new ByteArrayInputStream(baos.toByteArray());
    }

}
