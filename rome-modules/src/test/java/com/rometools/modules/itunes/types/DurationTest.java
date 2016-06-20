/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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
 */
package com.rometools.modules.itunes.types;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.itunes.types.Duration;

public class DurationTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(DurationTest.class);

    private final Duration duration = new Duration(2 * Duration.HOUR + 3 * Duration.MINUTE + 20 * Duration.SECOND);
    private final Duration duration2 = new Duration(12000 * Duration.HOUR + 61 * Duration.MINUTE + 61 * Duration.SECOND);
    private final Duration duration3 = new Duration("1:20:01");
    private final long duration3ms = Duration.HOUR * 1 + Duration.MINUTE * 20 + Duration.SECOND * 1;

    private final Duration duration4 = new Duration("1:02");
    private final long duration4ms = Duration.MINUTE * 1 + Duration.SECOND * 2;

    public DurationTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(DurationTest.class);

        return suite;
    }

    /**
     * Test of toString method, of class com.totsp.xml.syndication.itunes.Duration.
     */
    public void testToString() {
        LOG.debug("testToString");
        LOG.debug(duration.toString());
        assertEquals("Regular time failed", "02:03:20", duration.toString());
        LOG.debug(duration2.toString());
        assertEquals("Long time failed", "12001:02:01", duration2.toString());
    }

    public void testGetMilliseconds() {
        LOG.debug("testGetMilliseconds");
        assertEquals("Milliseconds from 3 string constructor", duration3ms, duration3.getMilliseconds());
        assertEquals("Milliseconds from 2 string constructor", duration4ms, duration4.getMilliseconds());
    }
}
