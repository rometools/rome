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
package com.sun.syndication.io;

import com.sun.syndication.feed.synd.SyndFeed;
import org.jdom2.Document;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * Generates an XML document (String, File, OutputStream, Writer, W3C DOM document or JDOM document)
 * out of an SyndFeedImpl..
 * <p>
 * It delegates to a WireFeedOutput to generate all feed types.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndFeedOutput {
    private WireFeedOutput _feedOutput;

    /**
     * Creates a SyndFeedOutput instance.
     * <p>
     *
     */
    public SyndFeedOutput() {
        _feedOutput = new WireFeedOutput();
    }

    /**
     * Creates a String with the XML representation for the given SyndFeedImpl.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is the responsibility
     * of the developer to ensure that if the String is written to a character stream the stream charset is the same as
     * the feed encoding property.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the SyndFeedImpl must match
     *        the type given to the FeedOuptut constructor.
     * @return a String with the XML representation for the given SyndFeedImpl.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public String outputString(SyndFeed feed) throws FeedException {
        return _feedOutput.outputString(feed.createWireFeed());
    }

    /**
     * Creates a String with the XML representation for the given SyndFeedImpl.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is the responsibility
     * of the developer to ensure that if the String is written to a character stream the stream charset is the same as
     * the feed encoding property.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the SyndFeedImpl must match
     *        the type given to the FeedOuptut constructor.
     * @param prettyPrint pretty-print XML (true) oder collapsed
     * @return a String with the XML representation for the given SyndFeedImpl.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public String outputString(SyndFeed feed,boolean prettyPrint) throws FeedException {
        return _feedOutput.outputString(feed.createWireFeed(),prettyPrint);
    }

    /**
     * Creates a File containing with the XML representation for the given SyndFeedImpl.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. The platform
     * default charset encoding is used to write the feed to the file. It is the responsibility
     * of the developer to ensure the feed encoding is set to the platform charset encoding.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the SyndFeedImpl must match
     *        the type given to the FeedOuptut constructor.
     * @param file the file where to write the XML representation for the given SyndFeedImpl.
     * @throws IOException thrown if there was some problem writing to the File.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(SyndFeed feed,File file) throws IOException, FeedException {
        _feedOutput.output(feed.createWireFeed(),file);
    }

    /**
     * Creates a File containing with the XML representation for the given SyndFeedImpl.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. The platform
     * default charset encoding is used to write the feed to the file. It is the responsibility
     * of the developer to ensure the feed encoding is set to the platform charset encoding.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the SyndFeedImpl must match
     *        the type given to the FeedOuptut constructor.
     * @param prettyPrint pretty-print XML (true) oder collapsed
     * @param file the file where to write the XML representation for the given SyndFeedImpl.
     * @throws IOException thrown if there was some problem writing to the File.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(SyndFeed feed,File file,boolean prettyPrint) throws IOException, FeedException {
        _feedOutput.output(feed.createWireFeed(),file,prettyPrint);
    }

    /**
     * Writes to an Writer the XML representation for the given SyndFeedImpl.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is the responsibility
     * of the developer to ensure that if the String is written to a character stream the stream charset is the same as
     * the feed encoding property.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the SyndFeedImpl must match
     *        the type given to the FeedOuptut constructor.
     * @param writer Writer to write the XML representation for the given SyndFeedImpl.
     * @throws IOException thrown if there was some problem writing to the Writer.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(SyndFeed feed,Writer writer) throws IOException, FeedException {
        _feedOutput.output(feed.createWireFeed(),writer);
    }

    /**
     * Writes to an Writer the XML representation for the given SyndFeedImpl.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is the responsibility
     * of the developer to ensure that if the String is written to a character stream the stream charset is the same as
     * the feed encoding property.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the SyndFeedImpl must match
     *        the type given to the FeedOuptut constructor.
     * @param prettyPrint pretty-print XML (true) oder collapsed
     * @param writer Writer to write the XML representation for the given SyndFeedImpl.
     * @throws IOException thrown if there was some problem writing to the Writer.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(SyndFeed feed,Writer writer,boolean prettyPrint) throws IOException, FeedException {
        _feedOutput.output(feed.createWireFeed(),writer,prettyPrint);
    }

    /**
     * Creates a W3C DOM document for the given SyndFeedImpl.
     * <p>
     * This method does not use the feed encoding property.
     * <p>
     * @param feed Abstract feed to create W3C DOM document from. The type of the SyndFeedImpl must match
     *        the type given to the FeedOuptut constructor.
     * @return the W3C DOM document for the given SyndFeedImpl.
     * @throws FeedException thrown if the W3C DOM document for the feed could not be created.
     *
     */
    public org.w3c.dom.Document outputW3CDom(SyndFeed feed) throws FeedException {
        return _feedOutput.outputW3CDom(feed.createWireFeed());
    }

    /**
     * Creates a JDOM document for the given SyndFeedImpl.
     * <p>
     * This method does not use the feed encoding property.
     * <p>
     * @param feed Abstract feed to create JDOM document from. The type of the SyndFeedImpl must match
     *        the type given to the FeedOuptut constructor.
     * @return the JDOM document for the given SyndFeedImpl.
     * @throws FeedException thrown if the JDOM document for the feed could not be created.
     *
     */
    public Document outputJDom(SyndFeed feed) throws FeedException {
        return _feedOutput.outputJDom(feed.createWireFeed());
    }

}
