/*
 * Copyright 2004 Sun Microsystems, Inc.
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
package com.sun.syndication.feed.rss;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.impl.ModuleUtils;

import java.util.*;

/**
 * Bean for RSS feeds.
 * <p>
 * It handles all RSS versions (0.9, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0)
 * without losing information.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Channel extends WireFeed {
    public static final String SUNDAY    = "sunday";
    public static final String MONDAY    = "monday";
    public static final String TUESDAY   = "tuesday";
    public static final String WEDNESDAY = "wednesday";
    public static final String THURSDAY  = "thursday";
    public static final String FRIDAY    = "friday";
    public static final String SATURDAY  = "saturday";

    private static final Set DAYS = new HashSet();

    static {
        DAYS.add(SUNDAY   );
        DAYS.add(MONDAY   );
        DAYS.add(TUESDAY  );
        DAYS.add(WEDNESDAY);
        DAYS.add(THURSDAY );
        DAYS.add(FRIDAY   );
        DAYS.add(SATURDAY );
    }

    private String _title;
    private String _description;
    private String _link;
    private String _uri;
    private Image _image;
    private List _items;
    private TextInput _textInput;
    private String _language;
    private String _rating;
    private String _copyright;
    private Date _pubDate;
    private Date _lastBuildDate;
    private String _docs;
    private String _managingEditor;
    private String _webMaster;
    private List _skipHours;
    private List _skipDays;
    private Cloud _cloud;
    private List _categories;
    private String _generator;
    private int _ttl = -1;
    private List _modules;

    /**
     * Default constructor, for bean cloning purposes only.
     *
     */
    public Channel() {
    }

    /**
     * Channel Constructor. All properties, except the type, are set to <b>null</b>.
     * <p>
     * @param type the type of the RSS feed.
     *
     */
    public Channel(String type) {
        super(type);
    }

    /**
     * Returns the channel title.
     * <p>
     * @return the channel title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Sets the channel title.
     * <p>
     * @param title the channel title to set, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Returns the channel description.
     * <p>
     * @return the channel description, <b>null</b> if none.
     *
     */
    public String getDescription() {
        return _description;
    }

    /**
     * Sets the channel description.
     * <p>
     * @param description the channel description to set, <b>null</b> if none.
     *
     */
    public void setDescription(String description) {
        _description = description;
    }

    /**
     * Returns the channel link.
     * <p>
     * @return the channel link, <b>null</b> if none.
     *
     */
    public String getLink() {
        return _link;
    }

    /**
     * Sets the channel link.
     * <p>
     * @param link the channel link to set, <b>null</b> if none.
     *
     */
    public void setLink(String link) {
        _link = link;
    }

    /**
     * Returns the channel uri.
     * <p>
     * @return the channel uri, <b>null</b> if none.
     */
    public String getUri() {
        return _uri;
    }

    /**
     * Sets the channel uri.
     * <p>
     * @param uri the channel uri, <b>null</b> if none.
     */
    public void setUri(String uri) {
        _uri = uri;
    }

    /**
     * Returns the channel image.
     * <p>
     * @return the channel image, <b>null</b> if none.
     *
     */
    public Image getImage() {
        return _image;
    }

    /**
     * Sets the channel image.
     * <p>
     * @param image the channel image to set, <b>null</b> if none.
     *
     */
    public void setImage(Image image) {
        _image = image;
    }

    /**
     * Returns the channel items.
     * <p>
     * @return a list of Item elements with the channel items,
     *         an empty list if none.
     *
     */
    public List getItems() {
        return (_items==null) ? (_items=new ArrayList()) : _items;
    }

    /**
     * Sets the channel items.
     * <p>
     * @param items the list of Item elements with the channel items to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setItems(List items) {
        _items = items;
    }

    /**
     * Returns the channel text input.
     * <p>
     * @return the channel text input, <b>null</b> if none.
     *
     */
    public TextInput getTextInput() {
        return _textInput;
    }

    /**
     * Sets the channel text input.
     * <p>
     * @param textInput the channel text input to set, <b>null</b> if none.
     *
     */
    public void setTextInput(TextInput textInput) {
        _textInput = textInput;
    }

    /**
     * Returns the channel language.
     * <p>
     * @return the channel language, <b>null</b> if none.
     *
     */
    public String getLanguage() {
        return _language;
    }

    /**
     * Sets the channel language.
     * <p>
     * @param language the channel language to set, <b>null</b> if none.
     *
     */
    public void setLanguage(String language) {
        _language = language;
    }

    /**
     * Returns the channel rating.
     * <p>
     * @return the channel rating, <b>null</b> if none.
     *
     */
    public String getRating() {
        return _rating;
    }

    /**
     * Sets the channel rating.
     * <p>
     * @param rating the channel rating to set, <b>null</b> if none.
     *
     */
    public void setRating(String rating) {
        _rating = rating;
    }

    /**
     * Returns the channel copyright.
     * <p>
     * @return the channel copyright, <b>null</b> if none.
     *
     */
    public String getCopyright() {
        return _copyright;
    }

    /**
     * Sets the channel copyright.
     * <p>
     * @param copyright the channel copyright to set, <b>null</b> if none.
     *
     */
    public void setCopyright(String copyright) {
        _copyright = copyright;
    }

    /**
     * Returns the channel publishing date.
     * <p>
     * @return the channel publishing date, <b>null</b> if none.
     *
     */
    public Date getPubDate() {
        return _pubDate;
    }

    /**
     * Sets the channel publishing date.
     * <p>
     * @param pubDate the channel publishing date to set, <b>null</b> if none.
     *
     */
    public void setPubDate(Date pubDate) {
        _pubDate = pubDate;
    }

    /**
     * Returns the channel last build date.
     * <p>
     * @return the channel last build date, <b>null</b> if none.
     *
     */
    public Date getLastBuildDate() {
        return _lastBuildDate;
    }

    /**
     * Sets the channel last build date.
     * <p>
     * @param lastBuildDate the channel last build date to set, <b>null</b> if none.
     *
     */
    public void setLastBuildDate(Date lastBuildDate) {
        _lastBuildDate = lastBuildDate;
    }

    /**
     * Returns the channel docs.
     * <p>
     * @return the channel docs, <b>null</b> if none.
     *
     */
    public String getDocs() {
        return _docs;
    }

    /**
     * Sets the channel docs.
     * <p>
     * @param docs the channel docs to set, <b>null</b> if none.
     *
     */
    public void setDocs(String docs) {
        _docs = docs;
    }

    /**
     * Returns the channel managing editor.
     * <p>
     * @return the channel managing editor, <b>null</b> if none.
     *
     */
    public String getManagingEditor() {
        return _managingEditor;
    }

    /**
     * Sets the channel managing editor.
     * <p>
     * @param managingEditor the channel managing editor to set, <b>null</b> if none.
     *
     */
    public void setManagingEditor(String managingEditor) {
        _managingEditor = managingEditor;
    }

    /**
     * Returns the channel web master.
     * <p>
     * @return the channel web master, <b>null</b> if none.
     *
     */
    public String getWebMaster() {
        return _webMaster;
    }

    /**
     * Sets the channel web master.
     * <p>
     * @param webMaster the channel web master to set, <b>null</b> if none.
     *
     */
    public void setWebMaster(String webMaster) {
        _webMaster = webMaster;
    }

    /**
     * Returns the channel skip hours.
     * <p>
     * @return a list of Integer elements with the channel skip hours,
     *         an empty list if none.
     *
     */
    public List getSkipHours() {
        return (_skipHours!=null) ? _skipHours : new ArrayList();
    }

    /**
     * Sets the channel skip hours.
     * <p>
     * @param skipHours the list of Integer elements with the channel skip hours to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setSkipHours(List skipHours) {
        if (skipHours!=null) {
            for (int i=0;i<skipHours.size();i++) {
                Integer iHour = (Integer) skipHours.get(i);
                if (iHour!=null) {
                    int hour = iHour.intValue();
                    if (hour<0 || hour>24) {
                        throw new IllegalArgumentException("Invalid hour ["+hour+"]");
                    }
                }
                else {
                    throw new IllegalArgumentException("Invalid hour [null]");
                }
            }
        }
        _skipHours = skipHours;
    }

    /**
     * Returns the channel skip days.
     * <p>
     * @return a list of Day elements with the channel skip days,
     *         an empty list if none.
     *
     */
    public List getSkipDays() {
        return (_skipDays!=null) ? _skipDays : new ArrayList();
    }

    /**
     * Sets the channel skip days.
     * <p>
     * @param skipDays the list of Day elements with the channel skip days to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setSkipDays(List skipDays) {
        if (skipDays!=null) {
            for (int i=0;i<skipDays.size();i++) {
                String day = (String) skipDays.get(i);
                if (day!=null) {
                    day = day.toLowerCase();
                    if (!DAYS.contains(day)) {
                        throw new IllegalArgumentException("Invalid day ["+day+"]");
                    }
                    skipDays.set(i,day);
                }
                else {
                    throw new IllegalArgumentException("Invalid day [null]");
                }
            }
        }
        _skipDays = skipDays;
    }

    /**
     * Returns the channel cloud.
     * <p>
     * @return the channel cloud, <b>null</b> if none.
     *
     */
    public Cloud getCloud() {
        return _cloud;
    }

    /**
     * Sets the channel cloud.
     * <p>
     * @param cloud the channel cloud to set, <b>null</b> if none.
     *
     */
    public void setCloud(Cloud cloud) {
        _cloud = cloud;
    }

    /**
     * Returns the channel categories.
     * <p>
     * @return a list of Category elements with the channel categories,
     *         an empty list if none.
     *
     */
    public List getCategories() {
        return (_categories==null) ? (_categories=new ArrayList()) : _categories;
    }

    /**
     * Sets the channel categories.
     * <p>
     * @param categories the list of Category elements with the channel categories to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setCategories(List categories) {
        _categories = categories;
    }

    /**
     * Returns the channel generator.
     * <p>
     * @return the channel generator, <b>null</b> if none.
     *
     */
    public String getGenerator() {
        return _generator;
    }

    /**
     * Sets the channel generator.
     * <p>
     * @param generator the channel generator to set, <b>null</b> if none.
     *
     */
    public void setGenerator(String generator) {
        _generator = generator;
    }

    /**
     * Returns the channel time to live.
     * <p>
     * @return the channel time to live, <b>null</b> if none.
     *
     */
    public int getTtl() {
        return _ttl;
    }

    /**
     * Sets the channel time to live.
     * <p>
     * @param ttl the channel time to live to set, <b>null</b> if none.
     *
     */
    public void setTtl(int ttl) {
        _ttl = ttl;
    }

    /**
     * Returns the channel modules.
     * <p>
     * @return a list of ModuleImpl elements with the channel modules,
     *         an empty list if none.
     *
     */
    public List getModules() {
        return (_modules==null) ? (_modules=new ArrayList()) : _modules;
    }

    /**
     * Sets the channel modules.
     * <p>
     * @param modules the list of ModuleImpl elements with the channel modules to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setModules(List modules) {
        _modules = modules;
    }

    /**
     * Returns the module identified by a given URI.
     * <p>
     * @param uri the URI of the ModuleImpl.
     * @return The module with the given URI, <b>null</b> if none.
     */
    public Module getModule(String uri) {
        return ModuleUtils.getModule(_modules,uri);
    }


}
