package com.rometools.fetcher.impl;

import java.beans.EventSetDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

import com.rometools.fetcher.FetcherEvent;
import com.rometools.fetcher.FetcherListener;

/**
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public class AbstractFeedFetcherBeanInfo extends SimpleBeanInfo {

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {

        try {

            // get the class object which we'll describe
            final Class<AbstractFeedFetcher> clz = AbstractFeedFetcher.class;
            final Method addMethod = clz.getMethod("addFetcherEventListener", new Class[] { FetcherListener.class });
            final Method removeMethod = clz.getMethod("removeFetcherEventListener", new Class[] { FetcherListener.class });
            final Method listenerMethod = FetcherListener.class.getMethod("fetcherEvent", new Class[] { FetcherEvent.class });
            final EventSetDescriptor est = new EventSetDescriptor("fetcherEvent", clz, new Method[] { listenerMethod }, addMethod, removeMethod);
            return new EventSetDescriptor[] { est };

        } catch (final Exception e) {
            // IntrospectionException, SecurityException and/or NoSuchMethodException can be thrown
            // here. The best we can do is to convert them to runtime exceptions
            throw new RuntimeException(e);
        }

    }

}
