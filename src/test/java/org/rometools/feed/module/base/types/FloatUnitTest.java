/*
 * FloatUnitTest.java
 * JUnit based test
 *
 * Created on November 18, 2005, 5:16 PM
 */

package org.rometools.feed.module.base.types;
import org.rometools.feed.module.base.types.FloatUnit;
import junit.framework.*;
import org.rometools.feed.module.base.io.GoogleBaseParser;
/**
 *
 * @author rcooper
 */
public class FloatUnitTest extends TestCase {
    
    public FloatUnitTest(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(FloatUnitTest.class);
        
        return suite;
    }
    
    /**
     * Test of getUnits method, of class com.totsp.xml.syndication.base.types.FloatUnit.
     */
    public void testFloatUnit() {
        System.out.println("testFloatUnit");
        FloatUnit fu = new FloatUnit( "1.22km" );
        
        assertEquals( (float) 1.22, fu.getValue(), 0 );
        assertEquals( "km", fu.getUnits() );
        fu = new FloatUnit( "1 gb" );
        assertEquals( (float) 1, fu.getValue(), 0);
        assertEquals( "gb", fu.getUnits() );
        fu = new FloatUnit( "-3.1" );
        assertEquals( (float)-3.1, fu.getValue() ,0);
        assertEquals( null, fu.getUnits() );
    }
    
    
    
}
