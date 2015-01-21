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
import java.util.List;
import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.module.CBEvent;
import com.rometools.rome.feed.module.CBEventImpl;
import com.rometools.rome.feed.module.CBExchangeRate;
import com.rometools.rome.feed.module.CBExchangeRateImpl;
import com.rometools.rome.feed.module.CBInterestRate;
import com.rometools.rome.feed.module.CBInterestRateImpl;
import com.rometools.rome.feed.module.CBModule;
import com.rometools.rome.feed.module.CBModuleImpl;
import com.rometools.rome.feed.module.CBNews;
import com.rometools.rome.feed.module.CBNewsImpl;
import com.rometools.rome.feed.module.CBObservation;
import com.rometools.rome.feed.module.CBObservationImpl;
import com.rometools.rome.feed.module.CBObservationPeriod;
import com.rometools.rome.feed.module.CBObservationPeriodImpl;
import com.rometools.rome.feed.module.CBOtherStatistics;
import com.rometools.rome.feed.module.CBOtherStatisticsImpl;
import com.rometools.rome.feed.module.CBPaper;
import com.rometools.rome.feed.module.CBPaperImpl;
import com.rometools.rome.feed.module.CBPerson;
import com.rometools.rome.feed.module.CBPersonImpl;
import com.rometools.rome.feed.module.CBResource;
import com.rometools.rome.feed.module.CBResourceImpl;
import com.rometools.rome.feed.module.CBRole;
import com.rometools.rome.feed.module.CBRoleImpl;
import com.rometools.rome.feed.module.CBSpeech;
import com.rometools.rome.feed.module.CBSpeechImpl;
import com.rometools.rome.feed.module.CBStatistics;
import com.rometools.rome.feed.module.CBStatisticsImpl;
import com.rometools.rome.feed.module.CBTransaction;
import com.rometools.rome.feed.module.CBTransactionImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ISO3166CountyCode;
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

         final Element paper = cbRoot.getChild("paper", getCBNamespace());
         if (paper != null) {
             foundSomething = true;
             cbm.setPaper(parsePaper(paper));
         }

         final Element speech = cbRoot.getChild("speech", getCBNamespace());
         if (speech != null) {
             foundSomething = true;
             cbm.setSpeech(parseSpeech(speech));
         }

         final Element statistics = cbRoot.getChild("statistics", getCBNamespace());
         if (statistics != null) {
             foundSomething = true;
             cbm.setStatistics(parseStatistics(statistics));
         }

        if (foundSomething) {
            return cbm;
        } else {
            return null;
        }
    }
    
    /**
     * Utility method to parse a statistics.
     * <p>
     *
     * @param the element to parse.
     * @return statistics parsed from the element.
     */
    protected final CBStatistics parseStatistics(final Element element) {
    	final CBStatistics statistics = new CBStatisticsImpl();
    	
    	final Element institutionAbbrev = element.getChild("institutionAbbrev", getCBNamespace());
		if (institutionAbbrev != null) {
			statistics.setInstitutionAbbrev(institutionAbbrev.getText());
		}
		
		final Element country = element.getChild("country", getCBNamespace());
		if (country != null) {
			statistics.setLocationCountry(ISO3166CountyCode.fromValue(country.getText()));
		}
		
		final Element exchangeRate = element.getChild("exchangeRate", getCBNamespace());
		if (exchangeRate != null) {
			statistics.setExchangeRate(parseExchangeRate(exchangeRate));
		}
		
		final Element interestRate = element.getChild("interestRate", getCBNamespace());
		if (interestRate != null) {
			statistics.setInterestRate(parseInterestRate(interestRate));
		}
		
		final Element transaction = element.getChild("transaction", getCBNamespace());
		if (transaction != null) {
			statistics.setTransaction(parseTransaction(transaction));
		}
		
		final Element otherStatistic = element.getChild("otherStatistic", getCBNamespace());
		if (otherStatistic != null) {
			statistics.setOtherStatistic(parseOtherStatistic(otherStatistic));
		}
		
    	return statistics;
    }
    
    /**
     * Utility method to parse a exchangeRate.
     * <p>
     *
     * @param the element to parse.
     * @return exchangeRate parsed from the element.
     */
    protected final CBExchangeRate parseExchangeRate(final Element element) {
    	final CBExchangeRate exchangeRate = new CBExchangeRateImpl();
    	
    	final Element observation = element.getChild("observation", getCBNamespace());
		if (observation != null) {
			exchangeRate.setObservation(parseObservation(observation));
		}
    	
    	final Element baseCurrency = element.getChild("baseCurrency", getCBNamespace());
		if (baseCurrency != null) {
			exchangeRate.setBaseCurrency(baseCurrency.getText());
		}
		
    	final Element targetCurrency = element.getChild("targetCurrency", getCBNamespace());
		if (targetCurrency != null) {
			exchangeRate.setTargetCurrency(targetCurrency.getText());
		}
		
		final Element rateType = element.getChild("rateType", getCBNamespace());
		if (rateType != null) {
			exchangeRate.setRateType(rateType.getText());
		}
		
		final Element observationPeriod = element.getChild("observationPeriod", getCBNamespace());
		if (observationPeriod != null) {
			exchangeRate.setObservationPeriod(parseObservationPeriod(observationPeriod));
		}
		
    	return exchangeRate;
    }
    
    /**
     * Utility method to parse a interestRate.
     * <p>
     *
     * @param the element to parse.
     * @return interestRate parsed from the element.
     */
    protected final CBInterestRate parseInterestRate(final Element element) {
    	final CBInterestRate interestRate = new CBInterestRateImpl();
    	
    	final Element observation = element.getChild("observation", getCBNamespace());
		if (observation != null) {
			interestRate.setObservation(parseObservation(observation));
		}
    	
    	final Element rateName = element.getChild("rateName", getCBNamespace());
		if (rateName != null) {
			interestRate.setRateName(rateName.getText());
		}
		
    	final Element rateType = element.getChild("rateType", getCBNamespace());
		if (rateType != null) {
			interestRate.setRateType(rateType.getText());
		}
		
		final Element observationPeriod = element.getChild("observationPeriod", getCBNamespace());
		if (observationPeriod != null) {
			interestRate.setObservationPeriod(parseObservationPeriod(observationPeriod));
		}
		
    	return interestRate;
    }
    
    /**
     * Utility method to parse a transaction.
     * <p>
     *
     * @param the element to parse.
     * @return transaction parsed from the element.
     */
    protected final CBTransaction parseTransaction(final Element element) {
    	final CBTransaction transaction = new CBTransactionImpl();
    	
    	final Element observation = element.getChild("observation", getCBNamespace());
		if (observation != null) {
			transaction.setObservation(parseObservation(observation));
		}
    	
    	final Element transactionName = element.getChild("transactionName", getCBNamespace());
		if (transactionName != null) {
			transaction.setTransactionName(transactionName.getText());
		}
		
    	final Element transactionType = element.getChild("transactionType", getCBNamespace());
		if (transactionType != null) {
			transaction.setTransactionType(transactionType.getText());
		}
		
		final Element observationPeriod = element.getChild("observationPeriod", getCBNamespace());
		if (observationPeriod != null) {
			transaction.setObservationPeriod(parseObservationPeriod(observationPeriod));
		}
		
		final Element transactionTerm = element.getChild("transactionTerm", getCBNamespace());
		if (transactionTerm != null) {
			transaction.setTransactionTerm(transactionTerm.getText());
		}
		
    	return transaction;
    }
    
    /**
     * Utility method to parse a otherStatistic.
     * <p>
     *
     * @param the element to parse.
     * @return otherStatistic parsed from the element.
     */
    protected final CBOtherStatistics parseOtherStatistic(final Element element) {
    	final CBOtherStatistics otherStatistic = new CBOtherStatisticsImpl();
    	
    	final Element observation = element.getChild("observation", getCBNamespace());
		if (observation != null) {
			otherStatistic.setObservation(parseObservation(observation));
		}
		
		final Element topic = element.getChild("topic", getCBNamespace());
		if (topic != null) {
			otherStatistic.setTopic(topic.getText());
		}
		
		final Element coverage = element.getChild("coverage", getCBNamespace());
		if (coverage != null) {
			otherStatistic.setCoverage(coverage.getText());
		}
		
		final Element dataType = element.getChild("dataType", getCBNamespace());
		if (dataType != null) {
			otherStatistic.setDataType(dataType.getText());
		}
		
		final Element observationPeriod = element.getChild("observationPeriod", getCBNamespace());
		if (observationPeriod != null) {
			otherStatistic.setObservationPeriod(parseObservationPeriod(observationPeriod));
		}
		
    	return otherStatistic;
    }
    
    /**
     * Utility method to parse a observationPeriod.
     * <p>
     *
     * @param the element to parse.
     * @return observationPeriod parsed from the element.
     */
    protected final CBObservationPeriod parseObservationPeriod(final Element element) {
    	final CBObservationPeriod observationPeriod = new CBObservationPeriodImpl();
    	
    	final Element frequency = element.getChild("frequency", getCBNamespace());
		if (frequency != null) {
			observationPeriod.setFrequency(frequency.getText());
		}
		
    	final Element period = element.getChild("period", getCBNamespace());
		if (period != null) {
			observationPeriod.setPeriod(period.getText());
		}
		
    	return observationPeriod;
    }
    
    /**
     * Utility method to parse a observation.
     * <p>
     *
     * @param the element to parse.
     * @return observation parsed from the element.
     */
    protected final CBObservation parseObservation(final Element element) {
    	final CBObservation observation = new CBObservationImpl();
    	
    	final Element value = element.getChild("value", getCBNamespace());
		if (value != null) {
			observation.setValue(value.getText());
		}
		
    	final Element unit = element.getChild("unit", getCBNamespace());
		if (unit != null) {
			observation.setUnit(unit.getText());
		}

		final Element unit_mult = element.getChild("unit_mult", getCBNamespace());
		if (unit_mult != null) {
			observation.setUnitMult(unit_mult.getText());
		}
		
		final Element decimals = element.getChild("decimals", getCBNamespace());
		if (decimals != null) {
			observation.setDecimal(decimals.getText());
		}
		
    	return observation;
    }
    
    /**
     * Utility method to parse a speech.
     * <p>
     *
     * @param the element to parse.
     * @return speech parsed from the element.
     */
    protected final CBSpeech parseSpeech(final Element element) {
    	final CBSpeech speech = new CBSpeechImpl();
    	
    	final Element simpleTitle = element.getChild("simpleTitle", getCBNamespace());
		if (simpleTitle != null) {
			speech.setSimpleTitle(simpleTitle.getText());
		}
		
		final Element occurrenceDate = element.getChild("occurrenceDate", getCBNamespace());
		if (occurrenceDate != null) {
			speech.setOccurrenceDate(occurrenceDate.getText());
		}
		
		final Element institutionAbbrev = element.getChild("institutionAbbrev", getCBNamespace());
		if (institutionAbbrev != null) {
			speech.setInstitutionAbbrev(institutionAbbrev.getText());
		}
		
		final Element audience = element.getChild("audience", getCBNamespace());
		if (audience != null) {
			speech.setAudience(audience.getText());
		}
		
		final List<Element> keyList = element.getChildren("keyword", getCBNamespace());
		for(Element keyword : keyList){
			speech.getKeyword().add(keyword.getText());
		}
		
		final Element resource = element.getChild("resource", getCBNamespace());
		if (resource != null) {
			speech.setResource(parseResource(resource));
		}
		
		final Element person = element.getChild("person", getCBNamespace());
		if (person != null) {
			speech.setPerson(parsePerson(person));
		}
		
		final Element venue = element.getChild("venue", getCBNamespace());
		if (venue != null) {
			speech.setVenue(venue.getText());
		}
		
		final Element locationAsWritten = element.getChild("locationAsWritten", getCBNamespace());
		if (locationAsWritten != null) {
			speech.setLocationAsWritten(locationAsWritten.getText());
		}
		
		final Element locationCountry = element.getChild("locationCountry", getCBNamespace());
		if (locationCountry != null) {
			speech.setLocationCountry(ISO3166CountyCode.fromValue(locationCountry.getText()));
		}
		
		final Element locationState = element.getChild("locationState", getCBNamespace());
		if (locationState != null) {
			speech.setLocationState(locationState.getText());
		}
		
		final Element locationCity = element.getChild("locationCity", getCBNamespace());
		if (locationCity != null) {
			speech.setLocationCity(locationCity.getText());
		}
		
    	return speech;
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

		final List<Element> keyList = element.getChildren("keyword", getCBNamespace());
		for(Element keyword : keyList){
			event.getKeyword().add(keyword.getText());
		}

		final Element resource = element.getChild("resource", getCBNamespace());
		if (resource != null) {
		 	event.setResource(parseResource(resource));
		}

		final Element person = element.getChild("person", getCBNamespace());
		if (person != null) {
		 	event.setPerson(parsePerson(person));
		}

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
     * Utility method to parse a paper.
     * <p>
     *
     * @param the element to parse.
     * @return paper parsed from the element.
     */
    protected final CBPaper parsePaper(final Element element) {
    	final CBPaper paperObj = new CBPaperImpl();
    	
    	final Element simpleTitle = element.getChild("simpleTitle", getCBNamespace());
		if (simpleTitle != null) {
			paperObj.setSimpleTitle(simpleTitle.getText());
		}
		
    	final Element occurrenceDate = element.getChild("occurrenceDate", getCBNamespace());
		if (occurrenceDate != null) {
			paperObj.setOccurrenceDate(occurrenceDate.getText());
		}
		
		final Element institutionAbbrev = element.getChild("institutionAbbrev", getCBNamespace());
		if (institutionAbbrev != null) {
			paperObj.setInstitutionAbbrev(institutionAbbrev.getText());
		}
		
		final List<Element> keyList = element.getChildren("keyword", getCBNamespace());
		List<String> kList = new ArrayList<String>();
		for(Element keyword : keyList){
			kList.add(keyword.getText());
		}
		paperObj.setKeyword(kList);
		
		final Element resource = element.getChild("resource", getCBNamespace());
		if (resource != null) {
			paperObj.setResource(parseResource(resource));
		}
		
		final Element person = element.getChild("person", getCBNamespace());
		if (person != null) {
			paperObj.setPerson(parsePerson(person));
		}
		
		final Element byline = element.getChild("byline", getCBNamespace());
		if (byline != null) {
			paperObj.setByline(byline.getText());
		}
		
		final Element publicationDate = element.getChild("publicationDate", getCBNamespace());
		if (publicationDate != null) {
			paperObj.setPublicationDate(publicationDate.getText());
		}
		
		final Element publication = element.getChild("publication", getCBNamespace());
		if (publication != null) {
			paperObj.setPublication(publication.getText());
		}
		
		final Element issue = element.getChild("issue", getCBNamespace());
		if (issue != null) {
			paperObj.setIssue(issue.getText());
		}
		
		final Element JELCode = element.getChild("JELCode", getCBNamespace());
		if (JELCode != null) {
			paperObj.setJELCode(JELCode.getText());
		}
    	
    	return paperObj;
    }
    
    /**
     * Utility method to parse a resource.
     * <p>
     *
     * @param the element to parse.
     * @return resource parsed from the element.
     */
    protected final CBResource parseResource(final Element element) {
    	final CBResource resource = new CBResourceImpl();
    	
    	final Element title = element.getChild("title", getCBNamespace());
		if (title != null) {
			resource.setTitle(title.getText());
		}
		
		final Element link = element.getChild("link", getCBNamespace());
		if (link != null) {
			resource.setLink(link.getText());
		}
		
		final Element description = element.getChild("description", getCBNamespace());
		if (description != null) {
			resource.setDescription(description.getText());
		}
    	
    	return resource;
    }
    
    /**
     * Utility method to parse a person.
     * <p>
     *
     * @param the element to parse.
     * @return person parsed from the element.
     */
    protected final CBPerson parsePerson(final Element element) {
    	final CBPerson person = new CBPersonImpl();
    	
    	final Element givenName = element.getChild("givenName", getCBNamespace());
		if (givenName != null) {
			person.setGivenName(givenName.getText());
		}
		
		final Element surname = element.getChild("surname", getCBNamespace());
		if (surname != null) {
			person.setSurname(surname.getText());
		}
		
		final Element personalTitle = element.getChild("personalTitle", getCBNamespace());
		if (personalTitle != null) {
			person.setPersonalTitle(personalTitle.getText());
		}
		
		final Element nameAsWritten = element.getChild("nameAsWritten", getCBNamespace());
		if (nameAsWritten != null) {
			person.setNameAsWritten(nameAsWritten.getText());
		}
		
		final Element role = element.getChild("role", getCBNamespace());
		if (nameAsWritten != null) {
			person.setRole(parseRole(role));
		}
    	
    	return person;
    }
    
    /**
     * Utility method to parse a role.
     * <p>
     *
     * @param the element to parse.
     * @return role parsed from the element.
     */
    protected final CBRole parseRole(final Element element) {
    	final CBRole role = new CBRoleImpl();
    	
    	final Element jobTitle = element.getChild("jobTitle", getCBNamespace());
		if (jobTitle != null) {
			role.setJobTitle(jobTitle.getText());
		}
		
    	final Element affiliation = element.getChild("affiliation", getCBNamespace());
		if (affiliation != null) {
			role.setAffiliation(affiliation.getText());
		}
    	
    	return role;
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

		final List<Element> keyList = element.getChildren("keyword", getCBNamespace());
		for(Element keyword : keyList){
			news.getKeyword().add(keyword.getText());
		}

		 final Element resource = element.getChild("resource", getCBNamespace());
		 if (resource != null) {
		 	news.setResource(parseResource(resource));
		 }

		 final Element person = element.getChild("person", getCBNamespace());
		 if (person != null) {
		 	news.setPerson(parsePerson(person));
		 }

        return news;
    }

}
