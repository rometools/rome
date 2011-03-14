/*
 * Review.java
 *
 * Created on November 16, 2005, 1:58 PM
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rometools.feed.module.base;

import org.rometools.feed.module.base.types.FloatUnit;
import java.net.URL;

import java.util.Date;


/**
 * This is an interface for the GoogleBase plug in that exposes methods used for
 * critical reviews of other things.
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.1 $
 */
public interface Review extends GlobalInterface {
    /**
     * Author of the item.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="author"></a>author</b></font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *
     *  <td><font size="-1">
     *  Author of the item.</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *  <td><font size="-1"> &lt;g:author&gt;John Steinbeck&lt;/g:author&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *  <td><font size="-1">
     *  Reference Items, News and Articles</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  string</font></td>
     * </tr>
     * </tbody></table>
     * @param authors Author of the item.
     */
    public void setAuthors(String[] authors);

    /**
     * Author of the item.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="author"></a>author</b></font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *
     *  <td><font size="-1">
     *  Author of the item.</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *  <td><font size="-1"> &lt;g:author&gt;John Steinbeck&lt;/g:author&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *  <td><font size="-1">
     *  Reference Items, News and Articles</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  string</font></td>
     * </tr>
     * </tbody></table>
     * @return Author of the item.
     */
    public String[] getAuthors();

    /**
     * Additional instructions to explain the item’s delivery process.
     *
     *    <table border="1" cellpadding="5" cellspacing="0" width="640">
     *          <tbody><tr valign="top">
     *            <td colspan="2" bgcolor="#dddddd" valign="top"> <font size="-1"><b><a name="delivery_notes"></a>delivery_notes</b></font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Details</b></font></td>
     *
     *            <td> <font size="-1">Additional instructions to explain the item’s delivery process.</font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"><font size="-1"><b>Example</b></font></td>
     *            <td>
     *              <font size="-1">&lt;g:delivery_notes&gt;Items usually shipped within 24 hours.&lt;g:/delivery_notes&gt;<br>
     *
     *              </font></td>
     *
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Attribute
     *                of</b></font></td>
     *            <td>
     *                         <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted
     *                Ads. </font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Content
     *                type</b></font></td>
     *
     *            <td>  <font size="-1">string</font></td>
     *          </tr>
     *        </tbody></table>
     * @param deliveryNotes Additional instructions to explain the item’s delivery process.
     */
    public void setDeliveryNotes(String deliveryNotes);

    /**
     * Additional instructions to explain the item’s delivery process.
     *
     *    <table border="1" cellpadding="5" cellspacing="0" width="640">
     *          <tbody><tr valign="top">
     *            <td colspan="2" bgcolor="#dddddd" valign="top"> <font size="-1"><b><a name="delivery_notes"></a>delivery_notes</b></font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Details</b></font></td>
     *
     *            <td> <font size="-1">Additional instructions to explain the item’s delivery process.</font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"><font size="-1"><b>Example</b></font></td>
     *            <td>
     *              <font size="-1">&lt;g:delivery_notes&gt;Items usually shipped within 24 hours.&lt;g:/delivery_notes&gt;<br>
     *
     *              </font></td>
     *
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Attribute
     *                of</b></font></td>
     *            <td>
     *                         <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted
     *                Ads. </font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Content
     *                type</b></font></td>
     *
     *            <td>  <font size="-1">string</font></td>
     *          </tr>
     *        </tbody></table>
     * @return Additional instructions to explain the item’s delivery process.
     */
    public String getDeliveryNotes();

    /**
     * The maximum distance you will deliver an item in any direction.
     *        <table border="1" cellpadding="5" cellspacing="0" width="640">
     *          <tbody><tr valign="top">
     *            <td colspan="2" bgcolor="#dddddd" valign="top"> <font size="-1"><b><a name="delivery_radius"></a>delivery_radius</b></font></td>
     *
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Details</b></font></td>
     *            <td> <font size="-1">The maximum
     *                distance you will deliver an item in any direction. </font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     *            <td>
     *              <font size="-1">&lt;g:delivery_radius&gt;10&lt;g:/delivery_radius&gt;
     *              </font>
     *        <br><font size="-1">&lt;g:delivery_radius&gt;10km&lt;/g:delivery_radius&gt;</font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Attribute
     *                of</b></font></td>
     *
     *            <td>
     *                         <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted
     *                Ads. </font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Content
     *                type</b></font></td>
     *            <td>  <font size="-1">floatUnit</font></td>
     *          </tr>
     *
     *        </tbody></table>
     * @param deliveryRadius The maximum distance you will deliver an item in any direction.
     */
    public void setDeliveryRadius(FloatUnit deliveryRadius);

