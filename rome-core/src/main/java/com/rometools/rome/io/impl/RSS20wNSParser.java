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

import java.util.Locale;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.WireFeed;

/**
 * To address issue with certain feeds (brought up by Charles Miller):
 *
 * "During the debacle that was the rollout of RSS2.0, this namespace was tried, and even appeared
 * in Dave Winer's Scripting News feed for a while. It was then withdrawn, but the wonderful thing
 * about standards is the moment you roll one out, even if it's marked as unfinished and subject to
 * change, someone will end up stuck with it forever."
 *
 * Note that there is not counter part on the generator, we only generate the final RSS2
 *
 */
public class RSS20wNSParser extends RSS20Parser {

    private static String RSS20_URI = "http://backend.userland.com/rss2";

    public RSS20wNSParser() {
        this("rss_2.0wNS");
    }

    protected RSS20wNSParser(final String type) {
        super(type);
    }

    @Override
    public boolean isMyType(final Document document) {
        final Element rssRoot = document.getRootElement();
        final Namespace defaultNS = rssRoot.getNamespace();
        return defaultNS != null && defaultNS.equals(getRSSNamespace()) && super.isMyType(document);
    }

    @Override
    protected Namespace getRSSNamespace() {
        return Namespace.getNamespace(RSS20_URI);
    }

    /**
     * After we parse the feed we put "rss_2.0" in it (so converters and generators work) this
     * parser is a phantom.
     *
     */
    @Override
    protected WireFeed parseChannel(final Element rssRoot, final Locale locale) {
        final WireFeed wFeed = super.parseChannel(rssRoot, locale);
        wFeed.setFeedType("rss_2.0");
        return wFeed;
    }

}
