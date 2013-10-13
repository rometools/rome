package org.rometools.feed.module.sse.modules;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.module.Module;

/**
 * <sx:conflicts> element within <sx:sync>
 * <p>
 * The sx:conflicts element MUST contain one or more sx:conflict sub-elements.
 */
public class Conflicts extends SSEModule {
    private static final long serialVersionUID = 2722893639295169689L;
    public static final String NAME = "conflicts";

    @Override
    public void copyFrom(final CopyFrom<? extends Module> obj) {
        // nothing to copy, just a place-holder
    }
}