    /**
     * The maximum distance you will deliver an item in any direction.
     *        <table border="1" cellpadding="5" cellspacing="0" width="640">
     *          <tbody><tr valign="top">
     *            <td colspan="2" bgcolor="#dddddd" valign="top"> <font size="-1"><b><a name="delivery_radius"></a>delivery_radius</b></font></td>
     *
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Details</b></font></td>
     *            <td> <font size="-1">The maximum
     *                distance you will deliver an item in any direction. </font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     *            <td>
     *              <font size="-1">&lt;g:delivery_radius&gt;10&lt;g:/delivery_radius&gt;
     *              </font>
     *        <br><font size="-1">&lt;g:delivery_radius&gt;10km&lt;/g:delivery_radius&gt;</font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Attribute
     *                of</b></font></td>
     *
     *            <td>
     *                         <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted
     *                Ads. </font></td>
     *          </tr>
     *          <tr valign="top">
     *            <td width="120"> <font size="-1"><b>Content
     *                type</b></font></td>
     *            <td>  <font size="-1">floatUnit</font></td>
     *          </tr>
     *
     *        </tbody></table>
     * @return The maximum distance you will deliver an item in any direction.
     */
    public FloatUnit getDeliveryRadius();

    /**
     * The name of an item being reviewed.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="name_of_item_being_reviewed"></a>name_of_item_being_reviewed</b></font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *
     *  <td><font size="-1">
     *
     *  The name of an item being reviewed.</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *  <td><font size="-1"> &lt;g:name_of_item_being__reviewed&gt;Fleur de lys Restaurant&lt;/g:name_of_item_being_reviewed&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *  <td><font size="-1">
     * Reviews</font></td>
     * </tr>
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  string</font></td>
     * </tr>
     * </tbody></table>
     * @param nameOfItemBeingReviewed The name of an item being reviewed.
     */
    public void setNameOfItemBeingReviewed(String nameOfItemBeingReviewed);

    /**
     * The name of an item being reviewed.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="name_of_item_being_reviewed"></a>name_of_item_being_reviewed</b></font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *
     *  <td><font size="-1">
     *
     *  The name of an item being reviewed.</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *  <td><font size="-1"> &lt;g:name_of_item_being__reviewed&gt;Fleur de lys Restaurant&lt;/g:name_of_item_being_reviewed&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *  <td><font size="-1">
     * Reviews</font></td>
     * </tr>
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  string</font></td>
     * </tr>
     * </tbody></table>
     * @return The name of an item being reviewed.
     */
    public String getNameOfItemBeingReviewed();

    /**
     * Date the item was published.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd"><font size="-1">
     *  <b><a name="publish_date"></a>publish_date</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *
     *  <td><font size="-1">
     *  Date the item was published in <a href="http://www.iso.org/iso/en/prods-services/popstds/datesandtime.html">ISO 8601</a> format:
     *              YYYY-MM-DD</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *
     *  <td><font size="-1"> &lt;g:publish_date&gt;2005-12-20&lt;/g:publish_date&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *  <td><font size="-1">
     *
     *  Reference Items</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  date</font></td>
     *
     * </tr>
     * </tbody></table>
     * @param publishDate Date the item was published.
     */
    public void setPublishDate(Date publishDate);

    /**
     * Date the item was published.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd"><font size="-1">
     *  <b><a name="publish_date"></a>publish_date</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *
     *  <td><font size="-1">
     *  Date the item was published in <a href="http://www.iso.org/iso/en/prods-services/popstds/datesandtime.html">ISO 8601</a> format:
     *              YYYY-MM-DD</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *
     *  <td><font size="-1"> &lt;g:publish_date&gt;2005-12-20&lt;/g:publish_date&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *  <td><font size="-1">
     *
     *  Reference Items</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  date</font></td>
     *
     * </tr>
     * </tbody></table>
     * @return Date the item was published.
     */
    public Date getPublishDate();

    /**
     * Rating of the product or service on a scale of 1-5, with 5 as the best.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="rating"></a>rating</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *  <td><font size="-1">
     *  Rating of the product or service
     *  on a scale of 1-5, with 5 as the best. Numeric values only.</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *  <td><font size="-1">
     *  <em>Acceptable:</em><br>
     * &lt;g:rating&gt;4&lt;/g:rating&gt;<br>
     *
     *  <em>Not acceptable:</em><br>
     *
     * &lt;g:rating&gt;good&lt;/g:rating&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *
     *  <td><font size="-1">
     *  Reviews</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *
     * string</font></td>
     * </tr>
     * </tbody></table>
     * @param rating Rating of the product or service on a scale of 1-5, with 5 as the best.
     */
    public void setRating(Float rating);

    /**
     * Rating of the product or service on a scale of 1-5, with 5 as the best.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="rating"></a>rating</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *  <td><font size="-1">
     *  Rating of the product or service
     *  on a scale of 1-5, with 5 as the best. Numeric values only.</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *  <td><font size="-1">
     *  <em>Acceptable:</em><br>
     * &lt;g:rating&gt;4&lt;/g:rating&gt;<br>
     *
     *  <em>Not acceptable:</em><br>
     *
     * &lt;g:rating&gt;good&lt;/g:rating&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *
     *  <td><font size="-1">
     *  Reviews</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *
     * string</font></td>
     * </tr>
     * </tbody></table>
     * @return Rating of the product or service on a scale of 1-5, with 5 as the best.
     */
    public Float getRating();

