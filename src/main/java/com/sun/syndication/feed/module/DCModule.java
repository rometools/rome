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

import com.sun.syndication.feed.CopyFrom;

import java.util.List;
import java.util.Date;

/**
 * Dublin Core Module.
 * <p>
 * @see <a href="http://web.resource.org/rss/1.0/modules/dc/">Dublin Core module</a>.
 * @author Alejandro Abdelnur
 *
 */
public interface DCModule extends Module,CopyFrom {

    /**
     * URI of the Dublin Core Module (http://purl.org/dc/elements/1.1/).
     *
     */
    String URI = "http://purl.org/dc/elements/1.1/";

    /**
     * Returns the DublinCore module titles.
     * <p>
     * @return a list of Strings representing the DublinCore module title,
     *         an empty list if none.
     *
     */
    List getTitles();

    /**
     * Sets the DublinCore module titles.
     * <p>
     * @param titles the list of String representing the DublinCore module titles
     *        to set, an empty list or <b>null</b> if none.
     *
     */
    void setTitles(List titles);

    /**
     * Gets the DublinCore module title. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module title, <b>null</b> if none.
     */
    String getTitle();

    /**
     * Sets the DublinCore module title. Convenience method that can be used
     * when there is only one title to set.
     * <p>
     * @param title the DublinCore module title to set, <b>null</b> if none.
     *
     */
    void setTitle(String title);

    /**
     * Returns the DublinCore module creator.
     * <p>
     * @return a list of Strings representing the DublinCore module creator,
     *         an empty list if none.
     *
     */
    List getCreators();

    /**
     * Sets the DublinCore module creators.
     * <p>
     * @param creators the list of String representing the DublinCore module
     *        creators to set, an empty list or <b>null</b> if none.
     *
     */
    void setCreators(List creators);

    /**
     * Gets the DublinCore module creator. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module creator, <b>null</b> if none.
     */
    String getCreator();
    
    /**
     * Sets the DublinCore module creator. Convenience method that can be used
     * when there is only one creator to set.
     * <p>
     * @param creator the DublinCore module creator to set, <b>null</b> if none.
     *
     */
    void setCreator(String creator);

    /**
     * Returns the DublinCore module subjects.
     * <p>
     * @return a list of DCSubject elements with the DublinCore module subjects,
     *         an empty list if none.
     *
     */
    List getSubjects();

    /**
     * Sets the DublinCore module subjects.
     * <p>
     * @param subjects the list of DCSubject elements with the DublinCore 
     * 	      module subjects to set, an empty list or <b>null</b> if none.
     *
     */
    void setSubjects(List subjects);

    /**
     * Gets the DublinCore module subject. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module subject, <b>null</b> if none.
     */
    DCSubject getSubject();

    /**
     * Sets the DCSubject element. Convenience method that can be used when
     * there is only one subject to set.
     * <p>
     * @param subject the DublinCore module subject to set, <b>null</b> if none.
     *
     */
    void setSubject(DCSubject subject);

    /**
     * Returns the DublinCore module description.
     * <p>
     * @return a list of Strings representing the DublinCore module description,
     *         an empty list if none.
     *
     */
    List getDescriptions();

    /**
     * Sets the DublinCore module descriptions.
     * <p>
     * @param descriptions the list of String representing the DublinCore
     * 	     module descriptions to set, an empty list or <b>null</b> if none.
     *
     */
    void setDescriptions(List descriptions);

    /**
     * Gets the DublinCore module description. Convenience method that can be
     * used to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module description, <b>null</b> if none.
     */
    String getDescription();

    /**
     * Sets the DublinCore module description. Convenience method that can be
     * used when there is only one description to set.
     * <p>
     * @param description the DublinCore module description to set, <b>null</b> if none.
     *
     */
    void setDescription(String description);

    /**
     * Returns the DublinCore module publisher.
     * <p>
     * @return a list of Strings representing the DublinCore module publisher,
     *         an empty list if none.
     *
     */
    List getPublishers();

    /**
     * Sets the DublinCore module publishers.
     * <p>
     * @param publishers the list of String representing the DublinCore module
     * 		publishers to set, an empty list or <b>null</b> if none.
     *
     */
    void setPublishers(List publishers);

