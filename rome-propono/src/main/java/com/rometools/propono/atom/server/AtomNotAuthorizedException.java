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
 * Exception to be thrown by <code>AtomHandler</code> implementations in the case that a user is not
 * authorized to access a resource.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class AtomNotAuthorizedException extends AtomException {

    private static final long serialVersionUID = 1L;

    /** Construct new exception */
    public AtomNotAuthorizedException() {
        super();
    }

    /** Construct new exception with message */
    public AtomNotAuthorizedException(final String msg) {
        super(msg);
    }

    /** Construct new exception with message and root cause */
    public AtomNotAuthorizedException(final String msg, final Throwable t) {
        super(msg, t);
    }

    /** Construct new exception to wrap root cause */
    public AtomNotAuthorizedException(final Throwable t) {
        super(t);
    }

    /** Get HTTP status code of exception (HTTP 403 unauthorized) */
    @Override
    public int getStatus() {
        return HttpServletResponse.SC_UNAUTHORIZED;
    }

}
