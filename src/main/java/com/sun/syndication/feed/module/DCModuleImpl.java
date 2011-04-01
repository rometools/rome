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

import com.sun.syndication.feed.impl.CopyFromHelper;
import com.sun.syndication.feed.impl.ObjectBean;

import java.util.*;


/**
 * Dublin Core ModuleImpl, default implementation.
 * <p>
 * @see <a href="http://web.resource.org/rss/1.0/modules/dc/">Dublin Core module</a>.
 * @author Alejandro Abdelnur
 *
 */
public class DCModuleImpl extends ModuleImpl implements DCModule {
    private ObjectBean _objBean;
    private List _title;
    private List _creator;
    private List _subject;
    private List _description;
    private List _publisher;
    private List _contributors;
    private List _date;
    private List _type;
    private List _format;
    private List _identifier;
    private List _source;
    private List _language;
    private List _relation;
    private List _coverage;
    private List _rights;

    /**
     * Properties to be ignored when cloning.
     */
    private static final Set IGNORE_PROPERTIES = new HashSet();

    /**
     * Unmodifiable Set containing the convenience properties of this class.
     * <p>
     * Convenience properties are mapped to Modules, for cloning the convenience
     * properties can be ignored as the will be copied as part of the module
     * cloning.
     */
    public static final Set CONVENIENCE_PROPERTIES = Collections.unmodifiableSet(IGNORE_PROPERTIES);

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
        _objBean = new ObjectBean(DCModule.class, this, CONVENIENCE_PROPERTIES);
    }

    /**
     * Returns the DublinCore module titles.
     * <p>
     * @return a list of Strings representing the DublinCore module title,
     *         an empty list if none.
     *
     */
    public List getTitles() {
        return (_title == null) ? (_title = new ArrayList()) : _title;
    }

    /**
     * Sets the DublinCore module titles.
     * <p>
     * @param titles the list of String representing the DublinCore module
     * 		titles to set, an empty list or <b>null</b> if none.
     *
     */
    public void setTitles(List titles) {
    		_title = titles;
    }

    /**
     * Gets the DublinCore module title. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module title, <b>null</b> if none.
     */
    public String getTitle() {
        return ((_title != null) && (_title.size() > 0)) ? (String) _title.get(0) : null;
    }

    /**
     * Sets the DublinCore module title. Convenience method that can be used
     * when there is only one title to set.
     * <p>
     * @param title the DublinCore module title to set, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
    		_title = new ArrayList();
    		_title.add(title);
    }

    /**
     * Returns the DublinCore module creator.
     * <p>
     * @return a list of Strings representing the DublinCore module creator,
     *         an empty list if none.
     *
     */
    public List getCreators() {
        return (_creator == null) ? (_creator = new ArrayList()) : _creator;
    }

    /**
     * Sets the DublinCore module creators.
     * <p>
     * @param creators the list of String representing the DublinCore module
     * 		creators to set, an empty list or <b>null</b> if none.
     *
     */
    public void setCreators(List creators) {
        _creator = creators;
    }
    
    /**
     * Gets the DublinCore module title. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module title, <b>null</b> if none.
     */
    public String getCreator() {
        return ((_creator != null) && (_creator.size() > 0)) ? (String) _creator.get(0) : null;
    }
    
    /**
     * Sets the DublinCore module creator. Convenience method that can be used
     * when there is only one creator to set.
     * <p>
     * @param creator the DublinCore module creator to set, <b>null</b> if none.
     *
     */
    public void setCreator(String creator) {
        _creator = new ArrayList();
        _creator.add(creator);
    }

    /**
     * Returns the DublinCore module subjects.
     * <p>
     * @return a list of DCSubject elements with the DublinCore module subjects,
     *         an empty list if none.
     *
     */
    public List getSubjects() {
        return (_subject == null) ? (_subject = new ArrayList()) : _subject;
    }

    /**
     * Sets the DublinCore module subjects.
     * <p>
     * @param subjects the list of DCSubject elements with the DublinCore
     * 		module subjects to set, an empty list or <b>null</b> if none.
     *
     */
    public void setSubjects(List subjects) {
        _subject = subjects;
    }

    /**
     * Gets the DublinCore module subject. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module subject, <b>null</b> if none. 
     */
    public DCSubject getSubject() {
        return ((_subject != null) && (_subject.size() > 0)) ?
                (DCSubject) _subject.get(0) : null;
    }
    
    /**
     * Sets the DCSubject element. Convenience method that can be used when
     * there is only one subject to set.
     * <p>
     * @param subject the DublinCore module subject to set, <b>null</b> if none.
     *
     */
    public void setSubject(DCSubject subject) {
        _subject = new ArrayList();
        _subject.add(subject);
    }

    /**
     * Returns the DublinCore module description.
     * <p>
     * @return a list of Strings representing the DublinCore module
     * 		description, an empty list if none.
     *
     */
    public List getDescriptions() {
        return (_description == null) ? (_description = new ArrayList()) : _description;
    }

    /**
     * Sets the DublinCore module descriptions.
     * <p>
     * @param descriptions the list of String representing the DublinCore
     * 		module descriptions to set, an empty list or <b>null</b> if none.
     *
     */
    public void setDescriptions(List descriptions) {
        _description = descriptions;
    }
    
    /**
     * Gets the DublinCore module description. Convenience method that can be
     * used to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module description, <b>null</b> if none.
     */
    public String getDescription() {
        return ((_description != null) && (_description.size() > 0)) ?
                (String) _description.get(0) : null;
    }
    
    /**
     * Sets the DublinCore module description. Convenience method that can be
     * used when there is only one description to set.
     * <p>
     * @param description the DublinCore module description to set, <b>null</b> if none.
     *
     */
    public void setDescription(String description) {
        _description = new ArrayList();
        _description.add(description);
    }

    /**
     * Returns the DublinCore module publisher.
     * <p>
     * @return a list of Strings representing the DublinCore module publisher,
     *         an empty list if none.
     *
     */
    public List getPublishers() {
        return (_publisher == null) ? (_publisher = new ArrayList()) : _publisher;
    }

    /**
     * Sets the DublinCore module publishers.
     * <p>
     * @param publishers the list of String representing the DublinCore module
     * 		publishers to set, an empty list or <b>null</b> if none.
     *
     */
    public void setPublishers(List publishers) {
        _publisher = publishers;
    }

    /**
     * Gets the DublinCore module title. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module title, <b>null</b> if none.
     */
    public String getPublisher() {
        return ((_publisher != null) && (_publisher.size() > 0)) ?
                (String) _publisher.get(0) : null;
    }

    /**
     * Sets the DublinCore module publisher. Convenience method that can be
     * used when there is only one publisher to set.
     * <p>
     * @param publisher the DublinCore module publisher to set, <b>null</b> if none.
     *
     */
    public void setPublisher(String publisher) {
        _publisher = new ArrayList();
        _publisher.add(publisher);
    }

    /**
     * Returns the DublinCore module contributor.
     * <p>
     * @return a list of Strings representing the DublinCore module contributor,
     *         an empty list if none.
     *
     */
    public List getContributors() {
        return (_contributors == null) ? (_contributors = new ArrayList()) : _contributors;
    }

    /**
     * Sets the DublinCore module contributors.
     * <p>
     * @param contributors the list of String representing the DublinCore
     * 		module contributors to set, an empty list or <b>null</b> if none.
     *
     */
    public void setContributors(List contributors) {
        _contributors = contributors;
    }

    /**
     * Gets the DublinCore module contributor. Convenience method that can be
     * used to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module contributor, <b>null</b> if none.
     */
    public String getContributor() {
        return ((_contributors != null) && (_contributors.size() > 0)) ?
                (String) _contributors.get(0) : null;
    }
    
    /**
     * Sets the DublinCore module contributor. Convenience method that can be
     * used when there is only one contributor to set.
     * <p>
     * @param contributor the DublinCore module contributor to set, <b>null</b> if none.
     *
     */
    public void setContributor(String contributor) {
        _contributors = new ArrayList();
        _contributors.add(contributor);
    }

    /**
     * Returns the DublinCore module date.
     * <p>
     * @return a list of Strings representing the DublinCore module date,
     *         an empty list if none.
     *
     */
    public List getDates() {
        return (_date == null) ? (_date = new ArrayList()) : _date;
    }

    /**
     * Sets the DublinCore module dates.
     * <p>
     * @param dates the list of Date representing the DublinCore module dates
     * 		to set, an empty list or <b>null</b> if none.
     *
     */
    public void setDates(List dates) {
        _date = dates;
    }
    
    /**
     * Gets the DublinCore module date. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module date, <b>null</b> if none.
     */
    public Date getDate() {
        return ((_date != null) && (_date.size() > 0)) ?
                (Date) _date.get(0) : null;
    }
    /**
     * Sets the DublinCore module date. Convenience method that can be used
     * when there is only one date to set.
     * <p>
     * @param date the DublinCore module date to set, <b>null</b> if none.
     *
     */
    public void setDate(Date date) {
        _date = new ArrayList();
        _date.add(date);
    }

    /**
     * Returns the DublinCore module type.
     * <p>
     * @return a list of Strings representing the DublinCore module type,
     *         an empty list if none.
     *
     */
    public List getTypes() {
        return (_type == null) ? (_type = new ArrayList()) : _type;
    }

    /**
     * Sets the DublinCore module types.
     * <p>
     * @param types the list of String representing the DublinCore module types
     * 		to set, an empty list or <b>null</b> if none.
     *
     */
    public void setTypes(List types) {
        _type = types;
    }
    
    /**
     * Gets the DublinCore module type. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module type, <b>null</b> if none.
     */
    public String getType() {
        return ((_type != null) && (_type.size() > 0)) ?
                (String) _type.get(0) : null;
    }
    
    /**
     * Sets the DublinCore module type. Convenience method that can be used
     * when there is only one type to set.
     * <p>
     * @param type the DublinCore module type to set, <b>null</b> if none.
     *
     */
    public void setType(String type) {
        _type = new ArrayList();
        _type.add(type);
    }

    /**
     * Returns the DublinCore module format.
     * <p>
     * @return a list of Strings representing the DublinCore module format,
     *         an empty list if none.
     *
     */
    public List getFormats() {
        return (_format == null) ? (_format = new ArrayList()) : _format;
    }

    /**
     * Sets the DublinCore module formats.
     * <p>
     * @param formats the list of String representing the DublinCore module
     * 		formats to set, an empty list or <b>null</b> if none.
     *
     */
    public void setFormats(List formats) {
        _format = formats;
    }

    /**
     * Gets the DublinCore module format. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module format, <b>null</b> if none.
     */
    public String getFormat() {
        return ((_format != null) && (_format.size() > 0)) ?
                (String) _format.get(0) : null;
    }

    /**
     * Sets the DublinCore module format. Convenience method that can be used
     * when there is only one format to set.
     * <p>
     * @param format the DublinCore module format to set, <b>null</b> if none.
     *
     */
    public void setFormat(String format) {
        _format = new ArrayList();
        _format.add(format);
    }

    /**
     * Returns the DublinCore module identifier.
     * <p>
     * @return a list of Strings representing the DublinCore module identifier,
     *         an empty list if none.
     *
     */
    public List getIdentifiers() {
        return (_identifier == null) ? (_identifier = new ArrayList()) : _identifier;
    }

    /**
     * Sets the DublinCore module identifiers.
     * <p>
     * @param identifiers the list of String representing the DublinCore module
     * 		identifiers to set, an empty list or <b>null</b> if none.
     *
     */
    public void setIdentifiers(List identifiers) {
        _identifier = identifiers;
    }
    
    /**
     * Gets the DublinCore module identifier. Convenience method that can be
     * used to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module identifier, <b>null</b> if none.
     */
    public String getIdentifier() {
        return ((_identifier != null) && (_identifier.size() > 0)) ?
                (String) _identifier.get(0) : null;
    }

    /**
     * Sets the DublinCore module identifier. Convenience method that can be
     * used when there is only one identifier to set.
     * <p>
     * @param identifier the DublinCore module identifier to set, <b>null</b> if none.
     *
     */
    public void setIdentifier(String identifier) {
        _identifier = new ArrayList();
        _identifier.add(identifier);
    }

    /**
     * Returns the DublinCore module source.
     * <p>
     * @return a list of Strings representing the DublinCore module source,
     *         an empty list if none.
     *
     */
    public List getSources() {
        return (_source == null) ? (_source = new ArrayList()) : _source;
    }

    /**
     * Sets the DublinCore module sources.
     * <p>
     * @param sources the list of String representing the DublinCore module
     * 		sources to set, an empty list or <b>null</b> if none.
     *
     */
    public void setSources(List sources) {
        _source = sources;
    }
    
    /**
     * Gets the DublinCore module source. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module source, <b>null</b> if none.
     */
    public String getSource() {
        return ((_source != null) && (_source.size() > 0)) ?
                (String) _source.get(0) : null;
    }

    /**
     * Sets the DublinCore module source. Convenience method that can be used
     * when there is only one source to set.
     * <p>
     * @param source the DublinCore module source to set, <b>null</b> if none.
     *
     */
    public void setSource(String source) {
        _source = new ArrayList();
        _source.add(source);
    }

    /**
     * Returns the DublinCore module language.
     * <p>
     * @return a list of Strings representing the DublinCore module language,
     *         an empty list if none.
     *
     */
    public List getLanguages() {
        return (_language == null) ? (_language = new ArrayList()) : _language;
    }

    /**
     * Sets the DublinCore module languages.
     * <p>
     * @param languages the list of String representing the DublinCore module
     * 		languages to set, an empty list or <b>null</b> if none.
     *
     */
    public void setLanguages(List languages) {
        _language = languages;
    }

    /**
     * Gets the DublinCore module language. Convenience method that can be
     * used to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module langauge, <b>null</b> if none.
     */
    public String getLanguage() {
        return ((_language != null) && (_language.size() > 0)) ?
                (String) _language.get(0) : null;
    }
    /**
     * Sets the DublinCore module language. Convenience method that can be used
     * when there is only one language to set.
     * <p>
     * @param language the DublinCore module language to set, <b>null</b> if none.
     *
     */
    public void setLanguage(String language) {
        _language = new ArrayList();
        _language.add(language);
    }

    /**
     * Returns the DublinCore module relation.
     * <p>
     * @return a list of Strings representing the DublinCore module relation,
     *         an empty list if none.
     *
     */
    public List getRelations() {
        return (_relation == null) ? (_relation = new ArrayList()) : _relation;
    }

    /**
     * Sets the DublinCore module relations.
     * <p>
     * @param relations the list of String representing the DublinCore module
     * 		relations to set, an empty list or <b>null</b> if none.
     *
     */
    public void setRelations(List relations) {
        _relation = relations;
    }

    /**
     * Gets the DublinCore module relation. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module relation, <b>null</b> if none.
     */
    public String getRelation() {
        return ((_relation != null) && (_relation.size() > 0)) ?
                (String) _relation.get(0) : null;
    }

    /**
     * Sets the DublinCore module relation. Convenience method that can be used
     * when there is only one relation to set.
     * <p>
     * @param relation the DublinCore module relation to set, <b>null</b> if none.
     *
     */
    public void setRelation(String relation) {
        _relation = new ArrayList();
        _relation.add(relation);
    }

    /**
     * Returns the DublinCore module coverage.
     * <p>
     * @return a list of Strings representing the DublinCore module coverage,
     *         an empty list if none.
     *
     */
    public List getCoverages() {
        return (_coverage == null) ? (_coverage = new ArrayList()) : _coverage;
    }

    /**
     * Sets the DublinCore module coverages.
     * <p>
     * @param coverages the list of String representing the DublinCore module
     * 		coverages to set, an empty list or <b>null</b> if none.
     *
     */
    public void setCoverages(List coverages) {
        _coverage = coverages;
    }

    /**
     * Gets the DublinCore module coverage. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module coverage, <b>null</b> if none.
     */
    public String getCoverage() {
        return ((_coverage != null) && (_coverage.size() > 0)) ?
                (String) _coverage.get(0) : null;
    }

    /**
     * Sets the DublinCore module coverage. Convenience method that can be used
     * when there is only one coverage to set.
     * <p>
     * @param coverage the DublinCore module coverage to set, <b>null</b> if none.
     *
     */
    public void setCoverage(String coverage) {
        _coverage = new ArrayList();
        _coverage.add(coverage);
    }

    /**
     * Returns the DublinCore module rights.
     * <p>
     * @return a list of Strings representing the DublinCore module rights,
     *         an empty list if none.
     *
     */
    public List getRightsList() {
        return (_rights == null) ? (_rights = new ArrayList()) : _rights;
    }

    /**
     * Sets the DublinCore module rights.
     * <p>
     * @param rights the list of String representing the DublinCore module
     * 		rights to set, an empty list or <b>null</b> if none.
     *
     */
    public void setRightsList(List rights) {
        _rights = rights;
    }
    
    /**
     * Gets the DublinCore module rights. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module rights, <b>null</b> if none.
     */
    public String getRights() {
        return ((_rights != null) && (_rights.size() > 0)) ?
                (String) _rights.get(0) : null;
    }
    
    /**
     * Sets the DublinCore module rights. Convenience method that can be used
     * when there is only one rights to set.
     * <p>
     * @param rights the DublinCore module rights to set, <b>null</b> if none.
     *
     */
    public void setRights(String rights) {
        _rights = new ArrayList();
        _rights.add(rights);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public final Object clone() throws CloneNotSupportedException {
        return _objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals() method.
     * <p>
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    public final boolean equals(Object other) {
        return _objBean.equals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * @return the hashcode of the bean object.
     *
     */
    public final int hashCode() {
        return _objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * @return String representation for the object.
     *
     */
    public final String toString() {
        return _objBean.toString();
    }
    
    public final Class getInterface() {
        return DCModule.class;
    }

    public final void copyFrom(Object obj) {
        COPY_FROM_HELPER.copy(this,obj);
    }

    private static final CopyFromHelper COPY_FROM_HELPER;
    
    static {
        Map basePropInterfaceMap = new HashMap();
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

        Map basePropClassImplMap = new HashMap();
        basePropClassImplMap.put(DCSubject.class,DCSubjectImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(DCModule.class,basePropInterfaceMap,basePropClassImplMap);
    }
}
