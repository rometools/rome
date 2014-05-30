package com.rometools.modules.sse.modules;

import com.rometools.rome.feed.CopyFrom;

/**
 * <sx:conflicts> element within <sx:sync>
 * <p>
 * The sx:conflicts element MUST contain one or more sx:conflict sub-elements.
 */
public class Conflicts extends SSEModule {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "conflicts";

    @Override
    public void copyFrom(final CopyFrom obj) {
        // nothing to copy, just a place-holder
    }
}
