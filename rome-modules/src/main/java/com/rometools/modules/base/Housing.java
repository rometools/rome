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
     *
     * @param agents An array of agent name Strings, not to exceed 10 in lenght.
     */
    public void setAgents(String[] agents);

    /**
     * An array of agent name Strings, not to exceed 10 in lenght.
     *
     * @return An array of agent name Strings, not to exceed 10 in lenght.
     */
    public String[] getAgents();

    /**
     * The area of the real estate.
     *
     *
     * @param area The area of the real estate
     */
    public void setArea(IntUnit area);

    /**
     * The area of the real estate.
     *
     * @return The area of the real estate
     */
    public IntUnit getArea();

    /**
     * The number of bathrooms.
     *
     * @param bathrooms The number of bathrooms.
     */
    public void setBathrooms(Float bathrooms);

    /**
     * The number of bathrooms.
     *
     * @return number of bathrooms
     */
    public Float getBathrooms();

    /**
     * Number of bedrooms.
     *
     * @param bedrooms Number of bedrooms.
     */
    public void setBedrooms(Integer bedrooms);

    /**
     * Number of bedrooms.
     *
     * @return Number of bedrooms
     */
    public Integer getBedrooms();

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
     * Homeowners association dues on the property.
     *
     *
     * @param hoaDues Homeowners association dues on the property.
     */
    public void setHoaDues(Float hoaDues);

    /**
     * Homeowners association dues on the property.
     *
     * @return Homeowners association dues on the property.
     */
    public Float getHoaDues();

    /**
     * Indicates whether this property is for sale or not.
     *
     * @param forSale Indicates whether this property is for sale or not.
     */
    public void setListingType(Boolean forSale);

    /**
     * Indicates whether this property is for sale or not.
     *
     *
     * @return Indicates whether this property is for sale or not.
     */
    public Boolean getListingType();

    /**
     * Location of the property.
     *
     * @param location Location of the property.
     */
    public void setLocation(String location);

    /**
     * Location of the property.
     *
     * @return Location of the property.
     */
    public String getLocation();

    /**
     * Payment Methods acceptable for the property.
     *
     *
     * @param paymentAccepted Payment Methods acceptable for the property.
     */
    public void setPaymentAccepted(PaymentTypeEnumeration[] paymentAccepted);

    /**
     * Payment Methods acceptable for the property.
     *
     * @return Payment Methods acceptable for the property.
     */
    public PaymentTypeEnumeration[] getPaymentAccepted();

    /**
     * Additional payment information.
     *
     * @param paymentNotes Additional payment information.
     */
    public void setPaymentNotes(String paymentNotes);

    /**
     * Additional payment information.
     *
     * @return Additional payment information.
     */
    public String getPaymentNotes();

    /**
     * Price for the property. 
     *
     * @param price Price for the property.
     */
    public void setPrice(FloatUnit price);

    /**
     * Price for the property.
     *
     * @return Price for the property.
     */
    public FloatUnit getPrice();

    /**
     * Price type information.
     *
     * @param priceType Price type information.
     */
    public void setPriceType(PriceTypeEnumeration priceType);

    /**
     * Price type information.
     *
     *
     * @return Price type information.
     */
    public PriceTypeEnumeration getPriceType();

    /**
     * Types of property represented here. Limit 10.
     *
     * @param propertyTypes Types of property represented here. Limit 10.
     */
    public void setPropertyTypes(String[] propertyTypes);

    /**
     * Types of property represented here.
     *
     * @return Types of property represented here. Limit 10.
     */
    public String[] getPropertyTypes();

    /**
     * School district a property is in.
     *
     * @param schoolDistrict School district a property is in.
     */
    public void setSchoolDistrict(String schoolDistrict);

    /**
     * School district a property is in.
     *
     *
     * @return School district a property is in.
     */
    public String getSchoolDistrict();

    /**
     * Percentage tax rate.
     *
     * @param taxPercent Percentage tax rate.
     */
    public void setTaxPercent(Float taxPercent);

    /**
     * Percentage tax rate.
     *
     * @return Percentage tax rate.
     */
    public Float getTaxPercent();

    /**
     * Geographical region a tax rate applies to.
     *
     * @param taxRegion Geographical region a tax rate applies to.
     */
    public void setTaxRegion(String taxRegion);

    /**
     * Geographical region a tax rate applies to.
     *
     * @return Geographical region a tax rate applies to.
     */
    public String getTaxRegion();

    /**
     * The four digit model year or year built.
     *
     * @param year The four digit model year or year built.
     */
    public void setYear(YearType year);

    /**
     * The four digit model year or year built.
     *
     * @return The four digit model year or year built.
     */
    public YearType getYear();
}
