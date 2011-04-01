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
package com.sun.syndication.propono.atom.server.impl;

import com.sun.syndication.propono.atom.server.AtomHandlerFactory;
import com.sun.syndication.propono.atom.server.AtomHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Extends {@link com.sun.syndication.propono.atom.server.AtomHandlerFactory} to create and return 
 * {@link com.sun.syndication.propono.atom.server.impl.FileBasedAtomHandler}.
 */
public class FileBasedAtomHandlerFactory extends AtomHandlerFactory {
    
    /**
     * Create new AtomHandler.
     */
    public AtomHandler newAtomHandler( 
            HttpServletRequest req, HttpServletResponse res ) {
        return new FileBasedAtomHandler(req);
    }    
}
      
