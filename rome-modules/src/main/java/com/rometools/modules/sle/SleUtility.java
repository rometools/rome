/*
 * GroupAndSort.java
 *
 * Created on April 29, 2006, 5:56 PM
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

import java.util.List;

import org.jdom2.Document;

import com.rometools.modules.sle.types.Group;
import com.rometools.modules.sle.types.Sort;
import com.rometools.rome.feed.module.Extendable;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;

/**
 * This is a utiltiy class for grouping and sorting lists of entries based on the SLE.
 *
 * <p>
 * Note, this class can <b>ONLY</b> be used on parsed feeds, unless you manually add the appropriate
 * SleEntry objects on the items.
 * </p>
 *
 * @see SleEntry
 */
public final class SleUtility {

    // private static final String ITEM_MODULE_GENERATORS_POSFIX_KEY =
    // ".item.ModuleGenerator.classes";

    /** Private constructor for utility class */
    private SleUtility() {
    }

    /**
     * Groups values by the groups from the SLE.
     *
     * @param values List of Extendable implementations to group.
     * @param groups Group fields (from the SimpleListExtension module)
     * @return Grouped list of entries.
     */
    public static <T extends Extendable> List<T> group(final List<T> values, final Group[] groups) {
        final SortableList<T> list = getSortableList(values);
        final GroupStrategy strategy = new GroupStrategy();
        for (int i = groups.length - 1; i >= 0; i--) {
            list.sortOnProperty(groups[i], true, strategy);
        }
        return list;
    }

    /**
     * Sorts a list of values based on a given sort field using a selection sort.
     *
     * @param values List of values (implements Extendable) to sort.
     * @param sort The sort field to sort on.
     * @param ascending Sort ascending/descending.
     * @return Sorted list of values
     */
    public static <T extends Extendable> List<T> sort(final List<T> values, final Sort sort, final boolean ascending) {
        final SortableList<T> list = getSortableList(values);
        list.sortOnProperty(sort, ascending, new SortStrategy());
        return list;
    }

    /**
     * Sorts and groups a set of entries.
     *
     * @param values List of Extendable implementations.
     * @param groups Group items to group by.
     * @param sort Field to sort on.
     * @param ascending Sort ascending/descending
     * @return Grouped and sorted list of entries.
     */
    public static <T extends Extendable> List<T> sortAndGroup(final List<T> values, final Group[] groups, final Sort sort, final boolean ascending) {
        List<T> list = sort(values, sort, ascending);
        list = group(list, groups);
        return list;
    }

    /**
     * This method will take a SyndFeed object with a SimpleListExtension on it and populate the
     * entries with current SleEntry values for sorting and grouping. <b>NB</b>: This basically does
     * this by re-generating the XML for all the entries then re-parsing them into the SLE data
     * structures. It is a very heavy operation and should not be called frequently!
     */
    public static void initializeForSorting(final SyndFeed feed) throws FeedException {
        // TODO: the null parameter below will break delegating parsers and generators
        // final ModuleGenerators g = new ModuleGenerators(feed.getFeedType() +
        // ITEM_MODULE_GENERATORS_POSFIX_KEY, null);
        final SyndFeedOutput output = new SyndFeedOutput();
        final Document document = output.outputJDom(feed);
        final SyndFeed copy = new SyndFeedInput().build(document);
        feed.copyFrom(copy);
    }

    private static <T extends Extendable> SortableList<T> getSortableList(final List<T> list) {
        SortableList<T> sortableList;
        if (list instanceof SortableList) {
            sortableList = (SortableList<T>) list;
        } else {
            sortableList = new SortableList<T>(list);
        }
        return sortableList;
    }

}
