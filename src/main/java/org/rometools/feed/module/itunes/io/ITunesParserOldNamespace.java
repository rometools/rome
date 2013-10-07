/*
 * ITunesParserOldNamespace.java
 *
 * Created on April 23, 2006, 2:09 AM
 *
 * Copyright Robert Cooper, Temple of the Screaming Penguin
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
 */

package org.rometools.feed.module.itunes.io;

import org.jdom2.Namespace;

/**
 * 
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ITunesParserOldNamespace extends ITunesParser {
    String URI = "http://www.itunes.com/DTDs/Podcast-1.0.dtd";

    /** Creates a new instance of ITunesParserOldNamespace */
    public ITunesParserOldNamespace() {
        super();
        super.ns = Namespace.getNamespace(URI);
    }

    @Override
    public String getNamespaceUri() {
        return URI;
    }
}
