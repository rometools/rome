/*
 * Copyright 2007 Sun Microsystems, Inc.
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
package com.rometools.propono.atom.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

/**
 * Tests Collection class, no server needed.
 */
public class CollectionTest extends TestCase {

    public CollectionTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    /**
     * Test of accepts method, of class com.rometools.rome.propono.atom.common.Collection.
     */
    public void testAccepts() {

        final Collection col = new Collection("dummy_title", "dummy_titletype", "dummy_href");

        col.setAccepts(Collections.singletonList("image/*"));
        assertTrue(col.accepts("image/gif"));
        assertTrue(col.accepts("image/jpg"));
        assertTrue(col.accepts("image/png"));
        assertFalse(col.accepts("test/html"));

        final List<String> accepts = new ArrayList<String>();
        accepts.add("image/*");
        accepts.add("text/*");
        col.setAccepts(accepts);
        assertTrue(col.accepts("image/gif"));
        assertTrue(col.accepts("image/jpg"));
        assertTrue(col.accepts("image/png"));
        assertTrue(col.accepts("text/html"));

        col.setAccepts(Collections.singletonList("*/*"));
        assertTrue(col.accepts("image/gif"));
        assertTrue(col.accepts("image/jpg"));
        assertTrue(col.accepts("image/png"));
        assertTrue(col.accepts("text/html"));
    }
}
