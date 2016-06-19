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
package com.rometools.rome.feed.module;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.utils.Lists;

/**
 * Dublin Core ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://web.resource.org/rss/1.0/modules/dc/">Dublin Core module</a>.
 */
public class DCModuleImpl extends ModuleImpl implements DCModule {

    private static final long serialVersionUID = 1L;

    private final ObjectBean objBean;
    private List<String> title;
    private List<String> creator;
    private List<DCSubject> subject;
    private List<String> description;
    private List<String> publisher;
    private List<String> contributors;
    private List<Date> date;
    private List<String> type;
    private List<String> format;
    private List<String> identifier;
    private List<String> source;
    private List<String> language;
    private List<String> relation;
    private List<String> coverage;
    private List<String> rights;

    /**
     * Properties to be ignored when cloning.
     */
    private static final Set<String> IGNORE_PROPERTIES = new HashSet<String>();

    /**
     * Unmodifiable Set containing the convenience properties of this class.
     * <p>
     * Convenience properties are mapped to Modules, for cloning the convenience properties can be
     * ignored as the will be copied as part of the module cloning.
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

    public DCModuleImpl() {
        super(DCModule.class, URI);
        objBean = new ObjectBean(DCModule.class, this, CONVENIENCE_PROPERTIES);
    }

    /**
     * Returns the DublinCore module titles.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module title, an empty list if none.
     *
     */
    @Override
    public List<String> getTitles() {
        return title = Lists.createWhenNull(title);
    }

    /**
     * Sets the DublinCore module titles.
     * <p>
     *
     * @param titles the list of String representing the DublinCore module titles to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    @Override
    public void setTitles(final List<String> titles) {
        title = titles;
    }

    /**
     * Gets the DublinCore module title. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module title, <b>null</b> if none.
     */
    @Override
    public String getTitle() {
        return Lists.firstEntry(title);
    }

    /**
     * Sets the DublinCore module title. Convenience method that can be used when there is only one
     * title to set.
     * <p>
     *
     * @param title the DublinCore module title to set, <b>null</b> if none.
     *
     */
    @Override
    public void setTitle(final String title) {
        this.title = Lists.create(title);
    }

    /**
     * Returns the DublinCore module creator.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module creator, an empty list if none.
     *
     */
    @Override
    public List<String> getCreators() {
        return creator = Lists.createWhenNull(creator);
    }

    /**
     * Sets the DublinCore module creators.
     * <p>
     *
     * @param creators the list of String representing the DublinCore module creators to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setCreators(final List<String> creators) {
        creator = creators;
    }

    /**
     * Gets the DublinCore module title. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module title, <b>null</b> if none.
     */
    @Override
    public String getCreator() {
        return Lists.firstEntry(creator);
    }

    /**
     * Sets the DublinCore module creator. Convenience method that can be used when there is only
     * one creator to set.
     * <p>
     *
     * @param creator the DublinCore module creator to set, <b>null</b> if none.
     *
     */
    @Override
    public void setCreator(final String creator) {
        this.creator = Lists.create(creator);
    }

    /**
     * Returns the DublinCore module subjects.
     * <p>
     *
     * @return a list of DCSubject elements with the DublinCore module subjects, an empty list if
     *         none.
     *
     */
    @Override
    public List<DCSubject> getSubjects() {
        return subject = Lists.createWhenNull(subject);
    }

    /**
     * Sets the DublinCore module subjects.
     * <p>
     *
     * @param subjects the list of DCSubject elements with the DublinCore module subjects to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setSubjects(final List<DCSubject> subjects) {
        subject = subjects;
    }

    /**
     * Gets the DublinCore module subject. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module subject, <b>null</b> if none.
     */
    @Override
    public DCSubject getSubject() {
        return Lists.firstEntry(subject);
    }

    /**
     * Sets the DCSubject element. Convenience method that can be used when there is only one
     * subject to set.
     * <p>
     *
     * @param subject the DublinCore module subject to set, <b>null</b> if none.
     *
     */
    @Override
    public void setSubject(final DCSubject subject) {
        this.subject = Lists.create(subject);
    }

