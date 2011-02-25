/*
 * GoogleBaseParserTest.java
 * JUnit based test
 *
 * Created on November 17, 2005, 4:49 PM
 */
package com.sun.syndication.feed.module.base.io;

import com.sun.syndication.feed.module.AbstractTestCase;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.feed.module.base.Article;
import com.sun.syndication.feed.module.base.Course;
import com.sun.syndication.feed.module.base.Event;
import com.sun.syndication.feed.module.base.GoogleBase;
import com.sun.syndication.feed.module.base.Housing;
import com.sun.syndication.feed.module.base.Job;
import com.sun.syndication.feed.module.base.Person;
import com.sun.syndication.feed.module.base.Product;
import com.sun.syndication.feed.module.base.Review;
import com.sun.syndication.feed.module.base.ScholarlyArticle;
import com.sun.syndication.feed.module.base.Service;
import com.sun.syndication.feed.module.base.Travel;
import com.sun.syndication.feed.module.base.Vehicle;
import com.sun.syndication.feed.module.base.Wanted;
import com.sun.syndication.feed.module.base.types.CurrencyEnumeration;
import com.sun.syndication.feed.module.base.types.FloatUnit;
import com.sun.syndication.feed.module.base.types.GenderEnumeration;
import com.sun.syndication.feed.module.base.types.PaymentTypeEnumeration;
import com.sun.syndication.feed.module.base.types.PriceTypeEnumeration;
import com.sun.syndication.feed.module.base.types.ShippingType;
import java.io.File;
import java.net.URL;
import java.util.Calendar;
import junit.framework.*;
import java.util.List;

/**
 *
 * @author rcooper
 */
public class GoogleBaseParserTest extends AbstractTestCase {

    public GoogleBaseParserTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(GoogleBaseParserTest.class);

