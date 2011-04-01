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
package org.rometools.propono.atom.server;

import javax.servlet.http.HttpServletResponse;


/**
 * Exception thrown by {@link com.sun.syndication.propono.atom.server.AtomHandler} 
 * and extended by other Propono Atom exception classes.
 */
public class AtomException extends Exception {
    /** Construct new exception */
    public AtomException() {
        super();
    }
    /** Construct new exception with message */
    public AtomException(String msg) {
        super(msg);
    }
    /** Contruct new exception with message and wrapping existing exception */
    public AtomException(String msg, Throwable t) {
        super(msg, t);
    }
    /** Construct new exception to wrap existing one. */
    public AtomException(Throwable t) {
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
