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

import java.util.Locale;

import org.jdom2.Document;

import com.rometools.rome.feed.WireFeed;

/**
 * Parses an XML document (JDOM) into a feed bean.
 * WireFeedParser instances must thread safe.
 */
public interface WireFeedParser {

    /**
     * Returns the type of feed the parser handles.
     *
     * @see WireFeed for details on the format of this string.
     * @return the type of feed the parser handles.
     *
     */
    public String getType();

    /**
     * Inspects an XML Document (JDOM) to check if it can parse it.
     * It checks if the given document if the type of feeds the parser understands.
     *
     * @param document XML Document (JDOM) to check if it can be parsed by this parser.
     * @return <b>true</b> if the parser know how to parser this feed, <b>false</b> otherwise.
     *
     */
    public boolean isMyType(Document document);

    /**
     * Parses an XML document (JDOM Document) into a feed bean.
     *
     * @param document XML document (JDOM) to parse.
     * @param validate indicates if the feed should be strictly validated (NOT YET IMPLEMENTED).
     * @param locale the Locale instance.
     * @return the resulting feed bean.
     * @throws IllegalArgumentException thrown if the parser cannot handle the given feed type.
     * @throws FeedException thrown if a feed bean cannot be created out of the XML document (JDOM).
     *
     */
    public WireFeed parse(Document document, boolean validate, final Locale locale) throws IllegalArgumentException, FeedException;

}
