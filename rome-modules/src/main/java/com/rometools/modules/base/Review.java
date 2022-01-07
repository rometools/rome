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

import java.net.URL;
import java.util.Date;

import com.rometools.modules.base.types.FloatUnit;

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for critical reviews of
 * other things.
 */
public interface Review extends GlobalInterface {
    /**
     * Author of the item.
     *
     * @param authors Author of the item.
     */
    public void setAuthors(String[] authors);

    /**
     * Author of the item.
     *
     * @return Author of the item.
     */
    public String[] getAuthors();

    /**
     * Additional instructions to explain the item’s delivery process.
     *
     * @param deliveryNotes Additional instructions to explain the item’s delivery process.
     */
    public void setDeliveryNotes(String deliveryNotes);

    /**
     * Additional instructions to explain the item’s delivery process.
     *
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
     * The name of an item being reviewed.
     *
     * @param nameOfItemBeingReviewed The name of an item being reviewed.
     */
    public void setNameOfItemBeingReviewed(String nameOfItemBeingReviewed);

    /**
     * The name of an item being reviewed.
     *
     * @return The name of an item being reviewed.
     */
    public String getNameOfItemBeingReviewed();

    /**
     * Date the item was published.
     *
     * @param publishDate Date the item was published.
     */
    public void setPublishDate(Date publishDate);

    /**
     * Date the item was published.
     *
     * @return Date the item was published.
     */
    public Date getPublishDate();

    /**
     * Rating of the product or service on a scale of 1-5, with 5 as the best.
     *
     * @param rating Rating of the product or service on a scale of 1-5, with 5 as the best.
     */
    public void setRating(Float rating);

    /**
     * Rating of the product or service on a scale of 1-5, with 5 as the best.
     *
     * @return Rating of the product or service on a scale of 1-5, with 5 as the best.
     */
    public Float getRating();

    /**
     * The category of the item being reviewed.
     *
     * @param reviewType The category of the item being reviewed.
     */
    public void setReviewType(String reviewType);

    /**
     * The category of the item being reviewed.
     *
     * @return The category of the item being reviewed.
     */
    public String getReviewType();

    /**
     * The type of rating being provided: editorial (a review written by a member of your staff) or
     * “user” ( a review written by a user of your site).
     *
     * @param reviewerType The type of rating being provided: editorial (a review written by a
     *            member of your staff) or “user” ( a review written by a user of your site).
     */
    public void setReviewerType(String reviewerType);

    /**
     * The type of rating being provided: editorial (a review written by a member of your staff) or
     * “user” ( a review written by a user of your site).
     *
     * @return The type of rating being provided: editorial (a review written by a member of your
     *         staff) or “user” ( a review written by a user of your site).
     */
    public String getReviewerType();

    /**
     * The web page of an item being reviewed.
     *
     * @param urlOfItemBeingReviewed The web page of an item being reviewed.
     */
    public void setUrlOfItemBeingReviewed(URL urlOfItemBeingReviewed);

    /**
     * The web page of an item being reviewed.
     *
     * @return The web page of an item being reviewed.
     */
    public URL getUrlOfItemBeingReviewed();
}
