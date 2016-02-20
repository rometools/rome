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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringsTest {

    @Test
    public void testIsNull() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = "a";

        assertThat(Strings.isNull(nullString), is(true));
        assertThat(Strings.isNull(emptyString), is(false));
        assertThat(Strings.isNull(blankString), is(false));
        assertThat(Strings.isNull(string), is(false));

    }

    @Test
    public void testIsEmpty() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = "a";

        assertThat(Strings.isEmpty(nullString), is(true));
        assertThat(Strings.isEmpty(emptyString), is(true));
        assertThat(Strings.isEmpty(blankString), is(false));
        assertThat(Strings.isEmpty(string), is(false));

    }

    @Test
    public void testIsNotEmpty() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = "a";

        assertThat(Strings.isNotEmpty(nullString), is(false));
        assertThat(Strings.isNotEmpty(emptyString), is(false));
        assertThat(Strings.isNotEmpty(blankString), is(true));
        assertThat(Strings.isNotEmpty(string), is(true));
    }

    @Test
    public void testIsBlank() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = "a";

        assertThat(Strings.isBlank(nullString), is(true));
        assertThat(Strings.isBlank(emptyString), is(true));
        assertThat(Strings.isBlank(blankString), is(true));
        assertThat(Strings.isBlank(string), is(false));
    }

    @Test
    public void testTrim() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = " a ";

        assertThat(Strings.trim(nullString), is(nullValue()));
        assertThat(Strings.trim(emptyString), is(""));
        assertThat(Strings.trim(blankString), is(""));
        assertThat(Strings.trim(string), is("a"));
    }

    @Test
    public void testTrimToEmpty() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = " a ";

        assertThat(Strings.trimToEmpty(nullString), is(""));
        assertThat(Strings.trimToEmpty(emptyString), is(""));
        assertThat(Strings.trimToEmpty(blankString), is(""));
        assertThat(Strings.trimToEmpty(string), is("a"));
    }

    @Test
    public void testTrimToNull() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = " a ";

        assertThat(Strings.trimToNull(nullString), is(nullValue()));
        assertThat(Strings.trimToNull(emptyString), is(nullValue()));
        assertThat(Strings.trimToNull(blankString), is(nullValue()));
        assertThat(Strings.trimToNull(string), is("a"));
    }

    @Test
    public void testToLowerCase() {

        final String nullString = null;
        final String emptyString = "";
        final String blankString = " ";
        final String string = "A";

        assertThat(Strings.toLowerCase(nullString), is(nullValue()));
        assertThat(Strings.toLowerCase(emptyString), is(""));
        assertThat(Strings.toLowerCase(blankString), is(" "));
        assertThat(Strings.toLowerCase(string), is("a"));
    }

}
