/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
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
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
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
