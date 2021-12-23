/*
 * This code is currently released under the Mozilla Public License.
 * http://www.mozilla.org/MPL/
 *
 * Alternately you may apply the terms of the Apache Software License
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
 *
 */
package com.rometools.modules.mediarss.types;

import java.io.Serializable;
import java.net.URL;

/**
 * Optional tag to include pricing information about a media object.
 * 
 * @since MediaRSS 1.5.0
 */
public class Price implements Serializable {
    private static final long serialVersionUID = 446362162632617445L;

    /**
     * Valid values are {@code rent}, {@code purchase}, {@code package} or {@code subscription}.
     * 
     * If nothing is specified, then the media is free.
     */
    public enum Type {
        RENT, PURCHASE, PACKAGE, SUBSCRIPTION;
    }

    private Type type;
    private Double price;
    private String currency;
    private URL info;

    /**
     * Valid values are "rent", "purchase", "package" or "subscription". If nothing is specified, then the media is free.
     * 
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Valid values are "rent", "purchase", "package" or "subscription". If nothing is specified, then the media is free.
     * 
     * @param type the type
     */
    public void setType(final Type type) {
        this.type = type;
    }

    /**
     * price is the price of the media object.
     * 
     * This is an optional attribute.
     * 
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * price is the price of the media object.
     * 
     * This is an optional attribute.
     * 
     * @param price the price
     */
    public void setPrice(final Double price) {
        this.price = price;
    }

    /**
     * use ISO 4217 {@link http://en.wikipedia.org/wiki/ISO_4217} for currency codes. This is an optional attribute.
     * 
     * @return ISO 4217 currency code
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * use ISO 4217 {@link http://en.wikipedia.org/wiki/ISO_4217} for currency codes. This is an optional attribute.
     * 
     * @param currency ISO 4217 currency code
     */
    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    /**
     * if the type is "package" or "subscription", then info is a URL pointing to package or subscription information.
     * 
     * This is an optional attribute.
     * 
     * @return info url
     */
    public URL getInfo() {
        return info;
    }

    /**
     * if the type is "package" or "subscription", then info is a URL pointing to package or subscription information.
     * 
     * This is an optional attribute.
     * 
     * @param info url
     */
    public void setInfo(final URL info) {
        this.info = info;
    }

    // CHECKSTYLE:OFF
    @Override
    public String toString() {
        return "Price [type=" + type + ", price=" + price + ", currency=" + currency + ", info=" + info + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (currency == null ? 0 : currency.hashCode());
        result = prime * result + (info == null ? 0 : info.hashCode());
        result = prime * result + (price == null ? 0 : price.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Price other = (Price) obj;
        if (currency == null) {
            if (other.currency != null) {
                return false;
            }
        } else if (!currency.equals(other.currency)) {
            return false;
        }
        if (info == null) {
            if (other.info != null) {
                return false;
            }
        } else if (!info.equals(other.info)) {
            return false;
        }
        if (price == null) {
            if (other.price != null) {
                return false;
            }
        } else if (!price.equals(other.price)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }
    // CHECKSTYLE:ON
}
