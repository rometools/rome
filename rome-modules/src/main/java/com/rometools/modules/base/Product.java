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
import com.rometools.modules.base.types.Size;

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for Products.
 */
public interface Product extends GlobalInterface {
    /**
     * Actor featured in the video. <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr bgcolor="#dddddd" valign="top">
     *
     * <td colspan="2" nowrap="nowrap"><font size="-1"><strong><a name="actor"></a>actor</strong></font></td>
     * </tr> <tr valign="top"> <td width="120"><strong><font size="-1">Details</font></strong></td>
     * <td><font size="-1">Actor featured in the video.</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td><strong><font size="-1">Example</font></strong></td> <td><font
     * size="-1">&lt;g:actor&gt;Charlie Chaplin&lt;/g:actor&gt;</font></td> </tr> <tr valign="top">
     * <td><strong><font size="-1">Attribute of</font></strong></td>
     *
     * <td><font size="-1">Products</font></td>
     *
     * </tr> <tr valign="top"> <td><strong><font size="-1">Content type</font></strong></td>
     * <td><font size="-1">string</font></td> </tr> </tbody> </table>
     *
     * @return Actor featured in the video.
     */
    String[] getActors();

    /**
     * Actor featured in the video. <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr bgcolor="#dddddd" valign="top">
     *
     * <td colspan="2" nowrap="nowrap"><font size="-1"><strong><a name="actor"></a>actor</strong></font></td>
     * </tr> <tr valign="top"> <td width="120"><strong><font size="-1">Details</font></strong></td>
     * <td><font size="-1">Actor featured in the video.</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td><strong><font size="-1">Example</font></strong></td> <td><font
     * size="-1">&lt;g:actor&gt;Charlie Chaplin&lt;/g:actor&gt;</font></td> </tr> <tr valign="top">
     * <td><strong><font size="-1">Attribute of</font></strong></td>
     *
     * <td><font size="-1">Products</font></td>
     *
     * </tr> <tr valign="top"> <td><strong><font size="-1">Content type</font></strong></td>
     * <td><font size="-1">string</font></td> </tr> </tbody> </table>
     *
     * @param actors Actor featured in the video.
     */
    void setActors(String[] actors);

    /**
     * The type of apparel being offered. <br>
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr bgcolor="#dddddd"
     * valign="top">
     *
     * <td colspan="2" nowrap="nowrap"><font size="-1"><strong><a name="apparel_type"></a>apparel_type</strong></font></td>
     * </tr> <tr valign="top"> <td width="120"><strong><font size="-1">Details</font></strong></td>
     *
     * <td><font size="-1"> The type of apparel being offered. Skirt, pants, sleepwear,
     * etc.</font></td> </tr> <tr valign="top">
     *
     * <td><strong><font size="-1">Example</font></strong></td> <td><font
     * size="-1">&lt;g:apparel_type&gt;sweater&lt;/g:apparel_type&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td><strong><font size="-1">Attribute of</font></strong></td>
     * <td><font size="-1">Products</font></td>
     *
     * </tr> <tr valign="top"> <td><strong><font size="-1">Content type</font></strong></td>
     *
     * <td><font size="-1">string</font></td> </tr> </tbody> </table>
     *
     * @return The type of apparel being offered.
     */
    String getApparelType();

    /**
     * The type of apparel being offered. <br>
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr bgcolor="#dddddd"
     * valign="top">
     *
     * <td colspan="2" nowrap="nowrap"><font size="-1"><strong><a name="apparel_type"></a>apparel_type</strong></font></td>
     * </tr> <tr valign="top"> <td width="120"><strong><font size="-1">Details</font></strong></td>
     *
     * <td><font size="-1"> The type of apparel being offered. Skirt, pants, sleepwear,
     * etc.</font></td> </tr> <tr valign="top">
     *
     * <td><strong><font size="-1">Example</font></strong></td> <td><font
     * size="-1">&lt;g:apparel_type&gt;sweater&lt;/g:apparel_type&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td><strong><font size="-1">Attribute of</font></strong></td>
     * <td><font size="-1">Products</font></td>
     *
     * </tr> <tr valign="top"> <td><strong><font size="-1">Content type</font></strong></td>
     *
     * <td><font size="-1">string</font></td> </tr> </tbody> </table>
     *
     * @param apparelType The type of apparel being offered.
     */
    void setApparelType(String apparelType);

    /**
     * Artist that created the work. <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="artist"></a>artist</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Artist that created the work.</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:artist&gt;Vincent van Gogh&lt;/g:artist&gt;</font></td>
     *
     * </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font size="-1">
     * Products</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> string</font></td> </tr> </tbody>
     * </table>
     *
     * @return Artist that created the work.
     */
    String[] getArtists();

    /**
     * Artist that created the work. <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="artist"></a>artist</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Artist that created the work.</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:artist&gt;Vincent van Gogh&lt;/g:artist&gt;</font></td>
     *
     * </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font size="-1">
     * Products</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> string</font></td> </tr> </tbody>
     * </table>
     *
     * @param artists Artist that created the work.
     */
    void setArtists(String[] artists);

    /**
     * Author of the item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="author"></a>author</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Author of the item.</font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:author&gt;John Steinbeck&lt;/g:author&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, News and Articles</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * string</font></td> </tr> </tbody> </table>
     *
     * @return Author of the item.
     */
    String[] getAuthors();

