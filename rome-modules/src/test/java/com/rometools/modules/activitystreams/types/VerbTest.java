/*
 *  Copyright 2011 robert.cooper.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package com.rometools.modules.activitystreams.types;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.activitystreams.types.Verb;

public class VerbTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(VerbTest.class);

    public VerbTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of valueOf method, of class Verb.
     */
    public void testValueOf() {
        LOG.debug("valueOf");
        final String fav = Verb.MARK_AS_FAVORITE.toString();
        assertEquals("http://activitystrea.ms/schema/1.0/favorite", fav);
        assertEquals(Verb.MARK_AS_FAVORITE, Verb.fromIRI(fav));
    }

}
