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
package com.sun.syndication.feed.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.impl.CopyFromHelper;
import com.sun.syndication.feed.impl.ObjectBean;

/**
 * Dublin Core ModuleImpl, default implementation.
 * <p>
 * 
 * @see <a href="http://web.resource.org/rss/1.0/modules/dc/">Dublin Core
 *      module</a>.
 * @author Alejandro Abdelnur
 * 
 */
public class DCModuleImpl extends ModuleImpl implements DCModule {
    private final ObjectBean _objBean;
    private List<String> _title;
    private List<String> _creator;
    private List<DCSubject> _subject;
    private List<String> _description;
    private List<String> _publisher;
    private List<String> _contributors;
    private List<Date> _date;
    private List<String> _type;
    private List<String> _format;
    private List<String> _identifier;
    private List<String> _source;
    private List<String> _language;
    private List<String> _relation;
    private List<String> _coverage;
    private List<String> _rights;

    /**
     * Properties to be ignored when cloning.
     */
    private static final Set<String> IGNORE_PROPERTIES = new HashSet<String>();

    /**
     * Unmodifiable Set containing the convenience properties of this class.
     * <p>
     * Convenience properties are mapped to Modules, for cloning the convenience
     * properties can be ignored as the will be copied as part of the module
     * cloning.
     */
    public static final Set<String> CONVENIENCE_PROPERTIES = Collections.unmodifiableSet(IGNORE_PROPERTIES);

