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

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.module.ModuleImpl;
import org.rometools.feed.module.opensearch.OpenSearchModule;
import org.rometools.feed.module.opensearch.entity.OSQuery;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Michael W. Nassif (enrouteinc@gmail.com)
 * OpenSearch Module implementation
 */
public class OpenSearchModuleImpl extends ModuleImpl implements OpenSearchModule, Serializable {
    private int totalResults = -1;
    private int startIndex = 1;
    private int itemsPerPage = -1;
    private Link link;
    private List queries;

    public OpenSearchModuleImpl() {
        super(OpenSearchModuleImpl.class, OpenSearchModuleImpl.URI);
    }

    /**
     * @return Returns the itemsPerPage.
     */
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * @param itemsPerPage The itemsPerPage to set.
     */
    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * @return Returns the link.
     */
    public Link getLink() {
        return link;
    }

    /**
     * @param link The link to set.
     */
    public void setLink(Link link) {
        this.link = link;
    }

    
    /**
     * @return Returns the queries.
     */
    public List getQueries() {
        this.queries = (queries == null) ? new LinkedList() : queries;

        return this.queries;
    }

    /**
     * @param queries The queries to set.
     */
    public void setQueries(List queries) {
        this.queries = queries;
    }

    public void addQuery(OSQuery query) {
        if (queries != null) {
            queries.add(query);
        } else {
            queries = new LinkedList();
            queries.add(query);
        }
    }

    /**
     * @return Returns the startIndex.
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * @param startIndex The startIndex to set.
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @return Returns the totalResults.
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The totalResults to set.
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.feed.CopyFrom#copyFrom(java.lang.Object)
     */
    public void copyFrom(CopyFrom obj) {
        OpenSearchModule osm = (OpenSearchModuleImpl) obj;

        setTotalResults(osm.getTotalResults());
        setItemsPerPage(osm.getItemsPerPage());
        setStartIndex(osm.getStartIndex());
        setLink(osm.getLink());

        // setQueries(osm.getQueries());
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.feed.CopyFrom#getInterface()
     */
    public Class getInterface() {
        // TODO Auto-generated method stub
        return OpenSearchModule.class;
    }
}
