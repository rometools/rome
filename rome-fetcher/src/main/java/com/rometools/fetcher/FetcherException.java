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
package com.rometools.fetcher;

/**
 * @author Nick Lothian
 *
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public class FetcherException extends Exception {
    private static final long serialVersionUID = 1L;

    int responseCode;

    public FetcherException(final Throwable cause) {
        super();
        initCause(cause);
    }

    public FetcherException(final String message, final Throwable cause) {
        super(message);
        initCause(cause);
    }

    public FetcherException(final String message) {
        super(message);
    }

    public FetcherException(final int responseCode, final String message) {
        this(message);
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

}