    /**
     * Author of the item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="author"></a>author</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Author of the item.</font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:author&gt;John Steinbeck&lt;/g:author&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, News and Articles</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * string</font></td> </tr> </tbody> </table>
     *
     * @param authors Author of the item.
     */
    void setAuthors(String[] authors);

    /**
     * The brand name of an item. <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="brand"></a>brand</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> The brand name of an item.</font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:brand&gt;Acme&lt;/g:brand&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Products</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td> </tr> </tbody> </table>
     *
     * @return The brand name of an item.
     */
    String getBrand();

    /**
     * The brand name of an item. <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="brand"></a>brand</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> The brand name of an item.</font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:brand&gt;Acme&lt;/g:brand&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Products</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td> </tr> </tbody> </table>
     *
     * @param brand The brand name of an item.
     */
    void setBrand(String brand);

    /**
     * Color of an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="color"></a>color</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Color of an item.</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:color&gt;Black&lt;/g:color&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td> <td><font size="-1"> Products, Vehicles</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td> </tr> </tbody> </table>
     *
     * @return Color of an item.
     */
    String[] getColors();

    /**
     * Color of an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="color"></a>color</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Color of an item.</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:color&gt;Black&lt;/g:color&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td> <td><font size="-1"> Products, Vehicles</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td> </tr> </tbody> </table>
     *
     * @param color Color of an item.
     */
    void setColors(String[] color);

    /**
     * Condition of the item. For example: new, used, or refurbished.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="condition"></a>condition</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Details</b></font></td> <td><font
     * size="-1">Condition of the item. For example: new, used, or refurbished.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td> <font size="-1">&lt;g:condition&gt;refurbished&lt;/g:condition&gt;</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1">Products, Vehicles</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">string</font></td> </tr>
     *
     * </tbody> </table>
     *
     * @return Condition of the item. For example: new, used, or refurbished.
     */
    String getCondition();

    /**
     * Condition of the item. For example: new, used, or refurbished.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="condition"></a>condition</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Details</b></font></td> <td><font
     * size="-1">Condition of the item. For example: new, used, or refurbished.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td> <font size="-1">&lt;g:condition&gt;refurbished&lt;/g:condition&gt;</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1">Products, Vehicles</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">string</font></td> </tr>
     *
     * </tbody> </table>
     *
     * @param condition Condition of the item. For example: new, used, or refurbished.
     */
    void setCondition(String condition);

    /**
     * Currency of the price amount for an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="currency"></a>currency</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">Currency of the price amount for an item. Values must be in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/currencycodeslist.html">ISO 4217</a>
     * currency code format.</font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"><em>Acceptable:</em><br> &lt;g:currency&gt;USD&lt;/g:currency&gt;<br>
     *
     * <em>Not acceptable:</em><br> &lt;g:currency&gt;US Dollars&lt;/g:currency&gt;</font></td>
     * </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td> <td><font size="-1">Events,
     * Housing, Products, Services, Travel, Vehicles</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">currencyEnumeration</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @return Currency of the price amount for an item.
     */
    CurrencyEnumeration getCurrency();

    /**
     * Currency of the price amount for an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="currency"></a>currency</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">Currency of the price amount for an item. Values must be in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/currencycodeslist.html">ISO 4217</a>
     * currency code format.</font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"><em>Acceptable:</em><br> &lt;g:currency&gt;USD&lt;/g:currency&gt;<br>
     *
     * <em>Not acceptable:</em><br> &lt;g:currency&gt;US Dollars&lt;/g:currency&gt;</font></td>
     * </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td> <td><font size="-1">Events,
     * Housing, Products, Services, Travel, Vehicles</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">currencyEnumeration</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @param value Currency of the price amount for an item.
     */
    void setCurrency(CurrencyEnumeration value);

    /**
     * Additional instructions to explain the item;s delivery process. <table border="1"
     * cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td colspan="2"
     * bgcolor="#dddddd" valign="top"><font size="-1"><b><a name="delivery_notes"></a>delivery_notes</b></font></td>
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">Additional instructions to explain the item�s delivery
     * process.</font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"><b>Example</b></font></td> <td> <font size="-1">&lt;g:delivery_notes&gt;Items
     * usually shipped within 24 hours.&lt;g:/delivery_notes&gt;<br>
     *
     * </font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Attribute of</b></font></td> <td>
     * <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.
     * </font></td> </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content
     * type</b></font></td>
     *
     * <td><font size="-1">string</font></td> </tr> </tbody> </table>
     *
     * @return Additional instructions to explain the item�s delivery process.
     */
    String getDeliveryNotes();

    /**
     * Additional instructions to explain the item’s delivery process.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="delivery_notes"></a>delivery_notes</b></font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">Additional instructions to explain the item’s delivery
     * process.</font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"><b>Example</b></font></td> <td> <font size="-1">&lt;g:delivery_notes&gt;Items
     * usually shipped within 24 hours.&lt;g:/delivery_notes&gt;<br>
     *
     * </font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Attribute of</b></font></td> <td>
     * <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.
     * </font></td> </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content
     * type</b></font></td>
     *
     * <td><font size="-1">string</font></td> </tr> </tbody> </table>
     *
     * @param deliveryNotes Additional instructions to explain the item’s delivery process.
     */
    void setDeliveryNotes(String deliveryNotes);