    /**
     * The category of the item being reviewed.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="review_type"></a>review_type</b></font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *
     *  <b>Details</b></font></td>
     *
     *  <td><font size="-1"> The category of the item being reviewed.</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *
     *  <td><font size="-1">
     * &lt;g:review_type&gt;toy&lt;/g:review_type&gt;<br>
     *
     * &lt;g:review_type&gt;music&lt;/g:review_type&gt;<br>
     * &lt;g:review_type&gt;restaraunt&lt;/g:review_type&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *
     *  <td><font size="-1">
     *  Reviews</font></td>
     * </tr>
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  string</font></td>
     *
     * </tr>
     * </tbody></table>
     * @param reviewType The category of the item being reviewed.
     */
    public void setReviewType(String reviewType);

    /**
     * The category of the item being reviewed.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="review_type"></a>review_type</b></font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *
     *  <b>Details</b></font></td>
     *
     *  <td><font size="-1"> The category of the item being reviewed.</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *
     *  <td><font size="-1">
     * &lt;g:review_type&gt;toy&lt;/g:review_type&gt;<br>
     *
     * &lt;g:review_type&gt;music&lt;/g:review_type&gt;<br>
     * &lt;g:review_type&gt;restaraunt&lt;/g:review_type&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *
     *  <td><font size="-1">
     *  Reviews</font></td>
     * </tr>
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  string</font></td>
     *
     * </tr>
     * </tbody></table>
     * @return The category of the item being reviewed.
     */
    public String getReviewType();

    /**
     * The type of rating being provided: editorial (a
     *  review written by a member of your staff) or “user” ( a review written by a
     *  user of your site).
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="reviewer_type"></a>reviewer_type</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *
     *  <td><font size="-1"> The type of rating being provided: editorial (a
     *  review written by a member of your staff) or “user” ( a review written by a
     *  user of your site)</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *
     *  <b>Example</b></font></td>
     *  <td><font size="-1">
     * &lt;g:reviewer_type&gt;editorial&lt;/g:reviewer_type&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *
     *  <td><font size="-1">
     *  Reviews</font></td>
     * </tr>
     *
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  string</font></td>
     *
     * </tr>
     * </tbody></table>
     * @param reviewerType The type of rating being provided: editorial (a
     *  review written by a member of your staff) or “user” ( a review written by a
     *  user of your site).
     */
    public void setReviewerType(String reviewerType);

    /**
     * The type of rating being provided: editorial (a
     *  review written by a member of your staff) or “user” ( a review written by a
     *  user of your site).
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="reviewer_type"></a>reviewer_type</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *
     *  <td><font size="-1"> The type of rating being provided: editorial (a
     *  review written by a member of your staff) or “user” ( a review written by a
     *  user of your site)</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *
     *  <b>Example</b></font></td>
     *  <td><font size="-1">
     * &lt;g:reviewer_type&gt;editorial&lt;/g:reviewer_type&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *
     *  <td><font size="-1">
     *  Reviews</font></td>
     * </tr>
     *
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  string</font></td>
     *
     * </tr>
     * </tbody></table>
     * @return The type of rating being provided: editorial (a
     *  review written by a member of your staff) or “user” ( a review written by a
     *  user of your site).
     */
    public String getReviewerType();

    /**
     * The web page of an item being reviewed.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="url_of_item_being_reviewed"></a>url_of_item_being_reviewed</b></font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *
     *  <b>Details</b></font></td>
     *  <td><font size="-1">
     *  The web page of an item being reviewed.</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *
     *  <td><font size="-1"> &lt;g:url_of_item_being_reviewed&gt;http://www.fleurdelyssf.com/&lt;/g:url_of_item_being_reviewed&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *  <td><font size="-1">
     *
     * Reviews</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     * uri</font></td>
     * </tr>
     *
     * </tbody></table>
     * @param urlOfItemBeingReviewed The web page of an item being reviewed.
     */
    public void setUrlOfItemBeingReviewed(URL urlOfItemBeingReviewed);

    /**
     * The web page of an item being reviewed.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="url_of_item_being_reviewed"></a>url_of_item_being_reviewed</b></font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *
     *  <b>Details</b></font></td>
     *  <td><font size="-1">
     *  The web page of an item being reviewed.</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *
     *  <td><font size="-1"> &lt;g:url_of_item_being_reviewed&gt;http://www.fleurdelyssf.com/&lt;/g:url_of_item_being_reviewed&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *  <td><font size="-1">
     *
     * Reviews</font></td>
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     * uri</font></td>
     * </tr>
     *
     * </tbody></table>
     * @return The web page of an item being reviewed.
     */
    public URL getUrlOfItemBeingReviewed();
}
