
package com.sun.syndication.io.impl;


/**
 * A helper class that parses Numbers out of Strings in a lenient manner.
 *
 * <p>
 * No method will throw any sort of Exception when parsing a string.
 * All methods accept any Java String or null as legal input, if the
 * input is non null, whitespace will be trimmed first, and then parsing
 * will be attempted.
 * </p>
 * <p>
 * :TODO: Add Integer, Float, and Double methods as needed.
 * </p>
 */
public class NumberParser {

    /**
     * Private constructor to avoid NumberParser instances creation.
     */
    private NumberParser() {
    }

    /**
     * Parses a Long out of a string.
     *
     * @param str string to parse for a Long.
     * @return the Long represented by the given string,
     * It returns <b>null</b> if it was not possible to parse the the string.
     */
    public static Long parseLong(String str) {
        if (null != str) {
            try {
                return new Long(Long.parseLong(str.trim()));
            } catch (Exception e) {
                // :IGNORE:
            }
        }
        return null;
    }
    
    /**
     * Parse an Integer from a String. If the String is not an integer <b>null</b> is returned
     * and no exception is thrown.
     * 
     * @param str the String to parse
     * @return The Integer represented by the String, or null if it could not be parsed.
     */
    public static Integer parseInt(String str) {
        if (null != str) {
            try {
                return new Integer(Integer.parseInt(str.trim()));
            } catch (Exception e) {
                // :IGNORE:
            }
        }
        return null;    	
    }

    /**
     * Parse a Float from a String without exceptions. If the String is not a Float then null is returned
     * 
     * @param str the String to parse
     * @return The Float represented by the String, or null if it could not be parsed.
     */
    public static Float parseFloat(String str) {
        if (null != str) {
            try {
                return new Float(Float.parseFloat(str.trim()));
            } catch (Exception e) {
                // :IGNORE:
            }
        }
        return null;     	
    }
    
    /**
     * Parse a float from a String, with a default value
     * 
     * @param str
     * @param def the value to return if the String cannot be parsed
     * @return
     */
    public static float parseFloat(String str, float def) {
    	Float result = parseFloat(str);
    	return (result == null) ? def : result.floatValue();
    }
    
    /**
     * Parses a long out of a string.
     *
     * @param str string to parse for a long.
     * @param def default value to return if it is not possible to parse the the string.
     * @return the long represented by the given string, or the default.
     */
    public static long parseLong(String str, long def) {
        Long ret = parseLong(str);
        return (null == ret) ? def : ret.longValue();
    }


}
