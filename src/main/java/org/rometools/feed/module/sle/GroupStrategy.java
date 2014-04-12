package org.rometools.feed.module.sle;

import org.rometools.feed.module.sle.io.ModuleParser;
import org.rometools.feed.module.sle.types.EntryValue;
import org.rometools.feed.module.sle.types.Group;

import com.sun.syndication.feed.module.Extendable;

class GroupStrategy implements ValueStrategy {

    @Override
    public Comparable getValue(final Extendable o, final Object value) {
        Comparable oc = null;
        try {
            final String uri = ModuleParser.TEMP.getURI();
            final SleEntry entry = (SleEntry) o.getModule(uri);
            final Group group = (Group) value;
            final EntryValue entryValue = entry.getGroupByElement(group);
            oc = entryValue.getValue();
        } catch (final NullPointerException npe) {
            ; // watch it go by
        }
        return oc;
    }

}