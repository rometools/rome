/*
 * Generator.java
 *
 * Created on March 30, 2006, 6:43 PM
 *
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2006  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.rometools.feed.module.photocast.io;

import java.util.HashSet;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.rometools.feed.module.photocast.PhotocastModule;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleGenerator;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Generator implements ModuleGenerator {

    private static final Namespace NS = Namespace.getNamespace("apple-wallpapers", PhotocastModule.URI);
    private static final HashSet NAMESPACES = new HashSet();
    private static final String FEED_VERSION = "0.9";
    static {
        NAMESPACES.add(NS);
    }

    /** Creates a new instance of Generator */
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
    public Set getNamespaces() {
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
