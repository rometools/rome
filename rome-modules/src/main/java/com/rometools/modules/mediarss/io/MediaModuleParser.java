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

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.mediarss.MediaEntryModuleImpl;
import com.rometools.modules.mediarss.MediaModule;
import com.rometools.modules.mediarss.MediaModuleImpl;
import com.rometools.modules.mediarss.types.Category;
import com.rometools.modules.mediarss.types.Credit;
import com.rometools.modules.mediarss.types.Expression;
import com.rometools.modules.mediarss.types.Hash;
import com.rometools.modules.mediarss.types.MediaContent;
import com.rometools.modules.mediarss.types.MediaGroup;
import com.rometools.modules.mediarss.types.Metadata;
import com.rometools.modules.mediarss.types.PlayerReference;
import com.rometools.modules.mediarss.types.Rating;
import com.rometools.modules.mediarss.types.Restriction;
import com.rometools.modules.mediarss.types.Text;
import com.rometools.modules.mediarss.types.Thumbnail;
import com.rometools.modules.mediarss.types.Time;
import com.rometools.modules.mediarss.types.UrlReference;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import com.rometools.utils.Integers;
import com.rometools.utils.Longs;
import com.rometools.utils.Strings;

public class MediaModuleParser implements ModuleParser {

    private static final Logger LOG = LoggerFactory.getLogger(MediaModuleParser.class);

    private static final Namespace NS = Namespace.getNamespace(MediaModule.URI);

    private static final Pattern FILESIZE_WITH_UNIT_PATTERN = Pattern.compile("([\\d,.]+)([TGMK])?B", Pattern.CASE_INSENSITIVE);

    @Override
    public String getNamespaceUri() {
        return MediaModule.URI;
    }

    @Override
    public Module parse(final Element mmRoot, final Locale locale) {
        MediaModuleImpl mod = null;

        if (mmRoot.getName().equals("channel") || mmRoot.getName().equals("feed")) {
            mod = new MediaModuleImpl();
        } else {
            mod = new MediaEntryModuleImpl();
        }

        mod.setMetadata(parseMetadata(mmRoot));
        mod.setPlayer(parsePlayer(mmRoot));

        if (mod instanceof MediaEntryModuleImpl) {
            final MediaEntryModuleImpl m = (MediaEntryModuleImpl) mod;
            m.setMediaContents(parseContent(mmRoot));
            m.setMediaGroups(parseGroup(mmRoot));
        }

        return mod;
    }

    static long parseFileSize(String fileSizeAttrValue) {
        String nonWSFileSize = fileSizeAttrValue.replaceAll("\\s", "");

        if(nonWSFileSize.matches("\\d+")) {
            return Long.valueOf(nonWSFileSize);
        }

        Matcher sizeWithUnitMatcher = FILESIZE_WITH_UNIT_PATTERN.matcher(nonWSFileSize);
        if (sizeWithUnitMatcher.matches()) {
            BigDecimal number = new BigDecimal(sizeWithUnitMatcher.group(1).replace(',', '.'));
            BigDecimal multiplier = BigDecimal.valueOf(1);
            if (sizeWithUnitMatcher.group(2) != null) {
                char unit  = sizeWithUnitMatcher.group(2).toLowerCase().charAt(0);
                if (unit == 'k') {
                    multiplier = BigDecimal.valueOf(1000);
                } else if (unit == 'm') {
                    multiplier = BigDecimal.valueOf(1000).pow(2);
                } else if (unit == 'g') {
                    multiplier = BigDecimal.valueOf(1000).pow(3);
                } else if (unit == 't') {
                    multiplier = BigDecimal.valueOf(1000).pow(4);
                }
            }
            return number.multiply(multiplier).longValue();
        }

        throw new NumberFormatException("Invalid file size: " + fileSizeAttrValue);
    }

