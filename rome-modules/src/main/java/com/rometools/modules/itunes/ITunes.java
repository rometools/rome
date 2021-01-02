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
 */

package com.rometools.modules.itunes;

import com.rometools.rome.feed.module.Module;

import java.net.URL;

/**
 * This interface contains the methods common to all iTunes module points.
 */
public interface ITunes extends Module {

    public static final String URI = AbstractITunesObject.URI;

    /**
     * Returns the author string for this feed or entry
     *
     * @return Returns the author string for this feed or entry
     */
    public String getAuthor();

    /**
     * Sets the author string for this feed or entry
     *
     * @param author Sets the author string for this feed or entry
     */
    public void setAuthor(String author);

    /**
     * Boolean as to whether to block this feed or entry
     *
     * @return Boolean as to whether to block this feed or entry
     */
    public boolean getBlock();

    /**
     * Boolean as to whether to block this feed or entry
     *
     * @param block Boolean as to whether to block this feed or entry
     */
    public void setBlock(boolean block);

    /**
     * Boolean as to whether this feed or entry contains adult content
     *
     * @return Boolean as to whether this feed or entry contains adult content
     */
    public boolean getExplicit();

    public Boolean getExplicitNullable();

    /**
     * Boolean as to whether this feed or entry contains adult content
     *
     * @param explicit Boolean as to whether this feed or entry contains adult content
     */
    public void setExplicit(boolean explicit);

    public void setExplicitNullable(Boolean explicit);

    public URL getImage();

    public void setImage(URL image);

    /**
     * A list of keywords for this feed or entry
     *
     * Must not contain spaces
     *
     * @return A list of keywords for this feed or entry
     */
    public String[] getKeywords();

    /**
     * A list of keywords for this feed or entry
     *
     * Must not contain spaces
     *
     * @param keywords A list of keywords for this feed or enty
     */
    public void setKeywords(String[] keywords);

    /**
     * A subtitle for this feed or entry
     *
     * @return A subtitle for this feed or entry
     */
    public String getSubtitle();

    /**
     * A subtitle for this feed or entry
     *
     * @param subtitle A subtitle for this feed or entry
     */
    public void setSubtitle(String subtitle);

    /**
     * A subtitle for this feed or entry
     *
     * @return A subtitle for this feed or entry
     */
    public String getSummary();

    /**
     * A subtitle for this feed or entry
     *
     * @param summary A subtitle for this feed or entry
     */
    public void setSummary(String summary);

    public String getImageUri();

    public void setImageUri(String image);
}
