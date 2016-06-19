/*
 * StringValue.java
 *
 * Created on April 29, 2006, 4:59 PM
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
package com.rometools.modules.sle.types;

import org.jdom2.Namespace;

import com.rometools.rome.feed.impl.ObjectBean;

/**
 * An EntryValue implementation for "text" data-types.
 */
public class StringValue implements EntryValue {

    private static final long serialVersionUID = 1L;

    private final ObjectBean obj = new ObjectBean(StringValue.class, this);

    private String element;
    private String label;
    private String value;
    private Namespace namespace = Namespace.XML_NAMESPACE;

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public String getElement() {
        return element;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setElement(final String element) {
        this.element = element;
    }

    @Override
    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(final Namespace namespace) {
        this.namespace = namespace == null ? Namespace.XML_NAMESPACE : namespace;
    }

    @Override
    public Object clone() {
        final StringValue clone = new StringValue();
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
    public int compareTo(final EntryValue other) {
        final Comparable<?> otherValue = other.getValue();
        if (otherValue instanceof String) {
            return value.compareTo((String) otherValue);
        } else {
            throw new RuntimeException("Can't compare different EntryValue types");
        }
    }

}
