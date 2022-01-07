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

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for service offerings.
 */
public interface Service extends GlobalInterface {
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
     * Location of the where the service is offered.
     *
     * @param location Location of the where the service is offered.
     */
    public void setLocation(String location);

    /**
     * Location of the where the service is offered.
     *
     * @return Location of the where the service is offered.
     */
    public String getLocation();

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
     * Price for the service. 
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
     *
     * @param priceType Price type information.
     */
    public void setPriceType(PriceTypeEnumeration priceType);

    /**
     * Price type information.
     *
     * @return Price type information.
     */
    public PriceTypeEnumeration getPriceType();

    /**
     * Quantity available.
     * @param quantity Quantity available.
     */
    public void setQuantity(Integer quantity);

    /**
     * Quantity available.
     * @return Quantity available.
     */
    public Integer getQuantity();

    /**
     * The type of service being offered.
     *
     * @param serviceType The type of service being offered.
     */
    public void setServiceType(String serviceType);

    /**
     * The type of service being offered.
     *
     * @return The type of service being offered.
     */
    public String getServiceType();

    /**
     * Shipping options available for an item.
     *
     * @param shipping Shipping options available for an item.
     */
    public void setShipping(ShippingType[] shipping);

    /**
     * Shipping options available for an item.
     * @return Shipping options available for an item.
     */
    public ShippingType[] getShipping();

    /**
     * Tax rate associated with the item.
     * @param taxPercent Tax rate associated with the event.
     */
    public void setTaxPercent(Float taxPercent);

    /**
     * Tax rate associated with the service.
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
     * @return Region where tax applies.
     */
    public String getTaxRegion();
}
