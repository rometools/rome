/*
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
package com.rometools.modules.thr.io;

import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.thr.ThreadingModule;
import com.rometools.modules.thr.ThreadingModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;

/**
 * Currently no support for thr:count, thr:updated, thr:total link attributes.
 */
public class ThreadingModuleParser implements ModuleParser {

    private static final Namespace NS = Namespace.getNamespace(ThreadingModule.URI);

    @Override
    public String getNamespaceUri() {
        return ThreadingModule.URI;
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        final ThreadingModule tm = new ThreadingModuleImpl();
        Element inReplyTo = element.getChild("in-reply-to", ThreadingModuleParser.NS);

        if (inReplyTo != null) {
            tm.setHref(inReplyTo.getAttributeValue("href"));
            tm.setRef(inReplyTo.getAttributeValue("ref"));
            tm.setSource(inReplyTo.getAttributeValue("source"));
            tm.setType(inReplyTo.getAttributeValue("type"));
            return tm;
        }

        return null;
    }
}
