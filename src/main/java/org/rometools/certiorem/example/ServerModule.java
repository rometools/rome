/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rometools.certiorem.example;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import org.rometools.certiorem.hub.Hub;
import org.rometools.certiorem.hub.data.ram.InMemoryHubDAO;
import org.rometools.certiorem.hub.notify.standard.UnthreadedNotifier;
import org.rometools.certiorem.hub.verify.standard.UnthreadedVerifier;
import org.rometools.certiorem.sub.Subscriptions;
import org.rometools.certiorem.sub.data.ram.InMemorySubDAO;
import org.rometools.certiorem.sub.request.AsyncRequester;

import org.rometools.fetcher.FeedFetcher;
import org.rometools.fetcher.impl.HashMapFeedInfoCache;
import org.rometools.fetcher.impl.HttpURLFeedFetcher;


/**
 *
 * @author robert.cooper
 */
public class ServerModule extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new AbstractModule() {
                @Override
                protected void configure() {
                }

                @Provides
                @Singleton
                public Hub buildHub() {
                    FeedFetcher fetcher = new HttpURLFeedFetcher(new HashMapFeedInfoCache());
                    Hub hub = new Hub(new InMemoryHubDAO(), new UnthreadedVerifier(), new UnthreadedNotifier(), fetcher);

                    return hub;
                }

                @Provides
                @Singleton
                public Subscriptions buildSubs() {
                    System.out.println("buildSubs");
                    Subscriptions subs = new Subscriptions(new HashMapFeedInfoCache(), new AsyncRequester(),
                            "http://localhost/webapp/subscriptions/", new InMemorySubDAO());

                    return subs;
                }
            },
            new ServletModule() {
                @Override
                protected void configureServlets() {
                    serve("/hub*")
                        .with(HubServlet.class);
                    serve("/subscriptions/*")
                            .with(SubServlet.class);
                    serve("/test/sub")
                            .with(SubTest.class);
                    serve("/test/pub")
                            .with(NotifyTest.class);
                }
            });
    }
}
