/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.rometools.modules.opensearch.impl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.opensearch.OpenSearchModule;
import com.rometools.modules.opensearch.entity.OSQuery;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.ModuleImpl;

public class OpenSearchModuleImpl extends ModuleImpl implements OpenSearchModule, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(OpenSearchModuleImpl.class);
    private int totalResults = -1;
    private int startIndex = 1;
    private int itemsPerPage = -1;
    private Link link;
    private List<OSQuery> queries;

    public OpenSearchModuleImpl() {
        super(OpenSearchModuleImpl.class, OpenSearchModule.URI);
    }

    /**
     * @return Returns the itemsPerPage.
     */
    @Override
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * @param itemsPerPage The itemsPerPage to set.
     */
    @Override
    public void setItemsPerPage(final int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * @return Returns the link.
     */
    @Override
    public Link getLink() {
        return link;
    }

    /**
     * @param link The link to set.
     */
    @Override
    public void setLink(final Link link) {
        this.link = link;
    }

    /**
     * @return Returns the queries.
     */
    @Override
    public List<OSQuery> getQueries() {
        if (queries == null) {
            queries = new LinkedList<OSQuery>();
        }
        return queries;
    }

    /**
     * @param queries The queries to set.
     */
    @Override
    public void setQueries(final List<OSQuery> queries) {
        this.queries = queries;
    }

    @Override
    public void addQuery(final OSQuery query) {
        getQueries().add(query);
    }

    /**
     * @return Returns the startIndex.
     */
    @Override
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * @param startIndex The startIndex to set.
     */
    @Override
    public void setStartIndex(final int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @return Returns the totalResults.
     */
    @Override
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The totalResults to set.
     */
    @Override
    public void setTotalResults(final int totalResults) {
        this.totalResults = totalResults;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.rometools.rome.feed.CopyFrom#copyFrom(java.lang.Object)
     */
    @Override
    public void copyFrom(final CopyFrom obj) {
        final OpenSearchModule osm = (OpenSearchModule) obj;
        setTotalResults(osm.getTotalResults());
        setItemsPerPage(osm.getItemsPerPage());
        setStartIndex(osm.getStartIndex());
        setLink(osm.getLink());
        for (final OSQuery q : osm.getQueries()) {
            try {
                getQueries().add((OSQuery) q.clone());
            } catch (final CloneNotSupportedException e) {
                LOG.error("Error", e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.rometools.rome.feed.CopyFrom#getInterface()
     */
    @Override
    public Class<OpenSearchModule> getInterface() {
        return OpenSearchModule.class;
    }
}
