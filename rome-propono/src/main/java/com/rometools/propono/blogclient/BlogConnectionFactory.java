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

import java.lang.reflect.Constructor;

/**
 * Entry point to the Blogapps blog client library.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class BlogConnectionFactory {

    // BlogConnection implementations must:
    // 1) implement BlogConnection
    // 2) privide contructor that accepts three strings args: url, username and password.

    // TODO: make implementations configurable
    private static String ATOMPROTOCOL_IMPL_CLASS = "com.rometools.propono.blogclient.atomprotocol.AtomConnection";

    private static String METAWEBLOG_IMPL_CLASS = "com.rometools.propono.blogclient.metaweblog.MetaWeblogConnection";

    private BlogConnectionFactory() {
    }

    /**
     * Create a connection to a blog server.
     *
     * @param type Connection type, must be "atom" or "metaweblog"
     * @param url End-point URL to connect to
     * @param username Username for login to blog server
     * @param password Password for login to blog server
     */
    public static BlogConnection getBlogConnection(final String type, final String url, final String username, final String password)
            throws BlogClientException {
        BlogConnection blogConnection = null;
        if (type == null || type.equals("metaweblog")) {
            blogConnection = createBlogConnection(METAWEBLOG_IMPL_CLASS, url, username, password);
        } else if (type.equals("atom")) {
            blogConnection = createBlogConnection(ATOMPROTOCOL_IMPL_CLASS, url, username, password);
        } else {
            throw new BlogClientException("Type must be 'atom' or 'metaweblog'");
        }
        return blogConnection;
    }

    private static BlogConnection createBlogConnection(final String className, final String url, final String username, final String password)
            throws BlogClientException {

        Class<?> conClass;
        try {
            conClass = Class.forName(className);
        } catch (final ClassNotFoundException ex) {
            throw new BlogClientException("BlogConnection impl. class not found: " + className, ex);
        }
        final Class<?>[] args = new Class[] { String.class, String.class, String.class };
        Constructor<?> ctor;
        try {
            ctor = conClass.getConstructor(args);
            return (BlogConnection) ctor.newInstance(new Object[] { url, username, password });
        } catch (final Throwable t) {
            throw new BlogClientException("ERROR instantiating BlogConnection impl.", t);
        }
    }

}
