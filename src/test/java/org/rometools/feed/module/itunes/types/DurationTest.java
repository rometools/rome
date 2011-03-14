/*
 * DurationTest.java
 * JUnit based test
 *
 * Created on August 1, 2005, 8:02 PM
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package org.rometools.feed.module.itunes.types;
import org.rometools.feed.module.itunes.types.Duration;
import junit.framework.*;

import java.text.NumberFormat;
/**
 *
 * @author cooper
 */
public class DurationTest extends TestCase {
    private Duration duration = new Duration((2 * Duration.HOUR) +
            (3 * Duration.MINUTE) + (20 * Duration.SECOND));
    private Duration duration2 = new Duration((12000 * Duration.HOUR) +
            (61 * Duration.MINUTE) + (61 * Duration.SECOND));
    private Duration duration3 = new Duration("1:20:01");
    private long duration3ms = Duration.HOUR * 1 + Duration.MINUTE * 20 + Duration.SECOND * 1;

    private Duration duration4 = new Duration("1:02");
    private long duration4ms = Duration.MINUTE * 1 + Duration.SECOND * 2;
    
    
    public DurationTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(DurationTest.class);

        return suite;
    }

    /**
     * Test of toString method, of class com.totsp.xml.syndication.itunes.Duration.
     */
    public void testToString() {
        System.out.println("testToString");
        System.out.println(duration.toString());
        assertEquals("Regular time failed", "02:03:20", duration.toString());
        System.out.println(duration2.toString());
        assertEquals("Long time failed", "12001:02:01", duration2.toString());
    }
    
    public void testGetMilliseconds(){
        System.out.println("testGetMilliseconds" );
        assertEquals("Milliseconds from 3 string constructor", this.duration3ms, this.duration3.getMilliseconds() );
        assertEquals("Milliseconds from 2 string constructor", this.duration4ms, this.duration4.getMilliseconds() );
    }
}
