package com.rometools.fetcher;

import java.util.EventListener;

/**
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public interface FetcherListener extends EventListener {

    /**
     * <p>
     * Called when a fetcher event occurs
     * </p>
     *
     * @param event the event that fired
     */
    public void fetcherEvent(FetcherEvent event);

}
