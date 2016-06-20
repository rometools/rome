/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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
 *
 */
package com.rometools.modules.content;

import java.util.ArrayList;
import java.util.List;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.module.ModuleImpl;

public class ContentModuleImpl extends ModuleImpl implements ContentModule {

    private static final long serialVersionUID = 1L;

    private List<String> encodeds;
    private List<String> contents;
    private List<ContentItem> contentItems;

    public ContentModuleImpl() {
        super(ContentModuleImpl.class, URI);
    }

    protected ContentModuleImpl(final Class<ContentModuleImpl> beanClass, final java.lang.String uri) {
        super(beanClass, uri);
    }

    @Override
    public List<String> getEncodeds() {
        encodeds = encodeds == null ? new ArrayList<String>() : encodeds;
        return encodeds;
    }

    @Override
    public void setEncodeds(final List<String> encodeds) {
        this.encodeds = encodeds;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        final ContentModule cm = (ContentModule) obj;
        setEncodeds(cm.getEncodeds());
        setContentItems(cm.getContentItems());
        setContents(cm.getContents());
    }

    @Override
    public Class<ContentModule> getInterface() {
        return ContentModule.class;
    }

    @Override
    public List<ContentItem> getContentItems() {
        contentItems = contentItems == null ? new ArrayList<ContentItem>() : contentItems;
        return contentItems;
    }

    @Override
    public void setContentItems(final List<ContentItem> list) {
        contentItems = list;
    }

    @Override
    public List<String> getContents() {
        contents = contents == null ? new ArrayList<String>() : contents;
        return contents;
    }

    @Override
    public void setContents(final List<String> contents) {
        this.contents = contents;
    }

    @Override
    public String toString(final String str) {
        return contentItems.toString();
    }
}
