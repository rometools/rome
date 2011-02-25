/*
 * TimeTest.java
 * JUnit based test
 *
 * Created on April 18, 2006, 10:01 PM
 */

package com.sun.syndication.feed.module.mediarss.types;

import junit.framework.*;
import java.text.NumberFormat;

/**
 *
 * @author cooper
 */
public class TimeTest extends TestCase {
    
    public TimeTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(TimeTest.class);
        
        return suite;
    }

    /**
     * Test of toString method, of class com.sun.syndication.feed.module.mediarss.types.Time.
     */
    public void testToString() {
        Time t = new Time( "12:05:35.3");
        System.out.println( t );
        Time t2 = new Time( t.toString() );
        this.assertEquals( t.toString(), t2.toString() );
        System.out.println( t2 );
        System.out.println( new Time( "3:54.00001").toString() );
    }
    
}
