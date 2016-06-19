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
import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.module.Extendable;
import com.rometools.rome.feed.module.Module;

/**
 * Bean interface for all types of feeds.
 * <p>
 * It handles all RSS versions and Atom 0.3, it normalizes all info, it may lose information.
 */
public interface SyndFeed extends Cloneable, CopyFrom, Extendable {

    /**
     * Returns the real feed types the SyndFeedImpl supports when converting from and to.
     * <p>
     *
     * @return the real feed type supported.
     */
    List<String> getSupportedFeedTypes();

    /**
     * Creates a real feed containing the information of the SyndFeedImpl.
     * <p>
     * The feed type of the created WireFeed is taken from the SyndFeedImpl feedType property.
     * <p>
     *
     * @return the real feed.
     *
     */
    WireFeed createWireFeed();

    /**
     * Creates a real feed containing the information of the SyndFeedImpl.
     * <p>
     *
     * @param feedType the feed type for the WireFeed to be created.
     * @return the real feed.
     *
     */
    WireFeed createWireFeed(String feedType);

    /**
     * Returns the WireFeed this SyndFeed was created from. Will return null if the original feed is
     * not stored or if this SyndFeed was not created from a WireFeed
     *
     * @return The WireFeed this was created from, or null
     *
     */
    WireFeed originalWireFeed();

    /**
     *
     * @return true if this SyndFeed preserves the WireFeed it was created from
     */
    boolean isPreservingWireFeed();

    /**
     * Returns the wire feed type the feed had/will-have when converted from/to a WireFeed.
     * <p>
     *
     * @return the feed type, <b>null</b> if none.
     *
     */
    String getFeedType();

    /**
     * Sets the wire feed type the feed will-have when coverted to a WireFeed.
     * <p>
     *
     * @param feedType the feed type to set, <b>null</b> if none.
     *
     */
    void setFeedType(String feedType);

    /**
     * Returns the charset encoding of a the feed. This is not set by Rome parsers.
     * <p>
     *
     * @return the charset encoding of the feed.
     *
     */
    public String getEncoding();

    /**
     * Sets the charset encoding of a the feed. This is not set by Rome parsers.
     * <p>
     *
     * @param encoding the charset encoding of the feed.
     *
     */
    public void setEncoding(String encoding);

    /**
     * Returns the feed URI.
     * <p>
     * How the feed URI maps to a concrete feed type (RSS or Atom) depends on the concrete feed
     * type. This is explained in detail in Rome documentation, <a
     * href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI
     * mapping</a>.
     * <p>
     * The returned URI is a normalized URI as specified in RFC 2396bis.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is the GUID, for RSS
     * 1.0 this is the URI attribute of the item. The Link is the URL that the item is accessible
     * under, the URI is the permanent identifier which the aggregator should use to reference this
     * item. Often the URI will use some standardized identifier scheme such as DOI's so that items
     * can be identified even if they appear in multiple feeds with different "links" (they might be
     * on different hosting platforms but be the same item). Also, though rare, there could be
     * multiple items with the same link but a different URI and associated metadata which need to
     * be treated as distinct entities. In the RSS 1.0 case the URI must be a valid RDF URI
     * reference.
     * <p>
     *
     * @return the feed URI, <b>null</b> if none.
     *
     */
    String getUri();

    /**
     * Sets the feed URI.
     * <p>
     * How the feed URI maps to a concrete feed type (RSS or Atom) depends on the concrete feed
     * type. This is explained in detail in Rome documentation, <a
     * href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI
     * mapping</a>.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is the GUID, for RSS
     * 1.0 this is the URI attribute of the item. The Link is the URL that the item is accessible
     * under, the URI is the permanent identifier which the aggregator should use to reference this
     * item. Often the URI will use some standardized identifier scheme such as DOI's so that items
     * can be identified even if they appear in multiple feeds with different "links" (they might be
     * on different hosting platforms but be the same item). Also, though rare, there could be
     * multiple items with the same link but a different URI and associated metadata which need to
     * be treated as distinct entities. In the RSS 1.0 case the URI must be a valid RDF URI
     * reference.
     * <p>
     *
     * @param uri the feed URI to set, <b>null</b> if none.
     *
     */
    void setUri(String uri);

