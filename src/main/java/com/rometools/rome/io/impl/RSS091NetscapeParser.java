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

import org.jdom2.Attribute;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;

public class RSS091NetscapeParser extends RSS091UserlandParser {

    public RSS091NetscapeParser() {
        this("rss_0.91N");
    }

    protected RSS091NetscapeParser(final String type) {
        super(type);
    }

    static final String ELEMENT_NAME = "rss";
    static final String PUBLIC_ID = "-//Netscape Communications//DTD RSS 0.91//EN";
    static final String SYSTEM_ID = "http://my.netscape.com/publish/formats/rss-0.91.dtd";

    @Override
    public boolean isMyType(final Document document) {

        final Element rssRoot = document.getRootElement();
        final String name = rssRoot.getName();
        final Attribute version = rssRoot.getAttribute("version");
        final DocType docType = document.getDocType();

        return name.equals(ELEMENT_NAME) && version != null && version.getValue().equals(getRSSVersion()) && docType != null
                && ELEMENT_NAME.equals(docType.getElementName()) && PUBLIC_ID.equals(docType.getPublicID()) && SYSTEM_ID.equals(docType.getSystemID());

    }

    @Override
    protected boolean isHourFormat24(final Element rssRoot) {
        return false;
    }

    @Override
    protected String getTextInputLabel() {
        return "textinput";
    }

}
