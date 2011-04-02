/*
 * ContentModuleImpl.java
 *
 * Created on January 11, 2005, 1:07 PM
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
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

import com.sun.syndication.feed.CopyFrom;
import java.util.ArrayList;
import java.util.List;


/**
 * @version $Revision: 1.4 $
 * @author  <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ContentModuleImpl extends com.sun.syndication.feed.module.ModuleImpl implements ContentModule {
    private List encodeds;
    private List contents;
    private List contentItems;

    public ContentModuleImpl() {
        super(ContentModuleImpl.class, URI);
    }

    protected ContentModuleImpl(java.lang.Class beanClass, java.lang.String uri) {
        super(beanClass, uri);
    }

    public List getEncodeds() {
        this.encodeds = this.encodeds == null ? new ArrayList() : this.encodeds;
        return this.encodeds;
    }

    public void setEncodeds(List encodeds) {
        this.encodeds = encodeds;
    }

    public void copyFrom(CopyFrom obj) {
        ContentModule cm = (ContentModule) obj;
        this.setEncodeds(cm.getEncodeds());
        this.setContentItems(cm.getContentItems());
        this.setContents(cm.getContents());
    }

    public Class getInterface() {
        return ContentModule.class;
    }

    public List getContentItems() {
        this.contentItems = this.contentItems == null ? new ArrayList() : this.contentItems;
        return this.contentItems;
    }

    public void setContentItems(List list) {
        this.contentItems = list;
    }

    public List getContents() {
        this.contents = this.contents == null ? new ArrayList() : this.contents;
        return this.contents;
    }

    public void setContents(List contents) {
        this.contents = contents;
    }

    public String toString(String str) {
        return contentItems.toString();
    }
}
