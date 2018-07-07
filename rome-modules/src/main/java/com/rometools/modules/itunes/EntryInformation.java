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

/**
 * This class contains information for iTunes podcast feeds that exist at the Item level.
 */
public interface EntryInformation extends ITunes {

    /**
     * Returns the Duration object for this Item
     *
     * @return Returns the Duration object for this Item
     */
    public Duration getDuration();

    /**
     * Sets the Duration object for this Item
     *
     * @param duration Sets the Duration object for this Item
     */
    public void setDuration(Duration duration);

    public boolean getClosedCaptioned();

    public void setClosedCaptioned(boolean closedCaptioned);

    public Integer getOrder();

    public void setOrder(Integer order);

    /**
     * Get the episode type
     * @see #setEpisodeType(String) setEpisodeType(episodeType) for details
     */
    public String getEpisodeType();

    /**
     * Set the episode type to one of full (default), trailer or bonus. See the <a href="http://podcasts.apple.com/resources/spec/ApplePodcastsSpecUpdatesiOS11.pdf">new spec by Apple</a> for details.
     * @param episodeType
     */
    public void setEpisodeType(String episodeType);

    public Integer getSeason();

    public void setSeason(Integer season);

    public Integer getEpisode();

    public void setEpisode(Integer episode);

    public String getTitle();

    public void setTitle(String title);
}
