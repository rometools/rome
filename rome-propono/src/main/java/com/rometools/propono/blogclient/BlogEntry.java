/*
 *  Copyright 2007 Dave Johnson (Blogapps project)
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
package com.rometools.propono.blogclient;

import java.util.Date;
import java.util.List;

/**
 * Represents a single blog entry.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public interface BlogEntry {

    /** Get token, which can be used to fetch the blog entry */
    public String getToken();

    /**
     * Save this entry to it's collection. If this is a new entry and does not have a collection
     * yet, then save() will save it to the primary collection.
     */
    public void save() throws BlogClientException;

    /** Delete this entry from blog server */
    public void delete() throws BlogClientException;

    /** Permanent link to this entry (assigned by server) */
    public String getPermalink();

    /** Blog is associated with a blog */
    public Blog getBlog();

    /** Get categories, a list of BlogEntry.Category objects */
    public List<Category> getCategories();

    /** Set categories, a list of BlogEntry.Category objects */
    public void setCategories(List<Category> categories);

    /** Get globally unique ID of this blog entry */
    public String getId();

    /** Get title of this blog entry */
    public String getTitle();

    /** Set title of this blog entry */
    public void setTitle(String title);

    /** Get summary of this blog entry */
    public String getSummary();

    /** Set summary of this blog entry */
    public void setSummary(String summary);

    /** Get content of this blog entry */
    public Content getContent();

    /** Set content of this blog entry */
    public void setContent(Content content);

    /** Get draft status of this entry */
    public boolean getDraft();

    /** Set draft status of this entry */
    public void setDraft(boolean draft);

    /** Get author of this entry */
    public Person getAuthor();

    /** Set author of this entry */
    public void setAuthor(Person author);

    /** Set publish date of this entry */
    public Date getPublicationDate();

    /** Get publish date of this entry */
    public void setPublicationDate(Date date);

    /** Get update date of this entry */
    public Date getModificationDate();

    /** Set update date of this entry */
    public void setModificationDate(Date date);

    /** Represents blog entry content */
    public class Content {
        String type = "html";
        String value = null;
        String src = null;

        /** Construct content */
        public Content() {
        }

        /** Construct content with value (and type="html") */
        public Content(final String value) {
            this.value = value;
        }

        /** Get value of content if in-line */
        public String getValue() {
            return value;
        }

        /** Set value of content if in-line */
        public void setValue(final String value) {
            this.value = value;
        }

        /**
         * Get type of content, either "text", "html", "xhtml" or a MIME content-type. Defaults to
         * HTML.
         */
        public String getType() {
            return type;
        }

        /**
         * Set type of content, either "text", "html", "xhtml" or a MIME content-type. Defaults to
         * HTML.
         */
        public void setType(final String type) {
            this.type = type;
        }

        /** Get URI of content if out-of-line */
        public String getSrc() {
            return src;
        }

        /** Set URI of content if out-of-line */
        public void setSrc(final String src) {
            this.src = src;
        }
    }

    /** Represents a blog author or contributor */
    public class Person {
        String name;
        String email;
        String url;

        /** Get person's email */
        public String getEmail() {
            return email;
        }

        /** Set person's email */
        public void setEmail(final String email) {
            this.email = email;
        }

        /** Get person's name */
        public String getName() {
            return name;
        }

        /** Set person's name */
        public void setName(final String name) {
            this.name = name;
        }

        /** Get person's URL */
        public String getUrl() {
            return url;
        }

        /** Set person's URL */
        public void setUrl(final String url) {
            this.url = url;
        }

        /** Returns person's name */
        @Override
        public String toString() {
            return name;
        }
    }

    /** Represents a weblog category */
    public class Category {
        String id;
        String name;
        String url;

        /**
         * Create new Catetory
         */
        public Category() {
        }

        /**
         * Create new category with name.
         */
        public Category(final String id) {
            this.id = id;
            name = id;
        }

        /**
         * Determines if categories are equal based on id.
         */
        @Override
        public boolean equals(final Object obj) {
            final Category other = (Category) obj;
            if (obj == null) {
                return false;
            }
            if (getId() != null && other.getId() != null && getId().equals(other.getId())) {
                return true;
            }
            return false;
        }

        /** Get category id */
        public String getId() {
            return id;
        }

        /** Set category id */
        public void setId(final String id) {
            this.id = id;
        }

        /** Get category display name */
        public String getName() {
            return name;
        }

        /** Set category display name */
        public void setName(final String name) {
            this.name = name;
        }

        /** Get URL of category domain */
        public String getUrl() {
            return url;
        }

        /** Set URL of category domain */
        public void setUrl(final String url) {
            this.url = url;
        }

        /** Return category's name or id for display */
        @Override
        public String toString() {
            return name != null ? name : id;
        }
    }
}
