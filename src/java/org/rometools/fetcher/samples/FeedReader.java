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

package org.rometools.fetcher.samples;
import java.net.URL;

import com.sun.syndication.feed.synd.SyndFeed;
import org.rometools.fetcher.FeedFetcher;
import org.rometools.fetcher.FetcherEvent;
import org.rometools.fetcher.FetcherListener;
import org.rometools.fetcher.impl.FeedFetcherCache;
import org.rometools.fetcher.impl.HashMapFeedInfoCache;
import org.rometools.fetcher.impl.HttpURLFeedFetcher;
/**
 * Reads and prints any RSS/Atom feed type. Converted from the
 * original Rome sample FeedReader
 * <p>
 * @author Alejandro Abdelnur
 * @author Nick Lothian
 *
 */
public class FeedReader {
	public static void main(String[] args) {
		boolean ok = false;
		if (args.length==1) {
			try {
				URL feedUrl = new URL(args[0]);
				FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.getInstance();
				FeedFetcher fetcher = new HttpURLFeedFetcher(feedInfoCache);

				FetcherEventListenerImpl listener = new FetcherEventListenerImpl();

				fetcher.addFetcherEventListener(listener);

				System.err.println("Retrieving feed " + feedUrl);
				// Retrieve the feed.
				// We will get a Feed Polled Event and then a
				// Feed Retrieved event (assuming the feed is valid)
				SyndFeed feed = fetcher.retrieveFeed(feedUrl);

				System.err.println(feedUrl + " retrieved");
				System.err.println(feedUrl + " has a title: " + feed.getTitle() + " and contains " + feed.getEntries().size() + " entries.");
				// We will now retrieve the feed again. If the feed is unmodified
				// and the server supports conditional gets, we will get a "Feed
				// Unchanged" event after the Feed Polled event
				System.err.println("Polling " + feedUrl + " again to test conditional get support.");
				SyndFeed feed2 = fetcher.retrieveFeed(feedUrl);
				System.err.println("If a \"Feed Unchanged\" event fired then the server supports conditional gets.");

				ok = true;
			}
			catch (Exception ex) {
				System.out.println("ERROR: "+ex.getMessage());
				ex.printStackTrace();
			}
		}

		if (!ok) {
			System.out.println();
			System.out.println("FeedReader reads and prints any RSS/Atom feed type.");
			System.out.println("The first parameter must be the URL of the feed to read.");
			System.out.println();
		}

	}

	static class FetcherEventListenerImpl implements FetcherListener {
		/**
		 * @see com.sun.syndication.fetcher.FetcherListener#fetcherEvent(com.sun.syndication.fetcher.FetcherEvent)
		 */
		public void fetcherEvent(FetcherEvent event) {
			String eventType = event.getEventType();
			if (FetcherEvent.EVENT_TYPE_FEED_POLLED.equals(eventType)) {
				System.err.println("\tEVENT: Feed Polled. URL = " + event.getUrlString());
			} else if (FetcherEvent.EVENT_TYPE_FEED_RETRIEVED.equals(eventType)) {
				System.err.println("\tEVENT: Feed Retrieved. URL = " + event.getUrlString());
			} else if (FetcherEvent.EVENT_TYPE_FEED_UNCHANGED.equals(eventType)) {
				System.err.println("\tEVENT: Feed Unchanged. URL = " + event.getUrlString());
			}
		}
	}
}
