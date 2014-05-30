package com.rometools.modules.sle;

import com.rometools.modules.sle.io.ModuleParser;
import com.rometools.modules.sle.types.EntryValue;
import com.rometools.modules.sle.types.Sort;
import com.rometools.rome.feed.module.Extendable;

class SortStrategy implements ValueStrategy {

    @Override
    public EntryValue getValue(final Extendable extendable, final Object value) {
        try {
            final String uri = ModuleParser.TEMP.getURI();
            final SleEntry entry = (SleEntry) extendable.getModule(uri);
            final Sort sort = (Sort) value;
            return entry.getSortByElement(sort);
        } catch (final NullPointerException npe) {
            return null;
        }
    }

}