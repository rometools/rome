/*
 * Copyright 2004 Sun Microsystems, Inc.
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
 */
package com.rometools.rome.feed.synd;

import java.util.Date;
import java.util.List;

import org.jdom2.Element;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.module.Extendable;
import com.rometools.rome.feed.module.Module;

/**
 * Bean interface for entries of SyndFeedImpl feeds.
 */
public interface SyndEntry extends Cloneable, CopyFrom, Extendable {

    /**
     * Returns the entry URI.
     * <p>
     * How the entry URI maps to a concrete feed type (RSS or Atom) depends on the concrete feed
     * type. This is explained in detail in Rome documentation, <a
     * href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI
     * mapping</a>.
     * <p>
     * The returned URI is a normalized URI as specified in RFC 2396bis.
     * <p>
     *
     * @return the entry URI, <b>null</b> if none.
     *
     */
    String getUri();

    /**
     * Sets the entry URI.
     * <p>
     * How the entry URI maps to a concrete feed type (RSS or Atom) depends on the concrete feed
     * type. This is explained in detail in Rome documentation, <a
     * href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI
     * mapping</a>.
     * <p>
     *
     * @param uri the entry URI to set, <b>null</b> if none.
     *
     */
    void setUri(String uri);

    /**
     * Returns the entry title.
     * <p>
     *
     * @return the entry title, <b>null</b> if none.
     *
     */
    String getTitle();

    /**
     * Sets the entry title.
     * <p>
     *
     * @param title the entry title to set, <b>null</b> if none.
     *
     */
    void setTitle(String title);

    /**
     * Returns the entry title as a text construct.
     * <p>
     *
     * @return the entry title, <b>null</b> if none.
     *
     */
    SyndContent getTitleEx();

    /**
     * Sets the entry title as a text construct.
     * <p>
     *
     * @param title the entry title to set, <b>null</b> if none.
     *
     */
    void setTitleEx(SyndContent title);

    /**
     * Returns the entry link.
     * <p>
     *
     * @return the entry link, <b>null</b> if none.
     *
     */
    String getLink();

    /**
     * Sets the entry link.
     * <p>
     *
     * @param link the entry link to set, <b>null</b> if none.
     *
     */
    void setLink(String link);

    /**
     * Returns the entry links
     * <p>
     *
     * @return the entry links, <b>null</b> if none.
     *
     */
    List<SyndLink> getLinks();

    /**
     * Sets the entry links.
     * <p>
     *
     * @param links the entry links to set, <b>null</b> if none.
     *
     */
    void setLinks(List<SyndLink> links);

    /**
     * Returns the entry description.
     * <p>
     *
     * @return the entry description, <b>null</b> if none.
     *
     */
    SyndContent getDescription();

    /**
     * Sets the entry description.
     * <p>
     *
     * @param description the entry description to set, <b>null</b> if none.
     *
     */
    void setDescription(SyndContent description);

    /**
     * Returns the entry contents.
     * <p>
     *
     * @return a list of SyndContentImpl elements with the entry contents, an empty list if none.
     *
     */
    List<SyndContent> getContents();

    /**
     * Sets the entry contents.
     * <p>
     *
     * @param contents the list of SyndContentImpl elements with the entry contents to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    void setContents(List<SyndContent> contents);

    /**
     * Returns the entry enclosures.
     * <p>
     *
     * @return a list of SyndEnclosure elements with the entry enclosures, an empty list if none.
     *
     */
    public List<SyndEnclosure> getEnclosures();

    /**
     * Sets the entry enclosures.
     * <p>
     *
     * @param enclosures the list of SyndEnclosure elements with the entry enclosures to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    public void setEnclosures(List<SyndEnclosure> enclosures);

    /**
     * Returns the entry published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     *
     * @return the entry published date, <b>null</b> if none.
     *
     */
    Date getPublishedDate();

    /**
     * Sets the entry published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     *
     * @param publishedDate the entry published date to set, <b>null</b> if none.
     *
     */
    void setPublishedDate(Date publishedDate);

    /**
     * Returns the entry updated date.
     * <p>
     *
     * @return the entry updated date, <b>null</b> if none.
     *
     */
    Date getUpdatedDate();

    /**
     * Sets the entry updated date.
     * <p>
     *
     * @param updatedDate the entry updated date to set, <b>null</b> if none.
     *
     */
    void setUpdatedDate(Date updatedDate);

