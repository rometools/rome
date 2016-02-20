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

package com.rometools.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

public class LongsTest {

    @Test
    public void testParseDecimal() {

        final String nullString = null;
        final String emptyString = "";
        final String longString = String.valueOf(Long.MAX_VALUE);
        final String decimalString = String.valueOf(Double.MAX_VALUE);

        assertThat(Longs.parseDecimal(nullString), is(nullValue()));
        assertThat(Longs.parseDecimal(emptyString), is(nullValue()));
        assertThat(Longs.parseDecimal(longString), is(Long.MAX_VALUE));
        assertThat(Longs.parseDecimal(decimalString), is((long) Double.MAX_VALUE));

    }

}
