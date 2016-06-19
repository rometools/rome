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

import com.rometools.modules.base.types.CurrencyEnumeration;
import com.rometools.modules.base.types.FloatUnit;
import com.rometools.modules.base.types.PaymentTypeEnumeration;
import com.rometools.modules.base.types.PriceTypeEnumeration;
import com.rometools.modules.base.types.ShippingType;
import com.rometools.modules.base.types.YearType;

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for vehicles.
 */
public interface Vehicle extends GlobalInterface {
    /**
     * Color of an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="color"></a>color</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Color of an item.</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:color&gt;Black&lt;/g:color&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1"> Products, Vehicles</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param color Color of an item.
     */
    public void setColors(String[] color);

    /**
     * Color of an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="color"></a>color</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Color of an item.</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:color&gt;Black&lt;/g:color&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1"> Products, Vehicles</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Color of an item.
     */
    public String[] getColors();

    /**
     * Condition of the item. For example: new, used, or refurbished.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="condition"></a>condition</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1">Condition of the item. For example: new, used, or refurbished.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td>
     * <font size="-1">&lt;g:condition&gt;refurbished&lt;/g:condition&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1">Products, Vehicles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">string</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @param condition Condition of the item. For example: new, used, or refurbished.
     */
    public void setCondition(String condition);

    /**
     * Condition of the item. For example: new, used, or refurbished.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="condition"></a>condition</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1">Condition of the item. For example: new, used, or refurbished.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td>
     * <font size="-1">&lt;g:condition&gt;refurbished&lt;/g:condition&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1">Products, Vehicles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">string</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @return Condition of the item. For example: new, used, or refurbished.
     */
    public String getCondition();

    /**
     * Currency of the price amount for an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="currency"></a>currency</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">Currency of the price amount for an item. Values must be in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/currencycodeslist.html">ISO 4217</a>
     * currency code format.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"><em>Acceptable:</em><br>
     * &lt;g:currency&gt;USD&lt;/g:currency&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:currency&gt;US Dollars&lt;/g:currency&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1">Events, Housing, Products, Services, Travel, Vehicles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">currencyEnumeration</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param value Currency of the price amount for an item.
     */
    public void setCurrency(CurrencyEnumeration value);

    /**
     * Currency of the price amount for an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="currency"></a>currency</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">Currency of the price amount for an item. Values must be in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/currencycodeslist.html">ISO 4217</a>
     * currency code format.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"><em>Acceptable:</em><br>
     * &lt;g:currency&gt;USD&lt;/g:currency&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:currency&gt;US Dollars&lt;/g:currency&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1">Events, Housing, Products, Services, Travel, Vehicles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">currencyEnumeration</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Currency of the price amount for an item.
     */
    public CurrencyEnumeration getCurrency();

    /**
     * Additional instructions to explain the item’s delivery process.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="delivery_notes"></a>delivery_notes</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">Additional instructions to explain the item’s delivery process.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td>
     * <font size="-1">&lt;g:delivery_notes&gt;Items usually shipped within 24
     * hours.&lt;g:/delivery_notes&gt;<br>
     *
     * </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td>
     * <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted Ads. </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1">string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param deliveryNotes Additional instructions to explain the item’s delivery process.
     */
    public void setDeliveryNotes(String deliveryNotes);

    /**
     * Additional instructions to explain the item’s delivery process.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="delivery_notes"></a>delivery_notes</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">Additional instructions to explain the item’s delivery process.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td>
     * <font size="-1">&lt;g:delivery_notes&gt;Items usually shipped within 24
     * hours.&lt;g:/delivery_notes&gt;<br>
     *
     * </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td>
     * <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted Ads. </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1">string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Additional instructions to explain the item’s delivery process.
     */
    public String getDeliveryNotes();

    /**
     * The maximum distance you will deliver an item in any direction.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="delivery_radius"></a>delivery_radius</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1">The maximum distance you will deliver an item in any direction. </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td>
     * <font size="-1">&lt;g:delivery_radius&gt;10&lt;g:/delivery_radius&gt; </font> <br>
     * <font size="-1">&lt;g:delivery_radius&gt;10km&lt;/g:delivery_radius&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td>
     * <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted Ads. </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">floatUnit</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @param deliveryRadius The maximum distance you will deliver an item in any direction.
     */
    public void setDeliveryRadius(FloatUnit deliveryRadius);

