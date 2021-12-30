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
package com.rometools.rome.io;

import org.jdom2.input.JDOMParseException;

/**
 * Exception thrown by WireFeedInput instance if it can not parse a feed.
 */
public class ParsingFeedException extends FeedException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a FeedException with a message.
     *
     * @param msg exception message.
     *
     */
    public ParsingFeedException(final String msg) {
        super(msg);
    }

    /**
     * Creates a FeedException with a message and a root cause exception.
     *
     * @param msg exception message.
     * @param rootCause root cause exception.
     *
     */
    public ParsingFeedException(final String msg, final Throwable rootCause) {
        super(msg, rootCause);
    }

    /**
     * Returns the line number of the end of the text where the parse error occurred.
     * <p>
     * The first line in the document is line 1.
     * </p>
     *
     * @return an integer representing the line number, or -1 if the information is not available.
     */
    public int getLineNumber() {
        if (getCause() instanceof JDOMParseException) {
            return ((JDOMParseException) getCause()).getLineNumber();
        } else {
            return -1;
        }
    }

    /**
     * Returns the column number of the end of the text where the parse error occurred.
     * <p>
     * The first column in a line is position 1.
     * </p>
     *
     * @return an integer representing the column number, or -1 if the information is not available.
     */
    public int getColumnNumber() {
        if (getCause() instanceof JDOMParseException) {
            return ((JDOMParseException) getCause()).getColumnNumber();
        } else {
            return -1;
        }
    }

}
