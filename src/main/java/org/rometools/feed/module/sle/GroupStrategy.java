package org.rometools.feed.module.sle;

import org.rometools.feed.module.sle.io.ModuleParser;
import org.rometools.feed.module.sle.types.EntryValue;
import org.rometools.feed.module.sle.types.Group;

import com.sun.syndication.feed.module.Extendable;

class GroupStrategy implements ValueStrategy {

    @Override
    public EntryValue getValue(final Extendable extendable, final Object value) {
        try {
            final String uri = ModuleParser.TEMP.getURI();
            final SleEntry entry = (SleEntry) extendable.getModule(uri);
            final Group group = (Group) value;
            return entry.getGroupByElement(group);
        } catch (final NullPointerException npe) {
            return null;
        }
    }

}