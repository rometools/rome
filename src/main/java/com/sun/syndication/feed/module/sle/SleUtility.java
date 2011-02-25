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
package com.sun.syndication.feed.module.sle;

import com.sun.syndication.feed.module.Extendable;
import com.sun.syndication.feed.module.sle.io.ModuleParser;
import com.sun.syndication.feed.module.sle.types.Group;
import com.sun.syndication.feed.module.sle.types.Sort;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.impl.ModuleGenerators;
import org.jdom.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * This is a utiltiy class for grouping and sorting lists of entries based on the SLE.
 *
 * <p>
 * Note, this class can <b>ONLY</b> be used on parsed feeds, unless you manually add
 * the appropriate SleEntry objects on the items.
 * </p>
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @see SleEntry
 */
public class SleUtility {
    private static final String ITEM_MODULE_GENERATORS_POSFIX_KEY = ".item.ModuleGenerator.classes";
    
    /** Creates a new instance of GroupAndSort */
    private SleUtility() {
        super();
    }
    
    /**
     * Groups values by the groups from the SLE.
     * @param values List of Extendable implementations to group.
     * @param groups Group fields (from the SimpleListExtension module)
     * @return Grouped list of entries.
     */
    public static List group(List values, Group[] groups) {
        SortableList list = (values instanceof SortableList) ? (SortableList) values : new SortableList(values);
        GroupStrategy gs = new GroupStrategy();
        
        for (int i = groups.length - 1; i >= 0; i--) {
            list.sortOnProperty(groups[i], true, gs);
        }
        
        return list;
    }
    
    /**
     * Sorts a list of values based on a given sort field using a selection sort.
     * @param values List of values (implements Extendable) to sort.
     * @param sort The sort field to sort on.
     * @param ascending Sort ascending/descending.
     * @return Sorted list of values
     */
    public static List sort(List values, Sort sort, boolean ascending) {
        SortableList list = (values instanceof SortableList) ? (SortableList) values : new SortableList(values);
        list.sortOnProperty(sort, ascending, new SortStrategy());
        
        return list;
    }
    
    /**
     * Sorts and groups a set of entries.
     * @param values List of Extendable implementations.
     * @param groups Group items to group by.
     * @param sort Field to sort on.
     * @param ascending Sort ascending/descending
     * @return Grouped and sorted list of entries.
     */
    public static List sortAndGroup(List values, Group[] groups, Sort sort, boolean ascending) {
        List list = sort(values, sort, ascending);
        list = group(list, groups);
        
        return list;
    }
    
    /** This method will take a SyndFeed object with a SimpleListExtension on it and populate the entries
     * with current SleEntry values for sorting and grouping.
     * <b>NB</b>: This basically does this by re-generating the XML for all the entries then re-parsing them
     * into the SLE data structures. It is a very heavy operation and should not be called frequently!
     */
    public static void initializeForSorting( SyndFeed feed )throws FeedException {
        List syndEntries = feed.getEntries();

        // TODO: the null parameter below will break delegating parsers and generators
        ModuleGenerators g =
                new ModuleGenerators( feed.getFeedType() + ITEM_MODULE_GENERATORS_POSFIX_KEY,
                        null);
        SyndFeedOutput o = new SyndFeedOutput();
        Document d = o.outputJDom( feed );
        SyndFeed feed2 = new SyndFeedInput().build( d );
        feed.copyFrom( feed2 );
    }
    
    
    
    private static interface ValueStrategy {
        public Comparable getValue(Extendable o, Object valueName);
    }
    
    private static class GroupStrategy implements ValueStrategy {
        public Comparable getValue(Extendable o, Object value) {
            Comparable oc = null;
            
            try {
                oc = (Comparable) ((SleEntry) o.getModule(ModuleParser.TEMP.getURI())).getGroupByElement((Group)value).getValue();
            } catch (NullPointerException npe) {
                ; // watch it go by
            }
            
            return oc;
        }
    }
    
    private static class SortStrategy implements ValueStrategy {
        public Comparable getValue(Extendable o, Object value) {
            Comparable oc = null;
            try {
                oc = (Comparable) ((SleEntry) o.getModule(ModuleParser.TEMP.getURI())).getSortByElement((Sort)value).getValue();
            } catch (NullPointerException npe) {
                ; // watch it go by
            }
            
            return oc;
        }
    }
    
    private static class SortableList extends ArrayList {
        public SortableList(Collection c) {
            super(c);
        }
        
        /**
         * performs a selection sort on all the beans in the List
         */
        public synchronized void sortOnProperty(Object value, boolean ascending, ValueStrategy strat) {
            Extendable temp = null;
            
            for (int i = 0; i < (this.size() - 1); i++) {
                for (int j = i + 1; j < this.size(); j++) {
                    Extendable o1 = (Extendable) this.get(i);
                    Comparable oc1 = strat.getValue(o1, value);
                    
                    Extendable o2 = (Extendable) this.get(j);
                    Comparable oc2 = strat.getValue(o2, value);
                    System.out.println(oc1 +" < "+oc2);
                    if (ascending) {
                        if ((oc1 != oc2) && ((oc2 == null) || ((oc1 != null) && (oc2 != null) && (oc2.compareTo(oc1) < 0)))) { //swap
                            this.set(i, o2);
                            this.set(j, o1);
                        }
                    } else {
                        if ((oc1 != oc2) && ((oc1 == null) || ((oc1 != null) && (oc2 != null) && (oc1.compareTo(oc2) < 0)))) { //swap
                            
                            this.set(i, o2);
                            this.set(j, o1);
                        }
                    }
                }
            }
        }
    }
}
