/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rometools.utils;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ListsTest {

    @Test
    public void testCreateWhenNull() {

        final List<Integer> list = new ArrayList<Integer>();
        final List<Integer> nullList = null;

        assertThat(Lists.createWhenNull(list), is(notNullValue()));
        assertThat(Lists.createWhenNull(list), is(list));
        assertThat(Lists.createWhenNull(nullList), is(notNullValue()));

    }

    @Test
    public void testCreate() {

        final List<Integer> create = Lists.create(1);
        assertThat(create, is(notNullValue()));
        assertThat(create.size(), is(1));
        assertThat(create, hasItem(1));

    }

    @Test
    public void testFirstEntry() {

        final List<Integer> nullList = null;
        final List<Integer> listWithoutEntries = new ArrayList<Integer>();
        final List<Integer> listWithOneEntry = Arrays.asList(1);
        final List<Integer> listWithTwoEntries = Arrays.asList(1, 2);

        assertThat(Lists.firstEntry(nullList), is(nullValue()));
        assertThat(Lists.firstEntry(listWithoutEntries), is(nullValue()));
        assertThat(Lists.firstEntry(listWithOneEntry), is(1));
        assertThat(Lists.firstEntry(listWithTwoEntries), is(1));

    }

    @Test
    public void testIsEmpty() {

        final List<Integer> nullList = null;
        final List<Integer> listWithoutEntries = new ArrayList<Integer>();
        final List<Integer> listWithOneEntry = Arrays.asList(1);

        assertThat(Lists.isEmpty(nullList), is(true));
        assertThat(Lists.isEmpty(listWithoutEntries), is(true));
        assertThat(Lists.isEmpty(listWithOneEntry), is(false));

    }

    @Test
    public void testIsNotEmpty() {

        final List<Integer> nullList = null;
        final List<Integer> listWithoutEntries = new ArrayList<Integer>();
        final List<Integer> listWithOneEntry = Arrays.asList(1);

        assertThat(Lists.isNotEmpty(nullList), is(false));
        assertThat(Lists.isNotEmpty(listWithoutEntries), is(false));
        assertThat(Lists.isNotEmpty(listWithOneEntry), is(true));
    }

    @Test
    public void testSizeIs() {

        final List<Integer> nullList = null;
        final List<Integer> listWithoutEntries = new ArrayList<Integer>();
        final List<Integer> listWithOneEntry = Arrays.asList(1);

        assertThat(Lists.sizeIs(nullList, 0), is(true));
        assertThat(Lists.sizeIs(listWithoutEntries, 0), is(true));
        assertThat(Lists.sizeIs(listWithOneEntry, 1), is(true));

    }

    @Test
    public void testEmptyToNull() {

        final List<Integer> nullList = null;
        final List<Integer> listWithoutEntries = new ArrayList<Integer>();
        final List<Integer> listWithOneEntry = Arrays.asList(1);

        assertThat(Lists.emptyToNull(nullList), is(nullValue()));
        assertThat(Lists.emptyToNull(listWithoutEntries), is(nullValue()));
        assertThat(Lists.emptyToNull(listWithOneEntry), is(notNullValue()));

    }

}
