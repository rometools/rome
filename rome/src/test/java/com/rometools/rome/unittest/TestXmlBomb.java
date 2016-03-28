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
package com.rometools.rome.unittest;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import com.rometools.rome.io.ParsingFeedException;
import com.rometools.rome.io.WireFeedInput;
import junit.framework.TestCase;

public class TestXmlBomb extends TestCase {

	public void testXmlBomb() throws Exception {

		// https://en.wikipedia.org/wiki/Billion_laughs
		// https://msdn.microsoft.com/en-us/magazine/ee335713.aspx
		String content = "<?xml version=\"1.0\"?>\n" +
				"<!DOCTYPE lolz [\n" +
				" <!ENTITY lol \"lol\">\n" +
				" <!ELEMENT lolz (#PCDATA)>\n" +
				" <!ENTITY lol1 \"&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;\">\n" +
				" <!ENTITY lol2 \"&lol1;&lol1;&lol1;&lol1;&lol1;&lol1;&lol1;&lol1;&lol1;&lol1;\">\n" +
				" <!ENTITY lol3 \"&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;\">\n" +
				" <!ENTITY lol4 \"&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;\">\n" +
				" <!ENTITY lol5 \"&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;\">\n" +
				" <!ENTITY lol6 \"&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;\">\n" +
				" <!ENTITY lol7 \"&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;\">\n" +
				" <!ENTITY lol8 \"&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;\">\n" +
				" <!ENTITY lol9 \"&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;\">\n" +
				"]>\n" +
				"<feed>\n" +
				"    <title>&lol9;</title>\n" +
				"    <subtitle>subtitle</subtitle>\n" +
				"    <entry>\n" +
				"        <id>id1</id>\n" +
				"        <title>title1</title>\n" +
				"    </entry>\n" +
				"</feed>";

		InputStream is = new ByteArrayInputStream(content.getBytes("UTF-8"));
		WireFeedInput feedInput = new WireFeedInput();
		Reader reader = new InputStreamReader(is, Charset.forName("UTF-8"));
		try {
			feedInput.build(reader);
			fail("Expected exception");
		}
		catch (ParsingFeedException ex) {
		}
	}

}
