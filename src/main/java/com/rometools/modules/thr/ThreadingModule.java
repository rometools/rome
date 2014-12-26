package com.rometools.modules.thr;

import com.rometools.rome.feed.module.Module;

/**
 * Currently no support for thr:count, thr:updated, thr:total link attributes.
 * 
 * @author <a href="mailto:andreas@feldschmid.com">Andreas Feldschmid</a>
 */
public interface ThreadingModule extends Module {

    public static final String URI = "http://purl.org/syndication/thread/1.0";

    public String getRef();

    public void setRef(String ref);

    public String getType();

    public void setType(String type);

    public String getHref();

    public void setHref(String href);

    public String getSource();

    public void setSource(String href);
}
