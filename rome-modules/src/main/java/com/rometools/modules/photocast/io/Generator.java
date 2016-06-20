/*
 * Copyright 2006 Robert Cooper, Temple of the Screaming Penguin
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

package com.rometools.modules.photocast.io;

import java.util.HashSet;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.photocast.PhotocastModule;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

public class Generator implements ModuleGenerator {

    private static final Namespace NS = Namespace.getNamespace("apple-wallpapers", PhotocastModule.URI);
    private static final HashSet<Namespace> NAMESPACES = new HashSet<Namespace>();
    private static final String FEED_VERSION = "0.9";
    static {
        NAMESPACES.add(NS);
    }

    public Generator() {
        super();
    }

    @Override
    public void generate(final Module module, final Element element) {
        if (!(module instanceof PhotocastModule)) {
            return;
        }
        final PhotocastModule pm = (PhotocastModule) module;
        if (element.getName().equals("channel") || element.getName().equals("feed")) {
            element.addContent(generateSimpleElement("feedVersion", FEED_VERSION));
            return;
        }
        element.addContent(generateSimpleElement("photoDate", Parser.PHOTO_DATE_FORMAT.format(pm.getPhotoDate())));
        element.addContent(generateSimpleElement("cropDate", Parser.CROP_DATE_FORMAT.format(pm.getCropDate())));
        element.addContent(generateSimpleElement("thumbnail", pm.getThumbnailUrl().toString()));
        element.addContent(generateSimpleElement("image", pm.getImageUrl().toString()));
        final Element e = new Element("metadata", NS);
        final Element pd = new Element("PhotoDate", "");
        pd.addContent(pm.getMetadata().getPhotoDate().toString());
        e.addContent(pd);
        final Element com = new Element("Comments", "");
        com.addContent(pm.getMetadata().getComments());
        e.addContent(com);
        element.addContent(e);
    }

    @Override
    public Set<Namespace> getNamespaces() {
        return Generator.NAMESPACES;
    }

    @Override
    public String getNamespaceUri() {
        return PhotocastModule.URI;
    }

    protected Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, NS);
        element.addContent(value);

        return element;
    }

}
