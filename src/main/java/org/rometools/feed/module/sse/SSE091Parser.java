package org.rometools.feed.module.sse;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.jdom2.filter.AbstractFilter;
import org.rometools.feed.module.sse.modules.Conflict;
import org.rometools.feed.module.sse.modules.Conflicts;
import org.rometools.feed.module.sse.modules.History;
import org.rometools.feed.module.sse.modules.Related;
import org.rometools.feed.module.sse.modules.SSEModule;
import org.rometools.feed.module.sse.modules.Sharing;
import org.rometools.feed.module.sse.modules.Sync;
import org.rometools.feed.module.sse.modules.Update;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.io.DelegatingModuleParser;
import com.sun.syndication.io.WireFeedParser;
import com.sun.syndication.io.impl.DateParser;
import com.sun.syndication.io.impl.RSS20Parser;

/**
 * Parses embedded SSE content from RSS channel and item content.
 * 
 * @author <a href="mailto:ldornin@dev.java.net">ldornin</a>
 */
public class SSE091Parser implements DelegatingModuleParser {
    static Logger log = Logger.getLogger(SSE091Parser.class.getName());

    // root of the sharing element
    private RSS20Parser rssParser;

    /** Creates a new instance of SSE091Parser */
    public SSE091Parser() {
    }

    @Override
    public void setFeedParser(final WireFeedParser feedParser) {
        rssParser = (RSS20Parser) feedParser;
    }

    @Override
    public String getNamespaceUri() {
        return SSEModule.SSE_SCHEMA_URI;
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        SSEModule sseModule = null;
        final String name = element.getName();

        if (name.equals("rss")) {
            sseModule = parseSharing(element, locale);
        } else if (name.equals("item")) {
            sseModule = parseSync(element, locale);
        }
        return sseModule;
    }

    private Sharing parseSharing(final Element element, final Locale locale) {
        final Element root = getRoot(element);

        Sharing sharing = null;
        final Element sharingChild = root.getChild(Sharing.NAME, SSEModule.SSE_NS);
        if (sharingChild != null) {
            sharing = new Sharing();
            sharing.setOrdered(parseBooleanAttr(sharingChild, Sharing.ORDERED_ATTRIBUTE));
            sharing.setSince(parseDateAttribute(sharingChild, Sharing.SINCE_ATTRIBUTE, locale));
            sharing.setUntil(parseDateAttribute(sharingChild, Sharing.UNTIL_ATTRIBUTE, locale));
            sharing.setWindow(parseIntegerAttribute(sharingChild, Sharing.WINDOW_ATTRIBUTE));
            sharing.setVersion(parseStringAttribute(sharingChild, Sharing.VERSION_ATTRIBUTE));
            parseRelated(root, sharing, locale);
        }

        return sharing;
    }

    private void parseRelated(final Element root, final Sharing sharing, final Locale locale) {
        Related related;
        final Element relatedChild = root.getChild(Related.NAME, SSEModule.SSE_NS);
        if (relatedChild != null) {
            related = new Related();
            // TODO; is this an attribute?
            related.setLink(parseStringAttribute(relatedChild, Related.LINK_ATTRIBUTE));
            related.setSince(parseDateAttribute(relatedChild, Related.SINCE_ATTRIBUTE, locale));
            related.setTitle(parseStringAttribute(relatedChild, Related.TITLE_ATTRIBUTE));
            related.setType(parseIntegerAttribute(relatedChild, Related.TYPE_ATTRIBUTE));
            related.setUntil(parseDateAttribute(relatedChild, Related.UNTIL_ATTRIBUTE, locale));
            sharing.setRelated(related);
        }
    }

    private Sync parseSync(final Element element, final Locale locale) {
        // Now I am going to get the item specific tags
        final Element syncChild = element.getChild(Sync.NAME, SSEModule.SSE_NS);
        Sync sync = null;

        if (syncChild != null) {
            sync = new Sync();
            sync.setId(parseStringAttribute(syncChild, Sync.ID_ATTRIBUTE));
            sync.setVersion(parseIntegerAttribute(syncChild, Sync.VERSION_ATTRIBUTE));
            sync.setDeleted(parseBooleanAttr(syncChild, Sync.DELETED_ATTRIBUTE));
            sync.setConflict(parseBooleanAttr(syncChild, Sync.CONFLICT_ATTRIBUTE));
            sync.setHistory(parseHistory(syncChild, locale));
            sync.setConflicts(parseConflicts(syncChild, locale));
        }
        return sync;
    }

