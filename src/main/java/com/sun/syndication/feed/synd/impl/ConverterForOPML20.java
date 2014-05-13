/*
 * ConverterForOPML20.java
 *
 * Created on April 25, 2006, 5:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.sun.syndication.feed.synd.impl;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 *
 * @author cooper
 */
public class ConverterForOPML20 extends ConverterForOPML10 {
    /** Creates a new instance of ConverterForOPML20 */
    public ConverterForOPML20() {
        super();
    }

    /**
     * Returns the type (version) of the real feed this converter handles.
     * <p>
     *
     * @return the real feed type.
     * @see WireFeed for details on the format of this string.
     *      <p>
     */
    @Override
    public String getType() {
        return "opml_2.0";
    }

    /**
     * Makes a deep copy/conversion of the values of a real feed into a SyndFeedImpl.
     * <p>
     * It assumes the given SyndFeedImpl has no properties set.
     * <p>
     *
     * @param feed real feed to copy/convert.
     * @param syndFeed the SyndFeedImpl that will contain the copied/converted values of the real
     *            feed.
     */
    @Override
    public void copyInto(final WireFeed feed, final SyndFeed syndFeed) {
        super.copyInto(feed, syndFeed);
    }

    /**
     * Creates real feed with a deep copy/conversion of the values of a SyndFeedImpl.
     * <p>
     *
     * @param syndFeed SyndFeedImpl to copy/convert value from.
     * @return a real feed with copied/converted values of the SyndFeedImpl.
     */
    @Override
    public WireFeed createRealFeed(final SyndFeed syndFeed) {
        WireFeed retValue;

        retValue = super.createRealFeed(syndFeed);

        return retValue;
    }
}
