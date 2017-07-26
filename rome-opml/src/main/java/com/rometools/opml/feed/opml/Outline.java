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
package com.rometools.opml.feed.opml;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.rometools.rome.feed.module.Module;

/**
 * This class represents an OPML outline element.
 */
public class Outline implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private Date created;
    private List<Attribute> attributes;
    private List<String> categories;
    private List<Outline> children;
    private List<Module> modules;
    private String text;
    private String title;
    private String type;
    private boolean breakpoint;
    private boolean comment;

    public Outline() {
    }

    /**
     * Creates a new outline with the specified type and text values.
     *
     * @param type type attribute value/
     * @param text text attribute value
     */
    public Outline(final String type, final String text) {
        setType(type);
        setText(text);
    }

    /**
     * Creates an outline with the given title, xmlUrl and htmlUrl. This is traditionally used for aggregator feed lists
     * and will get a type of "rss".
     *
     * @param title Title of the entry.
     * @param xmlUrl link to XML file.
     * @param htmlUrl link to html page.
     */
    public Outline(final String title, final URL xmlUrl, final URL htmlUrl) {
        super();
        setType("rss");
        setTitle(title);
        setAttributes(new ArrayList<Attribute>());

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
    public void setAttributes(final List<Attribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * List of attributes on this outline excluding the "common types" for the specification.
     *
     * @return List of attributes on this outline.
     */
    public List<Attribute> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<Attribute>();
        }

        return attributes;
    }

    /**
     * isBreakpoint is a string, either "true" or "false", indicating whether a breakpoint is set on this outline. This
     * attribute is mainly necessary for outlines used to edit scripts. If it's not present, the value is false.
     *
     * @param breakpoint whether a breakpoint is set on this outline.
     */
    public void setBreakpoint(final boolean breakpoint) {
        this.breakpoint = breakpoint;
    }

    /**
     * isBreakpoint is a string, either "true" or "false", indicating whether a breakpoint is set on this outline. This
     * attribute is mainly necessary for outlines used to edit scripts. If it's not present, the value is false.
     *
     * @return whether a breakpoint is set on this outline
     */
    public boolean isBreakpoint() {
        return breakpoint;
    }

    /**
     * (OPML 2) A List of Strings indicating values in the category attribute.
     *
     * @param categories (OPML 2) A List of Strings indicating values in the category attribute.
     */
    public void setCategories(final List<String> categories) {
        this.categories = categories;
    }

    /**
     * (OPML 2) A List of Strings indicating values in the category attribute.
     *
     * @return (OPML 2) A List of Strings indicating values in the category attribute.
     */
    public List<String> getCategories() {
        if (categories == null) {
            categories = new ArrayList<String>();
        }

        return categories;
    }

    /**
     * A list of sub-outlines for this entry.
     *
     * @param children A list of sub-outlines for this entry.
     */
    public void setChildren(final List<Outline> children) {
        this.children = children;
    }

    /**
     * A list of sub-outlines for this entry.
     *
     * @return A list of sub-outlines for this entry.
     */
    public List<Outline> getChildren() {
        if (children == null) {
            children = new ArrayList<Outline>();
        }

        return children;
    }

    /**
     * isComment is a string, either "true" or "false", indicating whether the outline is commented or not. By
     * convention if an outline is commented, all subordinate outlines are considered to also be commented. If it's not
     * present, the value is false.
     *
     * @param comment whether the outline is commented
     */
    public void setComment(final boolean comment) {
        this.comment = comment;
    }

    /**
     * isComment is a string, either "true" or "false", indicating whether the outline is commented or not. By
     * convention if an outline is commented, all subordinate outlines are considered to also be commented. If it's not
     * present, the value is false.
     *
     * @return whether the outline is commented
     */
    public boolean isComment() {
        return comment;
    }

    /**
     * (OPML 2) created is the date-time that the outline node was created.
     *
     * @param created date-time that the outline node was created.
     */
    public void setCreated(final Date created) {
        this.created = created;
    }

    /**
     * (OPML 2) created is the date-time that the outline node was created.
     *
     * @return date-time that the outline node was created.
     */
    public Date getCreated() {
        return created;
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

    public void setModules(final List<Module> modules) {
        this.modules = modules;
    }

    public List<Module> getModules() {
        if (modules == null) {
            modules = new ArrayList<Module>();
        }

        return modules;
    }

    /**
     * The "text" attribute of the outline.
     *
     * @param text The "text" attribute of the outline.
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * The "text" attribute of the outline.
     *
     * @return The "text" attribute of the outline.
     */
    public String getText() {
        return text;
    }

    /**
     * The "title" attribute of the outline.
     *
     * @param title The "title" attribute of the outline.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * The "title" attribute of the outline.
     *
     * @return The "title" attribute of the outline.
     */
    public String getTitle() {
        return title;
    }

    /**
     * The "type" attribute of the outline.
     *
     * @param type The "type" attribute of the outline.
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * The "type" attribute of the outline.
     *
     * @return The "type" attribute of the outline.
     */
    public String getType() {
        return type;
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
        final List<Attribute> attributes = Collections.synchronizedList(getAttributes());
        for (int i = 0; i < attributes.size(); i++) {
            final Attribute a = attributes.get(i);

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
        o.setCategories(new ArrayList<String>(getCategories()));
        o.setComment(isComment());
        o.setCreated(created != null ? (Date) created.clone() : null);
        o.setModules(new ArrayList<Module>(getModules()));
        o.setText(getText());
        o.setTitle(getTitle());
        o.setType(getType());

        final ArrayList<Outline> children = new ArrayList<Outline>();
        for (int i = 0; i < getChildren().size(); i++) {
            children.add((Outline) this.children.get(i).clone());
        }
        o.setChildren(children);

        final ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        for (int i = 0; i < getAttributes().size(); i++) {
            attributes.add((Attribute) this.attributes.get(i).clone());
        }
        o.setAttributes(attributes);

        return o;
    }

    @Override
    public boolean equals(final Object obj) {
        return new EqualsBean(Outline.class, this).beanEquals(obj);
    }

    @Override
    public int hashCode() {
        return new EqualsBean(Outline.class, this).beanHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBean(Outline.class, this).toString();
    }

}
