package org.rometools.fetcher;

import java.util.EventObject;

import com.sun.syndication.feed.synd.SyndFeed;

/**
 * Implementation note: FetcherEvent is not thread safe. Make sure that
 * they are only ever accessed by one thread. If necessary, make all getters
 * and setters synchronized, or alternatively make all fields final.
 * 
 * @author nl
 */
public class FetcherEvent extends EventObject {

	private static final long serialVersionUID = 3985600601904140103L;

	public static final String EVENT_TYPE_FEED_POLLED = "FEED_POLLED";
	public static final String EVENT_TYPE_FEED_RETRIEVED = "FEED_RETRIEVED";
	public static final String EVENT_TYPE_FEED_UNCHANGED = "FEED_UNCHANGED";
	
	private String eventType;
	private String urlString;
	private SyndFeed feed;
    
	public FetcherEvent(Object source) {
		super(source);
	}


	public FetcherEvent(Object source, String urlStr, String eventType) {
		this(source);
		setUrlString(urlStr);
		setEventType(eventType);
	}	

	public FetcherEvent(Object source, String urlStr, String eventType, SyndFeed feed) {
		this(source, urlStr, eventType);
		setFeed(feed);
	}	
	
	
    /**
     * @return Returns the feed.
     * 
     * <p>The feed will only be set if the eventType is EVENT_TYPE_FEED_RETRIEVED</p>
     */
    public SyndFeed getFeed() {
        return feed;
    }
    
    /**
     * @param feed The feed to set.
     * 
     * <p>The feed will only be set if the eventType is EVENT_TYPE_FEED_RETRIEVED</p>
     */
    public void setFeed(SyndFeed feed) {
        this.feed = feed;
    }
	
	/**
	 * @return Returns the eventType.
	 */
	public String getEventType() {
		return eventType;
	}
	/**
	 * @param eventType The eventType to set.
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return Returns the urlString.
	 */
	public String getUrlString() {
		return urlString;
	}
	/**
	 * @param urlString The urlString to set.
	 */
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
}
