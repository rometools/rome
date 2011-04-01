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
package com.sun.syndication.io.impl;

import com.sun.syndication.io.WireFeedParser;
import org.jdom.Document;
import java.util.List;

/**
 * Parses an XML document (JDOM Document) into a Feed.
 * <p>
 * It accepts all flavors of RSS (0.90, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) and
 * Atom 0.3 feeds.
 * <p>
 * The WireFeedParser is a liberal parser.
 * <p>
 * WireFeedParser instances are thread safe.
 * <p>
 * Parsers for a specific type must extend this class and register in the parser list.
 * (Right now registration is hardcoded in the WireFeedParser constructor).
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class FeedParsers extends PluginManager {

    /**
     * WireFeedParser.classes=  [className] ...
     *
     */
    public static final String FEED_PARSERS_KEY = "WireFeedParser.classes";

    /**
     * Creates a parser instance.
     * <p>
     *
     */
    public FeedParsers() {
        super(FEED_PARSERS_KEY);
    }

    public List getSupportedFeedTypes() {
        return getKeys();
    }

    /**
     * Finds the real parser type for the given document feed.
     * <p>
     * @param document document feed to find the parser for.
     * @return the parser for the given document or <b>null</b> if there is no parser for that document.
     *
     */
    public WireFeedParser getParserFor(Document document) {
        List parsers = getPlugins();
        WireFeedParser parser = null;
        for (int i=0;parser==null && i<parsers.size();i++) {
            parser = (WireFeedParser) parsers.get(i);
            if (!parser.isMyType(document)) {
                parser = null;
            }
        }
        return parser;
    }

    protected String getKey(Object obj) {
        return ((WireFeedParser)obj).getType();
    }

}
