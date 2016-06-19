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
import com.rometools.modules.base.types.IntUnit;
import com.rometools.modules.base.types.PaymentTypeEnumeration;
import com.rometools.modules.base.types.PriceTypeEnumeration;
import com.rometools.modules.base.types.YearType;

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for Housing entry
 * types.
 */
public interface Housing extends GlobalInterface {
    /**
     * An array of agent name Strings, not to exceed 10 in lenght.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr bgcolor="#dddddd" valign="top">
     * <td colspan="2" nowrap="nowrap"><font size="-1"><strong><a
     * name="agent"></a>agent</strong></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><strong><font size="-1">Details</font></strong></td>
     * <td><font size="-1">Individual who is negotiating and arranging the real estate sale.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td><strong><font size="-1">Example</font></strong></td>
     *
     * <td><font size="-1">&lt;g:agent&gt;Sue Smith&lt;/g:agent&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td><strong><font size="-1">Attribute of</font></strong></td>
     * <td><font size="-1">Housing</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td><strong><font size="-1">Content type</font></strong></td>
     * <td><font size="-1">string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param agents An array of agent name Strings, not to exceed 10 in lenght.
     */
    public void setAgents(String[] agents);

    /**
     * An array of agent name Strings, not to exceed 10 in lenght.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr bgcolor="#dddddd" valign="top">
     * <td colspan="2" nowrap="nowrap"><font size="-1"><strong><a
     * name="agent"></a>agent</strong></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><strong><font size="-1">Details</font></strong></td>
     * <td><font size="-1">Individual who is negotiating and arranging the real estate sale.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td><strong><font size="-1">Example</font></strong></td>
     *
     * <td><font size="-1">&lt;g:agent&gt;Sue Smith&lt;/g:agent&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td><strong><font size="-1">Attribute of</font></strong></td>
     * <td><font size="-1">Housing</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td><strong><font size="-1">Content type</font></strong></td>
     * <td><font size="-1">string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return An array of agent name Strings, not to exceed 10 in lenght.
     */
    public String[] getAgents();

    /**
     * The area of the real estate.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr bgcolor="#dddddd" valign="top">
     * <td colspan="2" nowrap="nowrap"><font size="-1"><strong><a
     * name="area"></a>area</strong></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><strong><font size="-1">Details</font></strong></td>
     * <td><font size="-1">The size of an area of real estate. </font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td><strong><font size="-1">Example</font></strong></td>
     * <td><font size="-1">&lt;g:area&gt;1000&lt;/g:area&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td><strong><font size="-1">Attribute of</font></strong></td>
     *
     * <td><font size="-1">Housing</font></td>
     * </tr>
     * <tr valign="top">
     * <td><strong><font size="-1">Content type</font></strong></td>
     *
     * <td><font size="-1">intUnit</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param area The area of the real estate
     */
    public void setArea(IntUnit area);

    /**
     * The area of the real estate.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr bgcolor="#dddddd" valign="top">
     * <td colspan="2" nowrap="nowrap"><font size="-1"><strong><a
     * name="area"></a>area</strong></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><strong><font size="-1">Details</font></strong></td>
     * <td><font size="-1">The size of an area of real estate. </font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td><strong><font size="-1">Example</font></strong></td>
     * <td><font size="-1">&lt;g:area&gt;1000&lt;/g:area&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td><strong><font size="-1">Attribute of</font></strong></td>
     *
     * <td><font size="-1">Housing</font></td>
     * </tr>
     * <tr valign="top">
     * <td><strong><font size="-1">Content type</font></strong></td>
     *
     * <td><font size="-1">intUnit</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return The area of the real estate
     */
    public IntUnit getArea();

    /**
     * The number of bathrooms.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="bathrooms"></a>bathrooms</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1">
     *
     * Number of bathrooms. Numeric values only.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br>
     *
     * &lt;g:bathrooms&gt;2&lt;/g:bathrooms&gt;<br>
     * <em>Not acceptable:</em><br>
     * &lt;g:bathrooms&gt;2 bathrooms&lt;/g:bathrooms&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1"> Housing</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> float</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param bathrooms The number of bathrooms.
     */
    public void setBathrooms(Float bathrooms);

    /**
     * The number of bathrooms.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="bathrooms"></a>bathrooms</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1">
     *
     * Number of bathrooms. Numeric values only.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br>
     *
     * &lt;g:bathrooms&gt;2&lt;/g:bathrooms&gt;<br>
     * <em>Not acceptable:</em><br>
     * &lt;g:bathrooms&gt;2 bathrooms&lt;/g:bathrooms&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1"> Housing</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> float</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return number of bathrooms
     */
    public Float getBathrooms();

    /**
     * Number of bedrooms.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="bedrooms"></a>bedrooms</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Number of bedrooms. Numeric values only.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:bedrooms&gt;3&lt;/g:bedrooms&gt;<br>
     * <em>Not acceptable:</em><br>
     *
     * &lt;g:bedrooms&gt;3 bedrooms&lt;/g:bedrooms&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Housing</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> integer</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param bedrooms Number of bedrooms.
     */
    public void setBedrooms(Integer bedrooms);