    /**
     * The maximum distance you will deliver an item in any direction. <table border="1"
     * cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td colspan="2"
     * bgcolor="#dddddd" valign="top"><font size="-1"><b><a name="delivery_radius"></a>delivery_radius</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Details</b></font></td> <td><font
     * size="-1">The maximum distance you will deliver an item in any direction. </font></td> </tr>
     * <tr valign="top"> <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td> <font size="-1">&lt;g:delivery_radius&gt;10&lt;g:/delivery_radius&gt; </font> <br> <font
     * size="-1">&lt;g:delivery_radius&gt;10km&lt;/g:delivery_radius&gt;</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td> <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.
     * </font></td> </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content
     * type</b></font></td> <td><font size="-1">floatUnit</font></td> </tr>
     *
     * </tbody> </table>
     *
     * @return The maximum distance you will deliver an item in any direction.
     */
    FloatUnit getDeliveryRadius();

    /**
     * The maximum distance you will deliver an item in any direction. <table border="1"
     * cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td colspan="2"
     * bgcolor="#dddddd" valign="top"><font size="-1"><b><a name="delivery_radius"></a>delivery_radius</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Details</b></font></td> <td><font
     * size="-1">The maximum distance you will deliver an item in any direction. </font></td> </tr>
     * <tr valign="top"> <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td> <font size="-1">&lt;g:delivery_radius&gt;10&lt;g:/delivery_radius&gt; </font> <br> <font
     * size="-1">&lt;g:delivery_radius&gt;10km&lt;/g:delivery_radius&gt;</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td> <font size="-1">Events, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.
     * </font></td> </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content
     * type</b></font></td> <td><font size="-1">floatUnit</font></td> </tr>
     *
     * </tbody> </table>
     *
     * @param deliveryRadius The maximum distance you will deliver an item in any direction.
     */
    void setDeliveryRadius(FloatUnit deliveryRadius);

    /**
     * Format of the content. <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody>
     * <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="format"></a>format</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Format of the content.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Example</b></font></td> <td><font
     * size="-1">&lt;g:format&gt;DVD&lt;/g:format&gt;<br>
     *
     * &lt;g:format&gt;CD&lt;/g:format&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Products</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td> </tr> </tbody> </table>
     *
     * @return Format of the content.
     */
    String[] getFormat();

    /**
     * Format of the content. <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody>
     * <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="format"></a>format</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Format of the content.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Example</b></font></td> <td><font
     * size="-1">&lt;g:format&gt;DVD&lt;/g:format&gt;<br>
     *
     * &lt;g:format&gt;CD&lt;/g:format&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Products</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td> </tr> </tbody> </table>
     *
     * @param format Format of the content.
     */
    void setFormat(String[] format);

    /**
     * A unique 10 or 13 digit number assigned to every printed book. <table border="1"
     * cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td colspan="2"
     * bgcolor="#dddddd" valign="top"><font size="-1"><b><a name="isbn"></a>isbn</b></font></td>
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">A unique 10 or 13 digit number assigned to every printed
     * book.</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"><b>Example</b></font></td> <td><font
     * size="-1">&lt;g:isbn&gt;0451524233&lt;/g:isbn&gt;</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"><b>Attribute of</b></font></td> <td><font
     * size="-1">Products</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">string</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @return A unique 10 or 13 digit number assigned to every printed book.
     */
    String getIsbn();

    /**
     * A unique 10 or 13 digit number assigned to every printed book. <table border="1"
     * cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td colspan="2"
     * bgcolor="#dddddd" valign="top"><font size="-1"><b><a name="isbn"></a>isbn</b></font></td>
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">A unique 10 or 13 digit number assigned to every printed
     * book.</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"><b>Example</b></font></td> <td><font
     * size="-1">&lt;g:isbn&gt;0451524233&lt;/g:isbn&gt;</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"><b>Attribute of</b></font></td> <td><font
     * size="-1">Products</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">string</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @param isbn A unique 10 or 13 digit number assigned to every printed book.
     */
    void setIsbn(String isbn);

    /**
     * Location of a property. Should include street, city, state, postal code, and country, in that
     * order. <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr
     * valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="location"></a>location</b></font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font size="-1"> Location of
     * a property. Should include street, city, state, postal code, and country, in that order.
     * </font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br> &lt;g:location&gt;<st1:place>123 Main St,
     * <st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     *
     * <em>Not acceptable:</em><br> &lt;g:location&gt;<st1:place><st1:city>123</st1:city> Main St,,
     * <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     * &lt;g:location&gt; <st1:place><st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>,
     * <st1:postalcode>12345</st1:postalcode>, <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, News and Articles, People
     * profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * locationType</font></td> </tr> </tbody> </table>
     *
     * @return Location of a property. Should include street, city, state, postal code, and country,
     * in that order.
     */
    String getLocation();

    /**
     * Location of a property. Should include street, city, state, postal code, and country, in that
     * order. <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr
     * valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="location"></a>location</b></font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font size="-1"> Location of
     * a property. Should include street, city, state, postal code, and country, in that order.
     * </font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br> &lt;g:location&gt;<st1:place>123 Main St,
     * <st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     *
     * <em>Not acceptable:</em><br> &lt;g:location&gt;<st1:place><st1:city>123</st1:city> Main St,,
     * <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     * &lt;g:location&gt; <st1:place><st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>,
     * <st1:postalcode>12345</st1:postalcode>, <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, News and Articles, People
     * profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * locationType</font></td> </tr> </tbody> </table>
     *
     * @param location Location of a property. Should include street, city, state, postal code, and
     *                 country, in that order.
     */
    void setLocation(String location);

