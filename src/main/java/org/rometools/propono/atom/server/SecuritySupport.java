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
package org.rometools.propono.atom.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/**
 * This class is duplicated for each subpackage, it is package private and therefore is not exposed as part of the public API.
 */
class SecuritySupport {

    ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() {
            @Override
            public Object run() {
                ClassLoader cl = null;
                try {
                    cl = Thread.currentThread().getContextClassLoader();
                } catch (final SecurityException ex) {
                }
                return cl;
            }
        });
    }

    String getSystemProperty(final String propName) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() {
            @Override
            public Object run() {
                return System.getProperty(propName);
            }
        });
    }

    FileInputStream getFileInputStream(final File file) throws FileNotFoundException {
        try {
            return (FileInputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                @Override
                public Object run() throws FileNotFoundException {
                    return new FileInputStream(file);
                }
            });
        } catch (final PrivilegedActionException e) {
            throw (FileNotFoundException) e.getException();
        }
    }

    InputStream getResourceAsStream(final ClassLoader cl, final String name) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() {
            @Override
            public Object run() {
                InputStream ris;
                if (cl == null) {
                    ris = ClassLoader.getSystemResourceAsStream(name);
                } else {
                    ris = cl.getResourceAsStream(name);
                }
                return ris;
            }
        });
    }

    boolean doesFileExist(final File f) {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction() {
            @Override
            public Object run() {
                return new Boolean(f.exists());
            }
        })).booleanValue();
    }

}
