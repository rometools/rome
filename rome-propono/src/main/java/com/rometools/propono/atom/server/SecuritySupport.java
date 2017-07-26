/*
 * Copyright 2007 Sun Microsystems, Inc.
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
package com.rometools.propono.atom.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/**
 * This class is duplicated for each subpackage, it is package private and therefore is not exposed
 * as part of the public API.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
class SecuritySupport {

    ClassLoader getContextClassLoader() {
        final PrivilegedAction<ClassLoader> action = new PrivilegedAction<ClassLoader>() {
            @Override
            public ClassLoader run() {
                ClassLoader cl = null;
                try {
                    cl = Thread.currentThread().getContextClassLoader();
                } catch (final SecurityException ex) {
                }
                return cl;
            }
        };
        return AccessController.doPrivileged(action);
    }

    String getSystemProperty(final String propName) {
        final PrivilegedAction<String> action = new PrivilegedAction<String>() {
            @Override
            public String run() {
                return System.getProperty(propName);
            }
        };
        return AccessController.doPrivileged(action);
    }

    FileInputStream getFileInputStream(final File file) throws FileNotFoundException {
        try {
            final PrivilegedExceptionAction<FileInputStream> action = new PrivilegedExceptionAction<FileInputStream>() {
                @Override
                public FileInputStream run() throws FileNotFoundException {
                    return new FileInputStream(file);
                }
            };
            return AccessController.doPrivileged(action);
        } catch (final PrivilegedActionException e) {
            throw (FileNotFoundException) e.getException();
        }
    }

    InputStream getResourceAsStream(final ClassLoader cl, final String name) {
        final PrivilegedAction<InputStream> action = new PrivilegedAction<InputStream>() {
            @Override
            public InputStream run() {
                InputStream ris;
                if (cl == null) {
                    ris = ClassLoader.getSystemResourceAsStream(name);
                } else {
                    ris = cl.getResourceAsStream(name);
                }
                return ris;
            }
        };
        return AccessController.doPrivileged(action);
    }

    boolean doesFileExist(final File f) {
        final PrivilegedAction<Boolean> action = new PrivilegedAction<Boolean>() {
            @Override
            public Boolean run() {
                return f.exists();
            }
        };
        return AccessController.doPrivileged(action).booleanValue();
    }

}
