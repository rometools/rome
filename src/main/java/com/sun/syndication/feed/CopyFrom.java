/*
 * Copyright 2004 Sun Microsystems, Inc.
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
 *
 */
package com.sun.syndication.feed;

/**
 * @author Alejandro Abdelnur
 */
public interface CopyFrom {

    /**
     * Returns the interface the copyFrom works on.
     * <p>
     * This is useful when dealing with properties that may have multiple implementations.
     * For example, Module.
     * <p>
     * @return the interface the copyFrom works on.
     */
    public Class getInterface();

    /**
     * Copies all the properties of the given bean into this one.
     * <p>
     * Any existing properties in this bean are lost.
     * <p>
     * This method is useful for moving from one implementation of a bean interface to another.
     * For example from the default SyndFeed bean implementation to a Hibernate ready implementation.
     * <p>
     * @param obj the instance to copy properties from.
     *
     */
    public void copyFrom(Object obj);

}
