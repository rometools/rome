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

import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;

/**
 * Feed Generator for RSS 0.91
 * <p/>
 *
 * @author Elaine Chien
 *
 */
public class RSS091NetscapeGenerator extends RSS091UserlandGenerator {
    private String _version;

    public RSS091NetscapeGenerator() {
        this("rss_0.91N","0.91");
    }

    protected RSS091NetscapeGenerator(String type,String version) {
        super(type,version);
    }

    protected Document createDocument(Element root) {
        Document doc = new Document(root);
        DocType docType = new DocType(RSS091NetscapeParser.ELEMENT_NAME,
                                      RSS091NetscapeParser.PUBLIC_ID,
                                      RSS091NetscapeParser.SYSTEM_ID);
        doc.setDocType(docType);
        return doc;
    }

    protected String getTextInputLabel() {
        return "textinput";
    }

    /**
     * To be overriden by RSS 0.91 Netscape and RSS 0.94
     */
    protected boolean isHourFormat24() {
        return false;
    }

}