    /**
     * Gets the DublinCore module publisher. Convenience method that can be
     * used to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module publisher, <b>null</b> if none.
     */
    String getPublisher();

    /**
     * Sets the DublinCore module publisher. Convenience method that can be used when
     * there is only one publisher to set.
     * <p>
     * @param publisher the DublinCore module publisher to set, <b>null</b> if none.
     *
     */
    void setPublisher(String publisher);

    /**
     * Returns the DublinCore module contributor.
     * <p>
     * @return a list of Strings representing the DublinCore module contributor,
     *         an empty list if none.
     *
     */
    List getContributors();

    /**
     * Sets the DublinCore module contributors.
     * <p>
     * @param contributors the list of String representing the DublinCore module
     * 	     contributors to set, an empty list or <b>null</b> if none.
     *
     */
    void setContributors(List contributors);

    /**
     * Gets the DublinCore module contributor. Convenience method that can be
     * used to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module contributor, <b>null</b> if none.
     */
    String getContributor();

    /**
     * Sets the DublinCore module contributor. Convenience method that can be
     * used when there is only one contributor to set.
     * <p>
     * @param contributor the DublinCore module contributor to set, <b>null</b> if none.
     *
     */
    void setContributor(String contributor);

    /**
     * Returns the DublinCore module date.
     * <p>
     * @return a list of Strings representing the DublinCore module date,
     *         an empty list if none.
     *
     */
    List getDates();

    /**
     * Sets the DublinCore module dates.
     * <p>
     * @param dates the list of Date representing the DublinCore module dates to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    void setDates(List dates);

    /**
     * Gets the DublinCore module date. Convenience method that can be used to
     * obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module date, <b>null</b> if none.
     */
    Date getDate();

    /**
     * Sets the DublinCore module date. Convenience method that can be used
     * when there is only one date to set.
     * <p>
     * @param date the DublinCore module date to set, <b>null</b> if none.
     *
     */
    void setDate(Date date);

    /**
     * Returns the DublinCore module type.
     * <p>
     * @return a list of Strings representing the DublinCore module type,
     *         an empty list if none.
     *
     */
    List getTypes();

    /**
     * Sets the DublinCore module types.
     * <p>
     * @param types the list of String representing the DublinCore module types to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    void setTypes(List types);

    /**
     * Gets the DublinCore module type. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module type, <b>null</b> if none.
     */
    String getType();

    /**
     * Sets the DublinCore module type. Convenience method that can be used
     * when there is only one type to set.
     * <p>
     * @param type the DublinCore module type to set, <b>null</b> if none.
     *
     */
    void setType(String type);

    /**
     * Returns the DublinCore module format.
     * <p>
     * @return a list of Strings representing the DublinCore module format,
     *         an empty list if none.
     *
     */
    List getFormats();

    /**
     * Sets the DublinCore module formats.
     * <p>
     * @param formats the list of String representing the DublinCore module
     * 		formats to set, an empty list or <b>null</b> if none.
     *
     */
    void setFormats(List formats);

    /**
     * Gets the DublinCore module format. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module format, <b>null</b> if none.
     */
    String getFormat();

    /**
     * Sets the DublinCore module format. Convenience method that can be used
     * when there is only one format to set.
     * <p>
     * @param format the DublinCore module format to set, <b>null</b> if none.
     *
     */
    void setFormat(String format);

    /**
     * Returns the DublinCore module identifier.
     * <p>
     * @return a list of Strings representing the DublinCore module identifier,
     *         an empty list if none.
     *
     */
    List getIdentifiers();

    /**
     * Sets the DublinCore module identifiers.
     * <p>
     * @param identifiers the list of String representing the DublinCore module
     * 	      identifiers to set, an empty list or <b>null</b> if none.
     *
     */
    void setIdentifiers(List identifiers);

    /**
     * Gets the DublinCore module identifier. Convenience method that can be
     * used to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module identifier, <b>null</b> if none.
     */
    String getIdentifier();

