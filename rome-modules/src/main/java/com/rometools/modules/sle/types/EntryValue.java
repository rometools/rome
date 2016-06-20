/*
 * EntryValue.java
 *
 * Created on April 29, 2006, 4:58 PM
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

import java.io.Serializable;

import org.jdom2.Namespace;

/**
 * An interface that parents data types for sorting and grouping.
 */
public interface EntryValue extends Serializable, Cloneable, Comparable<EntryValue> {
    /**
     * Returns the name of the element.
     *
     * @return Returns the name of the element.
     */
    public String getElement();

    /**
     * Returns a label for the element.
     *
     * @return Returns a label for the element.
     */
    public String getLabel();

    /**
     * Returns the value of the element.
     *
     * @return Returns the value of the element.
     */
    public Comparable<?> getValue();

    /**
     * Returns the namespace of the element.
     *
     * @return Returns the namespace of the element.
     */
    public Namespace getNamespace();

}
