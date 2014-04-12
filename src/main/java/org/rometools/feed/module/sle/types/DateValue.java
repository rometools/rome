/*
 * DateValue.java
 *
 * Created on April 29, 2006, 5:29 PM
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
package org.rometools.feed.module.sle.types;

import java.util.Date;

import org.jdom2.Namespace;

import com.sun.syndication.feed.impl.ObjectBean;

/**
 * An EntryValue implementation representing a "date" data-type.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class DateValue implements EntryValue {
    private static final long serialVersionUID = 8864338943592633517L;
    private Date value;
    private final ObjectBean obj = new ObjectBean(DateValue.class, this);
    private String element;
    private String label;
    private Namespace namespace = Namespace.XML_NAMESPACE;

    /** Creates a new instance of DateValue */
    public DateValue() {
        super();
    }

    /**
     *
     * @param element
     */
    public void setElement(final String element) {
        this.element = element;
    }

    /**
     *
     * @return
     */
    @Override
    public String getElement() {
        return element;
    }

    /**
     *
     * @param label
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     *
     * @return
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param value
     */
    public void setValue(final Date value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    @Override
    public Comparable<Date> getValue() {
        return value;
    }

    @Override
    public Object clone() {
        final DateValue clone = new DateValue();
        clone.setElement(getElement());
        clone.setLabel(getLabel());
        clone.setValue(value);
        clone.setNamespace(namespace);
        return clone;
    }

    @Override
    public boolean equals(final Object o) {
        return obj.equals(o);
    }

    @Override
    public int hashCode() {
        return obj.hashCode();
    }

    @Override
    public String toString() {
        return "[Namespace: " + namespace + " Element:" + element + " Label:" + label + " Value:" + value + "]";
    }

    @Override
    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(final Namespace namespace) {
        this.namespace = namespace == null ? Namespace.XML_NAMESPACE : namespace;
    }
}
