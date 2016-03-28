/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rometools.certiorem.webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.rometools.certiorem.sub.Subscriptions;
import com.rometools.certiorem.sub.data.Subscription;
import com.rometools.certiorem.sub.data.SubscriptionCallback;
import com.rometools.fetcher.impl.SyndFeedInfo;

/**
 *
 * @author robert.cooper
 */
@Singleton
public class SubTest extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SubTest.class);

    private static final long serialVersionUID = 1L;

    private final Subscriptions subs;

    @Inject
    public SubTest(final Subscriptions subs) {
        this.subs = subs;
    }

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) {
        subs.subscribe("http://localhost/webapp/hub", "http://localhost/webapp/research-atom.xml", true, -1L, null, new SubscriptionCallback() {

            @Override
            public void onFailure(final Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onSubscribe(final Subscription subscribed) {
                LOG.debug("Subscribed {} {}", subscribed.getId(), subscribed.getSourceUrl());
            }

            @Override
            public void onNotify(final Subscription subscribed, final SyndFeedInfo feedInfo) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onUnsubscribe(final Subscription subscribed) {
                // TODO Auto-generated method stub
            }

        });
    }

}
