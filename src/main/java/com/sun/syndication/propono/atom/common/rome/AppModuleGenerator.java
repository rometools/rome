/*
 * Copyright 2007 Apache Software Foundation
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package com.sun.syndication.propono.atom.common.rome;

import com.sun.syndication.io.impl.DateParser;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jdom.Element;
import org.jdom.Namespace;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleGenerator;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Creates JDOM representation for APP Extension Module.
 */
public class AppModuleGenerator implements ModuleGenerator {
    private static final Namespace APP_NS  = 
        Namespace.getNamespace("app", AppModule.URI);

    public String getNamespaceUri() {
        return AppModule.URI;
    }

    private static final Set NAMESPACES;

    static {
        Set nss = new HashSet();
        nss.add(APP_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }
    
    /** Get namespaces associated with this module */
    public Set getNamespaces() {
        return NAMESPACES;
    }

    /** Generate JDOM element for module and add it to parent element */
    public void generate(Module module, Element parent) {
        AppModule m = (AppModule)module;
        
        if (m.getDraft() != null) {
            String draft = m.getDraft().booleanValue() ? "yes" : "no";
            Element control = new Element("control", APP_NS);
            control.addContent(generateSimpleElement("draft", draft));
            parent.addContent(control);
        }
        if (m.getEdited() != null) {
            Element edited = new Element("edited", APP_NS);
            // Inclulde millis in date/time
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            dateFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
            edited.addContent(dateFormater.format(m.getEdited()));
            parent.addContent(edited);
        }
    }

    private Element generateSimpleElement(String name, String value)  {
        Element element = new Element(name, APP_NS);
        element.addContent(value);
        return element;
    }
}
