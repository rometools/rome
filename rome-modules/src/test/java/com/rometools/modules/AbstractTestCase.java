/*
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
package com.rometools.modules;

import java.io.File;
import java.net.URISyntaxException;

import junit.framework.TestCase;

/**
 * Abstract base class for test cases.
 */
public abstract class AbstractTestCase extends TestCase {
    /**
     * Basedir for all file I/O. Important when running tests from the reactor.
     */
    public String basedir = System.getProperty("basedir") + "/src/test/resources";

    public AbstractTestCase(final String testName) {
        super(testName);
    }

    /**
     * Get test input file.
     *
     * @param path Path to test input file.
     */
    public String getTestFile(final String path) {
        try {
            return new File(this.getClass().getResource("/" + path).toURI()).getAbsolutePath();
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean assertEquals(final String message, final Object[] control, final Object[] test) {
        if (control == null && test == null) {
            return true;
        }
        assertTrue(message + " Nulls", control != null && test != null);
        assertEquals(message + " [size]", control.length, test.length);
        for (int i = 0; i < control.length; i++) {
            assertEquals(message + "[" + i + "]", control[i], test[i]);
        }
        return true;
    }
}