    /**
     * Company that manufactures the item. <table border="1" cellpadding="5" cellspacing="0"
     * width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"> <font
     * size="-1"><b><a name="manufacturer"></a>manufacturer</b></font></td> </tr>
     *
     * <tr valign="top"> <td width="120"> <font size="-1"><b>Details</b></font></td> <td>
     *
     * <font size="-1">Company that manufactures the item.</font></td> </tr> <tr valign="top">
     *
     * <td width="120"> <font size="-1"><b>Example</b></font></td> <td> <font
     * size="-1">&lt;g:manufacturer&gt;Acme, Inc&lt;/g:manufacturer&gt;</font></td>
     *
     * </tr> <tr valign="top">
     *
     * <td width="120"> <font size="-1"><b>Attribute of</b></font></td> <td> <font
     * size="-1">Products</font></td> </tr> <tr valign="top">
     *
     * <td width="120">
     *
     * <font size="-1"><b>Content type</b></font></td> <td> <font size="-1">string</font></td> </tr>
     * </tbody> </table>
     *
     * @return Company that manufactures the item.
     */
    String getManufacturer();

    /**
     * Company that manufactures the item. <table border="1" cellpadding="5" cellspacing="0"
     * width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"> <font
     * size="-1"><b><a name="manufacturer"></a>manufacturer</b></font></td> </tr>
     *
     * <tr valign="top"> <td width="120"> <font size="-1"><b>Details</b></font></td> <td>
     *
     * <font size="-1">Company that manufactures the item.</font></td> </tr> <tr valign="top">
     *
     * <td width="120"> <font size="-1"><b>Example</b></font></td> <td> <font
     * size="-1">&lt;g:manufacturer&gt;Acme, Inc&lt;/g:manufacturer&gt;</font></td>
     *
     * </tr> <tr valign="top">
     *
     * <td width="120"> <font size="-1"><b>Attribute of</b></font></td> <td> <font
     * size="-1">Products</font></td> </tr> <tr valign="top">
     *
     * <td width="120">
     *
     * <font size="-1"><b>Content type</b></font></td> <td> <font size="-1">string</font></td> </tr>
     * </tbody> </table>
     *
     * @param manufacturer Company that manufactures the item.
     */
    void setManufacturer(String manufacturer);

    /**
     * Unique product ID code assigned by its manufacturer.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="manufacturer_id"></a>manufacturer_id</b></font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Details</b></font></td> <td><font size="-1">
     *
     * Unique product ID code assigned by its manufacturer.</font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:manufacturer_id&gt;030779A&lt;/g:manufacturer_id&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Products</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * string</font></td> </tr> </tbody> </table>
     *
     * @return Unique product ID code assigned by its manufacturer.
     */
    String getManufacturerId();

    /**
     * Unique product ID code assigned by its manufacturer.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="manufacturer_id"></a>manufacturer_id</b></font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Details</b></font></td> <td><font size="-1">
     *
     * Unique product ID code assigned by its manufacturer.</font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:manufacturer_id&gt;030779A&lt;/g:manufacturer_id&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Products</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * string</font></td> </tr> </tbody> </table>
     *
     * @param manufacturerId Unique product ID code assigned by its manufacturer.
     */
    void setManufacturerId(String manufacturerId);

    /**
     * Resolution of a digital imaging device. <table border="1" cellpadding="5" cellspacing="0"
     * width="640"> <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font
     * size="-1">
     *
     * <b><a name="megapixels"></a>megapixels</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font
     * size="-1"> Resolution of a digital imaging device.</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:megapixels&gt;5.2 MP&lt;/g:megapixels&gt;</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font size="-1">
     * Products</font></td> </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     *
     * <td><font size="-1"> floatUnit</font></td> </tr> </tbody> </table>
     *
     * @return Resolution of a digital imaging device.
     */
    FloatUnit getMegapixels();

    /**
     * Resolution of a digital imaging device. <table border="1" cellpadding="5" cellspacing="0"
     * width="640"> <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font
     * size="-1">
     *
     * <b><a name="megapixels"></a>megapixels</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font
     * size="-1"> Resolution of a digital imaging device.</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:megapixels&gt;5.2 MP&lt;/g:megapixels&gt;</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font size="-1">
     * Products</font></td> </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     *
     * <td><font size="-1"> floatUnit</font></td> </tr> </tbody> </table>
     *
     * @param megapixels Resolution of a digital imaging device.
     */
    void setMegapixels(FloatUnit megapixels);

    /**
     * The amount of memory included in an item. <table border="1" cellpadding="5" cellspacing="0"
     * width="640"> <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd"><font size="-1">
     * <b><a name="memory"></a>memory</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font
     * size="-1"> The amount of memory included in an item.</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:memory&gt;128 MB&lt;/g:memory&gt;</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td> <td><font size="-1"> Products</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     *
     * <td><font size="-1"> floatUnit</font></td> </tr> </tbody> </table>
     *
     * @return The amount of memory included in an item.
     */
    FloatUnit getMemory();

