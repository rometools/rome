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
package com.sun.syndication.propono.atom.client;

import com.sun.syndication.propono.utils.ProponoException;
import com.sun.syndication.io.impl.Atom10Parser;


/**
 * Creates AtomService or ClientCollection based on username, password and 
 * end-point URI of Atom protocol service.
 */
public class AtomClientFactory {
    
    static {
        Atom10Parser.setResolveURIs(true);
    }
    
    /**
     * Create AtomService by reading service doc from Atom Server.
     */
    public static ClientAtomService getAtomService(
            String uri, AuthStrategy authStrategy) throws ProponoException {
        return new ClientAtomService(uri, authStrategy);
    }

    /**
     * Create ClientCollection bound to URI.
     */
    public static ClientCollection getCollection(
            String uri, AuthStrategy authStrategy) throws ProponoException {
        return new ClientCollection(uri, authStrategy);
    }
}
