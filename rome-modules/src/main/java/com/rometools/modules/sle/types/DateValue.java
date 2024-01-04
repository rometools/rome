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
package com.rometools.modules.sle.types;

import org.jdom2.Namespace;

import com.rometools.rome.feed.impl.EqualsBean;

import java.time.LocalDateTime;

/**
 * An EntryValue implementation representing a "date" data-type.
 */
public class DateValue implements EntryValue {

    private static final long serialVersionUID = 1L;

    private String element;
    private String label;
    private LocalDateTime value;
    private Namespace namespace = Namespace.XML_NAMESPACE;

    public void setElement(final String element) {
        this.element = element;
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

    public void setValue(final LocalDateTime value) {
        this.value = value;
    }

    @Override
    public LocalDateTime getValue() {
        return value;
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
        final DateValue clone = new DateValue();
        clone.setElement(getElement());
        clone.setLabel(getLabel());
        clone.setValue(value);
        clone.setNamespace(namespace);
        return clone;
    }

    @Override
    public boolean equals(final Object o) {
        return EqualsBean.beanEquals(DateValue.class, this, o);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return "[Namespace: " + namespace + " Element:" + element + " Label:" + label + " Value:" + value + "]";
    }

    @Override
    public int compareTo(final EntryValue other) {
        final Comparable<?> otherValue = other.getValue();
        if (otherValue instanceof LocalDateTime) {
            return value.compareTo((LocalDateTime) otherValue);
        } else {
            throw new RuntimeException("Can't compare different EntryValue types");
        }
    }

}
