/*
 * TimeTest.java
 * JUnit based test
 *
 * Created on April 18, 2006, 10:01 PM
 */

package org.rometools.feed.module.mediarss.types;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author cooper
 */
public class TimeTest extends TestCase {

    public TimeTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(TimeTest.class);

        return suite;
    }

    /**
     * Test of toString method, of class com.sun.syndication.feed.module.mediarss.types.Time.
     */
    public void testToString() {
        final Time t = new Time("12:05:35.3");
        System.out.println(t);
        final Time t2 = new Time(t.toString());
        Assert.assertEquals(t.toString(), t2.toString());
        System.out.println(t2);
        System.out.println(new Time("3:54.00001").toString());
    }

}
