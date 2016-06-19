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
package com.rometools.rome.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Locale;

import org.jdom2.Document;
import org.xml.sax.InputSource;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;

/**
 * Parses an XML document (File, InputStream, Reader, W3C SAX InputSource, W3C DOM Document or JDom
 * DOcument) into an SyndFeedImpl.
 * <p>
 * It delegates to a WireFeedInput to handle all feed types.
 */
public class SyndFeedInput {
    private final WireFeedInput feedInput;
    private boolean preserveWireFeed = false;

    /**
     * Creates a SyndFeedInput instance with input validation turned off.
     * <p>
     *
     */
    public SyndFeedInput() {
        this(false, Locale.US);
    }

    /**
     * Creates a SyndFeedInput instance.
     * <p>
     *
     * @param validate indicates if the input should be validated. NOT IMPLEMENTED YET (validation
     *            does not happen)
     *
     */
    public SyndFeedInput(final boolean validate, final Locale locale) {
        feedInput = new WireFeedInput(validate, locale);
    }

    /**
     * Enables XML healing in the WiredFeedInput instance.
     * <p>
     * Healing trims leading chars from the stream (empty spaces and comments) until the XML prolog.
     * <p>
     * Healing resolves HTML entities (from literal to code number) in the reader.
     * <p>
     * The healing is done only with the build(File) and build(Reader) signatures.
     * <p>
     * By default is TRUE.
     * <p>
     *
     * @param heals TRUE enables stream healing, FALSE disables it.
     *
     */
    public void setXmlHealerOn(final boolean heals) {
        feedInput.setXmlHealerOn(heals);
    }

    /**
     * Indicates if the WiredFeedInput instance will XML heal (if necessary) the character stream.
     * <p>
     * Healing trims leading chars from the stream (empty spaces and comments) until the XML prolog.
     * <p>
     * Healing resolves HTML entities (from literal to code number) in the reader.
     * <p>
     * The healing is done only with the build(File) and build(Reader) signatures.
     * <p>
     * By default is TRUE.
     * <p>
     *
     * @return TRUE if healing is enabled, FALSE if not.
     *
     */
    public boolean getXmlHealerOn() {
        return feedInput.getXmlHealerOn();
    }
    
    /**
     * Indicates whether Doctype declarations are allowed.
     *  
     * @return true when Doctype declarations are allowed, false otherwise
     */
    public boolean isAllowDoctypes() {
        return feedInput.isAllowDoctypes();
    }

    /**
     * Since ROME 1.5.1 we fixed a security vulnerability by disallowing Doctype declarations by default. 
     * This change breaks the compatibility with at least RSS 0.91N because it requires a Doctype declaration. 
     * You are able to allow Doctype declarations again with this property. You should only activate it 
     * when the feeds that you process are absolutely trustful. 
     *  
     * @param allowDoctypes true when Doctype declarations should be allowed again, false otherwise
     */
    public void setAllowDoctypes(boolean allowDoctypes) {
        feedInput.setAllowDoctypes(allowDoctypes);
    }

    /**
     * Builds SyndFeedImpl from a file.
     * <p>
     *
     * @param file file to read to create the SyndFeedImpl.
     * @return the SyndFeedImpl read from the file.
     * @throws FileNotFoundException thrown if the file could not be found.
     * @throws IOException thrown if there is problem reading the file.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the
     *             underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeed build(final File file) throws FileNotFoundException, IOException, IllegalArgumentException, FeedException {
        return new SyndFeedImpl(feedInput.build(file), preserveWireFeed);
    }

    /**
     * Builds SyndFeedImpl from an Reader.
     * <p>
     *
     * @param reader Reader to read to create the SyndFeedImpl.
     * @return the SyndFeedImpl read from the Reader.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the
     *             underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeed build(final Reader reader) throws IllegalArgumentException, FeedException {
        return new SyndFeedImpl(feedInput.build(reader), preserveWireFeed);
    }

    /**
     * Builds SyndFeedImpl from an W3C SAX InputSource.
     * <p>
     *
     * @param is W3C SAX InputSource to read to create the SyndFeedImpl.
     * @return the SyndFeedImpl read from the W3C SAX InputSource.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the
     *             underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeed build(final InputSource is) throws IllegalArgumentException, FeedException {
        return new SyndFeedImpl(feedInput.build(is), preserveWireFeed);
    }

    /**
     * Builds SyndFeedImpl from an W3C DOM document.
     * <p>
     *
     * @param document W3C DOM document to read to create the SyndFeedImpl.
     * @return the SyndFeedImpl read from the W3C DOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the
     *             underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeed build(final org.w3c.dom.Document document) throws IllegalArgumentException, FeedException {
        return new SyndFeedImpl(feedInput.build(document), preserveWireFeed);
    }

    /**
     * Builds SyndFeedImpl from an JDOM document.
     * <p>
     *
     * @param document JDOM document to read to create the SyndFeedImpl.
     * @return the SyndFeedImpl read from the JDOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the
     *             underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeed build(final Document document) throws IllegalArgumentException, FeedException {
        return new SyndFeedImpl(feedInput.build(document), preserveWireFeed);
    }

    /**
     *
     * @return true if the WireFeed is made available in the SyndFeed. False by default.
     */
    public boolean isPreserveWireFeed() {
        return preserveWireFeed;
    }

    /**
     *
     * @param preserveWireFeed set to true to make the WireFeed is made available in the SyndFeed.
     *            False by default.
     */
    public void setPreserveWireFeed(final boolean preserveWireFeed) {
        this.preserveWireFeed = preserveWireFeed;
    }

}
