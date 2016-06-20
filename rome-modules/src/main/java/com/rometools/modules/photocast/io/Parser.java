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

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.photocast.PhotocastModule;
import com.rometools.modules.photocast.PhotocastModuleImpl;
import com.rometools.modules.photocast.types.Metadata;
import com.rometools.modules.photocast.types.PhotoDate;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;

public class Parser implements ModuleParser {

    private static final Logger LOG = LoggerFactory.getLogger(Parser.class);

    private static final Namespace NS = Namespace.getNamespace(PhotocastModule.URI);
    // 2005-11-29T04:36:06
    static final DateFormat PHOTO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    // 2006-01-11 16:42:26 -0800
    static final DateFormat CROP_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    public Parser() {
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        if (element.getName().equals("channel") || element.getName().equals("feed")) {
            return new PhotocastModuleImpl();
        } else if (element.getChild("metadata", Parser.NS) == null && element.getChild("image", Parser.NS) == null) {
            return null;
        }
        final PhotocastModule pm = new PhotocastModuleImpl();
        final List<Element> children = element.getChildren();
        final Iterator<Element> it = children.iterator();
        while (it.hasNext()) {
            final Element e = it.next();
            if (!e.getNamespace().equals(Parser.NS)) {
                continue;
            }
            if (e.getName().equals("photoDate")) {
                try {
                    pm.setPhotoDate(Parser.PHOTO_DATE_FORMAT.parse(e.getText()));
                } catch (final Exception ex) {
                    LOG.warn("Unable to parse photoDate: " + e.getText(), ex);
                }
            } else if (e.getName().equals("cropDate")) {
                try {
                    pm.setCropDate(Parser.CROP_DATE_FORMAT.parse(e.getText()));
                } catch (final Exception ex) {
                    LOG.warn("Unable to parse cropDate: " + e.getText(), ex);
                }
            } else if (e.getName().equals("thumbnail")) {
                try {
                    pm.setThumbnailUrl(new URL(e.getText()));
                } catch (final Exception ex) {
                    LOG.warn("Unable to parse thumnail: " + e.getText(), ex);
                }
            } else if (e.getName().equals("image")) {
                try {
                    pm.setImageUrl(new URL(e.getText()));
                } catch (final Exception ex) {
                    LOG.warn("Unable to parse image: " + e.getText(), ex);
                }
            } else if (e.getName().equals("metadata")) {
                String comments = "";
                PhotoDate photoDate = null;
                if (e.getChildText("PhotoDate") != null) {
                    try {
                        photoDate = new PhotoDate(Double.parseDouble(e.getChildText("PhotoDate")));
                    } catch (final Exception ex) {
                        LOG.warn("Unable to parse PhotoDate: " + e.getText(), ex);
                    }
                }
                if (e.getChildText("Comments") != null) {
                    comments = e.getChildText("Comments");
                }
                pm.setMetadata(new Metadata(photoDate, comments));
            }
        }
        return pm;
    }

    @Override
    public String getNamespaceUri() {
        return PhotocastModule.URI;
    }

}
