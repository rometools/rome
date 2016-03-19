/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rometools.modules.mediarss.io;

import com.rometools.modules.AbstractTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MediaModuleParserTest extends AbstractTestCase {

    public MediaModuleParserTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MediaModuleParserTest.class);
    }

    public void testParsesFileSizeWithoutUnit() {
        assertThat(MediaModuleParser.parseFileSize("1234567"), is(1234567L));
    }

    public void testParsesFileSizeWithByteUnit() {
        assertThat(MediaModuleParser.parseFileSize("1B"), is(1L));
    }

    public void testParsesFileSizeWithKiloByteUnit() {
        assertThat(MediaModuleParser.parseFileSize("1KB"), is(1000L));
    }

    public void testParsesFileSizeWithMegaByteUnit() {
        assertThat(MediaModuleParser.parseFileSize("1MB"), is(new BigDecimal(1000).pow(2).longValue()));
    }

    public void testParsesFileSizeWithGigaByteUnit() {
        assertThat(MediaModuleParser.parseFileSize("1GB"), is(new BigDecimal(1000).pow(3).longValue()));
    }

    public void testParsesFileSizeWithTeraByteUnit() {
        assertThat(MediaModuleParser.parseFileSize("1TB"), is(new BigDecimal(1000).pow(4).longValue()));
    }

    public void testParsesFileSizeHandlesSpaces() {
        assertThat(MediaModuleParser.parseFileSize(" 1 KB "), is(new BigDecimal(1000).longValue()));
    }

    public void testParsesFileSizeHandlesDecimals() {
        assertThat(MediaModuleParser.parseFileSize("1.23KB"), is(new BigDecimal(1230).longValue()));
    }

    public void testThrowsExceptionOnInvalidFileSize() {
        try{
            MediaModuleParser.parseFileSize("reallybig");
            fail("Exception should've been thrown for invalid file size");
        } catch(NumberFormatException ignore) {}
    }
}
