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
package org.rometools.test;

import org.rometools.fetcher.impl.ResponseHandler;

import junit.framework.TestCase;

public class ResponseHandlerTest extends TestCase {

	/**
	 * Constructor for ResponseHandlerTest.
	 */
	public ResponseHandlerTest(String arg0) {
		super(arg0);
	}

	public void testGetCharacterEncodingString() {
		assertEquals(ResponseHandler.defaultCharacterEncoding, ResponseHandler.getCharacterEncoding((String)null));
		assertEquals(ResponseHandler.defaultCharacterEncoding, ResponseHandler.getCharacterEncoding("text/xml"));
		assertEquals(ResponseHandler.defaultCharacterEncoding, ResponseHandler.getCharacterEncoding("text/xml;"));
		assertEquals("ISO-8859-4", ResponseHandler.getCharacterEncoding("text/xml; charset=ISO-8859-4"));
		assertEquals("ISO-8859-4", ResponseHandler.getCharacterEncoding("text/xml;charset=ISO-8859-4"));
		assertEquals("ISO-8859-4", ResponseHandler.getCharacterEncoding("text/xml;charset=ISO-8859-4;something"));
		assertEquals(ResponseHandler.defaultCharacterEncoding, ResponseHandler.getCharacterEncoding("text/xml;something"));
	}

}
