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
package com.rometools.propono.atom.common.rome;

import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import com.rometools.rome.io.impl.DateParser;

/**
 * Parses APP module information from a JDOM element and into <code>AppModule</code> form.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class AppModuleParser implements ModuleParser {

    /** Get URI of module namespace */
    @Override
    public String getNamespaceUri() {
        return AppModule.URI;
    }

    /** Get namespace of module */
    public Namespace getContentNamespace() {
        return Namespace.getNamespace(AppModule.URI);
    }

    /** Parse JDOM element into module */
    @Override
    public Module parse(final Element elem, final Locale locale) {
        final AppModule m = new AppModuleImpl();
        final Element control = elem.getChild("control", getContentNamespace());
        if (control != null) {
            final Element draftElem = control.getChild("draft", getContentNamespace());
            if (draftElem != null) {
                if ("yes".equals(draftElem.getText())) {
                    m.setDraft(Boolean.TRUE);
                }
                if ("no".equals(draftElem.getText())) {
                    m.setDraft(Boolean.FALSE);
                }
            }
        }
        final Element edited = elem.getChild("edited", getContentNamespace());
        if (edited != null) {
            try {
                m.setEdited(DateParser.parseW3CDateTime(edited.getTextTrim(), locale));
            } catch (final Exception ignored) {
            }
        }
        return m;
    }
}