    /**
     * Returns the feed title.
     * <p>
     *
     * @return the feed title, <b>null</b> if none.
     *
     */
    String getTitle();

    /**
     * Sets the feed title.
     * <p>
     *
     * @param title the feed title to set, <b>null</b> if none.
     *
     */
    void setTitle(String title);

    /**
     * Returns the feed title.
     * <p>
     *
     * @return the feed title, <b>null</b> if none.
     *
     */
    SyndContent getTitleEx();

    /**
     * Sets the feed title.
     * <p>
     *
     * @param title the feed title to set, <b>null</b> if none.
     *
     */
    void setTitleEx(SyndContent title);

    /**
     * Returns the feed link.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is the GUID, for RSS
     * 1.0 this is the URI attribute of the item. The Link is the URL that the item is accessible
     * under, the URI is the permanent identifier which the aggregator should use to reference this
     * item. Often the URI will use some standardized identifier scheme such as DOI's so that items
     * can be identified even if they appear in multiple feeds with different "links" (they might be
     * on different hosting platforms but be the same item). Also, though rare, there could be
     * multiple items with the same link but a different URI and associated metadata which need to
     * be treated as distinct entities. In the RSS 1.0 case the URI must be a valid RDF URI
     * reference.
     * <p>
     *
     * @return the feed link, <b>null</b> if none.
     *
     */
    String getLink();

    /**
     * Sets the feed link.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is the GUID, for RSS
     * 1.0 this is the URI attribute of the item. The Link is the URL that the item is accessible
     * under, the URI is the permanent identifier which the aggregator should use to reference this
     * item. Often the URI will use some standardized identifier scheme such as DOI's so that items
     * can be identified even if they appear in multiple feeds with different "links" (they might be
     * on different hosting platforms but be the same item). Also, though rare, there could be
     * multiple items with the same link but a different URI and associated metadata which need to
     * be treated as distinct entities. In the RSS 1.0 case the URI must be a valid RDF URI
     * reference.
     * <p>
     *
     * @param link the feed link to set, <b>null</b> if none.
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
     * Returns the feed description.
     * <p>
     *
     * @return the feed description, <b>null</b> if none.
     *
     */
    String getDescription();

    /**
     * Sets the feed description.
     * <p>
     *
     * @param description the feed description to set, <b>null</b> if none.
     *
     */
    void setDescription(String description);

    /**
     * Returns the feed description as a text construct.
     * <p>
     *
     * @return the feed description, <b>null</b> if none.
     *
     */
    SyndContent getDescriptionEx();

    /**
     * Sets the feed description as a text construct.
     * <p>
     *
     * @param description the feed description to set, <b>null</b> if none.
     *
     */
    void setDescriptionEx(SyndContent description);

    /**
     * Returns the feed published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     *
     * @return the feed published date, <b>null</b> if none.
     *
     */
    Date getPublishedDate();

    /**
     * Sets the feed published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     *
     * @param publishedDate the feed published date to set, <b>null</b> if none.
     *
     */
    void setPublishedDate(Date publishedDate);

    /**
     * Returns the feed authors.
     * <p>
     * For Atom feeds, this returns the authors as a list of SyndPerson objects, for RSS feeds this
     * method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     *
     * @return the feed authors, <b>null</b> if none.
     *
     */
    List<SyndPerson> getAuthors();

    /**
     * Sets the feed authors.
     * <p>
     * For Atom feeds, this sets the authors as a list of SyndPerson objects, for RSS feeds this
     * method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     *
     * @param authors the feed authors to set, <b>null</b> if none.
     *
     */
    void setAuthors(List<SyndPerson> authors);

    /**
     * Returns the name of the first feed author in the collection of authors.
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
     * Sets the feed author.
     * <p>
     * For Atom feeds, this sets the feed author's name, for RSS feeds this method is a convenience
     * method, it maps to the Dublin Core module creator.
     * <p>
     *
     * @param author the feed author to set, <b>null</b> if none.
     *
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
    public List<SyndPerson> getContributors();

    /**
     * Sets the feed author.
     * <p>
     * Returns contributors as a list of SyndPerson objects.
     * <p>
     *
     * @param contributors the feed contributors to set, <b>null</b> if none.
     *
     */
    void setContributors(List<SyndPerson> contributors);

