package com.sun.syndication.test;

import java.io.InputStreamReader;
import java.io.Reader;

public final class TestUtil {

    private TestUtil() {
    }

    public static Reader loadFile(final String path) {
        return new InputStreamReader(TestUtil.class.getResourceAsStream(path));
    }

}