    /**
     * The maximum distance you will deliver an item in any direction.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="delivery_radius"></a>delivery_radius</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1">The maximum distance you will deliver an item in any direction. </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td>
     * <font size="-1">&lt;g:delivery_radius&gt;10&lt;g:/delivery_radius&gt; </font> <br>
     * <font size="-1">&lt;g:delivery_radius&gt;10km&lt;/g:delivery_radius&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td>
     * <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted Ads. </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">floatUnit</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @return The maximum distance you will deliver an item in any direction.
     */
    public FloatUnit getDeliveryRadius();

    /**
     * Location of a property. Should include street, city, state, postal code, and country, in that
     * order.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="location"></a>location</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Location of a property. Should include street, city, state, postal code,
     * and country, in that order. </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:location&gt;<st1:place>123 Main St, <st1:city>Anytown</st1:city>,
     * <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:location&gt;<st1:place><st1:city>123</st1:city> Main St,, <st1:state>CA</st1:state>,
     * <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     * &lt;g:location&gt; <st1:place><st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>,
     * <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, News and Articles, People
     * profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> locationType</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param location Location of a property. Should include street, city, state, postal code, and
     *            country, in that order.
     */
    public void setLocation(String location);

    /**
     * Location of a property. Should include street, city, state, postal code, and country, in that
     * order.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="location"></a>location</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Location of a property. Should include street, city, state, postal code,
     * and country, in that order. </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:location&gt;<st1:place>123 Main St, <st1:city>Anytown</st1:city>,
     * <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:location&gt;<st1:place><st1:city>123</st1:city> Main St,, <st1:state>CA</st1:state>,
     * <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     * &lt;g:location&gt; <st1:place><st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>,
     * <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, News and Articles, People
     * profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> locationType</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Location of a property. Should include street, city, state, postal code, and country,
     *         in that order.
     */
    public String getLocation();

    /**
     * The vehicle manufacturer.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="make"></a>make</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1">
     *
     * The vehicle manufacturer.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:make&gt;Honda&lt;/g:make&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param make The vehicle manufacturer.
     */
    public void setMake(String make);

    /**
     * The vehicle manufacturer.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="make"></a>make</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1">
     *
     * The vehicle manufacturer.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:make&gt;Honda&lt;/g:make&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return The vehicle manufacturer.
     */
    public String getMake();

    /**
     * Current mileage of the vehicle.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd"><font size="-1"><b><a name="mileage"></a>mileage</b></font>
     * </td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Current mileage of the vehicle. </font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:mileage&gt;1700&lt;/g:mileage&gt;<br>
     * <br>
     * &lt;g:mileage&gt;1,700 miles&lt;/g:mileage&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Vehicles</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> intUnit</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param mileage Current mileage of the vehicle.
     */
    public void setMileage(Integer mileage);

    /**
     * Current mileage of the vehicle.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd"><font size="-1"><b><a name="mileage"></a>mileage</b></font>
     * </td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Current mileage of the vehicle. </font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:mileage&gt;1700&lt;/g:mileage&gt;<br>
     * <br>
     * &lt;g:mileage&gt;1,700 miles&lt;/g:mileage&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Vehicles</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> intUnit</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Current mileage of the vehicle.
     */
    public Integer getMileage();

    /**
     * The vehicle model.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd"><font size="-1"> <b><a name="model"></a>model</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1">The vehicle model.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:model&gt;Camry&lt;/g:model&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Vehicles</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param model The vehicle model.
     */
    public void setModel(String model);

    /**
     * The vehicle model.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd"><font size="-1"> <b><a name="model"></a>model</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1">The vehicle model.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:model&gt;Camry&lt;/g:model&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Vehicles</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return The vehicle model.
     */
    public String getModel();

