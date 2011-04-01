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
package com.sun.syndication.io;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.WireFeed;
import org.jdom.Element;

import java.util.Set;

/**
 * Injects module metadata into a XML node (JDOM element).
 * <p>
 * ModuleGenerator instances must thread safe.
 * <p>
 * TODO: explain how developers can plugin their own implementations.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public interface ModuleGenerator {

    /**
     * Returns the namespace URI this generator handles.
     * <p>
     * @return the namespace URI.
     *
     */
    public String getNamespaceUri();

    /**
     * Returns a set with all the URIs (JDOM Namespace elements) this module generator uses.
     * <p/>
     * It is used by the the feed generators to add their namespace definition in
     * the root element of the generated document (forward-missing of Java 5.0 Generics).
     * <p/>
     *
     * @return a set with all the URIs (JDOM Namespace elements) this module generator uses.
     */
    public Set getNamespaces();

    /**
     * Generates and injects module metadata into an XML node (JDOM element).
     * <p>
     * @param module the module to inject into the XML node (JDOM element).
     * @param element the XML node into which module meta-data will be injected.
     */
    public void generate(Module module,Element element);
}