    /**
     * Returns the DublinCore module description.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module description, an empty list if
     *         none.
     *
     */
    @Override
    public List<String> getDescriptions() {
        return description = Lists.createWhenNull(description);
    }

    /**
     * Sets the DublinCore module descriptions.
     * <p>
     *
     * @param descriptions the list of String representing the DublinCore module descriptions to
     *            set, an empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setDescriptions(final List<String> descriptions) {
        description = descriptions;
    }

    /**
     * Gets the DublinCore module description. Convenience method that can be used to obtain the
     * first item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module description, <b>null</b> if none.
     */
    @Override
    public String getDescription() {
        return Lists.firstEntry(description);
    }

    /**
     * Sets the DublinCore module description. Convenience method that can be used when there is
     * only one description to set.
     * <p>
     *
     * @param description the DublinCore module description to set, <b>null</b> if none.
     *
     */
    @Override
    public void setDescription(final String description) {
        this.description = Lists.create(description);
    }

    /**
     * Returns the DublinCore module publisher.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module publisher, an empty list if
     *         none.
     *
     */
    @Override
    public List<String> getPublishers() {
        return publisher = Lists.createWhenNull(publisher);
    }

    /**
     * Sets the DublinCore module publishers.
     * <p>
     *
     * @param publishers the list of String representing the DublinCore module publishers to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setPublishers(final List<String> publishers) {
        publisher = publishers;
    }

    /**
     * Gets the DublinCore module title. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module title, <b>null</b> if none.
     */
    @Override
    public String getPublisher() {
        return Lists.firstEntry(publisher);
    }

    /**
     * Sets the DublinCore module publisher. Convenience method that can be used when there is only
     * one publisher to set.
     * <p>
     *
     * @param publisher the DublinCore module publisher to set, <b>null</b> if none.
     *
     */
    @Override
    public void setPublisher(final String publisher) {
        this.publisher = Lists.create(publisher);
    }

    /**
     * Returns the DublinCore module contributor.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module contributor, an empty list if
     *         none.
     *
     */
    @Override
    public List<String> getContributors() {
        return contributors = Lists.createWhenNull(contributors);
    }

    /**
     * Sets the DublinCore module contributors.
     * <p>
     *
     * @param contributors the list of String representing the DublinCore module contributors to
     *            set, an empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setContributors(final List<String> contributors) {
        this.contributors = contributors;
    }

    /**
     * Gets the DublinCore module contributor. Convenience method that can be used to obtain the
     * first item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module contributor, <b>null</b> if none.
     */
    @Override
    public String getContributor() {
        return Lists.firstEntry(contributors);
    }

    /**
     * Sets the DublinCore module contributor. Convenience method that can be used when there is
     * only one contributor to set.
     * <p>
     *
     * @param contributor the DublinCore module contributor to set, <b>null</b> if none.
     *
     */
    @Override
    public void setContributor(final String contributor) {
        contributors = Lists.create(contributor);
    }

    /**
     * Returns the DublinCore module date.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module date, an empty list if none.
     *
     */
    @Override
    public List<Date> getDates() {
        return date = Lists.createWhenNull(date);
    }

    /**
     * Sets the DublinCore module dates.
     * <p>
     *
     * @param dates the list of Date representing the DublinCore module dates to set, an empty list
     *            or <b>null</b> if none.
     *
     */
    @Override
    public void setDates(final List<Date> dates) {
        date = dates;
    }

    /**
     * Gets the DublinCore module date. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module date, <b>null</b> if none.
     */
    @Override
    public Date getDate() {
        return Lists.firstEntry(date);
    }

    /**
     * Sets the DublinCore module date. Convenience method that can be used when there is only one
     * date to set.
     * <p>
     *
     * @param date the DublinCore module date to set, <b>null</b> if none.
     *
     */
    @Override
    public void setDate(final Date date) {
        this.date = Lists.create(date);
    }

