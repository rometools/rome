/*
 * SleEntryImpl.java
 *
 * Created on April 29, 2006, 5:05 PM
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
package com.rometools.modules.sle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.sle.io.LabelNamespaceElement;
import com.rometools.modules.sle.io.ModuleParser;
import com.rometools.modules.sle.types.EntryValue;
import com.rometools.modules.sle.types.Group;
import com.rometools.modules.sle.types.Sort;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.ObjectBean;

/**
 * This is a <b>parse only</b> module that holds the values of enternal fields declared in the SLE
 * module. These will <b>not</b> be persisted on an output() call, <b>nor</b> will changing a value
 * here change a value in another module or a foreign markup tag.
 */
public class SleEntryImpl implements SleEntry {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(SleEntryImpl.class);

    private static final EntryValue[] EMPTY_VALUES = new EntryValue[0];
    private final ObjectBean obj = new ObjectBean(SleEntryImpl.class, this);
    private EntryValue[] groupValues = EMPTY_VALUES;
    private EntryValue[] sortValues = EMPTY_VALUES;

    public SleEntryImpl() {
        super();
    }

    @Override
    public EntryValue getGroupByElement(final Group element) {
        final EntryValue[] values = getGroupValues();
        final LabelNamespaceElement compare = new LabelNamespaceElement(element.getLabel(), element.getNamespace(), element.getElement());
        for (final EntryValue value : values) {
            if (compare.equals(new LabelNamespaceElement(value.getLabel(), value.getNamespace(), value.getElement()))) {
                return value;
            }
        }

        return null;
    }

    public void setGroupValues(final EntryValue[] groupValues) {
        this.groupValues = groupValues == null ? EMPTY_VALUES : groupValues;
    }

    @Override
    public EntryValue[] getGroupValues() {
        return groupValues;
    }

    /**
     * Returns the interface the copyFrom works on.
     * <p>
     * This is useful when dealing with properties that may have multiple implementations. For
     * example, Module.
     * <p>
     *
     * @return the interface the copyFrom works on.
     */
    @Override
    public Class<SleEntry> getInterface() {
        return SleEntry.class;
    }

    @Override
    public EntryValue getSortByElement(final Sort element) {
        LOG.debug("Looking for value for {} from {}", element.getLabel(), sortValues.length);
        final EntryValue[] values = getSortValues();
        final LabelNamespaceElement compare = new LabelNamespaceElement(element.getLabel(), element.getNamespace(), element.getElement());
        for (final EntryValue value : values) {
            LOG.debug("Compare to value {}", value.getLabel());
            if (compare.equals(new LabelNamespaceElement(value.getLabel(), value.getNamespace(), value.getElement()))) {
                LOG.debug("Match.");
                return value;
            }
        }

        return null;
    }

    public void setSortValues(final EntryValue[] sortValues) {
        this.sortValues = sortValues;
    }

    @Override
    public EntryValue[] getSortValues() {
        return sortValues;
    }

    /**
     * Returns the URI of the module.
     * <p>
     *
     * @return URI of the module.
     */
    @Override
    public String getUri() {
        return ModuleParser.TEMP.getURI();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return obj.clone();
    }

    /**
     * Copies all the properties of the given bean into this one.
     * <p>
     * Any existing properties in this bean are lost.
     * <p>
     * This method is useful for moving from one implementation of a bean interface to another. For
     * example from the default SyndFeed bean implementation to a Hibernate ready implementation.
     * <p>
     *
     * @param obj the instance to copy properties from.
     */
    @Override
    public void copyFrom(final CopyFrom obj) {
        final SleEntry entry = (SleEntry) obj;
        setGroupValues(entry.getGroupValues().clone());
        setSortValues(entry.getSortValues().clone());
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
        return obj.toString();
    }

}
