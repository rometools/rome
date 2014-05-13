package com.rometools.utils;

import java.util.ArrayList;
import java.util.List;

public final class Lists {

    private Lists() {
    }

    /**
     * Returns the list when it is not null. Returns a new list otherwise.
     *
     * @param list The list to process, can be null
     * @return The input list when it is not null, a new list otherwise
     */
    public static <T> List<T> createWhenNull(final List<T> list) {
        if (list == null) {
            return new ArrayList<T>();
        } else {
            return list;
        }
    }

    /**
     * Creates a new List with the given item as the first entry.
     *
     * @param item The item to add to the new list
     * @return List containing the given item
     */
    public static <T> List<T> create(final T item) {
        final List<T> list = new ArrayList<T>();
        list.add(item);
        return list;
    }

    /**
     * Extracts the first entry of the list when it is not null and contains values.
     *
     * @param list The list to extract the first entry from, can be null
     * @return The first entry of the list when it is not null or empty, null otherwise
     */
    public static <T> T firstEntry(final List<T> list) {
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * Checks whether the list is null or empty.
     *
     * @param list The list to check
     * @return true when the list is null or empty, false otherwise
     */
    public static boolean isEmpty(final List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * Checks whether the list is not null and not empty.
     *
     * @param list The list to check
     * @return true when the list is not null and not empty
     */
    public static boolean isNotEmpty(final List<?> list) {
        return !isEmpty(list);
    }

    /**
     * Checks whether the list has the given size. A null list is treated like a list without
     * entries.
     *
     * @param list The list to check
     * @param size The size to check
     * @return true when the list has the given size or when size = 0 and the list is null, false
     *         otherwise
     */
    public static boolean sizeIs(final List<?> list, final int size) {
        if (size == 0) {
            return list == null || list.isEmpty();
        } else {
            return list != null && list.size() == size;
        }
    }

    /**
     * Returns null, when the given list is empty or null
     *
     * @param list The list to process
     * @return null when the list is empty or null, the given list otherwise
     */
    public static <T> List<T> emptyToNull(final List<T> list) {
        if (isEmpty(list)) {
            return null;
        } else {
            return list;
        }
    }

}
