/*
 *  Copyright 2007 Dave Johnson (Blogapps project)
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
 */
package com.rometools.propono.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Utilities for file I/O and string manipulation.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public final class Utilities {

    private static final String LS = System.getProperty("line.separator");

    private Utilities() {
    }

    /**
     * Returns the contents of the file in a byte array (from JavaAlmanac).
     */
    public static byte[] getBytesFromFile(final File file) throws IOException {

        final InputStream is = new FileInputStream(file);
        try {

            // Get the size of the file
            final long length = file.length();

            // You cannot create an array using a long type.
            // It needs to be an int type.
            // Before converting to an int type, check
            // to ensure that file is not larger than Integer.MAX_VALUE.
            if (length > Integer.MAX_VALUE) {
                // File is too large
            }

            // Create the byte array to hold the data
            final byte[] bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }

            return bytes;

        } finally {
            is.close();
        }
    }

    /**
     * Read input from stream and into string.
     */
    public static String streamToString(final InputStream is) throws IOException {
        final StringBuffer sb = new StringBuffer();
        final BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
            sb.append(LS);
        }
        return sb.toString();
    }

    /**
     * Copy input stream to output stream using 8K buffer.
     */
    public static void copyInputToOutput(final InputStream input, final OutputStream output) throws IOException {
        final BufferedInputStream in = new BufferedInputStream(input);
        final BufferedOutputStream out = new BufferedOutputStream(output);
        final byte buffer[] = new byte[8192];
        for (int count = 0; count != -1;) {
            count = in.read(buffer, 0, 8192);
            if (count != -1) {
                out.write(buffer, 0, count);
            }
        }

        try {
            in.close();
            out.close();
        } catch (final IOException ex) {
            throw new IOException("Closing file streams, " + ex.getMessage());
        }
    }

    /**
     * Replaces occurences of non-alphanumeric characters with a supplied char.
     */
    public static String replaceNonAlphanumeric(final String str, final char subst) {
        final StringBuffer ret = new StringBuffer(str.length());
        final char[] testChars = str.toCharArray();
        for (final char testChar : testChars) {
            if (Character.isLetterOrDigit(testChar)) {
                ret.append(testChar);
            } else {
                ret.append(subst);
            }
        }
        return ret.toString();
    }

    /**
     * Convert string to string array.
     */
    public static String[] stringToStringArray(final String instr, final String delim) throws NoSuchElementException, NumberFormatException {
        final StringTokenizer toker = new StringTokenizer(instr, delim);
        final String stringArray[] = new String[toker.countTokens()];
        int i = 0;
        while (toker.hasMoreTokens()) {
            stringArray[i++] = toker.nextToken();
        }
        return stringArray;
    }

    /**
     * Convert string array to string.
     */
    public static String stringArrayToString(final String[] stringArray, final String delim) {
        String ret = "";
        for (final String element : stringArray) {
            if (ret.length() > 0) {
                ret = ret + delim + element;
            } else {
                ret = element;
            }
        }
        return ret;
    }

    static Pattern absoluteURIPattern = Pattern.compile("^[a-z0-9]*:.*$");

    // private static boolean isAbsoluteURI(final String uri) {
    // return absoluteURIPattern.matcher(uri).find();
    // }

    // private static boolean isRelativeURI(final String uri) {
    // return !isAbsoluteURI(uri);
    // }

    // /**
    // * } Resolve URI based considering xml:base and baseURI.
    // *
    // * @param baseURI Base URI of feed
    // * @param parent Parent from which to consider xml:base
    // * @param url URL to be resolved
    // */
    // private static String resolveURI(final String baseURI, final Parent parent, String url) {
    // if (isRelativeURI(url)) {
    // url = !".".equals(url) && !"./".equals(url) ? url : "";
    //
    // // Relative URI with parent
    // if (parent != null && parent instanceof Element) {
    //
    // // Do we have an xml:base?
    // String xmlbase = ((Element) parent).getAttributeValue("base", Namespace.XML_NAMESPACE);
    // if (xmlbase != null && xmlbase.trim().length() > 0) {
    // if (isAbsoluteURI(xmlbase)) {
    // // Absolute xml:base, so form URI right now
    // if (url.startsWith("/")) {
    // // Host relative URI
    // final int slashslash = xmlbase.indexOf("//");
    // final int nextslash = xmlbase.indexOf("/", slashslash + 2);
    // if (nextslash != -1) {
    // xmlbase = xmlbase.substring(0, nextslash);
    // }
    // return formURI(xmlbase, url);
    // }
    // if (!xmlbase.endsWith("/")) {
    // // Base URI is filename, strip it off
    // xmlbase = xmlbase.substring(0, xmlbase.lastIndexOf("/"));
    // }
    // return formURI(xmlbase, url);
    // } else {
    // // Relative xml:base, so walk up tree
    // return resolveURI(baseURI, parent.getParent(), stripTrailingSlash(xmlbase) + "/" +
    // stripStartingSlash(url));
    // }
    // }
    // // No xml:base so walk up tree
    // return resolveURI(baseURI, parent.getParent(), url);
    //
    // // Relative URI with no parent (i.e. top of tree), so form URI right now
    // } else if (parent == null || parent instanceof Document) {
    // return formURI(baseURI, url);
    // }
    // }
    // return url;
    // }

    // /**
    // * Form URI by combining base with append portion and giving special consideration to append
    // * portions that begin with ".."
    // *
    // * @param base Base of URI, may end with trailing slash
    // * @param append String to append, may begin with slash or ".."
    // */
    // private static String formURI(String base, String append) {
    // base = stripTrailingSlash(base);
    // append = stripStartingSlash(append);
    // if (append.startsWith("..")) {
    // final String ret = null;
    // final String[] parts = append.split("/");
    // for (final String part : parts) {
    // if ("..".equals(part)) {
    // final int last = base.lastIndexOf("/");
    // if (last != -1) {
    // base = base.substring(0, last);
    // append = append.substring(3, append.length());
    // } else {
    // break;
    // }
    // }
    // }
    // }
    // return base + "/" + append;
    // }

    // /**
    // * Strip starting slash from beginning of string.
    // */
    // private static String stripStartingSlash(String s) {
    // if (s != null && s.startsWith("/")) {
    // s = s.substring(1, s.length());
    // }
    // return s;
    // }

    // /**
    // * Strip trailing slash from end of string.
    // */
    // private static String stripTrailingSlash(String s) {
    // if (s != null && s.endsWith("/")) {
    // s = s.substring(0, s.length() - 1);
    // }
    // return s;
    // }

}
