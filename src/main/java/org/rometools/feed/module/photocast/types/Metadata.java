/*
 * Metadata.java
 *
 * Created on March 30, 2006, 6:16 PM
 *
  *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2006  Robert Cooper, Temple of the Screaming Penguin
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

package org.rometools.feed.module.photocast.types;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

/**
 * This is an immutable class representing
 * the apple-wallpapers:metadata tag in the photocast module.
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Metadata {
    
    private PhotoDate photoDate;
    private String comments;
    
    /**
     * Creates a new instance of Metadata with a set photoDate and comments value.
     * @param photoDate PhotoDate value to use
     * @param comments comments about this photo.
     */
    public Metadata(PhotoDate photoDate, String comments) {
        this.photoDate = photoDate == null ? new PhotoDate() : photoDate;
        this.comments = comments == null ? "" : comments;        
    }

    /**
     * Returns the PhotoDate value.
     * @return Returns the PhotoDate value.
     */
    public PhotoDate getPhotoDate() {
        return photoDate;
    }

    /**
     * Returns comments about the photo.
     * @return Returns comments about the photo.
     */
    public String getComments() {
        return comments;
    }
    /**
     * Returns a String representation of the metadata object.
     * @return Returns a String representation of the metadata object.
     */
    public String toString() {
        ToStringBean tsBean = new ToStringBean(Metadata.class, this);

        return tsBean.toString();
    }

    public boolean equals(Object obj) {
        EqualsBean eBean = new EqualsBean(Metadata.class, this);

        return eBean.beanEquals(obj);
    }

    public int hashCode() {
        EqualsBean equals = new EqualsBean(Metadata.class, this);

        return equals.beanHashCode();
    }
}
