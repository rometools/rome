package com.rometools.modules.sle;

import java.util.ArrayList;
import java.util.Collection;

import com.rometools.modules.sle.types.EntryValue;
import com.rometools.rome.feed.module.Extendable;

class SortableList<T extends Extendable> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    public SortableList(final Collection<T> c) {
        super(c);
    }

    /**
     * performs a selection sort on all the beans in the List
     */
    public synchronized void sortOnProperty(final Object value, final boolean ascending, final ValueStrategy strategy) {

        final int elementCount = size();

        for (int i = 0; i < elementCount - 1; i++) {

            for (int j = i + 1; j < elementCount; j++) {

                final T entry1 = get(i);
                final T entry2 = get(j);

                final EntryValue oc1 = strategy.getValue(entry1, value);
                final EntryValue oc2 = strategy.getValue(entry2, value);

                if (oc1 != oc2) {
                    final boolean bothNotNull = oc1 != null && oc2 != null;
                    if (ascending) {
                        if (oc2 == null || bothNotNull && oc2.compareTo(oc1) < 0) {
                            // swap entries
                            set(i, entry2);
                            set(j, entry1);
                        }
                    } else {
                        if (oc1 == null || bothNotNull && oc1.compareTo(oc2) < 0) {
                            // swap entries
                            set(i, entry2);
                            set(j, entry1);
                        }
                    }
                }
            }

        }

    }

}