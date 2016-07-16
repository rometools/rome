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

import javax.servlet.http.HttpServletRequest;

/**
 * Default request implementation.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class AtomRequestImpl implements AtomRequest {

    private HttpServletRequest wrapped = null;

    public AtomRequestImpl(final HttpServletRequest wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getPathInfo() {
        return wrapped.getPathInfo() != null ? wrapped.getPathInfo() : "";
    }

    @Override
    public String getQueryString() {
        return wrapped.getQueryString();
    }

    @Override
    public String getRemoteUser() {
        return wrapped.getRemoteUser();
    }

    @Override
    public boolean isUserInRole(final String arg0) {
        return wrapped.isUserInRole(arg0);
    }

    @Override
    public Principal getUserPrincipal() {
        return wrapped.getUserPrincipal();
    }

    @Override
    public String getRequestURI() {
        return wrapped.getRequestURI();
    }

    @Override
    public StringBuffer getRequestURL() {
        return wrapped.getRequestURL();
    }

    @Override
    public int getContentLength() {
        return wrapped.getContentLength();
    }

    @Override
    public String getContentType() {
        return wrapped.getContentType();
    }

    @Override
    public String getParameter(final String arg0) {
        return wrapped.getParameter(arg0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enumeration<String> getParameterNames() {
        return wrapped.getParameterNames();
    }

    @Override
    public String[] getParameterValues(final String arg0) {
        return wrapped.getParameterValues(arg0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getParameterMap() {
        return wrapped.getParameterMap();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return wrapped.getInputStream();
    }

    @Override
    public long getDateHeader(final String arg0) {
        return wrapped.getDateHeader(arg0);
    }

    @Override
    public String getHeader(final String arg0) {
        return wrapped.getHeader(arg0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enumeration<String> getHeaders(final String arg0) {
        return wrapped.getHeaders(arg0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enumeration<String> getHeaderNames() {
        return wrapped.getHeaderNames();
    }

    @Override
    public int getIntHeader(final String arg0) {
        return wrapped.getIntHeader(arg0);
    }
}
