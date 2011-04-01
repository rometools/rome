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

import org.jdom.*;

/**
 */
public class RSS091NetscapeParser extends RSS091UserlandParser {

    public RSS091NetscapeParser() {
        this("rss_0.91N");
    }

    protected RSS091NetscapeParser(String type) {
        super(type);
    }

    static final String ELEMENT_NAME = "rss";
    static final String PUBLIC_ID = "-//Netscape Communications//DTD RSS 0.91//EN";
    static final String SYSTEM_ID = "http://my.netscape.com/publish/formats/rss-0.91.dtd";

    public boolean isMyType(Document document) {
        boolean ok = false;
        Element rssRoot = document.getRootElement();
        ok = rssRoot.getName().equals("rss");
        if (ok) {
            ok = false;
            Attribute version = rssRoot.getAttribute("version");
            if (version!=null) {
                ok = version.getValue().equals(getRSSVersion());
                if (ok) {
                    ok = false;
                    DocType docType = document.getDocType();

                    if (docType!=null) {
                        ok = ELEMENT_NAME.equals(docType.getElementName());
                        ok = ok && PUBLIC_ID.equals(docType.getPublicID());
                        ok = ok && SYSTEM_ID.equals(docType.getSystemID());
                    }
                }
            }
        }
        return ok;
    }

    protected boolean isHourFormat24(Element rssRoot) {
        return false;
    }

    protected String getTextInputLabel() {
        return "textinput";
    }

}
