/*
 * ShippingType.java
 *
 * Created on November 16, 2005, 12:05 PM
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rometools.feed.module.base.types;

import java.util.HashMap;

/**
 * This class represents a specific shipping option for an item.
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.1 $
 */
public class ShippingType implements CloneableType {
    /**
     * price of the shipping.
     */
    private FloatUnit price;
    /**
     * Service used.
     */
    private ServiceEnumeration service;
    /**
     * Country to ship to
     */
    private String country;

    /**
     * Creates a new instance of ShippingType
     * @param price The price of the shipping option
     * @param service Shipping service used.
     * @param country Country shipped to.
     */
    public ShippingType(FloatUnit price, ServiceEnumeration service, String country) {
        this.price = price;
        this.service = service;
        this.country = country;
    }

    /**
     * Returns the destination country.
     * @return Returns the destination country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the price of this shipping option.
     * @return Returns the price of this shipping option.
     */
    public FloatUnit getPrice() {
        return price;
    }

    /**
     * Returns the ServiceEnumeration instance for the shipping service used.
     * @return Returns the ServiceEnumeration instance for the shipping service used.
     */
    public ServiceEnumeration getService() {
        return service;
    }

    /**
     * Clones this object.
     * @return Duplicate ShippingType object.
     */
    public Object clone() {
        return new ShippingType(this.price, this.service, this.country);
    }

    /**
     * Returns a String representation of this object.
     * @return String representation of this object.
     */
    public String toString() {
        return this.country + " " + this.price + " " + this.service;
    }

    public boolean equals( Object o ){
	if( !(o instanceof ShippingType))
	    return false;
	if( this.toString().equals( o.toString() ) )
	    return true;
	return false;
    }
    /**
     * Enumeration class of valid options for ServiceType.
     */
    public static class ServiceEnumeration {
	/**
         * Looks up a ServiceEnumeration based on the string value.
         */
        private static final HashMap lookup = new HashMap();
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
        private String value;

        /**
         * Creates a new instance of ServiceEnumeration.
         * @param value String value to encapsulate.
         */
        private ServiceEnumeration(String value) {
            this.value = value;
            lookup.put(this.value.toUpperCase(), this);
        }

        /**
         * String value of this Service.
         * @return String value of this Service.
         */
        public String getValue() {
            return this.value;
        }

        /**
         * Looks up a ServiceEnumeration based on the string value.
         * @param value String value to search for.
         * @return ServiceEnumeration or null.
         */
        public static ServiceEnumeration findByValue(String value) {
            return (ServiceEnumeration) lookup.get(value.toUpperCase());
        }

        /**
         * String value of this Service.
         * @return String value of this Service.
         */
        public String toString() {
            return this.value;
        }
    }
}
