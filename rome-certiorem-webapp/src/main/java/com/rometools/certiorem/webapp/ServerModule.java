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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.rometools.certiorem.hub.Hub;
import com.rometools.certiorem.hub.data.ram.InMemoryHubDAO;
import com.rometools.certiorem.hub.notify.standard.UnthreadedNotifier;
import com.rometools.certiorem.hub.verify.standard.UnthreadedVerifier;
import com.rometools.certiorem.sub.Subscriptions;
import com.rometools.certiorem.sub.data.ram.InMemorySubDAO;
import com.rometools.certiorem.sub.request.AsyncRequester;
import com.rometools.fetcher.FeedFetcher;
import com.rometools.fetcher.impl.HashMapFeedInfoCache;
import com.rometools.fetcher.impl.HttpURLFeedFetcher;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class ServerModule extends GuiceServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(ServerModule.class);

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }

            @Provides
            @Singleton
            public Hub buildHub() {
                final FeedFetcher fetcher = new HttpURLFeedFetcher(new HashMapFeedInfoCache());
                final Hub hub = new Hub(new InMemoryHubDAO(), new UnthreadedVerifier(), new UnthreadedNotifier(), fetcher);

                return hub;
            }

            @Provides
            @Singleton
            public Subscriptions buildSubs() {
                LOG.debug("buildSubs");
                final Subscriptions subs = new Subscriptions(new HashMapFeedInfoCache(), new AsyncRequester(), "http://localhost/webapp/subscriptions/",
                        new InMemorySubDAO());

                return subs;
            }
        }, new ServletModule() {
            @Override
            protected void configureServlets() {
                serve("/hub*").with(HubServlet.class);
                serve("/subscriptions/*").with(SubServlet.class);
                serve("/test/sub").with(SubTest.class);
                serve("/test/pub").with(NotifyTest.class);
            }
        });
    }
}
