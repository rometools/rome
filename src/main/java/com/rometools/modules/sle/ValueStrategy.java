package com.rometools.modules.sle;

import com.rometools.modules.sle.types.EntryValue;
import com.rometools.rome.feed.module.Extendable;

interface ValueStrategy {

    public EntryValue getValue(Extendable extendable, Object value);

}