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
package com.sun.syndication.propono.blogclient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * Base implementation of a blog entry.
 */
public abstract class BaseBlogEntry implements BlogEntry {
    protected String  id = null;
    protected Person  author = null;
    protected Content content = null;
    protected String  title = null;
    protected String  permalink = null;
    protected String  summary = null;
    protected Date    modificationDate = null;
    protected Date    publicationDate = null;
    protected List    categories = new ArrayList();
    protected boolean draft = false;
    private Blog      blog = null;
    
    /**
     * Contruct abstract blog entry.
     */
    public BaseBlogEntry(Blog blog) {
        this.blog = blog;
    }   

    
    /**
     * {@inheritDoc}
     */
    public String getId() {
        return id;
    }    
    
    /**
     * {@inheritDoc}
     */
    public String getPermalink() {
        return permalink;
    }      
    
    void setPermalink(String permalink) {
        this.permalink = permalink;
    }      
    
    /**
     * {@inheritDoc}
     */
    public Person getAuthor() {
        return author;
    }
    
    /**
     * {@inheritDoc}
     */
    public void setAuthor(Person author) {
        this.author = author;
    }
    
    /**
     * {@inheritDoc}
     */
    public Content getContent() {
        return content;
    }
    
    /**
     * {@inheritDoc}
     */
    public void setContent(Content content) {
        this.content = content;
    }    
    
    /**
     * {@inheritDoc}
     */
    public boolean getDraft() {
        return draft;
    }
    
    /**
     * {@inheritDoc}
     */
    public void setDraft(boolean draft) {
        this.draft = draft;
    }    
    
    /**
     * {@inheritDoc}
     */
    public Date getPublicationDate() {
        return publicationDate;
    }    
    
    /**
     * {@inheritDoc}
     */
    public void setPublicationDate(Date pubDate) {
        this.publicationDate = pubDate;
    }
    
    /**
     * {@inheritDoc}
     */
    public Date getModificationDate() {
        return modificationDate;
    }
    
    /**
     * {@inheritDoc}
     */
    public void setModificationDate(Date date) {
        modificationDate = date;
    }        
    
    /**
     * {@inheritDoc}
     */
    public String getTitle() {
        return title;
    }    
    
    /**
     * {@inheritDoc}
     */
    public void setTitle(String title) {
        this.title = title;
    }   
    
    /**
     * {@inheritDoc}
     */
    public String getSummary() {
        return summary;
    }    
    
    /**
     * {@inheritDoc}
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }    
    
    /**
     * {@inheritDoc}
     */
    public List getCategories() {
        return categories;
    }   
    
    /**
     * {@inheritDoc}
     */
    public void setCategories(List categories) {
        this.categories = categories;
    }
    
    /**
     * {@inheritDoc}
     */
    public Blog getBlog() {
        return blog;
    }
    
    void setBlog(Blog blog) {
        this.blog = blog;
    }
    
    /**
     * String representation, returns id. 
     */
    public String toString() {
        return id;
    }
}
