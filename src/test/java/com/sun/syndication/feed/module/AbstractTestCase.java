package com.sun.syndication.feed.module;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.io.File;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;


/**
 * Abstract base class for test cases.
 *
 * @author <a href="jason@zenplex.com">Jason van Zyl</a>
 */
public abstract class AbstractTestCase extends TestCase {
    /**
     * Basedir for all file I/O. Important when running tests from
     * the reactor.
     */
    public String basedir = System.getProperty("basedir") +"/src/test/resources";

    /**
     * Constructor.
     */
    public AbstractTestCase(String testName) {
        super(testName);
    }

    /**
     * Get test input file.
     *
     * @param path Path to test input file.
     */
    public String getTestFile(String path) {
        return new File(basedir, path).getAbsolutePath();
    }

    /** This method takes a JavaBean and generates a standard toString() type result for it.
     * @param o JavaBean object to stringinate
     * @return STRINGIATION! Stringingating the countryside. Stringinating all the peasants.
     */
    public static String beanToString(Object o, boolean showNulls) {
        StringBuffer result = new StringBuffer();

        if (o == null) {
            return "--- null";
        }

        result.append("--- begin");
        result.append(o.getClass().getName());
        result.append(" hash: ");
        result.append(o.hashCode());
        result.append("\r\n");

        try {
            PropertyDescriptor[] pds = Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors();

            for (int pdi = 0; pdi < pds.length; pdi++) {
                String out = "";

                try {
                    Object value = pds[pdi].getReadMethod().invoke(o, (Object[]) null);

                    if ((value != null) && value.getClass().isArray()) {
                        Object[] values = (Object[]) value;

                        for (int i = 0; i < values.length; i++) {
                            out += (values[i] + " ");
                        }
                    } else {
                        out += value;
                    }

                    if (!out.equals("null") || showNulls) {
                        result.append("Property: " + pds[pdi].getName() + " Value: " + out);
                    }
                } catch (IllegalAccessException iae) {
                    result.append("Property: " + pds[pdi].getName() + " (Illegal Access to Value) ");
                } catch (InvocationTargetException iae) {
                    result.append("Property: " + pds[pdi].getName() + " (InvocationTargetException) " + iae.toString());
                } catch (Exception e) {
                    result.append("Property: " + pds[pdi].getName() + " (Other Exception )" + e.toString());
                }

                if (!out.equals("null") || showNulls) {
                    result.append("\r\n");
                }
            }
        } catch (IntrospectionException ie) {
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
    
    public boolean assertEquals( String message, Object[] control , Object[] test){
	if( control == null && test == null ){
	    return true;
	}
	assertTrue( message+" Nulls", ( control != null && test != null));
	assertEquals( message+" [size]", control.length, test.length );
	for( int i=0; i < control.length; i++ ){
	    assertEquals( message +"["+i+"]", control[i], test[i]);
	}
	return true;
    }
}
