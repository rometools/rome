/*
 * Copyright 2006 Nathanial X. Freitas, openvision.tv
 *
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
package com.rometools.modules.mediarss;

import java.io.Serializable;

import com.rometools.modules.mediarss.types.MediaContent;
import com.rometools.modules.mediarss.types.MediaGroup;
import com.rometools.modules.mediarss.types.Metadata;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

/**
 * Represents information for an Entry/Item level.
 */
public class MediaEntryModuleImpl extends MediaModuleImpl implements MediaEntryModule, Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    /*
     * the variables in the MediaModule are set when they apply to all MediaContent instances in the
     * set
     */
    private MediaContent[] mediaContents = new MediaContent[0];
    private MediaGroup[] mediaGroups = new MediaGroup[0];

    public MediaEntryModuleImpl() {
        super(MediaEntryModule.class, MediaModule.URI);
    }

    /**
     * MediaContent items for the entry
     *
     * @param mediaContents MediaContent items for the entry
     */
    public void setMediaContents(final MediaContent[] mediaContents) {
        this.mediaContents = mediaContents == null ? new MediaContent[0] : mediaContents;
    }

    /**
     * MediaContent items for the entry
     *
     * @return MediaContent items for the entry
     */
    @Override
    public MediaContent[] getMediaContents() {
        return mediaContents;
    }

    /**
     * MediaGroups for the entry
     *
     * @param mediaGroups MediaGroups for the entry
     */
    public void setMediaGroups(final MediaGroup[] mediaGroups) {
        this.mediaGroups = mediaGroups == null ? new MediaGroup[0] : mediaGroups;
    }

    /**
     * MediaGroups for the entry
     *
     * @return MediaGroups for the entry
     */
    @Override
    public MediaGroup[] getMediaGroups() {
        return mediaGroups;
    }

    @Override
    public Object clone() {
        final MediaEntryModuleImpl m = new MediaEntryModuleImpl();
        m.setMediaContents(mediaContents.clone());
        m.setMediaGroups(mediaGroups.clone());
        m.setMetadata(getMetadata() == null ? null : (Metadata) getMetadata().clone());
        m.setPlayer(getPlayer());

        return m;
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(MediaEntryModuleImpl.class, this);

        return eBean.beanEquals(obj);
    }

    @Override
    public int hashCode() {
        final EqualsBean equals = new EqualsBean(MediaEntryModuleImpl.class, this);

        return equals.beanHashCode();
    }

    @Override
    public String toString() {
        final ToStringBean tsBean = new ToStringBean(MediaEntryModuleImpl.class, this);

        return tsBean.toString();
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        MediaEntryModuleImpl other = (MediaEntryModuleImpl) obj;
        other = (MediaEntryModuleImpl) other.clone();
        setMediaContents(other.getMediaContents());
        setMediaGroups(other.getMediaGroups());
        setMetadata(other.getMetadata());
        setPlayer(other.getPlayer());
    }
}
