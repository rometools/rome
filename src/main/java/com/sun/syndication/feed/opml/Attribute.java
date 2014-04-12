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
package com.sun.syndication.feed.opml;

import java.io.Serializable;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

/**
 * This is a simple name-value pair attribute for outlines.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Attribute implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
    private String _name;
    private String _value;

    /** Creates a new instance of Attribute */
    public Attribute() {
        super();
    }

    /**
     * Creates a new instance of Attribute.
     *
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

    /**
     * name of the attribute.
     *
     * @param name name of the attribute.
     */
    public void setName(final String name) {
        _name = name;
    }

    /**
     * name of the attribute.
     *
     * @return name of the attribute.
     */
    public String getName() {
        return _name;
    }

    /**
     * value of the attribute.
     *
     * @param value value of the attribute.
     */
    public void setValue(final String value) {
        _value = value;
    }

    /**
     * value of the attribute.
     *
     * @return value of the attribute.
     */
    public String getValue() {
        return _value;
    }

    @Override
    public Object clone() {
        return new Attribute(_name, _value);
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(Attribute.class, this);
        return eBean.beanEquals(obj);
    }

    @Override
    public int hashCode() {
        final EqualsBean equals = new EqualsBean(Attribute.class, this);
        return equals.beanHashCode();
    }

    @Override
    public String toString() {
        final ToStringBean tsBean = new ToStringBean(Attribute.class, this);
        return tsBean.toString();
    }
}
