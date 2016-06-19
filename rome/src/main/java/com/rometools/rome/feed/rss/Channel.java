/*
 * Copyright 2004 Sun Microsystems, Inc.
 * Copyright 2011 The ROME Team
 *
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
package com.rometools.rome.feed.rss;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.impl.ModuleUtils;
import com.rometools.utils.Dates;
import com.rometools.utils.Lists;

/**
 * Bean for RSS feeds.
 * <p>
 * It handles all RSS versions (0.9, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) without losing
 * information.
 */
public class Channel extends WireFeed {

    private static final long serialVersionUID = 1L;

    public static final String SUNDAY = "sunday";
    public static final String MONDAY = "monday";
    public static final String TUESDAY = "tuesday";
    public static final String WEDNESDAY = "wednesday";
    public static final String THURSDAY = "thursday";
    public static final String FRIDAY = "friday";
    public static final String SATURDAY = "saturday";

    private static final Set<String> DAYS;

    static {
        final HashSet<String> days = new HashSet<String>();
        days.add(SUNDAY);
        days.add(MONDAY);
        days.add(TUESDAY);
        days.add(WEDNESDAY);
        days.add(THURSDAY);
        days.add(FRIDAY);
        days.add(SATURDAY);
        DAYS = Collections.unmodifiableSet(days);
    }

    private String title;
    private String description;
    private String link;
    private String uri;
    private Image image;
    private List<Item> items;
    private TextInput textInput;
    private String language;
    private String rating;
    private String copyright;
    private Date pubDate;
    private Date lastBuildDate;
    private String docs;
    private String managingEditor;
    private String webMaster;
    private List<Integer> skipHours;
    private List<String> skipDays;
    private Cloud cloud;
    private List<Category> categories;
    private String generator;
    private int ttl = -1;
    private List<Module> modules;

    /**
     * Default constructor, for bean cloning purposes only.
     *
     */
    public Channel() {
    }

    /**
     * Channel Constructor. All properties, except the type, are set to <b>null</b>.
     * <p>
     *
     * @param type the type of the RSS feed.
     *
     */
    public Channel(final String type) {
        super(type);
    }

    /**
     * Returns the channel title.
     * <p>
     *
     * @return the channel title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the channel title.
     * <p>
     *
     * @param title the channel title to set, <b>null</b> if none.
     *
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Returns the channel description.
     * <p>
     *
     * @return the channel description, <b>null</b> if none.
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the channel description.
     * <p>
     *
     * @param description the channel description to set, <b>null</b> if none.
     *
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns the channel link.
     * <p>
     *
     * @return the channel link, <b>null</b> if none.
     *
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the channel link.
     * <p>
     *
     * @param link the channel link to set, <b>null</b> if none.
     *
     */
    public void setLink(final String link) {
        this.link = link;
    }

    /**
     * Returns the channel uri.
     * <p>
     *
     * @return the channel uri, <b>null</b> if none.
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the channel uri.
     * <p>
     *
     * @param uri the channel uri, <b>null</b> if none.
     */
    public void setUri(final String uri) {
        this.uri = uri;
    }

    /**
     * Returns the channel image.
     * <p>
     *
     * @return the channel image, <b>null</b> if none.
     *
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the channel image.
     * <p>
     *
     * @param image the channel image to set, <b>null</b> if none.
     *
     */
    public void setImage(final Image image) {
        this.image = image;
    }

    /**
     * Returns the channel items.
     * <p>
     *
     * @return a list of Item elements with the channel items, an empty list if none.
     *
     */
    public List<Item> getItems() {
        return items = Lists.createWhenNull(items);
    }

    /**
     * Sets the channel items.
     * <p>
     *
     * @param items the list of Item elements with the channel items to set, an empty list or
     *            <b>null</b> if none.
     *
     */
    public void setItems(final List<Item> items) {
        this.items = items;
    }

    /**
     * Returns the channel text input.
     * <p>
     *
     * @return the channel text input, <b>null</b> if none.
     *
     */
    public TextInput getTextInput() {
        return textInput;
    }

