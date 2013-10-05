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

import com.sun.syndication.io.impl.Atom10Parser;
import org.rometools.propono.utils.ProponoException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Element;


/**
 * Models an Atom workspace collection.    
 */
public class Collection {
    
    public static final String ENTRY_TYPE = "application/atom+xml;type=entry";
    
    private Element collectionElement = null;
    private String baseURI = null;
    private String title = null;
    private String titleType = null; // may be TEXT, HTML, XHTML
    private List accepts = new ArrayList(); // of Strings
    private String listTemplate = null;
    private String href = null;
    private List categories = new ArrayList(); // of Categories objects    
    
    /**
     * Collection MUST have title and href.
     * @param title    Title for collection
     * @param titleType Content type of title (null for plain text)
     * @param href     Collection URI.
     */
    public Collection(String title, String titleType, String href) {
        this.title = title;
        this.titleType = titleType;
        this.href = href;
    }

    /** Load self from XML element */
    public Collection(Element e) throws ProponoException {
        this.collectionElement = e;
        this.parseCollectionElement(e); 
    }
    
    /** Load self from XML element and base URI for resolving relative URIs */
    public Collection(Element e, String baseURI) throws ProponoException {
        this.collectionElement = e;
        this.baseURI = baseURI;
        this.parseCollectionElement(e); 
    }
    
    /**
     * List of content-type ranges accepted by collection.
     */
    public List getAccepts() {
        return accepts;
    }
    
    public void addAccept(String accept) {
        this.accepts.add(accept);
    }
    
    public void setAccepts(List accepts) {
        this.accepts = accepts;
    }
    
    /** The URI of the collection */
    public String getHref() {
        return href;
    }
    
    /**
     * Set URI of collection
     */
    public void setHref(String href) {
        this.href = href;
    }
    
    /** Get resolved URI of the collection, or null if impossible to determine */
    public String getHrefResolved() {
        if (Atom10Parser.isAbsoluteURI(href)) {
            return href;        
        } else if (baseURI != null && collectionElement != null) {
            int lastslash = baseURI.lastIndexOf("/");
            return Atom10Parser.resolveURI(baseURI.substring(0, lastslash), collectionElement, href);
        }
        return null;
    }
        
    /** Get resolved URI using collection's baseURI, or null if impossible to determine */
    public String getHrefResolved(String relativeUri) {
        if (Atom10Parser.isAbsoluteURI(relativeUri)) {
            return relativeUri;        
        } else if (baseURI != null && collectionElement != null) {
            int lastslash = baseURI.lastIndexOf("/");
            return Atom10Parser.resolveURI(baseURI.substring(0, lastslash), collectionElement, relativeUri);
        }
        return null;
    }
        
    /** Must have human readable title */
    public String getTitle() {
        return title;
    }
    
    /**
     * Set title of collection.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Type of title ("text", "html" or "xhtml")
     */
    public String getTitleType() {
        return titleType;
    }

    /**
     * Type of title ("text", "html" or "xhtml")
     */
    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    /** Workspace can have multiple Categories objects */
    public void addCategories(Categories cats) {
        categories.add(cats);
    }
    
    /**
     * Get categories allowed by collection.
     * @return Collection of {@link com.sun.syndication.propono.atom.common.Categories} objects.
     */
    public List getCategories() {
        return categories;
    }
    
    /**
     * Returns true if contentType is accepted by collection.
     */
    public boolean accepts(String ct) {
        for (Iterator it = accepts.iterator(); it.hasNext();) {
            String accept = (String)it.next();
            if (accept != null && accept.trim().equals("*/*")) return true;
            String entryType = "application/atom+xml";
            boolean entry = entryType.equals(ct);
            if (entry && null == accept) {
                return true;
            } else if (entry && "entry".equals(accept)) {
                return true;
            } else if (entry && entryType.equals(accept)) {
                return true;
            } else {
                String[] rules = (String[])accepts.toArray(new String[accepts.size()]);
                for (int i=0; i<rules.length; i++) {
                    String rule = rules[i].trim();
                    if (rule.equals(ct)) return true;
                    int slashstar = rule.indexOf("/*");
                    if (slashstar > 0) {
                        rule = rule.substring(0, slashstar + 1);
                        if (ct.startsWith(rule)) return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Serialize an AtomService.Collection into an XML element
     */
    public Element collectionToElement() {
        Collection collection = this;
        Element element = new Element("collection", AtomService.ATOM_PROTOCOL);
        element.setAttribute("href", collection.getHref());
                       
        Element titleElem = new Element("title", AtomService.ATOM_FORMAT);
        titleElem.setText(collection.getTitle());
        if (collection.getTitleType() != null && !collection.getTitleType().equals("TEXT")) {
            titleElem.setAttribute("type", collection.getTitleType(), AtomService.ATOM_FORMAT);
        }
        element.addContent(titleElem);
        
        // Loop to create <app:categories> elements            
        for (Iterator it = collection.getCategories().iterator(); it.hasNext();) {
            Categories cats = (Categories)it.next();
            element.addContent(cats.categoriesToElement());          
        }
        
        for (Iterator it = collection.getAccepts().iterator(); it.hasNext();) {
            String range = (String)it.next();
            Element acceptElem = new Element("accept", AtomService.ATOM_PROTOCOL);
            acceptElem.setText(range);
            element.addContent(acceptElem);
        }
        
        return element;
    }
    
    /** Deserialize an Atom service collection XML element into an object */
    public Collection elementToCollection(Element element) throws ProponoException {
        return new Collection(element);
    }
    
    protected void parseCollectionElement(Element element) throws ProponoException {
        setHref(element.getAttribute("href").getValue());
        
        Element titleElem = element.getChild("title", AtomService.ATOM_FORMAT);
        if (titleElem != null) {
            setTitle(titleElem.getText());
            if (titleElem.getAttribute("type", AtomService.ATOM_FORMAT) != null) {
                setTitleType(titleElem.getAttribute("type", AtomService.ATOM_FORMAT).getValue());
            }
        }
        
        List acceptElems = element.getChildren("accept",  AtomService.ATOM_PROTOCOL);
        if (acceptElems != null && acceptElems.size() > 0) {
            for (Iterator it = acceptElems.iterator(); it.hasNext();) {
                Element acceptElem = (Element)it.next();
                addAccept(acceptElem.getTextTrim());
            }
        }
        
        // Loop to parse <app:categories> element to Categories objects
        List catsElems = element.getChildren("categories", AtomService.ATOM_PROTOCOL);
        for (Iterator catsIter = catsElems.iterator(); catsIter.hasNext();) {
            Element catsElem = (Element) catsIter.next();  
            Categories cats = new Categories(catsElem, baseURI);
            addCategories(cats);
        }
    } 
}

