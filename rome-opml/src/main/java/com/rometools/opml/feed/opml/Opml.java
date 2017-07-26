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
package com.rometools.opml.feed.opml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rometools.rome.feed.WireFeed;

/**
 * This class represents the root of an OPML 1/2 feed and contains the elements that may appear in the &lt;head&gt; tag
 * of the feed.
 */
public class Opml extends WireFeed {

    private static final long serialVersionUID = 1L;

    private Date created;
    private Date modified;
    private Integer verticalScrollState;
    private Integer windowBottom;
    private Integer windowLeft;
    private Integer windowRight;
    private Integer windowTop;
    private List<Outline> outlines;
    private String docs;
    private String ownerEmail;
    private String ownerId;
    private String ownerName;
    private String title;
    private int[] expansionState;

    /**
     * <dateCreated> is a date-time, indicating when the document was created.
     *
     * @param created date-time, indicating when the document was created.
     */
    public void setCreated(final Date created) {
        this.created = created;
    }

    /**
     * &lt;dateCreated&gt; is a date-time, indicating when the document was created.
     *
     * @return date-time, indicating when the document was created.
     */
    public Date getCreated() {
        return created;
    }

    /**
     * (OPML 2) &lt;docs&gt; is the http address of documentation for the format used in the OPML file. It's probably a
     * pointer to <a href="http://www.opml.org/spec2">this page</a> for people who might stumble across the file on a
     * web server 25 years from now and wonder what it is.
     *
     * @param docs http address of documentation for the format used
     */
    public void setDocs(final String docs) {
        this.docs = docs;
    }

    /**
     * (OPML 2) &lt;docs&gt; is the http address of documentation for the format used in the OPML file. It's probably a
     * pointer to <a href="http://www.opml.org/spec2">this page</a> for people who might stumble across the file on a
     * web server 25 years from now and wonder what it is.
     *
     * @return http address of documentation for the format used
     */
    public String getDocs() {
        return docs;
    }

    /**
     * &lt;expansionState&gt;is a comma-separated list of line numbers that are expanded. The line numbers in the list
     * tell you which headlines to expand. The order is important. For each element in the list, X, starting at the
     * first summit, navigate flatdown X times and expand. Repeat for each element in the list.
     *
     * @param expansionState int array containing expanded elements.
     */
    public void setExpansionState(final int[] expansionState) {
        this.expansionState = expansionState;
    }

    /**
     * &lt;expansionState&gt; is a comma-separated list of line numbers that are expanded. The line numbers in the list
     * tell you which headlines to expand. The order is important. For each element in the list, X, starting at the
     * first summit, navigate flatdown X times and expand. Repeat for each element in the list.
     *
     * @return int array containing expanded elements.
     */
    public int[] getExpansionState() {
        return expansionState;
    }

    /**
     * &lt;dateModified&gt; is a date-time, indicating when the document was last modified.
     *
     * @param modified date-time, indicating when the document was last modified.
     */
    public void setModified(final Date modified) {
        this.modified = modified;
    }

    /**
     * &lt;dateModified&gt; is a date-time, indicating when the document was last modified.
     *
     * @return date-time, indicating when the document was last modified.
     */
    public Date getModified() {
        return modified;
    }

    /**
     * Root level Outline object that should appear in the &lt;body&gt;
     *
     * @param outlines Root level Outline object that should appear in the &lt;body&gt;
     */
    public void setOutlines(final List<Outline> outlines) {
        this.outlines = outlines;
    }

    /**
     * Root level Outline object that should appear in the &lt;body&gt;
     *
     * @return Root level Outline object that should appear in the &lt;body&gt;
     */
    public List<Outline> getOutlines() {
        if (outlines == null) {
            outlines = new ArrayList<Outline>();
        }

        return outlines;
    }

