package com.rometools.modules.atom.modules;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

import java.io.Serializable;

public class AtomLinkModuleImpl implements AtomLinkModule, Cloneable, Serializable {

    private Link link;

    @Override
    public Link getLink() {
        return link;
    }

    @Override
    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public String getUri() {
        return URI;
    }

    @Override
    public Class<? extends CopyFrom> getInterface() {
        return AtomLinkModule.class;
    }

    @Override
    public void copyFrom(CopyFrom obj) {
        AtomLinkModule other = (AtomLinkModule) obj;
        Link link = other.getLink();
        if (link != null) {
            Link l = new Link();
            l.setHref(link.getHref());
            l.setType(link.getType());
            l.setRel(link.getRel());
            l.setHreflang(link.getHreflang());
            l.setTitle(link.getTitle());
            l.setLength(link.getLength());
            setLink(l);
        }
    }

    @Override
    public Object clone() {
        final AtomLinkModuleImpl m = new AtomLinkModuleImpl();
        if (link != null) {
            Link l = new Link();
            l.setHref(link.getHref());
            l.setType(link.getType());
            l.setRel(link.getRel());
            l.setHreflang(link.getHreflang());
            l.setTitle(link.getTitle());
            l.setLength(link.getLength());
            m.setLink(l);
        }
        return m;
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(AtomLinkModuleImpl.class, this);
        return eBean.beanEquals(obj);
    }

    @Override
    public int hashCode() {
        final EqualsBean equals = new EqualsBean(AtomLinkModuleImpl.class, this);
        return equals.beanHashCode();
    }

    @Override
    public String toString() {
        final ToStringBean tsBean = new ToStringBean(AtomLinkModuleImpl.class, this);
        return tsBean.toString();
    }
}
