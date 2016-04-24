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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.sax.XMLReaders;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.impl.ConfigurableClassLoader;
import com.rometools.rome.io.impl.FeedParsers;
import com.rometools.rome.io.impl.XmlFixerReader;

/**
 * Parses an XML document (File, InputStream, Reader, W3C SAX InputSource, W3C DOM Document or JDom
 * DOcument) into an WireFeed (RSS/Atom).
 * <p>
 * It accepts all flavors of RSS (0.90, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) and Atom 0.3 feeds.
 * Parsers are plugable (they must implement the WireFeedParser interface).
 * <p>
 * The WireFeedInput useds liberal parsers.
 * <p>
 *
 * @author Alejandro Abdelnur
 *
 */
public class WireFeedInput {

    private static final InputSource EMPTY_INPUTSOURCE = new InputSource(new ByteArrayInputStream(new byte[0]));
    private static final EntityResolver RESOLVER = new EmptyEntityResolver();

    private static Map<ClassLoader, FeedParsers> clMap = new WeakHashMap<ClassLoader, FeedParsers>();

    private final boolean validate;
    private final Locale locale;

    private boolean xmlHealerOn;
    private boolean allowDoctypes = false;

    private static FeedParsers getFeedParsers() {
        synchronized (WireFeedInput.class) {
            final ClassLoader classLoader = ConfigurableClassLoader.INSTANCE.getClassLoader();
            FeedParsers parsers = clMap.get(classLoader);
            if (parsers == null) {
                parsers = new FeedParsers();
                clMap.put(classLoader, parsers);
            }
            return parsers;
        }
    }

    private static class EmptyEntityResolver implements EntityResolver {
        @Override
        public InputSource resolveEntity(final String publicId, final String systemId) {
            if (systemId != null && systemId.endsWith(".dtd")) {
                return EMPTY_INPUTSOURCE;
            }
            return null;
        }
    }

    /**
     * Returns the list of supported input feed types.
     * <p>
     *
     * @see WireFeed for details on the format of these strings.
     *      <p>
     * @return a list of String elements with the supported input feed types.
     *
     */
    public static List<String> getSupportedFeedTypes() {
        return getFeedParsers().getSupportedFeedTypes();
    }

    /**
     * Creates a WireFeedInput instance with input validation turned off.
     * <p>
     *
     */
    public WireFeedInput() {
        this(false, Locale.US);
    }

    /**
     * Creates a WireFeedInput instance.
     * <p>
     *
     * @param validate indicates if the input should be validated. NOT IMPLEMENTED YET (validation
     *            does not happen)
     *
     */
    public WireFeedInput(final boolean validate, final Locale locale) {
        this.validate = false; // TODO FIX THIS THINGY
        xmlHealerOn = true;
        this.locale = locale;
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
        xmlHealerOn = heals;
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
        return xmlHealerOn;
    }
    
    /**
     * Indicates whether Doctype declarations are allowed.
     *  
     * @return true when Doctype declarations are allowed, false otherwise
     */
    public boolean isAllowDoctypes() {
        return allowDoctypes;
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
        this.allowDoctypes = allowDoctypes;
    }

