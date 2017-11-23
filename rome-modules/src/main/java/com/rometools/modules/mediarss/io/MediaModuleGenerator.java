/*
 * Copyright 2006 Nathanial X. Freitas, openvision.tv
 *
 * This code is currently released under the Mozilla Public License.
 * http://www.mozilla.org/MPL/
 *
 * Alternately you may apply the terms of the Apache Software License
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.rometools.modules.mediarss.io;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.georss.GMLGenerator;
import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.MediaModule;
import com.rometools.modules.mediarss.types.Category;
import com.rometools.modules.mediarss.types.Credit;
import com.rometools.modules.mediarss.types.Embed.Param;
import com.rometools.modules.mediarss.types.License;
import com.rometools.modules.mediarss.types.Location;
import com.rometools.modules.mediarss.types.MediaContent;
import com.rometools.modules.mediarss.types.MediaGroup;
import com.rometools.modules.mediarss.types.Metadata;
import com.rometools.modules.mediarss.types.PeerLink;
import com.rometools.modules.mediarss.types.PlayerReference;
import com.rometools.modules.mediarss.types.Price;
import com.rometools.modules.mediarss.types.Rating;
import com.rometools.modules.mediarss.types.Restriction;
import com.rometools.modules.mediarss.types.Scene;
import com.rometools.modules.mediarss.types.SubTitle;
import com.rometools.modules.mediarss.types.Tag;
import com.rometools.modules.mediarss.types.Text;
import com.rometools.modules.mediarss.types.Thumbnail;
import com.rometools.modules.mediarss.types.UrlReference;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

/**
 * Generator for MediaRSS module.
 *
 */
public class MediaModuleGenerator implements ModuleGenerator {

    private static final Namespace NS = Namespace.getNamespace("media", MediaModule.URI);
    private static final Set<Namespace> NAMESPACES = new HashSet<Namespace>();

    static {
        NAMESPACES.add(NS);
    }

    @Override
    public String getNamespaceUri() {
        return MediaModule.URI;
    }

    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(final Module module, final Element element) {
        if (module instanceof MediaModule) {
            final MediaModule m = (MediaModule) module;
            generateMetadata(m.getMetadata(), element);
            generatePlayer(m.getPlayer(), element);
        }

        if (module instanceof MediaEntryModule) {
            final MediaEntryModule m = (MediaEntryModule) module;
            for (final MediaGroup element2 : m.getMediaGroups()) {
                generateGroup(element2, element);
            }
            for (final MediaContent element2 : m.getMediaContents()) {
                generateContent(element2, element);
            }
        }
    }

    public void generateContent(final MediaContent c, final Element e) {
        final Element mc = new Element("content", NS);
        addNotNullAttribute(mc, "medium", c.getMedium());
        addNotNullAttribute(mc, "channels", c.getAudioChannels());
        addNotNullAttribute(mc, "bitrate", c.getBitrate());
        addNotNullAttribute(mc, "duration", c.getDuration());
        addNotNullAttribute(mc, "expression", c.getExpression());
        addNotNullAttribute(mc, "fileSize", c.getFileSize());
        addNotNullAttribute(mc, "framerate", c.getFramerate());
        addNotNullAttribute(mc, "height", c.getHeight());
        addNotNullAttribute(mc, "lang", c.getLanguage());
        addNotNullAttribute(mc, "samplingrate", c.getSamplingrate());
        addNotNullAttribute(mc, "type", c.getType());
        addNotNullAttribute(mc, "width", c.getWidth());

        if (c.isDefaultContent()) {
            addNotNullAttribute(mc, "isDefault", "true");
        }

        if (c.getReference() instanceof UrlReference) {
            addNotNullAttribute(mc, "url", c.getReference());
            generatePlayer(c.getPlayer(), mc);
        } else {
            generatePlayer(c.getPlayer(), mc);
        }

        generateMetadata(c.getMetadata(), mc);
        e.addContent(mc);
    }

    public void generateGroup(final MediaGroup g, final Element e) {
        final Element t = new Element("group", NS);
        final MediaContent[] c = g.getContents();

        for (final MediaContent element : c) {
            generateContent(element, t);
        }

        generateMetadata(g.getMetadata(), t);
        e.addContent(t);
    }

