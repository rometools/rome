/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.rometools.modules;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

import junit.framework.TestCase;

/**
 * Abstract base class for test cases.
 */
public abstract class AbstractTestCase extends TestCase {
    /**
     * Basedir for all file I/O. Important when running tests from the reactor.
     */
    public String basedir = System.getProperty("basedir") + "/src/test/resources";

    public AbstractTestCase(final String testName) {
        super(testName);
    }

    /**
     * Get test input file.
     *
     * @param path Path to test input file.
     */
    public String getTestFile(final String path) {
        try {
            return new File(this.getClass().getResource("/" + path).toURI()).getAbsolutePath();
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method takes a JavaBean and generates a standard toString() type result for it.
     *
     * @param o JavaBean object to stringinate
     * @return STRINGIATION! Stringingating the countryside. Stringinating all the peasants.
     */
    public static String beanToString(final Object o, final boolean showNulls) {
        final StringBuffer result = new StringBuffer();

        if (o == null) {
            return "--- null";
        }

        result.append("--- begin");
        result.append(o.getClass().getName());
        result.append(" hash: ");
        result.append(o.hashCode());
        result.append("\r\n");

        try {
            final PropertyDescriptor[] pds = Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors();

            for (final PropertyDescriptor pd : pds) {
                String out = "";

                try {
                    final Object value = pd.getReadMethod().invoke(o, (Object[]) null);

                    if (value != null && value.getClass().isArray()) {
                        final Object[] values = (Object[]) value;

                        for (final Object value2 : values) {
                            out += value2 + " ";
                        }
                    } else {
                        out += value;
                    }

                    if (!out.equals("null") || showNulls) {
                        result.append("Property: " + pd.getName() + " Value: " + out);
                    }
                } catch (final IllegalAccessException iae) {
                    result.append("Property: " + pd.getName() + " (Illegal Access to Value) ");
                } catch (final InvocationTargetException iae) {
                    result.append("Property: " + pd.getName() + " (InvocationTargetException) " + iae.toString());
                } catch (final Exception e) {
                    result.append("Property: " + pd.getName() + " (Other Exception )" + e.toString());
                }

                if (!out.equals("null") || showNulls) {
                    result.append("\r\n");
                }
            }
        } catch (final IntrospectionException ie) {
            result.append("Introspection Exception: " + ie.toString());
            result.append("\r\n");
        }

        result.append("--- end ");
        result.append(o.getClass().getName());
        result.append(" hash: ");
        result.append(o.hashCode());
        result.append("\n");

        return result.toString();
    }

    public boolean assertEquals(final String message, final Object[] control, final Object[] test) {
        if (control == null && test == null) {
            return true;
        }
        assertTrue(message + " Nulls", control != null && test != null);
        assertEquals(message + " [size]", control.length, test.length);
        for (int i = 0; i < control.length; i++) {
            assertEquals(message + "[" + i + "]", control[i], test[i]);
        }
        return true;
    }
}