    /**
     * Returns the DublinCore module type.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module type, an empty list if none.
     *
     */
    @Override
    public List<String> getTypes() {
        return type = Lists.createWhenNull(type);
    }

    /**
     * Sets the DublinCore module types.
     * <p>
     *
     * @param types the list of String representing the DublinCore module types to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    @Override
    public void setTypes(final List<String> types) {
        type = types;
    }

    /**
     * Gets the DublinCore module type. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module type, <b>null</b> if none.
     */
    @Override
    public String getType() {
        return Lists.firstEntry(type);
    }

    /**
     * Sets the DublinCore module type. Convenience method that can be used when there is only one
     * type to set.
     * <p>
     *
     * @param type the DublinCore module type to set, <b>null</b> if none.
     *
     */
    @Override
    public void setType(final String type) {
        this.type = Lists.create(type);
    }

    /**
     * Returns the DublinCore module format.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module format, an empty list if none.
     *
     */
    @Override
    public List<String> getFormats() {
        return format = Lists.createWhenNull(format);
    }

    /**
     * Sets the DublinCore module formats.
     * <p>
     *
     * @param formats the list of String representing the DublinCore module formats to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    @Override
    public void setFormats(final List<String> formats) {
        format = formats;
    }

    /**
     * Gets the DublinCore module format. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module format, <b>null</b> if none.
     */
    @Override
    public String getFormat() {
        return Lists.firstEntry(format);
    }

    /**
     * Sets the DublinCore module format. Convenience method that can be used when there is only one
     * format to set.
     * <p>
     *
     * @param format the DublinCore module format to set, <b>null</b> if none.
     *
     */
    @Override
    public void setFormat(final String format) {
        this.format = Lists.create(format);
    }

    /**
     * Returns the DublinCore module identifier.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module identifier, an empty list if
     *         none.
     *
     */
    @Override
    public List<String> getIdentifiers() {
        return identifier = Lists.createWhenNull(identifier);
    }

    /**
     * Sets the DublinCore module identifiers.
     * <p>
     *
     * @param identifiers the list of String representing the DublinCore module identifiers to set,
     *            an empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setIdentifiers(final List<String> identifiers) {
        identifier = identifiers;
    }

    /**
     * Gets the DublinCore module identifier. Convenience method that can be used to obtain the
     * first item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module identifier, <b>null</b> if none.
     */
    @Override
    public String getIdentifier() {
        return Lists.firstEntry(identifier);
    }

    /**
     * Sets the DublinCore module identifier. Convenience method that can be used when there is only
     * one identifier to set.
     * <p>
     *
     * @param identifier the DublinCore module identifier to set, <b>null</b> if none.
     *
     */
    @Override
    public void setIdentifier(final String identifier) {
        this.identifier = Lists.create(identifier);
    }

    /**
     * Returns the DublinCore module source.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module source, an empty list if none.
     *
     */
    @Override
    public List<String> getSources() {
        return source = Lists.createWhenNull(source);
    }

    /**
     * Sets the DublinCore module sources.
     * <p>
     *
     * @param sources the list of String representing the DublinCore module sources to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    @Override
    public void setSources(final List<String> sources) {
        source = sources;
    }

    /**
     * Gets the DublinCore module source. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module source, <b>null</b> if none.
     */
    @Override
    public String getSource() {
        return Lists.firstEntry(source);
    }

    /**
     * Sets the DublinCore module source. Convenience method that can be used when there is only one
     * source to set.
     * <p>
     *
     * @param source the DublinCore module source to set, <b>null</b> if none.
     *
     */
    @Override
    public void setSource(final String source) {
        this.source = Lists.create(source);
    }

    /**
     * Returns the DublinCore module language.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module language, an empty list if none.
     *
     */
    @Override
    public List<String> getLanguages() {
        return language = Lists.createWhenNull(language);
    }

