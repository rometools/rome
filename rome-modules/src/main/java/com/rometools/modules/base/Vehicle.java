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
     * @param deliveryNotes Additional instructions to explain the item’s delivery process.
     */
    public void setDeliveryNotes(String deliveryNotes);

    /**
     * Additional instructions to explain the item’s delivery process.
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
     * The vehicle manufacturer.
     *
     * @param make The vehicle manufacturer.
     */
    public void setMake(String make);

    /**
     * The vehicle manufacturer.
     *
     *
     * @return The vehicle manufacturer.
     */
    public String getMake();

    /**
     * Current mileage of the vehicle.
     *
     * @param mileage Current mileage of the vehicle.
     */
    public void setMileage(Integer mileage);

    /**
     * Current mileage of the vehicle.
     *
     *
     * @return Current mileage of the vehicle.
     */
    public Integer getMileage();

    /**
     * The vehicle model.
     *
     * @param model The vehicle model.
     */
    public void setModel(String model);

    /**
     * The vehicle model.
     *
     * @return The vehicle model.
     */
    public String getModel();

    /**
     * Payment Methods acceptable for the service.
     *
     * @param paymentAccepted Payment Methods acceptable for the service.
     */
    public void setPaymentAccepted(PaymentTypeEnumeration[] paymentAccepted);

    /**
     * Payment Methods acceptable for the service.
     *
     * @return Payment Methods acceptable for the service.
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
     *
     * @return Additional payment information.
     */
    public String getPaymentNotes();

    /**
     * Price for the service. 
     *
     * @param price Price for the service.
     */
    public void setPrice(FloatUnit price);

    /**
     * Price for the service. 
     *
     * @return Price for the service.
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
     * @return Price type information.
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
     *
     *
     * @return Quantity available.
     */
    public Integer getQuantity();

    /**
     * Shipping options available for an item.
     *
     *
     * @param shipping Shipping options available for an item.
     */
    public void setShipping(ShippingType[] shipping);

    /**
     * Shipping options available for an item.
     *
     * @return Shipping options available for an item.
     */
    public ShippingType[] getShipping();

    /**
     * Tax rate associated with the item.
     *
     * @param taxPercent Tax rate associated with the event.
     */
    public void setTaxPercent(Float taxPercent);

    /**
     * Tax rate associated with the item.
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
     * @return Region where tax applies.
     */
    public String getTaxRegion();

    /**
     * The type of vehicle: Car, motorcycle, scooter, etc.
     *
     * @param vehicleType The type of vehicle: Car, motorcycle, scooter, etc.
     */
    public void setVehicleType(String vehicleType);

    /**
     * The type of vehicle: Car, motorcycle, scooter, etc.
     * @return The type of vehicle: Car, motorcycle, scooter, etc.
     */
    public String getVehicleType();

    /**
     * Vehicle Identification Number.
     * @param vin Vehicle Identification Number.
     */
    public void setVin(String vin);

    /**
     * Vehicle Identification Number.
     * @return Vehicle Identification Number.
     */
    public String getVin();

    /**
     * The four digit model year or year built.
     * @param year The four digit model year or year built.
     */
    public void setYear(YearType year);

    /**
     * The four digit model year or year built.
     * @return The four digit model year or year built.
     */
    public YearType getYear();
}
