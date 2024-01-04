/*
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

package com.rometools.modules.sse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.sse.modules.Conflict;
import com.rometools.modules.sse.modules.Conflicts;
import com.rometools.modules.sse.modules.History;
import com.rometools.modules.sse.modules.Related;
import com.rometools.modules.sse.modules.SSEModule;
import com.rometools.modules.sse.modules.Sharing;
import com.rometools.modules.sse.modules.Sync;
import com.rometools.modules.sse.modules.Update;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.io.DelegatingModuleGenerator;
import com.rometools.rome.io.WireFeedGenerator;
import com.rometools.rome.io.impl.DateParser;
import com.rometools.rome.io.impl.RSS20Generator;

/**
 * Generator for the SSE Module.
 */
public class SSE091Generator implements DelegatingModuleGenerator {
    private RSS20Generator parentGenerator;

    public SSE091Generator() {
    }

    @Override
    public void setFeedGenerator(final WireFeedGenerator feedGenerator) {
        parentGenerator = (RSS20Generator) feedGenerator;
    }

    @Override
    public String getNamespaceUri() {
        return SSEModule.SSE_SCHEMA_URI;
    }

    /**
     * Returns a set with all the URIs (JDOM Namespace elements) this module generator uses.
     * <p/>
     * It is used by the the feed generators to add their namespace definition in the root element
     * of the generated document (forward-missing of Java 5.0 Generics).
     * <p/>
     *
     * @return a set with all the URIs (JDOM Namespace elements) this module generator uses.
     */
    @Override
    public Set<Namespace> getNamespaces() {
        return SSEModule.NAMESPACES;
    }

    @Override
    public void generate(final Module module, final Element element) {
        if (!(module instanceof SSEModule)) {
            return;
        }

        final SSEModule sseModule = (SSEModule) module;

        if (sseModule instanceof Sharing) {
            final Sharing sharing = (Sharing) sseModule;
            // add sse namespace
            Element root = element;
            while (root.getParent() != null && root.getParent() instanceof Element) {
                root = (Element) root.getParent();
            }
            root.addNamespaceDeclaration(SSEModule.SSE_NS);

            generateSharing(sharing, root);
        } else if (sseModule instanceof Sync) {
            generateSync((Sync) sseModule, element);
        }
    }

    private void generateSharing(final Sharing sharing, final Element parent) {
        // inject sse sharingModule element
        final Element sharingElement = new Element(Sharing.NAME, SSEModule.SSE_NS);
        generateAttribute(sharingElement, Sharing.UNTIL_ATTRIBUTE, sharing.getUntil());
        generateAttribute(sharingElement, Sharing.SINCE_ATTRIBUTE, sharing.getSince());
        generateAttribute(sharingElement, Sharing.ORDERED_ATTRIBUTE, sharing.getOrdered());
        generateAttribute(sharingElement, Sharing.WINDOW_ATTRIBUTE, sharing.getWindow());
        generateAttribute(sharingElement, Sharing.VERSION_ATTRIBUTE, sharing.getVersion());

        // add sharing as the first element of the rss root
        parent.addContent(0, sharingElement);

        final Related related = sharing.getRelated();
        if (related != null) {
            generateRelated(related);
        }
    }

    private void generateRelated(final Related related) {
        final Element relatedElement = new Element(Related.NAME, SSEModule.SSE_NS);
        generateAttribute(relatedElement, Related.SINCE_ATTRIBUTE, related.getSince());
        generateAttribute(relatedElement, Related.UNTIL_ATTRIBUTE, related.getUntil());
        generateAttribute(relatedElement, Related.LINK_ATTRIBUTE, related.getLink());
        generateAttribute(relatedElement, Related.TITLE_ATTRIBUTE, related.getTitle());
        generateAttribute(relatedElement, Related.TYPE_ATTRIBUTE, related.getType());
    }

    protected void generateSync(final Sync sync, final Element parent) {
        final Element syncElement = new Element(Sync.NAME, SSEModule.SSE_NS);
        generateAttribute(syncElement, Sync.DELETED_ATTRIBUTE, sync.isDeleted());
        generateAttribute(syncElement, Sync.VERSION_ATTRIBUTE, sync.getVersion());
        generateAttribute(syncElement, Sync.ID_ATTRIBUTE, sync.getId());
        generateAttribute(syncElement, Sync.CONFLICT_ATTRIBUTE, sync.isConflict());
        generateHistory(syncElement, sync.getHistory());
        generateConflicts(syncElement, sync.getConflicts());
        parent.addContent(syncElement);
    }

    private void generateConflicts(final Element syncElement, final List<Conflict> conflicts) {
        if (conflicts != null) {
            final Element conflictsElement = new Element(Conflicts.NAME, SSEModule.SSE_NS);
            for (final Conflict conflict2 : conflicts) {
                final Element conflictElement = new Element(Conflict.NAME, SSEModule.SSE_NS);
                final Conflict conflict = conflict2;
                generateAttribute(conflictElement, Conflict.BY_ATTRIBUTE, conflict.getBy());
                generateAttribute(conflictElement, Conflict.VERSION_ATTRIBUTE, conflict.getVersion());
                generateAttribute(conflictElement, Conflict.WHEN_ATTRIBUTE, conflict.getWhen());
                generateItem(conflictElement, conflict.getItem());
                conflictsElement.addContent(conflictElement);
            }
            syncElement.addContent(conflictsElement);
        }
    }

    private void generateItem(final Element conflictElement, final Item item) {
        if (item != null) {
            final Element itemElement = new Element("item");
            parentGenerator.populateItem(item, itemElement, 0);
            parentGenerator.generateItemModules(item.getModules(), itemElement);
            conflictElement.addContent(itemElement);
        }
    }

    private void generateHistory(final Element syncElement, final History history) {
        if (history != null) {
            final Element historyElement = new Element(History.NAME, SSEModule.SSE_NS);
            generateAttribute(historyElement, History.BY_ATTRIBUTE, history.getBy());
            generateAttribute(historyElement, History.WHEN_ATTRIBUTE, history.getWhen());
            generateUpdates(historyElement, history.getUpdates());
            syncElement.addContent(historyElement);
        }
    }

    private void generateUpdates(final Element historyElement, final List<Update> updates) {
        if (updates != null) {
            for (final Update update : updates) {
                final Element updateElement = new Element(Update.NAME, SSEModule.SSE_NS);
                generateAttribute(updateElement, Update.BY_ATTRIBUTE, update.getBy());
                generateAttribute(updateElement, Update.WHEN_ATTRIBUTE, update.getWhen());
                historyElement.addContent(updateElement);
            }
        }
    }

    private void generateAttribute(final Element syncElement, final String attrName, final Object attribute) {
        if (attribute != null) {
            syncElement.setAttribute(attrName, toString(attribute));
        }
    }

    private String toString(final Object o) {
        if (o != null) {
            if (o instanceof LocalDateTime) {
                return DateParser.formatRFC822((LocalDateTime) o, Locale.US);
            } else {
                return o.toString();
            }
        }
        return "";
    }
}
