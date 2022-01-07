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
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.feed.synd.SyndPersonImpl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class AtomLinkModuleImpl implements AtomLinkModule, Cloneable, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1830237533424528186L;
	
	private List<Link> links = new LinkedList<Link>();
    private List<SyndPerson> authors = new LinkedList<SyndPerson>();
    private List<SyndPerson> contributors = new LinkedList<SyndPerson>();

    @Override
    public List<Link> getLinks() {
        return links;
    }

    @Override
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public List<SyndPerson> getAuthors() {
        return this.authors;
    }

    @Override
    public void setAuthors(List<SyndPerson> authors) {
        this.authors = authors;
    }

    @Override
    public List<SyndPerson> getContributors() {
        return this.contributors;
    }

    @Override
    public void setContributors(List<SyndPerson> contributors) {
        this.contributors = contributors;
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
        final AtomLinkModule other = (AtomLinkModule) obj;
        copyLinks(other.getLinks(), this.links);
        copyPerson(other.getAuthors(), this.authors);
        copyPerson(other.getContributors(), this.contributors);
    }

    private void copyLinks(List<Link> from, List<Link> to) {
        for (Link link : from) {
            final Link l = new Link();
            l.setHref(link.getHref());
            l.setType(link.getType());
            l.setRel(link.getRel());
            l.setHreflang(link.getHreflang());
            l.setTitle(link.getTitle());
            l.setLength(link.getLength());
            to.add(l);
        }
    }

    private void copyPerson(List<SyndPerson> from, List<SyndPerson> to) {
        for (SyndPerson person : from) {
            final SyndPerson p = new SyndPersonImpl();
            p.setName(person.getName());
            p.setEmail(person.getEmail());
            p.setUri(person.getUri());
            to.add(p);
        }
    }

    @Override
    public Object clone() {
        final AtomLinkModuleImpl m = new AtomLinkModuleImpl();

        final List<Link> linksCopy = new LinkedList<Link>();
        copyLinks(this.getLinks(), linksCopy);
        m.setLinks(linksCopy);

        final List<SyndPerson> authorsCopy = new LinkedList<SyndPerson>();
        copyPerson(this.getAuthors(), authorsCopy);
        m.setAuthors(authorsCopy);

        final List<SyndPerson> contributorsCopy = new LinkedList<SyndPerson>();
        copyPerson(this.getContributors(), contributorsCopy);
        m.setContributors(contributorsCopy);

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
