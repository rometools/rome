package com.rometools.rome.io.impl;

import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

public class PropertiesLoaderTest {

    private PropertiesLoader propertyLoader;

    @Before
    public void setUp() throws Exception {
        Constructor[] ctors = PropertiesLoader.class.getDeclaredConstructors();
        Constructor ctor = ctors[0];
        ctor.setAccessible(true);
        propertyLoader = (PropertiesLoader) ctor.newInstance("rome.properties", "test.rome.properties");
    }

    @Test
    public void testPropertyListLength() {
        String propertyList[] = propertyLoader.getProperty("test_property");
        assertEquals(1, propertyList.length);
    }

    @Test
    public void testPropertyValue() {
        String propertyList[] = propertyLoader.getProperty("test_property");
        assertEquals("part1 part2", propertyList[0]);
    }

    @Test
    public void testPropertyValueTokenized() {
        String tokenizedProperties[] = propertyLoader.getTokenizedProperty("test_property", " ");
        assertEquals(2, tokenizedProperties.length);
        assertEquals("part1", tokenizedProperties[0]);
        assertEquals("part2", tokenizedProperties[1]);
    }
}
