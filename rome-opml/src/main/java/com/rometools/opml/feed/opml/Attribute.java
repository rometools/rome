/*
 * Attribute.java
 *
 * Created on April 24, 2006, 11:11 PM
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

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

/**
 * This is a simple name-value pair attribute for outlines.
 */
public class Attribute implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String value;

    /**
     * @param name name of the attribute.
     * @param value value of the attribute.
     */
    public Attribute(final String name, final String value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and value are required.");
        }
        setName(name);
        setValue(value);
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Object clone() {
        return new Attribute(name, value);
    }

    @Override
    public boolean equals(final Object obj) {
        return new EqualsBean(Attribute.class, this).beanEquals(obj);
    }

    @Override
    public int hashCode() {
        return new EqualsBean(Attribute.class, this).beanHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBean(Attribute.class, this).toString();
    }

}
