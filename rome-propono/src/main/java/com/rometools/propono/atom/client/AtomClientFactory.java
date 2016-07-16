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
package com.rometools.propono.atom.client;

import com.rometools.propono.utils.ProponoException;
import com.rometools.rome.io.impl.Atom10Parser;

/**
 * Creates AtomService or ClientCollection based on username, password and end-point URI of Atom
 * protocol service.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class AtomClientFactory {

    static {
        Atom10Parser.setResolveURIs(true);
    }

    private AtomClientFactory() {
    }

    /**
     * Create AtomService by reading service doc from Atom Server.
     */
    public static ClientAtomService getAtomService(final String uri, final AuthStrategy authStrategy) throws ProponoException {
        return new ClientAtomService(uri, authStrategy);
    }

    /**
     * Create ClientCollection bound to URI.
     */
    public static ClientCollection getCollection(final String uri, final AuthStrategy authStrategy) throws ProponoException {
        return new ClientCollection(uri, authStrategy);
    }
}
