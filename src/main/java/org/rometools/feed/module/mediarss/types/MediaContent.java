/*
 * Copyright 2006 Nathanial X. Freitas, openvision.tv
 *
 * This code is currently released under the Mozilla Public License.
 * http://www.mozilla.org/MPL
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
package org.rometools.feed.module.mediarss.types;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import java.io.Serializable;


/**
 * <strong>&lt;media:content&gt;</strong></p>
 * <p>&lt;media:content&gt; is a sub-element of either &lt;item&gt; or &lt;media:group&gt;.&nbsp;Media objects that are not the same content should not be included in the same &lt;media:group&gt; element.&nbsp;The sequence of these items implies the order of presentation.
 * While many of the attributes appear to be audio/video specific, this element can be used to publish any type of media.
 * It contains 14 attributes, most of which are optional.</p>
 *
 * <pre>
 *        &lt;media:content
 *               url="http://www.foo.com/movie.mov"
 *               fileSize="12216320"
 *               type="video/quicktime"
 *               medium="video"
 *               isDefault="true"
 *               expression="full"
 *               bitrate="128"
 *               framerate="25"
 *               samplingrate="44.1"
 *               channels="2"
 *               duration="185"
 *               height="200"
 *               width="300"
 *               lang="en" /&gt;</pre>
 *
 * <p><em>url</em> should specify the direct url to the media object. If not included, a &lt;media:player&gt; element must be specified.</p><p><em>fileSize</em> is the number of bytes of the media object. It is an optional attribute.</p>
 * <p><em>type</em> is the standard MIME type of the object. It is an optional attribute.</p>
 *
 * <p><em>medium</em> is the type of object (image | audio | video | document | executable). While this attribute can at times seem redundant if <em>type</em> is supplied, it is included because it simplifies decision making on the reader side, as well as flushes out any ambiguities between MIME type and object type. It is an optional attribute.</p>
 *
 *
 * <p><em>isDefault</em> determines if this is the default object that should be used for the &lt;media:group&gt;.  There should only be one default object per &lt;media:group&gt;. It is an optional attribute.</p>
 *
 * <p><em>expression</em> determines if the object is a sample or the full version of the object, or even if it is a continuous stream (sample | full | nonstop).
 * Default value is 'full'.
 * It is an optional attribute. </p>
 *
 * <p><em>bitrate</em> is the kilobits per second rate of media. It is an optional attribute.</p>
 * <p><em>framerate</em> is the number of frames per second for the media object. It is an optional attribute.</p>
 * <p><em>samplingrate</em> is the number of samples per second taken to create the media object. It is expressed in thousands of samples per second (kHz). It is an optional attribute.</p>
 * <p><em>channels</em> is number of audio channels in the media object. It is an optional attribute.
 * </p><p><em>duration</em> is the number of seconds the media object plays. It is an optional attribute.</p>
 *
 *
 *
 *
 *
 * <p><em>height</em> is the height of the media object. It is an optional attribute.</p>
 * <p><em>width</em> is the width of the media object. It is an optional attribute.</p>
 *
 * <p><em>lang</em> is the primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066. This attribute is used similar to the <em>xml:lang</em> attribute detailed in the XML 1.0 Specification (Third Edition). It is an optional attribute.</p>
 *
 *
 *        <p> These optional attributes, along with the optional elements below, contain the primary metadata entries needed to index and organize media content.
 *            Additional supported attributes for describing images, audio, and video may be added in future revisions of this document.</p>
 * @author Nathanial X. Freitas
 *
 * MediaContent corresponds to the <madia:content> element defined within the MediaRSS specification.
 * There may be one or more <media:content> instances within each instance of an <item> within an
 * RSS 2.0 document.
 */
public class MediaContent implements Serializable {
    private static final long serialVersionUID = -4990262574794352616L;
    private Expression expression;
    private Float bitrate = null;
    private Float framerate = null;
    private Float samplingrate = null;

    /*private variable for storing instances of Thumbnail class
      each MediaContent can have zero to many thumbnails*/
    private Integer audioChannels = null;

    /*the height in pixels of the resource*/
    private Integer height = null;

    /*the width in pixels of the resource*/
    private Integer width = null;

    /*the duration in seconds of the resource*/
    private Long duration = null;

    /*the file size in bytes of the resouce*/
    private Long fileSize = null;
    private Metadata metadata;
    private PlayerReference player;
    private Reference reference;
    private String language;
    private String medium;

    /*the MIME type of the resource*/
    private String type = null;
    private boolean defaultContent;

    /*
     * MediaContent constructor
     */

