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
package com.rometools.rome.feed.module;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;

/**
 * Subject of the CB ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 *
 */
public class CBResourceImpl implements Cloneable, Serializable, CBResource {

    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;
    private String title;
    private String link;
    private String description;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("title", String.class);
        basePropInterfaceMap.put("link", String.class);
        basePropInterfaceMap.put("description", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = Collections.<Class<? extends CopyFrom>, Class<?>> emptyMap();

        COPY_FROM_HELPER = new CopyFromHelper(CBResource.class, basePropInterfaceMap, basePropClassImplMap);
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBResourceImpl() {
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
        if (!(other instanceof CBResourceImpl)) {
            return false;
        }
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

    @Override
    public Class<CBResource> getInterface() {
        return CBResource.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    /**
     * Returns the CB resource title.
     * <p>
     *
     * @return the CB resource title, <b>null</b> if none.
     *
     */
	@Override
    public String getTitle() {
		return title;
	}

    /**
     * Sets the CB resource title.
     * <p>
     *
     * @param title the CB resource title to set, <b>null</b> if none.
     *
     */
	@Override
    public void setTitle(String title) {
		this.title = title;
	}

    /**
     * Returns the CB resource link.
     * <p>
     *
     * @return the CB resource link, <b>null</b> if none.
     *
     */
	@Override
    public String getLink() {
		return link;
	}

    /**
     * Sets the CB resource link.
     * <p>
     *
     * @param link the CB resource link to set, <b>null</b> if none.
     *
     */
	@Override
    public void setLink(String link) {
		this.link = link;
	}

    /**
     * Returns the CB resource description.
     * <p>
     *
     * @return the CB resource description, <b>null</b> if none.
     *
     */
	@Override
    public String getDescription() {
		return description;
	}

    /**
     * Sets the CB resource description.
     * <p>
     *
     * @param description the CB resource description to set, <b>null</b> if none.
     *
     */
	@Override
    public void setDescription(String description) {
		this.description = description;
	}

}
