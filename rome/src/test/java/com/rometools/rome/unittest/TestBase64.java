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

import junit.framework.TestCase;

import com.rometools.rome.io.impl.Base64;

public class TestBase64 extends TestCase {

    private void testEncodeDecode(final String s) {
        final String encoded = Base64.encode(s);
        final String decoded = Base64.decode(encoded);
        assertEquals(s, decoded);
    }

    public void testEncodeDecode() {
        testEncodeDecode("");
        testEncodeDecode("A");
        testEncodeDecode("AB");
        testEncodeDecode("ABC");
        testEncodeDecode("ABCD");
        testEncodeDecode("ABCDE");
        testEncodeDecode("&");
        testEncodeDecode("a&");
        testEncodeDecode("ab&");
        testEncodeDecode("abc&");
        testEncodeDecode("abcd&");

    }

    public void testDecodeWithEnters() {
        final String s = "Hello World!";
        String encoded = Base64.encode(s);
        encoded = encoded.substring(0, 3) + "\n\r\n" + encoded.substring(3);
        final String decoded = Base64.decode(encoded);
        assertEquals(s, decoded);
    }

}
