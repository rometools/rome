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
package com.rometools.propono.atom.server.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rometools.propono.atom.server.AtomHandler;
import com.rometools.propono.atom.server.AtomHandlerFactory;

/**
 * Extends {@link com.rometools.rome.propono.atom.server.AtomHandlerFactory} to create and return
 * {@link com.rometools.rome.propono.atom.server.impl.FileBasedAtomHandler}.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class FileBasedAtomHandlerFactory extends AtomHandlerFactory {

    /**
     * Create new AtomHandler.
     */
    @Override
    public AtomHandler newAtomHandler(final HttpServletRequest req, final HttpServletResponse res) {
        return new FileBasedAtomHandler(req);
    }

}
