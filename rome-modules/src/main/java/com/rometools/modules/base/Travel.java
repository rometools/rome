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
import com.rometools.modules.base.types.DateTimeRange;
import com.rometools.modules.base.types.FloatUnit;
import com.rometools.modules.base.types.PaymentTypeEnumeration;
import com.rometools.modules.base.types.PriceTypeEnumeration;
import com.rometools.modules.base.types.ShippingType;

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for travel items.
 */
public interface Travel extends GlobalInterface {
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
     * Additional instructions to explain the item’s delivery process.
     *
     *
     * @return Additional instructions to explain the item’s delivery process.
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
     * Starting city and state/country of the trip.
     *
     * @param fromLocation Starting city and state/country of the trip.
     */
    public void setFromLocation(String fromLocation);

    /**
     * Starting city and state/country of the trip.
     *
     * @return Starting city and state/country of the trip.
     */
    public String getFromLocation();

    /**
     * Acceptable payment methods for item purchases.
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
     * @param paymentNotes Additional instructions to explain a payment policy.
     */
    public void setPaymentNotes(String paymentNotes);

    /**
     * Additional instructions to explain a payment policy.
     *
     * @return Additional instructions to explain a payment policy.
     */
    public String getPaymentNotes();

    /**
     * Price of the item.
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
     * Quantity available.
     *
     * @param quantity Quantity available.
     */
    public void setQuantity(Integer quantity);

    /**
     * Quantity available.
     * @return Quantity available.
     */
    public Integer getQuantity();

    /**
     * Tax rate associated with the event.
     *
     * @param taxPercent Tax rate associated with the event.
     */
    public void setTaxPercent(Float taxPercent);

    /**
     * Tax rate associated with the service.
     *
     * @return Tax rate associated with the event.
     */
    public Float getTaxPercent();

    /**
     * Region where tax applies.
     *
     *
     * @param taxRegion Region where tax applies.
     */
    public void setTaxRegion(String taxRegion);

    /**
     * Region where tax applies.
     *
     * @return Region where tax applies.
     */
    public String getTaxRegion();

    /**
     * Destination city and state/country of the trip.
     * @param toLocation Destination city and state/country of the trip.
     */
    public void setToLocation(String toLocation);

    /**
     * Destination city and state/country of the trip.
     *
     * @return Destination city and state/country of the trip.
     */
    public String getToLocation();

    /**
     * Departure date and time of the trip.
     *
     * @param travelDateRange Departure date and time of the trip.
     */
    public void setTravelDateRange(DateTimeRange travelDateRange);

    /**
     * Departure date and time of the trip.
     *
     * @return Departure date and time of the trip.
     */
    public DateTimeRange getTravelDateRange();

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
     * Shipping options available for an item.
     *
     * @return Shipping options available for an item.
     */
    public ShippingType[] getShipping();

}
