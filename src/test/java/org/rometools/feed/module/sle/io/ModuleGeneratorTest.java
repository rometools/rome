/*
 * ModuleGeneratorTest.java
 * JUnit based test
 *
 * Created on April 29, 2006, 9:34 PM
 */

package org.rometools.feed.module.sle.io;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author cooper
 */
public class ModuleGeneratorTest extends TestCase {

    public ModuleGeneratorTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(ModuleGeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.sun.syndication.feed.module.sle.io.ModuleGenerator.
     */
    public void testGenerate() {
        // TODO add your test code.
    }

}
