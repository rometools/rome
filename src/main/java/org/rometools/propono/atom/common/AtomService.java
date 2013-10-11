/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
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
package org.rometools.propono.atom.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.rometools.propono.utils.ProponoException;

/**
 * Models an Atom Publishing Protocol Service Document. Is able to read a Service document from a JDOM Document and to write Service document out as a JDOM
 * Document.
 */
public class AtomService {

    private List workspaces = new ArrayList();

    /** Namespace for Atom Syndication Format */
    public static Namespace ATOM_FORMAT = Namespace.getNamespace("atom", "http://www.w3.org/2005/Atom");

    /** Namespace for Atom Publishing Protocol */
    public static Namespace ATOM_PROTOCOL = Namespace.getNamespace("app", "http://www.w3.org/2007/app");

    /**
     * Create new and empty Atom service
     */
    public AtomService() {
    }

    /**
     * Add Workspace to service.
     */
    public void addWorkspace(final Workspace workspace) {
        workspaces.add(workspace);
    }

    /**
     * Get Workspaces available from service.
     */
    public List getWorkspaces() {
        return workspaces;
    }

    /**
     * Set Workspaces of service.
     */
    public void setWorkspaces(final List workspaces) {
        this.workspaces = workspaces;
    }

    /**
     * Find workspace by title.
     * 
     * @param title Match this title
     * @return Matching Workspace or null if none found.
     */
    public Workspace findWorkspace(final String title) {
        for (final Iterator it = workspaces.iterator(); it.hasNext();) {
            final Workspace ws = (Workspace) it.next();
            if (title.equals(ws.getTitle())) {
                return ws;
            }
        }
        return null;
    }

    /**
     * Deserialize an Atom service XML document into an object
     */
    public static AtomService documentToService(final Document document) throws ProponoException {
        final AtomService service = new AtomService();
        final Element root = document.getRootElement();
        final List spaces = root.getChildren("workspace", ATOM_PROTOCOL);
        final Iterator iter = spaces.iterator();
        while (iter.hasNext()) {
            final Element e = (Element) iter.next();
            service.addWorkspace(Workspace.elementToWorkspace(e));
        }
        return service;
    }

    /**
     * Serialize an AtomService object into an XML document
     */
    public Document serviceToDocument() {
        final AtomService service = this;

        final Document doc = new Document();
        final Element root = new Element("service", ATOM_PROTOCOL);
        doc.setRootElement(root);
        final Iterator iter = service.getWorkspaces().iterator();
        while (iter.hasNext()) {
            final Workspace space = (Workspace) iter.next();
            root.addContent(space.workspaceToElement());
        }
        return doc;
    }

}
