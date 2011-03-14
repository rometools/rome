/*
 * Wanted.java
 *
 * Created on November 16, 2005, 3:32 PM
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


/**This is an interface for the GoogleBase plug in that exposes methods used for
 * wanted ads.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.1 $
 */
public interface Wanted extends GlobalInterface {
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
     * Location of the property.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="location"></a>location</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *  <td><font size="-1"> Location of a property. Should
     *  include street, city, state, postal code, and country, in that order. </font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *
     *  <td><font size="-1">
     *  <em>Acceptable:</em><br>
     * &lt;g:location&gt;<st1:place>123 Main St, <st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>, <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:location&gt;<st1:place><st1:city>123</st1:city> Main St,, <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>, <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     * &lt;g:location&gt;
     *  <st1:place><st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>, <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *  <td><font size="-1">
     *  Reference Items, Events, Housing, Jobs, News and Articles,
     *  People profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  locationType</font></td>
     * </tr>
     * </tbody></table>
     * @param location Location of the property.
     */
    public void setLocation(String location);

    /**
     * Location of the property.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody><tr valign="top">
     *
     *  <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *  <b><a name="location"></a>location</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Details</b></font></td>
     *  <td><font size="-1"> Location of a property. Should
     *  include street, city, state, postal code, and country, in that order. </font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Example</b></font></td>
     *
     *  <td><font size="-1">
     *  <em>Acceptable:</em><br>
     * &lt;g:location&gt;<st1:place>123 Main St, <st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>, <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:location&gt;<st1:place><st1:city>123</st1:city> Main St,, <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>, <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     * &lt;g:location&gt;
     *  <st1:place><st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>, <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *  <td width="120"><font size="-1">
     *  <b>Attribute of</b></font></td>
     *  <td><font size="-1">
     *  Reference Items, Events, Housing, Jobs, News and Articles,
     *  People profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     *  <td width="120"><font size="-1">
     *  <b>Content type</b></font></td>
     *  <td><font size="-1">
     *  locationType</font></td>
     * </tr>
     * </tbody></table>
     * @return Location of the property.
     */
    public String getLocation();

}
