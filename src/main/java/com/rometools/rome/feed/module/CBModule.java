/*
 * Copyright 2004 Sun Microsystems, Inc.
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
package com.rometools.rome.feed.module;

import java.util.Date;
import java.util.List;

/**
 * Dublin Core Module.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 *
 */
public interface CBModule extends Module {

    /**
     * URI of the RSS CB Module
     *
     */
    String URI = "http://www.centralbanks.org/rss/";

    /**
     * Gets the CB event.
     * <p>
     *
     * @return the CB Event, or <b>null</b> if none.
     *
     */
    CBEvent getEvent();

    /**
     * Sets the CB event.
     * <p>
     *
     * @param Set the CB Event or <b>null</b> if none.
     *
     */
    void setEvent(CBEvent event);

    /**
     * Gets the CB news.
     * <p>
     *
     * @return the CB News, or <b>null</b> if none.
     *
     */
    CBNews getNews();

    /**
     * Sets the CB news.
     * <p>
     *
     * @param Set the CB News or <b>null</b> if none.
     *
     */
    void setNews(CBNews news);

    // /**
    //  * Gets the CB paper.
    //  * <p>
    //  *
    //  * @return the CB Paper, or <b>null</b> if none.
    //  *
    //  */
    // CBPaper getPaper();

    // /**
    //  * Sets the CB paper.
    //  * <p>
    //  *
    //  * @param Set the CB Paper or <b>null</b> if none.
    //  *
    //  */
    // void setPaper(CBPaper paper);

    // /**
    //  * Gets the CB speech.
    //  * <p>
    //  *
    //  * @return the CB Speech, or <b>null</b> if none.
    //  *
    //  */
    // CBSpeech getSpeech();

    // /**
    //  * Sets the CB speech.
    //  * <p>
    //  *
    //  * @param Set the CB Speech or <b>null</b> if none.
    //  *
    //  */
    // void setSpeech(CBSpeech speech);

    // /**
    //  * Gets the CB statistics.
    //  * <p>
    //  *
    //  * @return the CB Statistics, or <b>null</b> if none.
    //  *
    //  */
    // CBStatistics getStatistics();

    // /**
    //  * Sets the CB statistics.
    //  * <p>
    //  *
    //  * @param Set the CB Statistics or <b>null</b> if none.
    //  *
    //  */
    // void setStatistics(CBStatistics statistics);
}