    /**
     * Payment Methods acceptable for the service.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="payment_accepted"></a>payment_accepted</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Acceptable payment methods for item purchases. Acceptable values are
     * "Cash," "Check," "Traveler’s Check," "Visa," "MasterCard,"
     *
     * "American Express," "Discover," "Wire transfer" or "Paypal." If you accept more than one
     * method, include multiple instances of the &lt;payment_accepted&gt; attribute for each
     * acceptable method.</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:payment_accepted&gt;Cash&lt;/g:payment_accepted&gt;<br>
     *
     * &lt;g:payment_accepted&gt;Check&lt;/g:payment_accepted&gt;<br>
     *
     * &lt;g:payment_accepted&gt;Paypal&lt;/g:payment_accepted&gt;<br>
     * <em>Not acceptable:</em><br>
     * &lt;g:payment_accepted&gt;Cash Check Paypal&lt;/g:payment_accepted&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> paymentMethodEnumeration</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param paymentAccepted Payment Methods acceptable for the service.
     */
    public void setPaymentAccepted(PaymentTypeEnumeration[] paymentAccepted);

    /**
     * Payment Methods acceptable for the service.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="payment_accepted"></a>payment_accepted</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Acceptable payment methods for item purchases. Acceptable values are
     * "Cash," "Check," "Traveler’s Check," "Visa," "MasterCard,"
     *
     * "American Express," "Discover," "Wire transfer" or "Paypal." If you accept more than one
     * method, include multiple instances of the &lt;payment_accepted&gt; attribute for each
     * acceptable method.</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:payment_accepted&gt;Cash&lt;/g:payment_accepted&gt;<br>
     *
     * &lt;g:payment_accepted&gt;Check&lt;/g:payment_accepted&gt;<br>
     *
     * &lt;g:payment_accepted&gt;Paypal&lt;/g:payment_accepted&gt;<br>
     * <em>Not acceptable:</em><br>
     * &lt;g:payment_accepted&gt;Cash Check Paypal&lt;/g:payment_accepted&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> paymentMethodEnumeration</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Payment Methods acceptable for the service.
     */
    public PaymentTypeEnumeration[] getPaymentAccepted();

    /**
     * Additional payment information.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="payment_notes"></a>payment_notes</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Additional instructions to explain a payment policy.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:payment_notes&gt;Cash only for local
     * orders.&lt;/g:payment_notes&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Events, Housing, Products, Services, Travel, Vehicles</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param paymentNotes Additional payment information.
     */
    public void setPaymentNotes(String paymentNotes);

    /**
     * Additional payment information.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="payment_notes"></a>payment_notes</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Additional instructions to explain a payment policy.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:payment_notes&gt;Cash only for local
     * orders.&lt;/g:payment_notes&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Events, Housing, Products, Services, Travel, Vehicles</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Additional payment information.
     */
    public String getPaymentNotes();

    /**
     * Price for the service. <br>
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="price"></a>price</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Prices can be a single price, 0 (free), or blank if not applicable.
     * Numerice values only. When used as a sub-attribute of &lt;shipping&gt;, the value included
     * reflects the price of shipping.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:price&gt;5.95&lt;/g:price&gt;<br>
     *
     * &lt;g:price&gt;0&lt;/g:price&gt;<br>
     * <em>Not acceptable:</em><br>
     * &lt;g:price&gt;5,95&lt;/g:price&gt;<br>
     * &lt;g:price&gt;5.00 � 10.00&lt;/g:price&gt;<br>
     *
     * &lt;g:price&gt;100 or best offer&lt;/g:price&gt;<br>
     * &lt;g:price&gt;free&lt;/g:price&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td valign="top"><font size="-1">floatUnit</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param price Price for the service.
     */
    public void setPrice(FloatUnit price);

