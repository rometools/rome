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
