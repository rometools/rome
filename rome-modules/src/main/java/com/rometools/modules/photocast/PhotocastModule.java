/*
 * Copyright 2006 Robert Cooper, Temple of the Screaming Penguin
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
package com.rometools.modules.photocast;

import java.net.URL;
import java.util.Date;

import com.rometools.modules.photocast.types.Metadata;
import com.rometools.rome.feed.module.Module;

/**
 * This is a ROME module that provides support for the "http://www.apple.com/ilife/wallpapers"
 * namespace.
 *
 * See the package information for example usage.
 */
public interface PhotocastModule extends Module {
    /**
     * The URI of the namespace. ("http://www.apple.com/ilife/wallpapers")
     */
    public static final String URI = "http://www.apple.com/ilife/wallpapers";

    /**
     * Returns the Item level photoDate value.
     *
     * @return Returns the Item level photoDate value.
     */
    public Date getPhotoDate();

    /**
     * Set the Item level photoDate value.
     *
     * @param photoDate Item level photoDate value
     */
    public void setPhotoDate(Date photoDate);

    /**
     * Returns the cropDate value from the item level.
     *
     * @return Item level cropDate value
     */
    public Date getCropDate();

    /**
     * Sets the cropDate value for the item level.
     *
     * @param cropDate cropDate value for the item level
     */
    public void setCropDate(Date cropDate);

    /**
     * The URL of the image.
     *
     * @return The URL of the image.
     */
    public URL getImageUrl();

    /**
     * The URL of the image.
     *
     * @param imageUrl The URL of the image.
     */
    public void setImageUrl(URL imageUrl);

    /**
     * The URL of the image thumbnail.
     *
     * @return The URL of the image thumbnail.
     */
    public URL getThumbnailUrl();

    /**
     * The URL of the image thumbnail.
     *
     * @param thumbnailUrl The URL of the image thumbnail.
     */
    public void setThumbnailUrl(URL thumbnailUrl);

    /**
     * The iPhoto metadata for the image.
     *
     * @return The iPhoto metadata for the image.
     */
    public Metadata getMetadata();

    /**
     * The iPhoto metadata for the image.
     *
     * @param metadata The iPhoto metadata for the image.
     */
    public void setMetadata(Metadata metadata);
}
