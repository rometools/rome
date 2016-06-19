/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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
 */

package com.rometools.modules.slash.io;

import java.util.Locale;
import java.util.StringTokenizer;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.slash.Slash;
import com.rometools.modules.slash.SlashImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;

/**
 * ModuleParser implementation for Slash RSS.
 */
public class SlashModuleParser implements ModuleParser {

    private static final Namespace NS = Namespace.getNamespace(Slash.URI);

    public SlashModuleParser() {
        super();
    }

    @Override
    public String getNamespaceUri() {
        return Slash.URI;
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        final SlashImpl si = new SlashImpl();
        Element tag = element.getChild("hit_parade", SlashModuleParser.NS);
        if (tag != null) {
            final StringTokenizer tok = new StringTokenizer(tag.getText(), ",");
            final Integer[] hp = new Integer[tok.countTokens()];
            for (int i = 0; tok.hasMoreTokens(); i++) {
                hp[i] = new Integer(tok.nextToken());
            }
            si.setHitParade(hp);
        }
        tag = null;
        tag = element.getChild("comments", SlashModuleParser.NS);
        if (tag != null) {
            si.setComments(new Integer(tag.getText()));
        }
        tag = null;
        tag = element.getChild("department", SlashModuleParser.NS);
        if (tag != null) {
            si.setDepartment(tag.getText().trim());
        }
        tag = null;
        tag = element.getChild("section", SlashModuleParser.NS);
        if (tag != null) {
            si.setSection(tag.getText().trim());
        }
        if (si.getHitParade() != null || si.getComments() != null || si.getDepartment() != null || si.getSection() != null) {
            return si;
        }
        return null;
    }

}
