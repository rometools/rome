package com.rometools.rome.io.impl;

import static org.assertj.core.api.Assertions.*;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link DateParser}.
 * 
 * @author <a href="mailto:lutz.horn@posteo.de">Lutz Horn</a>
 *
 */
public class DateParserTest {

	@Before
	public void before() {
	}

	@After
	public void after() {
	}

	@Test
	public void parseW3CDateTimeIsOk() {
		Date result = DateParser.parseW3CDateTime("2019-06-07T15:27:13+02:00", Locale.GERMANY);

		assertDateFields(result);
	}

	@Test
	public void parseW3CDateTimeWithTrailingWhitespaceIsOk() {
		Date result = DateParser.parseW3CDateTime("2019-06-07T15:27:13+02:00    ", Locale.GERMANY);

		assertDateFields(result);
	}

	private void assertDateFields(Date result) {
		assertThat(result).isNotNull();
		assertThat(result).hasYear(2019);
		assertThat(result).hasMonth(6);
		assertThat(result).hasDayOfMonth(7);
		assertThat(result).hasHourOfDay(15);
		assertThat(result).hasMinute(27);
		assertThat(result).hasSecond(13);
	}

}
