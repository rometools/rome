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

package org.rometools.feed.module.opensearch.entity;

import java.io.Serializable;

import com.sun.syndication.feed.impl.ObjectBean;

/**
 * @author Michael W. Nassif (enrouteinc@gmail.com)
 * Class representation of the Query object (Response portion so far)
 */
public class OSQuery implements Cloneable, Serializable{

	ObjectBean _objBean = null;
	
	// role is required
	private String role;
	private int startPage = -1;
	private int totalResults = -1;
	private String osd;
	private String title;
	private String searchTerms;
	
    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     */
    public OSQuery() {
        _objBean = new ObjectBean(this.getClass(),this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException {
        return _objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals() method.
     * <p>
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    public boolean equals(Object other) {
        return _objBean.equals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * @return the hashcode of the bean object.
     *
     */
    public int hashCode() {
        return _objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * @return String representation for the object.
     *
     */
    public String toString() {
        return _objBean.toString();
    }
	
	/**
	 * @return Returns the osd.
	 */
	public String getOsd() {
		return osd;
	}
	/**
	 * Typically represents a url link to the os description file
	 * @param osd The osd to set.
	 */
	public void setOsd(String osd) {
		this.osd = osd;
	}
	/**
	 * @return Returns the role.
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role The role to set.
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return Returns the searchTerms.
	 */
	public String getSearchTerms() {
		return searchTerms;
	}
	/**
	 * @param searchTerms The searchTerms to set.
	 */
	public void setSearchTerms(String searchTerms) {
		this.searchTerms = searchTerms;
	}
	/**
	 * @return Returns the startPage.
	 */
	public int getStartPage() {
		return startPage;
	}
	/**
	 * @param startPage The startPage to set.
	 */
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
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

}
