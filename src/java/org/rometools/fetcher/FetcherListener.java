package org.rometools.fetcher;

import java.util.EventListener;


public interface FetcherListener extends EventListener {

	/**
	 * <p>Called when a fetcher event occurs</p>
	 * 
	 * @param event the event that fired
	 */
	public void fetcherEvent(FetcherEvent event);
	
}
