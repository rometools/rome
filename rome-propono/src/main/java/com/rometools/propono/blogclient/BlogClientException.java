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
package com.rometools.propono.blogclient;

/**
 * Represents a Blog Client exception, the library throws these instead of implementation specific
 * exceptions.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class BlogClientException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construct a new exception
     *
     * @param msg Text message that explains exception
     */
    public BlogClientException(final String msg) {
        super(msg);
    }

    /**
     * Construct a new exception which wraps a throwable.
     *
     * @param msg Text message that explains exception
     * @param t Throwable to be wrapped by exception
     */
    public BlogClientException(final String msg, final Throwable t) {
        super(msg, t);
    }
}
