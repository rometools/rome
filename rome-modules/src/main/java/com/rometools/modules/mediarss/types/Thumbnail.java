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
package com.rometools.modules.mediarss.types;

import java.io.Serializable;
import java.net.URI;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

/**
 * <strong>&lt;media:thumbnail&gt;</strong></p>
 *
 *
 * <p>
 * Allows particular images to be used as representative images for the media object. If multiple
 * thumbnails are included, and time coding is not at play, it is assumed that the images are in
 * order of importance. It has 1 required attribute and 3 optional attributes.
 * </p>
 *
 *
 *
 *
 *
 * <pre>
 * &lt;media:thumbnail url="http://www.foo.com/keyframe.jpg" width="75" height="50" time="12:05:01.123" /&gt;
 * </pre>
 * <p>
 * <em>url</em> specifies the url of the thumbnail. It is a required attribute.
 * </p>
 * <p>
 * <em>height</em> specifies the height of the thumbnail. It is an optional attribute.
 * </p>
 * <p>
 * <em>width</em> specifies the width of the thumbnail. It is an optional attribute.
 * </p>
 *
 *
 * <p>
 * <em>time</em> specifies the time offset in relation to the media object. Typically this is used
 * when creating multiple keyframes within a single video. The format for this attribute should be
 * in the DSM-CC's Normal Play Time (NTP) as used in RTSP [<a
 * href="http://www.ietf.org/rfc/rfc2326.txt">RFC 2326 3.6 Normal Play Time</a>]. It is an optional
 * attribute.
 * </p>
 */
public class Thumbnail implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    private Integer thumbHeight = null;
    private Integer thumbWidth = null;
    private final Time time;
    private URI thumbUrl = null;

    /**
     * @param url URL to thumbnail
     * @param width width in pixels
     * @param height height in pixels
     * @param time Timecode representing the thumbnails position in a source.
     */
    public Thumbnail(final URI url, final Integer width, final Integer height, final Time time) {
        thumbUrl = url;
        thumbHeight = height;
        thumbWidth = width;
        this.time = time;
    }

    /**
     * @param url URL
     * @param width width
     * @param height height
     */
    public Thumbnail(final URI url, final Integer width, final Integer height) {
        this(url, width, height, null);
    }

    /**
     * @param url URL
     */
    public Thumbnail(final URI url) {
        this(url, null, null, null);
    }

    /**
     *
     * Returns the thumbHeight.
     *
     * @return Returns the thumbHeight.
     */
    public Integer getHeight() {
        return thumbHeight;
    }

    /**
     * returns the time that the thumbnail was captured from its source
     *
     * @return returns the time that the thumbnail was captured from its source
     */
    public Time getTime() {
        return time;
    }

    /**
     *
     * Retursn the URL
     *
     * @return Returns the thumbUrl.
     */
    public URI getUrl() {
        return thumbUrl;
    }

    /**
     *
     * Returns width.
     *
     * @return Returns the thumbWidth.
     */
    public Integer getWidth() {
        return thumbWidth;
    }

    @Override
    public Object clone() {
        return new Thumbnail(thumbUrl, thumbWidth, thumbHeight);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBean.beanEquals(this.getClass(), this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBean.toString(this.getClass(), this);
    }
}
