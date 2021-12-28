/*
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
 *
 */
package com.rometools.rome.unittest;

import junit.framework.TestCase;

import com.rometools.rome.feed.atom.Feed;

public class TestEqualsBean extends TestCase {

    public void testEquals() {
        final Feed feed1 = new Feed();
        final Feed feed2 = new Feed();
        final Feed feed3 = new Feed();
        final Feed feed4 = new Feed();
        feed4.setId("a");

        // reflexive
        assertTrue(feed1.equals(feed1));

        // symmetric
        assertTrue(feed1.equals(feed2));
        assertTrue(feed2.equals(feed1));

        assertFalse(feed1.equals(feed4));
        assertFalse(feed4.equals(feed1));

        // transitive
        assertTrue(feed1.equals(feed2));
        assertTrue(feed2.equals(feed3));
        assertTrue(feed1.equals(feed3));

        assertTrue(feed1.equals(feed2));
        assertFalse(feed2.equals(feed4));
        assertFalse(feed1.equals(feed4));

        // consistent
        assertTrue(feed1.equals(feed2));
        assertTrue(feed1.equals(feed2));

        assertFalse(feed1.equals(feed4));
        assertFalse(feed1.equals(feed4));

        // not-null to null is FALSE
        assertFalse(feed1.equals(null));
    }

}
