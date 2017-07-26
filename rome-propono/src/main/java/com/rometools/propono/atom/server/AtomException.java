/*
 * Copyright 2007 Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package com.rometools.propono.atom.server;

import javax.servlet.http.HttpServletResponse;

/**
 * Exception thrown by {@link com.rometools.rome.propono.atom.server.AtomHandler} and extended by
 * other Propono Atom exception classes.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class AtomException extends Exception {

    private static final long serialVersionUID = 1L;

    /** Construct new exception */
    public AtomException() {
        super();
    }

    /** Construct new exception with message */
    public AtomException(final String msg) {
        super(msg);
    }

    /** Contruct new exception with message and wrapping existing exception */
    public AtomException(final String msg, final Throwable t) {
        super(msg, t);
    }

    /** Construct new exception to wrap existing one. */
    public AtomException(final Throwable t) {
        super(t);
    }

    /* Get HTTP status code associated with exception (HTTP 500 server error) */
    /**
     * Get HTTP status associated with exception.
     */
    public int getStatus() {
        return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }
}
