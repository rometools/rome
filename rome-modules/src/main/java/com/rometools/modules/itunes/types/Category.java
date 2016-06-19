/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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
package com.rometools.modules.itunes.types;

import java.io.Serializable;

/**
 * This Category information. Basically a name and an optional Subcategory. Categories are defined
 * by Apple. See ITMS for a view.
 */
public class Category implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private Subcategory subcategory;

    /** Creates a new instance of Category */
    public Category() {
    }

    /**
     * Creates a new instance of Category with a given name.
     *
     * @param name Name of the category.
     */
    public Category(final String name) {
        setName(name);
    }

    /**
     * Returns the name of the category
     *
     * @return Returns the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category
     *
     * @param name Sets the name of the category
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the Subcategory object for this category
     *
     * @return Returns the Subcategory object for this category
     */
    public Subcategory getSubcategory() {
        return subcategory;
    }

    /**
     * Sets the Subcategory object for this category
     *
     * @param subcategory Sets the Subcategory object for this category
     */
    public void setSubcategory(final Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    /**
     * Returns a copy of this category.
     *
     * @return Returns a copy of this category.
     */
    @Override
    public Object clone() {
        final Category c = new Category();
        c.setName(getName());

        if (getSubcategory() != null) {
            c.setSubcategory((Subcategory) getSubcategory().clone());
        }

        return c;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(getName());

        if (getSubcategory() != null) {
            sb.append(" -> " + getSubcategory().toString());
        }

        return sb.toString();
    }
}
