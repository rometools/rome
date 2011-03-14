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

import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import org.rometools.fetcher.FeedFetcher;
import org.rometools.fetcher.impl.FeedFetcherCache;
import org.rometools.fetcher.impl.HashMapFeedInfoCache;
import org.rometools.fetcher.impl.HttpURLFeedFetcher;
import com.sun.syndication.io.SyndFeedOutput;

/**
 * <p>It aggregates a list of RSS/Atom feeds (they can be of different types)
 * into a single feed of the specified type.</p>
 *
 * <p>Converted from the original FeedAggregator sample</p>
 *
 * @author Alejandro Abdelnur
 * @author Nick Lothian
 *
 */
public class FeedAggregator {

    public static void main(String[] args) {
        boolean ok = false;
        if (args.length>=2) {
            try {
                String outputType = args[0];

                SyndFeed feed = new SyndFeedImpl();
                feed.setFeedType(outputType);

                feed.setTitle("Aggregated Feed");
                feed.setDescription("Anonymous Aggregated Feed");
                feed.setAuthor("anonymous");
                feed.setLink("http://www.anonymous.com");

                List entries = new ArrayList();
                feed.setEntries(entries);

				FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.getInstance();
				FeedFetcher feedFetcher = new HttpURLFeedFetcher(feedInfoCache);

                for (int i=1;i<args.length;i++) {
                    URL inputUrl = new URL(args[i]);
					SyndFeed inFeed = feedFetcher.retrieveFeed(inputUrl);
                    entries.addAll(inFeed.getEntries());
                }

                SyndFeedOutput output = new SyndFeedOutput();
				output.output(feed, new PrintWriter(System.out));

                ok = true;
            }
            catch (Exception ex) {
                System.out.println("ERROR: "+ex.getMessage());
                ex.printStackTrace();
            }
        }

        if (!ok) {
            System.out.println();
            System.out.println("FeedAggregator aggregates different feeds into a single one.");
            System.out.println("The first parameter must be the feed type for the aggregated feed.");
            System.out.println(" [valid values are: rss_0.9, rss_0.91, rss_0.92, rss_0.93, ]");
            System.out.println(" [                  rss_0.94, rss_1.0, rss_2.0 & atom_0.3  ]");
            System.out.println("The second to last parameters are the URLs of feeds to aggregate.");
            System.out.println();
        }
    }

}
