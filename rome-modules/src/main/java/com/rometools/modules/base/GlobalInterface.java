/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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
package com.rometools.modules.base;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

/**
 * This is an interface representing the universals.
 */
public interface GlobalInterface extends Serializable {
    /**
     * Expiration Date for this item. <br>
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="expiration_date"></a>expiration_date</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Date that the item expires in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/datesandtime.html">ISO 8601</a>
     * format: YYYY-MM-DD</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:expiration_date&gt;2005-20-12&lt;/g:expiration_date&gt;
     *
     * </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, People profiles, Products,
     * Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> date</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param expirationDate the date this entry will expire
     */
    public void setExpirationDate(Date expirationDate);

    /**
     * Expiration Date for this item. <br>
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="expiration_date"></a>expiration_date</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Date that the item expires in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/datesandtime.html">ISO 8601</a>
     * format: YYYY-MM-DD</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:expiration_date&gt;2005-20-12&lt;/g:expiration_date&gt;
     *
     * </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, People profiles, Products,
     * Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> date</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return the date this entry will expire
     */
    public Date getExpirationDate();

    /**
     * Date and time that the item expires.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="expiration_date_time"></a>expiration_date_time</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1"> Date and time that the item expires in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/datesandtime.html">ISO 8601</a>
     * format: YYYY-MM-DDThh:mm:ss</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1">
     * &lt;g:expiration_date_time&gt;12-20-05T11:59:59&lt;/g:expiration_date_time&gt; </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, People profiles, Products,
     * Services, Travel, Vehicles, Wanted Ads.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> dateTime</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @param expirationDateTime Date and time that the item expires.
     */
    public void setExpirationDateTime(Date expirationDateTime);

    /**
     * Date and time that the item expires.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="expiration_date_time"></a>expiration_date_time</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1"> Date and time that the item expires in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/datesandtime.html">ISO 8601</a>
     * format: YYYY-MM-DDThh:mm:ss</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1">
     * &lt;g:expiration_date_time&gt;12-20-05T11:59:59&lt;/g:expiration_date_time&gt; </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, People profiles, Products,
     * Services, Travel, Vehicles, Wanted Ads.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> dateTime</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @return Date and time that the item expires.
     */
    public Date getExpirationDateTime();

    /**
     * Unique id for this item.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="id"></a>id</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Unique alphanumeric identifier for each item - e.g., your internal ID
     * code.<br>
     * IMPORTANT: Once you submit an item with a unique id, this identifier must not change when you
     * send in a new bulk upload. Each item must retain the same id in subsequent bulk
     * uploads.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1">
     *
     * &lt;g:id&gt;01flx&lt;/g:id&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, News and Articles, People
     * profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param id unique identifier for this entry
     */
    public void setId(String id);

    /**
     * Unique id for this item.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="id"></a>id</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Unique alphanumeric identifier for each item - e.g., your internal ID
     * code.<br>
     * IMPORTANT: Once you submit an item with a unique id, this identifier must not change when you
     * send in a new bulk upload. Each item must retain the same id in subsequent bulk
     * uploads.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1">
     *
     * &lt;g:id&gt;01flx&lt;/g:id&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, News and Articles, People
     * profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return unique identifier for this entry
     */
    public String getId();

    /**
     * Images for this item. <br>
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="image_link"></a>image_link</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1"> URL of an associated image if available online. Use your full-sized
     * images; do not use thumbnail images. Up to ten image_links, all placed in between their own
     * &lt;image_link&gt; and &lt;/image_link&gt; attributes, can be included with each item. If you
     * do not have an image available, do not include this attribute. Please do not include an image
     * that says "Image not available." Relative URLs and logo images are not acceptable.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;image_link&gt;http://example.com/images/1006.jpg&lt;/image_link&gt;<br>
     *
     * &lt;image_link&gt;http://example.com/i/6.jpg,http://example.com/i/9.jpg&lt;/image_link&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;image_link&gt;/images/1006.jpg&lt;/image_link&gt;<br>
     * &lt;image_link&gt;example.com/images/1006.jpg&lt;/image_link&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, News and Articles, People
     * profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> url</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @param imageLinks URLs to images. Limit 10.
     */
    public void setImageLinks(URL[] imageLinks);

    /**
     * Images for this item. <br>
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="image_link"></a>image_link</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1"> URL of an associated image if available online. Use your full-sized
     * images; do not use thumbnail images. Up to ten image_links, all placed in between their own
     * &lt;image_link&gt; and &lt;/image_link&gt; attributes, can be included with each item. If you
     * do not have an image available, do not include this attribute. Please do not include an image
     * that says "Image not available." Relative URLs and logo images are not acceptable.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;image_link&gt;http://example.com/images/1006.jpg&lt;/image_link&gt;<br>
     *
     * &lt;image_link&gt;http://example.com/i/6.jpg,http://example.com/i/9.jpg&lt;/image_link&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;image_link&gt;/images/1006.jpg&lt;/image_link&gt;<br>
     * &lt;image_link&gt;example.com/images/1006.jpg&lt;/image_link&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, News and Articles, People
     * profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> url</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @return URLs to images
     */
    public URL[] getImageLinks();

