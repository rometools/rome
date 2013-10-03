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

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.io.impl.FeedGenerators;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.output.DOMOutputter;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.Writer;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Generates an XML document (String, File, OutputStream, Writer, W3C DOM document or JDOM document)
 * out of an WireFeed (RSS/Atom).
 * <p>
 * It generates all flavors of RSS (0.90, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) and
 * Atom 0.3 feeds. Generators are plugable (they must implement the ModuleParser interface).
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class WireFeedOutput {
    private static Map clMap = new WeakHashMap();

    private static FeedGenerators getFeedGenerators() {
        synchronized(WireFeedOutput.class) {
            FeedGenerators generators = (FeedGenerators)
                clMap.get(Thread.currentThread().getContextClassLoader());
            if (generators == null) {
                generators = new FeedGenerators();
                clMap.put(Thread.currentThread().getContextClassLoader(), generators);
            }
            return generators;
        }
    }

    /**
     * Returns the list of supported output feed types.
     * <p>
     * @see WireFeed for details on the format of these strings.
     * <p>
     * @return a list of String elements with the supported output feed types.
     *
     */
    public static List getSupportedFeedTypes() {
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
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is the responsibility
     * of the developer to ensure that if the String is written to a character stream the stream charset is the same as
     * the feed encoding property.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @return a String with the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public String outputString(WireFeed feed) throws IllegalArgumentException,FeedException {
        return outputString(feed, true);
    }

    /**
     * Creates a String with the XML representation for the given WireFeed.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is the responsibility
     * of the developer to ensure that if the String is written to a character stream the stream charset is the same as
     * the feed encoding property.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @param prettyPrint pretty-print XML (true) oder collapsed
     * @return a String with the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public String outputString(WireFeed feed, boolean prettyPrint) throws IllegalArgumentException,FeedException {
        Document doc = outputJDom(feed);
        String encoding = feed.getEncoding();
        Format format = prettyPrint ? Format.getPrettyFormat() : Format.getCompactFormat();
        if (encoding!=null) {
            format.setEncoding(encoding);
        }
        XMLOutputter outputter = new XMLOutputter(format);
        return outputter.outputString(doc);
    }

    /**
     * Creates a File containing with the XML representation for the given WireFeed.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. The platform
     * default charset encoding is used to write the feed to the file. It is the responsibility
     * of the developer to ensure the feed encoding is set to the platform charset encoding.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @param file the file where to write the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws IOException thrown if there was some problem writing to the File.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(WireFeed feed,File file) throws IllegalArgumentException,IOException,FeedException {
        output(feed,file,true);
    }

    /**
     * Creates a File containing with the XML representation for the given WireFeed.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. The platform
     * default charset encoding is used to write the feed to the file. It is the responsibility
     * of the developer to ensure the feed encoding is set to the platform charset encoding.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @param file the file where to write the XML representation for the given WireFeed.
     * @param prettyPrint pretty-print XML (true) oder collapsed
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws IOException thrown if there was some problem writing to the File.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(WireFeed feed,File file,boolean prettyPrint) throws IllegalArgumentException,IOException,FeedException {
        Writer writer = new FileWriter(file);
        output(feed,writer,prettyPrint);
        writer.close();
    }

    /**
     * Writes to an Writer the XML representation for the given WireFeed.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is the responsibility
     * of the developer to ensure the Writer instance is using the same charset encoding.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @param writer Writer to write the XML representation for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws IOException thrown if there was some problem writing to the Writer.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(WireFeed feed,Writer writer) throws IllegalArgumentException,IOException, FeedException {
        output(feed,writer,true);
    }

    /**
     * Writes to an Writer the XML representation for the given WireFeed.
     * <p>
     * If the feed encoding is not NULL, it will be used in the XML prolog encoding attribute. It is the responsibility
     * of the developer to ensure the Writer instance is using the same charset encoding.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create XML representation from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @param writer Writer to write the XML representation for the given WireFeed.
     * @param prettyPrint pretty-print XML (true) oder collapsed
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws IOException thrown if there was some problem writing to the Writer.
     * @throws FeedException thrown if the XML representation for the feed could not be created.
     *
     */
    public void output(WireFeed feed,Writer writer,boolean prettyPrint) throws IllegalArgumentException,IOException, FeedException {
        Document doc = outputJDom(feed);
        String encoding = feed.getEncoding();
        Format format = prettyPrint ? Format.getPrettyFormat() : Format.getCompactFormat();
        if (encoding!=null) {
            format.setEncoding(encoding);
        }
        XMLOutputter outputter = new XMLOutputter(format);
        outputter.output(doc,writer);
    }

    /**
     * Creates a W3C DOM document for the given WireFeed.
     * <p>
     * This method does not use the feed encoding property.
     * <p>
     * NOTE: This method delages to the 'Document WireFeedOutput#outputJDom(WireFeed)'.
     * <p>
     * @param feed Abstract feed to create W3C DOM document from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @return the W3C DOM document for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws FeedException thrown if the W3C DOM document for the feed could not be created.
     *
     */
    public org.w3c.dom.Document outputW3CDom(WireFeed feed) throws IllegalArgumentException,FeedException {
        Document doc = outputJDom(feed);
        DOMOutputter outputter = new DOMOutputter();
        try {
            return outputter.output(doc);
        }
        catch (JDOMException jdomEx) {
            throw new FeedException("Could not create DOM",jdomEx);
        }
    }

    /**
     * Creates a JDOM document for the given WireFeed.
     * <p>
     * This method does not use the feed encoding property.
     * <p>
     * NOTE: All other output methods delegate to this method.
     * <p>
     * @param feed Abstract feed to create JDOM document from. The type of the WireFeed must match
     *        the type given to the FeedOuptut constructor.
     * @return the JDOM document for the given WireFeed.
     * @throws IllegalArgumentException thrown if the feed type of the WireFeedOutput and WireFeed don't match.
     * @throws FeedException thrown if the JDOM document for the feed could not be created.
     *
     */
    public Document outputJDom(WireFeed feed) throws IllegalArgumentException,FeedException {
        String type = feed.getFeedType();
        WireFeedGenerator generator = getFeedGenerators().getGenerator(type);
        if (generator==null) {
            throw new IllegalArgumentException("Invalid feed type ["+type+"]");
        }

        if (!generator.getType().equals(type)) {
            throw new IllegalArgumentException("WireFeedOutput type["+type+"] and WireFeed type ["+
                                               type+"] don't match");
        }
        return generator.generate(feed);
    }

}
