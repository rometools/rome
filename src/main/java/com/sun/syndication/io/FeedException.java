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
package com.sun.syndication.io;

/**
 * Exception thrown by WireFeedInput, WireFeedOutput, WireFeedParser and WireFeedGenerator instances if they
 * can not parse or generate a feed.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class FeedException extends Exception {

    /**
     * Creates a FeedException with a message.
     * <p>
     * @param msg exception message.
     *
     */
    public FeedException(String msg) {
        super(msg);
    }

    /**
     * Creates a FeedException with a message and a root cause exception.
     * <p>
     * @param msg exception message.
     * @param rootCause root cause exception.
     *
     */
    public FeedException(String msg,Throwable rootCause) {
        super(msg,rootCause);
    }

}
