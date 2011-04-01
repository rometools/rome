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
package com.sun.syndication.propono.atom.server;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Default request implementation.
 */
public class AtomRequestImpl implements AtomRequest {
    private HttpServletRequest wrapped = null;
            
    public AtomRequestImpl(HttpServletRequest wrapped) {
        this.wrapped = wrapped;
    }

    public String getPathInfo() {
        return wrapped.getPathInfo() != null ? wrapped.getPathInfo() : "";
    }

    public String getQueryString() {
        return wrapped.getQueryString();
    }

    public String getRemoteUser() {
        return wrapped.getRemoteUser();
    }

    public boolean isUserInRole(String arg0) {
        return wrapped.isUserInRole(arg0);
    }

    public Principal getUserPrincipal() {
        return wrapped.getUserPrincipal();
    }

    public String getRequestURI() {
        return wrapped.getRequestURI();
    }

    public StringBuffer getRequestURL() {
        return wrapped.getRequestURL();
    }

    public int getContentLength() {
        return wrapped.getContentLength();
    }

    public String getContentType() {
        return wrapped.getContentType();
    }

    public String getParameter(String arg0) {
        return wrapped.getParameter(arg0);
    }

    public Enumeration getParameterNames() {
        return wrapped.getParameterNames();
    }

    public String[] getParameterValues(String arg0) {
        return wrapped.getParameterValues(arg0);
    }

    public Map getParameterMap() {
        return wrapped.getParameterMap();
    }
    
    public InputStream getInputStream() throws IOException { 
        return wrapped.getInputStream();
    }    

    public long getDateHeader(String arg0) {
        return wrapped.getDateHeader(arg0);
    }

    public String getHeader(String arg0) {
        return wrapped.getHeader(arg0);
    }

    public Enumeration getHeaders(String arg0) {
        return wrapped.getHeaders(arg0);
    }

    public Enumeration getHeaderNames() {
        return wrapped.getHeaderNames();
    }

    public int getIntHeader(String arg0) {
        return wrapped.getIntHeader(arg0);
    }
}