    /**
     * Sets the DublinCore module identifier. Convenience method that can be
     * used when there is only one identifier to set.
     * <p>
     * @param identifier the DublinCore module identifier to set, <b>null</b> if none.
     *
     */
    void setIdentifier(String identifier);

    /**
     * Returns the DublinCore module source.
     * <p>
     * @return a list of Strings representing the DublinCore module source,
     *         an empty list if none.
     *
     */
    List getSources();

    /**
     * Sets the DublinCore module sources.
     * <p>
     * @param sources the list of String representing the DublinCore module
     * 	     sources to set, an empty list or <b>null</b> if none.
     *
     */
    void setSources(List sources);

    /**
     * Gets the DublinCore module subject. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module creator, <b>null</b> if none.
     */
    String getSource();

    /**
     * Sets the DublinCore module source. Convenience method that can be used
     * when there is only one source to set.
     * <p>
     * @param source the DublinCore module source to set, <b>null</b> if none.
     *
     */
    void setSource(String source);

    /**
     * Returns the DublinCore module language.
     * <p>
     * @return a list of Strings representing the DublinCore module language,
     *         an empty list if none.
     *
     */
    List getLanguages();

    /**
     * Sets the DublinCore module languages.
     * <p>
     * @param languages the list of String representing the DublinCore module
     * 	     languages to set, an empty list or <b>null</b> if none.
     *
     */
    void setLanguages(List languages);

    /**
     * Gets the DublinCore module language. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module language, <b>null</b> if none.
     */
    String getLanguage();

    /**
     * Sets the DublinCore module language. Convenience method that can be used
     * when there is only one language to set.
     * <p>
     * @param language the DublinCore module language to set, <b>null</b> if none.
     *
     */
    void setLanguage(String language);

    /**
     * Returns the DublinCore module relation.
     * <p>
     * @return a list of Strings representing the DublinCore module relation,
     *         an empty list if none.
     *
     */
    List getRelations();

    /**
     * Sets the DublinCore module relations.
     * <p>
     * @param relations the list of String representing the DublinCore module
     * 	     relations to set, an empty list or <b>null</b> if none.
     *
     */
    void setRelations(List relations);

    /**
     * Gets the DublinCore module relation. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module relation, <b>null</b> if none.
     */
    String getRelation();

    /**
     * Sets the DublinCore module relation. Convenience method that can be used
     * when there is only one relation to set.
     * <p>
     * @param relation the DublinCore module relation to set, <b>null</b> if none.
     *
     */
    void setRelation(String relation);

    /**
     * Returns the DublinCore module coverage.
     * <p>
     * @return a list of Strings representing the DublinCore module coverage,
     *         an empty list if none.
     *
     */
    List getCoverages();

    /**
     * Sets the DublinCore module coverages.
     * <p>
     * @param coverages the list of String representing the DublinCore module
     * 	     coverages to set, an empty list or <b>null</b> if none.
     *
     */
    void setCoverages(List coverages);

    /**
     * Gets the DublinCore module coverage. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module coverage, <b>null</b> if none.
     */
    String getCoverage();

    /**
     * Sets the DublinCore module coverage. Convenience method that can be used
     * when there is only one coverage to set.
     * <p>
     * @param coverage the DublinCore module coverage to set, <b>null</b> if none.
     *
     */
    void setCoverage(String coverage);

    /**
     * Returns the DublinCore module rights.
     * <p>
     * @return a list of Strings representing the DublinCore module rights,
     *         an empty list if none.
     *
     */
    List getRightsList();

    /**
     * Sets the DublinCore module rightss.
     * <p>
     * @param rights the list of String representing the DublinCore module
     * 	     rights to set, an empty list or <b>null</b> if none.
     *
     */
    void setRightsList(List rights);

    /**
     * Gets the DublinCore module right. Convenience method that can be used
     * to obtain the first item, <b>null</b> if none.
     * <p>
     * @return the first DublinCore module right, <b>null</b> if none.
     */
    String getRights();

    /**
     * Sets the DublinCore module rights. Convenience method that can be used
     * when there is only one rights to set.
     * <p>
     * @param rights the DublinCore module rights to set, <b>null</b> if none.
     *
     */
    void setRights(String rights);
}
