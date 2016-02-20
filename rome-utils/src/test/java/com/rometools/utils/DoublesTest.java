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

public class DoublesTest {

    @Test
    public void testParse() {

        final String nullString = null;
        final String emptyString = null;
        final String integerString = "1";
        final String decimalString = "1.0";

        assertThat(Doubles.parse(nullString), is(nullValue()));
        assertThat(Doubles.parse(emptyString), is(nullValue()));
        assertThat(Doubles.parse(integerString), is(1.0));
        assertThat(Doubles.parse(decimalString), is(1.0));

    }

}
