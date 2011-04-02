/*
 * ContentModule.java
 *
 * Created on January 11, 2005, 1:02 PM
 *
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rometools.feed.module.content;

import com.sun.syndication.feed.module.Module;
import java.util.List;


/**
 * @version $Revision: 1.1 $
 * @author  <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public interface ContentModule extends Module {
    public static final String URI = "http://purl.org/rss/1.0/modules/content/";
    public static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    /** Returns a List of Strings containing the New Syntax Encoded values
     * are in the element.
     * @return List of content Strings
     */
    public List getEncodeds();

    /** Sets a List of Strings containing the New Syntax Encoded values
     * are in the element.
     * @return List of content Strings
     */
    public void setEncodeds(List encodeds);

    public String getUri();

    public String toString(String str);

    /** Contains a list of ContentItems that represent the "Original Syntax" set.
     * @see com.totsp.xml.syndication.content.ContentItem
     * @return List of ContentItems.
     */
    public List getContentItems();

    /** Contains a list of ContentItems that represent the "Original Syntax" set.
     * @see com.totsp.xml.syndication.content.ContentItem
     * @param List of ContentItems.
     */
    public void setContentItems(List list);

    /** Returns a List of Strings containing whatever new or original syntax items
     * are in the element.
     * @return List of content Strings
     */
    public List getContents();

    /** Sets a List of Strings containing whatever new or original syntax items
     * are in the element.
     * @return List of content Strings
     */
    public void setContents(List contents);
}
