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
package org.rometools.feed.module.sle;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.impl.ObjectBean;
import org.rometools.feed.module.sle.io.*;
import org.rometools.feed.module.sle.io.LabelNamespaceElement;
import org.rometools.feed.module.sle.types.EntryValue;
import org.rometools.feed.module.sle.types.Group;
import org.rometools.feed.module.sle.types.Sort;

/**This is a <b>parse only</b> module that holds the values of enternal fields declared in the SLE module.
 * These will <b>not</b> be persisted on an output() call, <b>nor</b> will changing a value here change a
 * value in another module or a foreign markup tag.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class SleEntryImpl implements SleEntry {
    private static final EntryValue[] EMPTY_VALUES = new EntryValue[0];
    private ObjectBean obj = new ObjectBean(SleEntryImpl.class, this);
    private EntryValue[] groupValues = EMPTY_VALUES;
    private EntryValue[] sortValues = EMPTY_VALUES;
    
    /** Creates a new instance of SleEntryImpl */
    public SleEntryImpl() {
        super();
    }
    
    public EntryValue getGroupByElement(Group element) {
        EntryValue[] values = this.getGroupValues();
        LabelNamespaceElement compare = new LabelNamespaceElement( element.getLabel(), element.getNamespace(), element.getElement() );
        for (int i = 0; i < values.length; i++) {
            if( compare.equals( new LabelNamespaceElement( values[i].getLabel(), values[i].getNamespace(), values[i].getElement() )))
                return values[i];
        }
        
        
        return null;
    }
    
    public void setGroupValues(EntryValue[] groupValues) {
        this.groupValues = (groupValues == null) ? EMPTY_VALUES : groupValues;
    }
    
    public EntryValue[] getGroupValues() {
        return groupValues;
    }
    
    /**
     * Returns the interface the copyFrom works on.
     * <p>
     * This is useful when dealing with properties that may have multiple implementations.
     * For example, Module.
     * <p>
     *
     * @return the interface the copyFrom works on.
     */
    public Class getInterface() {
        return SleEntry.class;
    }
    
    public EntryValue getSortByElement(Sort element) {
        System.out.println("Looking for value for "+element.getLabel() + " from "+this.sortValues.length);
        EntryValue[] values = this.getSortValues();
        LabelNamespaceElement compare = new LabelNamespaceElement( element.getLabel(), element.getNamespace(), element.getElement() );
        for (int i = 0; i < values.length; i++) {
            System.out.println("Compare to value "+values[i].getLabel());
            if( compare.equals( new LabelNamespaceElement( values[i].getLabel(), values[i].getNamespace(), values[i].getElement() ) ) ){
                System.out.println("Match.");
                return values[i];
            }
        }
        
        return null;
    }
    
    public void setSortValues(EntryValue[] sortValues) {
        this.sortValues = sortValues;
    }
    
    public EntryValue[] getSortValues() {
        return sortValues;
    }
    
    /**
     * Returns the URI of the module.
     * <p>
     *
     * @return URI of the module.
     */
    public String getUri() {
        return ModuleParser.TEMP.getURI();
    }
    
    public Object clone() throws CloneNotSupportedException {
        return obj.clone();
    }
    
    /**
     * Copies all the properties of the given bean into this one.
     * <p>
     * Any existing properties in this bean are lost.
     * <p>
     * This method is useful for moving from one implementation of a bean interface to another.
     * For example from the default SyndFeed bean implementation to a Hibernate ready implementation.
     * <p>
     *
     * @param obj the instance to copy properties from.
     */
    public void copyFrom(CopyFrom obj) {
        SleEntry entry = (SleEntry) obj;
        this.setGroupValues((EntryValue[]) entry.getGroupValues().clone());
        this.setSortValues((EntryValue[]) entry.getSortValues().clone());
    }
    
    public boolean equals(Object o) {
        return obj.equals(o);
    }
    
    public int hashCode() {
        return obj.hashCode();
    }
    
    public String toString() {
        return obj.toString();
    }

}