    /**
     * Price for the service. <br>
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="price"></a>price</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Prices can be a single price, 0 (free), or blank if not applicable.
     * Numerice values only. When used as a sub-attribute of &lt;shipping&gt;, the value included
     * reflects the price of shipping.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:price&gt;5.95&lt;/g:price&gt;<br>
     *
     * &lt;g:price&gt;0&lt;/g:price&gt;<br>
     * <em>Not acceptable:</em><br>
     * &lt;g:price&gt;5,95&lt;/g:price&gt;<br>
     * &lt;g:price&gt;5.00 � 10.00&lt;/g:price&gt;<br>
     *
     * &lt;g:price&gt;100 or best offer&lt;/g:price&gt;<br>
     * &lt;g:price&gt;free&lt;/g:price&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td valign="top"><font size="-1">floatUnit</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Price for the service.
     */
    public FloatUnit getPrice();

    /**
     * Price type information.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="price_type"></a>price_type</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1">The type of pricing for the item. Acceptable values are �negotiable,� or
     * �starting.� The default is �starting�</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"><em>Acceptable:</em><br>
     * &lt;g:price_type&gt;starting&lt;/g:price_type&gt;<br>
     * <em>Not acceptable:</em><br>
     * &lt;g:price_type&gt;100 OBO&lt;/g:price_type&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> priceTypeEnumeration</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param priceType Price type information.
     */
    public void setPriceType(PriceTypeEnumeration priceType);

    /**
     * Price type information.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="price_type"></a>price_type</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1">The type of pricing for the item. Acceptable values are �negotiable,� or
     * �starting.� The default is �starting�</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"><em>Acceptable:</em><br>
     * &lt;g:price_type&gt;starting&lt;/g:price_type&gt;<br>
     * <em>Not acceptable:</em><br>
     * &lt;g:price_type&gt;100 OBO&lt;/g:price_type&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> priceTypeEnumeration</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Price type information.
     */
    public PriceTypeEnumeration getPriceType();

    /**
     * Quantity available.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="quantity"></a>quantity</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The number of units available for purchase. This attribute can be left
     * blank if you have a large quantity or if it is not applicable. </font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:quantity&gt;18&lt;/g:quantity&gt;<br>
     *
     * &lt;g:quantity&gt;0&lt;/g:quantity&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:quantity&gt;out of stock&lt;/g:quantity&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Products, Services, Travel, Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1">
     *
     * integer</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param quantity Quantity available.
     */
    public void setQuantity(Integer quantity);

    /**
     * Quantity available.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="quantity"></a>quantity</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The number of units available for purchase. This attribute can be left
     * blank if you have a large quantity or if it is not applicable. </font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:quantity&gt;18&lt;/g:quantity&gt;<br>
     *
     * &lt;g:quantity&gt;0&lt;/g:quantity&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:quantity&gt;out of stock&lt;/g:quantity&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Products, Services, Travel, Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1">
     *
     * integer</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Quantity available.
     */
    public Integer getQuantity();

    /**
     * Shipping options available for an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="shipping"></a>shipping</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Shipping options available for an item. Up to 10 shipping options can be
     * included for each item. Three sub-attributes are included in the shipping attribute:
     * <ul type="disc">
     *
     * <li>service = The type of service used to ship an item. Acceptable values are 'FedEx', 'UPS',
     * 'DHL', 'Mail', and 'Other'</li>
     * <li>country = The country an item will ship to. Only acceptable values are<b> </b>ISO 3166
     * country codes.</li>
     * <li>price = the price of shipping.</li>
     * </ul>
     * </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:shipping&gt;<br>
     * &lt;g:country&gt;US&lt;/g:country&gt;<br>
     *
     * &lt;g:service&gt;UPS&lt;/g:shipping&gt;<br>
     *
     * &lt;g:price&gt;35.95&lt;/g:price&gt;<br>
     * &lt;/g:shipping&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Products</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> shippingType</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param shipping Shipping options available for an item.
     */
    public void setShipping(ShippingType[] shipping);

    /**
     * Shipping options available for an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="shipping"></a>shipping</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Shipping options available for an item. Up to 10 shipping options can be
     * included for each item. Three sub-attributes are included in the shipping attribute:
     * <ul type="disc">
     *
     * <li>service = The type of service used to ship an item. Acceptable values are 'FedEx', 'UPS',
     * 'DHL', 'Mail', and 'Other'</li>
     * <li>country = The country an item will ship to. Only acceptable values are<b> </b>ISO 3166
     * country codes.</li>
     * <li>price = the price of shipping.</li>
     * </ul>
     * </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:shipping&gt;<br>
     * &lt;g:country&gt;US&lt;/g:country&gt;<br>
     *
     * &lt;g:service&gt;UPS&lt;/g:shipping&gt;<br>
     *
     * &lt;g:price&gt;35.95&lt;/g:price&gt;<br>
     * &lt;/g:shipping&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Products</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> shippingType</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Shipping options available for an item.
     */
    public ShippingType[] getShipping();

