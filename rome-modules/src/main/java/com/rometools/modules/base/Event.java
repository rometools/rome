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
 * This is an interface for the GoogleBase plug in that exposes methods used for
 * Event entry types.
 */
public interface Event extends GlobalInterface {
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
	 * @param deliveryNotes Additional instructions to explain the item’s delivery
	 *                      process.
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
	 * @param deliveryRadius The maximum distance you will deliver an item in any
	 *                       direction.
	 */
	public void setDeliveryRadius(FloatUnit deliveryRadius);

	/**
	 * The maximum distance you will deliver an item in any direction.
	 *
	 * @return The maximum distance you will deliver an item in any direction.
	 */
	public FloatUnit getDeliveryRadius();

	/**
	 * The time during which this event takes place.
	 *
	 * @param eventDateRange The time during which this event takes place.
	 */
	public void setEventDateRange(DateTimeRange eventDateRange);

	/**
	 * The time during which this event takes place.
	 *
	 * @return The time during which this event takes place.
	 */
	public DateTimeRange getEventDateRange();

	/**
	 * Location where the event will be held.
	 *
	 * @param location Location where the event will be held.
	 */
	public void setLocation(String location);

	/**
	 * Location where the event will be held.
	 *
	 * @return Location where the event will be held.
	 */
	public String getLocation();

	/**
	 * Payment Methods acceptable for the event.
	 *
	 * @param paymentAccepted Payment Methods acceptable for the event.
	 */
	public void setPaymentAccepted(PaymentTypeEnumeration[] paymentAccepted);

	/**
	 * Payment Methods acceptable for the event.
	 *
	 * @return Payment Methods acceptable for the event.
	 */
	public PaymentTypeEnumeration[] getPaymentAccepted();

	/**
	 * Additional information about payment.
	 *
	 * @param paymentNotes Additional information about payment.
	 */
	public void setPaymentNotes(String paymentNotes);

	/**
	 * Additional information about payment.
	 *
	 * @return Additional information about payment.
	 */
	public String getPaymentNotes();

	/**
	 * Price of the event.
	 *
	 * @param price Price of the event.
	 */
	public void setPrice(FloatUnit price);

	/**
	 * Price of the event.
	 *
	 * @return Price of the event.
	 */
	public FloatUnit getPrice();

	/**
	 * Price qualifier for the even cost.
	 *
	 * @param priceType Price qualifier for the even cost.
	 */
	public void setPriceType(PriceTypeEnumeration priceType);

	/**
	 * Price qualifier for the even cost.
	 *
	 * @return Price qualifier for the even cost.
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
	 * @return Quantity available.
	 */
	public Integer getQuantity();

	/**
	 * Shipping information related to the event.
	 *
	 * @param shipping Shipping information related to the event.
	 */
	public void setShipping(ShippingType[] shipping);

	/**
	 * Shipping information related to the event.
	 *
	 * @return Shipping information related to the event.
	 */
	public ShippingType[] getShipping();

	/**
	 * Tax rate associated with the event.
	 *
	 * @param taxPercent Tax rate associated with the event.
	 */
	public void setTaxPercent(Float taxPercent);

	/**
	 * Tax rate associated with the event.
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
	 * @return Region where tax applies.
	 */
	public String getTaxRegion();
}
