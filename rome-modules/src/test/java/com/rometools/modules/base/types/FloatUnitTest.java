/*
 * FloatUnitTest.java
 * JUnit based test
 *
 * Created on November 18, 2005, 5:16 PM
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

package com.rometools.modules.base.types;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.base.types.FloatUnit;

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