    /**
     * Builds an WireFeed (RSS or Atom) from a file.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom2.Document)'.
     * <p>
     *
     * @param file file to read to create the WireFeed.
     * @return the WireFeed read from the file.
     * @throws FileNotFoundException thrown if the file could not be found.
     * @throws IOException thrown if there is problem reading the file.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the
     *             underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(final File file) throws FileNotFoundException, IOException, IllegalArgumentException, FeedException {
        WireFeed feed;
        Reader reader = new FileReader(file);
        try {
            if (xmlHealerOn) {
                reader = new XmlFixerReader(reader);
            }
            feed = this.build(reader);
        } finally {
            reader.close();
        }
        return feed;
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an Reader.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom2.Document)'.
     * <p>
     *
     * @param reader Reader to read to create the WireFeed.
     * @return the WireFeed read from the Reader.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the
     *             underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(Reader reader) throws IllegalArgumentException, FeedException {
        final SAXBuilder saxBuilder = createSAXBuilder();
        try {
            if (xmlHealerOn) {
                reader = new XmlFixerReader(reader);
            }
            final Document document = saxBuilder.build(reader);
            return this.build(document);
        } catch (final JDOMParseException ex) {
            throw new ParsingFeedException("Invalid XML: " + ex.getMessage(), ex);
        } catch (final IllegalArgumentException ex) {
            throw ex;
        } catch (final Exception ex) {
            throw new ParsingFeedException("Invalid XML", ex);
        }
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an W3C SAX InputSource.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom2.Document)'.
     * <p>
     *
     * @param is W3C SAX InputSource to read to create the WireFeed.
     * @return the WireFeed read from the W3C SAX InputSource.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the
     *             underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(final InputSource is) throws IllegalArgumentException, FeedException {
        final SAXBuilder saxBuilder = createSAXBuilder();
        try {
            final Document document = saxBuilder.build(is);
            return this.build(document);
        } catch (final JDOMParseException ex) {
            throw new ParsingFeedException("Invalid XML: " + ex.getMessage(), ex);
        } catch (final IllegalArgumentException ex) {
            throw ex;
        } catch (final Exception ex) {
            throw new ParsingFeedException("Invalid XML", ex);
        }
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an W3C DOM document.
     * <p>
     * NOTE: This method delages to the 'AsbtractFeed WireFeedInput#build(org.jdom2.Document)'.
     * <p>
     *
     * @param document W3C DOM document to read to create the WireFeed.
     * @return the WireFeed read from the W3C DOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the
     *             underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(final org.w3c.dom.Document document) throws IllegalArgumentException, FeedException {
        final DOMBuilder domBuilder = new DOMBuilder();
        try {
            final Document jdomDoc = domBuilder.build(document);
            return this.build(jdomDoc);
        } catch (final IllegalArgumentException ex) {
            throw ex;
        } catch (final Exception ex) {
            throw new ParsingFeedException("Invalid XML", ex);
        }
    }

    /**
     * Builds an WireFeed (RSS or Atom) from an JDOM document.
     * <p>
     * NOTE: All other build methods delegate to this method.
     * <p>
     *
     * @param document JDOM document to read to create the WireFeed.
     * @return the WireFeed read from the JDOM document.
     * @throws IllegalArgumentException thrown if feed type could not be understood by any of the
     *             underlying parsers.
     * @throws FeedException if the feed could not be parsed
     *
     */
    public WireFeed build(final Document document) throws IllegalArgumentException, FeedException {
        final WireFeedParser parser = getFeedParsers().getParserFor(document);
        if (parser == null) {
            throw new IllegalArgumentException("Invalid document");
        }
        return parser.parse(document, validate, locale);
    }

    /**
     * Creates and sets up a org.jdom2.input.SAXBuilder for parsing.
     *
     * @return a new org.jdom2.input.SAXBuilder object
     */
    protected SAXBuilder createSAXBuilder() {
        SAXBuilder saxBuilder;
        if (validate) {
            saxBuilder = new SAXBuilder(XMLReaders.DTDVALIDATING);
        } else {
            saxBuilder = new SAXBuilder(XMLReaders.NONVALIDATING);
        }
        saxBuilder.setEntityResolver(RESOLVER);

        //
        // This code is needed to fix the security problem outlined in
        // http://www.securityfocus.com/archive/1/297714
        //
        // Unfortunately there isn't an easy way to check if an XML parser
        // supports a particular feature, so
        // we need to set it and catch the exception if it fails. We also need
        // to subclass the JDom SAXBuilder
        // class in order to get access to the underlying SAX parser - otherwise
        // the features don't get set until
        // we are already building the document, by which time it's too late to
        // fix the problem.
        //
        // Crimson is one parser which is known not to support these features.
        try {
            
            final XMLReader parser = saxBuilder.createParser();
            
            setFeature(saxBuilder, parser, "http://xml.org/sax/features/external-general-entities", false);
            setFeature(saxBuilder, parser, "http://xml.org/sax/features/external-parameter-entities", false);
            setFeature(saxBuilder, parser, "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            if(!allowDoctypes) {
                setFeature(saxBuilder, parser, "http://apache.org/xml/features/disallow-doctype-decl", true);
            }

        } catch (final JDOMException e) {
            throw new IllegalStateException("JDOM could not create a SAX parser", e);
        }
        
        saxBuilder.setExpandEntities(false);

        return saxBuilder;

    }
    
    private void setFeature(SAXBuilder saxBuilder, XMLReader parser, String feature, boolean value) {
        if (isFeatureSupported(parser, feature, value)) {
            saxBuilder.setFeature(feature, value);
        }
    }

    private boolean isFeatureSupported(XMLReader parser, String feature, boolean value) {
        try {
            parser.setFeature(feature, value);
            return true;
        } catch (final SAXNotRecognizedException e) {
            return false;
        } catch (final SAXNotSupportedException e) {
            return false;
        }
    }

}
