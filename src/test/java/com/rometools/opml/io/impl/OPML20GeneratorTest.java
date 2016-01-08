package com.rometools.opml.io.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.exceptions.XpathException;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.rometools.opml.feed.opml.Opml;
import com.rometools.opml.feed.opml.Outline;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.WireFeedOutput;

public class OPML20GeneratorTest {

    @Test
    public void testCategoryOutput() throws IllegalArgumentException, FeedException, SAXException, IOException, XpathException {
        checkCategoryOutput(null, "");
        checkCategoryOutput(Arrays.asList("category1"), "/category1");
        checkCategoryOutput(Arrays.asList("category1", "category2"), "/category1/category2");
    }

    private void checkCategoryOutput(final List<String> categories, final String asserted)
            throws IllegalArgumentException, FeedException, SAXException, IOException, XpathException {

        final Outline outline = new Outline("outline1", null);
        outline.setCategories(categories);
        final List<Outline> outlines = Arrays.asList(outline);

        final Opml opml = new Opml();
        opml.setFeedType("opml_2.0");
        opml.setTitle("title");
        opml.setOutlines(outlines);

        final WireFeedOutput output = new WireFeedOutput();
        final String xml = output.outputString(opml);

        final Document document = XMLUnit.buildControlDocument(xml);
        final String categoryValue = XMLUnit.newXpathEngine().evaluate("/opml/body/outline/@category", document);
        assertThat(categoryValue, is(equalTo(asserted)));

    }

}
