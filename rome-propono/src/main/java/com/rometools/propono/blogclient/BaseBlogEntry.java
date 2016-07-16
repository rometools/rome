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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Base implementation of a blog entry.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public abstract class BaseBlogEntry implements BlogEntry {
    protected String id = null;
    protected Person author = null;
    protected Content content = null;
    protected String title = null;
    protected String permalink = null;
    protected String summary = null;
    protected Date modificationDate = null;
    protected Date publicationDate = null;
    protected List<Category> categories = new ArrayList<Category>();
    protected boolean draft = false;
    private Blog blog = null;

    /**
     * Contruct abstract blog entry.
     */
    public BaseBlogEntry(final Blog blog) {
        this.blog = blog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPermalink() {
        return permalink;
    }

    void setPermalink(final String permalink) {
        this.permalink = permalink;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person getAuthor() {
        return author;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAuthor(final Person author) {
        this.author = author;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Content getContent() {
        return content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContent(final Content content) {
        this.content = content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getDraft() {
        return draft;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDraft(final boolean draft) {
        this.draft = draft;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPublicationDate(final Date pubDate) {
        publicationDate = pubDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getModificationDate() {
        return modificationDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModificationDate(final Date date) {
        modificationDate = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSummary() {
        return summary;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSummary(final String summary) {
        this.summary = summary;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCategories(final List<Category> categories) {
        this.categories = categories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blog getBlog() {
        return blog;
    }

    void setBlog(final Blog blog) {
        this.blog = blog;
    }

    /**
     * String representation, returns id.
     */
    @Override
    public String toString() {
        return id;
    }
}
