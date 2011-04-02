/*
 * Copyright 2010 Scandio GmbH.
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

package org.rometools.feed.module.feedburner;

import com.sun.syndication.feed.CopyFrom;

/**
 * Implementation of the FeedBurner RSS extension.
 *
 * @version 1.0
 * @author Georg Schmidl <georg.schmidl@scandio.de>
 *
 */
public class FeedBurnerImpl implements FeedBurner {

	private static final long serialVersionUID = 4595722221441468838L;

	private String awareness;
	private String origLink;
	private String origEnclosureLink;

	public String getAwareness() {
		return awareness;
	}

	public void setAwareness(String awareness) {
		this.awareness = awareness;
	}

	public String getOrigLink() {
		return origLink;
	}

	public void setOrigLink(String origLink) {
		this.origLink = origLink;
	}

	public String getOrigEnclosureLink() {
		return origEnclosureLink;
	}

	public void setOrigEnclosureLink(String origEnclosureLink) {
		this.origEnclosureLink = origEnclosureLink;
	}

	public String getUri() {
		return FeedBurner.URI;
	}

	public void copyFrom(CopyFrom object) {
		FeedBurner source = (FeedBurner) object;
		this.setAwareness(source.getAwareness());
		this.setOrigLink(source.getOrigLink());
		this.setOrigEnclosureLink(source.getOrigEnclosureLink());
	}

	public Class getInterface() {
		return FeedBurner.class;
	}

	public Object clone() {
		FeedBurnerImpl fbi = new FeedBurnerImpl();
		fbi.copyFrom( this );
		return fbi;
    }
}