    /**
     * The amount of memory included in an item. <table border="1" cellpadding="5" cellspacing="0"
     * width="640"> <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd"><font size="-1">
     * <b><a name="memory"></a>memory</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font
     * size="-1"> The amount of memory included in an item.</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:memory&gt;128 MB&lt;/g:memory&gt;</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td> <td><font size="-1"> Products</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     *
     * <td><font size="-1"> floatUnit</font></td> </tr> </tbody> </table>
     *
     * @param memory The amount of memory included in an item.
     */
    void setMemory(FloatUnit memory);

    /**
     * Model number of the product.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd"><font size="-1"> <b><a name="model_number"></a>model_number</b></font></td>
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font
     * size="-1">
     *
     * Model number of the product.</font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Example</b></font></td> <td><font size="-1"> &lt;g:model_number&gt;1924863&lt;/g:model_number&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Products</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * string</font></td> </tr> </tbody> </table>
     *
     * @return Model number of the product.
     */
    String getModelNumber();

    /**
     * Model number of the product.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd"><font size="-1"> <b><a name="model_number"></a>model_number</b></font></td>
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font
     * size="-1">
     *
     * Model number of the product.</font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Example</b></font></td> <td><font size="-1"> &lt;g:model_number&gt;1924863&lt;/g:model_number&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Products</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * string</font></td> </tr> </tbody> </table>
     *
     * @param modelNumber Model number of the product.
     */
    void setModelNumber(String modelNumber);

    /**
     * Acceptable payment methods for item purchases.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="payment_accepted"></a>payment_accepted</b></font></td> </tr> <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font size="-1"> Acceptable
     * payment methods for item purchases. Acceptable values are “Cash,” “Check,” “Traveler’s
     * Check,” “Visa,” “MasterCard,”
     *
     * “American Express,” “Discover,” or “Wire transfer.” If you accept more than one method,
     * include multiple instances of the &lt;payment_accepted&gt; attribute for each acceptable
     * method.</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> <em>Acceptable:</em><br> &lt;g:payment_accepted&gt;Cash&lt;/g:payment_accepted&gt;<br>
     *
     * &lt;g:payment_accepted&gt;Check&lt;/g:payment_accepted&gt;<br> <em>Not acceptable:</em><br>
     * &lt;g:payment_accepted&gt;Cash Check&lt;/g:payment_accepted&gt;</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td> </tr>
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font
     * size="-1">
     *
     * paymentMethodEnumeration</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @return Acceptable payment methods for item purchases.
     */
    PaymentTypeEnumeration[] getPaymentAccepted();

    /**
     * Acceptable payment methods for item purchases.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="payment_accepted"></a>payment_accepted</b></font></td> </tr> <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font size="-1"> Acceptable
     * payment methods for item purchases. Acceptable values are “Cash,” “Check,” “Traveler’s
     * Check,” “Visa,” “MasterCard,”
     *
     * “American Express,” “Discover,” or “Wire transfer.” If you accept more than one method,
     * include multiple instances of the &lt;payment_accepted&gt; attribute for each acceptable
     * method.</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> <em>Acceptable:</em><br> &lt;g:payment_accepted&gt;Cash&lt;/g:payment_accepted&gt;<br>
     *
     * &lt;g:payment_accepted&gt;Check&lt;/g:payment_accepted&gt;<br> <em>Not acceptable:</em><br>
     * &lt;g:payment_accepted&gt;Cash Check&lt;/g:payment_accepted&gt;</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td> </tr>
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font
     * size="-1">
     *
     * paymentMethodEnumeration</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @param paymentAccepted Acceptable payment methods for item purchases.
     */
    void setPaymentAccepted(PaymentTypeEnumeration[] paymentAccepted);

    /**
     * Additional instructions to explain a payment policy.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="payment_notes"></a>payment_notes</b></font></td>
     *
     * </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font size="-1"> Additional
     * instructions to explain a payment policy.</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:payment_notes&gt;Cash only for local
     * orders.&lt;/g:payment_notes&gt;</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font size="-1">
     *
     * Events, Housing, Products, Services, Travel, Vehicles</font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> string</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @return Additional instructions to explain a payment policy.
     */
    String getPaymentNotes();

    /**
     * Additional instructions to explain a payment policy.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="payment_notes"></a>payment_notes</b></font></td>
     *
     * </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font size="-1"> Additional
     * instructions to explain a payment policy.</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:payment_notes&gt;Cash only for local
     * orders.&lt;/g:payment_notes&gt;</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font size="-1">
     *
     * Events, Housing, Products, Services, Travel, Vehicles</font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> string</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @param paymentNotes Additional instructions to explain a payment policy.
     */
    void setPaymentNotes(String paymentNotes);

    /**
     * Whether or not an item is available for pick up.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="pickup"></a>pickup</b></font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Information about whether or not an item is available for pick up.
     * Acceptable values are "True and "False". </font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1">
     *
     * <em>Acceptable:</em><br> &lt;g:pickup&gt;True&lt;/g:pickup&gt;<br> <em>Not
     * acceptable:</em><br> &lt;g:pickup&gt;Not on Tuesdays&lt;/g:pickup&gt;</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font size="-1">
     * Products</font></td> </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> boolean</font></td> </tr> </tbody>
     * </table>
     *
     * @return Whether or not an item is available for pick up.
     */
    Boolean getPickup();

