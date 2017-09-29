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

import com.rometools.modules.base.types.AvailabilityEnumeration;

import java.util.Date;

public interface Offer {


    /**
     * Use the availability_date [availability_date] attribute to tell users when a pre-ordered
     * product will be shipped.
     *
     *
     * XML feeds is <g:availability_date>2016-11-25T13:00-0800</g:availability_date>
     */
    Date getAvailabilityDate();

    /**
     *
     * @param availabilityDate
     */
    void setAvailabilityDate(Date availabilityDate);


    /**
     * Use the availability attribute to tell users and us whether you have a product in stock.
     *
     * XML feeds	<g:availability>in stock</g:availability>
     */

    AvailabilityEnumeration getAvailability();


    void setAvailability(AvailabilityEnumeration availability);


}
