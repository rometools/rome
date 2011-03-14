/*
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
 *
 */

package org.rometools.feed.module.opensearch.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.module.Module;
import org.rometools.feed.module.opensearch.OpenSearchModule;
import org.rometools.feed.module.opensearch.RequiredAttributeMissingException;
import org.rometools.feed.module.opensearch.entity.OSQuery;
import com.sun.syndication.io.ModuleGenerator;

/**
 * @author Michael W. Nassif (enrouteinc@gmail.com)
 * OpenSearch implementation of ModuleGenerator
 */
public class OpenSearchModuleGenerator  implements ModuleGenerator {
	
	private static final Namespace OS_NS  = Namespace.getNamespace("opensearch", OpenSearchModule.URI);

    public String getNamespaceUri() {
        return OpenSearchModule.URI;
    }

    private static final Set NAMESPACES;

    static {
        Set nss = new HashSet();
        nss.add(OS_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    /**
     * Returns a set with all the URIs (JDOM Namespace elements) this module generator uses.
     * <p/>
     * It is used by the the feed generators to add their namespace definition in
     * the root element of the generated document (forward-missing of Java 5.0 Generics).
     * <p/>
     *
     * @return a set with all the URIs (JDOM Namespace elements) this module generator uses.
     */
    public Set getNamespaces() {
        return NAMESPACES;
    }

    public void generate(Module module, Element element) {

        OpenSearchModule osm = (OpenSearchModule)module;

        if(osm.getItemsPerPage() > -1){
        	element.addContent(generateSimpleElement("itemsPerPage", Integer.toString(osm.getItemsPerPage())));
        }

        if(osm.getTotalResults() > -1){
            element.addContent(generateSimpleElement("totalResults", Integer.toString(osm.getTotalResults())));
        }
            
        int startIndex = (osm.getStartIndex() > 0)?osm.getStartIndex():1;
        element.addContent(generateSimpleElement("startIndex",Integer.toString(startIndex)));

        if(osm.getQueries() != null){
        	
        	List queries = osm.getQueries();
        	
        	for (Iterator iter = queries.iterator(); iter.hasNext();) {
				OSQuery query = (OSQuery) iter.next();
				if(query != null){
  				    element.addContent(generateQueryElement(query));
				}
			}
        }        	
        
        if(osm.getLink() != null){
        	element.addContent(generateLinkElement(osm.getLink()));
        }
    }

    protected Element generateQueryElement(OSQuery query) {
    	
        Element qElement = new Element("Query", OS_NS);
        
        if (query.getRole() != null) {
            Attribute roleAttribute = new Attribute("role", query.getRole());
            qElement.setAttribute(roleAttribute);
        }
        else{
        	throw new RequiredAttributeMissingException("If declaring a Query element, the field 'role' must be be specified");
        }

        if(query.getOsd() != null){
        	Attribute osd = new Attribute("osd", query.getOsd());
            qElement.setAttribute(osd);
        }
        
        if(query.getSearchTerms() != null){
        	Attribute searchTerms = new Attribute("searchTerms", query.getSearchTerms());
            qElement.setAttribute(searchTerms);
        }
        
        if(query.getStartPage() > -1){
        	int startPage = (query.getStartPage() != 0)?query.getStartPage():1;
        	Attribute sp = new Attribute("startPage", Integer.toString(startPage));
            qElement.setAttribute(sp);
        }
        
        if(query.getTitle() != null){
        	qElement.setAttribute(new Attribute("title", query.getTitle()));
        }
 
        if(query.getTotalResults() > -1){
        	qElement.setAttribute(new Attribute("totalResults", Integer.toString(query.getTotalResults())));
        }
        
        return qElement;
    }
    
    protected Element generateLinkElement(Link link) {
        Element linkElement = new Element("link", OS_NS);

        if (link.getRel() != null) {
            Attribute relAttribute = new Attribute("rel", "search");
            linkElement.setAttribute(relAttribute);
        }

        if (link.getType() != null) {
            Attribute typeAttribute = new Attribute("type", link.getType());
            linkElement.setAttribute(typeAttribute);
        }

        if (link.getHref() != null) {
            Attribute hrefAttribute = new Attribute("href", link.getHref());
            linkElement.setAttribute(hrefAttribute);
        }
        
        if (link.getHreflang() != null) {
            Attribute hreflangAttribute = new Attribute("hreflang", link.getHreflang());
            linkElement.setAttribute(hreflangAttribute);
        }
        return linkElement;
    }

    protected Element generateSimpleElement(String name, String value)  {

        Element element = new Element(name, OS_NS);
        element.addContent(value);

        return element;
    }

}
