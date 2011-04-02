package org.rometools.feed.module.sse.modules;

import com.sun.syndication.feed.CopyFrom;

/**
 * <sx:conflicts> element within <sx:sync>
 * <p>
 * The sx:conflicts element MUST contain one or more sx:conflict sub-elements.
 */
public class Conflicts extends SSEModule {
    public static final String NAME = "conflicts";

    public void copyFrom(CopyFrom obj) {
        // nothing to copy, just a place-holder
    }
}
