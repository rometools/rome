package com.rometools.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

public class IntegersTest {

    @Test
    public void testParse() {

        final String nullString = null;
        final String emptyString = null;
        final String integerString = "1";
        final String decimalString = "1.0";

        assertThat(Integers.parse(nullString), is(nullValue()));
        assertThat(Integers.parse(emptyString), is(nullValue()));
        assertThat(Integers.parse(integerString), is(1));
        assertThat(Integers.parse(decimalString), is(nullValue()));

    }

}
