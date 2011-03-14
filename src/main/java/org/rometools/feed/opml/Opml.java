/*
 * Opml.java
 *
 * Created on April 24, 2006, 11:00 PM
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
package org.rometools.feed.opml;

import com.sun.syndication.feed.WireFeed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * This class represents the root of an OPML 1/2 feed and contains the elements that 
 * may appear in the &lt;head&gt; tag of the feed.
 * @author <a href="mailto:cooper@screaming-penguin.com"> Robert "kebernet" Cooper</a>
 */
public class Opml extends WireFeed {
    private Date _created;
    private Date _modified;
    private Integer _verticalScrollState;
    private Integer _windowBottom;
    private Integer _windowLeft;
    private Integer _windowRight;
    private Integer _windowTop;
    private List _outlines;
    private String _docs;
    private String _ownerEmail;
    private String _ownerId;
    private String _ownerName;
    private String _title;
    private int[] _expansionState;

    /** Creates a new instance of Opml */
    public Opml() {
        super();
    }

    /**
     * <dateCreated> is a date-time, indicating when the document was created.
     * @param created date-time, indicating when the document was created.
     */
    public void setCreated(Date created) {
        this._created = created;
    }

    /**
     * &lt;dateCreated&gt; is a date-time, indicating when the document was created.
     * @return date-time, indicating when the document was created.
     */
    public Date getCreated() {
        return _created;
    }

    /**
     * (OPML 2) &lt;docs&gt; is the http address of documentation for the format used in the OPML file. It's probably a pointer to <a href="http://www.opml.org/spec2">this page</a> for people who might stumble across the file on a web server 25 years from now and wonder what it is.
     * @param docs http address of documentation for the format used
     */
    public void setDocs(String docs) {
        this._docs = docs;
    }

    /**
     * (OPML 2) &lt;docs&gt; is the http address of documentation for the format used in the OPML file. It's probably a pointer to <a href="http://www.opml.org/spec2">this page</a> for people who might stumble across the file on a web server 25 years from now and wonder what it is.
     * @return http address of documentation for the format used
     */
    public String getDocs() {
        return _docs;
    }

    /**
     * &lt;expansionState&gt;is a comma-separated list of line numbers that are expanded. The line numbers in the list tell you which headlines to expand. The order is important. For each element in the list, X, starting at the first summit, navigate flatdown X times and expand. Repeat for each element in the list.
     * @param expansionState int array containing expanded elements.
     */
    public void setExpansionState(int[] expansionState) {
        this._expansionState = expansionState;
    }

    /**
     * &lt;expansionState&gt; is a comma-separated list of line numbers that are expanded. The line numbers in the list tell you which headlines to expand. The order is important. For each element in the list, X, starting at the first summit, navigate flatdown X times and expand. Repeat for each element in the list.
     * @return int array containing expanded elements.
     */
    public int[] getExpansionState() {
        return _expansionState;
    }

    /**
     * &lt;dateModified&gt; is a date-time, indicating when the document was last modified.
     * @param modified date-time, indicating when the document was last modified.
     */
    public void setModified(Date modified) {
        this._modified = modified;
    }

    /**
     * &lt;dateModified&gt; is a date-time, indicating when the document was last modified.
     * @return date-time, indicating when the document was last modified.
     */
    public Date getModified() {
        return _modified;
    }

    /**
     * Root level Outline object that should appear in the &lt;body&gt;
     * @param outlines Root level Outline object that should appear in the &lt;body&gt;
     */
    public void setOutlines(List outlines) {
        this._outlines = outlines;
    }

    /**
     * Root level Outline object that should appear in the &lt;body&gt;
     * @return Root level Outline object that should appear in the &lt;body&gt;
     */
    public List getOutlines() {
        if (_outlines == null) {
            _outlines = new ArrayList();
        }

        return _outlines;
    }

    /**
     * 	&lt;ownerEmail&gt; is a string, the email address of the owner of the document.
     * @param ownerEmail the email address of the owner of the document.
     */
    public void setOwnerEmail(String ownerEmail) {
        this._ownerEmail = ownerEmail;
    }

    /**
     * 	&lt;ownerEmail&gt; is a string, the email address of the owner of the document.
     * @return the email address of the owner of the document.
     */
    public String getOwnerEmail() {
        return _ownerEmail;
    }

