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
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.output.DOMOutputter;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.impl.ConfigurableClassLoader;
import com.rometools.rome.io.impl.FeedGenerators;

/**
 * Generates an XML document (String, File, OutputStream, Writer, W3C DOM document or JDOM document)
 * out of an WireFeed (RSS/Atom).
 * <p>
 * It generates all flavors of RSS (0.90, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) and Atom 0.3 feeds.
 * Generators are plugable (they must implement the ModuleParser interface).
 */
public class WireFeedOutput {
    private static Map<ClassLoader, FeedGenerators> clMap = new WeakHashMap<ClassLoader, FeedGenerators>();

    private static FeedGenerators getFeedGenerators() {
        synchronized (WireFeedOutput.class) {
            final ClassLoader classLoader = ConfigurableClassLoader.INSTANCE.getClassLoader();
            FeedGenerators generators = clMap.get(classLoader);
            if (generators == null) {
                generators = new FeedGenerators();
                clMap.put(classLoader, generators);
            }
            return generators;
        }
    }

    /**
     * Returns the list of supported output feed types.
     * <p>
     *
     * @see WireFeed for details on the format of these strings.
     *      <p>
     * @return a list of String elements with the supported output feed types.
     *
     */
    public static List<String> getSupportedFeedTypes() {
        return getFeedGenerators().getSupportedFeedTypes();
    }

    /**
     * Creates a FeedOuput instance.
     * <p>
     *
     */
    public WireFeedOutput() {
    }

    /**
     * Creates a String with the XML representation for the given WireFeed.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is
     * the responsibility of the developer to ensure that if the String is written to a character
     * stream the stream charset is the same as the feed encoding property.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     *
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must
     *            match the type given to the FeedOuptut constructor.
     * @return a String with the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed
     *             don't match.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public String outputString(final WireFeed feed) throws IllegalArgumentException, FeedException {
        return this.outputString(feed, true);
    }

    /**
     * Creates a String with the XML representation for the given WireFeed.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is
     * the responsibility of the developer to ensure that if the String is written to a character
     * stream the stream charset is the same as the feed encoding property.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     *
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must
     *            match the type given to the FeedOuptut constructor.
     * @param prettyPrint pretty-print XML (true) oder collapsed
     * @return a String with the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed
     *             don't match.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public String outputString(final WireFeed feed, final boolean prettyPrint) throws IllegalArgumentException, FeedException {
        final Document doc = outputJDom(feed);
        final String encoding = feed.getEncoding();
        Format format;
        if (prettyPrint) {
            format = Format.getPrettyFormat();
        } else {
            format = Format.getCompactFormat();
        }
        if (encoding != null) {
            format.setEncoding(encoding);
        }
        final XMLOutputter outputter = new XMLOutputter(format);
        return outputter.outputString(doc);
    }

    /**
     * Creates a File containing with the XML representation for the given WireFeed.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. The
     * platform default charset encoding is used to write the feed to the file. It is the
     * responsibility of the developer to ensure the feed encoding is set to the platform charset
     * encoding.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     *
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must
     *            match the type given to the FeedOuptut constructor.
     * @param file the file where to write the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed
     *             don't match.
     * @throws IOException thrown if there was some problem writing to the File.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(final WireFeed feed, final File file) throws IllegalArgumentException, IOException, FeedException {
        this.output(feed, file, true);
    }

    /**
     * Creates a File containing with the XML representation for the given WireFeed.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. The
     * platform default charset encoding is used to write the feed to the file. It is the
     * responsibility of the developer to ensure the feed encoding is set to the platform charset
     * encoding.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     *
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must
     *            match the type given to the FeedOuptut constructor.
     * @param file the file where to write the XML representation for the given WireFeed.
     * @param prettyPrint pretty-print XML (true) oder collapsed
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed
     *             don't match.
     * @throws IOException thrown if there was some problem writing to the File.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(final WireFeed feed, final File file, final boolean prettyPrint) throws IllegalArgumentException, IOException, FeedException {
        final Writer writer = new FileWriter(file);
        try {
            this.output(feed, writer, prettyPrint);
        } finally {
            writer.close();
        }
    }

    /**
     * Writes to an Writer the XML representation for the given WireFeed.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is
     * the responsibility of the developer to ensure the Writer instance is using the same charset
     * encoding.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     *
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must
     *            match the type given to the FeedOuptut constructor.
     * @param writer Writer to write the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed
     *             don't match.
     * @throws IOException thrown if there was some problem writing to the Writer.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(final WireFeed feed, final Writer writer) throws IllegalArgumentException, IOException, FeedException {
        this.output(feed, writer, true);
    }

    /**
     * Writes to an Writer the XML representation for the given WireFeed.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is
     * the responsibility of the developer to ensure the Writer instance is using the same charset
     * encoding.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     *
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must
     *            match the type given to the FeedOuptut constructor.
     * @param writer Writer to write the XML representation for the given WireFeed.
     * @param prettyPrint pretty-print XML (true) oder collapsed
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed
     *             don't match.
     * @throws IOException thrown if there was some problem writing to the Writer.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(final WireFeed feed, final Writer writer, final boolean prettyPrint) throws IllegalArgumentException, IOException, FeedException {
        final Document doc = outputJDom(feed);
        final String encoding = feed.getEncoding();
        Format format;
        if (prettyPrint) {
            format = Format.getPrettyFormat();
        } else {
            format = Format.getCompactFormat();
        }
        if (encoding != null) {
            format.setEncoding(encoding);
        }
        final XMLOutputter outputter = new XMLOutputter(format);
        outputter.output(doc, writer);
    }

    /**
     * Creates a W3C DOM document for the given WireFeed.
     * <p>
     * This method does not use the feed encoding property.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     *
     * @param feed Abstract feed to create W3C DOM document from. The type of the WireFeed must
     *            match the type given to the FeedOuptut constructor.
     * @return the W3C DOM document for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed
     *             don't match.
     * @throws FeedException thrown if the W3C DOM document for the feed could not be created.
     *
     */
    public org.w3c.dom.Document outputW3CDom(final WireFeed feed) throws IllegalArgumentException, FeedException {
        final Document doc = outputJDom(feed);
        final DOMOutputter outputter = new DOMOutputter();
        try {
            return outputter.output(doc);
        } catch (final JDOMException jdomEx) {
            throw new FeedException("Could not create DOM", jdomEx);
        }
    }

    /**
     * Creates a JDOM document for the given WireFeed.
     * <p>
     * This method does not use the feed encoding property.
     * <p>
     * NOTE: All other output methods delegate to this method.
     * <p>
     *
     * @param feed Abstract feed to create JDOM document from. The type of the WireFeed must match
     *            the type given to the FeedOuptut constructor.
     * @return the JDOM document for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed
     *             don't match.
     * @throws FeedException thrown if the JDOM document for the feed could not be created.
     *
     */
    public Document outputJDom(final WireFeed feed) throws IllegalArgumentException, FeedException {
        final String type = feed.getFeedType();
        final WireFeedGenerator generator = getFeedGenerators().getGenerator(type);
        if (generator == null) {
            throw new IllegalArgumentException("Invalid feed type [" + type + "]");
        }

        if (!generator.getType().equals(type)) {
            throw new IllegalArgumentException("WireFeedOutput type[" + type + "] and WireFeed type [" + type + "] don't match");
        }
        return generator.generate(feed);
    }

}
