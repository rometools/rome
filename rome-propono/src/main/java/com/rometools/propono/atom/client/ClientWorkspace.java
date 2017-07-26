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

import java.util.List;

import org.jdom2.Element;

import com.rometools.propono.atom.common.AtomService;
import com.rometools.propono.atom.common.Workspace;
import com.rometools.propono.utils.ProponoException;

/**
 * Represents Atom protocol workspace on client-side. It extends the common
 * {@link com.rometools.rome.propono.atom.common.Workspace} to return
 * {@link com.rometools.rome.propono.atom.client.ClientCollection} objects instead of common
 * {@link com.rometools.rome.propono.atom.common.Collection}s.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class ClientWorkspace extends Workspace {

    private ClientAtomService atomService = null;

    ClientWorkspace(final Element e, final ClientAtomService atomService, final String baseURI) throws ProponoException {
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
    protected void parseWorkspaceElement(final Element element, final String baseURI) throws ProponoException {
        final Element titleElem = element.getChild("title", AtomService.ATOM_FORMAT);
        setTitle(titleElem.getText());
        if (titleElem.getAttribute("type", AtomService.ATOM_FORMAT) != null) {
            setTitleType(titleElem.getAttribute("type", AtomService.ATOM_FORMAT).getValue());
        }
        final List<Element> collections = element.getChildren("collection", AtomService.ATOM_PROTOCOL);
        for (final Element e : collections) {
            addCollection(new ClientCollection(e, this, baseURI));
        }
    }

}
