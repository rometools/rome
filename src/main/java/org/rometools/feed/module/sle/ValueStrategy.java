package org.rometools.feed.module.sle;

import org.rometools.feed.module.sle.types.EntryValue;

import com.sun.syndication.feed.module.Extendable;

interface ValueStrategy {

    public EntryValue getValue(Extendable extendable, Object value);

}