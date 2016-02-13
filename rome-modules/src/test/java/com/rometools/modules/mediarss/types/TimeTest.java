/*
 * TimeTest.java
 * JUnit based test
 *
 * Created on April 18, 2006, 10:01 PM
 */

package com.rometools.modules.mediarss.types;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.mediarss.types.Time;

/**
 *
 * @author cooper
 */
public class TimeTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(TimeTest.class);

    public TimeTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(TimeTest.class);

        return suite;
    }

    /**
     * Test of toString method, of class com.rometools.rome.feed.module.mediarss.types.Time.
     */
    public void testToString() {
        final Time t = new Time("12:05:35.3");
        LOG.debug("{}", t);
        final Time t2 = new Time(t.toString());
        Assert.assertEquals(t.toString(), t2.toString());
        LOG.debug("{}", t2);
        LOG.debug(new Time("3:54.00001").toString());
    }

}
