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
