package org.rometools.fetcher.impl;

import java.beans.EventSetDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

import org.rometools.fetcher.FetcherEvent;
import org.rometools.fetcher.FetcherListener;

public class AbstractFeedFetcherBeanInfo extends SimpleBeanInfo {

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            final Class<AbstractFeedFetcher> clz = AbstractFeedFetcher.class; // get the class object which we'll describe
            final Method addMethod = clz.getMethod("addFetcherEventListener", new Class[] { FetcherListener.class });
            final Method removeMethod = clz.getMethod("removeFetcherEventListener", new Class[] { FetcherListener.class });
            final Method listenerMethod = FetcherListener.class.getMethod("fetcherEvent", new Class[] { FetcherEvent.class });
            final EventSetDescriptor est = new EventSetDescriptor("fetcherEvent", clz, new Method[] { listenerMethod }, addMethod, removeMethod);
            final EventSetDescriptor[] results = new EventSetDescriptor[] { est };
            return results;
        } catch (final Exception e) {
            // IntrospectionException, SecurityException and/or NoSuchMethodException can be thrown here
            // the best we can do is to convert them to runtime exceptions
            throw new RuntimeException(e);
        }
    }

}
