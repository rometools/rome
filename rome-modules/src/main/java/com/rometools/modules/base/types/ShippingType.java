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

/**
 * This class represents a specific shipping option for an item.
 */
public class ShippingType implements CloneableType {
    /**
     * price of the shipping.
     */
    private final FloatUnit price;
    /**
     * Service used.
     */
    private final ServiceEnumeration service;
    /**
     * Country to ship to
     */
    private final String country;

    /**
     * @param price The price of the shipping option
     * @param service Shipping service used.
     * @param country Country shipped to.
     */
    public ShippingType(final FloatUnit price, final ServiceEnumeration service, final String country) {
        this.price = price;
        this.service = service;
        this.country = country;
    }

    /**
     * Returns the destination country.
     *
     * @return Returns the destination country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the price of this shipping option.
     *
     * @return Returns the price of this shipping option.
     */
    public FloatUnit getPrice() {
        return price;
    }

    /**
     * Returns the ServiceEnumeration instance for the shipping service used.
     *
     * @return Returns the ServiceEnumeration instance for the shipping service used.
     */
    public ServiceEnumeration getService() {
        return service;
    }

    /**
     * Clones this object.
     *
     * @return Duplicate ShippingType object.
     */
    @Override
    public Object clone() {
        return new ShippingType(price, service, country);
    }

    /**
     * Returns a String representation of this object.
     *
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        return country + " " + price + " " + service;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ShippingType)) {
            return false;
        }
        if (toString().equals(o.toString())) {
            return true;
        }
        return false;
    }

    /**
     * Enumeration class of valid options for ServiceType.
     */
    public static class ServiceEnumeration {
        /**
         * Looks up a ServiceEnumeration based on the string value.
         */
        private static final HashMap<String, ServiceEnumeration> lookup = new HashMap<String, ServiceEnumeration>();
        /**
         * Standard
         */
        public static final ServiceEnumeration STANDARD = new ServiceEnumeration("Standard");
        /**
         * Freight
         */
        public static final ServiceEnumeration FREIGHT = new ServiceEnumeration("Freight");
        /**
         * Overnight
         */
        public static final ServiceEnumeration OVERNIGHT = new ServiceEnumeration("Overnight");

        /**
         * String value
         */
        private final String value;

        /**
         * @param value String value to encapsulate.
         */
        private ServiceEnumeration(final String value) {
            this.value = value;
            lookup.put(this.value.toUpperCase(), this);
        }

        /**
         * String value of this Service.
         *
         * @return String value of this Service.
         */
        public String getValue() {
            return value;
        }

        /**
         * Looks up a ServiceEnumeration based on the string value.
         *
         * @param value String value to search for.
         * @return ServiceEnumeration or null.
         */
        public static ServiceEnumeration findByValue(final String value) {
            return lookup.get(value.toUpperCase());
        }

        /**
         * String value of this Service.
         *
         * @return String value of this Service.
         */
        @Override
        public String toString() {
            return value;
        }
    }
}
