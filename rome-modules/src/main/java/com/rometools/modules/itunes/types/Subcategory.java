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
 * This class represents a Subcategor of a Category.
 */
public class Subcategory implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;

    public Subcategory() {
    }

    /**
     * Creates a new instance of Category with a given name.
     *
     * @param name Name of the category.
     */
    public Subcategory(final String name) {
        setName(name);
    }

    /**
     * Returns the name of the subcategory.
     *
     * @return Returns the name of the subcategory.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the subcategory.
     *
     * @param name Set the name of the subcategory.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Clones the object.
     *
     * @return Clone of the object.
     */
    @Override
    public Object clone() {
        final Subcategory sc = new Subcategory();
        sc.setName(getName());

        return sc;
    }

    /**
     * String representation of the object.
     *
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        return new StringBuffer(getName()).toString();
    }
}
