package org.rometools.feed.module.sle;

import java.util.ArrayList;
import java.util.Collection;

import com.sun.syndication.feed.module.Extendable;

class SortableList<T extends Extendable> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    public SortableList(final Collection<T> c) {
        super(c);
    }

    /**
     * performs a selection sort on all the beans in the List
     */
    public synchronized void sortOnProperty(final Object value, final boolean ascending, final ValueStrategy strat) {
        for (int i = 0; i < size() - 1; i++) {
            for (int j = i + 1; j < size(); j++) {

                final T o1 = get(i);
                final Comparable oc1 = strat.getValue(o1, value);

                final T o2 = get(j);
                final Comparable oc2 = strat.getValue(o2, value);

                System.out.println(oc1 + " < " + oc2);

                if (ascending) {
                    if (oc1 != oc2 && (oc2 == null || oc1 != null && oc2 != null && oc2.compareTo(oc1) < 0)) { // swap
                        set(i, o2);
                        set(j, o1);
                    }
                } else {
                    if (oc1 != oc2 && (oc1 == null || oc1 != null && oc2 != null && oc1.compareTo(oc2) < 0)) { // swap

                        set(i, o2);
                        set(j, o1);
                    }
                }
            }
        }
    }

}