    /**
     * Labels for this item. <br>
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top">
     * <font size="-1"><b><a name="label"></a>label</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120">
     *
     * <font size="-1"><b>Details</b></font></td>
     * <td>
     * <font size="-1">A list of classifications the item may fall under. Up to ten user-selected
     * label, each placed in between their own &lt;label&gt;
     *
     * and &lt;/label&gt; tags, can be included with each item. These attributes will be used to
     * match your items to search queries. Each attribute value will be checked for policy
     * compliance.</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120">
     * <font size="-1"><b>Example</b></font></td>
     * <td>
     *
     * <font size="-1"><em>Acceptable:<br>
     * For an automobile in a Vehicles information type:</em><br>
     * </font>
     *
     * <font size="-1">&lt;g:label&gt;Leather&lt;/g:label&gt;<br>
     * </font> <font size="-1">&lt;g:label&gt;Power locks&lt;/g:label&gt;<br>
     *
     * </font> <font size="-1">&lt;g:label&gt;sunroof&lt;/g:label&gt;<br>
     *
     * </font> <font size="-1">&lt;g:label&gt;ABS&lt;/g:label&gt;<br>
     * <br>
     * </font> <font size="-1"><em>For a concert in an Events information type:</em></font><br>
     *
     * <font size="-1">&lt;g:label&gt;VIP&lt;/g:label&gt;<br>
     *
     * </font> <font size="-1">&lt;g:label&gt;front row&lt;/g:label&gt;<br>
     * </font> <font size="-1">&lt;g:label&gt;backstage&lt;/g:label&gt;<br>
     *
     * </font>
     *
     * <font size="-1">&lt;g:label&gt;KROCK 101.5&lt;/g:label&gt;<br>
     * </font> <font size="-1">&lt;g:label&gt;parking passes&lt;/g:label&gt;<br>
     * <br>
     * </font> <font size="-1"><em>Not acceptable:</em><br>
     *
     * </font> <font size="-1">&lt;g:label&gt; leater, power locks, sunroof, ABS
     * &lt;/g:label&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120">
     * <font size="-1"><b>Attribute of</b></font></td>
     *
     * <td>
     * <font size="-1">Reference Items, Events, Housing, Jobs, News and Articles, People profiles,
     * Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120">
     * <font size="-1"><b>Content type</b></font></td>
     * <td>
     *
     * <font size="-1">string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param labels labels for this entry. Limit 10.
     */
    public void setLabels(String[] labels);

    /**
     * Labels for this item. <br>
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top">
     * <font size="-1"><b><a name="label"></a>label</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120">
     *
     * <font size="-1"><b>Details</b></font></td>
     * <td>
     * <font size="-1">A list of classifications the item may fall under. Up to ten user-selected
     * label, each placed in between their own &lt;label&gt;
     *
     * and &lt;/label&gt; tags, can be included with each item. These attributes will be used to
     * match your items to search queries. Each attribute value will be checked for policy
     * compliance.</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120">
     * <font size="-1"><b>Example</b></font></td>
     * <td>
     *
     * <font size="-1"><em>Acceptable:<br>
     * For an automobile in a Vehicles information type:</em><br>
     * </font>
     *
     * <font size="-1">&lt;g:label&gt;Leather&lt;/g:label&gt;<br>
     * </font> <font size="-1">&lt;g:label&gt;Power locks&lt;/g:label&gt;<br>
     *
     * </font> <font size="-1">&lt;g:label&gt;sunroof&lt;/g:label&gt;<br>
     *
     * </font> <font size="-1">&lt;g:label&gt;ABS&lt;/g:label&gt;<br>
     * <br>
     * </font> <font size="-1"><em>For a concert in an Events information type:</em></font><br>
     *
     * <font size="-1">&lt;g:label&gt;VIP&lt;/g:label&gt;<br>
     *
     * </font> <font size="-1">&lt;g:label&gt;front row&lt;/g:label&gt;<br>
     * </font> <font size="-1">&lt;g:label&gt;backstage&lt;/g:label&gt;<br>
     *
     * </font>
     *
     * <font size="-1">&lt;g:label&gt;KROCK 101.5&lt;/g:label&gt;<br>
     * </font> <font size="-1">&lt;g:label&gt;parking passes&lt;/g:label&gt;<br>
     * <br>
     * </font> <font size="-1"><em>Not acceptable:</em><br>
     *
     * </font> <font size="-1">&lt;g:label&gt; leater, power locks, sunroof, ABS
     * &lt;/g:label&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120">
     * <font size="-1"><b>Attribute of</b></font></td>
     *
     * <td>
     * <font size="-1">Reference Items, Events, Housing, Jobs, News and Articles, People profiles,
     * Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120">
     * <font size="-1"><b>Content type</b></font></td>
     * <td>
     *
     * <font size="-1">string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return labels for this entry
     */
    public String[] getLabels();
}