    public void generateMetadata(final Metadata m, final Element e) {
        if (m == null) {
            return;
        }

        final Category[] cats = m.getCategories();

        for (final Category cat : cats) {
            final Element c = generateSimpleElement("category", cat.getValue());
            addNotNullAttribute(c, "scheme", cat.getScheme());
            addNotNullAttribute(c, "label", cat.getLabel());
            e.addContent(c);
        }

        final Element copyright = addNotNullElement(e, "copyright", m.getCopyright());
        addNotNullAttribute(copyright, "url", m.getCopyrightUrl());

        final Credit[] creds = m.getCredits();

        for (final Credit cred : creds) {
            final Element c = generateSimpleElement("credit", cred.getName());
            addNotNullAttribute(c, "role", cred.getRole());
            addNotNullAttribute(c, "scheme", cred.getScheme());
            e.addContent(c);
        }

        final Element desc = addNotNullElement(e, "description", m.getDescription());
        addNotNullAttribute(desc, "type", m.getDescriptionType());

        if (m.getHash() != null) {
            final Element hash = addNotNullElement(e, "hash", m.getHash().getValue());
            addNotNullAttribute(hash, "algo", m.getHash().getAlgorithm());
        }

        final String[] keywords = m.getKeywords();

        if (keywords.length > 0) {
            String keyword = keywords[0];

            for (int i = 1; i < keywords.length; i++) {
                keyword += ", " + keywords[i];
            }

            addNotNullElement(e, "keywords", keyword);
        }

        final Rating[] rats = m.getRatings();

        for (final Rating rat2 : rats) {
            final Element rat = addNotNullElement(e, "rating", rat2.getValue());
            addNotNullAttribute(rat, "scheme", rat2.getScheme());

            if (rat2.equals(Rating.ADULT)) {
                addNotNullElement(e, "adult", "true");
            } else if (rat2.equals(Rating.NONADULT)) {
                addNotNullElement(e, "adult", "false");
            }
        }

        final Text[] text = m.getText();

        for (final Text element : text) {
            final Element t = addNotNullElement(e, "text", element.getValue());
            addNotNullAttribute(t, "type", element.getType());
            addNotNullAttribute(t, "start", element.getStart());
            addNotNullAttribute(t, "end", element.getEnd());
        }

        final Element title = addNotNullElement(e, "title", m.getTitle());
        addNotNullAttribute(title, "type", m.getTitleType());

        generateBackLinks(m, e);
        generateComments(m, e);
        generateCommunity(m, e);
        generateEmbed(m, e);
        generateLicenses(m, e);
        generateLocations(m, e);
        generatePeerLinks(m, e);
        generatePrices(m, e);
        generateResponses(m, e);
        final Restriction[] r = m.getRestrictions();
        for (final Restriction element : r) {
            final Element res = addNotNullElement(e, "restriction", element.getValue());
            addNotNullAttribute(res, "type", element.getType());
            addNotNullAttribute(res, "relationship", element.getRelationship());
        }
        if (m.getRights() != null) {
            final Element rights = new Element("rights", NS);
            rights.setAttribute("status", m.getRights().name());
            e.addContent(rights);
        }
        generateScenes(m, e);
        generateStatus(m, e);
        generateSubTitles(m, e);
        generateThumbails(m, e);
    }