    private List<Conflict> parseConflicts(final Element syncElement, final Locale locale) {
        List<Conflict> conflicts = null;

        final List<Element> conflictsContent = syncElement.getContent(new ContentFilter(Conflicts.NAME));
        for (final Iterator<Element> conflictsIter = conflictsContent.iterator(); conflictsIter.hasNext();) {
            final Element conflictsElement = conflictsIter.next();

            final List<Element> conflictContent = conflictsElement.getContent(new ContentFilter(Conflict.NAME));
            for (final Iterator<Element> conflictIter = conflictContent.iterator(); conflictIter.hasNext();) {
                final Element conflictElement = (Element) conflictIter.next();

                final Conflict conflict = new Conflict();
                conflict.setBy(parseStringAttribute(conflictElement, Conflict.BY_ATTRIBUTE));
                conflict.setWhen(parseDateAttribute(conflictElement, Conflict.WHEN_ATTRIBUTE, locale));
                conflict.setVersion(parseIntegerAttribute(conflictElement, Conflict.VERSION_ATTRIBUTE));

                final List<Element> conflictItemContent = conflictElement.getContent(new ContentFilter("item"));
                for (final Iterator<Element> conflictItemIter = conflictItemContent.iterator(); conflictItemIter.hasNext();) {
                    final Element conflictItemElement = (Element) conflictItemIter.next();
                    final Element root = getRoot(conflictItemElement);
                    final Item conflictItem = rssParser.parseItem(root, conflictItemElement, locale);
                    conflict.setItem(conflictItem);

                    if (conflicts == null) {
                        conflicts = new ArrayList<Conflict>();
                    }
                    conflicts.add(conflict);
                }
            }
        }

        return conflicts;
    }

    private Element getRoot(final Element start) {
        // reach up to grab the sharing element out of the root
        Element root = start;

        while (root.getParent() != null && root.getParent() instanceof Element) {
            root = (Element) root.getParent();
        }
        return root;
    }

    private History parseHistory(final Element historyElement, final Locale locale) {
        final Element historyContent = getFirstContent(historyElement, History.NAME);

        History history = null;
        if (historyContent != null) {
            history = new History();
            history.setBy(parseStringAttribute(historyContent, History.BY_ATTRIBUTE));
            history.setWhen(parseDateAttribute(historyContent, History.WHEN_ATTRIBUTE, locale));
            parseUpdates(historyContent, history, locale);
        }
        return history;
    }

    private Element getFirstContent(final Element element, final String name) {
        final List<Element> filterList = element.getContent(new ContentFilter(name));
        Element firstContent = null;
        if (filterList != null && filterList.size() > 0) {
            firstContent = (Element) filterList.get(0);
        }
        return firstContent;
    }

    private void parseUpdates(final Element historyChild, final History history, final Locale locale) {
        final List<Element> updatedChildren = historyChild.getContent(new ContentFilter(Update.NAME));
        for (final Iterator<Element> childIter = updatedChildren.iterator(); childIter.hasNext();) {
            final Element updateChild = childIter.next();
            final Update update = new Update();
            update.setBy(parseStringAttribute(updateChild, Update.BY_ATTRIBUTE));
            update.setWhen(parseDateAttribute(updateChild, Update.WHEN_ATTRIBUTE, locale));
            history.addUpdate(update);
        }
    }

    private String parseStringAttribute(final Element syncChild, final String attrName) {
        final Attribute idAttribute = syncChild.getAttribute(attrName);
        return idAttribute != null ? idAttribute.getValue().trim() : null;
    }

    private Integer parseIntegerAttribute(final Element sharingChild, final String attrName) {
        final Attribute integerAttribute = sharingChild.getAttribute(attrName);
        Integer integerAttr = null;
        if (integerAttribute != null) {
            try {
                integerAttr = new Integer(integerAttribute.getIntValue());
            } catch (final DataConversionException e) {
                // dont use the data
            }
        }
        return integerAttr;
    }

    private Boolean parseBooleanAttr(final Element sharingChild, final String attrName) {
        final Attribute attribute = sharingChild.getAttribute(attrName);
        Boolean attrValue = null;
        if (attribute != null) {
            try {
                attrValue = Boolean.valueOf(attribute.getBooleanValue());
            } catch (final DataConversionException e) {
                // dont use the data
            }
        }
        return attrValue;
    }

    private Date parseDateAttribute(final Element childElement, final String attrName, final Locale locale) {
        final Attribute dateAttribute = childElement.getAttribute(attrName);
        final Date date = null;
        if (dateAttribute != null) {
            // SSE spec requires the timezone to be 'GMT'
            // admittedly, this is a bit heavy-handed
            final String dateAttr = dateAttribute.getValue().trim();
            return DateParser.parseRFC822(dateAttr, locale);
        }
        return date;
    }

    private static class ContentFilter extends AbstractFilter<Element> {
        private static final long serialVersionUID = 9087423853758730810L;
        private final String name;

        private ContentFilter(final String name) {
            this.name = name;
        }

        @Override
        public Element filter(final Object content) {
            final Element returnValue;
            if (content instanceof Element && name.equals(((Element) content).getName())) {
                returnValue = (Element) content;
            } else {
                returnValue = null;
            }
            return returnValue;
        }

    }

}
