/*
 * SimpleListExtensionImpl.java
 *
 * Created on April 27, 2006, 10:41 PM
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
import com.sun.syndication.feed.module.ModuleImpl;
import org.rometools.feed.module.sle.types.Group;
import org.rometools.feed.module.sle.types.Sort;


/**
 * 
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class SimpleListExtensionImpl extends ModuleImpl implements SimpleListExtension {
    private String treatAs = "list";
    private Group[] groupFields;
    private Sort[] sortFields;

    /** Creates a new instance of SimpleListExtensionImpl */
    public SimpleListExtensionImpl() {
        super(SimpleListExtensionImpl.class, SimpleListExtension.URI);
    }

    public void setGroupFields(Group[] groupFields) {
        this.groupFields = groupFields;
    }

    public Group[] getGroupFields() {
        return groupFields;
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
        return SimpleListExtension.class;
    }

    public void setSortFields(Sort[] sortFields) {
        this.sortFields = sortFields;
    }

    public Sort[] getSortFields() {
        return sortFields;
    }

    public void setTreatAs(String treatAs) {
        this.treatAs = treatAs;
    }

    public String getTreatAs() {
        return treatAs;
    }

    /**
     * Returns the URI of the module.
     * <p>
     *
     * @return URI of the module.
     */
    public String getUri() {
        return SimpleListExtension.URI;
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
        SimpleListExtension sle = (SimpleListExtension) obj;
        this.setGroupFields((Group[]) sle.getGroupFields().clone());
        this.setSortFields((Sort[]) sle.getSortFields().clone());
        this.setTreatAs(sle.getTreatAs());
    }
}
