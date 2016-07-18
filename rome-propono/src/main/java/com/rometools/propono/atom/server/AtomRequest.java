/*
 * Copyright 2007 Sun Microsystems, Inc.
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

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Map;

/**
 * Represents HTTP request to be processed by AtomHandler.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public interface AtomRequest {

    /**
     * Returns any extra path information associated with the URL the client sent when it made this
     * request.
     */
    public String getPathInfo();

    /**
     * Returns the query string that is contained in the request URL after the path.
     */
    public String getQueryString();

    /**
     * Returns the login of the user making this request, if the user has been authenticated, or
     * null if the user has not been authenticated.
     */
    public String getRemoteUser();

    /**
     * Returns a boolean indicating whether the authenticated user is included in the specified
     * logical "role".
     */
    public boolean isUserInRole(String arg0);

    /**
     * Returns a java.security.Principal object containing the name of the current authenticated
     * user.
     */
    public Principal getUserPrincipal();

    /**
     * Returns the part of this request's URL from the protocol name up to the query string in the
     * first line of the HTTP request.
     */
    public String getRequestURI();

    /**
     * Reconstructs the URL the client used to make the request.
     */
    public StringBuffer getRequestURL();

    /**
     * Returns the length, in bytes, of the request body and made available by the input stream, or
     * -1 if the length is not known.
     */
    public int getContentLength();

    /**
     * Returns the MIME type of the body of the request, or null if the type is not known.
     */
    public String getContentType();

    /**
     * Returns the value of a request parameter as a String, or null if the parameter does not
     * exist.
     */
    public String getParameter(String arg0);

    /**
     * Returns an Enumeration of String objects containing the names of the parameters contained in
     * this request.
     */
    public Enumeration<String> getParameterNames();

    /**
     * Returns an array of String objects containing all of the values the given request parameter
     * has, or null if the parameter does not exist.
     */
    public String[] getParameterValues(String arg0);

    /**
     * Returns a java.util.Map of the parameters of this request.
     */
    public Map<String, Object> getParameterMap();

    /**
     * Retrieves the body of the request as binary data using a ServletInputStream.
     */
    public InputStream getInputStream() throws IOException;

    /**
     * Returns the value of the specified request header as a long value that represents a Date
     * object.
     */
    public long getDateHeader(String arg0);

    /**
     * Returns the value of the specified request header as a String.
     */
    public String getHeader(String arg0);

    /**
     * Returns all the values of the specified request header as an Enumeration of String objects.
     */
    public Enumeration<String> getHeaders(String arg0);

    /**
     * Returns an enumeration of all the header names this request contains.
     */
    public Enumeration<String> getHeaderNames();

    /**
     * Returns the value of the specified request header as an int.
     */
    public int getIntHeader(String arg0);
}
