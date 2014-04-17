package com.rometools.utils;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AlternativesTest {

    @Test
    public void testFirstNotNull() {

        final Integer nullInteger = null;
        final Integer notNullInteger = 1;

        assertThat(Alternatives.firstNotNull(notNullInteger, nullInteger), is(notNullInteger));
        assertThat(Alternatives.firstNotNull(nullInteger, notNullInteger), is(notNullInteger));
        assertThat(Alternatives.firstNotNull(nullInteger, nullInteger), is(nullValue()));

    }

}
