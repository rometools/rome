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

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Feed Generator for RSS 0.91
 * <p/>
 */
public class RSS091NetscapeGenerator extends RSS091UserlandGenerator {

    public RSS091NetscapeGenerator() {
        this("rss_0.91N", "0.91");
    }

    protected RSS091NetscapeGenerator(final String type, final String version) {
        super(type, version);
    }

    @Override
    protected Document createDocument(final Element root) {
        final Document doc = new Document(root);
        final DocType docType = new DocType(RSS091NetscapeParser.ELEMENT_NAME, RSS091NetscapeParser.PUBLIC_ID, RSS091NetscapeParser.SYSTEM_ID);
        doc.setDocType(docType);
        return doc;
    }

    @Override
    protected String getTextInputLabel() {
        return "textinput";
    }

    /**
     * To be overriden by RSS 0.91 Netscape and RSS 0.94
     */
    @Override
    protected boolean isHourFormat24() {
        return false;
    }

}
