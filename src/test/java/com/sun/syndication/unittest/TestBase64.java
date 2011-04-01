package com.sun.syndication.unittest;

import junit.framework.TestCase;
import com.sun.syndication.io.impl.Base64;

public class TestBase64 extends TestCase {

    private void _testEncodeDecode(String s) {
        String encoded = Base64.encode(s);
        String decoded = Base64.decode(encoded);
        assertEquals(s, decoded);
    }

    public void testEncodeDecode() {
        _testEncodeDecode("");
        _testEncodeDecode("A");
        _testEncodeDecode("AB");
        _testEncodeDecode("ABC");
        _testEncodeDecode("ABCD");
        _testEncodeDecode("ABCDE");
        _testEncodeDecode("&");
        _testEncodeDecode("a&");
        _testEncodeDecode("ab&");
        _testEncodeDecode("abc&");
        _testEncodeDecode("abcd&");

    }

    public void testDecodeWithEnters() {
        String s = "Hello World!";
        String encoded = Base64.encode(s);
        encoded = encoded.substring(0, 3) + "\n\r\n" + encoded.substring(3);
        System.out.println(encoded);
        String decoded = Base64.decode(encoded);
        assertEquals(s, decoded);
    }

}