    /**
     * Sets the channel text input.
     * <p>
     *
     * @param textInput the channel text input to set, <b>null</b> if none.
     *
     */
    public void setTextInput(final TextInput textInput) {
        this.textInput = textInput;
    }

    /**
     * Returns the channel language.
     * <p>
     *
     * @return the channel language, <b>null</b> if none.
     *
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the channel language.
     * <p>
     *
     * @param language the channel language to set, <b>null</b> if none.
     *
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

    /**
     * Returns the channel rating.
     * <p>
     *
     * @return the channel rating, <b>null</b> if none.
     *
     */
    public String getRating() {
        return rating;
    }

    /**
     * Sets the channel rating.
     * <p>
     *
     * @param rating the channel rating to set, <b>null</b> if none.
     *
     */
    public void setRating(final String rating) {
        this.rating = rating;
    }

    /**
     * Returns the channel copyright.
     * <p>
     *
     * @return the channel copyright, <b>null</b> if none.
     *
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Sets the channel copyright.
     * <p>
     *
     * @param copyright the channel copyright to set, <b>null</b> if none.
     *
     */
    public void setCopyright(final String copyright) {
        this.copyright = copyright;
    }

    /**
     * Returns the channel publishing date.
     * <p>
     *
     * @return the channel publishing date, <b>null</b> if none.
     *
     */
    public Date getPubDate() {
        return Dates.copy(pubDate);
    }

    /**
     * Sets the channel publishing date.
     * <p>
     *
     * @param pubDate the channel publishing date to set, <b>null</b> if none.
     *
     */
    public void setPubDate(final Date pubDate) {
        this.pubDate = Dates.copy(pubDate);
    }

    /**
     * Returns the channel last build date.
     * <p>
     *
     * @return the channel last build date, <b>null</b> if none.
     *
     */
    public Date getLastBuildDate() {
        return Dates.copy(lastBuildDate);
    }

    /**
     * Sets the channel last build date.
     * <p>
     *
     * @param lastBuildDate the channel last build date to set, <b>null</b> if none.
     *
     */
    public void setLastBuildDate(final Date lastBuildDate) {
        this.lastBuildDate = Dates.copy(lastBuildDate);
    }

    /**
     * Returns the channel docs.
     * <p>
     *
     * @return the channel docs, <b>null</b> if none.
     *
     */
    public String getDocs() {
        return docs;
    }

    /**
     * Sets the channel docs.
     * <p>
     *
     * @param docs the channel docs to set, <b>null</b> if none.
     *
     */
    public void setDocs(final String docs) {
        this.docs = docs;
    }

    /**
     * Returns the channel managing editor.
     * <p>
     *
     * @return the channel managing editor, <b>null</b> if none.
     *
     */
    public String getManagingEditor() {
        return managingEditor;
    }

    /**
     * Sets the channel managing editor.
     * <p>
     *
     * @param managingEditor the channel managing editor to set, <b>null</b> if none.
     *
     */
    public void setManagingEditor(final String managingEditor) {
        this.managingEditor = managingEditor;
    }

    /**
     * Returns the channel web master.
     * <p>
     *
     * @return the channel web master, <b>null</b> if none.
     *
     */
    public String getWebMaster() {
        return webMaster;
    }

    /**
     * Sets the channel web master.
     * <p>
     *
     * @param webMaster the channel web master to set, <b>null</b> if none.
     *
     */
    public void setWebMaster(final String webMaster) {
        this.webMaster = webMaster;
    }

    /**
     * Returns the channel skip hours.
     * <p>
     *
     * @return a list of Integer elements with the channel skip hours, an empty list if none.
     *
     */
    public List<Integer> getSkipHours() {
        return Lists.createWhenNull(skipHours);
    }

