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

import com.sun.syndication.io.XmlReader;
import com.sun.syndication.io.impl.XmlFixerReader;
import junit.framework.TestCase;
import org.jdom.input.SAXBuilder;

import java.io.*;

/**
 * @author pat, tucu
 *
 */
public class TestXmlFixerReader extends TestCase {
    private static final String XML_PROLOG = "<?xml version=\"1.0\" ?>";

    public void testTrim() throws Exception {
        _testValidTrim("","<hello></hello>");
        _testValidTrim("",XML_PROLOG+"<hello></hello>");
        _testValidTrim(" ","<hello></hello>");
        _testValidTrim(" ",XML_PROLOG+"<hello></hello>");
        _testValidTrim(" \n","<hello></hello>");
        _testValidTrim(" \n",XML_PROLOG+"<hello></hello>");
        _testValidTrim("<!-- - -- -->","<hello></hello>");
        _testValidTrim("<!-- - -- -->",XML_PROLOG+"<hello></hello>");
        _testValidTrim(" <!-- - -- -->","<hello></hello>");
        _testValidTrim(" <!-- - -- -->",XML_PROLOG+"<hello></hello>");
        _testValidTrim(" <!-- - -- --> ","<hello></hello>");
        _testValidTrim(" <!-- - -- --> ",XML_PROLOG+"<hello></hello>");
        _testValidTrim(" <!-- - -- --> <!-- - -- --> ","<hello></hello>");
        _testValidTrim(" <!-- - -- --> <!-- - -- --> ",XML_PROLOG+"<hello></hello>");
        _testValidTrim(" <!-- - -- --> \n <!-- - -- --> ","<hello></hello>");
        _testValidTrim(" <!-- - -- --> \n <!-- - -- --> ",XML_PROLOG+"<hello></hello>");

        _testInvalidTrim("x","<hello></hello>");
        _testInvalidTrim("x",XML_PROLOG+"<hello></hello>");
        _testInvalidTrim(" x","<hello></hello>");
        _testInvalidTrim(" x",XML_PROLOG+"<hello></hello>");
        _testInvalidTrim(" x\n","<hello></hello>");
        _testInvalidTrim(" x\n",XML_PROLOG+"<hello></hello>");
        _testInvalidTrim("<!-- - -- - ->","<hello></hello>");
        _testInvalidTrim("<!-- - -- - ->",XML_PROLOG+"<hello></hello>");
        _testInvalidTrim(" <!-- - -- -- >","<hello></hello>");
        _testInvalidTrim(" <!-- - -- -- >",XML_PROLOG+"<hello></hello>");
        _testInvalidTrim(" <!-- - -- -->x ","<hello></hello>");
        _testInvalidTrim(" <!-- - -- -->x ",XML_PROLOG+"<hello></hello>");
        _testInvalidTrim(" <!-- - -- --> x <!-- - -- --> ","<hello></hello>");
        _testInvalidTrim(" <!-- - -- --> x <!-- - -- --> ",XML_PROLOG+"<hello></hello>");
        _testInvalidTrim(" <!-- - -- --> x\n <!-- - -- --> ","<hello></hello>");
        _testInvalidTrim(" <!-- - -- --> x\n <!-- - -- --> ",XML_PROLOG+"<hello></hello>");
    }

    public void testAmpHandling() throws Exception {
        String input = "&amp; &aa &";
        BufferedReader reader = new BufferedReader(new XmlFixerReader(new StringReader(input)));
        String output = reader.readLine();
        reader.close();
        assertEquals("&amp; &amp;aa &amp;", output);
    }

