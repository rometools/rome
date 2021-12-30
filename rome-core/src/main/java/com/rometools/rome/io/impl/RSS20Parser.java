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

import com.rometools.rome.feed.rss.Description;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

public class RSS20Parser extends RSS094Parser {

    public RSS20Parser() {
        this("rss_2.0");
    }

    protected RSS20Parser(final String type) {
        super(type);
    }

    @Override
    protected String getRSSVersion() {
        return "2.0";
    }

    @Override
    protected boolean isHourFormat24(final Element rssRoot) {
        return false;
    }

    @Override
    protected Description parseItemDescription(final Element rssRoot, final Element eDesc) {
        final Description desc = super.parseItemDescription(rssRoot, eDesc);
        return desc;
    }

    @Override
    public boolean isMyType(final Document document) {
        return rootElementMatches(document)
               && (versionMatches(document) || versionAbsent(document));
    }

    private boolean rootElementMatches(final Document document) {
        return document.getRootElement().getName().equals("rss");
    }

    private boolean versionMatches(final Document document) {
        final Attribute version = document.getRootElement().getAttribute("version");
        return (version != null)
               && version.getValue().trim().startsWith(getRSSVersion());
    }

    private boolean versionAbsent(final Document document) {
        return document.getRootElement().getAttribute("version") == null;
    }
}