    /**
     * Creates a new MediaContent
     * @param reference UrlReference or Player reference for the item.
     */
    public MediaContent(Reference reference) {
        super();

        if (reference == null) {
            throw new NullPointerException("You must provide either a PlayerReference or URL reference.");
        }

        this.setReference(reference);
    }

    /**
     * channels is number of audio channels in the media object. It is an optional attribute.
     * @param audioChannels channels is number of audio channels in the media object. It is an optional attribute.
     */
    public void setAudioChannels(Integer audioChannels) {
        this.audioChannels = audioChannels;
    }

    /**
     * channels is number of audio channels in the media object. It is an optional attribute.
     * @return channels is number of audio channels in the media object. It is an optional attribute.
     */
    public Integer getAudioChannels() {
        return audioChannels;
    }

    /**
     * bitrate is the kilobits per second rate of media. It is an optional attribute.
     * @param bitrate bitrate is the kilobits per second rate of media. It is an optional attribute.
     */
    public void setBitrate(Float bitrate) {
        this.bitrate = bitrate;
    }

    /**
     * bitrate is the kilobits per second rate of media. It is an optional attribute.
     * @return bitrate is the kilobits per second rate of media. It is an optional attribute.
     */
    public Float getBitrate() {
        return bitrate;
    }

    /**
     * isDefault determines if this is the default object that should be used for the <media:group>. There should only be one default object per <media:group>. It is an optional attribute.
     * @param defaultContent isDefault determines if this is the default object that should be used for the <media:group>. There should only be one default object per <media:group>. It is an optional attribute.
     */
    public void setDefaultContent(boolean defaultContent) {
        this.defaultContent = defaultContent;
    }

    /**
     * isDefault determines if this is the default object that should be used for the <media:group>. There should only be one default object per <media:group>. It is an optional attribute.
     * @return isDefault determines if this is the default object that should be used for the <media:group>. There should only be one default object per <media:group>. It is an optional attribute.
     */
    public boolean isDefaultContent() {
        return defaultContent;
    }

    /**
     * duration is the number of seconds the media object plays. It is an optional attribute.
     * @param duration duration is the number of seconds the media object plays. It is an optional attribute.
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * duration is the number of seconds the media object plays. It is an optional attribute.
     * @return duration is the number of seconds the media object plays. It is an optional attribute.
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * expression determines if the object is a sample or the full version of the object, or even if it is a continuous stream (sample | full | nonstop). Default value is 'full'. It is an optional attribute.
     * @param expression expression determines if the object is a sample or the full version of the object, or even if it is a continuous stream (sample | full | nonstop). Default value is 'full'. It is an optional attribute.
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * expression determines if the object is a sample or the full version of the object, or even if it is a continuous stream (sample | full | nonstop). Default value is 'full'. It is an optional attribute.
     * @return expression determines if the object is a sample or the full version of the object, or even if it is a continuous stream (sample | full | nonstop). Default value is 'full'. It is an optional attribute.
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     *
     * fileSize is the number of bytes of the media object. It is an optional attribute.
     * @param fileSize The fileSize to set.
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * fileSize is the number of bytes of the media object. It is an optional attribute.
     * @return Returns the fileSize.
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * framerate is the number of frames per second for the media object. It is an optional attribute.
     * @param framerate framerate is the number of frames per second for the media object. It is an optional attribute.
     */
    public void setFramerate(Float framerate) {
        this.framerate = framerate;
    }

    /**
     * framerate is the number of frames per second for the media object. It is an optional attribute.
     * @return framerate is the number of frames per second for the media object. It is an optional attribute.
     */
    public Float getFramerate() {
        return framerate;
    }

    /**
     * height is the height of the media object. It is an optional attribute.
     * @param height height is the height of the media object. It is an optional attribute.
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * height is the height of the media object. It is an optional attribute.
     * @return height is the height of the media object. It is an optional attribute.
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * lang is the primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066. This attribute is used similar to the xml:lang attribute detailed in the XML 1.0 Specification (Third Edition). It is an optional attribute.
     * @param language lang is the primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066. This attribute is used similar to the xml:lang attribute detailed in the XML 1.0 Specification (Third Edition). It is an optional attribute.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * lang is the primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066. This attribute is used similar to the xml:lang attribute detailed in the XML 1.0 Specification (Third Edition). It is an optional attribute.
     * @return lang is the primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066. This attribute is used similar to the xml:lang attribute detailed in the XML 1.0 Specification (Third Edition). It is an optional attribute.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set the value of medium
     *
     * @param newmedium new value of medium
     */
    public void setMedium(String newmedium) {
        this.medium = newmedium;
    }

    /**
     * Get the value of medium
     *
     * @return the value of medium
     */
    public String getMedium() {
        return this.medium;
    }

