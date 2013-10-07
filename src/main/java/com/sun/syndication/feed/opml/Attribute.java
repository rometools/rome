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

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import java.io.Serializable;

/**
 * This is a simple name-value pair attribute for outlines.
 * 
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Attribute implements Cloneable, Serializable {
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
    public Attribute(String name, String value) {
        if ((name == null) || (value == null)) {
            throw new NullPointerException("Name and value are required.");
        }

        this.setName(name);
        this.setValue(value);
    }

    /**
     * name of the attribute.
     * 
     * @param name name of the attribute.
     */
    public void setName(String name) {
        this._name = name;
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
    public void setValue(String value) {
        this._value = value;
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
        return new Attribute(this._name, this._value);
    }

    @Override
    public boolean equals(Object obj) {
        EqualsBean eBean = new EqualsBean(Attribute.class, this);

        return eBean.beanEquals(obj);
    }

    @Override
    public int hashCode() {
        EqualsBean equals = new EqualsBean(Attribute.class, this);

        return equals.beanHashCode();
    }

    @Override
    public String toString() {
        ToStringBean tsBean = new ToStringBean(Attribute.class, this);

        return tsBean.toString();
    }
}
