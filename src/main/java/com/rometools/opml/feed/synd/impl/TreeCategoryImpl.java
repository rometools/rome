package com.rometools.opml.feed.synd.impl;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndCategoryImpl;

/**
 * @author cooper
 */
public class TreeCategoryImpl extends SyndCategoryImpl {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(final Object o) {
        final SyndCategory c = (SyndCategory) o;
        if (c.getTaxonomyUri() != null && c.getTaxonomyUri().equals(getTaxonomyUri())) {
            return true;
        } else {
            return false;
        }
    }

}
