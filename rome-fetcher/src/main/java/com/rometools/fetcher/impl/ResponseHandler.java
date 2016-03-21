/*
 * Copyright 2004 Sun Microsystems, Inc.
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
package com.rometools.fetcher.impl;

import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to help deal with HTTP responses
 * *
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public class ResponseHandler {
    public static final String defaultCharacterEncoding = "ISO-8859-1";

    private final static Pattern characterEncodingPattern = Pattern.compile("charset=([.[^; ]]*)");

    private ResponseHandler() {
    }

    public static String getCharacterEncoding(final URLConnection connection) {
        return getCharacterEncoding(connection.getContentType());
    }

    /**
     *
     * <p>
     * Gets the character encoding of a response. (Note that this is different to the
     * content-encoding)
     * </p>
     *
     * @param contentTypeHeader the value of the content-type HTTP header eg: text/html;
     *            charset=ISO-8859-4
     * @return the character encoding, eg: ISO-8859-4
     */
    public static String getCharacterEncoding(final String contentTypeHeader) {
        if (contentTypeHeader == null) {
            return defaultCharacterEncoding;
        }

        final Matcher m = characterEncodingPattern.matcher(contentTypeHeader);
        // if (!m.matches()) {
        if (!m.find()) {
            return defaultCharacterEncoding;
        } else {
            return m.group(1);
        }
    }
}
