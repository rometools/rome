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
package com.rometools.rome.feed.synd.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.rometools.rome.feed.rss.Category;
import com.rometools.rome.feed.rss.Enclosure;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndCategoryImpl;
import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEnclosureImpl;
import com.rometools.rome.feed.synd.SyndEntry;

public class ConverterForRSS092 extends ConverterForRSS091Userland {

    public ConverterForRSS092() {
        this("rss_0.92");
    }

    protected ConverterForRSS092(final String type) {
        super(type);
    }

    @Override
    protected SyndEntry createSyndEntry(final Item item, final boolean preserveWireItem) {

        final SyndEntry syndEntry = super.createSyndEntry(item, preserveWireItem);

        final List<Category> cats = item.getCategories();

        if (!cats.isEmpty()) {

            // using a set to remove duplicates and use a LinkedHashSet to try to retain the
            // document order
            final Set<SyndCategory> s = new LinkedHashSet<SyndCategory>();

            // feed native categories (as syndcat)
            s.addAll(createSyndCategories(cats));

            // DC subjects (as syndcat)
            s.addAll(syndEntry.getCategories());

            syndEntry.setCategories(new ArrayList<SyndCategory>(s)); // c
        }

        final List<Enclosure> enclosures = item.getEnclosures();
        if (!enclosures.isEmpty()) {
            syndEntry.setEnclosures(createSyndEnclosures(enclosures));
        }

        return syndEntry;

    }

    protected List<SyndCategory> createSyndCategories(final List<Category> rssCats) {
        final List<SyndCategory> syndCats = new ArrayList<SyndCategory>();
        for (final Category rssCat : rssCats) {
            final SyndCategory sCat = new SyndCategoryImpl();
            sCat.setTaxonomyUri(rssCat.getDomain());
            sCat.setName(rssCat.getValue());
            syndCats.add(sCat);
        }
        return syndCats;
    }

    protected List<SyndEnclosure> createSyndEnclosures(final List<Enclosure> enclosures) {
        final List<SyndEnclosure> sEnclosures = new ArrayList<SyndEnclosure>();
        for (final Enclosure enc : enclosures) {
            final SyndEnclosure sEnc = new SyndEnclosureImpl();
            sEnc.setUrl(enc.getUrl());
            sEnc.setType(enc.getType());
            sEnc.setLength(enc.getLength());
            sEnclosures.add(sEnc);
        }
        return sEnclosures;
    }

    @Override
    protected Item createRSSItem(final SyndEntry sEntry) {

        final Item item = super.createRSSItem(sEntry);

        final List<SyndCategory> sCats = sEntry.getCategories(); // c
        if (!sCats.isEmpty()) {
            item.setCategories(createRSSCategories(sCats));
        }

        final List<SyndEnclosure> sEnclosures = sEntry.getEnclosures();
        if (!sEnclosures.isEmpty()) {
            item.setEnclosures(createEnclosures(sEnclosures));
        }

        return item;

    }

    protected List<Category> createRSSCategories(final List<SyndCategory> sCats) {
        final List<Category> cats = new ArrayList<Category>();
        for (final SyndCategory sCat : sCats) {
            final Category cat = new Category();
            cat.setDomain(sCat.getTaxonomyUri());
            cat.setValue(sCat.getName());
            cats.add(cat);
        }
        return cats;
    }

    protected List<Enclosure> createEnclosures(final List<SyndEnclosure> sEnclosures) {
        final List<Enclosure> enclosures = new ArrayList<Enclosure>();
        for (final SyndEnclosure sEnc : sEnclosures) {
            final Enclosure enc = new Enclosure();
            enc.setUrl(sEnc.getUrl());
            enc.setType(sEnc.getType());
            enc.setLength(sEnc.getLength());
            enclosures.add(enc);
        }
        return enclosures;
    }

}
