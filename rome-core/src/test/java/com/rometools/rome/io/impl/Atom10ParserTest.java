package com.rometools.rome.io.impl;

import static java.lang.String.join;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Locale;

import org.jdom2.JDOMException;
import org.junit.Test;

import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.io.FeedException;

public class Atom10ParserTest {

    @Test
    public void testParseEntryCatchingXxe() throws IllegalArgumentException, JDOMException, IOException, FeedException {

        // @formatter:off
        final String content = join("",
            "<?xml  version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
            "<!DOCTYPE foo [\n" +
            "   <!ELEMENT foo ANY >\n" +
            "   <!ENTITY xxe SYSTEM  \"file:///etc/passwd\" >]>" +
            "<entry xmlns=\"http://www.w3.org/2005/Atom\">" +
            "  <title>Hello &xxe;</title>" +
            "</entry>"
        );
        // @formatter:on

        final byte[] bytes = content.getBytes();
        final ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        final Reader reader = new InputStreamReader(input);

        final Entry entry = Atom10Parser.parseEntry(reader, null, Locale.ENGLISH);
        assertEquals("Hello ", entry.getTitle());

    }

}