    /**
     * The metadata for the item
     * @param metadata The metadata for the item
     */
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    /**
     * The metadata for the item
     * @return The metadata for the item
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * <strong>&lt;media:player&gt;</strong></p>
     * <p>Allows the media object to be accessed through a web browser media player console.
     * This element is required only if a direct media <em>url</em> attribute is not specified in the &lt;media:content&gt; element. It has 1 required attribute, and 2 optional attributes.</p>
     * <pre>        &lt;media:player url="http://www.foo.com/player?id=1111" height="200" width="400" /&gt;</pre>
     * <p><em>url</em> is the url of the player console that plays the media. It is a required attribute.</p>
     *
     * <p><em>height</em> is the height of the browser window that the <em>url</em> should be opened in. It is an optional attribute.</p>
     * <p><em>width</em> is the width of the browser window that the <em>url</em> should be opened in. It is an optional attribute.</p>
     *
     * <p>
     * @param player PlayerReference for the item.
     */
    public void setPlayer(PlayerReference player) {
        this.player = player;
    }

    /**
     * <strong>&lt;media:player&gt;</strong></p>
     * <p>Allows the media object to be accessed through a web browser media player console.
     * This element is required only if a direct media <em>url</em> attribute is not specified in the &lt;media:content&gt; element. It has 1 required attribute, and 2 optional attributes.</p>
     * <pre>        &lt;media:player url="http://www.foo.com/player?id=1111" height="200" width="400" /&gt;</pre>
     * <p><em>url</em> is the url of the player console that plays the media. It is a required attribute.</p>
     *
     * <p><em>height</em> is the height of the browser window that the <em>url</em> should be opened in. It is an optional attribute.</p>
     * <p><em>width</em> is the width of the browser window that the <em>url</em> should be opened in. It is an optional attribute.</p>
     * @return PlayerReference for the item.
     */
    public PlayerReference getPlayer() {
        return player;
    }

    /**
     * The player or URL reference for the item
     * @param reference The player or URL reference for the item
     */
    public void setReference(Reference reference) {
        this.reference = reference;

        if (reference instanceof PlayerReference) {
            this.setPlayer((PlayerReference) reference);
        }
    }

    /**
     * The player or URL reference for the item
     * @return The player or URL reference for the item
     */
    public Reference getReference() {
        return reference;
    }

    /**
     * samplingrate is the number of samples per second taken to create the media object. It is expressed in thousands of samples per second (kHz). It is an optional attribute.
     * @param samplingrate samplingrate is the number of samples per second taken to create the media object. It is expressed in thousands of samples per second (kHz). It is an optional attribute.
     */
    public void setSamplingrate(Float samplingrate) {
        this.samplingrate = samplingrate;
    }

    /**
     * samplingrate is the number of samples per second taken to create the media object. It is expressed in thousands of samples per second (kHz). It is an optional attribute.
     * @return samplingrate is the number of samples per second taken to create the media object. It is expressed in thousands of samples per second (kHz). It is an optional attribute.
     */
    public Float getSamplingrate() {
        return samplingrate;
    }

    /**
     *
     * type is the standard MIME type of the object. It is an optional attribute.
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * type is the standard MIME type of the object. It is an optional attribute.
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * width is the width of the media object. It is an optional attribute.
     * @param width width is the width of the media object. It is an optional attribute.
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * width is the width of the media object. It is an optional attribute.
     * @return width is the width of the media object. It is an optional attribute.
     */
    public Integer getWidth() {
        return width;
    }

    public Object clone() {
        MediaContent c = new MediaContent(getReference());
        c.setAudioChannels(getAudioChannels());
        c.setBitrate(getBitrate());
        c.setDefaultContent(isDefaultContent());
        c.setDuration(getDuration());
        c.setExpression(getExpression());
        c.setFileSize(getFileSize());
        c.setFramerate(getFramerate());
        c.setHeight(getHeight());
        c.setLanguage(getLanguage());
        c.setMetadata((getMetadata() == null) ? null : (Metadata) getMetadata()
                                                                      .clone());
        c.setPlayer(getPlayer());
        c.setSamplingrate(getSamplingrate());
        c.setType(getType());
        c.setWidth(getWidth());

        return c;
    }

    public boolean equals(Object obj) {
        EqualsBean eBean = new EqualsBean(MediaContent.class, this);

        return eBean.beanEquals(obj);
    }

    public int hashCode() {
        EqualsBean equals = new EqualsBean(MediaContent.class, this);

        return equals.beanHashCode();
    }

    public String toString() {
        ToStringBean tsBean = new ToStringBean(MediaContent.class, this);

        return tsBean.toString();
    }
}
