package com.rometools.rome.io.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class NumberParserTest {

    @Test
    public void testLongParseFailure() {
        Long num = NumberParser.parseLong("Non Long");
        assertNull(num);
    }

    @Test
    public void testLongParseSuccess() {
        Long num = NumberParser.parseLong("1");
        assertEquals(new Long(1L), num);
    }

    @Test
    public void testLongParseDefaultSuccess() {
        long num = NumberParser.parseLong("Non Long", 1L);
        assertEquals(1L, num);
    }

    @Test
    public void testLongParseNonDefaultSuccess() {
        long num = NumberParser.parseLong("0", 1L);
        assertEquals(0L, num);
    }

    @Test
    public void testIntParseFailure() {
        Integer num = NumberParser.parseInt("Non Int");
        assertNull(num);
    }

    @Test
    public void testIntParseSuccess() {
        Integer num = NumberParser.parseInt("1");
        assertEquals(new Integer(1), num);
    }

    @Test
    public void testFloatParseFailure() {
        Float num = NumberParser.parseFloat("Non Float");
        assertNull(num);
    }

    @Test
    public void testFloatParseSuccess() {
        Float num = NumberParser.parseFloat("1.0");
        assertEquals(new Float(1.0f), num);
    }

    @Test
    public void testFloatParseDefaultSuccess() {
        float num = NumberParser.parseFloat("Non Float", 1.0f);
        assertEquals(1.0f, num, 0);
    }

    @Test
    public void testFloatParseNonDefaultSuccess() {
        float num = NumberParser.parseFloat("0.0", 1.0f);
        assertEquals(0.0f, num, 0);
    }

}
