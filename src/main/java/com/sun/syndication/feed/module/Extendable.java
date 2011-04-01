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

package com.sun.syndication.feed.module;

import java.util.List;

/**
 * Objects that can have modules are Extendable.
 * @author Dave Johnson
 */
public interface Extendable {
    
    /**
     * Returns the module identified by a given URI.
     * <p>
     * @param uri the URI of the ModuleImpl.
     * @return The module with the given URI, <b>null</b> if none.
     */
    public Module getModule(String uri);

    /**
     * Returns the entry modules.
     * <p>
     * @return a list of ModuleImpl elements with the entry modules,
     *         an empty list if none.
     *
     */
    List getModules();

    /**
     * Sets the entry modules.
     * <p>
     * @param modules the list of ModuleImpl elements with the entry modules to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    void setModules(List modules);
}
