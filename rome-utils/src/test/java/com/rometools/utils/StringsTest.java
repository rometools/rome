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

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

public class StringsTest {

    @Test
    public void testIsNull() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = "a";

        assertThat(Strings.isNull(nullString), Is.is(true));
        assertThat(Strings.isNull(emptyString), Is.is(false));
        assertThat(Strings.isNull(blankString), Is.is(false));
        assertThat(Strings.isNull(string), Is.is(false));

    }

    @Test
    public void testIsEmpty() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = "a";

        assertThat(Strings.isEmpty(nullString), Is.is(true));
        assertThat(Strings.isEmpty(emptyString), Is.is(true));
        assertThat(Strings.isEmpty(blankString), Is.is(false));
        assertThat(Strings.isEmpty(string), Is.is(false));

    }

    @Test
    public void testIsNotEmpty() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = "a";

        assertThat(Strings.isNotEmpty(nullString), Is.is(false));
        assertThat(Strings.isNotEmpty(emptyString), Is.is(false));
        assertThat(Strings.isNotEmpty(blankString), Is.is(true));
        assertThat(Strings.isNotEmpty(string), Is.is(true));
    }

    @Test
    public void testIsBlank() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = "a";

        assertThat(Strings.isBlank(nullString), Is.is(true));
        assertThat(Strings.isBlank(emptyString), Is.is(true));
        assertThat(Strings.isBlank(blankString), Is.is(true));
        assertThat(Strings.isBlank(string), Is.is(false));
    }

    @Test
    public void testTrim() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = " a ";

        assertThat(Strings.trim(nullString), Is.is(nullValue()));
        assertThat(Strings.trim(emptyString), Is.is(""));
        assertThat(Strings.trim(blankString), Is.is(""));
        assertThat(Strings.trim(string), Is.is("a"));
    }

    @Test
    public void testTrimToEmpty() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = " a ";

        assertThat(Strings.trimToEmpty(nullString), Is.is(""));
        assertThat(Strings.trimToEmpty(emptyString), Is.is(""));
        assertThat(Strings.trimToEmpty(blankString), Is.is(""));
        assertThat(Strings.trimToEmpty(string), Is.is("a"));
    }

    @Test
    public void testTrimToNull() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = " a ";

        assertThat(Strings.trimToNull(nullString), Is.is(nullValue()));
        assertThat(Strings.trimToNull(emptyString), Is.is(nullValue()));
        assertThat(Strings.trimToNull(blankString), Is.is(nullValue()));
        assertThat(Strings.trimToNull(string), Is.is("a"));
    }

    @Test
    public void testToLowerCase() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = "A";

        assertThat(Strings.toLowerCase(nullString), Is.is(nullValue()));
        assertThat(Strings.toLowerCase(emptyString), Is.is(""));
        assertThat(Strings.toLowerCase(blankString), Is.is(" "));
        assertThat(Strings.toLowerCase(string), Is.is("a"));
    }

}
