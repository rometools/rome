package com.rometools.modules.feedpress.modules;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.rometools.rome.feed.module.ModuleImpl;

import java.io.Serializable;

public class FeedpressModuleImpl
        extends ModuleImpl
        implements FeedpressModule, Cloneable, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5013141869124014476L;
	
	private String newsletterId;
    private String locale;
    private String podcastId;
    private String cssFile;

    public FeedpressModuleImpl() {
        super(FeedpressModule.class, FeedpressModule.URI);
    }

    @Override
    public String getNewsletterId() {
        return newsletterId;
    }

    @Override
    public void setNewsletterId(String newsletterId) {
        this.newsletterId = newsletterId;
    }

    @Override
    public String getLocale() {
        return locale;
    }

    @Override
    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String getPodcastId() {
        return podcastId;
    }

    @Override
    public void setPodcastId(String podcastId) {
        this.podcastId = podcastId;
    }

    @Override
    public String getCssFile() {
        return cssFile;
    }

    @Override
    public void setCssFile(String cssFile) {
        this.cssFile = cssFile;
    }

    @Override
    public Class<? extends CopyFrom> getInterface() {
        return FeedpressModule.class;
    }

    @Override
    public void copyFrom(CopyFrom obj) {
        final FeedpressModule feedpress = (FeedpressModule) obj;
        this.setNewsletterId(feedpress.getNewsletterId());
        this.setLocale(feedpress.getLocale());
        this.setPodcastId(feedpress.getPodcastId());
        this.setCssFile(feedpress.getCssFile());
    }

    @Override
    public String getUri() {
        return FeedpressModule.URI;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final FeedpressModule feedpress = new FeedpressModuleImpl();
        feedpress.setNewsletterId(this.getNewsletterId());
        feedpress.setLocale(this.getLocale());
        feedpress.setPodcastId(this.getPodcastId());
        feedpress.setCssFile(this.getCssFile());
        return feedpress;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBean.beanEquals(FeedpressModuleImpl.class, this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBean.toString(FeedpressModuleImpl.class, this);
    }

}
