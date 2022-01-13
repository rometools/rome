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
package com.rometools.rome.io.impl;

import java.util.List;

import org.jdom2.Document;

import com.rometools.rome.io.WireFeedParser;

/**
 * Parses an XML document (JDOM Document) into a Feed.
 * <p>
 * It accepts all flavors of RSS (0.90, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) and Atom 0.3 feeds.
 * <p>
 * The WireFeedParser is a liberal parser.
 * <p>
 * WireFeedParser instances are thread safe.
 * <p>
 * Parsers for a specific type must extend this class and register in the parser list. (Right now
 * registration is hardcoded in the WireFeedParser constructor).
 */
public class FeedParsers extends PluginManager<WireFeedParser> {

    /**
     * WireFeedParser.classes= [className] ...
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

    public List<String> getSupportedFeedTypes() {
        return getKeys();
    }

    /**
     * Finds the real parser type for the given document feed.
     * <p>
     *
     * @param document document feed to find the parser for.
     * @return the parser for the given document or <b>null</b> if there is no parser for that
     *         document.
     *
     */
    public WireFeedParser getParserFor(final Document document) {
        final List<WireFeedParser> parsers = getPlugins();
        for (final WireFeedParser parser : parsers) {
            if (parser.isMyType(document)) {
                return parser;
            }
        }
        return null;
    }

    @Override
    protected String getKey(final WireFeedParser obj) {
        return obj.getType();
    }

}
