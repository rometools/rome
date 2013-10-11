/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.rometools.certiorem.sub.data;

import org.rometools.fetcher.impl.SyndFeedInfo;

/**
 *
 * @author najmi
 */
public interface SubscriptionCallback {

    void onNotify(Subscription subscribed, SyndFeedInfo feedInfo);

    void onFailure(Exception e);

    void onSubscribe(Subscription subscribed);
    
    void onUnsubscribe(Subscription subscribed);
}
