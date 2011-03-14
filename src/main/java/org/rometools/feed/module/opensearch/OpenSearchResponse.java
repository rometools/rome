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

package org.rometools.feed.module.opensearch;

import java.util.List;

import com.sun.syndication.feed.atom.Link;
import org.rometools.feed.module.opensearch.entity.OSQuery;

/** Provides access to A9 Open Search information.
 * @author Michael W. Nassif (enrouteinc@gmail.com)
 */
public interface OpenSearchResponse{

    /**
     * #  totalResults – the maximum number of results available for these search terms
     * 
     *    * Restrictions: An integer greater than or equal to 0.
     *    * Default: The number of items that were returned in this set of results.
     *    * Requirements: May appear zero or one time.
     * @param totalResults A positive integer value.
     */
	public void setTotalResults(int totalResults);
	
    /**
     * #  totalResults – the maximum number of results available for these search terms
     * 
     *    * Restrictions: An integer greater than or equal to 0.
     *    * Default: The number of items that were returned in this set of results.
     *    * Requirements: May appear zero or one time.
     * @return a positive integer value.
     */
	public int getTotalResults();
	
    /**
     * #  startIndex – the index of the first item returned in the result.
     * 
     *    * Restrictions: An integer greater than or equal to 1.
     *    * Note: The first result is 1.
     *    * Default: 1
     *    * Requirements: May appear zero or one time.
     * @param startIndex int value >= 1.
     */
	public void setStartIndex(int startIndex);
	
    /**
     * #  startIndex – the index of the first item returned in the result.
     * 
     *    * Restrictions: An integer greater than or equal to 1.
     *    * Note: The first result is 1.
     *    * Default: 1
     *    * Requirements: May appear zero or one time.
     * @return int value >= 1.
     */
	public int getStartIndex();
	
    /**
     * #  itemsPerPage – the maximum number of items that can appear in one page of results.
     * 
     *    * Restrictions: An integer greater than or equal to 1.
     *    * Default: The number of items that were returned in this set of results.
     *    * Requirements: May appear zero or one time.
     * @param itemsPerPage int value >= 1.
     */
	public void setItemsPerPage(int itemsPerPage);
	
    /**
     * #  itemsPerPage – the maximum number of items that can appear in one page of results.
     * 
     *    * Restrictions: An integer greater than or equal to 1.
     *    * Default: The number of items that were returned in this set of results.
     *    * Requirements: May appear zero or one time.
     * @return int value >= 1
     */
	public int getItemsPerPage();
	
    /**
     * #  link – a reference back to the OpenSearch Description file
     * 
     *    * Attributes: This is a clone of the link element in Atom, including href, hreflang, rel, and type attributes.
     *    * Restrictions: The rel attribute must equal search.
     *    * Note: New in version 1.1.
     *    * Requirements: May appear zero or one time.
     * @param link link to the open search spec.
     */
	public void setLink(Link link);
	
    /**
     * #  link – a reference back to the OpenSearch Description file
     * 
     *    * Attributes: This is a clone of the link element in Atom, including href, hreflang, rel, and type attributes.
     *    * Restrictions: The rel attribute must equal search.
     *    * Note: New in version 1.1.
     *    * Requirements: May appear zero or one time.
     * @return link to the opensearch spec.
     */
	public Link getLink();
	
	// list of OSResponseQuery interfaces
    /**
     * <code>Query</code> – in an OpenSearch Response, can be used both to echo back the original query and to suggest new searches.
     * 	Please see the <a href="../query/">OpenSearch Query specification</a> for more information.
     *     <ul>
     *        <li>Note: <em>New in version 1.1.</em></li>
     *        <li>Requirements: May appear zero or more times. Note that the “Q” is capitalized.</li>
     * @param query List of OSQuery objects.
     */
	public void setQueries(List query);
	
    /**
     * <code>Query</code> – in an OpenSearch Response, can be used both to echo back the original query and to suggest new searches.
     * 	Please see the <a href="../query/">OpenSearch Query specification</a> for more information.
     *     <ul>
     *        <li>Note: <em>New in version 1.1.</em></li>
     *        <li>Requirements: May appear zero or more times. Note that the “Q” is capitalized.</li>
     * @return A list of OSQuery objects.
     */
	public List getQueries();
	
	// convenience method
    /**
     * Adds a query to the module.
     * @param query OSQuery object to add.
     */
	public void addQuery(OSQuery query);
}
