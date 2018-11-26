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
 */

package com.rometools.modules.atom.modules;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class AtomLinkModuleImpl implements AtomLinkModule, Cloneable, Serializable {

    private List<Link> links = new LinkedList<Link>();

    @Override
    public List<Link> getLinks() {
        return links;
    }

    @Override
    public Link getLink() {
        if(links.size() > 0) {
            return links.get(0);
        }
        return null;
    }

    @Override
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public void setLink(Link link) {
        if(links.size() > 0) {
            links.set(0, link);
        } else {
            links.add(link);
        }
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
        List<Link> links = other.getLinks();
        for (Link link : links) {
            Link l = new Link();
            l.setHref(link.getHref());
            l.setType(link.getType());
            l.setRel(link.getRel());
            l.setHreflang(link.getHreflang());
            l.setTitle(link.getTitle());
            l.setLength(link.getLength());
            this.links.add(l);
        }
    }

    @Override
    public Object clone() {
        final AtomLinkModuleImpl m = new AtomLinkModuleImpl();
        List<Link> result = new LinkedList<Link>();
        for(Link link : this.getLinks()) {
            Link l = new Link();
            l.setHref(link.getHref());
            l.setType(link.getType());
            l.setRel(link.getRel());
            l.setHreflang(link.getHreflang());
            l.setTitle(link.getTitle());
            l.setLength(link.getLength());
            result.add(l);
        }
        links.subList(0, links.size());
        m.setLinks(result);
        return m;
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBean.beanEquals(AtomLinkModuleImpl.class, this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBean.toString(AtomLinkModuleImpl.class, this);
    }
}
