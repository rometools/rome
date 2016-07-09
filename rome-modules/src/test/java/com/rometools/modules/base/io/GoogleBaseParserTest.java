/*
 * GoogleBaseParserTest.java
 * JUnit based test
 *
 * Created on November 17, 2005, 4:49 PM
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
package com.rometools.modules.base.io;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.base.Article;
import com.rometools.modules.base.Course;
import com.rometools.modules.base.Event;
import com.rometools.modules.base.GoogleBase;
import com.rometools.modules.base.Housing;
import com.rometools.modules.base.Job;
import com.rometools.modules.base.Person;
import com.rometools.modules.base.Product;
import com.rometools.modules.base.Review;
import com.rometools.modules.base.ScholarlyArticle;
import com.rometools.modules.base.Service;
import com.rometools.modules.base.Travel;
import com.rometools.modules.base.Vehicle;
import com.rometools.modules.base.Wanted;
import com.rometools.modules.base.io.GoogleBaseParser;
import com.rometools.modules.base.types.CurrencyEnumeration;
import com.rometools.modules.base.types.FloatUnit;
import com.rometools.modules.base.types.GenderEnumeration;
import com.rometools.modules.base.types.PaymentTypeEnumeration;
import com.rometools.modules.base.types.PriceTypeEnumeration;
import com.rometools.modules.base.types.ShippingType;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;

public class GoogleBaseParserTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleBaseParserTest.class);

    public GoogleBaseParserTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(GoogleBaseParserTest.class);

        return suite;
    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testCourse2Parse() throws Exception {
        LOG.debug("testCourse2Parse");
        final SyndFeedInput input = new SyndFeedInput();

        final SyndFeed feed = input.build(new File(super.getTestFile("xml/courses2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        SyndEntry entry = entries.get(0);
        Course course = (Course) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", course.getImageLinks()[0].toString());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2005, 10, 30, 0, 0, 0);
        Assert.assertEquals("Course Date", cal.getTime(), course.getExpirationDate());
        String[] labels = new String[] { "robots", "society", "computers" };
        this.assertEquals("Labels", labels, course.getLabels());
        cal.set(2005, 7, 19, 8, 30, 00);
        Assert.assertEquals("Start Time", cal.getTime(), course.getCourseDateRange().getStart());
        cal.set(2005, 11, 20, 9, 45, 00);
        Assert.assertEquals("End Time", cal.getTime(), course.getCourseDateRange().getEnd());
        Assert.assertEquals("Course Number", "CS 230", course.getCourseNumber());
        Assert.assertEquals("Coutse Times", "MWF 08:30-09:00", course.getCourseTimes());
        this.assertEquals("Subject", new String[] { "computer science" }, course.getSubjects());
        Assert.assertEquals("University", "Johnson State", course.getUniversity());

        entry = entries.get(1);
        course = (Course) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", course.getImageLinks()[0].toString());
        cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2005, 10, 30, 0, 0, 0);
        Assert.assertEquals("Course Date", cal.getTime(), course.getExpirationDate());
        labels = new String[] { "film", "video", "documentary" };
        this.assertEquals("Labels", labels, course.getLabels());
        cal.set(2005, 7, 19, 8, 30, 00);
        Assert.assertEquals("Start Time", cal.getTime(), course.getCourseDateRange().getStart());
        cal.set(2005, 11, 20, 9, 45, 00);
        Assert.assertEquals("End Time", cal.getTime(), course.getCourseDateRange().getEnd());
        Assert.assertEquals("Course Number", "FS 192", course.getCourseNumber());
        Assert.assertEquals("Coutse Times", "TTh 14:00-16:00", course.getCourseTimes());
        Assert.assertEquals("Subject", "film", course.getSubjects()[0]);
        Assert.assertEquals("University", "Johnson State", course.getUniversity());
    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testEvent2Parse() throws Exception {
        LOG.debug("testEvent2Parse");
        final SyndFeedInput input = new SyndFeedInput();

        final SyndFeed feed = input.build(new File(super.getTestFile("xml/events2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        SyndEntry entry = entries.get(0);
        Event event = (Event) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", event.getImageLinks()[0].toString());
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2005, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), event.getExpirationDate());
        this.assertEquals("Labels", new String[] { "Festival", "Halloween", "Party", "Costumes" }, event.getLabels());
        Assert.assertEquals("Currency", CurrencyEnumeration.USD, event.getCurrency());
        Assert.assertEquals("Price", 10, event.getPrice().getValue(), 0);
        Assert.assertEquals("PriceUnit", null, event.getPrice().getUnits());
        Assert.assertEquals("PriceType", PriceTypeEnumeration.STARTING, event.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[] { PaymentTypeEnumeration.CASH, PaymentTypeEnumeration.CHECK,
                PaymentTypeEnumeration.VISA }, event.getPaymentAccepted());
        Assert.assertEquals("Payment Notes", "Cash only for local orders", event.getPaymentNotes());
        /*
         * <g:event_date_range> <g:start>2005-07-04T20:00:00</g:start>
         * <g:end>2005-07-04T23:00:00</g:end> </g:event_date_range>
         */
        cal.set(2005, 06, 04, 20, 00, 00);
        Assert.assertEquals("Start Time", cal.getTime(), event.getEventDateRange().getStart());
        cal.set(2005, 06, 04, 23, 00, 00);
        Assert.assertEquals("End Time", cal.getTime(), event.getEventDateRange().getEnd());
        Assert.assertEquals("Location", "1600 Amphitheatre Parkway, Mountain View, CA, 94043", event.getLocation());
        Assert.assertEquals("Shipping Price", (float) 32.95, event.getShipping()[0].getPrice().getValue(), 0);
        // TODO: Determine what to do about the bogus services.
        Assert.assertEquals("Shipping Country", "US", event.getShipping()[0].getCountry());
        Assert.assertEquals("Tax Region", "California", event.getTaxRegion());
        Assert.assertEquals("Tax Percentage", new Float(8.25), event.getTaxPercent());

        entry = entries.get(1);
        event = (Event) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image2.jpg", event.getImageLinks()[0].toString());
        cal.setTimeInMillis(0);
        cal.set(2005, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), event.getExpirationDate());
        this.assertEquals("Labels", new String[] { "Concert", "festival", "music" }, event.getLabels());
        Assert.assertEquals("Currency", CurrencyEnumeration.USD, event.getCurrency());
        Assert.assertEquals("Price", 50, event.getPrice().getValue(), 0);
        Assert.assertEquals("PriceUnit", null, event.getPrice().getUnits());
        Assert.assertEquals("PriceType", PriceTypeEnumeration.STARTING, event.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[] { PaymentTypeEnumeration.CASH, PaymentTypeEnumeration.CHECK,
                PaymentTypeEnumeration.VISA }, event.getPaymentAccepted());
        Assert.assertEquals("Payment Notes", "Cash only for local orders", event.getPaymentNotes());
        /*
         * <g:event_date_range> <g:start>2005-08-23T20:00:00</g:start>
         * <g:end>2005-08-23T23:00:00</g:end> </g:event_date_range>
         */
        cal.set(2005, 07, 23, 20, 00, 00);
        Assert.assertEquals("Start Time", cal.getTime(), event.getEventDateRange().getStart());
        cal.set(2005, 07, 23, 23, 00, 00);
        Assert.assertEquals("End Time", cal.getTime(), event.getEventDateRange().getEnd());
        Assert.assertEquals("Location", "123 Main St, Anytown, CA, 12345, USA", event.getLocation());
        Assert.assertEquals("Shipping Price", (float) 32.95, event.getShipping()[0].getPrice().getValue(), 0);
        // TODO: Determine what to do about the bogus services.
        Assert.assertEquals("Shipping Country", "US", event.getShipping()[0].getCountry());
        Assert.assertEquals("Tax Region", "California", event.getTaxRegion());
        Assert.assertEquals("Tax Percentage", new Float(8.25), event.getTaxPercent());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testHousing2Parse() throws Exception {
        LOG.debug("testHousing2Parse");
        final SyndFeedInput input = new SyndFeedInput();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        final SyndFeed feed = input.build(new File(super.getTestFile("xml/housing2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        SyndEntry entry = entries.get(0);
        Housing module = (Housing) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2007, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "Housing", "New House", "Sale" }, module.getLabels());
        Assert.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        Assert.assertEquals("Price", 350000, module.getPrice().getValue(), 0);
        Assert.assertEquals("PriceUnit", null, module.getPrice().getUnits());
        Assert.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[] { PaymentTypeEnumeration.CASH, PaymentTypeEnumeration.CHECK,
                PaymentTypeEnumeration.VISA }, module.getPaymentAccepted());
        Assert.assertEquals("Payment Notes", "1000 deposit", module.getPaymentNotes());
        Assert.assertEquals("Listing Type", new Boolean(true), module.getListingType());
        this.assertEquals("PropertyTypes", new String[] { "Townhouse" }, module.getPropertyTypes());
        Assert.assertEquals("Location", "123 Main St, Anytown, CA, 12345, USA", module.getLocation());
        Assert.assertEquals("Bedrooms", new Integer(3), module.getBedrooms());
        Assert.assertEquals("Bathrooms", new Float(3), module.getBathrooms());
        Assert.assertEquals("Area", 1300, module.getArea().getValue());
        Assert.assertEquals("Area Units", null, module.getArea().getUnits());
        Assert.assertEquals("School District", "Union School District", module.getSchoolDistrict());
        Assert.assertEquals("HOA Dues", new Float(120), module.getHoaDues());
        Assert.assertEquals("Year", "2005", module.getYear().toString());
        this.assertEquals("Agents", new String[] { "Sue Smith" }, module.getAgents());
        Assert.assertEquals("Tax Region", "California", module.getTaxRegion());
        Assert.assertEquals("Tax Percentage", new Float(8.25), module.getTaxPercent());

        entry = entries.get(1);
        module = (Housing) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image2.jpg", module.getImageLinks()[0].toString());
        cal.set(2008, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "Housing", "rent", "lease" }, module.getLabels());
        Assert.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        Assert.assertEquals("Price", 1400, module.getPrice().getValue(), 0);
        Assert.assertEquals("PriceUnit", null, module.getPrice().getUnits());
        Assert.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[] { PaymentTypeEnumeration.CHECK }, module.getPaymentAccepted());
        Assert.assertEquals("Payment Notes", "1000 deposit", module.getPaymentNotes());
        Assert.assertEquals("Listing Type", new Boolean(false), module.getListingType());
        this.assertEquals("PropertyTypes", new String[] { "apartment" }, module.getPropertyTypes());
        Assert.assertEquals("Location", "123 Main St, Anytown, CA, 12345, USA", module.getLocation());
        Assert.assertEquals("Bedrooms", new Integer(2), module.getBedrooms());
        Assert.assertEquals("Bathrooms", new Float(2), module.getBathrooms());
        Assert.assertEquals("Area", 1100, module.getArea().getValue());
        Assert.assertEquals("Area Units", null, module.getArea().getUnits());
        Assert.assertEquals("School District", "Union School District", module.getSchoolDistrict());
        Assert.assertEquals("HOA Dues", null, module.getHoaDues());
        Assert.assertEquals("Year", "2004", module.getYear().toString());
        this.assertEquals("Agents", new String[] { "Sue Smith" }, module.getAgents());
        Assert.assertEquals("Tax Region", null, module.getTaxRegion());
        Assert.assertEquals("Tax Percentage", null, module.getTaxPercent());
    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testJobs2Parse() throws Exception {
        LOG.debug("testJobs2Parse");
        final SyndFeedInput input = new SyndFeedInput();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        final SyndFeed feed = input.build(new File(super.getTestFile("xml/jobs2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final Job module = (Job) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "Coordinator", "Google", "Online Support" }, module.getLabels());
        this.assertEquals("Industriy", new String[] { "Internet" }, module.getJobIndustries());
        Assert.assertEquals("Employer", "Google, Inc", module.getEmployer());
        this.assertEquals("Job Function", new String[] { "Google Coordinator" }, module.getJobFunctions());
        LOG.debug("{}", new Object[] { module.getJobTypes() });
        this.assertEquals("Job Type", new String[] { "full-time" }, module.getJobTypes());
        Assert.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        Assert.assertEquals("Salary", new Float(40000), module.getSalary());
        Assert.assertEquals("Salary Type", PriceTypeEnumeration.STARTING, module.getSalaryType());
        Assert.assertEquals("Education", "BS", module.getEducation());
        Assert.assertEquals("Immigration", "Permanent Resident", module.getImmigrationStatus());
        Assert.assertEquals("Location", "1600 Amphitheatre Parkway, Mountain View, CA, 94043, USA", module.getLocation());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testNews2Parse() throws Exception {
        LOG.debug("testNews2Parse");
        final SyndFeedInput input = new SyndFeedInput();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        final SyndFeed feed = input.build(new File(super.getTestFile("xml/news2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final Article module = (Article) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2007, 2, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "news", "old" }, module.getLabels());
        Assert.assertEquals("Source", "Journal", module.getNewsSource());
        cal.set(1961, 3, 12, 0, 0, 0);
        Assert.assertEquals("Pub Date", cal.getTime(), module.getPublishDate());
        this.assertEquals("Authors", new String[] { "James Smith" }, module.getAuthors());
        Assert.assertEquals("Pages", new Integer(1), module.getPages());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testTravel2Parse() throws Exception {
        LOG.debug("testTravel2Parse");
        final SyndFeedInput input = new SyndFeedInput();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        final SyndFeed feed = input.build(new File(super.getTestFile("xml/travel2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final Travel module = (Travel) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "Vacation", "Train" }, module.getLabels());
        Assert.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        Assert.assertEquals("Price", 250, module.getPrice().getValue(), 0);
        Assert.assertEquals("PriceUnit", null, module.getPrice().getUnits());
        Assert.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[] { PaymentTypeEnumeration.CHECK, PaymentTypeEnumeration.VISA },
                module.getPaymentAccepted());
        Assert.assertEquals("Payment notes", "minimum payment on credit cards:45", module.getPaymentNotes());
        Assert.assertEquals("Quantity", new Integer(204), module.getQuantity());
        Assert.assertEquals("From", "Mytown, USA", module.getFromLocation());
        Assert.assertEquals("To", "Anytown, USA", module.getToLocation());
        cal.set(2005, 11, 20, 18, 0, 0);
        Assert.assertEquals("Start Date", cal.getTime(), module.getTravelDateRange().getStart());
        cal.set(2005, 11, 22, 18, 0, 0);
        Assert.assertEquals("End Date", cal.getTime(), module.getTravelDateRange().getEnd());
        Assert.assertEquals("Location", "123 Main St, Mytown, CA, 12345, USA", module.getLocation());
        this.assertEquals("Shipping", new ShippingType[] { new ShippingType(new FloatUnit("32.95"), ShippingType.ServiceEnumeration.OVERNIGHT, "US") },
                module.getShipping());
        Assert.assertEquals("Tax Region", "California", module.getTaxRegion());
        Assert.assertEquals("Tax Percentage", new Float(8.25), module.getTaxPercent());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testPersona2Parse() throws Exception {
        LOG.debug("testPerson2Parse");
        final SyndFeedInput input = new SyndFeedInput();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        final SyndFeed feed = input.build(new File(super.getTestFile("xml/personals2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final Person module = (Person) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "Personals", "m4w" }, module.getLabels());
        this.assertEquals("Ethnicity", new String[] { "South Asian" }, module.getEthnicities());
        Assert.assertEquals("Gender", GenderEnumeration.MALE, module.getGender());
        Assert.assertEquals("Sexual Orientation", "straight", module.getSexualOrientation());
        this.assertEquals("Interested In", new String[] { "Single Women" }, module.getInterestedIn());
        Assert.assertEquals("Marital Status", "single", module.getMaritalStatus());
        Assert.assertEquals("Occupation", "Sales", module.getOccupation());
        Assert.assertEquals("Employer", "Google, Inc.", module.getEmployer());
        Assert.assertEquals("Age", new Integer(23), module.getAge());
        Assert.assertEquals("Location", "Anytown, 12345, USA", module.getLocation());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testProduct2Parse() throws Exception {
        LOG.debug("testProduct2Parse");
        final SyndFeedInput input = new SyndFeedInput();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        final SyndFeed feed = input.build(new File(super.getTestFile("xml/products2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final Product module = (Product) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.googlestore.com/appliance/images/products/GO0144E.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "web search", "appliance" }, module.getLabels());
        Assert.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        Assert.assertEquals("Price", 2995, module.getPrice().getValue(), 0);
        Assert.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[] { PaymentTypeEnumeration.CHECK, PaymentTypeEnumeration.MASTERCARD },
                module.getPaymentAccepted());
        Assert.assertEquals("Payment Notes", "financing available", module.getPaymentNotes());
        Assert.assertEquals("Brand", "Google", module.getBrand());
        Assert.assertEquals("UPC", "12348573", module.getUpc());
        Assert.assertEquals("Manufacturer", "Google", module.getManufacturer());
        Assert.assertEquals("ManufacturerId", "2325", module.getManufacturerId());
        Assert.assertEquals("Model number", "234", module.getModelNumber());
        LOG.debug("{}", module.getSize());
        Assert.assertEquals("Size", 10, module.getSize().getLength().getValue(), 0);
        Assert.assertEquals("Size", 50, module.getSize().getWidth().getValue(), 0);
        Assert.assertEquals("Size", 20, module.getSize().getHeight().getValue(), 0);
        Assert.assertEquals("Weight", 2, module.getWeight().getValue(), 0);
        Assert.assertEquals("Quantity", new Integer(300), module.getQuantity());
        Assert.assertEquals("Condition", "new", module.getCondition());
        this.assertEquals("Colors", new String[] { "blue" }, module.getColors());
        Assert.assertEquals("Location", "1600 Amphitheatre Pkwy Mountain View, CA 94043-1351, US", module.getLocation());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testResearch2Parse() throws Exception {
        LOG.debug("testResearch2Parse");
        final SyndFeedInput input = new SyndFeedInput();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        final SyndFeed feed = input.build(new File(super.getTestFile("xml/research2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final ScholarlyArticle module = (ScholarlyArticle) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "Economy", "Tsunami" }, module.getLabels());
        cal.set(2005, 1, 25);
        Assert.assertEquals("PubDate", cal.getTime(), module.getPublishDate());
        this.assertEquals("Authors", new String[] { "James Smith" }, module.getAuthors());
        Assert.assertEquals("Pub Name", "Tsunami and the Economy", module.getPublicationName());
        Assert.assertEquals("Pub Vol", "III", module.getPublicationVolume());
        Assert.assertEquals("Pages", new Integer(5), module.getPages());
    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testReview2Parse() throws Exception {
        LOG.debug("testReview2Parse");
        final SyndFeedInput input = new SyndFeedInput();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        final SyndFeed feed = input.build(new File(super.getTestFile("xml/reviews2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final Review module = (Review) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "Review", "Earth", "Google" }, module.getLabels());
        cal.set(2005, 2, 24);
        Assert.assertEquals("PubDate", cal.getTime(), module.getPublishDate());
        this.assertEquals("Authors", new String[] { "Jimmy Smith" }, module.getAuthors());
        Assert.assertEquals("Name of Item Rev", "Google Earth", module.getNameOfItemBeingReviewed());
        Assert.assertEquals("Type", "Product", module.getReviewType());
        Assert.assertEquals("Rever Type", "editorial", module.getReviewerType());
        Assert.assertEquals("Rating", new Float(5), module.getRating());
        Assert.assertEquals("URL of Item", new URL("http://earth.google.com/"), module.getUrlOfItemBeingReviewed());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testService2Parse() throws Exception {
        LOG.debug("testService2Parse");
        final SyndFeedInput input = new SyndFeedInput();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        final SyndFeed feed = input.build(new File(super.getTestFile("xml/services2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final Service module = (Service) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "Food delivery" }, module.getLabels());
        cal.set(2005, 2, 24);
        Assert.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        Assert.assertEquals("Price", 15, module.getPrice().getValue(), 0);
        Assert.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[] { PaymentTypeEnumeration.VISA, PaymentTypeEnumeration.MASTERCARD },
                module.getPaymentAccepted());
        Assert.assertEquals("Payment Notes", "minimum payment on credit cards:45", module.getPaymentNotes());
        Assert.assertEquals("Service Type", "delivery", module.getServiceType());
        Assert.assertEquals("Location", "Anytown, CA, USA", module.getLocation());
        Assert.assertEquals("DeliveryRad", 20, module.getDeliveryRadius().getValue(), 0);
        Assert.assertEquals("Delivery Notes", "will deliver between 9am -5pm", module.getDeliveryNotes());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testVehicle2Parse() throws Exception {
        LOG.debug("testVehicle2Parse");
        final SyndFeedInput input = new SyndFeedInput();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        final SyndFeed feed = input.build(new File(super.getTestFile("xml/vehicles2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final Vehicle module = (Vehicle) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "car", "mini" }, module.getLabels());
        cal.set(2005, 2, 24);
        Assert.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        Assert.assertEquals("Price", 24000, module.getPrice().getValue(), 0);
        Assert.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[] { PaymentTypeEnumeration.CHECK, PaymentTypeEnumeration.VISA,
                PaymentTypeEnumeration.MASTERCARD }, module.getPaymentAccepted());
        Assert.assertEquals("Payment Notes", "financing available", module.getPaymentNotes());
        Assert.assertEquals("Vehicle Type", "car", module.getVehicleType());
        Assert.assertEquals("Make", "Mini", module.getMake());
        Assert.assertEquals("Model", "Cooper S", module.getModel());
        Assert.assertEquals("Year", "2006", module.getYear().toString());
        Assert.assertEquals("Mileage", new Integer(0), module.getMileage());
        this.assertEquals("Colors", new String[] { "red" }, module.getColors());
        Assert.assertEquals("Vin", "1M8GDM9AXKP042788", module.getVin());
        Assert.assertEquals("Location", "123 Main Street, Anytown, CA, 12345, USA", module.getLocation());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testWanted2Parse() throws Exception {
        LOG.debug("testVehicle2Parse");
        final SyndFeedInput input = new SyndFeedInput();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        final SyndFeed feed = input.build(new File(super.getTestFile("xml/wanted2.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final Wanted module = (Wanted) entry.getModule(GoogleBase.URI);
        Assert.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        Assert.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[] { "Wanted", "Truck" }, module.getLabels());
        Assert.assertEquals("Location", "123 Main Street, Anytown, CA, 12345, USA", module.getLocation());
    }

    /**
     * Test of getNamespaceUri method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testGetNamespaceUri() {
        LOG.debug("testGetNamespaceUri");
        LOG.debug(new GoogleBaseParser().getNamespaceUri());
    }
}