    /**
     * Returns the feed copyright.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module rights.
     * <p>
     *
     * @return the feed copyright, <b>null</b> if none.
     *
     */
    String getCopyright();

    /**
     * Sets the feed copyright.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module rights.
     * <p>
     *
     * @param copyright the feed copyright to set, <b>null</b> if none.
     *
     */
    void setCopyright(String copyright);

    /**
     * Returns the feed image.
     * <p>
     *
     * @return the feed image, <b>null</b> if none.
     *
     */
    SyndImage getImage();

    /**
     * Sets the feed image.
     * <p>
     *
     * @param image the feed image to set, <b>null</b> if none.
     *
     */
    void setImage(SyndImage image);

    /**
     * Returns the feed categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     *
     * @return a list of SyndCategoryImpl elements with the feed categories, an empty list if none.
     *
     */
    List<SyndCategory> getCategories();

    /**
     * Sets the feed categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     *
     * @param categories the list of SyndCategoryImpl elements with the feed categories to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    void setCategories(List<SyndCategory> categories);

    /**
     * Returns the feed entries.
     * <p>
     *
     * @return a list of SyndEntry elements with the feed entries, an empty list if none.
     *
     */
    List<SyndEntry> getEntries();

    /**
     * Sets the feed entries.
     * <p>
     *
     * @param entries the list of SyndEntryImpl elements with the feed entries to set, an empty list
     *            or <b>null</b> if none.
     *
     */
    void setEntries(List<SyndEntry> entries);

    /**
     * Returns the feed language.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module language.
     * <p>
     *
     * @return the feed language, <b>null</b> if none.
     *
     */
    String getLanguage();

    /**
     * Sets the feed language.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module language.
     * <p>
     *
     * @param language the feed language to set, <b>null</b> if none.
     *
     */
    void setLanguage(String language);

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
     * Returns the feed modules.
     * <p>
     *
     * @return a list of ModuleImpl elements with the feed modules, an empty list if none.
     *
     */
    @Override
    List<Module> getModules();

    /**
     * Sets the feed modules.
     * <p>
     *
     * @param modules the list of ModuleImpl elements with the feed modules to set, an empty list or
     *            <b>null</b> if none.
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
     * This tag should contain a URL that references a description of the channel.
     *
     * @since 2.0.0
     * @return channel description URL or {@code null}
     */
    String getDocs();

    /**
     * This tag should contain a URL that references a description of the channel.
     *
     * @since 2.0.0
     * @param channel description URL
     */
    public void setDocs(String docs);

    /**
     * A string indicating the program used to generate the channel.
     *
     * @since 2.0.0
     * @return string indicating the program or {@code null}
     */
    String getGenerator();

    /**
     * A string indicating the program used to generate the channel.
     *
     * @since 2.0.0
     * @param string indicating the program
     */
    public void setGenerator(String generator);

    /**
     * The email address of the managing editor of the site, the person to contact for editorial
     * inquiries.
     *
     * @since 2.0.0
     * @return email address of the managing editor or {@code null}
     */
    String getManagingEditor();

    /**
     * The email address of the managing editor of the site, the person to contact for editorial
     * inquiries.
     *
     * @since 2.0.0
     * @param managingEditor email address of the managing editor
     */
    public void setManagingEditor(String managingEditor);

    /**
     * The email address of the webmaster for the site, the person to contact if there are technical
     * problems with the channel.
     *
     * @since 2.0.0
     * @return email address of the webmaster or {@code null}
     */
    String getWebMaster();

    /**
     * The email address of the webmaster for the site, the person to contact if there are technical
     * problems with the channel.
     *
     * @since 2.0.0
     * @param webMaster email address of the webmaster
     */
    void setWebMaster(String webMaster);

    /**
     * URL of XSL-Stylesheet.
     *
     * @since 2.0.0
     * @return styleSheet URL or {@code null}
     */
    String getStyleSheet();

    /**
     * URL of XSL-Stylesheet.
     *
     * @since 2.0.0
     * @param styleSheet URL or {@code null}
     */
    void setStyleSheet(String styleSheet);

    /**
     * Creates a deep clone of the object.
     * <p>
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException;

}
