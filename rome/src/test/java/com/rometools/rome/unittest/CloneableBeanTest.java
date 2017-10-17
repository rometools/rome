/*
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
package com.rometools.rome.unittest;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.rometools.rome.feed.impl.CloneableBean;

import junit.framework.TestCase;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;
import static java.util.Collections.unmodifiableSortedMap;
import static java.util.Collections.unmodifiableSortedSet;

public class CloneableBeanTest extends TestCase {

    public static class TestBean {

        private Collection<String> collection;

        private Map<String, String> map;

        public Collection<String> getCollection() {
            return collection;
        }

        public void setCollection(Collection<String> collection) {
            this.collection = collection;
        }

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }
    }

    public static class TestCollection<E> extends AbstractCollection<E> {

        private final Collection<E> collection;

        public TestCollection() {
            this(Collections.<E>emptyList());
        }

        public TestCollection(Collection<E> collection) {
            this.collection = new ArrayList<E>(collection);
        }

        @Override
        public boolean add(E e) {
            return collection.add(e);
        }

        @Override
        public Iterator<E> iterator() {
            return collection.iterator();
        }

        @Override
        public int size() {
            return collection.size();
        }
    }

    public void testCloneSetProperty() throws CloneNotSupportedException {
        TestBean bean = new TestBean();
        Set<String> set = new HashSet<String>(asList("x", "y"));
        bean.setCollection(set);

        TestBean clonedBean = (TestBean) new CloneableBean(bean).beanClone();

        Collection<String> clonedSet = clonedBean.getCollection();
        assertNotSame(set, clonedSet);
        assertEquals(new HashSet<String>(asList("x", "y")), clonedSet);
    }

    public void testCloneNonInstantiatableSetProperty() throws CloneNotSupportedException {
        TestBean bean = new TestBean();
        bean.setCollection(unmodifiableSet(new HashSet<String>(asList("x", "y"))));

        TestBean clonedBean = (TestBean) new CloneableBean(bean).beanClone();

        assertEquals(new HashSet<String>(asList("x", "y")), clonedBean.getCollection());
    }

    public void testCloneSortedSetProperty() throws CloneNotSupportedException {
        TestBean bean = new TestBean();
        SortedSet<String> sortedSet = new TreeSet<String>(asList("x", "y"));
        bean.setCollection(sortedSet);

        TestBean clonedBean = (TestBean) new CloneableBean(bean).beanClone();

        Collection<String> clonedSortedSet = clonedBean.getCollection();
        assertNotSame(sortedSet, clonedSortedSet);
        assertTrue(clonedSortedSet instanceof SortedSet);
        assertEquals(new TreeSet<String>(asList("x", "y")), clonedSortedSet);
    }

    public void testCloneNonInstantiatableSortedSetProperty() throws CloneNotSupportedException {
        TestBean bean = new TestBean();
        bean.setCollection(unmodifiableSortedSet(new TreeSet<String>(asList("x", "y"))));

        TestBean clonedBean = (TestBean) new CloneableBean(bean).beanClone();

        assertEquals(new TreeSet<String>(asList("x", "y")), clonedBean.getCollection());
    }

    public void testCloneListProperty() throws CloneNotSupportedException {
        TestBean bean = new TestBean();
        List<String> list = new ArrayList<String>(asList("x", "y"));
        bean.setCollection(list);

        TestBean clonedBean = (TestBean) new CloneableBean(bean).beanClone();

        Collection<String> clonedList = clonedBean.getCollection();
        assertNotSame(list, clonedList);
        assertEquals(asList("x", "y"), clonedList);
    }

    public void testCloneNonInstantiatableListProperty() throws CloneNotSupportedException {
        TestBean bean = new TestBean();
        bean.setCollection(unmodifiableList(asList("x", "y")));

        TestBean clonedBean = (TestBean) new CloneableBean(bean).beanClone();

        assertEquals(asList("x", "y"), clonedBean.getCollection());
    }

    public void testCloneMapProperty() throws CloneNotSupportedException {
        TestBean bean = new TestBean();
        Map<String, String> map = new HashMap<String, String>(mapOf("x1", "y1", "x2", "y2"));
        bean.setMap(map);

        TestBean clonedBean = (TestBean) new CloneableBean(bean).beanClone();

        Map<String, String> clonedMap = clonedBean.getMap();
        assertNotSame(map, clonedMap);
        assertEquals(mapOf("x1", "y1", "x2", "y2"), clonedMap);
    }

    public void testCloneNonInstantiatableMapProperty() throws CloneNotSupportedException {
        TestBean bean = new TestBean();
        bean.setMap(unmodifiableMap(mapOf("x1", "y1", "x2", "y2")));

        TestBean clonedBean = (TestBean) new CloneableBean(bean).beanClone();

        assertEquals(mapOf("x1", "y1", "x2", "y2"), clonedBean.getMap());
    }

    public void testCloneSortedMapProperty() throws CloneNotSupportedException {
        TestBean bean = new TestBean();
        SortedMap<String, String> sortedMap = new TreeMap<String, String>(mapOf("x1", "y1", "x2", "y2"));
        bean.setMap(sortedMap);

        TestBean clonedBean = (TestBean) new CloneableBean(bean).beanClone();

        Map<String, String> clonedSortedMap = clonedBean.getMap();
        assertNotSame(sortedMap, clonedSortedMap);
        assertTrue(clonedSortedMap instanceof SortedMap);
        assertEquals(new TreeMap<String, String>(mapOf("x1", "y1", "x2", "y2")), clonedSortedMap);
    }

    public void testCloneNonInstantiatableSortedMapProperty() throws CloneNotSupportedException {
        TestBean bean = new TestBean();
        bean.setMap(unmodifiableSortedMap(new TreeMap<String, String>(mapOf("x1", "y1", "x2", "y2"))));

        TestBean clonedBean = (TestBean) new CloneableBean(bean).beanClone();

        assertEquals(mapOf("x1", "y1", "x2", "y2"), clonedBean.getMap());
    }

    public void testCloneUnknownCollectionProperty() throws CloneNotSupportedException {
        TestBean bean = new TestBean();
        Collection<String> collection = new TestCollection<String>(asList("x", "y"));
        bean.setCollection(collection);

        TestBean clonedBean = (TestBean) new CloneableBean(bean).beanClone();

        Collection<String> clonedCollection = clonedBean.getCollection();
        assertNotSame(collection, clonedCollection);
        assertTrue(clonedCollection instanceof TestCollection);
        assertEquals(asList("x", "y"), new ArrayList<String>(clonedCollection));
    }

    private static Map<String, String> mapOf(String key1, String value1, String key2, String value2) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key1, value1);
        map.put(key2, value2);
        return map;
    }
}
