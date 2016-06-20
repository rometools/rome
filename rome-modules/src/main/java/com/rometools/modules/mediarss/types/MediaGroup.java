/*
 * MediaGroup.java
 *
 * Created on April 19, 2006, 12:34 AM
 *
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
 */
package com.rometools.modules.mediarss.types;

import java.io.Serializable;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

/**
 * <strong>&lt;media:group&gt;</strong></p>
 * <p>
 * &lt;media:group&gt; is a sub-element of &lt;item&gt;. It allows grouping of &lt;media:content&gt;
 * elements that are effectively the same content, yet different representations.&nbsp;For instance:
 * the same song recorded in both the WAV and MP3 format. It's an optional element that must only be
 * used for this purpose.
 * </p>
 */
public class MediaGroup implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    private Integer defaultContentIndex;
    private Metadata metadata;
    private MediaContent[] contents = new MediaContent[0];

    /**
     * @param contents Contents of the group.
     */
    public MediaGroup(final MediaContent[] contents) {
        setContents(contents);
    }

    /**
     * @param contents contents of the group
     * @param defaultContentIndex index of the default content value.
     */
    public MediaGroup(final MediaContent[] contents, final Integer defaultContentIndex) {
        setContents(contents);
        setDefaultContentIndex(defaultContentIndex);
    }

    /**
     * @param contents contents of the group
     * @param defaultContentIndex index of the default content item.
     * @param metadata metadata for the group.
     */
    public MediaGroup(final MediaContent[] contents, final Integer defaultContentIndex, final Metadata metadata) {
        setContents(contents);
        setDefaultContentIndex(defaultContentIndex);
        setMetadata(metadata);
    }

    /**
     * MediaContents for the group
     *
     * @param contents MediaContents for the group
     */
    public void setContents(final MediaContent[] contents) {
        this.contents = contents == null ? new MediaContent[0] : contents;
    }

    /**
     * MediaContents for the group
     *
     * @return MediaContents for the group
     */
    public MediaContent[] getContents() {
        return contents;
    }

    /**
     * Default content index MediaContent.
     *
     * @param defaultContentIndex Default content index MediaContent.
     */
    public void setDefaultContentIndex(final Integer defaultContentIndex) {
        for (int i = 0; i < getContents().length; i++) {
            if (i == defaultContentIndex.intValue()) {
                getContents()[i].setDefaultContent(true);
            } else {
                getContents()[i].setDefaultContent(false);
            }
        }

        this.defaultContentIndex = defaultContentIndex;
    }

    /**
     * Default content index MediaContent.
     *
     * @return Default content index MediaContent.
     */
    public Integer getDefaultContentIndex() {
        return defaultContentIndex;
    }

    /**
     * Metadata for the group
     *
     * @param metadata Metadata for the group
     */
    public void setMetadata(final Metadata metadata) {
        this.metadata = metadata;
    }

    /**
     * Metadata for the group
     *
     * @return Metadata for the group
     */
    public Metadata getMetadata() {
        return metadata;
    }

    @Override
    public Object clone() {
        return new MediaGroup(getContents(), getDefaultContentIndex(), getMetadata());
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(this.getClass(), this);

        return eBean.beanEquals(obj);
    }

    @Override
    public int hashCode() {
        final EqualsBean equals = new EqualsBean(this.getClass(), this);

        return equals.beanHashCode();
    }

    @Override
    public String toString() {
        final ToStringBean tsBean = new ToStringBean(this.getClass(), this);

        return tsBean.toString();
    }
}
