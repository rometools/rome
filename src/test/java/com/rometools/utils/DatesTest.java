package com.rometools.utils;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

public class DatesTest {

    @Test
    public void testCopy() {

        final Date date = new Date();
        final Date nullDate = null;

        assertThat(Dates.copy(date), is(notNullValue()));
        assertThat(Dates.copy(date).getTime(), is(date.getTime()));
        assertThat(Dates.copy(nullDate), is(nullValue()));

    }

}