    /**
     * (OPML 2) 	&lt;ownerId&gt; is the http address of a web page that contains <strike>an HTML</strike> a form that allows a human reader to communicate with the author of the document via email or other means.
     * @param ownerId http address of a web page that contains <strike>an HTML</strike> a form that allows a human reader to communicate with the author of the document via email or other means.
     */
    public void setOwnerId(String ownerId) {
        this._ownerId = ownerId;
    }

    /**
     * (OPML 2) &lt;ownerId&gt; is the http address of a web page that contains <strike>an HTML</strike> a form that allows a human reader to communicate with the author of the document via email or other means.
     * @return http address of a web page that contains <strike>an HTML</strike> a form that allows a human reader to communicate with the author of the document via email or other means.
     */
    public String getOwnerId() {
        return _ownerId;
    }

    /**
     * 	&lt;ownerName&gt; is a string, the owner of the document.
     * @param ownerName the owner of the document.
     */
    public void setOwnerName(String ownerName) {
        this._ownerName = ownerName;
    }

    /**
     * 	&lt;ownerName&gt; is a string, the owner of the document.
     * @return the owner of the document.
     */
    public String getOwnerName() {
        return _ownerName;
    }

    /**
     * 	&lt;title&gt; is the title of the document.
     * @param title title of the document.
     */
    public void setTitle(String title) {
        this._title = title;
    }

    /**
     * 	&lt;title&gt; is the title of the document.
     * @return title of the document.
     */
    public String getTitle() {
        return _title;
    }

    /**
     * 	&lt;vertScrollState&gt; is a number, saying which line of the outline is displayed on the top line of the window. This number is calculated with the expansion state already applied.
     * @param verticalScrollState which line of the outline is displayed on the top line of the window.
     */
    public void setVerticalScrollState(Integer verticalScrollState) {
        this._verticalScrollState = verticalScrollState;
    }

    /**
     * 	&lt;vertScrollState&gt; is a number, saying which line of the outline is displayed on the top line of the window. This number is calculated with the expansion state already applied.
     * @return  which line of the outline is displayed on the top line of the window. This number is calculated with the expansion state already applied.
     */
    public Integer getVerticalScrollState() {
        return _verticalScrollState;
    }

    /**
     * &lt;windowBottom&gt; is a number, the pixel location of the bottom edge of the window.
     * @param windowBottom the pixel location of the bottom edge of the window.
     */
    public void setWindowBottom(Integer windowBottom) {
        this._windowBottom = windowBottom;
    }

    /**
     * &lt;windowBottom&gt; is a number, the pixel location of the bottom edge of the window.
     * @return  the pixel location of the bottom edge of the window.
     */
    public Integer getWindowBottom() {
        return _windowBottom;
    }

    /**
     * &lt;windowLeft&gt; is a number, the pixel location of the left edge of the window.
     * @param windowLeft the pixel location of the left edge of the window.
     */
    public void setWindowLeft(Integer windowLeft) {
        this._windowLeft = windowLeft;
    }

    /**
     * &lt;windowLeft&gt; is a number, the pixel location of the left edge of the window.
     * @return the pixel location of the left edge of the window.
     */
    public Integer getWindowLeft() {
        return _windowLeft;
    }

    /**
     * &lt;windowRight&gt; is a number, the pixel location of the right edge of the window.
     * @param windowRight the pixel location of the right edge of the window.
     */
    public void setWindowRight(Integer windowRight) {
        this._windowRight = windowRight;
    }

    /**
     * &lt;windowRight&gt; is a number, the pixel location of the right edge of the window.
     * @return the pixel location of the right edge of the window.
     */
    public Integer getWindowRight() {
        return _windowRight;
    }

    /**
     * &lt;windowTop&gt; is a number, the pixel location of the top edge of the window.
     * @param windowTop the pixel location of the top edge of the window.
     */
    public void setWindowTop(Integer windowTop) {
        this._windowTop = windowTop;
    }

    /**
     * &lt;windowTop&gt; is a number, the pixel location of the top edge of the window.
     * @return the pixel location of the top edge of the window.
     */
    public Integer getWindowTop() {
        return _windowTop;
    }
}