    private MediaContent[] parseContent(final Element e) {

        final List<Element> contents = e.getChildren("content", getNS());
        final ArrayList<MediaContent> values = new ArrayList<MediaContent>();

        try {
            for (int i = 0; contents != null && i < contents.size(); i++) {
                final Element content = contents.get(i);
                MediaContent mc = null;

                if (content.getAttributeValue("url") != null) {
                    try {
                        mc = new MediaContent(new UrlReference(new URI(content.getAttributeValue("url").replace(' ', '+'))));
                        mc.setPlayer(parsePlayer(content));
                    } catch (final Exception ex) {
                        LOG.warn("Exception parsing content tag.", ex);
                    }
                } else {
                    mc = new MediaContent(parsePlayer(content));
                }
                if (mc != null) {
                    values.add(mc);
                    try {
                        if (content.getAttributeValue("channels") != null) {
                            mc.setAudioChannels(Integer.valueOf(content.getAttributeValue("channels")));
                        }
                    } catch (final Exception ex) {
                        LOG.warn("Exception parsing content tag.", ex);
                    }
                    try {
                        if (content.getAttributeValue("bitrate") != null) {
                            mc.setBitrate(Float.valueOf(content.getAttributeValue("bitrate")));
                        }
                    } catch (final Exception ex) {
                        LOG.warn("Exception parsing content tag.", ex);
                    }
                    try {
                        if (content.getAttributeValue("duration") != null) {
                            mc.setDuration(Longs.parseDecimal(content.getAttributeValue("duration")));
                        }
                    } catch (final Exception ex) {
                        LOG.warn("Exception parsing content tag.", ex);
                    }

                    mc.setMedium(content.getAttributeValue("medium"));

                    final String expression = content.getAttributeValue("expression");

                    if (expression != null) {
                        if (expression.equalsIgnoreCase("full")) {
                            mc.setExpression(Expression.FULL);
                        } else if (expression.equalsIgnoreCase("sample")) {
                            mc.setExpression(Expression.SAMPLE);
                        } else if (expression.equalsIgnoreCase("nonstop")) {
                            mc.setExpression(Expression.NONSTOP);
                        }
                    }

                    try {
                        if (content.getAttributeValue("fileSize") != null) {
                            mc.setFileSize(parseFileSize(content.getAttributeValue("fileSize")));
                        }
                    } catch (final Exception ex) {
                        LOG.warn("Exception parsing content tag.", ex);
                    }
                    try {
                        if (content.getAttributeValue("framerate") != null) {
                            mc.setFramerate(Float.valueOf(content.getAttributeValue("framerate")));
                        }
                    } catch (final Exception ex) {
                        LOG.warn("Exception parsing content tag.", ex);
                    }
                    try {
                        if (content.getAttributeValue("height") != null) {
                            mc.setHeight(Integer.valueOf(content.getAttributeValue("height")));
                        }
                    } catch (final Exception ex) {
                        LOG.warn("Exception parsing content tag.", ex);
                    }

                    mc.setLanguage(content.getAttributeValue("lang"));
                    mc.setMetadata(parseMetadata(content));
                    try {
                        if (content.getAttributeValue("samplingrate") != null) {
                            mc.setSamplingrate(Float.valueOf(content.getAttributeValue("samplingrate")));
                        }
                    } catch (final Exception ex) {
                        LOG.warn("Exception parsing content tag.", ex);
                    }

                    mc.setType(content.getAttributeValue("type"));
                    try {
                        if (content.getAttributeValue("width") != null) {
                            mc.setWidth(Integer.valueOf(content.getAttributeValue("width")));
                        }
                    } catch (final Exception ex) {
                        LOG.warn("Exception parsing content tag.", ex);
                    }

                    if (content.getAttributeValue("isDefault") != null) {
                        mc.setDefaultContent(Boolean.valueOf(content.getAttributeValue("isDefault")));
                    }
                } else {
                    LOG.warn("Could not find MediaContent.");
                }

            }
        } catch (final Exception ex) {
            LOG.warn("Exception parsing content tag.", ex);
        }

        return values.toArray(new MediaContent[values.size()]);
    }

