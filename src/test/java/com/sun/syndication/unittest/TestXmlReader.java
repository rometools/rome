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
package com.sun.syndication.unittest;

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

import com.sun.syndication.io.XmlReader;

/**
 * @author pat, tucu
 * 
 */
public class TestXmlReader extends TestCase {
    private static final String XML5 = "xml-prolog-encoding-spaced-single-quotes";
    private static final String XML4 = "xml-prolog-encoding-single-quotes";
    private static final String XML3 = "xml-prolog-encoding-double-quotes";
    private static final String XML2 = "xml-prolog";
    private static final String XML1 = "xml";

    public static void main(final String[] args) throws Exception {
        final TestXmlReader test = new TestXmlReader();
        test.testRawBom();
        test.testRawNoBom();
        test.testHttp();
    }

    protected void testRawNoBomValid(final String encoding) throws Exception {
        checkEncoding(XML1, encoding, "UTF-8");
        checkEncoding(XML2, encoding, "UTF-8");
        checkEncoding(XML3, encoding, encoding);
        checkEncoding(XML4, encoding, encoding);
        checkEncoding(XML5, encoding, encoding);
    }

    private void checkEncoding(final String xmlType, final String encoding, final String expected) throws IOException {
        InputStream is = null;
        XmlReader xmlReader = null;
        try {
            is = getXmlStream("no-bom", xmlType, encoding, encoding);
            xmlReader = new XmlReader(is, false);
            assertEquals(xmlReader.getEncoding(), expected);
        } finally {
            if (xmlReader != null) {
                xmlReader.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    protected void testRawNoBomInvalid(final String encoding) throws Exception {
        InputStream is = null;
        XmlReader xmlReader = null;
        try {
            is = getXmlStream("no-bom", XML3, encoding, encoding);
            xmlReader = new XmlReader(is, false);
            fail("It should have failed");
        } catch (final IOException ex) {
            assertTrue(ex.getMessage().indexOf("Invalid encoding,") > -1);
        } finally {
            if (xmlReader != null) {
                xmlReader.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    public void testRawNoBom() throws Exception {
        testRawNoBomValid("US-ASCII");
        testRawNoBomValid("UTF-8");
        testRawNoBomValid("ISO-8859-1");
    }

    protected void testRawBomValid(final String encoding) throws Exception {
        final InputStream is = getXmlStream(encoding + "-bom", XML3, encoding, encoding);
        final XmlReader xmlReader = new XmlReader(is, false);
        if (!encoding.equals("UTF-16")) {
            assertEquals(xmlReader.getEncoding(), encoding);
        } else {
            assertEquals(xmlReader.getEncoding().substring(0, encoding.length()), encoding);
        }
        xmlReader.close();
    }

    protected void testRawBomInvalid(final String bomEnc, final String streamEnc, final String prologEnc) throws Exception {
        final InputStream is = getXmlStream(bomEnc, XML3, streamEnc, prologEnc);
        try {
            final XmlReader xmlReader = new XmlReader(is, false);
            fail("It should have failed for BOM " + bomEnc + ", streamEnc " + streamEnc + " and prologEnc " + prologEnc);
            xmlReader.close();
        } catch (final IOException ex) {
            assertTrue(ex.getMessage().indexOf("Invalid encoding,") > -1);
        }
    }

    public void testRawBom() throws Exception {
        testRawBomValid("UTF-8");
        testRawBomValid("UTF-16BE");
        testRawBomValid("UTF-16LE");
        testRawBomValid("UTF-16");

        testRawBomInvalid("UTF-8-bom", "US-ASCII", "US-ASCII");
        testRawBomInvalid("UTF-8-bom", "ISO-8859-1", "ISO-8859-1");
        testRawBomInvalid("UTF-8-bom", "UTF-8", "UTF-16");
        testRawBomInvalid("UTF-8-bom", "UTF-8", "UTF-16BE");
        testRawBomInvalid("UTF-8-bom", "UTF-8", "UTF-16LE");
        testRawBomInvalid("UTF-16BE-bom", "UTF-16BE", "UTF-16LE");
        testRawBomInvalid("UTF-16LE-bom", "UTF-16LE", "UTF-16BE");
        testRawBomInvalid("UTF-16LE-bom", "UTF-16LE", "UTF-8");
    }

    public void testHttp() throws Exception {
        testHttpValid("application/xml", "no-bom", "US-ASCII", null);
        testHttpValid("application/xml", "UTF-8-bom", "US-ASCII", null);
        testHttpValid("application/xml", "UTF-8-bom", "UTF-8", null);
        testHttpValid("application/xml", "UTF-8-bom", "UTF-8", "UTF-8");
        testHttpValid("application/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", null);
        testHttpValid("application/xml;charset=\"UTF-8\"", "UTF-8-bom", "UTF-8", null);
        testHttpValid("application/xml;charset='UTF-8'", "UTF-8-bom", "UTF-8", null);
        testHttpValid("application/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", "UTF-8");
        testHttpValid("application/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", null);
        testHttpValid("application/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16");
        testHttpValid("application/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE");

        testHttpInvalid("application/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", null);
        testHttpInvalid("application/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16");
        testHttpInvalid("application/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE");
        testHttpInvalid("application/xml", "UTF-8-bom", "US-ASCII", "US-ASCII");
        testHttpInvalid("application/xml;charset=UTF-16", "UTF-16LE", "UTF-8", "UTF-8");
        testHttpInvalid("application/xml;charset=UTF-16", "no-bom", "UTF-16BE", "UTF-16BE");

        testHttpValid("text/xml", "no-bom", "US-ASCII", null);
        testHttpValid("text/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", "UTF-8");
        testHttpValid("text/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", null);
        testHttpValid("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", null);
        testHttpValid("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16");
        testHttpValid("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE");
        testHttpValid("text/xml", "UTF-8-bom", "US-ASCII", null);

        testAlternateDefaultEncoding("application/xml", "UTF-8-bom", "UTF-8", null, null);
        testAlternateDefaultEncoding("application/xml", "no-bom", "US-ASCII", null, "US-ASCII");
        testAlternateDefaultEncoding("application/xml", "UTF-8-bom", "UTF-8", null, "UTF-8");
        testAlternateDefaultEncoding("text/xml", "no-bom", "US-ASCII", null, null);
        testAlternateDefaultEncoding("text/xml", "no-bom", "US-ASCII", null, "US-ASCII");
        testAlternateDefaultEncoding("text/xml", "no-bom", "US-ASCII", null, "UTF-8");

        testHttpInvalid("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", null);
        testHttpInvalid("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16");
        testHttpInvalid("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE");
        testHttpInvalid("text/xml;charset=UTF-16", "no-bom", "UTF-16BE", "UTF-16BE");
        testHttpInvalid("text/xml;charset=UTF-16", "no-bom", "UTF-16BE", null);

        testHttpLenient("text/xml", "no-bom", "US-ASCII", null, "US-ASCII");
        testHttpLenient("text/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", "UTF-8", "UTF-8");
        testHttpLenient("text/xml;charset=UTF-8", "UTF-8-bom", "UTF-8", null, "UTF-8");
        testHttpLenient("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", null, "UTF-16BE");
        testHttpLenient("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16", "UTF-16");
        testHttpLenient("text/xml;charset=UTF-16", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE", "UTF-16BE");
        testHttpLenient("text/xml", "UTF-8-bom", "US-ASCII", null, "US-ASCII");

        testHttpLenient("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", null, "UTF-16BE");
        testHttpLenient("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16", "UTF-16");
        testHttpLenient("text/xml;charset=UTF-16BE", "UTF-16BE-bom", "UTF-16BE", "UTF-16BE", "UTF-16BE");
        testHttpLenient("text/xml;charset=UTF-16", "no-bom", "UTF-16BE", "UTF-16BE", "UTF-16BE");
        testHttpLenient("text/xml;charset=UTF-16", "no-bom", "UTF-16BE", null, "UTF-16");

        testHttpLenient("text/html", "no-bom", "US-ASCII", "US-ASCII", "US-ASCII");
        testHttpLenient("text/html", "no-bom", "US-ASCII", null, "US-ASCII");
        testHttpLenient("text/html;charset=UTF-8", "no-bom", "US-ASCII", "UTF-8", "UTF-8");
        testHttpLenient("text/html;charset=UTF-16BE", "no-bom", "US-ASCII", "UTF-8", "UTF-8");
    }

    public void testAlternateDefaultEncoding(final String cT, final String bomEnc, final String streamEnc, final String prologEnc, final String alternateEnc)
            throws Exception {
        XmlReader xmlReader = null;
        try {
            final InputStream is;
            if (prologEnc == null) {
                is = getXmlStream(bomEnc, XML1, streamEnc, prologEnc);
            } else {
                is = getXmlStream(bomEnc, XML3, streamEnc, prologEnc);
            }
            XmlReader.setDefaultEncoding(alternateEnc);
            xmlReader = new XmlReader(is, cT, false);
            if (!streamEnc.equals("UTF-16")) {
                // we can not assert things here becuase UTF-8, US-ASCII and
                // ISO-8859-1 look alike for the chars used for detection
            } else {
                assertEquals(xmlReader.getEncoding().substring(0, streamEnc.length()), streamEnc);
            }
        } finally {
            XmlReader.setDefaultEncoding(null);
            if (xmlReader != null) {
                xmlReader.close();
            }
        }
    }

    public void testHttpValid(final String cT, final String bomEnc, final String streamEnc, final String prologEnc) throws Exception {
        final InputStream is;
        if (prologEnc == null) {
            is = getXmlStream(bomEnc, XML1, streamEnc, prologEnc);
        } else {
            is = getXmlStream(bomEnc, XML3, streamEnc, prologEnc);
        }
        final XmlReader xmlReader = new XmlReader(is, cT, false);
        if (!streamEnc.equals("UTF-16")) {
            // we can not assert things here becuase UTF-8, US-ASCII and
            // ISO-8859-1 look alike for the chars used for detection
        } else {
            assertEquals(xmlReader.getEncoding().substring(0, streamEnc.length()), streamEnc);
        }
        xmlReader.close();
    }

    protected void testHttpInvalid(final String cT, final String bomEnc, final String streamEnc, final String prologEnc) throws Exception {
        final InputStream is;
        if (prologEnc == null) {
            is = getXmlStream(bomEnc, XML2, streamEnc, prologEnc);
        } else {
            is = getXmlStream(bomEnc, XML3, streamEnc, prologEnc);
        }
        try {
            new XmlReader(is, cT, false);
            fail("It should have failed for HTTP Content-type " + cT + ", BOM " + bomEnc + ", streamEnc " + streamEnc + " and prologEnc " + prologEnc);
        } catch (final IOException ex) {
            assertTrue(ex.getMessage().indexOf("Invalid encoding,") > -1);
        }
    }

    protected void testHttpLenient(final String cT, final String bomEnc, final String streamEnc, final String prologEnc, final String shouldbe)
            throws Exception {
        final InputStream is;
        if (prologEnc == null) {
            is = getXmlStream(bomEnc, XML2, streamEnc, prologEnc);
        } else {
            is = getXmlStream(bomEnc, XML3, streamEnc, prologEnc);
        }
        final XmlReader xmlReader = new XmlReader(is, cT, true);
        assertEquals(xmlReader.getEncoding(), shouldbe);
        xmlReader.close();
    }

    private static final String ENCODING_ATTRIBUTE_XML = "<?xml version=\"1.0\" ?> \n" + "<atom:feed xmlns:atom=\"http://www.w3.org/2005/Atom\">\n" + "\n"
            + "  <atom:entry>\n" + "    <atom:title encoding=\"base64\"><![CDATA\n" + "aW5nTGluZSIgLz4";

    public void testEncodingAttributeXML() throws Exception {
        final InputStream is = new ByteArrayInputStream(ENCODING_ATTRIBUTE_XML.getBytes());
        final XmlReader xmlReader = new XmlReader(is, "", true);
        assertEquals(xmlReader.getEncoding(), "UTF-8");
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
    private static final MessageFormat XML_WITH_PROLOG_AND_ENCODING_DOUBLE_QUOTES = new MessageFormat(
            "<?xml version=\"1.0\" encoding=\"{1}\"?>\n<root>{2}</root>");
    private static final MessageFormat XML_WITH_PROLOG_AND_ENCODING_SINGLE_QUOTES = new MessageFormat(
            "<?xml version=\"1.0\" encoding=''{1}''?>\n<root>{2}</root>");
    private static final MessageFormat XML_WITH_PROLOG_AND_ENCODING_SPACED_SINGLE_QUOTES = new MessageFormat(
            "<?xml version=\"1.0\" encoding =  \t \n \r''{1}''?>\n<root>{2}</root>");

    private static final MessageFormat INFO = new MessageFormat("\nBOM : {0}\nDoc : {1}\nStream Enc : {2}\nProlog Enc : {3}\n");

    private static final Map<String, MessageFormat> XMLs = new HashMap<String, MessageFormat>();

    static {
        XMLs.put(XML1, XML);
        XMLs.put(XML2, XML_WITH_PROLOG);
        XMLs.put(XML3, XML_WITH_PROLOG_AND_ENCODING_DOUBLE_QUOTES);
        XMLs.put(XML4, XML_WITH_PROLOG_AND_ENCODING_SINGLE_QUOTES);
        XMLs.put(XML5, XML_WITH_PROLOG_AND_ENCODING_SPACED_SINGLE_QUOTES);
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