    public void testHtmlEntities() throws Exception {
        _testValidEntities("<hello></hello>");
        _testValidEntities(XML_PROLOG+"<hello></hello>");
        _testValidEntities(" <!-- just in case -->\n"+XML_PROLOG+"<hello></hello>");

        _testValidEntities("<hello>&apos;&yen;&#250;&yen;</hello>");
        _testValidEntities(XML_PROLOG+"<hello>&apos;&yen;&#250;&yen;</hello>");
        _testValidEntities(" <!-- just in case -->\n"+XML_PROLOG+"<hello>&apos;&yen;&#250;&yen;</hello>");

        _testValidEntities("<hello>&Pi;&Rho;#913;&Rho;</hello>");
        _testValidEntities(XML_PROLOG+"<hello>&Pi;&Rho;&#913;&Rho;</hello>");
        _testValidEntities(" <!-- just in case -->\n"+XML_PROLOG+"<hello>&Pi;&Rho;&#913;&Rho;</hello>");

        _testValidEntities("<hello>&OElig;&mdash;&#8211;&mdash;</hello>");
        _testValidEntities(XML_PROLOG+"<hello>&OElig;&mdash;&#8211;&mdash;</hello>");
        _testValidEntities(" <!-- just in case -->\n"+XML_PROLOG+"<hello>&OElig;&mdash;&#8211;&mdash;</hello>");        
        
        _testInvalidEntities("<hello>&apos;&yexn;&#250;&yen;</hello>");
        _testInvalidEntities(XML_PROLOG+"<hello>&apos;&yexn;&#250;&yen;</hello>");
        _testInvalidEntities(" <!-- just in case -->\n"+XML_PROLOG+"<hello>&apos;&yexn;&#250;&yen;</hello>");

        _testInvalidEntities("<hello>&Pi;&Rhox;#913;&Rho;</hello>");
        _testInvalidEntities(XML_PROLOG+"<hello>&Pi;&Rhox;&#913;&Rho;</hello>");
        _testInvalidEntities(" <!-- just in case -->\n"+XML_PROLOG+"<hello>&Pi;&Rhox;&#913;&Rho;</hello>");        
        
        _testInvalidEntities("<hello>&apos;&yen;&#2x50;&yen;</hello>");
        _testInvalidEntities(XML_PROLOG+"<hello>&apos;&yen;&#2x50;&yen;</hello>");
        _testInvalidEntities(" <!-- just in case -->\n"+XML_PROLOG+"<hello>&apos;&yen;&#2x50;&yen;</hello>");

        _testInvalidEntities("<hello>&Pi;&Rho;&#9x13;&Rho;</hello>");
        _testInvalidEntities(XML_PROLOG+"<hello>&Pi;&Rho;&#9x13;&Rho;</hello>");
        _testInvalidEntities(" <!-- just in case -->\n"+XML_PROLOG+"<hello>&Pi;&Rho;&#9x13;&Rho;</hello>");
    }

    protected void _testXmlParse(String garbish,String xmlDoc) throws Exception {
        InputStream is = getStream(garbish,xmlDoc);
        Reader reader = new XmlReader(is);
        reader = new XmlFixerReader(reader);
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.build(reader);
    }

    protected void _testValidTrim(String garbish,String xmlDoc) throws Exception {
        _testXmlParse(garbish,xmlDoc);
    }

    protected void _testInvalidTrim(String garbish,String xmlDoc) throws Exception {
        try {
            _testXmlParse(garbish,xmlDoc);
            assertTrue(false);
        }
        catch (Exception ex) {
        }
    }

    protected void _testValidEntities(String xmlDoc) throws Exception {
        _testXmlParse("",xmlDoc);
    }

    protected void _testInvalidEntities(String xmlDoc) throws Exception {
        try {
            _testXmlParse("",xmlDoc);
            assertTrue(false);
        }
        catch (Exception ex) {
        }
    }

    // XML Stream generator

    protected InputStream getStream(String garbish,String xmlDoc) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        Writer writer = new OutputStreamWriter(baos);
        writer.write(garbish);
        writer.write(xmlDoc);
        writer.close();
        return new ByteArrayInputStream(baos.toByteArray());
    }


}
