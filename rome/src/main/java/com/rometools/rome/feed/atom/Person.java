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
package com.rometools.rome.feed.atom;

import java.io.Serializable;
import java.util.List;

import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.rome.feed.module.Extendable;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.impl.ModuleUtils;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.utils.Alternatives;
import com.rometools.utils.Lists;

/**
 * Bean for person elements of Atom feeds.
 */
public class Person implements SyndPerson, Cloneable, Serializable, Extendable {

    private static final long serialVersionUID = 1L;

    private final ObjectBean objBean;

    private String name;
    private String uri; // since Atom 1.0 (was called url)
    private String uriResolved;
    private String email;
    private List<Module> modules;

    public Person() {
        objBean = new ObjectBean(this.getClass(), this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals()
     * method.
     * <p>
     *
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public boolean equals(final Object other) {
        return objBean.equals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     *
     * @return the hashcode of the bean object.
     *
     */
    @Override
    public int hashCode() {
        return objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     *
     * @return String representation for the object.
     *
     */
    @Override
    public String toString() {
        return objBean.toString();
    }

    /**
     * Returns the person name.
     * <p>
     *
     * @return the person name, <b>null</b> if none.
     *
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the personname.
     * <p>
     *
     * @param name the person name, <b>null</b> if none.
     *
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the person URL (same as {@link #getUri()})
     * <p>
     *
     * @return the person URL, <b>null</b> if none.
     */
    public String getUrl() {
        return uri;
    }

    /**
     * Sets the person URL (same as {@link #setUri(java.lang.String)})
     * <p>
     *
     * @param url the person URL, <b>null</b> if none.
     */
    public void setUrl(final String url) {
        uri = url;
    }

    public void setUriResolved(final String uriResolved) {
        this.uriResolved = uriResolved;
    }

    public String getUriResolved(final String resolveURI) {
        return Alternatives.firstNotNull(uriResolved, uri);
    }

    /**
     * Returns the person email.
     * <p>
     *
     * @return the person email, <b>null</b> if none.
     *
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Sets the person email.
     * <p>
     *
     * @param email the person email, <b>null</b> if none.
     *
     */
    @Override
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Returns the uri
     * <p>
     *
     * @return Returns the uri.
     * @since Atom 1.0
     */
    @Override
    public String getUri() {
        return uri;
    }

    /**
     * Set the uri
     * <p>
     *
     * @param uri The uri to set.
     * @since Atom 1.0
     */
    @Override
    public void setUri(final String uri) {
        this.uri = uri;
    }

    /**
     * Returns the entry modules.
     * <p>
     *
     * @return a list of ModuleImpl elements with the entry modules, an emtpy list if none.
     *
     */
    @Override
    public List<Module> getModules() {
        return modules = Lists.createWhenNull(modules);
    }

    /**
     * Sets the entry modules.
     * <p>
     *
     * @param modules the list of ModuleImpl elements with the entry modules to set, an empty list
     *            or <b>null</b> if none.
     *
     */
    @Override
    public void setModules(final List<Module> modules) {
        this.modules = modules;
    }

    /**
     * Returns the module identified by a given URI.
     * <p>
     *
     * @param uri the URI of the ModuleImpl.
     * @return The module with the given URI, <b>null</b> if none.
     */
    @Override
    public Module getModule(final String uri) {
        return ModuleUtils.getModule(modules, uri);
    }

}