    /**
     * &lt;ownerEmail&gt; is a string, the email address of the owner of the document.
     *
     * @param ownerEmail the email address of the owner of the document.
     */
    public void setOwnerEmail(final String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    /**
     * &lt;ownerEmail&gt; is a string, the email address of the owner of the document.
     *
     * @return the email address of the owner of the document.
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * (OPML 2) &lt;ownerId&gt; is the http address of a web page that contains <strike>an HTML</strike> a form that
     * allows a human reader to communicate with the author of the document via email or other means.
     *
     * @param ownerId http address of a web page that contains <strike>an HTML</strike> a form that allows a human
     *            reader to communicate with the author of the document via email or other means.
     */
    public void setOwnerId(final String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * (OPML 2) &lt;ownerId&gt; is the http address of a web page that contains <strike>an HTML</strike> a form that
     * allows a human reader to communicate with the author of the document via email or other means.
     *
     * @return http address of a web page that contains <strike>an HTML</strike> a form that allows a human reader to
     *         communicate with the author of the document via email or other means.
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * &lt;ownerName&gt; is a string, the owner of the document.
     *
     * @param ownerName the owner of the document.
     */
    public void setOwnerName(final String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * &lt;ownerName&gt; is a string, the owner of the document.
     *
     * @return the owner of the document.
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * &lt;title&gt; is the title of the document.
     *
     * @param title title of the document.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * &lt;title&gt; is the title of the document.
     *
     * @return title of the document.
     */
    public String getTitle() {
        return title;
    }

    /**
     * &lt;vertScrollState&gt; is a number, saying which line of the outline is displayed on the top line of the window.
     * This number is calculated with the expansion state already applied.
     *
     * @param verticalScrollState which line of the outline is displayed on the top line of the window.
     */
    public void setVerticalScrollState(final Integer verticalScrollState) {
        this.verticalScrollState = verticalScrollState;
    }

    /**
     * &lt;vertScrollState&gt; is a number, saying which line of the outline is displayed on the top line of the window.
     * This number is calculated with the expansion state already applied.
     *
     * @return which line of the outline is displayed on the top line of the window. This number is calculated with the
     *         expansion state already applied.
     */
    public Integer getVerticalScrollState() {
        return verticalScrollState;
    }

    /**
     * &lt;windowBottom&gt; is a number, the pixel location of the bottom edge of the window.
     *
     * @param windowBottom the pixel location of the bottom edge of the window.
     */
    public void setWindowBottom(final Integer windowBottom) {
        this.windowBottom = windowBottom;
    }

    /**
     * &lt;windowBottom&gt; is a number, the pixel location of the bottom edge of the window.
     *
     * @return the pixel location of the bottom edge of the window.
     */
    public Integer getWindowBottom() {
        return windowBottom;
    }

    /**
     * &lt;windowLeft&gt; is a number, the pixel location of the left edge of the window.
     *
     * @param windowLeft the pixel location of the left edge of the window.
     */
    public void setWindowLeft(final Integer windowLeft) {
        this.windowLeft = windowLeft;
    }

    /**
     * &lt;windowLeft&gt; is a number, the pixel location of the left edge of the window.
     *
     * @return the pixel location of the left edge of the window.
     */
    public Integer getWindowLeft() {
        return windowLeft;
    }

    /**
     * &lt;windowRight&gt; is a number, the pixel location of the right edge of the window.
     *
     * @param windowRight the pixel location of the right edge of the window.
     */
    public void setWindowRight(final Integer windowRight) {
        this.windowRight = windowRight;
    }

    /**
     * &lt;windowRight&gt; is a number, the pixel location of the right edge of the window.
     *
     * @return the pixel location of the right edge of the window.
     */
    public Integer getWindowRight() {
        return windowRight;
    }

    /**
     * &lt;windowTop&gt; is a number, the pixel location of the top edge of the window.
     *
     * @param windowTop the pixel location of the top edge of the window.
     */
    public void setWindowTop(final Integer windowTop) {
        this.windowTop = windowTop;
    }

    /**
     * &lt;windowTop&gt; is a number, the pixel location of the top edge of the window.
     *
     * @return the pixel location of the top edge of the window.
     */
    public Integer getWindowTop() {
        return windowTop;
    }

}
