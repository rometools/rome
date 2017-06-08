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
 *
 */
package com.rometools.modules.itunes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.itunes.types.Category;
import com.rometools.rome.feed.CopyFrom;

/**
 * This class contains information for iTunes podcast feeds that exist at the Channel level.
 */
public class FeedInformationImpl extends AbstractITunesObject implements FeedInformation {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(FeedInformationImpl.class);

    private String ownerName;
    private String ownerEmailAddress;
    private List<Category> categories;
    private boolean complete;
    private String newFeedUrl;

    public FeedInformationImpl() {
    }

    /**
     * The parent categories for this feed
     *
     * @return The parent categories for this feed
     */
    @Override
    public List<Category> getCategories() {
        return categories == null ? (categories = new ArrayList<Category>()) : categories;
    }

    /**
     * The parent categories for this feed
     *
     * @param categories The parent categories for this feed
     */
    @Override
    public void setCategories(final List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean getComplete() {
        return complete;
    }

    @Override
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String getNewFeedUrl() {
        return newFeedUrl;
    }

    @Override
    public void setNewFeedUrl(String newFeedUrl) {
        this.newFeedUrl = newFeedUrl;
    }

    /**
     * Returns the owner name for the feed
     *
     * @return Returns the owner name for the feed
     */
    @Override
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the owner name for the feed
     *
     * @param ownerName Sets the owner name for the feed
     */
    @Override
    public void setOwnerName(final String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Returns the owner email address for the feed.
     *
     * @return Returns the owner email address for the feed.
     */
    @Override
    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    /**
     * Sets the owner email address for the feed.
     *
     * @param ownerEmailAddress Sets the owner email address for the feed.
     */
    @Override
    public void setOwnerEmailAddress(final String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;
    }

    /**
     * Required by the ROME API
     *
     * @param obj object to copy property values from
     */
    @Override
    public void copyFrom(final CopyFrom obj) {
        final FeedInformationImpl info = (FeedInformationImpl) obj;
        setAuthor(info.getAuthor());
        setBlock(info.getBlock());

        getCategories().clear();
        if (info.getCategories() != null) {
            getCategories().addAll(info.getCategories());
        }

        setComplete(info.getComplete());
        setNewFeedUrl(info.getNewFeedUrl());
        setExplicit(info.getExplicit());

        try {
            if (info.getImage() != null) {
                setImage(new URL(info.getImage().toExternalForm()));
            }
        } catch (final MalformedURLException e) {
            LOG.debug("Error copying URL:" + info.getImage(), e);
        }

        if (info.getKeywords() != null) {
            setKeywords(info.getKeywords().clone());
        }

        setOwnerEmailAddress(info.getOwnerEmailAddress());
        setOwnerName(info.getOwnerName());
        setSubtitle(info.getSubtitle());
        setSummary(info.getSummary());
    }

    /**
     * Returns a copy of this FeedInformationImpl object
     *
     * @return Returns a copy of this FeedInformationImpl object
     */
    @Override
    public Object clone() {
        final FeedInformationImpl info = new FeedInformationImpl();
        info.copyFrom(this);

        return info;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        sb.append(" email: ");
        sb.append(getOwnerEmailAddress());
        sb.append(" name: ");
        sb.append(getOwnerName());
        sb.append(" categories: ");
        sb.append(getCategories());
        sb.append(" complete: ");
        sb.append(getComplete());
        sb.append(" newFeedUrl: ");
        sb.append(getNewFeedUrl());
        sb.append("]");
        sb.append(super.toString());

        return sb.toString();
    }
}