    /**
     * Tax rate associated with the item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="tax_percent"></a>tax_percent</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1"> Tax rate as a percentage.</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:tax_percent&gt;8.2&lt;g:/tax_percent&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Products, Events</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> percentType</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param taxPercent Tax rate associated with the event.
     */
    public void setTaxPercent(Float taxPercent);

    /**
     * Tax rate associated with the item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="tax_percent"></a>tax_percent</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1"> Tax rate as a percentage.</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:tax_percent&gt;8.2&lt;g:/tax_percent&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Products, Events</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> percentType</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Tax rate associated with the event.
     */
    public Float getTaxPercent();

    /**
     * Region where tax applies.
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="tax_region"></a>tax_region</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Geographical region a tax rate applies to.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:tax_region&gt;California&lt;/g:tax_region&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Product, Events,</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1">string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param taxRegion Region where tax applies.
     */
    public void setTaxRegion(String taxRegion);

    /**
     * Region where tax applies.
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="tax_region"></a>tax_region</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Geographical region a tax rate applies to.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:tax_region&gt;California&lt;/g:tax_region&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Product, Events,</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1">string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Region where tax applies.
     */
    public String getTaxRegion();

    /**
     * The type of vehicle: Car, motorcycle, scooter, etc.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="vehicle_type"></a>vehicle_type</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The type of vehicle: Car, motorcycle, scooter, etc. </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:vehicle_type&gt;truck&lt;/g:vehicle_type&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Products</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1">
     *
     * string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param vehicleType The type of vehicle: Car, motorcycle, scooter, etc.
     */
    public void setVehicleType(String vehicleType);

    /**
     * The type of vehicle: Car, motorcycle, scooter, etc.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="vehicle_type"></a>vehicle_type</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The type of vehicle: Car, motorcycle, scooter, etc. </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:vehicle_type&gt;truck&lt;/g:vehicle_type&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Products</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1">
     *
     * string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return The type of vehicle: Car, motorcycle, scooter, etc.
     */
    public String getVehicleType();

    /**
     * Vehicle Identification Number.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="vin"></a>vin</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Vehicle Identification Number.</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:vin&gt;1M8GDM9AXKP042788&lt;/g:vin&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param vin Vehicle Identification Number.
     */
    public void setVin(String vin);

    /**
     * Vehicle Identification Number.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="vin"></a>vin</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Vehicle Identification Number.</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:vin&gt;1M8GDM9AXKP042788&lt;/g:vin&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Vehicles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Vehicle Identification Number.
     */
    public String getVin();

    /**
     * The four digit model year or year built.
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="year"></a>year</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> The four digit model year or year built. Format YYYY</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> <em>Acceptable:</em><br>
     *
     * &lt;g:year&gt;2005&lt;/g:year&gt;<br>
     * <em>Not acceptable:</em><br>
     * &lt;g:year&gt;79&lt;/g:year&gt;<br>
     *
     * &lt;g:year&gt;26&lt;/g:year&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Housing, Vehicles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> year</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @param year The four digit model year or year built.
     */
    public void setYear(YearType year);

    /**
     * The four digit model year or year built.
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="year"></a>year</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> The four digit model year or year built. Format YYYY</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> <em>Acceptable:</em><br>
     *
     * &lt;g:year&gt;2005&lt;/g:year&gt;<br>
     * <em>Not acceptable:</em><br>
     * &lt;g:year&gt;79&lt;/g:year&gt;<br>
     *
     * &lt;g:year&gt;26&lt;/g:year&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Housing, Vehicles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> year</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @return The four digit model year or year built.
     */
    public YearType getYear();
}