    /**
     * Whether or not an item is available for pick up.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="pickup"></a>pickup</b></font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Information about whether or not an item is available for pick up.
     * Acceptable values are "True and "False". </font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1">
     *
     * <em>Acceptable:</em><br> &lt;g:pickup&gt;True&lt;/g:pickup&gt;<br> <em>Not
     * acceptable:</em><br> &lt;g:pickup&gt;Not on Tuesdays&lt;/g:pickup&gt;</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font size="-1">
     * Products</font></td> </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> boolean</font></td> </tr> </tbody>
     * </table>
     *
     * @param pickup Whether or not an item is available for pick up.
     */
    void setPickup(Boolean pickup);

    /**
     * Price of the item.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="price"></a>price</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Details</b></font></td> <td><font size="-1"> Prices can be a single price, 0
     * (free), or blank if not applicable. Numerice values only. When used as a sub-attribute of
     * &lt;shipping&gt;, the value included reflects the price of shipping.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> <em>Acceptable:</em><br> &lt;g:price&gt;5.95&lt;/g:price&gt;<br>
     *
     * &lt;g:price&gt;0&lt;/g:price&gt;<br> <em>Not acceptable:</em><br>
     * &lt;g:price&gt;5,95&lt;/g:price&gt;<br> &lt;g:price&gt;5.00 – 10.00&lt;/g:price&gt;<br>
     *
     * &lt;g:price&gt;100 or best offer&lt;/g:price&gt;<br> &lt;g:price&gt;free&lt;/g:price&gt;</font></td>
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td> </tr>
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Content type</b></font></td> <td
     * valign="top"><font size="-1">floatUnit</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @return Price of the item.
     */
    FloatUnit getPrice();

    /**
     * Price of the item.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="price"></a>price</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Details</b></font></td> <td><font size="-1"> Prices can be a single price, 0
     * (free), or blank if not applicable. Numerice values only. When used as a sub-attribute of
     * &lt;shipping&gt;, the value included reflects the price of shipping.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> <em>Acceptable:</em><br> &lt;g:price&gt;5.95&lt;/g:price&gt;<br>
     *
     * &lt;g:price&gt;0&lt;/g:price&gt;<br> <em>Not acceptable:</em><br>
     * &lt;g:price&gt;5,95&lt;/g:price&gt;<br> &lt;g:price&gt;5.00 – 10.00&lt;/g:price&gt;<br>
     *
     * &lt;g:price&gt;100 or best offer&lt;/g:price&gt;<br> &lt;g:price&gt;free&lt;/g:price&gt;</font></td>
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td> </tr>
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Content type</b></font></td> <td
     * valign="top"><font size="-1">floatUnit</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @param price Price of the item.
     */
    void setPrice(FloatUnit price);

    /**
     * The type of pricing for the item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="price_type"></a>price_type</b></font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Details</b></font></td> <td><font size="-1">The type of
     * pricing for the item. Acceptable values are “negotiable,” or “starting.” The default is
     * “starting”</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"><em>Acceptable:</em><br> &lt;g:price_type&gt;starting&lt;/g:price_type&gt;<br>
     * <em>Not acceptable:</em><br> &lt;g:price_type&gt;100 OBO&lt;/g:price_type&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font
     * size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td> </tr> <tr
     * valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * priceTypeEnumeration</font></td> </tr> </tbody> </table>
     *
     * @return The type of pricing for the item.
     */
    PriceTypeEnumeration getPriceType();

    /**
     * The type of pricing for the item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="price_type"></a>price_type</b></font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Details</b></font></td> <td><font size="-1">The type of
     * pricing for the item. Acceptable values are “negotiable,” or “starting.” The default is
     * “starting”</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"><em>Acceptable:</em><br> &lt;g:price_type&gt;starting&lt;/g:price_type&gt;<br>
     * <em>Not acceptable:</em><br> &lt;g:price_type&gt;100 OBO&lt;/g:price_type&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font
     * size="-1"> Events, Housing, Products, Services, Travel, Vehicles</font></td> </tr> <tr
     * valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * priceTypeEnumeration</font></td> </tr> </tbody> </table>
     *
     * @param priceType The type of pricing for the item.
     */
    void setPriceType(PriceTypeEnumeration priceType);

    /**
     * The processor speed for the product.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="processor_speed"></a>processor_speed</b></font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> The processor speed for the product.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:processor_speed&gt;2&lt;/g:processor_speed&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font
     * size="-1"> Products</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * floatUnit</font></td> </tr> </tbody> </table>
     *
     * @return The processor speed for the product.
     */
    FloatUnit getProcessorSpeed();

    /**
     * The processor speed for the product.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="processor_speed"></a>processor_speed</b></font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> The processor speed for the product.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:processor_speed&gt;2&lt;/g:processor_speed&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font
     * size="-1"> Products</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * floatUnit</font></td> </tr> </tbody> </table>
     *
     * @param processorSpeed The processor speed for the product.
     */
    void setProcessorSpeed(FloatUnit processorSpeed);

    /**
     * The type of product being offered. <table border="1" cellpadding="5" cellspacing="0"
     * width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="product_type"></a>product_type</b></font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1">
     *
     * The type of product being offered. Toys, books, flowers, etc.</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:product_type&gt;electronics&lt;/g:product_type&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Products</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * string</font></td> </tr> </tbody> </table>
     *
     * @return The type of product being offered.
     */
    String[] getProductTypes();

