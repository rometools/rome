/*
 * AbstractITunesObject.java
 *
 * Created on August 1, 2005, 7:37 PM
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
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
 *
 */
package org.rometools.feed.module.itunes;

import com.sun.syndication.feed.CopyFrom;

/**
 * This is an abstract object that implements the attributes common across Feeds or Items in an iTunes compatible RSS feed.
 * 
 * @version $Revision: 1.4 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public abstract class AbstractITunesObject implements ITunes, java.lang.Cloneable {
    /**
     * The URI that iTunes used for its custom tags.
     * <p>
     * What is up with using a versioned DTD anyway?
     * </p>
     * \
     */
    public static final String URI = "http://www.itunes.com/dtds/podcast-1.0.dtd";

    /**
     * The RDF namespace URI.
     */
    public static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    /**
     * A default prefix to use for itunes tags.
     */
    public static final String PREFIX = "itunes";
    private String author;
    private boolean block;
    private boolean explicit;
    private String[] keywords;
    private String subtitle;
    private String summary;

    /**
     * Defined by the ROME module API
     * 
     * @param obj Object to copy from
     */
    @Override
    public abstract void copyFrom(CopyFrom obj);

    /**
     * Defined by the ROME API
     * 
     * @return Class of the Interface for this module.
     */
    @Override
    public Class getInterface() {
        return getClass();
    }

    /**
     * The URI this module implements
     * 
     * @return "http://www.itunes.com/dtds/podcast-1.0.dtd"
     */
    @Override
    public String getUri() {
        return AbstractITunesObject.URI;
    }

    /**
     * Required by the ROME API
     * 
     * @return A clone of this module object
     */
    @Override
    public abstract Object clone();

    /**
     * Returns the author string for this feed or entry
     * 
     * @return Returns the author string for this feed or entry
     */
    @Override
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author string for this feed or entry
     * 
     * @param author Sets the author string for this feed or entry
     */
    @Override
    public void setAuthor(final String author) {
        this.author = author;
    }

    /**
     * Boolean as to whether to block this feed or entry
     * 
     * @return Boolean as to whether to block this feed or entry
     */
    @Override
    public boolean getBlock() {
        return block;
    }

    /**
     * Boolean as to whether to block this feed or entry
     * 
     * @param block Boolean as to whether to block this feed or entry
     */
    @Override
    public void setBlock(final boolean block) {
        this.block = block;
    }

    /**
     * Boolean as to whether this feed or entry contains adult content
     * 
     * @return Boolean as to whether this feed or entry contains adult content
     */
    @Override
    public boolean getExplicit() {
        return explicit;
    }

    /**
     * Boolean as to whether this feed or entry contains adult content
     * 
     * @param explicit Boolean as to whether this feed or entry contains adult content
     */
    @Override
    public void setExplicit(final boolean explicit) {
        this.explicit = explicit;
    }

    /**
     * A list of keywords for this feed or entry
     * 
     * Must not contain spaces
     * 
     * @return A list of keywords for this feed or entry
     */
    @Override
    public String[] getKeywords() {
        return keywords == null ? new String[0] : keywords;
    }

    /**
     * A list of keywords for this feed or entry
     * 
     * Must not contain spaces
     * 
     * @param keywords A list of keywords for this feed or enty
     */
    @Override
    public void setKeywords(final String[] keywords) {
        this.keywords = keywords;
    }

    /**
     * A subtitle for this feed or entry
     * 
     * @return A subtitle for this feed or entry
     */
    @Override
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * A subtitle for this feed or entry
     * 
     * @param subtitle A subtitle for this feed or entry
     */
    @Override
    public void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * A subtitle for this feed or entry
     * 
     * @return A subtitle for this feed or entry
     */
    @Override
    public String getSummary() {
        return summary;
    }

    /**
     * A subtitle for this feed or entry
     * 
     * @param summary A subtitle for this feed or entry
     */
    @Override
    public void setSummary(final String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("[");
        sb.append(" Author: ");
        sb.append(getAuthor());
        sb.append(" Block: ");
        sb.append(getBlock());
        sb.append(" Explicit: ");
        sb.append(getExplicit());
        sb.append(" Keywords: ");

        if (getKeywords() != null) {
            for (int i = 0; i < keywords.length; i++) {
                sb.append("'" + getKeywords()[i] + "'");
            }
        }

        sb.append(" Subtitle: ");
        sb.append(getSubtitle());
        sb.append(" Summary: ");
        sb.append(getSummary());
        sb.append("]");

        return sb.toString();
    }
}
