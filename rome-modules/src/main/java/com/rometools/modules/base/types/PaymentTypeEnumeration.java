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

public class PaymentTypeEnumeration {

    private static final HashMap<String, PaymentTypeEnumeration> lookup = new HashMap<String, PaymentTypeEnumeration>();

    public static final PaymentTypeEnumeration CASH = new PaymentTypeEnumeration("Cash");
    public static final PaymentTypeEnumeration CHECK = new PaymentTypeEnumeration("Check");
    public static final PaymentTypeEnumeration TRAVELERS_CHECK = new PaymentTypeEnumeration("Traveler�s Check");
    public static final PaymentTypeEnumeration VISA = new PaymentTypeEnumeration("Visa");
    public static final PaymentTypeEnumeration MASTERCARD = new PaymentTypeEnumeration("MasterCard");
    public static final PaymentTypeEnumeration AMERICAN_EXPRESS = new PaymentTypeEnumeration("American Express");
    public static final PaymentTypeEnumeration DISCOVER = new PaymentTypeEnumeration("Discover");
    public static final PaymentTypeEnumeration WIRE_TRANSFER = new PaymentTypeEnumeration("Wire transfer");
    public static final PaymentTypeEnumeration PAYPAL = new PaymentTypeEnumeration("Paypal");
    private final String value;

    private PaymentTypeEnumeration(final String value) {
        this.value = value;
        lookup.put(this.value.toUpperCase(), this);
    }

    public String getValue() {
        return value;
    }

    public static PaymentTypeEnumeration findByValue(final String value) {
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