    /**
     * Sets the channel skip hours.
     * <p>
     *
     * @param skipHours the list of Integer elements with the channel skip hours to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    public void setSkipHours(final List<Integer> skipHours) {
        if (skipHours != null) {
            for (int i = 0; i < skipHours.size(); i++) {
                final Integer iHour = skipHours.get(i);
                if (iHour != null) {
                    final int hour = iHour.intValue();
                    if (hour < 0 || hour > 24) {
                        throw new IllegalArgumentException("Invalid hour [" + hour + "]");
                    }
                } else {
                    throw new IllegalArgumentException("Invalid hour [null]");
                }
            }
        }
        this.skipHours = skipHours;
    }

    /**
     * Returns the channel skip days.
     * <p>
     *
     * @return a list of Day elements with the channel skip days, an empty list if none.
     *
     */
    public List<String> getSkipDays() {
        return Lists.createWhenNull(skipDays);
    }

    /**
     * Sets the channel skip days.
     * <p>
     *
     * @param skipDays the list of Day elements with the channel skip days to set, an empty list or
     *            <b>null</b> if none.
     *
     */
    public void setSkipDays(final List<String> skipDays) {
        if (skipDays != null) {
            for (int i = 0; i < skipDays.size(); i++) {
                String day = skipDays.get(i);
                if (day != null) {
                    day = day.toLowerCase();
                    if (!DAYS.contains(day)) {
                        throw new IllegalArgumentException("Invalid day [" + day + "]");
                    }
                    skipDays.set(i, day);
                } else {
                    throw new IllegalArgumentException("Invalid day [null]");
                }
            }
        }
        this.skipDays = skipDays;
    }

    /**
     * Returns the channel cloud.
     * <p>
     *
     * @return the channel cloud, <b>null</b> if none.
     *
     */
    public Cloud getCloud() {
        return cloud;
    }

    /**
     * Sets the channel cloud.
     * <p>
     *
     * @param cloud the channel cloud to set, <b>null</b> if none.
     *
     */
    public void setCloud(final Cloud cloud) {
        this.cloud = cloud;
    }

    /**
     * Returns the channel categories.
     * <p>
     *
     * @return a list of Category elements with the channel categories, an empty list if none.
     *
     */
    public List<Category> getCategories() {
        return categories = Lists.createWhenNull(categories);
    }

    /**
     * Sets the channel categories.
     * <p>
     *
     * @param categories the list of Category elements with the channel categories to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    public void setCategories(final List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Returns the channel generator.
     * <p>
     *
     * @return the channel generator, <b>null</b> if none.
     *
     */
    public String getGenerator() {
        return generator;
    }

    /**
     * Sets the channel generator.
     * <p>
     *
     * @param generator the channel generator to set, <b>null</b> if none.
     *
     */
    public void setGenerator(final String generator) {
        this.generator = generator;
    }

    /**
     * Returns the channel time to live.
     * <p>
     *
     * @return the channel time to live, <b>null</b> if none.
     *
     */
    public int getTtl() {
        return ttl;
    }

    /**
     * Sets the channel time to live.
     * <p>
     *
     * @param ttl the channel time to live to set, <b>null</b> if none.
     *
     */
    public void setTtl(final int ttl) {
        this.ttl = ttl;
    }

    /**
     * Returns the channel modules.
     * <p>
     *
     * @return a list of ModuleImpl elements with the channel modules, an empty list if none.
     *
     */
    @Override
    public List<Module> getModules() {
        return modules = Lists.createWhenNull(modules);
    }

    /**
     * Sets the channel modules.
     * <p>
     *
     * @param modules the list of ModuleImpl elements with the channel modules to set, an empty list
     *            or <b>null</b> if none.
     *
     */
    @Override
    public void setModules(final List<Module> modules) {
        this.modules = modules;
    }

    /**
     * Returns the module identified by a given URI.
     * <p>
     *
     * @param uri the URI of the ModuleImpl.
     * @return The module with the given URI, <b>null</b> if none.
     */
    @Override
    public Module getModule(final String uri) {
        return ModuleUtils.getModule(modules, uri);
    }

}
