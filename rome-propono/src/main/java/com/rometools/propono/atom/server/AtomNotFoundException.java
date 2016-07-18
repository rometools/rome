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
 * Exception thrown by AtomHandler in that case a resource is not found.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class AtomNotFoundException extends AtomException {

    private static final long serialVersionUID = 1L;

    /** Construct new exception */
    public AtomNotFoundException() {
        super();
    }

    /** Construct new exception with message */
    public AtomNotFoundException(final String msg) {
        super(msg);
    }

    /** Construct new exception with message and root cause */
    public AtomNotFoundException(final String msg, final Throwable t) {
        super(msg, t);
    }

    /** Construct new exception with root cause */
    public AtomNotFoundException(final Throwable t) {
        super(t);
    }

    /** Get HTTP status code associated with exception (404 not found) */
    @Override
    public int getStatus() {
        return HttpServletResponse.SC_NOT_FOUND;
    }
}