        return suite;
    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testQuickParse() throws Exception {
        try {
            System.out.println("testParse");
            SyndFeedInput input = new SyndFeedInput();
            File testDir = new File(super.getTestFile("xml"));
            File[] testFiles = testDir.listFiles();
            for (int h = 0; h < testFiles.length; h++) {
                if (!testFiles[h].getName().endsWith(".xml")) {
                    continue;
                }
                SyndFeed feed = null;
                try {
                     feed = input.build(testFiles[h]);
                } catch (Exception e) {
                    throw new RuntimeException(testFiles[h].getAbsolutePath(), e);
                }
                List entries = feed.getEntries();
                for (int i = 0; i < entries.size(); i++) {
                    SyndEntry entry = (SyndEntry) entries.get(i);
                    System.out.println(entry.getModules().size());
                    for (int j = 0; j < entry.getModules().size(); j++) {
                        System.out.println(entry.getModules().get(j).getClass());
                        if (entry.getModules().get(j) instanceof GoogleBase) {
                            GoogleBase base = (GoogleBase) entry.getModules().get(j);
                            System.out.println(testFiles[h].getName());
                            System.out.println(super.beanToString(base, false));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testCourse2Parse() throws Exception {
        System.out.println("testCourse2Parse");
        SyndFeedInput input = new SyndFeedInput();

        SyndFeed feed = input.build(new File(super.getTestFile("xml/courses2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Course course = (Course) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", course.getImageLinks()[0].toString());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2005, 10, 30, 0, 0, 0);
        this.assertEquals("Course Date", cal.getTime(), course.getExpirationDate());
        String[] labels = new String[]{"robots", "society", "computers"};
        this.assertEquals("Labels", labels, course.getLabels());
        cal.set(2005, 7, 19, 8, 30, 00);
        this.assertEquals("Start Time", cal.getTime(), course.getCourseDateRange().getStart());
        cal.set(2005, 11, 20, 9, 45, 00);
        this.assertEquals("End Time", cal.getTime(), course.getCourseDateRange().getEnd());
        this.assertEquals("Course Number", "CS 230", course.getCourseNumber());
        this.assertEquals("Coutse Times", "MWF 08:30-09:00", course.getCourseTimes());
        this.assertEquals("Subject", new String[]{"computer science"}, course.getSubjects());
        this.assertEquals("University", "Johnson State", course.getUniversity());

        entry = (SyndEntry) entries.get(1);
        course = (Course) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", course.getImageLinks()[0].toString());
        cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2005, 10, 30, 0, 0, 0);
        this.assertEquals("Course Date", cal.getTime(), course.getExpirationDate());
        labels = new String[]{"film", "video", "documentary"};
        this.assertEquals("Labels", labels, course.getLabels());
        cal.set(2005, 7, 19, 8, 30, 00);
        this.assertEquals("Start Time", cal.getTime(), course.getCourseDateRange().getStart());
        cal.set(2005, 11, 20, 9, 45, 00);
        this.assertEquals("End Time", cal.getTime(), course.getCourseDateRange().getEnd());
        this.assertEquals("Course Number", "FS 192", course.getCourseNumber());
        this.assertEquals("Coutse Times", "TTh 14:00-16:00", course.getCourseTimes());
        this.assertEquals("Subject", "film", course.getSubjects()[0]);
        this.assertEquals("University", "Johnson State", course.getUniversity());
    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testEvent2Parse() throws Exception {
        System.out.println("testEvent2Parse");
        SyndFeedInput input = new SyndFeedInput();

        SyndFeed feed = input.build(new File(super.getTestFile("xml/events2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Event event = (Event) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", event.getImageLinks()[0].toString());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2005, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), event.getExpirationDate());
        this.assertEquals("Labels", new String[]{"Festival", "Halloween", "Party", "Costumes"}, event.getLabels());
        this.assertEquals("Currency", CurrencyEnumeration.USD, event.getCurrency());
        this.assertEquals("Price", (float) 10, event.getPrice().getValue(), 0);
        this.assertEquals("PriceUnit", null, event.getPrice().getUnits());
        this.assertEquals("PriceType", PriceTypeEnumeration.STARTING, event.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[]{PaymentTypeEnumeration.CASH, PaymentTypeEnumeration.CHECK, PaymentTypeEnumeration.VISA}, event.getPaymentAccepted());
        this.assertEquals("Payment Notes", "Cash only for local orders", event.getPaymentNotes());
        /*
        <g:event_date_range>
        <g:start>2005-07-04T20:00:00</g:start>
        <g:end>2005-07-04T23:00:00</g:end>
        </g:event_date_range>
         */
        cal.set(2005, 06, 04, 20, 00, 00);
        this.assertEquals("Start Time", cal.getTime(), event.getEventDateRange().getStart());
        cal.set(2005, 06, 04, 23, 00, 00);
        this.assertEquals("End Time", cal.getTime(), event.getEventDateRange().getEnd());
        this.assertEquals("Location", "1600 Amphitheatre Parkway, Mountain View, CA, 94043", event.getLocation());
        this.assertEquals("Shipping Price", (float) 32.95, event.getShipping()[0].getPrice().getValue(), 0);
        //TODO: Determine what to do about the bogus services.
        this.assertEquals("Shipping Country", "US", event.getShipping()[0].getCountry());
        this.assertEquals("Tax Region", "California", event.getTaxRegion());
        this.assertEquals("Tax Percentage", new Float(8.25), event.getTaxPercent());

        entry = (SyndEntry) entries.get(1);
        event = (Event) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image2.jpg", event.getImageLinks()[0].toString());
        cal.setTimeInMillis(0);
        cal.set(2005, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), event.getExpirationDate());
        this.assertEquals("Labels", new String[]{"Concert", "festival", "music"}, event.getLabels());
        this.assertEquals("Currency", CurrencyEnumeration.USD, event.getCurrency());
        this.assertEquals("Price", (float) 50, event.getPrice().getValue(), 0);
        this.assertEquals("PriceUnit", null, event.getPrice().getUnits());
        this.assertEquals("PriceType", PriceTypeEnumeration.STARTING, event.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[]{PaymentTypeEnumeration.CASH, PaymentTypeEnumeration.CHECK, PaymentTypeEnumeration.VISA}, event.getPaymentAccepted());
        this.assertEquals("Payment Notes", "Cash only for local orders", event.getPaymentNotes());
        /*
        <g:event_date_range>
        <g:start>2005-08-23T20:00:00</g:start>
        <g:end>2005-08-23T23:00:00</g:end>
        </g:event_date_range>
         */
        cal.set(2005, 07, 23, 20, 00, 00);
        this.assertEquals("Start Time", cal.getTime(), event.getEventDateRange().getStart());
        cal.set(2005, 07, 23, 23, 00, 00);
        this.assertEquals("End Time", cal.getTime(), event.getEventDateRange().getEnd());
        this.assertEquals("Location", "123 Main St, Anytown, CA, 12345, USA", event.getLocation());
        this.assertEquals("Shipping Price", (float) 32.95, event.getShipping()[0].getPrice().getValue(), 0);
        //TODO: Determine what to do about the bogus services.
        this.assertEquals("Shipping Country", "US", event.getShipping()[0].getCountry());
        this.assertEquals("Tax Region", "California", event.getTaxRegion());
        this.assertEquals("Tax Percentage", new Float(8.25), event.getTaxPercent());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testHousing2Parse() throws Exception {
        System.out.println("testHousing2Parse");
        SyndFeedInput input = new SyndFeedInput();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        SyndFeed feed = input.build(new File(super.getTestFile("xml/housing2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Housing module = (Housing) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2007, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"Housing", "New House", "Sale"}, module.getLabels());
        this.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        this.assertEquals("Price", (float) 350000, module.getPrice().getValue(), 0);
        this.assertEquals("PriceUnit", null, module.getPrice().getUnits());
        this.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[]{PaymentTypeEnumeration.CASH, PaymentTypeEnumeration.CHECK, PaymentTypeEnumeration.VISA}, module.getPaymentAccepted());
        this.assertEquals("Payment Notes", "1000 deposit", module.getPaymentNotes());
        this.assertEquals("Listing Type", new Boolean(true), module.getListingType());
        this.assertEquals("PropertyTypes", new String[]{"Townhouse"}, module.getPropertyTypes());
        this.assertEquals("Location", "123 Main St, Anytown, CA, 12345, USA", module.getLocation());
        this.assertEquals("Bedrooms", new Integer(3), module.getBedrooms());
        this.assertEquals("Bathrooms", new Float(3), module.getBathrooms());
        this.assertEquals("Area", 1300, module.getArea().getValue());
        this.assertEquals("Area Units", null, module.getArea().getUnits());
        this.assertEquals("School District", "Union School District", module.getSchoolDistrict());
        this.assertEquals("HOA Dues", new Float(120), module.getHoaDues());
        this.assertEquals("Year", "2005", module.getYear().toString());
        this.assertEquals("Agents", new String[]{"Sue Smith"}, module.getAgents());
        this.assertEquals("Tax Region", "California", module.getTaxRegion());
        this.assertEquals("Tax Percentage", new Float(8.25), module.getTaxPercent());

        entry = (SyndEntry) entries.get(1);
        module = (Housing) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image2.jpg", module.getImageLinks()[0].toString());
        cal.set(2008, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"Housing", "rent", "lease"}, module.getLabels());
        this.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        this.assertEquals("Price", (float) 1400, module.getPrice().getValue(), 0);
        this.assertEquals("PriceUnit", null, module.getPrice().getUnits());
        this.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[]{PaymentTypeEnumeration.CHECK}, module.getPaymentAccepted());
        this.assertEquals("Payment Notes", "1000 deposit", module.getPaymentNotes());
        this.assertEquals("Listing Type", new Boolean(false), module.getListingType());
        this.assertEquals("PropertyTypes", new String[]{"apartment"}, module.getPropertyTypes());
        this.assertEquals("Location", "123 Main St, Anytown, CA, 12345, USA", module.getLocation());
        this.assertEquals("Bedrooms", new Integer(2), module.getBedrooms());
        this.assertEquals("Bathrooms", new Float(2), module.getBathrooms());
        this.assertEquals("Area", 1100, module.getArea().getValue());
        this.assertEquals("Area Units", null, module.getArea().getUnits());
        this.assertEquals("School District", "Union School District", module.getSchoolDistrict());
        this.assertEquals("HOA Dues", null, module.getHoaDues());
        this.assertEquals("Year", "2004", module.getYear().toString());
        this.assertEquals("Agents", new String[]{"Sue Smith"}, module.getAgents());
        this.assertEquals("Tax Region", null, module.getTaxRegion());
        this.assertEquals("Tax Percentage", null, module.getTaxPercent());
    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testJobs2Parse() throws Exception {
        System.out.println("testJobs2Parse");
        SyndFeedInput input = new SyndFeedInput();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        SyndFeed feed = input.build(new File(super.getTestFile("xml/jobs2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Job module = (Job) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"Coordinator", "Google", "Online Support"}, module.getLabels());
        this.assertEquals("Industriy", new String[]{"Internet"}, module.getJobIndustries());
        this.assertEquals("Employer", "Google, Inc", module.getEmployer());
        this.assertEquals("Job Function", new String[]{"Google Coordinator"}, module.getJobFunctions());
        System.out.println(module.getJobTypes());
        this.assertEquals("Job Type", new String[]{"full-time"}, module.getJobTypes());
        this.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        this.assertEquals("Salary", new Float(40000), module.getSalary());
        this.assertEquals("Salary Type", PriceTypeEnumeration.STARTING, module.getSalaryType());
        this.assertEquals("Education", "BS", module.getEducation());
        this.assertEquals("Immigration", "Permanent Resident", module.getImmigrationStatus());
        this.assertEquals("Location", "1600 Amphitheatre Parkway, Mountain View, CA, 94043, USA", module.getLocation());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testNews2Parse() throws Exception {
        System.out.println("testNews2Parse");
        SyndFeedInput input = new SyndFeedInput();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        SyndFeed feed = input.build(new File(super.getTestFile("xml/news2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Article module = (Article) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2007, 2, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"news", "old"}, module.getLabels());
        this.assertEquals("Source", "Journal", module.getNewsSource());
        cal.set(1961, 3, 12, 0, 0, 0);
        this.assertEquals("Pub Date", cal.getTime(), module.getPublishDate());
        this.assertEquals("Authors", new String[]{"James Smith"}, module.getAuthors());
        this.assertEquals("Pages", new Integer(1), module.getPages());


    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testTravel2Parse() throws Exception {
        System.out.println("testTravel2Parse");
        SyndFeedInput input = new SyndFeedInput();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        SyndFeed feed = input.build(new File(super.getTestFile("xml/travel2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Travel module = (Travel) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"Vacation", "Train"}, module.getLabels());
        this.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        this.assertEquals("Price", (float) 250, module.getPrice().getValue(), 0);
        this.assertEquals("PriceUnit", null, module.getPrice().getUnits());
        this.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[]{PaymentTypeEnumeration.CHECK, PaymentTypeEnumeration.VISA}, module.getPaymentAccepted());
        this.assertEquals("Payment notes", "minimum payment on credit cards:45", module.getPaymentNotes());
        this.assertEquals("Quantity", new Integer(204), module.getQuantity());
        this.assertEquals("From", "Mytown, USA", module.getFromLocation());
        this.assertEquals("To", "Anytown, USA", module.getToLocation());
        cal.set(2005, 11, 20, 18, 0, 0);
        this.assertEquals("Start Date", cal.getTime(), module.getTravelDateRange().getStart());
        cal.set(2005, 11, 22, 18, 0, 0);
        this.assertEquals("End Date", cal.getTime(), module.getTravelDateRange().getEnd());
        this.assertEquals("Location", "123 Main St, Mytown, CA, 12345, USA", module.getLocation());
        this.assertEquals("Shipping", new ShippingType[]{new ShippingType(new FloatUnit("32.95"), ShippingType.ServiceEnumeration.OVERNIGHT, "US")}, module.getShipping());
        this.assertEquals("Tax Region", "California", module.getTaxRegion());
        this.assertEquals("Tax Percentage", new Float(8.25), module.getTaxPercent());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testPersona2Parse() throws Exception {
        System.out.println("testPerson2Parse");
        SyndFeedInput input = new SyndFeedInput();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        SyndFeed feed = input.build(new File(super.getTestFile("xml/personals2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Person module = (Person) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"Personals", "m4w"}, module.getLabels());
        this.assertEquals("Ethnicity", new String[]{"South Asian"}, module.getEthnicities());
        this.assertEquals("Gender", GenderEnumeration.MALE, module.getGender());
        this.assertEquals("Sexual Orientation", "straight", module.getSexualOrientation());
        this.assertEquals("Interested In", new String[]{"Single Women"}, module.getInterestedIn());
        this.assertEquals("Marital Status", "single", module.getMaritalStatus());
        this.assertEquals("Occupation", "Sales", module.getOccupation());
        this.assertEquals("Employer", "Google, Inc.", module.getEmployer());
        this.assertEquals("Age", new Integer(23), module.getAge());
        this.assertEquals("Location", "Anytown, 12345, USA", module.getLocation());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testProduct2Parse() throws Exception {
        System.out.println("testProduct2Parse");
        SyndFeedInput input = new SyndFeedInput();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        SyndFeed feed = input.build(new File(super.getTestFile("xml/products2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Product module = (Product) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.googlestore.com/appliance/images/products/GO0144E.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"web search", "appliance"}, module.getLabels());
        this.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        this.assertEquals("Price", (float) 2995, module.getPrice().getValue(), 0);
        this.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[]{PaymentTypeEnumeration.CHECK, PaymentTypeEnumeration.MASTERCARD}, module.getPaymentAccepted());
        this.assertEquals("Payment Notes", "financing available", module.getPaymentNotes());
        this.assertEquals("Brand", "Google", module.getBrand());
        this.assertEquals("UPC", "12348573", module.getUpc());
        this.assertEquals("Manufacturer", "Google", module.getManufacturer());
        this.assertEquals("ManufacturerId", "2325", module.getManufacturerId());
        this.assertEquals("Model number", "234", module.getModelNumber());
        System.out.println(module.getSize());
        this.assertEquals("Size", (float) 10, module.getSize().getLength().getValue(), 0);
        this.assertEquals("Size", (float) 50, module.getSize().getWidth().getValue(), 0);
        this.assertEquals("Size", (float) 20, module.getSize().getHeight().getValue(), 0);
        this.assertEquals("Weight", (float) 2, module.getWeight().getValue(), 0);
        this.assertEquals("Quantity", new Integer(300), module.getQuantity());
        this.assertEquals("Condition", "new", module.getCondition());
        this.assertEquals("Colors", new String[]{"blue"}, module.getColors());
        this.assertEquals("Location", "1600 Amphitheatre Pkwy Mountain View, CA 94043-1351, US", module.getLocation());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testResearch2Parse() throws Exception {
        System.out.println("testResearch2Parse");
        SyndFeedInput input = new SyndFeedInput();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        SyndFeed feed = input.build(new File(super.getTestFile("xml/research2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        ScholarlyArticle module = (ScholarlyArticle) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"Economy", "Tsunami"}, module.getLabels());
        cal.set(2005, 1, 25);
        this.assertEquals("PubDate", cal.getTime(), module.getPublishDate());
        this.assertEquals("Authors", new String[]{"James Smith"}, module.getAuthors());
        this.assertEquals("Pub Name", "Tsunami and the Economy", module.getPublicationName());
        this.assertEquals("Pub Vol", "III", module.getPublicationVolume());
        this.assertEquals("Pages", new Integer(5), module.getPages());
    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testReview2Parse() throws Exception {
        System.out.println("testReview2Parse");
        SyndFeedInput input = new SyndFeedInput();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        SyndFeed feed = input.build(new File(super.getTestFile("xml/reviews2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Review module = (Review) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"Review", "Earth", "Google"}, module.getLabels());
        cal.set(2005, 2, 24);
        this.assertEquals("PubDate", cal.getTime(), module.getPublishDate());
        this.assertEquals("Authors", new String[]{"Jimmy Smith"}, module.getAuthors());
        this.assertEquals("Name of Item Rev", "Google Earth", module.getNameOfItemBeingReviewed());
        this.assertEquals("Type", "Product", module.getReviewType());
        this.assertEquals("Rever Type", "editorial", module.getReviewerType());
        this.assertEquals("Rating", new Float(5), module.getRating());
        this.assertEquals("URL of Item", new URL("http://earth.google.com/"), module.getUrlOfItemBeingReviewed());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testService2Parse() throws Exception {
        System.out.println("testService2Parse");
        SyndFeedInput input = new SyndFeedInput();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        SyndFeed feed = input.build(new File(super.getTestFile("xml/services2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Service module = (Service) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"Food delivery"}, module.getLabels());
        cal.set(2005, 2, 24);
        this.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        this.assertEquals("Price", (float) 15, module.getPrice().getValue(), 0);
        this.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[]{PaymentTypeEnumeration.VISA, PaymentTypeEnumeration.MASTERCARD}, module.getPaymentAccepted());
        this.assertEquals("Payment Notes", "minimum payment on credit cards:45", module.getPaymentNotes());
        this.assertEquals("Service Type", "delivery", module.getServiceType());
        this.assertEquals("Location", "Anytown, CA, USA", module.getLocation());
        this.assertEquals("DeliveryRad", (float) 20, module.getDeliveryRadius().getValue(), 0);
        this.assertEquals("Delivery Notes", "will deliver between 9am -5pm", module.getDeliveryNotes());

    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testVehicle2Parse() throws Exception {
        System.out.println("testVehicle2Parse");
        SyndFeedInput input = new SyndFeedInput();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        SyndFeed feed = input.build(new File(super.getTestFile("xml/vehicles2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Vehicle module = (Vehicle) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"car", "mini"}, module.getLabels());
        cal.set(2005, 2, 24);
        this.assertEquals("Currency", CurrencyEnumeration.USD, module.getCurrency());
        this.assertEquals("Price", (float) 24000, module.getPrice().getValue(), 0);
        this.assertEquals("PriceType", PriceTypeEnumeration.STARTING, module.getPriceType());
        this.assertEquals("Payment Accepted", new PaymentTypeEnumeration[]{PaymentTypeEnumeration.CHECK, PaymentTypeEnumeration.VISA, PaymentTypeEnumeration.MASTERCARD}, module.getPaymentAccepted());
        this.assertEquals("Payment Notes", "financing available", module.getPaymentNotes());
        this.assertEquals("Vehicle Type", "car", module.getVehicleType());
        this.assertEquals("Make", "Mini", module.getMake());
        this.assertEquals("Model", "Cooper S", module.getModel());
        this.assertEquals("Year", "2006", module.getYear().toString());
        this.assertEquals("Mileage", new Integer(0), module.getMileage());
        this.assertEquals("Colors", new String[]{"red"}, module.getColors());
        this.assertEquals("Vin", "1M8GDM9AXKP042788", module.getVin());
        this.assertEquals("Location", "123 Main Street, Anytown, CA, 12345, USA", module.getLocation());


    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testWanted2Parse() throws Exception {
        System.out.println("testVehicle2Parse");
        SyndFeedInput input = new SyndFeedInput();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        SyndFeed feed = input.build(new File(super.getTestFile("xml/wanted2.xml")));
        List entries = feed.getEntries();
        SyndEntry entry = (SyndEntry) entries.get(0);
        Wanted module = (Wanted) entry.getModule(GoogleBase.URI);
        this.assertEquals("Image Link", "http://www.providers-website.com/image1.jpg", module.getImageLinks()[0].toString());
        cal.set(2005, 11, 20, 0, 0, 0);
        this.assertEquals("Expiration Date", cal.getTime(), module.getExpirationDate());
        this.assertEquals("Labels", new String[]{"Wanted", "Truck"}, module.getLabels());
        this.assertEquals("Location", "123 Main Street, Anytown, CA, 12345, USA", module.getLocation());
    }

    /**
     * Test of getNamespaceUri method, of class com.totsp.xml.syndication.base.io.GoogleBaseParser.
     */
    public void testGetNamespaceUri() {
        System.out.println("testGetNamespaceUri");
        System.out.println(new GoogleBaseParser().getNamespaceUri());
    }
}
