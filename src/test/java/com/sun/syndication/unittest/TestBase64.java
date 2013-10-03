package com.sun.syndication.unittest;

import junit.framework.TestCase;

import com.sun.syndication.io.impl.Base64;

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