    /**
     * The type of product being offered. <table border="1" cellpadding="5" cellspacing="0"
     * width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     * <b><a name="product_type"></a>product_type</b></font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1">
     *
     * The type of product being offered. Toys, books, flowers, etc.</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:product_type&gt;electronics&lt;/g:product_type&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Products</font></td> </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font size="-1">
     * string</font></td> </tr> </tbody> </table>
     *
     * @param productTypes The type of product being offered.
     */
    void setProductTypes(String[] productTypes);

    /**
     * Quantity available.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="quantity"></a>quantity</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font
     * size="-1"> The number of units available for purchase. This attribute can be left blank if
     * you have a large quantity or if it is not applicable. </font></td>
     *
     * </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * <em>Acceptable:</em><br> &lt;g:quantity&gt;18&lt;/g:quantity&gt;<br>
     *
     * &lt;g:quantity&gt;0&lt;/g:quantity&gt;<br>
     *
     * <em>Not acceptable:</em><br> &lt;g:quantity&gt;out of stock&lt;/g:quantity&gt;</font></td>
     * </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Products, Services, Travel, Vehicles</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1">
     *
     * integer</font></td> </tr> </tbody> </table>
     *
     * @return Quantity available.
     */
    Integer getQuantity();

    /**
     * Quantity available.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="quantity"></a>quantity</b></font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font
     * size="-1"> The number of units available for purchase. This attribute can be left blank if
     * you have a large quantity or if it is not applicable. </font></td>
     *
     * </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * <em>Acceptable:</em><br> &lt;g:quantity&gt;18&lt;/g:quantity&gt;<br>
     *
     * &lt;g:quantity&gt;0&lt;/g:quantity&gt;<br>
     *
     * <em>Not acceptable:</em><br> &lt;g:quantity&gt;out of stock&lt;/g:quantity&gt;</font></td>
     * </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Events, Products, Services, Travel, Vehicles</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1">
     *
     * integer</font></td> </tr> </tbody> </table>
     *
     * @param quantity Quantity available.
     */
    void setQuantity(Integer quantity);

    /**
     * Shipping options available for an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="shipping"></a>shipping</b></font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Shipping options available for an item. Up to 10 shipping options can be
     * included for each item. Three sub-attributes are included in the shipping attribute: <ul
     * type="disc">
     *
     * <li>service = The type of service used to ship an item. Acceptable values are 'FedEx', 'UPS',
     * 'DHL', 'Mail', and 'Other'</li> <li>country = The country an item will ship to. Only
     * acceptable values are<b> </b>ISO 3166 country codes.</li> <li>price = the price of
     * shipping.</li> </ul> </font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td> <td><font size="-1"> &lt;g:shipping&gt;<br>
     * &lt;g:country&gt;US&lt;/g:country&gt;<br>
     *
     * &lt;g:service&gt;UPS&lt;/g:shipping&gt;<br>
     *
     * &lt;g:price&gt;35.95&lt;/g:price&gt;<br> &lt;/g:shipping&gt;</font></td> </tr> <tr
     * valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Products</font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> shippingType</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @return Shipping options available for an item.
     */
    ShippingType[] getShipping();

    /**
     * Shipping options available for an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="shipping"></a>shipping</b></font></td> </tr> <tr valign="top"> <td
     * width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Shipping options available for an item. Up to 10 shipping options can be
     * included for each item. Three sub-attributes are included in the shipping attribute: <ul
     * type="disc">
     *
     * <li>service = The type of service used to ship an item. Acceptable values are 'FedEx', 'UPS',
     * 'DHL', 'Mail', and 'Other'</li> <li>country = The country an item will ship to. Only
     * acceptable values are<b> </b>ISO 3166 country codes.</li> <li>price = the price of
     * shipping.</li> </ul> </font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td> <td><font size="-1"> &lt;g:shipping&gt;<br>
     * &lt;g:country&gt;US&lt;/g:country&gt;<br>
     *
     * &lt;g:service&gt;UPS&lt;/g:shipping&gt;<br>
     *
     * &lt;g:price&gt;35.95&lt;/g:price&gt;<br> &lt;/g:shipping&gt;</font></td> </tr> <tr
     * valign="top">
     *
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Products</font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> shippingType</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @param shipping Shipping options available for an item.
     */
    void setShipping(ShippingType[] shipping);

    /**
     * Dimensions of the item, expressed in either two or three dimensions.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="size"></a>size</b></font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Dimensions of the item, expressed in either two or three
     * dimensions.</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:size&gt;12x10x4&lt;/g:size&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td> <td><font size="-1"> Products</font></td> </tr> <tr
     * valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td> </tr> </tbody> </table>
     *
     * @return Dimensions of the item, expressed in either two or three dimensions.
     */
    Size getSize();

    /**
     * Dimensions of the item, expressed in either two or three dimensions.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="size"></a>size</b></font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Dimensions of the item, expressed in either two or three
     * dimensions.</font></td> </tr> <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font size="-1">
     * &lt;g:size&gt;12x10x4&lt;/g:size&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td> <td><font size="-1"> Products</font></td> </tr> <tr
     * valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td> </tr> </tbody> </table>
     *
     * @param size Dimensions of the item, expressed in either two or three dimensions.
     */
    void setSize(Size size);

