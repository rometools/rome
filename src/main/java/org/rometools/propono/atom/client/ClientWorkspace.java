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
package org.rometools.propono.atom.client;

import org.rometools.propono.atom.common.AtomService;
import org.rometools.propono.atom.common.Workspace;
import org.rometools.propono.atom.common.Workspace;
import org.rometools.propono.utils.ProponoException;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Element;


/**
 * Represents Atom protocol workspace on client-side.
 * It extends the common 
 * {@link com.sun.syndication.propono.atom.common.Workspace} 
 * to return 
 * {@link com.sun.syndication.propono.atom.client.ClientCollection} 
 * objects instead of common 
 * {@link com.sun.syndication.propono.atom.common.Collection}s.
 */
public class ClientWorkspace extends Workspace {
    private ClientAtomService atomService = null;
        
    ClientWorkspace(Element e, ClientAtomService atomService, String baseURI) throws ProponoException {
        super("dummy", "dummy"); 
        this.atomService = atomService;      
        parseWorkspaceElement(e, baseURI);   
    }       
                
    /**
     * Package access to parent service.
     */
    ClientAtomService getAtomService() {
        return atomService;
    }
    
    /** Deserialize a Atom workspace XML element into an object */
    protected void parseWorkspaceElement(Element element, String baseURI) throws ProponoException {       
        Element titleElem = element.getChild("title", AtomService.ATOM_FORMAT);
        setTitle(titleElem.getText());
        if (titleElem.getAttribute("type", AtomService.ATOM_FORMAT) != null) {
            setTitleType(titleElem.getAttribute("type", AtomService.ATOM_FORMAT).getValue());
        }       
        List collections = element.getChildren("collection", AtomService.ATOM_PROTOCOL);
        Iterator iter = collections.iterator();
        while (iter.hasNext()) {
            Element e = (Element) iter.next();
            addCollection(new ClientCollection(e, this, baseURI)); 
        }
    }   
}
