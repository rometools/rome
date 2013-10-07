/*
 * ContentItem.java
 *
 * Created on January 12, 2005, 8:52 AM
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
 *
 */
package org.rometools.feed.module.content;

import java.util.List;

/**
 * This class represents a content item per the "Original Syntax". http://purl.org/rss/1.0/modules/content/
 * 
 * @version $Revision: 1.1 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ContentItem implements Cloneable {
    private String contentFormat;
    private String contentEncoding;
    private String contentValue;
    private List contentValueDOM;
    private String contentAbout;
    private String contentValueParseType;
    private List contentValueNamespace;
    private String contentResource;

    /** Creates a new instance of ContentItem */
    public ContentItem() {
    }

    public String getContentFormat() {
        return contentFormat;
    }

    public void setContentFormat(final String contentFormat) {
        this.contentFormat = contentFormat;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(final String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(final String contentValue) {
        this.contentValue = contentValue;
    }

    public List getContentValueDOM() {
        return contentValueDOM;
    }

    public void setContentValueDOM(final List contentValueDOM) {
        this.contentValueDOM = contentValueDOM;
    }

    public String getContentAbout() {
        return contentAbout;
    }

    public void setContentAbout(final String contentAbout) {
        this.contentAbout = contentAbout;
    }

    public String getContentValueParseType() {
        return contentValueParseType;
    }

    public void setContentValueParseType(final String contentValueParseType) {
        this.contentValueParseType = contentValueParseType;
    }

    public List getContentValueNamespaces() {
        return contentValueNamespace;
    }

    public void setContentValueNamespaces(final List contentValueNamespace) {
        this.contentValueNamespace = contentValueNamespace;
    }

    public String getContentResource() {
        return contentResource;
    }

    public void setContentResource(final String contentResource) {
        this.contentResource = contentResource;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContentItem other = (ContentItem) obj;
        if (contentFormat == null ? other.contentFormat != null : !contentFormat.equals(other.contentFormat)) {
            // System.out.println("format");
            return false;
        }
        if (contentEncoding == null ? other.contentEncoding != null : !contentEncoding.equals(other.contentEncoding)) {
            // System.out.println("enc");
            return false;
        }
        final String thisCV = contentValue.replaceAll(" xmlns=\"http://www.w3.org/1999/xhtml\"", "").trim();
        ;
        final String thatCV = other.contentValue.replaceAll(" xmlns=\"http://www.w3.org/1999/xhtml\"", "").trim();
        if (contentValue == null ? other.contentValue != null : !thisCV.equals(thatCV)) {

            return false;
        }
        if (contentValueDOM != other.contentValueDOM && (contentValueDOM == null || !contentValueDOM.equals(other.contentValueDOM))) {
            // System.out.println("vd");
            return false;
        }
        if (contentAbout == null ? other.contentAbout != null : !contentAbout.equals(other.contentAbout)) {
            // System.out.println("abt");
            return false;
        }
        if (contentValueParseType == null ? other.contentValueParseType != null : !contentValueParseType.equals(other.contentValueParseType)) {
            // System.out.println("pt");
            return false;
        }
        if (contentValueNamespace != other.contentValueNamespace
                && (contentValueNamespace == null || !contentValueNamespace.equals(other.contentValueNamespace))) {
            // System.out.println("ns");
            return false;
        }
        if (contentResource == null ? other.contentResource != null : !contentResource.equals(other.contentResource)) {
            // System.out.println("res");
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (contentFormat != null ? contentFormat.hashCode() : 0);
        hash = 97 * hash + (contentEncoding != null ? contentEncoding.hashCode() : 0);
        hash = 97 * hash + (contentValue != null ? contentValue.hashCode() : 0);
        hash = 97 * hash + (contentValueDOM != null ? contentValueDOM.hashCode() : 0);
        hash = 97 * hash + (contentAbout != null ? contentAbout.hashCode() : 0);
        hash = 97 * hash + (contentValueParseType != null ? contentValueParseType.hashCode() : 0);
        hash = 97 * hash + (contentValueNamespace != null ? contentValueNamespace.hashCode() : 0);
        hash = 97 * hash + (contentResource != null ? contentResource.hashCode() : 0);
        return hash;
    }

    @Override
    public Object clone() {
        final ContentItem o = new ContentItem();
        o.contentAbout = contentAbout;
        o.contentEncoding = contentEncoding;
        o.contentFormat = contentFormat;
        o.contentResource = contentResource;
        o.contentValue = contentValue;
        o.contentValueDOM = contentValueDOM;
        o.contentValueNamespace = contentValueNamespace;
        o.contentValueParseType = contentValueParseType;

        return o;
    }
}
