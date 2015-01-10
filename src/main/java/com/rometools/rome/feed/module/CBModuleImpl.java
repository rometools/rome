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

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.utils.Lists;

/**
 * CB RSS ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 *
 */
public class CBModuleImpl extends ModuleImpl implements CBModule {

    private static final long serialVersionUID = 1L;

    private final ObjectBean objBean;
	private CBEvent event;
	private CBNews news;
	// private CBPaper paper;
	// private CBSpeech speech;
	// private CBStatistics statistics;

    /**
     * Properties to be ignored when cloning.
     */
    private static final Set<String> IGNORE_PROPERTIES = new HashSet<String>();

    /**
     * Unmodifiable Set containing the convenience properties of this class.
     * <p>
     * Convenience properties are mapped to Modules, for cloning the convenience properties can be
     * ignored as the will be copied as part of the module cloning.
     */
    public static final Set<String> CONVENIENCE_PROPERTIES = Collections.unmodifiableSet(IGNORE_PROPERTIES);

    static {
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBModuleImpl() {
        super(CBModule.class, URI);
        objBean = new ObjectBean(CBModule.class, this, CONVENIENCE_PROPERTIES);
    }

    /**
     * Gets the CB event.
     * <p>
     *
     * @return the CB Event, or <b>null</b> if none.
     *
     */
    public CBEvent getEvent() {
		return event;
	}
	

    /**
     * Sets the CB event.
     * <p>
     *
     * @param Set the CB Event or <b>null</b> if none.
     *
     */
    public void setEvent(CBEvent event) {
		this.event = event;
	}
	

    /**
     * Gets the CB news.
     * <p>
     *
     * @return the CB News, or <b>null</b> if none.
     *
     */
    public CBNews getNews() {
		return news;
	}

    /**
     * Sets the CB news.
     * <p>
     *
     * @param Set the CB News or <b>null</b> if none.
     *
     */
    public void setNews(CBNews news) {
		this.news = news;
	}
	
    // /**
    //  * Gets the CB paper.
    //  * <p>
    //  *
    //  * @return the CB Paper, or <b>null</b> if none.
    //  *
    //  */
    // CBPaper getPaper() {
	// 	return paper;
	// }
	
    // /**
    //  * Sets the CB paper.
    //  * <p>
    //  *
    //  * @param Set the CB Paper or <b>null</b> if none.
    //  *
    //  */
    // void setPaper(CBPaper paper) {
	// 	this.paper = paper;
	// }
	
    // /**
    //  * Gets the CB speech.
    //  * <p>
    //  *
    //  * @return the CB Speech, or <b>null</b> if none.
    //  *
    //  */
	// CBSpeech getSpeech() {
	// 	return speech;
	// }
	
    // /**
    //  * Sets the CB speech.
    //  * <p>
    //  *
    //  * @param Set the CB Speech or <b>null</b> if none.
    //  *
    //  */
    // void setSpeech(CBSpeech speech) {
	// 	this.speech = speech;
	// }
	
    // /**
    //  * Gets the CB statistics.
    //  * <p>
    //  *
    //  * @return the CB Statistics, or <b>null</b> if none.
    //  *
    //  */
    // CBStatistics getStatistics() {
	// 	return statistics;
	// }
	

    // /**
    //  * Sets the CB statistics.
    //  * <p>
    //  *
    //  * @param Set the CB Statistics or <b>null</b> if none.
    //  *
    //  */
    // void setStatistics(CBStatistics statistics) {
	// 	this.statistics = statistics;
	// }
	

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {
        return objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals()
     * method.
     * <p>
     *
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public final boolean equals(final Object other) {
        return objBean.equals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     *
     * @return the hashcode of the bean object.
     *
     */
    @Override
    public final int hashCode() {
        return objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     *
     * @return String representation for the object.
     *
     */
    @Override
    public final String toString() {
        return objBean.toString();
    }

    @Override
    public final Class<CBModule> getInterface() {
        return CBModule.class;
    }

    @Override
    public final void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    private static final CopyFromHelper COPY_FROM_HELPER;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = Collections.<String, Class<?>> emptyMap();

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(CBEvent.class, CBEventImpl.class);
        basePropClassImplMap.put(CBNews.class, CBNewsImpl.class);
        // basePropClassImplMap.put(CBPaper.class, CBPaperImpl.class);
        // basePropClassImplMap.put(CBSpeech.class, CBSpeechImpl.class);
        // basePropClassImplMap.put(CBStatistics.class, CBStatisticsImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(CBModule.class, basePropInterfaceMap, basePropClassImplMap);
    }

}
