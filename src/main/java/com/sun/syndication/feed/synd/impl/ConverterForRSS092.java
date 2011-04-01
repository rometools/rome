/*
 * Copyright 2004 Sun Microsystems, Inc.
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
package com.sun.syndication.feed.synd.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.sun.syndication.feed.rss.Category;
import com.sun.syndication.feed.rss.Enclosure;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEnclosureImpl;
import com.sun.syndication.feed.synd.SyndEntry;

/**
 */
public class ConverterForRSS092 extends ConverterForRSS091Userland {

    public ConverterForRSS092() {
        this("rss_0.92");
    }

    protected ConverterForRSS092(String type) {
        super(type);
    }

    @Override
    protected SyndEntry createSyndEntry(Item item, boolean preserveWireItem) {
        SyndEntry syndEntry = super.createSyndEntry(item, preserveWireItem);
        List cats =  item.getCategories();
        if (cats.size()>0) {
            Set s = new LinkedHashSet();                // using a set to remove duplicates and use a LinkedHashSet to try to retain the document order
            s.addAll(createSyndCategories(cats)); // feed native categories (as syndcat)
            s.addAll(syndEntry.getCategories());   // DC subjects (as syndcat)
            syndEntry.setCategories(new ArrayList(s));    //c
        }
        List enclosures = item.getEnclosures();
        if (enclosures.size()>0) {
            syndEntry.setEnclosures(createSyndEnclosures(enclosures));
        }
        return syndEntry;
    }

    protected List createSyndCategories(List rssCats) {
        List syndCats = new ArrayList();
        for (int i=0;i<rssCats.size();i++) {
            Category rssCat = (Category) rssCats.get(i);
            SyndCategory sCat = new SyndCategoryImpl();
            sCat.setTaxonomyUri(rssCat.getDomain());
            sCat.setName(rssCat.getValue());
            syndCats.add(sCat);
        }
        return syndCats;
    }

    protected List createSyndEnclosures(List enclosures) {
        List sEnclosures = new ArrayList();
        for (int i=0;i<enclosures.size();i++) {
            Enclosure enc = (Enclosure) enclosures.get(i);
            SyndEnclosure sEnc = new SyndEnclosureImpl();
            sEnc.setUrl(enc.getUrl());
            sEnc.setType(enc.getType());
            sEnc.setLength(enc.getLength());
            sEnclosures.add(sEnc);
        }
        return sEnclosures;
    }

    @Override
    protected Item createRSSItem(SyndEntry sEntry) {
        Item item = super.createRSSItem(sEntry);

        List sCats =  sEntry.getCategories();    //c
        if (sCats.size()>0) {
            item.setCategories(createRSSCategories(sCats));
        }
        List sEnclosures = sEntry.getEnclosures();
        if (sEnclosures.size()>0) {
            item.setEnclosures(createEnclosures(sEnclosures));
        }
        return item;
    }

    protected List createRSSCategories(List sCats) {
        List cats = new ArrayList();
        for (int i=0;i<sCats.size();i++) {
            SyndCategory sCat = (SyndCategory) sCats.get(i);
            Category cat = new Category();
            cat.setDomain(sCat.getTaxonomyUri());
            cat.setValue(sCat.getName());
            cats.add(cat);
        }
        return cats;
    }

    protected List createEnclosures(List sEnclosures) {
        List enclosures = new ArrayList();
        for (int i=0;i<sEnclosures.size();i++) {
            SyndEnclosure sEnc = (SyndEnclosure) sEnclosures.get(i);
            Enclosure enc = new Enclosure();
            enc.setUrl(sEnc.getUrl());
            enc.setType(sEnc.getType());
            enc.setLength(sEnc.getLength());
            enclosures.add(enc);
        }
        return enclosures;
    }

}
