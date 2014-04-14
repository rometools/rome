/*
 * FloatUnitTest.java
 * JUnit based test
 *
 * Created on November 18, 2005, 5:16 PM
 */

package org.rometools.feed.module.base.types;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rcooper
 */
public class FloatUnitTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(FloatUnitTest.class);

    public FloatUnitTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(FloatUnitTest.class);

        return suite;
    }

    /**
     * Test of getUnits method, of class com.totsp.xml.syndication.base.types.FloatUnit.
     */
    public void testFloatUnit() {
        LOG.debug("testFloatUnit");
        FloatUnit fu = new FloatUnit("1.22km");

        assertEquals((float) 1.22, fu.getValue(), 0);
        assertEquals("km", fu.getUnits());
        fu = new FloatUnit("1 gb");
        assertEquals(1, fu.getValue(), 0);
        assertEquals("gb", fu.getUnits());
        fu = new FloatUnit("-3.1");
        assertEquals((float) -3.1, fu.getValue(), 0);
        assertEquals(null, fu.getUnits());
    }

}
