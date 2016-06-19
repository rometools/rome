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
 */
package com.rometools.modules.content;

import java.util.List;

import com.rometools.rome.feed.module.Module;

public interface ContentModule extends Module {

    public static final String URI = "http://purl.org/rss/1.0/modules/content/";
    public static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    /**
     * Returns a List of Strings containing the New Syntax Encoded values are in the element.
     *
     * @return List of content Strings
     */
    public List<String> getEncodeds();

    /**
     * Sets a List of Strings containing the New Syntax Encoded values are in the element.
     *
     * @return List of content Strings
     */
    public void setEncodeds(List<String> encodeds);

    @Override
    public String getUri();

    public String toString(String str);

    /**
     * Contains a list of ContentItems that represent the "Original Syntax" set.
     *
     * @see com.totsp.xml.syndication.content.ContentItem
     * @return List of ContentItems.
     */
    public List<ContentItem> getContentItems();

    /**
     * Contains a list of ContentItems that represent the "Original Syntax" set.
     *
     * @see com.totsp.xml.syndication.content.ContentItem
     * @param List of ContentItems.
     */
    public void setContentItems(List<ContentItem> list);

    /**
     * Returns a List of Strings containing whatever new or original syntax items are in the
     * element.
     *
     * @return List of content Strings
     */
    public List<String> getContents();

    /**
     * Sets a List of Strings containing whatever new or original syntax items are in the element.
     *
     * @return List of content Strings
     */
    public void setContents(List<String> contents);

}
