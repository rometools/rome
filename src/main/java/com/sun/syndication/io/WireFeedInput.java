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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;
import org.jdom.input.JDOMParseException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.io.impl.FeedParsers;
import com.sun.syndication.io.impl.XmlFixerReader;

/**
 * Parses an XML document (File, InputStream, Reader, W3C SAX InputSource, W3C DOM Document or JDom DOcument)
 * into an WireFeed (RSS/Atom).
 * <p>
 * It accepts all flavors of RSS (0.90, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) and
 * Atom 0.3 feeds. Parsers are plugable (they must implement the WireFeedParser interface).
 * <p>
 * The WireFeedInput useds liberal parsers.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class WireFeedInput {

    private static Map clMap = new WeakHashMap();

    private static FeedParsers getFeedParsers() {
        synchronized(WireFeedInput.class) {
            FeedParsers parsers = (FeedParsers)
                clMap.get(Thread.currentThread().getContextClassLoader());
            if (parsers == null) {
                parsers = new FeedParsers();
                clMap.put(Thread.currentThread().getContextClassLoader(), parsers);
            }
            return parsers;
        }
    }

    private static final InputSource EMPTY_INPUTSOURCE = new InputSource(new ByteArrayInputStream(new byte[0]));
    private static final EntityResolver RESOLVER = new EmptyEntityResolver();

    private static class EmptyEntityResolver implements EntityResolver {
        public InputSource resolveEntity(String publicId, String systemId) {
            if(systemId != null && systemId.endsWith(".dtd")) return EMPTY_INPUTSOURCE;
            return null;
        }
    }

    private boolean _validate;

    private boolean _xmlHealerOn;

    /**
     * Returns the list of supported input feed types.
     * <p>
     * @see WireFeed for details on the format of these strings.
     * <p>
     * @return a list of String elements with the supported input feed types.
     *
     */
    public static List getSupportedFeedTypes() {
        return getFeedParsers().getSupportedFeedTypes();
    }

    /**
     * Creates a WireFeedInput instance with input validation turned off.
     * <p>
     *
     */
    public WireFeedInput() {
        this (false);
    }

    /**
     * Creates a WireFeedInput instance.
     * <p>
     * @param validate indicates if the input should be validated. NOT IMPLEMENTED YET (validation does not happen)
     *
     */
    public WireFeedInput(boolean validate) {
        _validate = false; // TODO FIX THIS THINGY
        _xmlHealerOn = true;
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
        _xmlHealerOn = heals;
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
        return _xmlHealerOn;
    }

    /**
     * Builds an WireFeed (RSS or Atom) from a file.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom.Document)'.
     * <p>
     * @param file file to read to create the WireFeed.
     * @return the WireFeed read from the file.
     * @throws FileNotFoundException thrown if the file could not be found.
     * @throws IOException thrown if there is problem reading the file.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(File file) throws FileNotFoundException,IOException,IllegalArgumentException,FeedException {
        WireFeed feed;
        Reader reader = new FileReader(file);
        if (_xmlHealerOn) {
            reader = new XmlFixerReader(reader);
        }
        feed = build(reader);
        reader.close();
        return feed;
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an Reader.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom.Document)'.
     * <p>
     * @param reader Reader to read to create the WireFeed.
     * @return the WireFeed read from the Reader.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(Reader reader) throws IllegalArgumentException,FeedException {
    	SAXBuilder saxBuilder = createSAXBuilder();
        try {
            if (_xmlHealerOn) {
                reader = new XmlFixerReader(reader);
            }            
            Document document = saxBuilder.build(reader);
            return build(document);
        }
        catch (JDOMParseException ex) {
            throw new ParsingFeedException("Invalid XML: " + ex.getMessage(), ex);
        }
        catch (IllegalArgumentException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new ParsingFeedException("Invalid XML",ex);
        }
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an W3C SAX InputSource.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom.Document)'.
     * <p>
     * @param is W3C SAX InputSource to read to create the WireFeed.
     * @return the WireFeed read from the W3C SAX InputSource.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(InputSource is) throws IllegalArgumentException,FeedException {
    	SAXBuilder saxBuilder = createSAXBuilder();
        try {
            Document document = saxBuilder.build(is);
            return build(document);
        }
        catch (JDOMParseException ex) {
            throw new ParsingFeedException("Invalid XML: " + ex.getMessage(), ex);
        }
        catch (IllegalArgumentException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new ParsingFeedException("Invalid XML",ex);
        }
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an W3C DOM document.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom.Document)'.
     * <p>
     * @param document W3C DOM document to read to create the WireFeed.
     * @return the WireFeed read from the W3C DOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(org.w3c.dom.Document document) throws IllegalArgumentException,FeedException {
        DOMBuilder domBuilder = new DOMBuilder();        
        try {
            Document jdomDoc = domBuilder.build(document);
            return build(jdomDoc);
        }
        catch (IllegalArgumentException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new ParsingFeedException("Invalid XML",ex);
        }
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an JDOM document.
     * <p>
     * NOTE: All other build methods delegate to this method.
     * <p>
     * @param document JDOM document to read to create the WireFeed.
     * @return the WireFeed read from the JDOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(Document document) throws IllegalArgumentException,FeedException {
        WireFeedParser parser = getFeedParsers().getParserFor(document);
        if (parser==null) {
            throw new IllegalArgumentException("Invalid document");
        }
        return parser.parse(document, _validate);
    }

    /**
     * Creates and sets up a org.jdom.input.SAXBuilder for parsing.
     * 
     * @return a new org.jdom.input.SAXBuilder object
     */
    protected SAXBuilder createSAXBuilder() {
        SAXBuilder saxBuilder = new SAXBuilder(_validate);        
        saxBuilder.setEntityResolver(RESOLVER);

        //
        // This code is needed to fix the security problem outlined in http://www.securityfocus.com/archive/1/297714
        //
        // Unfortunately there isn't an easy way to check if an XML parser supports a particular feature, so
        // we need to set it and catch the exception if it fails. We also need to subclass the JDom SAXBuilder 
        // class in order to get access to the underlying SAX parser - otherwise the features don't get set until
        // we are already building the document, by which time it's too late to fix the problem.
        //
        // Crimson is one parser which is known not to support these features.
		try {
			XMLReader parser = saxBuilder.createParser();
			try {				
				parser.setFeature("http://xml.org/sax/features/external-general-entities", false);
				saxBuilder.setFeature("http://xml.org/sax/features/external-general-entities", false);
			} catch (SAXNotRecognizedException e) {
				// ignore
			} catch (SAXNotSupportedException e) {
				// ignore
			}
			
			try {
				parser.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
				saxBuilder.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
			} catch (SAXNotRecognizedException e) {
				// ignore
			} catch (SAXNotSupportedException e) {
				// ignore
			}

			try {
				parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
				saxBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			} catch (SAXNotRecognizedException e) {
				// ignore
			} catch (SAXNotSupportedException e) {
				// ignore
			}
			
		} catch (JDOMException e) {
			throw new IllegalStateException("JDOM could not create a SAX parser");
		}

		saxBuilder.setExpandEntities(false);    
        return saxBuilder;
    }
}
