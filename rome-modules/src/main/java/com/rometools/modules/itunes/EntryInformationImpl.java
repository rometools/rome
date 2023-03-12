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

import com.rometools.modules.itunes.types.Duration;
import com.rometools.rome.feed.CopyFrom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class contains information for iTunes podcast feeds that exist at the Item level.
 */
public class EntryInformationImpl extends AbstractITunesObject implements EntryInformation {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(EntryInformationImpl.class);

    private Duration duration;
    private boolean closedCaptioned;
    private Integer order;
    private String episodeType;
    private Integer season;
    private Integer episode;
    private String title;

    public EntryInformationImpl() {
    }

    /**
     * Returns the Duration object for this Item
     *
     * @return Returns the Duration object for this Item
     */
    @Override
    public Duration getDuration() {
        return duration;
    }

    /**
     * Sets the Duration object for this Item
     *
     * @param duration Sets the Duration object for this Item
     */
    @Override
    public void setDuration(final Duration duration) {
        this.duration = duration;
    }

    @Override
    public boolean getClosedCaptioned() {
        return closedCaptioned;
    }

    @Override
    public void setClosedCaptioned(boolean closedCaptioned) {
        this.closedCaptioned = closedCaptioned;
    }

    @Override
    public Integer getOrder() {
        return order;
    }

    @Override
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * Get the episode type
     *
     * @see #setEpisodeType(String) setEpisodeType(episodeType) for details
     */
    @Override
    public String getEpisodeType() { return episodeType; }

    /**
     * Set the episode type to one of full (default), trailer or bonus. See see the <a href="http://podcasts.apple.com/resources/spec/ApplePodcastsSpecUpdatesiOS11.pdf">new spec by Apple</a> for details.
     *
     * @param episodeType
     */
    @Override
    public void setEpisodeType(String episodeType) { this.episodeType = episodeType; }

    @Override
    public Integer getSeason() { return season; }

    @Override
    public void setSeason(Integer season) { this.season = season; }

    @Override
    public Integer getEpisode() { return episode; }

    @Override
    public void setEpisode(Integer episode) { this.episode = episode; }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Defined by the ROME module API
     *
     * @param obj Object to copy from
     */
    @Override
    public void copyFrom(final CopyFrom obj) {
        final EntryInformationImpl info = (EntryInformationImpl) obj;
        setAuthor(info.getAuthor());
        setBlock(info.getBlock());

        if (info.getDuration() != null) {
            setDuration(new Duration(info.getDuration().getMilliseconds()));
        }

        setExplicitNullable(info.getExplicitNullable());

        if (info.getImage() != null) {
            try {
                setImage(new URL(info.getImage().toExternalForm()));
            } catch (final MalformedURLException e) {
                LOG.debug("Error copying URL:" + info.getImage(), e);
            }
        }

        if (info.getImageUri() != null) {
            setImageUri(info.getImageUri());
        }

        if (info.getKeywords() != null) {
            setKeywords(info.getKeywords().clone());
        }

        setSubtitle(info.getSubtitle());
        setSummary(info.getSummary());
        setClosedCaptioned(info.getClosedCaptioned());
        setOrder(info.getOrder());
        setEpisodeType(info.getEpisodeType());
        setSeason(info.getSeason());
        setEpisode(info.getEpisode());
        setTitle(info.getTitle());
    }

    /**
     * Required by the ROME API
     *
     * @return A clone of this module object
     */
    @Override
    public Object clone() {
        final EntryInformationImpl info = new EntryInformationImpl();
        info.copyFrom(this);

        return info;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("[");
        sb.append(" duration: ");
        sb.append(getDuration());
        sb.append(" closedCaptioned: ");
        sb.append(getClosedCaptioned());
        sb.append(" order: ");
        sb.append(getOrder());
        sb.append(" season: ");
        sb.append(getSeason());
        sb.append(" episode: ");
        sb.append(getEpisode());
        sb.append(" title: ");
        sb.append(getTitle());
        sb.append(" episodeType: ");
        sb.append(getEpisodeType());
        sb.append("]");
        sb.append(super.toString());

        return sb.toString();
    }
}
