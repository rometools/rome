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

import com.rometools.rome.io.WireFeedGenerator;

/**
 * Generates an XML document (JDOM Document) out of a Feed.
 * <p>
 * It can generate all flavors of RSS (0.90, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) and Atom 0.3 feed.
 * <p>
 * WireFeedGenerator instances are thread safe.
 * <p>
 * Generators for a specific type must extend this class and register in the generator list. (Right
 * now registration is hardcoded in the WireFeedGenerator constructor).
 */
public class FeedGenerators extends PluginManager<WireFeedGenerator> {

    /**
     * WireFeedGenerator.classes= [className] ...
     *
     */
    public static final String FEED_GENERATORS_KEY = "WireFeedGenerator.classes";

    public FeedGenerators() {
        super(FEED_GENERATORS_KEY);
    }

    public WireFeedGenerator getGenerator(final String feedType) {
        return getPlugin(feedType);
    }

    @Override
    protected String getKey(final WireFeedGenerator obj) {
        return obj.getType();
    }

    public List<String> getSupportedFeedTypes() {
        return getKeys();
    }

}
