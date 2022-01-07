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

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

/**
 * This is an interface representing the universals.
 */
public interface GlobalInterface extends Serializable {
	
    /**
     * Expiration Date for this item.
     *
     *
     * @param expirationDate the date this entry will expire
     */
    public void setExpirationDate(Date expirationDate);

    /**
     * Expiration Date for this item.
     *
     * @return the date this entry will expire
     */
    public Date getExpirationDate();

    /**
     * Date and time that the item expires.
     *
     * @param expirationDateTime Date and time that the item expires.
     */
    public void setExpirationDateTime(Date expirationDateTime);

    /**
     * Date and time that the item expires.
     *
     * @return Date and time that the item expires.
     */
    public Date getExpirationDateTime();

    /**
     * Unique id for this item.
     *
     * @param id unique identifier for this entry
     */
    public void setId(String id);

    /**
     * Unique id for this item.

     * @return unique identifier for this entry
     */
    public String getId();

    /**
     * Images for this item. 
     *
     * @param imageLinks image links.
     */
    public void setImageLinks(URL[] imageLinks);

    /**
     * Images for this item. 
     *
     *
     * @return URLs to images
     */
    public URL[] getImageLinks();

    /**
     * Labels for this item. 
     *
     * @param labels labels for this entry. Limit 10.
     */
    public void setLabels(String[] labels);

    /**
     * Labels for this item. 
     *
     * @return labels for this entry
     */
    public String[] getLabels();
}