    private MediaGroup[] parseGroup(final Element e) {
        final List<Element> groups = e.getChildren("group", getNS());
        final ArrayList<MediaGroup> values = new ArrayList<MediaGroup>();

        for (int i = 0; groups != null && i < groups.size(); i++) {
            final Element group = groups.get(i);
            final MediaGroup g = new MediaGroup(parseContent(group));

            for (int j = 0; j < g.getContents().length; j++) {
                if (g.getContents()[j].isDefaultContent()) {
                    g.setDefaultContentIndex(new Integer(j));

                    break;
                }
            }

            g.setMetadata(parseMetadata(group));
            values.add(g);
        }

        return values.toArray(new MediaGroup[values.size()]);
    }

    private Metadata parseMetadata(final Element e) {
        final Metadata md = new Metadata();
        // categories
        {
            final List<Element> categories = e.getChildren("category", getNS());
            final ArrayList<Category> values = new ArrayList<Category>();

            for (int i = 0; categories != null && i < categories.size(); i++) {
                try {
                    final Element cat = categories.get(i);
                    values.add(new Category(cat.getAttributeValue("scheme"), cat.getAttributeValue("label"), cat.getText()));
                } catch (final Exception ex) {
                    LOG.warn("Exception parsing category tag.", ex);
                }
            }

            md.setCategories(values.toArray(new Category[values.size()]));
        }

        // copyright
        try {
            final Element copy = e.getChild("copyright", getNS());

            if (copy != null) {
                md.setCopyright(copy.getText());
                md.setCopyrightUrl(copy.getAttributeValue("url") != null ? new URI(copy.getAttributeValue("url")) : null);
            }
        } catch (final Exception ex) {
            LOG.warn("Exception parsing copyright tag.", ex);
        }
        // credits
        {
            final List<Element> credits = e.getChildren("credit", getNS());
            final ArrayList<Credit> values = new ArrayList<Credit>();

            for (int i = 0; credits != null && i < credits.size(); i++) {
                try {
                    final Element cred = credits.get(i);
                    values.add(new Credit(cred.getAttributeValue("scheme"), cred.getAttributeValue("role"), cred.getText()));
                    md.setCredits(values.toArray(new Credit[values.size()]));
                } catch (final Exception ex) {
                    LOG.warn("Exception parsing credit tag.", ex);
                }
            }
        }

        // description
        try {
            final Element description = e.getChild("description", getNS());

            if (description != null) {
                md.setDescription(description.getText());
                md.setDescriptionType(description.getAttributeValue("type"));
            }
        } catch (final Exception ex) {
            LOG.warn("Exception parsing description tag.", ex);
        }

        // hash
        try {
            final Element hash = e.getChild("hash", getNS());

            if (hash != null) {
                md.setHash(new Hash(hash.getAttributeValue("algo"), hash.getText()));
            }
        } catch (final Exception ex) {
            LOG.warn("Exception parsing hash tag.", ex);
        }
        // keywords
        {
            final Element keywords = e.getChild("keywords", getNS());

            if (keywords != null) {
                final StringTokenizer tok = new StringTokenizer(keywords.getText(), ",");
                final String[] value = new String[tok.countTokens()];

                for (int i = 0; tok.hasMoreTokens(); i++) {
                    value[i] = tok.nextToken().trim();
                }

                md.setKeywords(value);
            }
        }
        // ratings
        {
            final ArrayList<Rating> values = new ArrayList<Rating>();

            final List<Element> ratings = e.getChildren("rating", getNS());
            for (final Element ratingElement : ratings) {
                try {
                    final String ratingText = ratingElement.getText();
                    String ratingScheme = Strings.trimToNull(ratingElement.getAttributeValue("scheme"));
                    if (ratingScheme == null) {
                        ratingScheme = "urn:simple";
                    }
                    final Rating rating = new Rating(ratingScheme, ratingText);
                    values.add(rating);
                } catch (final Exception ex) {
                    LOG.warn("Exception parsing rating tag.", ex);
                }
            }

            md.setRatings(values.toArray(new Rating[values.size()]));

        }
        // text
        {
            final List<Element> texts = e.getChildren("text", getNS());
            final ArrayList<Text> values = new ArrayList<Text>();

            for (int i = 0; texts != null && i < texts.size(); i++) {
                try {
                    final Element text = texts.get(i);
                    final Time start = text.getAttributeValue("start") == null ? null : new Time(text.getAttributeValue("start"));
                    final Time end = text.getAttributeValue("end") == null ? null : new Time(text.getAttributeValue("end"));
                    values.add(new Text(text.getAttributeValue("type"), text.getTextTrim(), start, end));
                } catch (final Exception ex) {
                    LOG.warn("Exception parsing text tag.", ex);
                }
            }

            md.setText(values.toArray(new Text[values.size()]));
        }
        // thumbnails
        {
            final ArrayList<Thumbnail> values = new ArrayList<Thumbnail>();

            final List<Element> thumbnails = e.getChildren("thumbnail", getNS());
            for (final Element thumb : thumbnails) {
                try {

                    final String timeAttr = Strings.trimToNull(thumb.getAttributeValue("time"));
                    Time time = null;
                    if (timeAttr != null) {
                        time = new Time(timeAttr);
                    }

                    final String widthAttr = thumb.getAttributeValue("width");
                    final Integer width = Integers.parse(widthAttr);

                    final String heightAttr = thumb.getAttributeValue("height");
                    final Integer height = Integers.parse(heightAttr);

                    final String url = thumb.getAttributeValue("url");
                    final URI uri = new URI(url);
                    final Thumbnail thumbnail = new Thumbnail(uri, width, height, time);

                    values.add(thumbnail);

                } catch (final Exception ex) {
                    LOG.warn("Exception parsing thumbnail tag.", ex);
                }
            }

            md.setThumbnail(values.toArray(new Thumbnail[values.size()]));
        }
        // title
        {
            final Element title = e.getChild("title", getNS());

            if (title != null) {
                md.setTitle(title.getText());
                md.setTitleType(title.getAttributeValue("type"));
            }
        }
        // restrictions
        {
            final List<Element> restrictions = e.getChildren("restriction", getNS());
            final ArrayList<Restriction> values = new ArrayList<Restriction>();

            for (int i = 0; i < restrictions.size(); i++) {
                final Element r = restrictions.get(i);
                Restriction.Type type = null;

                if (r.getAttributeValue("type") != null) {
                    if (r.getAttributeValue("type").equalsIgnoreCase("uri")) {
                        type = Restriction.Type.URI;
                    } else if (r.getAttributeValue("type").equalsIgnoreCase("country")) {
                        type = Restriction.Type.COUNTRY;
                    }
                }

                Restriction.Relationship relationship = null;

                if (r.getAttributeValue("relationship").equalsIgnoreCase("allow")) {
                    relationship = Restriction.Relationship.ALLOW;
                } else if (r.getAttributeValue("relationship").equalsIgnoreCase("deny")) {
                    relationship = Restriction.Relationship.DENY;
                }

                final Restriction value = new Restriction(relationship, type, r.getTextTrim());
                values.add(value);
            }

            md.setRestrictions(values.toArray(new Restriction[values.size()]));
        }
        // handle adult
        {
            final Element adult = e.getChild("adult", getNS());

            if (adult != null && md.getRatings().length == 0) {
                final Rating[] r = new Rating[1];

                if (adult.getTextTrim().equals("true")) {
                    r[0] = new Rating("urn:simple", "adult");
                } else {
                    r[0] = new Rating("urn:simple", "nonadult");
                }

                md.setRatings(r);
            }
        }

        return md;
    }

    private PlayerReference parsePlayer(final Element e) {
        final Element player = e.getChild("player", getNS());
        PlayerReference p = null;

        if (player != null) {
            final Integer width = player.getAttributeValue("width") == null ? null : new Integer(player.getAttributeValue("width"));
            final Integer height = player.getAttributeValue("height") == null ? null : new Integer(player.getAttributeValue("height"));

            try {
                p = new PlayerReference(new URI(player.getAttributeValue("url")), width, height);
            } catch (final Exception ex) {
                LOG.warn("Exception parsing player tag.", ex);
            }
        }

        return p;
    }

    public Namespace getNS() {
        return NS;
    }

}