    /**
     * Returns the entry authors.
     * <p>
     * For Atom feeds, this returns the authors as a list of SyndPerson objects, for RSS feeds this
     * method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     *
     * @return the feed author, <b>null</b> if none.
     *
     */
    List<SyndPerson> getAuthors();

    /**
     * Sets the entry author.
     * <p>
     * For Atom feeds, this sets the authors as a list of SyndPerson objects, for RSS feeds this
     * method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     *
     * @param authors the feed author to set, <b>null</b> if none.
     *
     */
    void setAuthors(List<SyndPerson> authors);

    /**
     * Returns the name of the first entry author in the collection of authors.
     * <p>
     * For Atom feeds, this returns the authors as a list of SyndPerson objects, for RSS feeds this
     * method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     *
     * @return the feed author, <b>null</b> if none.
     *
     */
    String getAuthor();

    /**
     * Sets the entry author.
     * <p>
     * For Atom feeds, this sets the feed author's name, for RSS feeds this method is a convenience
     * method, it maps to the Dublin Core module creator.
     * <p>
     *
     * @param author the feed author to set, <b>null</b> if none.
     */
    void setAuthor(String author);

    /**
     * Returns the feed author.
     * <p>
     * For Atom feeds, this returns the contributors as a list of SyndPerson objects
     * <p>
     *
     * @return the feed author, <b>null</b> if none.
     *
     */
    List<SyndPerson> getContributors();

    /**
     * Sets the feed contributors.
     * <p>
     * Returns contributors as a list of SyndPerson objects.
     * <p>
     *
     * @param contributors the feed contributors to set, <b>null</b> if none.
     *
     */
    void setContributors(List<SyndPerson> contributors);

    /**
     * Returns the entry categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     *
     * @return a list of SyndCategoryImpl elements with the entry categories, an empty list if none.
     *
     */
    List<SyndCategory> getCategories();

    /**
     * Sets the entry categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     *
     * @param categories the list of SyndCategoryImpl elements with the entry categories to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    void setCategories(List<SyndCategory> categories);

    /**
     * Returns the entry source.
     * <p>
     * This returns the entry source as a SyndFeed
     * <p>
     *
     * @return the SyndFeed to which this entry is attributed
     *
     */
    SyndFeed getSource();

    /**
     * Sets the entry source feed (for use if different from containing feed)
     * <p>
     *
     * @param source the original SyndFeed that contained this article
     *
     */
    void setSource(SyndFeed source);

    /**
     * Return the original item this SyndEntry is generated from. The type of the object returned
     * depends on the original type of the feed. Atom 0.3/1.0 will return
     * com.rometools.rome.feed.atom.Entry, while RSS will return
     * com.rometools.rome.feed.rss.Item.java. If this entry was not generated from a WireFeed, or
     * the SyndFeed was not set to preserve the WireFeed then it will return null
     *
     * @return the WireFeed Item or Entry this Entry is generated from, or null
     */
    Object getWireEntry();

    /**
     * Returns the module identified by a given URI.
     * <p>
     *
     * @param uri the URI of the ModuleImpl.
     * @return The module with the given URI, <b>null</b> if none.
     */
    @Override
    public Module getModule(String uri);

    /**
     * Returns the entry modules.
     * <p>
     *
     * @return a list of ModuleImpl elements with the entry modules, an empty list if none.
     *
     */
    @Override
    List<Module> getModules();

    /**
     * Sets the entry modules.
     * <p>
     *
     * @param modules the list of ModuleImpl elements with the entry modules to set, an empty list
     *            or <b>null</b> if none.
     *
     */
    @Override
    void setModules(List<Module> modules);

    /**
     * Returns foreign markup found at channel level.
     * <p>
     *
     * @return Opaque object to discourage use
     *
     */
    public List<Element> getForeignMarkup();

    /**
     * Sets foreign markup found at channel level.
     * <p>
     *
     * @param foreignMarkup Opaque object to discourage use
     *
     */
    public void setForeignMarkup(List<Element> foreignMarkup);

    /**
     * URL of a page for comments relating to the item.
     *
     * @since 2.0.0
     * @return comments URL or {@code null}.
     *
     */
    String getComments();

    /**
     * URL of a page for comments relating to the item.
     *
     * @since 2.0.0
     * @param comments URL
     */
    void setComments(String comments);

    /**
     * Creates a deep clone of the object.
     * <p>
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException;

    /**
     * Returns the first instance of a SyndLink with the specified relation, or null
     *
     */
    public SyndLink findRelatedLink(String relation);

}
