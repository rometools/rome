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
package com.sun.syndication.feed;

import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.impl.ModuleUtils;
import com.sun.syndication.feed.module.Extendable;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Parent class of the RSS (Channel) and Atom (Feed) feed beans.
 * <p>
 * NOTE: We don't like this class at this package level but the alternative would have
 * been a proliferation of packages (one more level to hold atom and rss package with
 * this class just in that package).
 * <p>
 * The format of the 'type' property must be [FEEDNAME]_[FEEDVERSION] with the FEEDNAME in lower case,
 * for example: rss_0.9, rss_0.93, atom_0.3
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public abstract class WireFeed implements Cloneable, Serializable, Extendable {
    private ObjectBean _objBean;
    private String _feedType;
    private String _encoding;
    private List _modules;
    private List _foreignMarkup;

    /**
     * Default constructor, for bean cloning purposes only.
     * <p>
     *
     */
    protected WireFeed() {
        _objBean = new ObjectBean(this.getClass(),this);
    }

    /**
     * Creates a feed for a given type.
     * <p>
     * @param type of the feed to create.
     *
     */
    protected WireFeed(String type) {
        this();
        _feedType = type;
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException {
        return _objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals() method.
     * <p>
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        // can't use foreign markup in equals, due to JDOM equals impl
        Object fm = getForeignMarkup();
        setForeignMarkup(((WireFeed)other).getForeignMarkup());        
        boolean ret = _objBean.equals(other);
        // restore foreign markup
        setForeignMarkup(fm);
        return ret;
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * @return the hashcode of the bean object.
     *
     */
    public int hashCode() {
        return _objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * @return String representation for the object.
     *
     */
    public String toString() {
        return _objBean.toString();
    }





    /**
     * Sets the feedType of a the feed. <b>Do not use</b>, for bean cloning purposes only.
     * <p>
     * @param feedType the feedType of the feed.
     *
     */
    public void setFeedType(String feedType) {
        _feedType = feedType;
    }

    /**
     * Returns the type of the feed.
     *
     * @return the type of the feed.
     */
    public String getFeedType() {
        return _feedType;
    }

    /**
     * Returns the charset encoding of a the feed.
     * <p>
     * This property is not set by feed parsers. But it is used by feed generators
     * to set the encoding in the XML prolog.
     * <p>
     * @return the charset encoding of the feed.
     *
     */
    public String getEncoding() {
        return _encoding;
    }

    /**
     * Sets the charset encoding of a the feed.
     * <p>
     * This property is not set by feed parsers. But it is used by feed generators
     * to set the encoding in the XML prolog.
     * <p>
     * @param encoding the charset encoding of the feed.
     *
     */
    public void setEncoding(String encoding) {
        _encoding = encoding;
    }


    /**
     * Returns the channel modules.
     * <p>
     * @return a list of ModuleImpl elements with the channel modules,
     *         an empty list if none.
     *
     */
    public List getModules() {
        return (_modules==null) ? (_modules=new ArrayList()) : _modules;
    }

    /**
     * Sets the channel modules.
     * <p>
     * @param modules the list of ModuleImpl elements with the channel modules to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setModules(List modules) {
        _modules = modules;
    }

    /**
     * Returns the module identified by a given URI.
     * <p>
     * @param uri the URI of the ModuleImpl.
     * @return The module with the given URI, <b>null</b> if none.
     */
    public Module getModule(String uri) {
        return ModuleUtils.getModule(_modules,uri);
    }

    /**
     * Returns foreign markup found at channel level.
     * <p>
     * @return Opaque object to discourage use
     *
     */
    public Object getForeignMarkup() {
        return (_foreignMarkup==null) ? (_foreignMarkup=new ArrayList()) : _foreignMarkup;
    }

    /**
     * Sets foreign markup found at channel level.
     * <p>
     * @param foreignMarkup Opaque object to discourage use
     *
     */
    public void setForeignMarkup(Object foreignMarkup) {
        _foreignMarkup = (List)foreignMarkup;
    }
}
