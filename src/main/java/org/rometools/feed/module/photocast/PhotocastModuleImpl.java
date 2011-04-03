

/*
 * PhotocastModuleImpl.java
 *
 * Created on March 30, 2006, 6:23 PM
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

package org.rometools.feed.module.photocast;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;
import org.rometools.feed.module.photocast.types.Metadata;

import java.net.URL;

import java.util.Date;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class PhotocastModuleImpl implements PhotocastModule {
    private Date photoDate;
    private Date cropDate;
    private URL imageUrl;
    private URL thumbnailUrl;
    private Metadata metadata;

    /** Creates a new instance of PhotocastModuleImpl */
    public PhotocastModuleImpl() {
    }

    public Date getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(Date photoDate) {
        this.photoDate = photoDate;
    }

    public Date getCropDate() {
        return cropDate;
    }

    public void setCropDate(Date cropDate) {
        this.cropDate = cropDate;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public URL getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(URL thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public void copyFrom(CopyFrom obj) {
        PhotocastModule pm = (PhotocastModule) obj;
        this.setPhotoDate((pm.getPhotoDate() == null) ? null
                                                      : (Date) pm.getPhotoDate()
                                                                 .clone());
        this.setCropDate((pm.getCropDate() == null) ? null
                                                    : (Date) pm.getCropDate()
                                                               .clone());
        this.setImageUrl(pm.getImageUrl());
        this.setThumbnailUrl(pm.getThumbnailUrl());
        this.setMetadata(pm.getMetadata());
    }

    public Object clone() {
        PhotocastModuleImpl pm = new PhotocastModuleImpl();
        pm.setPhotoDate((this.getPhotoDate() == null) ? null
                                                      : (Date) this.getPhotoDate()
                                                                   .clone());
        pm.setCropDate((this.getCropDate() == null) ? null
                                                    : (Date) this.getCropDate()
                                                                 .clone());
        pm.setImageUrl(this.getThumbnailUrl());
        pm.setThumbnailUrl(this.getThumbnailUrl());
        pm.setMetadata(this.getMetadata());

        return pm;
    }

    public String getUri() {
        return PhotocastModule.URI;
    }

    public Class getInterface() {
        return PhotocastModule.class;
    }

    public String toString() {
        ToStringBean tsBean = new ToStringBean(PhotocastModuleImpl.class, this);

        return tsBean.toString();
    }

    public boolean equals(Object obj) {
        EqualsBean eBean = new EqualsBean(PhotocastModuleImpl.class, this);

        return eBean.beanEquals(obj);
    }

    public int hashCode() {
        EqualsBean equals = new EqualsBean(PhotocastModuleImpl.class, this);

        return equals.beanHashCode();
    }
}
