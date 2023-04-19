package com.rometools.opml.feed.opml;

import java.io.Serializable;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

public class Cloud implements Cloneable, Serializable {
    
    private static final long serialVersionUID = 1L;

    private String domain;
    private String port;
    private String path;
    private String registerProcedure;
    private String protocol;

    public Cloud() {

    }

    public final String getDomain() {
        return this.domain;
    }

    public final void setDomain(final String domain) {
        this.domain = domain;
    }

    public final String getPort() {
        return this.port;
    }

    public final void setPort(final String port) {
        this.port = port;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(final String path) {
        this.path = path;
    }

    public final String getRegisterProcedure() {
        return this.registerProcedure;
    }

    public final void setRegisterProcedure(final String registerProcedure) {
        this.registerProcedure = registerProcedure;
    }

    public final String getProtocol() {
        return this.protocol;
    }

    public final void setProtocol(final String protocol) {
        this.protocol = protocol;
    }

    @Override
    public Object clone() {
        final Cloud c = new Cloud();
        c.setDomain(getDomain());
        c.setPort(getPort());
        c.setPath(getPath());
        c.setRegisterProcedure(getRegisterProcedure());
        c.setProtocol(getProtocol());
        return c;
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBean.beanEquals(Cloud.class, this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBean.toString(Cloud.class, this);
    }
}