    /**
     * Sets the DublinCore module languages.
     * <p>
     *
     * @param languages the list of String representing the DublinCore module languages to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setLanguages(final List<String> languages) {
        language = languages;
    }

    /**
     * Gets the DublinCore module language. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module langauge, <b>null</b> if none.
     */
    @Override
    public String getLanguage() {
        return Lists.firstEntry(language);
    }

    /**
     * Sets the DublinCore module language. Convenience method that can be used when there is only
     * one language to set.
     * <p>
     *
     * @param language the DublinCore module language to set, <b>null</b> if none.
     *
     */
    @Override
    public void setLanguage(final String language) {
        this.language = Lists.create(language);
    }

    /**
     * Returns the DublinCore module relation.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module relation, an empty list if none.
     *
     */
    @Override
    public List<String> getRelations() {
        return relation = Lists.createWhenNull(relation);
    }

    /**
     * Sets the DublinCore module relations.
     * <p>
     *
     * @param relations the list of String representing the DublinCore module relations to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setRelations(final List<String> relations) {
        relation = relations;
    }

    /**
     * Gets the DublinCore module relation. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module relation, <b>null</b> if none.
     */
    @Override
    public String getRelation() {
        return Lists.firstEntry(relation);
    }

    /**
     * Sets the DublinCore module relation. Convenience method that can be used when there is only
     * one relation to set.
     * <p>
     *
     * @param relation the DublinCore module relation to set, <b>null</b> if none.
     *
     */
    @Override
    public void setRelation(final String relation) {
        this.relation = Lists.create(relation);
    }

    /**
     * Returns the DublinCore module coverage.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module coverage, an empty list if none.
     *
     */
    @Override
    public List<String> getCoverages() {
        return coverage = Lists.createWhenNull(coverage);
    }

    /**
     * Sets the DublinCore module coverages.
     * <p>
     *
     * @param coverages the list of String representing the DublinCore module coverages to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setCoverages(final List<String> coverages) {
        coverage = coverages;
    }

    /**
     * Gets the DublinCore module coverage. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module coverage, <b>null</b> if none.
     */
    @Override
    public String getCoverage() {
        return Lists.firstEntry(coverage);
    }

    /**
     * Sets the DublinCore module coverage. Convenience method that can be used when there is only
     * one coverage to set.
     * <p>
     *
     * @param coverage the DublinCore module coverage to set, <b>null</b> if none.
     *
     */
    @Override
    public void setCoverage(final String coverage) {
        this.coverage = Lists.create(coverage);
    }

    /**
     * Returns the DublinCore module rights.
     * <p>
     *
     * @return a list of Strings representing the DublinCore module rights, an empty list if none.
     *
     */
    @Override
    public List<String> getRightsList() {
        return rights = Lists.createWhenNull(rights);
    }

    /**
     * Sets the DublinCore module rights.
     * <p>
     *
     * @param rights the list of String representing the DublinCore module rights to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    @Override
    public void setRightsList(final List<String> rights) {
        this.rights = rights;
    }

    /**
     * Gets the DublinCore module rights. Convenience method that can be used to obtain the first
     * item, <b>null</b> if none.
     * <p>
     *
     * @return the first DublinCore module rights, <b>null</b> if none.
     */
    @Override
    public String getRights() {
        return Lists.firstEntry(rights);
    }

    /**
     * Sets the DublinCore module rights. Convenience method that can be used when there is only one
     * rights to set.
     * <p>
     *
     * @param rights the DublinCore module rights to set, <b>null</b> if none.
     *
     */
    @Override
    public void setRights(final String rights) {
        this.rights = Lists.create(rights);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {
        return objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals()
     * method.
     * <p>
     *
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public final boolean equals(final Object other) {
        return objBean.equals(other);
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
        return objBean.hashCode();
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
        return objBean.toString();
    }

    @Override
    public final Class<DCModule> getInterface() {
        return DCModule.class;
    }

    @Override
    public final void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    private static final CopyFromHelper COPY_FROM_HELPER;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
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

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(DCSubject.class, DCSubjectImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(DCModule.class, basePropInterfaceMap, basePropClassImplMap);
    }

}
