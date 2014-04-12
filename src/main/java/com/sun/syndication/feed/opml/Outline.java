/*
 * Outline.java
 *
 * Created on April 24, 2006, 11:04 PM
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
package com.sun.syndication.feed.opml;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

/**
 * This class represents an OPML outline element.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Outline implements Cloneable, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Date _created;
    private List _attributes;
    private List _categories;
    private List _children;
    private List _modules;
    private String _text;
    private String _title;
    private String _type;
    private boolean _breakpoint;
    private boolean _comment;

    /** Creates a new instance of Outline */
    public Outline() {
        super();
    }

    /**
     * Creates a new outline with the specified type and text values.
     *
     * @param type type attribute value/
     * @param text text attribute value
     */
    public Outline(final String type, final String text) {
        super();
        setType(type);
        setText(text);
    }

    /**
     * Creates an outline with the given title, xmlUrl and htmlUrl. This is traditionally used for aggregator feed lists and will get a type of "rss".
     *
     * @param title Title of the entry.
     * @param xmlUrl link to XML file.
     * @param htmlUrl link to html page.
     */
    public Outline(final String title, final URL xmlUrl, final URL htmlUrl) {
        super();
        setType("rss");
        setTitle(title);
        setAttributes(new ArrayList());

        if (xmlUrl != null) {
            getAttributes().add(new Attribute("xmlUrl", xmlUrl.toString()));
        }

        if (htmlUrl != null) {
            getAttributes().add(new Attribute("htmlUrl", htmlUrl.toString()));
        }
    }

    /**
     * List of attributes on this outline excluding the "common types" for the specification.
     *
     * @param attributes List of attributes on this outline.
     */
    public void setAttributes(final List attributes) {
        _attributes = attributes;
    }

    /**
     * List of attributes on this outline excluding the "common types" for the specification.
     *
     * @return List of attributes on this outline.
     */
    public List getAttributes() {
        if (_attributes == null) {
            _attributes = new ArrayList();
        }

        return _attributes;
    }

    /**
     * isBreakpoint is a string, either "true" or "false", indicating whether a breakpoint is set on this outline. This attribute is mainly necessary for
     * outlines used to edit scripts. If it's not present, the value is false.
     *
     * @param breakpoint whether a breakpoint is set on this outline.
     */
    public void setBreakpoint(final boolean breakpoint) {
        _breakpoint = breakpoint;
    }

    /**
     * isBreakpoint is a string, either "true" or "false", indicating whether a breakpoint is set on this outline. This attribute is mainly necessary for
     * outlines used to edit scripts. If it's not present, the value is false.
     *
     * @return whether a breakpoint is set on this outline
     */
    public boolean isBreakpoint() {
        return _breakpoint;
    }

    /**
     * (OPML 2) A List of Strings indicating values in the category attribute.
     *
     * @param categories (OPML 2) A List of Strings indicating values in the category attribute.
     */
    public void setCategories(final List categories) {
        _categories = categories;
    }

    /**
     * (OPML 2) A List of Strings indicating values in the category attribute.
     *
     * @return (OPML 2) A List of Strings indicating values in the category attribute.
     */
    public List getCategories() {
        if (_categories == null) {
            _categories = new ArrayList();
        }

        return _categories;
    }

    /**
     * A list of sub-outlines for this entry.
     *
     * @param children A list of sub-outlines for this entry.
     */
    public void setChildren(final List children) {
        _children = children;
    }

    /**
     * A list of sub-outlines for this entry.
     *
     * @return A list of sub-outlines for this entry.
     */
    public List getChildren() {
        if (_children == null) {
            _children = new ArrayList();
        }

        return _children;
    }

    /**
     * isComment is a string, either "true" or "false", indicating whether the outline is commented or not. By convention if an outline is commented, all
     * subordinate outlines are considered to also be commented. If it's not present, the value is false.
     *
     * @param comment whether the outline is commented
     */
    public void setComment(final boolean comment) {
        _comment = comment;
    }

    /**
     * isComment is a string, either "true" or "false", indicating whether the outline is commented or not. By convention if an outline is commented, all
     * subordinate outlines are considered to also be commented. If it's not present, the value is false.
     *
     * @return whether the outline is commented
     */
    public boolean isComment() {
        return _comment;
    }

    /**
     * (OPML 2) created is the date-time that the outline node was created.
     *
     * @param created date-time that the outline node was created.
     */
    public void setCreated(final Date created) {
        _created = created;
    }

    /**
     * (OPML 2) created is the date-time that the outline node was created.
     *
     * @return date-time that the outline node was created.
     */
    public Date getCreated() {
        return _created;
    }

    /**
     * A convenience method to return the value of the url attribute.
     *
     * @return value of the htmlUrl attribute.
     */
    public String getUrl() {
        return getAttributeValue("url");
    }

    /**
     * A convenience method to return the value of the htmlUrl attribute.
     *
     * @return value of the htmlUrl attribute.
     */
    public String getHtmlUrl() {
        return getAttributeValue("htmlUrl");
    }

    public void setModules(final List modules) {
        _modules = modules;
    }

    public List getModules() {
        if (_modules == null) {
            _modules = new ArrayList();
        }

        return _modules;
    }

    /**
     * The "text" attribute of the outline.
     *
     * @param text The "text" attribute of the outline.
     */
    public void setText(final String text) {
        _text = text;
    }

    /**
     * The "text" attribute of the outline.
     *
     * @return The "text" attribute of the outline.
     */
    public String getText() {
        return _text;
    }

    /**
     * The "title" attribute of the outline.
     *
     * @param title The "title" attribute of the outline.
     */
    public void setTitle(final String title) {
        _title = title;
    }

    /**
     * The "title" attribute of the outline.
     *
     * @return The "title" attribute of the outline.
     */
    public String getTitle() {
        return _title;
    }

    /**
     * The "type" attribute of the outline.
     *
     * @param type The "type" attribute of the outline.
     */
    public void setType(final String type) {
        _type = type;
    }

    /**
     * The "type" attribute of the outline.
     *
     * @return The "type" attribute of the outline.
     */
    public String getType() {
        return _type;
    }

    /**
     * A convenience method to return the value of the xmlUrl attribute.
     *
     * @return value of the xmlUrl attribute.
     */
    public String getXmlUrl() {
        return getAttributeValue("xmlUrl");
    }

    /**
     * Returns the value of an attribute on the outline or null.
     *
     * @param name name of the attribute.
     */
    public String getAttributeValue(final String name) {
        final List attributes = Collections.synchronizedList(getAttributes());

        for (int i = 0; i < attributes.size(); i++) {
            final Attribute a = (Attribute) attributes.get(i);

            if (a.getName() != null && a.getName().equals(name)) {
                return a.getValue();
            }
        }

        return null;
    }

    @Override
    public Object clone() {
        final Outline o = new Outline();
        o.setBreakpoint(isBreakpoint());
        o.setCategories(new ArrayList(getCategories()));
        o.setComment(isComment());
        o.setCreated(_created != null ? (Date) _created.clone() : null);
        o.setModules(new ArrayList(getModules()));
        o.setText(getText());
        o.setTitle(getTitle());
        o.setType(getType());

        final ArrayList children = new ArrayList();

        for (int i = 0; i < getChildren().size(); i++) {
            children.add(((Outline) _children.get(i)).clone());
        }

        o.setChildren(children);

        final ArrayList attributes = new ArrayList();

        for (int i = 0; i < getAttributes().size(); i++) {
            attributes.add(((Attribute) _attributes.get(i)).clone());
        }

        o.setAttributes(attributes);

        return o;
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(Outline.class, this);

        return eBean.beanEquals(obj);
    }

    @Override
    public int hashCode() {
        final EqualsBean equals = new EqualsBean(Outline.class, this);

        return equals.beanHashCode();
    }

    @Override
    public String toString() {
        final ToStringBean tsBean = new ToStringBean(Outline.class, this);

        return tsBean.toString();
    }
}
