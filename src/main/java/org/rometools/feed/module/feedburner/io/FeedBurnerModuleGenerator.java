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
package org.rometools.feed.module.feedburner.io;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleGenerator;

import org.jdom.Element;
import org.jdom.Namespace;

import org.rometools.feed.module.feedburner.FeedBurner;

import java.util.HashSet;
import java.util.Set;


/**
 * ModuleGenerator implementation for the FeedBurner RSS extension.
 *
 * @version 1.0
 * @author Georg Schmidl <georg.schmidl@scandio.de>
 *
 */
public class FeedBurnerModuleGenerator implements ModuleGenerator {
    private static final Namespace NS = Namespace.getNamespace("feedburner", FeedBurner.URI);

    public String getNamespaceUri() {
        return FeedBurner.URI;
    }

    public Set getNamespaces() {
        HashSet set = new HashSet();
        set.add(FeedBurnerModuleGenerator.NS);

        return set;
    }

    public void generate(Module module, Element element) {
        if (!(module instanceof FeedBurner)) {
            return;
        }

        FeedBurner feedBurner = (FeedBurner) module;

        if (feedBurner.getAwareness() != null) {
            element.addContent(this.generateSimpleElement("awareness", feedBurner.getAwareness()));
        }

        if (feedBurner.getOrigLink() != null) {
            element.addContent(this.generateSimpleElement("origLink", feedBurner.getOrigLink()));
        }

        if (feedBurner.getOrigEnclosureLink() != null) {
            element.addContent(this.generateSimpleElement("origEnclosureLink", feedBurner.getOrigEnclosureLink()));
        }
    }

    protected Element generateSimpleElement(String name, String value) {
        Element element = new Element(name, FeedBurnerModuleGenerator.NS);
        element.addContent(value);

        return element;
    }
}
