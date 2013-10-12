/*
 * Parser.java
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

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.rometools.feed.module.photocast.PhotocastModule;
import org.rometools.feed.module.photocast.PhotocastModuleImpl;
import org.rometools.feed.module.photocast.types.Metadata;
import org.rometools.feed.module.photocast.types.PhotoDate;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleParser;

/**
 * 
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Parser implements ModuleParser {
    private static final Logger LOG = Logger.getAnonymousLogger();

    private static final Namespace NS = Namespace.getNamespace(PhotocastModule.URI);
    // 2005-11-29T04:36:06
    static final DateFormat PHOTO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    // 2006-01-11 16:42:26 -0800
    static final DateFormat CROP_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    /** Creates a new instance of Parser */
    public Parser() {
    }

    @Override
    public Module parse(final Element element) {
        if (element.getName().equals("channel") || element.getName().equals("feed")) {
            return new PhotocastModuleImpl();
        } else if (element.getChild("metadata", Parser.NS) == null && element.getChild("image", Parser.NS) == null) {
            return null;
        }
        final PhotocastModule pm = new PhotocastModuleImpl();
        final List children = element.getChildren();
        final Iterator it = children.iterator();
        while (it.hasNext()) {
            final Element e = (Element) it.next();
            if (!e.getNamespace().equals(Parser.NS)) {
                continue;
            }
            if (e.getName().equals("photoDate")) {
                try {
                    pm.setPhotoDate(Parser.PHOTO_DATE_FORMAT.parse(e.getText()));
                } catch (final Exception ex) {
                    LOG.warning("Unable to parse photoDate: " + e.getText() + " " + ex.toString());
                }
            } else if (e.getName().equals("cropDate")) {
                try {
                    pm.setCropDate(Parser.CROP_DATE_FORMAT.parse(e.getText()));
                } catch (final Exception ex) {
                    LOG.warning("Unable to parse cropDate: " + e.getText() + " " + ex.toString());
                }
            } else if (e.getName().equals("thumbnail")) {
                try {
                    pm.setThumbnailUrl(new URL(e.getText()));
                } catch (final Exception ex) {
                    LOG.warning("Unable to parse thumnail: " + e.getText() + " " + ex.toString());
                }
            } else if (e.getName().equals("image")) {
                try {
                    pm.setImageUrl(new URL(e.getText()));
                } catch (final Exception ex) {
                    LOG.warning("Unable to parse image: " + e.getText() + " " + ex.toString());
                }
            } else if (e.getName().equals("metadata")) {
                String comments = "";
                PhotoDate photoDate = null;
                if (e.getChildText("PhotoDate") != null) {
                    try {
                        photoDate = new PhotoDate(Double.parseDouble(e.getChildText("PhotoDate")));
                    } catch (final Exception ex) {
                        LOG.warning("Unable to parse PhotoDate: " + e.getText() + " " + ex.toString());
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
