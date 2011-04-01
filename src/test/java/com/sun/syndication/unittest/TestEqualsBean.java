package com.sun.syndication.unittest;

import com.sun.syndication.feed.atom.Feed;
import junit.framework.TestCase;

public class TestEqualsBean extends TestCase {

    public void testEquals() {
        Feed feed1 = new Feed();
        Feed feed2 = new Feed();
        Feed feed3 = new Feed();
        Feed feed4 = new Feed();
        feed4.setId("a");

        //reflexive
        assertTrue(feed1.equals(feed1));

        //symmetric
        assertTrue(feed1.equals(feed2));
        assertTrue(feed2.equals(feed1));

        assertFalse(feed1.equals(feed4));
        assertFalse(feed4.equals(feed1));

        //transitive
        assertTrue(feed1.equals(feed2));
        assertTrue(feed2.equals(feed3));
        assertTrue(feed1.equals(feed3));

        assertTrue(feed1.equals(feed2));
        assertFalse(feed2.equals(feed4));
        assertFalse(feed1.equals(feed4));

        //consistent
        assertTrue(feed1.equals(feed2));
        assertTrue(feed1.equals(feed2));

        assertFalse(feed1.equals(feed4));
        assertFalse(feed1.equals(feed4));

        //not-null to null is FALSE
        assertFalse(feed1.equals(null));
    }

}