    static {
        IGNORE_PROPERTIES.add("title");
        IGNORE_PROPERTIES.add("creator");
        IGNORE_PROPERTIES.add("subject");
        IGNORE_PROPERTIES.add("description");
        IGNORE_PROPERTIES.add("publisher");
        IGNORE_PROPERTIES.add("contributor");
        IGNORE_PROPERTIES.add("date");
        IGNORE_PROPERTIES.add("type");
        IGNORE_PROPERTIES.add("format");
        IGNORE_PROPERTIES.add("identifier");
        IGNORE_PROPERTIES.add("source");
        IGNORE_PROPERTIES.add("language");
        IGNORE_PROPERTIES.add("relation");
        IGNORE_PROPERTIES.add("coverage");
        IGNORE_PROPERTIES.add("rights");
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     * 
     */
    public DCModuleImpl() {
        super(DCModule.class, URI);
        this._objBean = new ObjectBean(DCModule.class, this, CONVENIENCE_PROPERTIES);
    }

    /**
     * Returns the DublinCore module titles.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module title, an
     *         empty list if none.
     * 
     */
    @Override
    public List<String> getTitles() {
        return this._title == null ? (this._title = new ArrayList<String>()) : this._title;
    }

    /**
     * Sets the DublinCore module titles.
     * <p>
     * 
     * @param titles the list of String representing the DublinCore module
     *            titles to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setTitles(final List<String> titles) {
        this._title = titles;
    }

    /**
     * Gets the DublinCore module title. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module title, <b>null</b> if none.
     */
    @Override
    public String getTitle() {
        return this._title != null && this._title.size() > 0 ? (String) this._title.get(0) : null;
    }

    /**
     * Sets the DublinCore module title. Convenience method that can be used
     * when there is only one title to set.
     * <p>
     * 
     * @param title the DublinCore module title to set, <b>null</b> if none.
     * 
     */
    @Override
    public void setTitle(final String title) {
        this._title = new ArrayList<String>();
        this._title.add(title);
    }

    /**
     * Returns the DublinCore module creator.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module creator, an
     *         empty list if none.
     * 
     */
    @Override
    public List<String> getCreators() {
        return this._creator == null ? (this._creator = new ArrayList<String>()) : this._creator;
    }

    /**
     * Sets the DublinCore module creators.
     * <p>
     * 
     * @param creators the list of String representing the DublinCore module
     *            creators to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setCreators(final List<String> creators) {
        this._creator = creators;
    }

    /**
     * Gets the DublinCore module title. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module title, <b>null</b> if none.
     */
    @Override
    public String getCreator() {
        return this._creator != null && this._creator.size() > 0 ? (String) this._creator.get(0) : null;
    }

    /**
     * Sets the DublinCore module creator. Convenience method that can be used
     * when there is only one creator to set.
     * <p>
     * 
     * @param creator the DublinCore module creator to set, <b>null</b> if none.
     * 
     */
    @Override
    public void setCreator(final String creator) {
        this._creator = new ArrayList<String>();
        this._creator.add(creator);
    }

    /**
     * Returns the DublinCore module subjects.
     * <p>
     * 
     * @return a list of DCSubject elements with the DublinCore module subjects,
     *         an empty list if none.
     * 
     */
    @Override
    public List<DCSubject> getSubjects() {
        return this._subject == null ? (this._subject = new ArrayList<DCSubject>()) : this._subject;
    }

    /**
     * Sets the DublinCore module subjects.
     * <p>
     * 
     * @param subjects the list of DCSubject elements with the DublinCore module
     *            subjects to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setSubjects(final List<DCSubject> subjects) {
        this._subject = subjects;
    }

    /**
     * Gets the DublinCore module subject. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module subject, <b>null</b> if none.
     */
    @Override
    public DCSubject getSubject() {
        return this._subject != null && this._subject.size() > 0 ? (DCSubject) this._subject.get(0) : null;
    }

    /**
     * Sets the DCSubject element. Convenience method that can be used when
     * there is only one subject to set.
     * <p>
     * 
     * @param subject the DublinCore module subject to set, <b>null</b> if none.
     * 
     */
    @Override
    public void setSubject(final DCSubject subject) {
        this._subject = new ArrayList<DCSubject>();
        this._subject.add(subject);
    }

    /**
     * Returns the DublinCore module description.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module description,
     *         an empty list if none.
     * 
     */
    @Override
    public List<String> getDescriptions() {
        return this._description == null ? (this._description = new ArrayList<String>()) : this._description;
    }

    /**
     * Sets the DublinCore module descriptions.
     * <p>
     * 
     * @param descriptions the list of String representing the DublinCore module
     *            descriptions to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setDescriptions(final List<String> descriptions) {
        this._description = descriptions;
    }

    /**
     * Gets the DublinCore module description. Convenience method that can be
     * used to obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module description, <b>null</b> if none.
     */
    @Override
    public String getDescription() {
        return this._description != null && this._description.size() > 0 ? (String) this._description.get(0) : null;
    }

    /**
     * Sets the DublinCore module description. Convenience method that can be
     * used when there is only one description to set.
     * <p>
     * 
     * @param description the DublinCore module description to set, <b>null</b>
     *            if none.
     * 
     */
    @Override
    public void setDescription(final String description) {
        this._description = new ArrayList<String>();
        this._description.add(description);
    }

    /**
     * Returns the DublinCore module publisher.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module publisher,
     *         an empty list if none.
     * 
     */
    @Override
    public List<String> getPublishers() {
        return this._publisher == null ? (this._publisher = new ArrayList<String>()) : this._publisher;
    }

    /**
     * Sets the DublinCore module publishers.
     * <p>
     * 
     * @param publishers the list of String representing the DublinCore module
     *            publishers to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setPublishers(final List<String> publishers) {
        this._publisher = publishers;
    }

    /**
     * Gets the DublinCore module title. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module title, <b>null</b> if none.
     */
    @Override
    public String getPublisher() {
        return this._publisher != null && this._publisher.size() > 0 ? (String) this._publisher.get(0) : null;
    }

    /**
     * Sets the DublinCore module publisher. Convenience method that can be used
     * when there is only one publisher to set.
     * <p>
     * 
     * @param publisher the DublinCore module publisher to set, <b>null</b> if
     *            none.
     * 
     */
    @Override
    public void setPublisher(final String publisher) {
        this._publisher = new ArrayList<String>();
        this._publisher.add(publisher);
    }

    /**
     * Returns the DublinCore module contributor.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module contributor,
     *         an empty list if none.
     * 
     */
    @Override
    public List<String> getContributors() {
        return this._contributors == null ? (this._contributors = new ArrayList<String>()) : this._contributors;
    }

    /**
     * Sets the DublinCore module contributors.
     * <p>
     * 
     * @param contributors the list of String representing the DublinCore module
     *            contributors to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setContributors(final List<String> contributors) {
        this._contributors = contributors;
    }

    /**
     * Gets the DublinCore module contributor. Convenience method that can be
     * used to obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module contributor, <b>null</b> if none.
     */
    @Override
    public String getContributor() {
        return this._contributors != null && this._contributors.size() > 0 ? (String) this._contributors.get(0) : null;
    }

    /**
     * Sets the DublinCore module contributor. Convenience method that can be
     * used when there is only one contributor to set.
     * <p>
     * 
     * @param contributor the DublinCore module contributor to set, <b>null</b>
     *            if none.
     * 
     */
    @Override
    public void setContributor(final String contributor) {
        this._contributors = new ArrayList<String>();
        this._contributors.add(contributor);
    }

    /**
     * Returns the DublinCore module date.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module date, an
     *         empty list if none.
     * 
     */
    @Override
    public List<Date> getDates() {
        return this._date == null ? (this._date = new ArrayList<Date>()) : this._date;
    }

    /**
     * Sets the DublinCore module dates.
     * <p>
     * 
     * @param dates the list of Date representing the DublinCore module dates to
     *            set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setDates(final List<Date> dates) {
        this._date = dates;
    }

    /**
     * Gets the DublinCore module date. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module date, <b>null</b> if none.
     */
    @Override
    public Date getDate() {
        return this._date != null && this._date.size() > 0 ? (Date) this._date.get(0) : null;
    }

    /**
     * Sets the DublinCore module date. Convenience method that can be used when
     * there is only one date to set.
     * <p>
     * 
     * @param date the DublinCore module date to set, <b>null</b> if none.
     * 
     */
    @Override
    public void setDate(final Date date) {
        this._date = new ArrayList<Date>();
        this._date.add(date);
    }

    /**
     * Returns the DublinCore module type.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module type, an
     *         empty list if none.
     * 
     */
    @Override
    public List<String> getTypes() {
        return this._type == null ? (this._type = new ArrayList<String>()) : this._type;
    }

    /**
     * Sets the DublinCore module types.
     * <p>
     * 
     * @param types the list of String representing the DublinCore module types
     *            to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setTypes(final List<String> types) {
        this._type = types;
    }

    /**
     * Gets the DublinCore module type. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module type, <b>null</b> if none.
     */
    @Override
    public String getType() {
        return this._type != null && this._type.size() > 0 ? (String) this._type.get(0) : null;
    }

    /**
     * Sets the DublinCore module type. Convenience method that can be used when
     * there is only one type to set.
     * <p>
     * 
     * @param type the DublinCore module type to set, <b>null</b> if none.
     * 
     */
    @Override
    public void setType(final String type) {
        this._type = new ArrayList<String>();
        this._type.add(type);
    }

    /**
     * Returns the DublinCore module format.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module format, an
     *         empty list if none.
     * 
     */
    @Override
    public List<String> getFormats() {
        return this._format == null ? (this._format = new ArrayList<String>()) : this._format;
    }

    /**
     * Sets the DublinCore module formats.
     * <p>
     * 
     * @param formats the list of String representing the DublinCore module
     *            formats to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setFormats(final List<String> formats) {
        this._format = formats;
    }

    /**
     * Gets the DublinCore module format. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module format, <b>null</b> if none.
     */
    @Override
    public String getFormat() {
        return this._format != null && this._format.size() > 0 ? (String) this._format.get(0) : null;
    }

    /**
     * Sets the DublinCore module format. Convenience method that can be used
     * when there is only one format to set.
     * <p>
     * 
     * @param format the DublinCore module format to set, <b>null</b> if none.
     * 
     */
    @Override
    public void setFormat(final String format) {
        this._format = new ArrayList<String>();
        this._format.add(format);
    }

    /**
     * Returns the DublinCore module identifier.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module identifier,
     *         an empty list if none.
     * 
     */
    @Override
    public List<String> getIdentifiers() {
        return this._identifier == null ? (this._identifier = new ArrayList<String>()) : this._identifier;
    }

    /**
     * Sets the DublinCore module identifiers.
     * <p>
     * 
     * @param identifiers the list of String representing the DublinCore module
     *            identifiers to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setIdentifiers(final List<String> identifiers) {
        this._identifier = identifiers;
    }

    /**
     * Gets the DublinCore module identifier. Convenience method that can be
     * used to obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module identifier, <b>null</b> if none.
     */
    @Override
    public String getIdentifier() {
        return this._identifier != null && this._identifier.size() > 0 ? (String) this._identifier.get(0) : null;
    }

    /**
     * Sets the DublinCore module identifier. Convenience method that can be
     * used when there is only one identifier to set.
     * <p>
     * 
     * @param identifier the DublinCore module identifier to set, <b>null</b> if
     *            none.
     * 
     */
    @Override
    public void setIdentifier(final String identifier) {
        this._identifier = new ArrayList<String>();
        this._identifier.add(identifier);
    }

    /**
     * Returns the DublinCore module source.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module source, an
     *         empty list if none.
     * 
     */
    @Override
    public List<String> getSources() {
        return this._source == null ? (this._source = new ArrayList<String>()) : this._source;
    }

    /**
     * Sets the DublinCore module sources.
     * <p>
     * 
     * @param sources the list of String representing the DublinCore module
     *            sources to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setSources(final List<String> sources) {
        this._source = sources;
    }

    /**
     * Gets the DublinCore module source. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module source, <b>null</b> if none.
     */
    @Override
    public String getSource() {
        return this._source != null && this._source.size() > 0 ? (String) this._source.get(0) : null;
    }

    /**
     * Sets the DublinCore module source. Convenience method that can be used
     * when there is only one source to set.
     * <p>
     * 
     * @param source the DublinCore module source to set, <b>null</b> if none.
     * 
     */
    @Override
    public void setSource(final String source) {
        this._source = new ArrayList<String>();
        this._source.add(source);
    }

    /**
     * Returns the DublinCore module language.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module language, an
     *         empty list if none.
     * 
     */
    @Override
    public List<String> getLanguages() {
        return this._language == null ? (this._language = new ArrayList<String>()) : this._language;
    }

    /**
     * Sets the DublinCore module languages.
     * <p>
     * 
     * @param languages the list of String representing the DublinCore module
     *            languages to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setLanguages(final List<String> languages) {
        this._language = languages;
    }

    /**
     * Gets the DublinCore module language. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module langauge, <b>null</b> if none.
     */
    @Override
    public String getLanguage() {
        return this._language != null && this._language.size() > 0 ? (String) this._language.get(0) : null;
    }

    /**
     * Sets the DublinCore module language. Convenience method that can be used
     * when there is only one language to set.
     * <p>
     * 
     * @param language the DublinCore module language to set, <b>null</b> if
     *            none.
     * 
     */
    @Override
    public void setLanguage(final String language) {
        this._language = new ArrayList<String>();
        this._language.add(language);
    }

    /**
     * Returns the DublinCore module relation.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module relation, an
     *         empty list if none.
     * 
     */
    @Override
    public List<String> getRelations() {
        return this._relation == null ? (this._relation = new ArrayList<String>()) : this._relation;
    }

    /**
     * Sets the DublinCore module relations.
     * <p>
     * 
     * @param relations the list of String representing the DublinCore module
     *            relations to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setRelations(final List<String> relations) {
        this._relation = relations;
    }

    /**
     * Gets the DublinCore module relation. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module relation, <b>null</b> if none.
     */
    @Override
    public String getRelation() {
        return this._relation != null && this._relation.size() > 0 ? (String) this._relation.get(0) : null;
    }

    /**
     * Sets the DublinCore module relation. Convenience method that can be used
     * when there is only one relation to set.
     * <p>
     * 
     * @param relation the DublinCore module relation to set, <b>null</b> if
     *            none.
     * 
     */
    @Override
    public void setRelation(final String relation) {
        this._relation = new ArrayList<String>();
        this._relation.add(relation);
    }

    /**
     * Returns the DublinCore module coverage.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module coverage, an
     *         empty list if none.
     * 
     */
    @Override
    public List<String> getCoverages() {
        return this._coverage == null ? (this._coverage = new ArrayList<String>()) : this._coverage;
    }

    /**
     * Sets the DublinCore module coverages.
     * <p>
     * 
     * @param coverages the list of String representing the DublinCore module
     *            coverages to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setCoverages(final List<String> coverages) {
        this._coverage = coverages;
    }

    /**
     * Gets the DublinCore module coverage. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module coverage, <b>null</b> if none.
     */
    @Override
    public String getCoverage() {
        return this._coverage != null && this._coverage.size() > 0 ? (String) this._coverage.get(0) : null;
    }

    /**
     * Sets the DublinCore module coverage. Convenience method that can be used
     * when there is only one coverage to set.
     * <p>
     * 
     * @param coverage the DublinCore module coverage to set, <b>null</b> if
     *            none.
     * 
     */
    @Override
    public void setCoverage(final String coverage) {
        this._coverage = new ArrayList<String>();
        this._coverage.add(coverage);
    }

    /**
     * Returns the DublinCore module rights.
     * <p>
     * 
     * @return a list of Strings representing the DublinCore module rights, an
     *         empty list if none.
     * 
     */
    @Override
    public List<String> getRightsList() {
        return this._rights == null ? (this._rights = new ArrayList<String>()) : this._rights;
    }

    /**
     * Sets the DublinCore module rights.
     * <p>
     * 
     * @param rights the list of String representing the DublinCore module
     *            rights to set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setRightsList(final List<String> rights) {
        this._rights = rights;
    }

    /**
     * Gets the DublinCore module rights. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * 
     * @return the first DublinCore module rights, <b>null</b> if none.
     */
    @Override
    public String getRights() {
        return this._rights != null && this._rights.size() > 0 ? (String) this._rights.get(0) : null;
    }

    /**
     * Sets the DublinCore module rights. Convenience method that can be used
     * when there is only one rights to set.
     * <p>
     * 
     * @param rights the DublinCore module rights to set, <b>null</b> if none.
     * 
     */
    @Override
    public void setRights(final String rights) {
        this._rights = new ArrayList<String>();
        this._rights.add(rights);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * 
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object
     *             cannot be cloned.
     * 
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {
        return this._objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by
     * the Object equals() method.
     * <p>
     * 
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     * 
     */
    @Override
    public final boolean equals(final Object other) {
        return this._objBean.equals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * 
     * @return the hashcode of the bean object.
     * 
     */
    @Override
    public final int hashCode() {
        return this._objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * 
     * @return String representation for the object.
     * 
     */
    @Override
    public final String toString() {
        return this._objBean.toString();
    }

    @Override
    public final Class<? extends CopyFrom> getInterface() {
        return DCModule.class;
    }

    @Override
    public final void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    private static final CopyFromHelper COPY_FROM_HELPER;

    static {
        final Map basePropInterfaceMap = new HashMap();
        basePropInterfaceMap.put("titles", String.class);
        basePropInterfaceMap.put("creators", String.class);
        basePropInterfaceMap.put("subjects", DCSubject.class);
        basePropInterfaceMap.put("descriptions", String.class);
        basePropInterfaceMap.put("publishers", String.class);
        basePropInterfaceMap.put("contributors", String.class);
        basePropInterfaceMap.put("dates", Date.class);
        basePropInterfaceMap.put("types", String.class);
        basePropInterfaceMap.put("formats", String.class);
        basePropInterfaceMap.put("identifiers", String.class);
        basePropInterfaceMap.put("sources", String.class);
        basePropInterfaceMap.put("languages", String.class);
        basePropInterfaceMap.put("relations", String.class);
        basePropInterfaceMap.put("coverages", String.class);
        basePropInterfaceMap.put("rightsList", String.class);

        final Map basePropClassImplMap = new HashMap();
        basePropClassImplMap.put(DCSubject.class, DCSubjectImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(DCModule.class, basePropInterfaceMap, basePropClassImplMap);
    }
}
