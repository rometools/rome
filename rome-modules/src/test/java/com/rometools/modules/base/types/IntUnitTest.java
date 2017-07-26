/*
 * IntUnitTest.java
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

import com.rometools.modules.base.types.IntUnit;
import com.rometools.modules.cc.io.CCModuleGenerator;

public class IntUnitTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(CCModuleGenerator.class);

    public IntUnitTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(IntUnitTest.class);

        return suite;
    }

    /**
     * Test of getUnits method, of class com.totsp.xml.syndication.base.types.IntUnit.
     */
    public void testIntUnit() {
        LOG.debug("testIntUnit");
        IntUnit fu = new IntUnit("1km");

        assertEquals(1, fu.getValue());
        assertEquals("km", fu.getUnits());
        fu = new IntUnit("1 gb");
        assertEquals(1, fu.getValue());
        assertEquals("gb", fu.getUnits());
        fu = new IntUnit("-3");
        assertEquals(-3, fu.getValue());
        assertEquals(null, fu.getUnits());
    }

}
