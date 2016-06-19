/*
 * ModuleGeneratorTest.java
 * JUnit based test
 *
 * Created on April 29, 2006, 9:34 PM
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

package com.rometools.modules.sle.io;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ModuleGeneratorTest extends TestCase {

    public ModuleGeneratorTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(ModuleGeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.rometools.rome.feed.module.sle.io.ModuleGenerator.
     */
    public void testGenerate() {
        // TODO add your test code.
    }

}
