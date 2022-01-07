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
     * Actor featured in the video.
     *
     * @param actors Actor featured in the video.
     */
    public void setActors(String[] actors);

    /**
     * Actor featured in the video.
     *
     * @return Actor featured in the video.
     */
    public String[] getActors();

    /**
     * The type of apparel being offered. 
     *
     *
     * @param apparelType The type of apparel being offered.
     */
    public void setApparelType(String apparelType);

    /**
     * The type of apparel being offered. 
     *
     * @return The type of apparel being offered.
     */
    public String getApparelType();

    /**
     * Artist that created the work.
     *
     * @param artists Artist that created the work.
     */
    public void setArtists(String[] artists);

    /**
     * Artist that created the work.
     *
     * @return Artist that created the work.
     */
    public String[] getArtists();

    /**
     * Author of the item.
     *
     * @param authors Author of the item.
     */
    public void setAuthors(String[] authors);

    /**
     * Author of the item.
     *
     * @return Author of the item.
     */
    public String[] getAuthors();

    /**
     * The brand name of an item.
     *
     * @param brand The brand name of an item.
     */
    public void setBrand(String brand);

    /**
     * The brand name of an item.
     *
     * @return The brand name of an item.
     */
    public String getBrand();

    /**
     * Color of an item.
     *
     *
     * @param color Color of an item.
     */
    public void setColors(String[] color);

    /**
     * Color of an item.
     *
     * @return Color of an item.
     */
    public String[] getColors();

    /**
     * Condition of the item. For example: new, used, or refurbished.
     *
     *
     * @param condition Condition of the item. For example: new, used, or refurbished.
     */
    public void setCondition(String condition);

    /**
     * Condition of the item. For example: new, used, or refurbished.
     *
     * @return Condition of the item. For example: new, used, or refurbished.
     */
    public String getCondition();

    /**
     * Currency of the price amount for an item.
     *
     *
     * @param value Currency of the price amount for an item.
     */
    public void setCurrency(CurrencyEnumeration value);

    /**
     * Currency of the price amount for an item.
     *
     * @return Currency of the price amount for an item.
     */
    public CurrencyEnumeration getCurrency();

    /**
     * Additional instructions to explain the item’s delivery process.
     *
     *
     * @param deliveryNotes Additional instructions to explain the item’s delivery process.
     */
    public void setDeliveryNotes(String deliveryNotes);

    /**
     * Additional instructions to explain the item;s delivery process.
     *
     * @return Additional instructions to explain the item�s delivery process.
     */
    public String getDeliveryNotes();

    /**
     * The maximum distance you will deliver an item in any direction.
     *
     * @param deliveryRadius The maximum distance you will deliver an item in any direction.
     */
    public void setDeliveryRadius(FloatUnit deliveryRadius);

    /**
     * The maximum distance you will deliver an item in any direction.
     *
     * @return The maximum distance you will deliver an item in any direction.
     */
    public FloatUnit getDeliveryRadius();

    /**
     * Format of the content.
     *
     * @param format Format of the content.
     */
    public void setFormat(String[] format);

    /**
     * Format of the content.
     *
     * @return Format of the content.
     */
    public String[] getFormat();

    /**
     * A unique 10 or 13 digit number assigned to every printed book.
     *
     * @param isbn A unique 10 or 13 digit number assigned to every printed book.
     */
    public void setIsbn(String isbn);

    /**
     * A unique 10 or 13 digit number assigned to every printed book.
     *
     * @return A unique 10 or 13 digit number assigned to every printed book.
     */
    public String getIsbn();

    /**
     * Location of a property. Should include street, city, state, postal code, and country, in that
     * order.
     *
     * @param location Location of a property. Should include street, city, state, postal code, and
     *            country, in that order.
     */
    public void setLocation(String location);

    /**
     * Location of a property. Should include street, city, state, postal code, and country, in that
     * order.
     *
     * @return Location of a property. Should include street, city, state, postal code, and country,
     *         in that order.
     */
    public String getLocation();

    /**
     * Company that manufactures the item.
     *
     * @param manufacturer Company that manufactures the item.
     */
    public void setManufacturer(String manufacturer);

    /**
     * Company that manufactures the item.
     *
     * @return Company that manufactures the item.
     */
    public String getManufacturer();

    /**
     * Unique product ID code assigned by its manufacturer.
     *
     * @param manufacturerId Unique product ID code assigned by its manufacturer.
     */
    public void setManufacturerId(String manufacturerId);

    /**
     * Unique product ID code assigned by its manufacturer.
     *
     * @return Unique product ID code assigned by its manufacturer.
     */
    public String getManufacturerId();

    /**
     * Resolution of a digital imaging device.
     *
     * @param megapixels Resolution of a digital imaging device.
     */
    public void setMegapixels(FloatUnit megapixels);

    /**
     * Resolution of a digital imaging device.
     *
     * @return Resolution of a digital imaging device.
     */
    public FloatUnit getMegapixels();

    /**
     * The amount of memory included in an item.
     *
     * @param memory The amount of memory included in an item.
     */
    public void setMemory(FloatUnit memory);

    /**
     * The amount of memory included in an item.
     *
     * @return The amount of memory included in an item.
     */
    public FloatUnit getMemory();

    /**
     * Model number of the product.
     *
     *
     * @param modelNumber Model number of the product.
     */
    public void setModelNumber(String modelNumber);

    /**
     * Model number of the product.
     *
     *
     * @return Model number of the product.
     */
    public String getModelNumber();

    /**
     * Acceptable payment methods for item purchases.
     *
     *
     * @param paymentAccepted Acceptable payment methods for item purchases.
     */
    public void setPaymentAccepted(PaymentTypeEnumeration[] paymentAccepted);

    /**
     * Acceptable payment methods for item purchases.
     *
     * @return Acceptable payment methods for item purchases.
     */
    public PaymentTypeEnumeration[] getPaymentAccepted();

    /**
     * Additional instructions to explain a payment policy.
     *
     *
     * @param paymentNotes Additional instructions to explain a payment policy.
     */
    public void setPaymentNotes(String paymentNotes);

    /**
     * Additional instructions to explain a payment policy.
     *
     *
     * @return Additional instructions to explain a payment policy.
     */
    public String getPaymentNotes();

    /**
     * Whether or not an item is available for pick up.
     *
     * @param pickup Whether or not an item is available for pick up.
     */
    public void setPickup(Boolean pickup);

    /**
     * Whether or not an item is available for pick up.
     *
     *
     * @return Whether or not an item is available for pick up.
     */
    public Boolean getPickup();

    /**
     * Price of the item.
     *
     *
     * @param price Price of the item.
     */
    public void setPrice(FloatUnit price);

    /**
     * Price of the item.
     *
     *
     * @return Price of the item.
     */
    public FloatUnit getPrice();

    /**
     * The type of pricing for the item.
     *
     * @param priceType The type of pricing for the item.
     */
    public void setPriceType(PriceTypeEnumeration priceType);

    /**
     * The type of pricing for the item.
     *
     * @return The type of pricing for the item.
     */
    public PriceTypeEnumeration getPriceType();

    /**
     * The processor speed for the product.
     *
     * @param processorSpeed The processor speed for the product.
     */
    public void setProcessorSpeed(FloatUnit processorSpeed);

    /**
     * The processor speed for the product.
     *
     * @return The processor speed for the product.
     */
    public FloatUnit getProcessorSpeed();

    /**
     * The type of product being offered.
     *
     * @param productTypes The type of product being offered.
     */
    public void setProductTypes(String[] productTypes);

    /**
     * The type of product being offered.
     *
     * @return The type of product being offered.
     */
    public String[] getProductTypes();

    /**
     * Quantity available.
     *
     * @param quantity Quantity available.
     */
    public void setQuantity(Integer quantity);

    /**
     * Quantity available.
     *
     *
     * @return Quantity available.
     */
    public Integer getQuantity();

    /**
     * Shipping options available for an item.
     *
     * @param shipping Shipping options available for an item.
     */
    public void setShipping(ShippingType[] shipping);

    /**
     * Shipping options available for an item.
     *
     *
     * @return Shipping options available for an item.
     */
    public ShippingType[] getShipping();

    /**
     * Dimensions of the item, expressed in either two or three dimensions.
     *
     *
     * @param size Dimensions of the item, expressed in either two or three dimensions.
     */
    public void setSize(Size size);

    /**
     * Dimensions of the item, expressed in either two or three dimensions.
     *
     * @return Dimensions of the item, expressed in either two or three dimensions.
     */
    public Size getSize();

    /**
     * Tax rate associated with the event.
     *
     * @param taxPercent Tax rate associated with the event.
     */
    public void setTaxPercent(Float taxPercent);

    /**
     * Tax rate associated with the event.
     *
     *
     * @return Tax rate associated with the event.
     */
    public Float getTaxPercent();

    /**
     * Region where tax applies.
     *
     * @param taxRegion Region where tax applies.
     */
    public void setTaxRegion(String taxRegion);

    /**
     * Region where tax applies.
     *
     *
     * @return Region where tax applies.
     */
    public String getTaxRegion();

    /**
     * Product UPC code (Isn't that redundant?).
     *
     * @param upc Product UPC code (Isn't that redundant?).
     */
    public void setUpc(String upc);

    /**
     * Product UPC code (Isn't that redundant?).
     *
     * @return Product UPC code (Isn't that redundant?).
     */
    public String getUpc();

    /**
     * Weight of the item.
     *
     * @param weight Weight of the item.
     */
    public void setWeight(FloatUnit weight);

    /**
     * Weight of the item.
     *
     * @return Weight of the item.
     */
    public FloatUnit getWeight();
}
