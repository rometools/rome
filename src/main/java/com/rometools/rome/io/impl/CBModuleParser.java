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
package com.rometools.rome.io.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.module.CBModule;
import com.rometools.rome.feed.module.CBModuleImpl;
import com.rometools.rome.feed.module.CBEvent;
import com.rometools.rome.feed.module.CBEventImpl;
import com.rometools.rome.feed.module.CBNews;
import com.rometools.rome.feed.module.CBNewsImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;

/**
 * Parser for the CB RSS module.
 */
public class CBModuleParser implements ModuleParser {

    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    private static final Namespace CB_NS = Namespace.getNamespace(CBModule.URI);
    private static final Namespace RDF_NS = Namespace.getNamespace(RDF_URI);

    @Override
    public final String getNamespaceUri() {
        return CBModule.URI;
    }

    private final Namespace getCBNamespace() {
        return CB_NS;
    }

    private final Namespace getRDFNamespace() {
        return RDF_NS;
    }

    /**
     * Parse an element tree and return the module found in it.
     * <p>
     *
     * @param cbRoot the root element containing the module elements.
     * @param locale for date/time parsing
     * @return the module parsed from the element tree, <i>null</i> if none.
     */
    @Override
    public Module parse(final Element cbRoot, final Locale locale) {

        boolean foundSomething = false;
        final CBModule cbm = new CBModuleImpl();

        final Element event = cbRoot.getChild("event", getCBNamespace());
        if (event != null) {
            foundSomething = true;
            cbm.setEvent(parseEvent(event));
        }

        final Element news = cbRoot.getChild("news", getCBNamespace());
        if (news != null) {
            foundSomething = true;
            cbm.setNews(parseNews(news));
        }

        // final Element paper = cbRoot.getChild("paper", getCBNamespace());
        // if (paper != null) {
        //     foundSomething = true;
        //     cbm.setPaper(parsePaper(paper));
        // }

        // final Element speech = cbRoot.getChild("speech", getCBNamespace());
        // if (speech != null) {
        //     foundSomething = true;
        //     cbm.setSpeech(parseSpeech(speech));
        // }

        // final Element statistics = cbRoot.getChild("statistics", getCBNamespace());
        // if (statistics != null) {
        //     foundSomething = true;
        //     cbm.setStatistics(parseStatistics(statistics));
        // }

        if (foundSomething) {
            return cbm;
        } else {
            return null;
        }

    }

    /**
     * Utility method to parse an event.
     * <p>
     *
     * @param the element to parse.
     * @return event parsed from the element.
     */
    protected final CBEvent parseEvent(final Element element) {

        final CBEvent event = new CBEventImpl();

		final Element simpleTitle = element.getChild("simpleTitle", getCBNamespace());
		if (simpleTitle != null) {
			event.setSimpleTitle(simpleTitle.getText());
		}

		final Element occurenceDate = element.getChild("occurenceDate", getCBNamespace());
		if (occurenceDate != null) {
			event.setOccurenceDate(occurenceDate.getText());
		}

		final Element institutionAbbrev = element.getChild("institutionAbbrev", getCBNamespace());
		if (institutionAbbrev != null) {
			event.setInstitutionAbbrev(institutionAbbrev.getText());
		}

		final Element audience = element.getChild("audience", getCBNamespace());
		if (audience != null) {
			event.setAudience(audience.getText());
		}

		final Element keyword = element.getChild("keyword", getCBNamespace());
		if (keyword != null) {
			event.setKeyword(keyword.getText());
		}

		// final Element resource = element.getChild("resource", getCBNamespace());
		// if (resource != null) {
		// 	event.setResource(resource.getText());
		// }

		// final Element person = element.getChild("person", getCBNamespace());
		// if (person != null) {
		// 	event.setPerson(person.getText());
		// }

		final Element venue = element.getChild("venue", getCBNamespace());
		if (venue != null) {
			event.setVenue(venue.getText());
		}

		final Element locationAsWritten = element.getChild("locationAsWritten", getCBNamespace());
		if (locationAsWritten != null) {
			event.setLocationAsWritten(locationAsWritten.getText());
		}

		final Element locationCountry = element.getChild("locationCountry", getCBNamespace());
		if (locationCountry != null) {
			event.setLocationCountry(locationCountry.getText());
		}

		final Element locationState = element.getChild("locationState", getCBNamespace());
		if (locationState != null) {
			event.setLocationState(locationState.getText());
		}

		final Element locationCity = element.getChild("locationCity", getCBNamespace());
		if (locationCity != null) {
			event.setLocationCity(locationCity.getText());
		}

		final Element eventDateEnd = element.getChild("eventDateEnd", getCBNamespace());
		if (eventDateEnd != null) {
			event.setEventDateEnd(eventDateEnd.getText());
		}

        return event;
    }

    /**
     * Utility method to parse an news.
     * <p>
     *
     * @param the element to parse.
     * @return news parsed from the element.
     */
    protected final CBNews parseNews(final Element element) {

        final CBNews news = new CBNewsImpl();

		final Element simpleTitle = element.getChild("simpleTitle", getCBNamespace());
		if (simpleTitle != null) {
			news.setSimpleTitle(simpleTitle.getText());
		}

		final Element occurenceDate = element.getChild("occurenceDate", getCBNamespace());
		if (occurenceDate != null) {
			news.setOccurenceDate(occurenceDate.getText());
		}

		final Element institutionAbbrev = element.getChild("institutionAbbrev", getCBNamespace());
		if (institutionAbbrev != null) {
			news.setInstitutionAbbrev(institutionAbbrev.getText());
		}

		final Element keyword = element.getChild("keyword", getCBNamespace());
		if (keyword != null) {
			news.setKeyword(keyword.getText());
		}

		// final Element resource = element.getChild("resource", getCBNamespace());
		// if (resource != null) {
		// 	news.setResource(resource.getText());
		// }

		// final Element person = element.getChild("person", getCBNamespace());
		// if (person != null) {
		// 	news.setPerson(person.getText());
		// }

        return news;
    }

}
