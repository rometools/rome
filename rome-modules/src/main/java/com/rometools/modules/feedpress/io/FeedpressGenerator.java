/*
 * Copyright 2019 Maximilian Irro
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
package com.rometools.modules.feedpress.io;

import com.rometools.modules.feedpress.modules.FeedpressModule;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The ModuleGenerator implementation for the Feedpress module.
 */
public class FeedpressGenerator implements ModuleGenerator {

    private static final Namespace NS = Namespace.getNamespace(FeedpressElement.PREFIX, FeedpressModule.URI);
    private static final Set<Namespace> NAMESPACES;

    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }


    @Override
    public String getNamespaceUri() {
        return FeedpressModule.URI;
    }

    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(Module module, Element element) {
        if (module instanceof FeedpressModule) {
            final FeedpressModule feedpress = (FeedpressModule) module;
            generateNewsletterId(feedpress.getNewsletterId(), element);
            generateLocale(feedpress.getLocale(), element);
            generatePodcastId(feedpress.getPodcastId(), element);
            generateCssFile(feedpress.getCssFile(), element);
        }
    }

    private void generateNewsletterId(String newsletterId, Element parent) {
        final Element child = new Element(FeedpressElement.NEWSLETTER_ID, NS);
        child.setText(newsletterId);
        parent.addContent(child);
    }

    private void generateLocale(String locale, Element parent) {
        final Element child = new Element(FeedpressElement.LOCALE, NS);
        child.setText(locale);
        parent.addContent(child);
    }

    private void generatePodcastId(String podcastId, Element parent) {
        final Element child = new Element(FeedpressElement.PODCAST_ID, NS);
        child.setText(podcastId);
        parent.addContent(child);
    }

    private void generateCssFile(String cssFile, Element parent) {
        final Element child = new Element(FeedpressElement.CSS_FILE, NS);
        child.setText(cssFile);
        parent.addContent(child);
    }

}
