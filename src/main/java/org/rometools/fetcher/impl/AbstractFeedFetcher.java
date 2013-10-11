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

package org.rometools.fetcher.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import com.sun.syndication.feed.synd.SyndFeed;
import org.rometools.fetcher.FeedFetcher;
import org.rometools.fetcher.FetcherEvent;
import org.rometools.fetcher.FetcherException;
import org.rometools.fetcher.FetcherListener;


public abstract class AbstractFeedFetcher implements FeedFetcher {
	private final Set fetcherEventListeners;
	private String userAgent;
	private boolean usingDeltaEncoding;	
	private boolean preserveWireFeed;
	
	
    
	public AbstractFeedFetcher() {
		fetcherEventListeners = Collections.synchronizedSet(new HashSet());
		
		Properties props = new Properties(System.getProperties());
		String resourceName = "fetcher.properties";
		
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
			if (inputStream == null) {
			    inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
			}			
			if (inputStream != null) {
				props.load(inputStream);
				System.getProperties().putAll(props);
				inputStream.close();
			} else {
				System.err.println("Could not find " + resourceName + " on classpath");
			}
		} catch (IOException e) {
			// do nothing - we don't want to fail just because we could not find the version
			System.err.println("Error reading " + resourceName + " from classpath: " + e.getMessage());
		}		
		
		
		setUserAgent(DEFAULT_USER_AGENT + " Ver: " + System.getProperty("rome.fetcher.version", "UNKNOWN"));
	}

	/**
	 * @return the User-Agent currently being sent to servers
	 */
	public synchronized String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param string The User-Agent to sent to servers
	 */
	public synchronized void setUserAgent(String string) {
		userAgent = string;
	}

	/**
	 * @param eventType The event type to fire
	 * @param connection the current connection
	 */
	protected void fireEvent(String eventType, URLConnection connection) {	    
		fireEvent(eventType, connection.getURL().toExternalForm(), null);
	}
	
	
	/**
	 * @param eventType The event type to fire
	 * @param connection the current connection
	 * @param feed The feed to pass to the event
	 */
	protected void fireEvent(String eventType, URLConnection connection, SyndFeed feed) {	    
		fireEvent(eventType, connection.getURL().toExternalForm(), feed);
	}

	/**
	 * @param eventType The event type to fire
	 * @param urlStr the current url as a string
	 */
	protected void fireEvent(String eventType, String urlStr) {
	    fireEvent(eventType, urlStr, null);
	}	
	
	/**
	 * @param eventType The event type to fire
	 * @param urlStr the current url as a string
	 * @param feed The feed to pass to the event
	 */
	protected void fireEvent(String eventType, String urlStr, SyndFeed feed) {
		FetcherEvent fetcherEvent = new FetcherEvent(this, urlStr, eventType, feed);
		synchronized(fetcherEventListeners) {
			Iterator iter = fetcherEventListeners.iterator();
			while ( iter.hasNext()) {
				FetcherListener fetcherEventListener = (FetcherListener) iter.next();
				fetcherEventListener.fetcherEvent(fetcherEvent);							
			}					
		}
	}	
	
	/**
	 * @see com.sun.syndication.fetcher.FeedFetcher#addFetcherEventListener(com.sun.syndication.fetcher.FetcherListener)
	 */
	public void addFetcherEventListener(FetcherListener listener) {
		if (listener != null) {
			fetcherEventListeners.add(listener);		
		}	
		
	}

	/**
	 * @see com.sun.syndication.fetcher.FeedFetcher#removeFetcherEventListener(com.sun.syndication.fetcher.FetcherListener)
	 */
	public void removeFetcherEventListener(FetcherListener listener) {
		if (listener != null) {
			fetcherEventListeners.remove(listener);		
		}		
	}

    /**
     * @return Returns the useDeltaEncoding.
     */
    public synchronized boolean isUsingDeltaEncoding() {
        return usingDeltaEncoding;
    }
    /**
     * @param useDeltaEncoding The useDeltaEncoding to set.
     */
    public synchronized void setUsingDeltaEncoding(boolean useDeltaEncoding) {
        this.usingDeltaEncoding = useDeltaEncoding;
    }		
	
	/**
	 * <p>Handles HTTP error codes.</p>
	 *
	 * @param responseCode the HTTP response code
	 * @throws FetcherException if response code is in the range 400 to 599 inclusive
	 */
	protected void handleErrorCodes(int responseCode) throws FetcherException {
		// Handle 2xx codes as OK, so ignore them here
		// 3xx codes are handled by the HttpURLConnection class
	    if (responseCode == 403) {
	        // Authentication is required
	        throwAuthenticationError(responseCode);
	    } else if (responseCode >= 400 && responseCode < 500) {
			throw4XXError(responseCode);
		} else if (responseCode >= 500 && responseCode < 600) {
			throw new FetcherException(responseCode, "The server encounted an error. HTTP Response code was:" + responseCode);
		}
	}
	
	protected void throw4XXError(int responseCode) throws FetcherException {
		throw new FetcherException(responseCode, "The requested resource could not be found. HTTP Response code was:" + responseCode);
	}

	protected void throwAuthenticationError(int responseCode) throws FetcherException {
		throw new FetcherException(responseCode, "Authentication required for that resource. HTTP Response code was:" + responseCode);
	}
	
	/**
	 * <p>Combine the entries in two feeds into a single feed.</p>
	 * 
	 * <p>The returned feed will have the same data as the newFeed parameter, with 
	 * the entries from originalFeed appended to the end of its entries.</p>
	 * 
	 * @param originalFeed
	 * @param newFeed
	 * @return
	 */
	public static SyndFeed combineFeeds(SyndFeed originalFeed, SyndFeed newFeed) {
	    SyndFeed result;
        try {
            result = (SyndFeed) newFeed.clone();
            
            result.getEntries().addAll(result.getEntries().size(), originalFeed.getEntries());
            
            return result;
        } catch (CloneNotSupportedException e) {
            IllegalArgumentException iae = new IllegalArgumentException("Cannot clone feed");
            iae.initCause(e);
            throw iae;
        }        
	}

	public boolean isPreserveWireFeed() {
		return preserveWireFeed;
	}

	public void setPreserveWireFeed(boolean preserveWireFeed) {
		this.preserveWireFeed = preserveWireFeed;
	}
	
}