    /**
     * Tax rate associated with the event.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="tax_percent"></a>tax_percent</b></font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td> <td><font size="-1"> Tax rate as a percentage.</font></td> </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:tax_percent&gt;8.2&lt;g:/tax_percent&gt;</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td> <td><font size="-1">
     *
     * Products, Events</font></td> </tr> <tr valign="top"> <td width="120"><font size="-1">
     * <b>Content type</b></font></td>
     *
     * <td><font size="-1"> percentType</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @return Tax rate associated with the event.
     */
    Float getTaxPercent();

    /**
     * Tax rate associated with the event.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="tax_percent"></a>tax_percent</b></font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td> <td><font size="-1"> Tax rate as a percentage.</font></td> </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:tax_percent&gt;8.2&lt;g:/tax_percent&gt;</font></td> </tr> <tr
     * valign="top"> <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td> <td><font size="-1">
     *
     * Products, Events</font></td> </tr> <tr valign="top"> <td width="120"><font size="-1">
     * <b>Content type</b></font></td>
     *
     * <td><font size="-1"> percentType</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @param taxPercent Tax rate associated with the event.
     */
    void setTaxPercent(Float taxPercent);

    /**
     * Region where tax applies.
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="tax_region"></a>tax_region</b></font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font
     * size="-1"> Geographical region a tax rate applies to.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td> <td><font size="-1"> &lt;g:tax_region&gt;California&lt;/g:tax_region&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Product, Events,</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font
     * size="-1">string</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @return Region where tax applies.
     */
    String getTaxRegion();

    /**
     * Region where tax applies.
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640"> <tbody> <tr valign="top"> <td
     * colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="tax_region"></a>tax_region</b></font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Details</b></font></td> <td><font
     * size="-1"> Geographical region a tax rate applies to.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td> <td><font size="-1"> &lt;g:tax_region&gt;California&lt;/g:tax_region&gt;</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Product, Events,</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Content type</b></font></td> <td><font
     * size="-1">string</font></td>
     *
     * </tr> </tbody> </table>
     *
     * @param taxRegion Region where tax applies.
     */
    void setTaxRegion(String taxRegion);

    /**
     * Product UPC code (Isn't that redundant?).
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="upc"></a>upc</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Product UPC code. </font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:upc&gt;834721479305&lt;/g:upc&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font
     * size="-1"> Products</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> string</font></td> </tr> </tbody>
     * </table>
     *
     * @return Product UPC code (Isn't that redundant?).
     */
    String getUpc();

    /**
     * Product UPC code (Isn't that redundant?).
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top"> <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="upc"></a>upc</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Product UPC code. </font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:upc&gt;834721479305&lt;/g:upc&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font
     * size="-1"> Products</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> string</font></td> </tr> </tbody>
     * </table>
     *
     * @param upc Product UPC code (Isn't that redundant?).
     */
    void setUpc(String upc);

    /**
     * Weight of the item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top">
     *
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="weight"></a>weight</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Weight of the item.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:weight&gt;5&lt;/g:weight&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font
     * size="-1"> Products</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> floatUnit</font></td> </tr> </tbody>
     * </table>
     *
     * @return Weight of the item.
     */
    FloatUnit getWeight();

    /**
     * Weight of the item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody> <tr valign="top">
     *
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="weight"></a>weight</b></font></td> </tr> <tr valign="top"> <td width="120"><font
     * size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Weight of the item.</font></td>
     *
     * </tr> <tr valign="top"> <td width="120"><font size="-1"> <b>Example</b></font></td> <td><font
     * size="-1"> &lt;g:weight&gt;5&lt;/g:weight&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1"> <b>Attribute of</b></font></td> <td><font
     * size="-1"> Products</font></td> </tr>
     *
     * <tr valign="top"> <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td> <td><font size="-1"> floatUnit</font></td> </tr> </tbody>
     * </table>
     *
     * @param weight Weight of the item.
     */
    void setWeight(FloatUnit weight);


    /**
     * Use the google_product_category [google_product_category] attribute to indicate the category
     * of your item based on the Google product taxonomy. Categorising your product helps ensure
     * that your ad is shown with the right search results.
     *
     * When to use: https://support.google.com/merchants/answer/6324436
     *
     *
     * <g:google_product_category>2271</g:google_product_category> or <g:google_product_category>Clothing
     * &amp; Accessories &gt; Clothing &gt; Dresses</g:google_product_category>
     */
    String getGoogleProductCategory();

    /**
     *
     * @param googleProductCategory
     */
    void setGoogleProductCategory(String googleProductCategory);


    /**
     * Global Trade Item Numbers (GTINs)
     *
     * https://support.google.com/merchants/answer/6324461
     *
     *
     * XML feeds is	<bold><g:gtin>3234567890126</g:gtin></bold>
     *
     * A GTIN uniquely identifies your product. This specific number helps us to make your ad richer
     * and easier for users to find. Products submitted without any unique product identifiers are
     * difficult to classify and may not be able to take advantage of all Google Shopping features.
     */

    String getGtin();

    void setGtin(String gtin);


    /**
     * Manufacturer Part Number (MPN)
     *
     * https://support.google.com/merchants/answer/6324482
     *
     * XML feeds is	<g:mpn>GO12345OOGLE</g:mpn>
     *
     * MPNs are used to uniquely identify a specific product among all products from the same
     * manufacturer. Users might search Google Shopping specifically for an MPN, so providing the
     * MPN can help to ensure that your product is shown in relevant situations.
     */
    String getMpn();

    void setMpn(String mpn);

}
