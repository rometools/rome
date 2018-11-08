/*
 * Copyright 2018 Maximilian Irro
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
package com.rometools.modules.psc.io;

import com.rometools.modules.psc.modules.PodloveSimpleChapterModule;
import com.rometools.modules.psc.types.SimpleChapter;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The ModuleGenerator implementation for the Podlove Simple Chapter plug in.
 */
public class PodloveSimpleChapterGenerator implements ModuleGenerator {

    private static final Namespace NS = Namespace.getNamespace(PodloveSimpleChapterAttribute.PREFIX, PodloveSimpleChapterModule.URI);
    private static final Set<Namespace> NAMESPACES;

    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    @Override
    public final String getNamespaceUri() {
        return PodloveSimpleChapterModule.URI;
    }

    @Override
    public final Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(Module module, Element element) {
        if (module instanceof PodloveSimpleChapterModule) {
            final PodloveSimpleChapterModule m = (PodloveSimpleChapterModule) module;
            generateChapters(m.getChapters(), element);
        }
    }

    private void generateChapters(final List<SimpleChapter> chapters, final Element parent) {
        final Element cs = new Element(PodloveSimpleChapterAttribute.CHAPTERS, NS);

        cs.setAttribute(PodloveSimpleChapterAttribute.VERSION, PodloveSimpleChapterModule.VERSION);

        for (SimpleChapter c : chapters) {
            cs.addContent(generateChapter(c));
        }

        parent.addContent(cs);
    }

    private Element generateChapter(final SimpleChapter c) {
        final Element e = new Element(PodloveSimpleChapterAttribute.CHAPTER, NS);

        addNotNullAttribute(e, PodloveSimpleChapterAttribute.START, c.getStart());
        addNotNullAttribute(e, PodloveSimpleChapterAttribute.TITLE, c.getTitle());
        addNotNullAttribute(e, PodloveSimpleChapterAttribute.HREF, c.getHref());
        addNotNullAttribute(e, PodloveSimpleChapterAttribute.IMAGE, c.getImage());

        return e;
    }

    private void addNotNullAttribute(final Element target, final String name, final Object value) {
        if (target == null || value == null) {
            return;
        } else {
            target.setAttribute(name, value.toString());
        }
    }
}