    /**
     * Generation of thumbnail tags.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generateThumbails(final Metadata m, final Element e) {
        for (final Thumbnail thumb : m.getThumbnail()) {
            final Element t = new Element("thumbnail", NS);
            addNotNullAttribute(t, "url", thumb.getUrl());
            addNotNullAttribute(t, "width", thumb.getWidth());
            addNotNullAttribute(t, "height", thumb.getHeight());
            addNotNullAttribute(t, "time", thumb.getTime());
            e.addContent(t);
        }
    }

    /**
     * Generation of backLinks tag.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generateBackLinks(final Metadata m, final Element e) {
        final Element backLinksElements = new Element("backLinks", NS);
        for (final URL backLink : m.getBackLinks()) {
            addNotNullElement(backLinksElements, "backLink", backLink);
        }
        if (!backLinksElements.getChildren().isEmpty()) {
            e.addContent(backLinksElements);
        }
    }

    /**
     * Generation of comments tag.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generateComments(final Metadata m, final Element e) {
        final Element commentsElements = new Element("comments", NS);
        for (final String comment : m.getComments()) {
            addNotNullElement(commentsElements, "comment", comment);
        }
        if (!commentsElements.getChildren().isEmpty()) {
            e.addContent(commentsElements);
        }
    }

    /**
     * Generation of community tag.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generateCommunity(final Metadata m, final Element e) {
        if (m.getCommunity() == null) {
            return;
        }
        final Element communityElement = new Element("community", NS);
        if (m.getCommunity().getStarRating() != null) {
            final Element starRatingElement = new Element("starRating", NS);
            addNotNullAttribute(starRatingElement, "average", m.getCommunity().getStarRating().getAverage());
            addNotNullAttribute(starRatingElement, "count", m.getCommunity().getStarRating().getCount());
            addNotNullAttribute(starRatingElement, "min", m.getCommunity().getStarRating().getMin());
            addNotNullAttribute(starRatingElement, "max", m.getCommunity().getStarRating().getMax());
            if (starRatingElement.hasAttributes()) {
                communityElement.addContent(starRatingElement);
            }
        }
        if (m.getCommunity().getStatistics() != null) {
            final Element statisticsElement = new Element("statistics", NS);
            addNotNullAttribute(statisticsElement, "views", m.getCommunity().getStatistics().getViews());
            addNotNullAttribute(statisticsElement, "favorites", m.getCommunity().getStatistics().getFavorites());
            if (statisticsElement.hasAttributes()) {
                communityElement.addContent(statisticsElement);
            }
        }
        if (m.getCommunity().getTags() != null && !m.getCommunity().getTags().isEmpty()) {
            final Element tagsElement = new Element("tags", NS);
            for (final Tag tag : m.getCommunity().getTags()) {
                if (!tagsElement.getTextTrim().isEmpty()) {
                    tagsElement.addContent(", ");
                }
                if (tag.getWeight() == null) {
                    tagsElement.addContent(tag.getName());
                } else {
                    tagsElement.addContent(tag.getName());
                    tagsElement.addContent(":");
                    tagsElement.addContent(String.valueOf(tag.getWeight()));
                }
            }
            if (!tagsElement.getTextTrim().isEmpty()) {
                communityElement.addContent(tagsElement);
            }
        }
        if (!communityElement.getChildren().isEmpty()) {
            e.addContent(communityElement);
        }
    }

    /**
     * Generation of embed tag.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generateEmbed(final Metadata m, final Element e) {
        if (m.getEmbed() == null) {
            return;
        }
        final Element embedElement = new Element("embed", NS);
        addNotNullAttribute(embedElement, "url", m.getEmbed().getUrl());
        addNotNullAttribute(embedElement, "width", m.getEmbed().getWidth());
        addNotNullAttribute(embedElement, "height", m.getEmbed().getHeight());
        for (final Param param : m.getEmbed().getParams()) {
            final Element paramElement = addNotNullElement(embedElement, "param", param.getValue());
            if (paramElement != null) {
                addNotNullAttribute(paramElement, "name", param.getName());
            }
        }
        if (embedElement.hasAttributes() || !embedElement.getChildren().isEmpty()) {
            e.addContent(embedElement);
        }
    }

    /**
     * Generation of scenes tag.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generateScenes(final Metadata m, final Element e) {
        final Element scenesElement = new Element("scenes", NS);
        for (final Scene scene : m.getScenes()) {
            final Element sceneElement = new Element("scene", NS);
            addNotNullElement(sceneElement, "sceneTitle", scene.getTitle());
            addNotNullElement(sceneElement, "sceneDescription", scene.getDescription());
            addNotNullElement(sceneElement, "sceneStartTime", scene.getStartTime());
            addNotNullElement(sceneElement, "sceneEndTime", scene.getEndTime());
            if (!sceneElement.getChildren().isEmpty()) {
                scenesElement.addContent(sceneElement);
            }
        }
        if (!scenesElement.getChildren().isEmpty()) {
            e.addContent(scenesElement);
        }
    }

    /**
     * Generation of location tags.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generateLocations(final Metadata m, final Element e) {
        final GMLGenerator geoRssGenerator = new GMLGenerator();
        for (final Location location : m.getLocations()) {
            final Element locationElement = new Element("location", NS);
            addNotNullAttribute(locationElement, "description", location.getDescription());
            addNotNullAttribute(locationElement, "start", location.getStart());
            addNotNullAttribute(locationElement, "end", location.getEnd());
            if (location.getGeoRss() != null) {
                geoRssGenerator.generate(location.getGeoRss(), locationElement);
            }
            if (locationElement.hasAttributes() || !locationElement.getChildren().isEmpty()) {
                e.addContent(locationElement);
            }
        }
    }

    /**
     * Generation of peerLink tags.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generatePeerLinks(final Metadata m, final Element e) {
        for (final PeerLink peerLink : m.getPeerLinks()) {
            final Element peerLinkElement = new Element("peerLink", NS);
            addNotNullAttribute(peerLinkElement, "type", peerLink.getType());
            addNotNullAttribute(peerLinkElement, "href", peerLink.getHref());
            if (peerLinkElement.hasAttributes()) {
                e.addContent(peerLinkElement);
            }
        }
    }

    /**
     * Generation of subTitle tags.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generateSubTitles(final Metadata m, final Element e) {
        for (final SubTitle subTitle : m.getSubTitles()) {
            final Element subTitleElement = new Element("subTitle", NS);
            addNotNullAttribute(subTitleElement, "type", subTitle.getType());
            addNotNullAttribute(subTitleElement, "lang", subTitle.getLang());
            addNotNullAttribute(subTitleElement, "href", subTitle.getHref());
            if (subTitleElement.hasAttributes()) {
                e.addContent(subTitleElement);
            }
        }
    }

    /**
     * Generation of license tags.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generateLicenses(final Metadata m, final Element e) {
        for (final License license : m.getLicenses()) {
            final Element licenseElement = new Element("license", NS);
            addNotNullAttribute(licenseElement, "type", license.getType());
            addNotNullAttribute(licenseElement, "href", license.getHref());
            if (license.getValue() != null) {
                licenseElement.addContent(license.getValue());
            }
            if (licenseElement.hasAttributes() || !licenseElement.getTextTrim().isEmpty()) {
                e.addContent(licenseElement);
            }
        }
    }

    /**
     * Generation of backLinks tag.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generatePrices(final Metadata m, final Element e) {
        for (final Price price : m.getPrices()) {
            if (price == null) {
                continue;
            }
            final Element priceElement = new Element("price", NS);
            if (price.getType() != null) {
                priceElement.setAttribute("type", price.getType().name().toLowerCase());
            }
            addNotNullAttribute(priceElement, "info", price.getInfo());
            addNotNullAttribute(priceElement, "price", price.getPrice());
            addNotNullAttribute(priceElement, "currency", price.getCurrency());
            if (priceElement.hasAttributes()) {
                e.addContent(priceElement);
            }
        }
    }

    /**
     * Generation of responses tag.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generateResponses(final Metadata m, final Element e) {
        if (m.getResponses() == null || m.getResponses().length == 0) {
            return;
        }
        final Element responsesElements = new Element("responses", NS);
        for (final String response : m.getResponses()) {
            addNotNullElement(responsesElements, "response", response);
        }
        e.addContent(responsesElements);
    }

    /**
     * Generation of status tag.
     * 
     * @param m source
     * @param e element to attach new element to
     */
    private void generateStatus(final Metadata m, final Element e) {
        if (m.getStatus() == null) {
            return;
        }
        final Element statusElement = new Element("status", NS);
        if (m.getStatus().getState() != null) {
            statusElement.setAttribute("state", m.getStatus().getState().name());
        }
        addNotNullAttribute(statusElement, "reason", m.getStatus().getReason());
        if (statusElement.hasAttributes()) {
            e.addContent(statusElement);
        }
    }

    public void generatePlayer(final PlayerReference p, final Element e) {
        if (p == null) {
            return;
        }

        final Element t = new Element("player", NS);
        addNotNullAttribute(t, "url", p.getUrl());
        addNotNullAttribute(t, "width", p.getWidth());
        addNotNullAttribute(t, "height", p.getHeight());
        e.addContent(t);
    }

    protected void addNotNullAttribute(final Element target, final String name, final Object value) {
        if (target == null || value == null) {
            return;
        } else {
            target.setAttribute(name, value.toString());
        }
    }

    protected Element addNotNullElement(final Element target, final String name, final Object value) {
        if (value == null) {
            return null;
        } else {
            final Element e = generateSimpleElement(name, value.toString());
            target.addContent(e);

            return e;
        }
    }

    protected Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, NS);
        element.addContent(value);

        return element;
    }
}

