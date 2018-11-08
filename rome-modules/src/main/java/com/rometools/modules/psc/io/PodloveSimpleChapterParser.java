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

import com.rometools.modules.psc.types.SimpleChapter;
import com.rometools.modules.psc.modules.PodloveSimpleChapterModule;
import com.rometools.modules.psc.modules.PodloveSimpleChapterModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * The ModuleParser implementation for the Podlove Simple Chapter plug in.
 */
public class PodloveSimpleChapterParser implements ModuleParser {

    private static final Namespace NS = Namespace.getNamespace(PodloveSimpleChapterModule.URI);

    @Override
    public String getNamespaceUri() {
        return PodloveSimpleChapterModule.URI;
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        final Element chaptersElement = element.getChild(PodloveSimpleChapterAttribute.CHAPTERS, NS);
        if (chaptersElement != null) {
            final PodloveSimpleChapterModuleImpl m = new PodloveSimpleChapterModuleImpl();
            final List<Element> es = chaptersElement.getChildren(PodloveSimpleChapterAttribute.CHAPTER, NS);
            if (!es.isEmpty()) {
                final List<SimpleChapter> result = new LinkedList<SimpleChapter>();
                for (Element e : es) {
                    final SimpleChapter c = parseChapter(e);
                    result.add(c);
                }
                m.setChapters(result);
                return m;
            }
        }

        return null;
    }

    private SimpleChapter parseChapter(final Element eChapter) {
        final SimpleChapter chapter = new SimpleChapter();

        final String start = getAttributeValue(eChapter, PodloveSimpleChapterAttribute.START);
        if (start != null) {
            chapter.setStart(start);
        }

        final String title = getAttributeValue(eChapter, PodloveSimpleChapterAttribute.TITLE);
        if (title != null) {
            chapter.setTitle(title);
        }

        final String href = getAttributeValue(eChapter, PodloveSimpleChapterAttribute.HREF);
        if (href != null) {
            chapter.setHref(href);
        }

        final String image = getAttributeValue(eChapter, PodloveSimpleChapterAttribute.IMAGE);
        if (image != null) {
            chapter.setImage(image);
        }

        return chapter;
    }

    protected String getAttributeValue(final Element e, final String attributeName) {
        Attribute attr = e.getAttribute(attributeName);
        if (attr == null) {
            attr = e.getAttribute(attributeName, NS);
        }
        if (attr != null) {
            return attr.getValue();
        } else {
            return null;
        }
    }

}
