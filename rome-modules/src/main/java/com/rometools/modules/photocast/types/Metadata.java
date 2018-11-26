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

package com.rometools.modules.photocast.types;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

/**
 * This is an immutable class representing the apple-wallpapers:metadata tag in the photocast
 * module.
 */
public class Metadata {

    private final PhotoDate photoDate;
    private final String comments;

    /**
     * Creates a new instance of Metadata with a set photoDate and comments value.
     *
     * @param photoDate PhotoDate value to use
     * @param comments comments about this photo.
     */
    public Metadata(final PhotoDate photoDate, final String comments) {
        this.photoDate = photoDate == null ? new PhotoDate() : photoDate;
        this.comments = comments == null ? "" : comments;
    }

    /**
     * Returns the PhotoDate value.
     *
     * @return Returns the PhotoDate value.
     */
    public PhotoDate getPhotoDate() {
        return photoDate;
    }

    /**
     * Returns comments about the photo.
     *
     * @return Returns comments about the photo.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Returns a String representation of the metadata object.
     *
     * @return Returns a String representation of the metadata object.
     */
    @Override
    public String toString() {
        return ToStringBean.toString(Metadata.class, this);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBean.beanEquals(Metadata.class, this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }
}
