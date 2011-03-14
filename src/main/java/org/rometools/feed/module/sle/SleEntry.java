/*
 * EntrySle.java
 *
 * Created on April 27, 2006, 7:13 PM
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

import com.sun.syndication.feed.module.Module;
import org.rometools.feed.module.sle.io.*;
import org.rometools.feed.module.sle.types.EntryValue;
import org.rometools.feed.module.sle.types.Group;
import org.rometools.feed.module.sle.types.Sort;


/** This is a <b>parse only</b> module that holds the values of enternal fields declared in the SLE module.
 * These will <b>not</b> be persisted on an output() call, <b>nor</b> will changing a value here change a
 * value in another module or a foreign markup tag.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public interface SleEntry extends Module {
    /**
     * A bogus namespace used for temporarily storing values during parsing.
     */
    public static final String URI = ModuleParser.TEMP.getURI();

    /**
     * Returns an EntryValue for the given element name.
     * @param element element name to look for
     * @return Returns an EntryValue for the given element name.
     */
    public EntryValue getGroupByElement(Group element);

    /**
     * An array of EntryValue objects that correspond to the grouping for the feed.
     * @return An array of EntryValue objects that correspond to the grouping for the feed.
     */
    public EntryValue[] getGroupValues();

    /**
     * Returns an EntryValue for the given element name.
     * @param element element name
     * @return Returns an EntryValue for the given element name.
     */
    public EntryValue getSortByElement(Sort element);

    /**
     * Returns an array of EntryValues for the fields declared in the heading.
     * 
     * <b>NB:</b><br /> 
     * Right now the parser will take any default=true field and change it to an 
     * integer value representing the default order in the field. You should not
     * rely on these values data display to a user!
     * @return Array of EntryValue implementations from this entry.
     */
    public EntryValue[] getSortValues();
}