    /**
     * Number of bedrooms.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="bedrooms"></a>bedrooms</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Number of bedrooms. Numeric values only.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:bedrooms&gt;3&lt;/g:bedrooms&gt;<br>
     * <em>Not acceptable:</em><br>
     *
     * &lt;g:bedrooms&gt;3 bedrooms&lt;/g:bedrooms&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Housing</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> integer</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Number of bedrooms
     */
    public Integer getBedrooms();

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
     * Homeowners association dues on the property.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="hoa_dues"></a>hoa_dues</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Monthly dues paid to a homeowners association. </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1">&lt;g:hoa_dues&gt;100&lt;/g:hoa_dues&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Housing</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> float</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @param hoaDues Homeowners association dues on the property.
     */
    public void setHoaDues(Float hoaDues);

    /**
     * Homeowners association dues on the property.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="hoa_dues"></a>hoa_dues</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Monthly dues paid to a homeowners association. </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1">&lt;g:hoa_dues&gt;100&lt;/g:hoa_dues&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Housing</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> float</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @return Homeowners association dues on the property.
     */
    public Float getHoaDues();

    /**
     * Indicates whether this property is for sale or not.
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="listing_type"></a>listing_type</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Information about whether or not a property is for sale or not.
     * Acceptable values are "True" or "False".</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br>
     *
     * &lt;g:for_sale&gt;true&lt;/g:for_sale&gt;<br>
     * &lt;g:for_sale&gt;false&lt;/g:for_sale&gt;<br>
     * <em>Not acceptable:</em><br>
     *
     * &lt;g:for_sale&gt;<st1:city><st1:place>Sale</st1:place></st1:city>&lt;/g:for_sale&gt;<br>
     *
     * &lt;g:for_sale&gt;Rent&lt;/g:for_sale&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Housing</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> Boolean</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @param forSale Indicates whether this property is for sale or not.
     */
    public void setListingType(Boolean forSale);

    /**
     * Indicates whether this property is for sale or not.
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="listing_type"></a>listing_type</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Information about whether or not a property is for sale or not.
     * Acceptable values are "True" or "False".</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br>
     *
     * &lt;g:for_sale&gt;true&lt;/g:for_sale&gt;<br>
     * &lt;g:for_sale&gt;false&lt;/g:for_sale&gt;<br>
     * <em>Not acceptable:</em><br>
     *
     * &lt;g:for_sale&gt;<st1:city><st1:place>Sale</st1:place></st1:city>&lt;/g:for_sale&gt;<br>
     *
     * &lt;g:for_sale&gt;Rent&lt;/g:for_sale&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Housing</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> Boolean</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @return Indicates whether this property is for sale or not.
     */
    public Boolean getListingType();

    /**
     * Location of the property.
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
     * @param location Location of the property.
     */
    public void setLocation(String location);

    /**
     * Location of the property.
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
     * @return Location of the property.
     */
    public String getLocation();

    /**
     * Payment Methods acceptable for the property.
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
     * @param paymentAccepted Payment Methods acceptable for the property.
     */
    public void setPaymentAccepted(PaymentTypeEnumeration[] paymentAccepted);

    /**
     * Payment Methods acceptable for the property.
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
     * @return Payment Methods acceptable for the property.
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
     * Price for the property. <br>
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
     * @param price Price for the property.
     */
    public void setPrice(FloatUnit price);

    /**
     * Price for the property. <br>
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
     * @return Price for the property.
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
     * Types of property represented here. Limit 10.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="property_type"></a>property_type</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1">
     *
     * Type of property: house, apartment, condominium, etc.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:property_type&gt;house&lt;/g:property_type&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Housing</font></td>
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
     * @param propertyTypes Types of property represented here. Limit 10.
     */
    public void setPropertyTypes(String[] propertyTypes);

    /**
     * Types of property represented here.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="property_type"></a>property_type</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1">
     *
     * Type of property: house, apartment, condominium, etc.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:property_type&gt;house&lt;/g:property_type&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Housing</font></td>
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
     * @return Types of property represented here. Limit 10.
     */
    public String[] getPropertyTypes();

    /**
     * School district a property is in.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="school_district"></a>school_district</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The school district the property is in.</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:school_district&gt;<st1:place><st1:placename>Union</st1:placename>
     * <st1:placetype>School District</st1:placetype></st1:place>&lt;/g:school_district&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Housing</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param schoolDistrict School district a property is in.
     */
    public void setSchoolDistrict(String schoolDistrict);

    /**
     * School district a property is in.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="school_district"></a>school_district</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The school district the property is in.</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:school_district&gt;<st1:place><st1:placename>Union</st1:placename>
     * <st1:placetype>School District</st1:placetype></st1:place>&lt;/g:school_district&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Housing</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return School district a property is in.
     */
    public String getSchoolDistrict();

    /**
     * Percentage tax rate.
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
     * @param taxPercent Percentage tax rate.
     */
    public void setTaxPercent(Float taxPercent);

    /**
     * Percentage tax rate.
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
     * @return Percentage tax rate.
     */
    public Float getTaxPercent();

    /**
     * Geographical region a tax rate applies to.
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
     * @param taxRegion Geographical region a tax rate applies to.
     */
    public void setTaxRegion(String taxRegion);

    /**
     * Geographical region a tax rate applies to.
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
     * @return Geographical region a tax rate applies to.
     */
    public String getTaxRegion();

    /**
     * The four digit model year or year built.
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
