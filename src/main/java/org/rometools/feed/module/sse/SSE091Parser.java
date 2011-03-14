package org.rometools.feed.module.sse;

import com.sun.syndication.feed.module.Module;
import org.rometools.feed.module.sse.modules.*;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.io.DelegatingModuleParser;
import com.sun.syndication.io.WireFeedParser;
import com.sun.syndication.io.impl.DateParser;
import com.sun.syndication.io.impl.RSS20Parser;
import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Element;
import org.jdom.filter.Filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

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

    public void setFeedParser(WireFeedParser feedParser) {
        this.rssParser = (RSS20Parser)feedParser;
    }

    public String getNamespaceUri() {
        return SSEModule.SSE_SCHEMA_URI;
    }

    public Module parse(org.jdom.Element element) {
        SSEModule sseModule = null;
        String name = element.getName();

        if (name.equals("rss")) {
            sseModule = parseSharing(element);
        } else if (name.equals("item")) {
            sseModule = parseSync(element);
        }
        return sseModule;
    }

    private Sharing parseSharing(Element element) {
        Element root = getRoot(element);

        Sharing sharing = null;
        Element sharingChild = root.getChild(Sharing.NAME, SSEModule.SSE_NS);
        if (sharingChild != null) {
            sharing = new Sharing();
            sharing.setOrdered(parseBooleanAttr(sharingChild, Sharing.ORDERED_ATTRIBUTE));
            sharing.setSince(parseDateAttribute(sharingChild, Sharing.SINCE_ATTRIBUTE));
            sharing.setUntil(parseDateAttribute(sharingChild, Sharing.UNTIL_ATTRIBUTE));
            sharing.setWindow(parseIntegerAttribute(sharingChild, Sharing.WINDOW_ATTRIBUTE));
            sharing.setVersion(parseStringAttribute(sharingChild, Sharing.VERSION_ATTRIBUTE));
            parseRelated(root, sharing);
        }

        return sharing;
    }

    private void parseRelated(Element root, Sharing sharing) {
        Related related;
        Element relatedChild = root.getChild(Related.NAME, SSEModule.SSE_NS);
        if (relatedChild != null) {
            related = new Related();
            // TODO; is this an attribute?
            related.setLink(parseStringAttribute(relatedChild, Related.LINK_ATTRIBUTE));
            related.setSince(parseDateAttribute(relatedChild, Related.SINCE_ATTRIBUTE));
            related.setTitle(parseStringAttribute(relatedChild, Related.TITLE_ATTRIBUTE));
            related.setType(parseIntegerAttribute(relatedChild, Related.TYPE_ATTRIBUTE));
            related.setUntil(parseDateAttribute(relatedChild, Related.UNTIL_ATTRIBUTE));
            sharing.setRelated(related);
        }
    }

    private Sync parseSync(Element element) {
        // Now I am going to get the item specific tags
        Element syncChild = element.getChild(Sync.NAME, SSEModule.SSE_NS);
        Sync sync = null;

        if (syncChild != null) {
            sync = new Sync();
            sync.setId(parseStringAttribute(syncChild, Sync.ID_ATTRIBUTE));
            sync.setVersion(parseIntegerAttribute(syncChild, Sync.VERSION_ATTRIBUTE));
            sync.setDeleted(parseBooleanAttr(syncChild, Sync.DELETED_ATTRIBUTE));
            sync.setConflict(parseBooleanAttr(syncChild, Sync.CONFLICT_ATTRIBUTE));
            sync.setHistory(parseHistory(syncChild));
            sync.setConflicts(parseConflicts(syncChild));
        }
        return sync;
    }

    private List parseConflicts(Element syncElement) {
        List conflicts = null;

        List conflictsContent = syncElement.getContent(new ContentFilter(Conflicts.NAME));
        for (Iterator conflictsIter = conflictsContent.iterator();
             conflictsIter.hasNext();)
        {
            Element conflictsElement = (Element) conflictsIter.next();

            List conflictContent =
                    conflictsElement.getContent(new ContentFilter(Conflict.NAME));
            for (Iterator conflictIter = conflictContent.iterator();
                 conflictIter.hasNext();)
            {
                Element conflictElement = (Element) conflictIter.next();

                Conflict conflict = new Conflict();
                conflict.setBy(parseStringAttribute(conflictElement, Conflict.BY_ATTRIBUTE));
                conflict.setWhen(parseDateAttribute(conflictElement, Conflict.WHEN_ATTRIBUTE));
                conflict.setVersion(parseIntegerAttribute(conflictElement, Conflict.VERSION_ATTRIBUTE));

                List conflictItemContent =
                        conflictElement.getContent(new ContentFilter("item"));
                for (Iterator conflictItemIter = conflictItemContent.iterator();
                     conflictItemIter.hasNext();)
                {
                    Element conflictItemElement = (Element) conflictItemIter.next();
                    Element root = getRoot(conflictItemElement);
                    Item conflictItem = rssParser.parseItem(root, conflictItemElement);
                    conflict.setItem(conflictItem);

                    if (conflicts == null) {
                        conflicts = new ArrayList();
                    }
                    conflicts.add(conflict);
                }
            }
        }

        return conflicts;
    }

    private Element getRoot(Element start) {
        // reach up to grab the sharing element out of the root
        Element root = start;

        while ((root.getParent() != null) && root.getParent() instanceof Element) {
            root = (Element) root.getParent();
        }
        return root;
    }

    private History parseHistory(Element historyElement) {
        Element historyContent = getFirstContent(historyElement, History.NAME);

        History history = null;
        if (historyContent != null) {
            history = new History();
            history.setBy(parseStringAttribute(historyContent, History.BY_ATTRIBUTE));
            history.setWhen(parseDateAttribute(historyContent, History.WHEN_ATTRIBUTE));
            parseUpdates(historyContent, history);
        }
        return history;
    }

    private Element getFirstContent(Element element, String name) {
        List filterList = element.getContent(new ContentFilter(name));
        Element firstContent = null;
        if ((filterList != null) && (filterList.size() > 0)) {
            firstContent = (Element) filterList.get(0);
        }
        return firstContent;
    }

    private void parseUpdates(Element historyChild, History history) {
        List updatedChildren = historyChild.getContent(new ContentFilter(Update.NAME));
        for (Iterator childIter = updatedChildren.iterator(); childIter.hasNext();) {
            Element updateChild = (Element) childIter.next();
            Update update = new Update();
            update.setBy(parseStringAttribute(updateChild, Update.BY_ATTRIBUTE));
            update.setWhen(parseDateAttribute(updateChild, Update.WHEN_ATTRIBUTE));
            history.addUpdate(update);
        }
    }

    private String parseStringAttribute(Element syncChild, String attrName) {
        Attribute idAttribute = syncChild.getAttribute(attrName);
        return (idAttribute != null ? idAttribute.getValue().trim() : null);
    }

    private Integer parseIntegerAttribute(Element sharingChild, String attrName) {
        Attribute integerAttribute = sharingChild.getAttribute(attrName);
        Integer integerAttr = null;
        if (integerAttribute != null) {
            try {
                integerAttr = new Integer(integerAttribute.getIntValue());
            } catch (DataConversionException e) {
                // dont use the data
            }
        }
        return integerAttr;
    }

    private Boolean parseBooleanAttr(Element sharingChild, String attrName) {
        Attribute attribute = sharingChild.getAttribute(attrName);
        Boolean attrValue = null;
        if (attribute != null) {
            try {
                attrValue = Boolean.valueOf(attribute.getBooleanValue());
            } catch (DataConversionException e) {
                // dont use the data
            }
        }
        return attrValue;
    }

    private Date parseDateAttribute(Element childElement, String attrName) {
        Attribute dateAttribute = childElement.getAttribute(attrName);
        Date date = null;
        if (dateAttribute != null) {
            // SSE spec requires the timezone to be 'GMT'
            // admittedly, this is a bit heavy-handed
            String dateAttr = dateAttribute.getValue().trim();
            return DateParser.parseRFC822(dateAttr);
        }
        return date;
    }

    private static class ContentFilter implements Filter {
        private String name;
        private ContentFilter(String name) {
            this.name = name;
        }

        public boolean matches(Object object) {
            return (object instanceof Element) &&
                    name.equals(((Element)object).getName());
        }
    }
}
