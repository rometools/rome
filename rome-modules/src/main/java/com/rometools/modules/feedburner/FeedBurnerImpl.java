/*
 * Copyright 2010 Scandio GmbH.
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

package com.rometools.modules.feedburner;

import com.rometools.rome.feed.CopyFrom;

/**
 * Implementation of the FeedBurner RSS extension.
 */
public class FeedBurnerImpl implements FeedBurner {

    private static final long serialVersionUID = 1L;

    private String awareness;
    private String origLink;
    private String origEnclosureLink;

    @Override
    public String getAwareness() {
        return awareness;
    }

    @Override
    public void setAwareness(final String awareness) {
        this.awareness = awareness;
    }

    @Override
    public String getOrigLink() {
        return origLink;
    }

    @Override
    public void setOrigLink(final String origLink) {
        this.origLink = origLink;
    }

    @Override
    public String getOrigEnclosureLink() {
        return origEnclosureLink;
    }

    @Override
    public void setOrigEnclosureLink(final String origEnclosureLink) {
        this.origEnclosureLink = origEnclosureLink;
    }

    @Override
    public String getUri() {
        return FeedBurner.URI;
    }

    @Override
    public void copyFrom(final CopyFrom object) {
        final FeedBurner source = (FeedBurner) object;
        setAwareness(source.getAwareness());
        setOrigLink(source.getOrigLink());
        setOrigEnclosureLink(source.getOrigEnclosureLink());
    }

    @Override
    public Class<FeedBurner> getInterface() {
        return FeedBurner.class;
    }

    @Override
    public Object clone() {
        final FeedBurnerImpl fbi = new FeedBurnerImpl();
        fbi.copyFrom(this);
        return fbi;
    }
}
