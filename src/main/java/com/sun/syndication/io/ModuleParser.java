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
import org.jdom.Element;

/**
 * Parses module metadata from a XML node (JDOM element).
 * <p>
 * ModuleParser instances must thread safe.
 * <p>
 * TODO: explain how developers can plugin their own implementations.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public interface ModuleParser {

    /**
     * Returns the namespace URI this parser handles.
     * <p>
     * @return the namespace URI.
     *
     */
    public String getNamespaceUri();

    /**
     * Parses the XML node (JDOM element) extracting module information.
     * <p>
     * @param element the XML node (JDOM element) to extract module information from.
     * @return a module instance, <b>null</b> if the element did not have module information.
     *
     */
    public Module parse(Element element);
}
