package com.rometools.rome.io;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class XmLReaderHttpHeaderTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

    @Before
    public void init() {
        stubFor(get(urlEqualTo("/test")).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/xml")
                .withBody("<?xml version=\"1.0\" encoding=\"utf-8\"?><rss version=\"2.0\"><channel/></rss>")));
    }

    @Test
    public void testUrlWithoutHeaders() throws IOException {
        final URL url = new URL("http://localhost:" + wireMockRule.port() + "/test");
        final XmlReader xmlReader = new XmlReader(url);
        xmlReader.close();
        verify(getRequestedFor(urlEqualTo("/test")).withHeader("User-Agent", matching("(?i)rome.*")));
    }

    @Test
    public void testUrlWithHeaders() throws IOException {
        final Map<String, String> headers = new HashMap<String, String>();
        headers.put("User-Agent", "abcd");
        headers.put("Accept", "efgh");
        final URL url = new URL("http://localhost:" + wireMockRule.port() + "/test");
        final XmlReader xmlReader = new XmlReader(url, headers);
        xmlReader.close();
        verify(getRequestedFor(urlEqualTo("/test")).withHeader("User-Agent", equalTo("abcd")).withHeader("Accept", equalTo("efgh")));
    }
}