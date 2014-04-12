package org.rometools.feed.module.sle;

import com.sun.syndication.feed.module.Extendable;

interface ValueStrategy {

    public Comparable getValue(Extendable o, Object valueName);

}