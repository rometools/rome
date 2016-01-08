package com.rometools.opml.io.impl;

import java.util.Locale;

import org.jdom2.Document;
import org.jdom2.Element;

import com.rometools.opml.feed.opml.Opml;
import com.rometools.opml.feed.opml.Outline;
import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.impl.DateParser;

/**
 * @author cooper
 */
public class OPML20Generator extends OPML10Generator {

    public OPML20Generator() {
    }

    /**
     * Returns the type of feed the generator creates.
     *
     * @return the type of feed the generator creates.
     * @see WireFeed for details on the format of this string.
     */
    @Override
    public String getType() {
        return "opml_2.0";
    }

    /**
     * Creates an XML document (JDOM) for the given feed bean.
     *
     * @param feed the feed bean to generate the XML document from.
     * @return the generated XML document (JDOM).
     * @throws IllegalArgumentException thrown if the type of the given feed bean does not match with the type of the
     *             WireFeedGenerator.
     * @throws FeedException thrown if the XML Document could not be created.
     */
    @Override
    public Document generate(final WireFeed feed) throws IllegalArgumentException, FeedException {
        final Document retValue = super.generate(feed);
        retValue.getRootElement().setAttribute("version", "2.0");
        return retValue;
    }

    @Override
    protected Element generateHead(final Opml opml) {

        final Element docs = new Element("docs");
        docs.setText(opml.getDocs());

        final Element retValue = super.generateHead(opml);
        retValue.addContent(docs);
        return retValue;

    }

    @Override
    protected Element generateOutline(final Outline outline) {

        final Element retValue = super.generateOutline(outline);

        if (outline.getCreated() != null) {
            retValue.setAttribute("created", DateParser.formatRFC822(outline.getCreated(), Locale.US));
        }

        return retValue;

    }

}
