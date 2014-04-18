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
