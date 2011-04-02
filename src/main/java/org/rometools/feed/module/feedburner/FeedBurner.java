/*
 *  Copyright 2011 robert.cooper.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package org.rometools.feed.module.feedburner;

import java.io.Serializable;

import com.sun.syndication.feed.module.Module;

/**
 * Interface for the FeedBurner RSS extension.
 *
 * @version 1.0
 * @author Georg Schmidl <georg.schmidl@scandio.de>
 *
 */
public interface FeedBurner extends Module, Serializable, Cloneable {

	public static final String URI = "http://rssnamespace.org/feedburner/ext/1.0";

	public String getAwareness();

	public void setAwareness(String awareness);

	public String getOrigLink();

	public void setOrigLink(String origLink);

	public String getOrigEnclosureLink();

	public void setOrigEnclosureLink(String origEnclosureLink);

}
