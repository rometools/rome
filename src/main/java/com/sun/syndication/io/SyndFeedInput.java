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
import com.sun.syndication.feed.synd.SyndFeedImpl;
import org.jdom2.Document;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

/**
 * Parses an XML document (File, InputStream, Reader, W3C SAX InputSource, W3C DOM Document or JDom DOcument)
 * into an SyndFeedImpl.
 * <p>
 * It delegates to a WireFeedInput to handle all feed types.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndFeedInput {
    private WireFeedInput _feedInput;
    private boolean preserveWireFeed = false;

    /**
     * Creates a SyndFeedInput instance with input validation turned off.
     * <p>
     *
     */
    public SyndFeedInput() {
        this(false);
    }

    /**
     * Creates a SyndFeedInput instance.
     * <p>
     * @param validate indicates if the input should be validated. NOT IMPLEMENTED YET (validation does not happen)
     *
     */
    public SyndFeedInput(boolean validate) {
        _feedInput = new WireFeedInput(validate);
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
     * @param heals TRUE enables stream healing, FALSE disables it.
     *
     */
    public void setXmlHealerOn(boolean heals) {
        _feedInput.setXmlHealerOn(heals);
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
     * @return TRUE if healing is enabled, FALSE if not.
     *
     */
    public boolean getXmlHealerOn() {
        return _feedInput.getXmlHealerOn();
    }


    /**
     * Builds SyndFeedImpl from a file.
     * <p>
     * @param file file to read to create the SyndFeedImpl.
     * @return the SyndFeedImpl read from the file.
     * @throws FileNotFoundException thrown if the file could not be found.
     * @throws IOException thrown if there is problem reading the file.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeed build(File file) throws FileNotFoundException,IOException,IllegalArgumentException,FeedException {
        return new SyndFeedImpl(_feedInput.build(file), preserveWireFeed);
    }

    /**
     * Builds SyndFeedImpl from an Reader.
     * <p>
     * @param reader Reader to read to create the SyndFeedImpl.
     * @return the SyndFeedImpl read from the Reader.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeed build(Reader reader) throws IllegalArgumentException,FeedException {
        return new SyndFeedImpl(_feedInput.build(reader), preserveWireFeed);
    }

    /**
     * Builds SyndFeedImpl from an W3C SAX InputSource.
     * <p>
     * @param is W3C SAX InputSource to read to create the SyndFeedImpl.
     * @return the SyndFeedImpl read from the W3C SAX InputSource.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeed build(InputSource is) throws IllegalArgumentException,FeedException {
        return new SyndFeedImpl(_feedInput.build(is), preserveWireFeed);
    }

    /**
     * Builds SyndFeedImpl from an W3C DOM document.
     * <p>
     * @param document W3C DOM document to read to create the SyndFeedImpl.
     * @return the SyndFeedImpl read from the W3C DOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeed build(org.w3c.dom.Document document) throws IllegalArgumentException,FeedException {
        return new SyndFeedImpl(_feedInput.build(document), preserveWireFeed);
    }

    /**
     * Builds SyndFeedImpl from an JDOM document.
     * <p>
     * @param document JDOM document to read to create the SyndFeedImpl.
     * @return the SyndFeedImpl read from the JDOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public SyndFeed build(Document document) throws IllegalArgumentException,FeedException {
        return new SyndFeedImpl(_feedInput.build(document), preserveWireFeed);
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
	 * @param preserveWireFeed set to true to make the WireFeed is made available in the SyndFeed. False by default.
	 */
	public void setPreserveWireFeed(boolean preserveWireFeed) {
		this.preserveWireFeed = preserveWireFeed;
	}

}
