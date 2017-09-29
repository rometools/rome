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
package com.rometools.modules.base.types;

import java.util.HashMap;

public class AvailabilityEnumeration {

    private static final HashMap<String, AvailabilityEnumeration> lookup = new HashMap<String, AvailabilityEnumeration>();

    /**
     * in stock
     *
     * You’re currently accepting orders for this product and can fulfil the purchase request.
     * You’re certain that the item will ship (or be in-transit to the customer) in a timely manner
     * because it's available for sale. You can deliver the product to all of the locations that you
     * support in your product data and account delivery settings.
     */

    public static final AvailabilityEnumeration IN_STOCK = new AvailabilityEnumeration("in stock", new String[]{"InStock", "LimitedAvailability", "OnlineOnly"});
    /**
     * out of stock
     *
     * You’re not currently accepting orders for this product, or the product is not available for
     * purchase. The product can be in stock on your landing page, but it won’t be shown in ads as
     * long as it has this status.
     */

    public static final AvailabilityEnumeration OUT_OF_STOCK = new AvailabilityEnumeration("out of stock", new String[]{"Discontinued", "InStoreOnly", "OutOfStock", "SoldOut"});
    /**
     * pre-order
     *
     * You’re currently taking orders for this product, but it’s not yet been released for sale. You
     * can use the availability_date attribute to indicate the day that the product becomes
     * available for delivery.
     */

    public static final AvailabilityEnumeration PRE_ORDER = new AvailabilityEnumeration("pre-order", new String[]{"Pre-order", "Pre-sale"});


    private final String value;

    private AvailabilityEnumeration(final String value, final String[] offerItems) {
        this.value = value;
        lookup.put(this.value.toUpperCase(), this);
        for (String offerItem: offerItems){
            lookup.put(offerItem.toUpperCase(), this);
        }
    }

    public String getValue() {
        return value;
    }

    public static AvailabilityEnumeration findByValue(final String value) {
        return lookup.get(value.toUpperCase());
    }

    @Override
    public Object clone() {
        return this;
    }

    @Override
    public String toString() {
        return value;
    }
}
