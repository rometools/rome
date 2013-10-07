/*
 * GoogleBaseImpl.java
 *
 * Created on November 16, 2005, 2:06 PM
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rometools.feed.module.base;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Date;

import org.rometools.feed.module.base.types.CloneableType;
import org.rometools.feed.module.base.types.CurrencyEnumeration;
import org.rometools.feed.module.base.types.DateTimeRange;
import org.rometools.feed.module.base.types.FloatUnit;
import org.rometools.feed.module.base.types.GenderEnumeration;
import org.rometools.feed.module.base.types.IntUnit;
import org.rometools.feed.module.base.types.PaymentTypeEnumeration;
import org.rometools.feed.module.base.types.PriceTypeEnumeration;
import org.rometools.feed.module.base.types.ShippingType;
import org.rometools.feed.module.base.types.ShortDate;
import org.rometools.feed.module.base.types.Size;
import org.rometools.feed.module.base.types.YearType;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.impl.EqualsBean;

/**
 * This is the implementation class for the GoogleBase module interface.
 * 
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.2 $
 */
public class GoogleBaseImpl implements GoogleBase {
    /** boolean:listing_type */
    private Boolean listingType;

    /** Can this item be picked up. */
    private Boolean pickup;

    /** currencyCodeEnumeration:currency */
    private CurrencyEnumeration currency;

    /** dateTime:expiration_date_time */
    private Date expirationDateTime;

    /** dateTimeRange:course_date_range */
    private DateTimeRange courseDateRange;

    /** dateTimeRange:event_date_range */
    private DateTimeRange eventDateRange;

    /** dateTimeRange:travel_date_range */
    private DateTimeRange travelDateRange;

    /** float:bathrooms */
    private Float bathrooms;

    /** float:hoa_dues */
    private Float hoaDues;

    /** string:rating thoug this a value from 1 to 5 */
    private Float rating;

    /** float:salary */
    private Float salary;

    /** percentType:tax_percent */
    private Float taxPercent;

    /** floatUnit:delivery_radius */
    private FloatUnit deliveryRadius;

    /** floatUnit:megapixels */
    private FloatUnit megapixels;

    /** floatUnit:memory */
    private FloatUnit memory;

    /** floatUnit:price */
    private FloatUnit price;

    /** floatUnit:processor_speed */
    private FloatUnit processorSpeed;

    /** floatUnit:weight */
    private FloatUnit weight;

    /** genderEnumeration:gender */
    private GenderEnumeration gender;

    /** intUnit:area */
    private IntUnit area;

    /** integer:age */
    private Integer age;

    /** integer:bedrooms */
    private Integer bedrooms;

    /** integer:mileage */
    private Integer mileage;

    /** integer:pages */
    private Integer pages;

    /** integer:quantity */
    private Integer quantity;

    /** locationType:from_location */
    private String fromLocation;

    /** locationType:location */
    private String location;

    /** locationType:to_location */
    private String toLocation;

    /** priceTypeEnumeration:price_type */
    private PriceTypeEnumeration priceType;

    /** starting/neg:salary_type */
    private PriceTypeEnumeration salaryType;

    /** date:expiration_date */
    private ShortDate expirationDate;

    /** date:publishedDate */
    private ShortDate publishDate;

    /** string:size */
    private Size size;

    /** string:sexual_orientation */
    private String SexualOrientation;

    /** string apparel_type */
    private String apparelType;

    /** string:brand */
    private String brand;

    /** string:condition */
    private String condition;

    /** string:course_number */
    private String courseNumber;

    /** string:course_times */
    private String courseTimes;

    /** string:delivery_notes */
    private String deliveryNotes;

    /** string:education */
    private String education;

    /** string:employer */
    private String employer;

    /** string:id */
    private String id;

    /** string:immigration_status */
    private String immigrationStatus;

    /** stirng:isbn */
    private String isbn;

    /** string:make */
    private String make;

    /** string:manufacturer */
    private String manufacturer;

    /** string:manufacturer_id */
    private String manufacturerId;

    /** string:marital_status */
    private String maritalStatus;

    /** string:model */
    private String model;

    /** string:model_number */
    private String modelNumber;

    /** string:name_of_item_being_reviewed */
    private String nameOfItemBeingReviewed;

    /** string:news_source */
    private String newsSource;

    /** string:occupation */
    private String occupation;

    /** string:operating_systems */
    private String operatingSystems;

    /** string:payment_notes */
    private String paymentNotes;

    /** string:publication_name */
    private String publicationName;

    /** string:publication_volume */
    private String publicationVolume;

    /** string:review_type */
    private String reviewType;

    /** string:reviewer_type */
    private String reviewerType;

    /** string:school_district */
    private String schoolDistrict;

    /** string:service_type */
    private String serviceType;

    /** string:taxRegion */
    private String taxRegion;

    /** string:university */
    private String university;

    /** string:upc */
    private String upc;

    /** string:vehicle_type */
    private String vehicleType;

    /** string:vin */
    private String vin;

    /** string:url_of_item_being_reviewed */
    private URL urlOfItemBeingReviewed;

    /** string:year */
    private YearType year;

    /** string:actor */
    private String[] actors;

    /** string:agent */
    private String[] agents;

    /** string:artist */
    private String[] artists;

    /** string:author */
    private String[] authors;

    /** string:color */
    private String[] color;

    /** string:ethnicities */
    private String[] ethnicities;

    /** string:format */
    private String[] format;

    /** url:image_links */
    private URL[] imageLinks;

    /** string:interested_in */
    private String[] interestedIn;

    /** string:job_function */
    private String[] jobFunctions;

    /** string:job_industry */
    private String[] jobIndustries;

    /** string:job_type */
    private String[] jobTypes;

    /** string:label */
    private String[] labels;

    /** string:license */
    private String[] licenses;

    /** paymentTypeEnumeration:payment_accepted */
    private PaymentTypeEnumeration[] paymentAccepted;

    /** string:product_type */
    private String[] productTypes;

    /** string:programming_language */
    private String[] programmingLanguages;

    /** string:property_type */
    private String[] propertyTypes;

    /** url:related_link */
    private URL[] relatedLinks;

    /** shippingType:shipping */
    private ShippingType[] shipping;

    /** intUnitType:square_footage */
    private IntUnit[] squareFootages;

    /** string:subject_area */
    private String[] subjectAreas;

    /** string:subject */
    private String[] subjects;

    /**
     * DOCUMENT ME!
     * 
     * @param actors
     */
    public void setActors(final String[] actors) {
        this.actors = actors;
    }

    public String[] getActors() {
        return actors == null ? new String[0] : actors;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAgents(final String[] agents) {
        this.agents = agents == null ? new String[0] : agents;
    }

    public String[] getAgents() {
        return agents;
    }

    public void setApparelType(final String apparelType) {
        this.apparelType = apparelType;
    }

    public String getApparelType() {
        return apparelType;
    }

    public void setArea(final IntUnit area) {
        this.area = area;
    }

    public IntUnit getArea() {
        return area;
    }

    public void setArtists(final String[] artists) {
        this.artists = artists;
    }

    public String[] getArtists() {
        return artists == null ? new String[0] : artists;
    }

    public void setAuthors(final String[] authors) {
        this.authors = authors;
    }

    public String[] getAuthors() {
        return authors == null ? new String[0] : authors;
    }

    public void setBathrooms(final Float bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Float getBathrooms() {
        return bathrooms;
    }

    public void setBedrooms(final Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setColors(final String[] color) {
        this.color = color;
    }

    public String[] getColors() {
        return color == null ? new String[0] : color;
    }

    public void setCondition(final String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCourseDateRange(final DateTimeRange courseDateRange) {
        this.courseDateRange = courseDateRange;
    }

    public DateTimeRange getCourseDateRange() {
        return courseDateRange;
    }

    public void setCourseNumber(final String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseTimes(final String courseTimes) {
        this.courseTimes = courseTimes;
    }

    public String getCourseTimes() {
        return courseTimes;
    }

    public void setCurrency(final CurrencyEnumeration currency) {
        this.currency = currency;
    }

    public CurrencyEnumeration getCurrency() {
        return currency;
    }

    public void setDeliveryNotes(final String deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }

    public String getDeliveryNotes() {
        return deliveryNotes;
    }

    public void setDeliveryRadius(final FloatUnit deliveryRadius) {
        this.deliveryRadius = deliveryRadius;
    }

    public FloatUnit getDeliveryRadius() {
        return deliveryRadius;
    }

    public void setEducation(final String education) {
        this.education = education;
    }

    public String getEducation() {
        return education;
    }

    public void setEmployer(final String employer) {
        this.employer = employer;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEthnicities(final String[] ethnicities) {
        this.ethnicities = ethnicities;
    }

    public String[] getEthnicities() {
        return ethnicities == null ? new String[0] : ethnicities;
    }

    public void setEventDateRange(final DateTimeRange eventDateRange) {
        this.eventDateRange = eventDateRange;
    }

    public DateTimeRange getEventDateRange() {
        return eventDateRange;
    }

    public void setExpirationDate(final Date expirationDate) {
        if (expirationDate != null && !(expirationDate instanceof ShortDate)) {
            this.expirationDate = new ShortDate(expirationDate);
        } else {
            this.expirationDate = null;
        }
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDateTime(final Date expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public Date getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setFormat(final String[] format) {
        this.format = format;
    }

    public String[] getFormat() {
        return format == null ? new String[0] : format;
    }

    public void setFromLocation(final String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setGender(final GenderEnumeration gender) {
        this.gender = gender;
    }

    public GenderEnumeration getGender() {
        return gender;
    }

    public void setHoaDues(final Float hoaDues) {
        this.hoaDues = hoaDues;
    }

    public Float getHoaDues() {
        return hoaDues;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setImageLinks(final URL[] imageLinks) {
        this.imageLinks = imageLinks;
    }

    public URL[] getImageLinks() {
        return imageLinks == null ? new URL[0] : imageLinks;
    }

    public void setImmigrationStatus(final String immigrationStatus) {
        this.immigrationStatus = immigrationStatus;
    }

    public String getImmigrationStatus() {
        return immigrationStatus;
    }

    public void setInterestedIn(final String[] interestedIn) {
        this.interestedIn = interestedIn;
    }

    public String[] getInterestedIn() {
        return interestedIn == null ? new String[0] : interestedIn;
    }

    public Class getInterface() {
        return GoogleBase.class;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setJobFunctions(final String[] jobFunctions) {
        this.jobFunctions = jobFunctions;
    }

    public String[] getJobFunctions() {
        return jobFunctions == null ? new String[0] : jobFunctions;
    }

    public void setJobIndustries(final String[] jobIndustries) {
        this.jobIndustries = jobIndustries;
    }

    public String[] getJobIndustries() {
        return jobIndustries == null ? new String[0] : jobIndustries;
    }

    public void setJobTypes(final String[] jobTypes) {
        this.jobTypes = jobTypes;
    }

    public String[] getJobTypes() {
        return jobTypes == null ? new String[0] : jobTypes;
    }

    public void setLabels(final String[] labels) {
        this.labels = labels;
    }

    public String[] getLabels() {
        return labels == null ? new String[0] : labels;
    }

    public void setLicenses(final String[] licenses) {
        this.licenses = licenses;
    }

    public String[] getLicenses() {
        return licenses == null ? new String[0] : licenses;
    }

    public void setListingType(final Boolean listingType) {
        this.listingType = listingType;
    }

    public Boolean getListingType() {
        return listingType;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setMake(final String make) {
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturerId(final String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setMaritalStatus(final String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMegapixels(final FloatUnit megapixels) {
        this.megapixels = megapixels;
    }

    public FloatUnit getMegapixels() {
        return megapixels;
    }

    public void setMemory(final FloatUnit memory) {
        this.memory = memory;
    }

    public FloatUnit getMemory() {
        return memory;
    }

    public void setMileage(final Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModelNumber(final String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setNameOfItemBeingReviewed(final String nameOfItemBeingReviewed) {
        this.nameOfItemBeingReviewed = nameOfItemBeingReviewed;
    }

    public String getNameOfItemBeingReviewed() {
        return nameOfItemBeingReviewed;
    }

    public void setNewsSource(final String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setOccupation(final String occupation) {
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOperatingSystems(final String operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    public String getOperatingSystems() {
        return operatingSystems;
    }

    public void setPages(final Integer pages) {
        this.pages = pages;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPaymentAccepted(final PaymentTypeEnumeration[] paymentAccepted) {
        this.paymentAccepted = paymentAccepted;
    }

    public PaymentTypeEnumeration[] getPaymentAccepted() {
        return paymentAccepted == null ? new PaymentTypeEnumeration[0] : paymentAccepted;
    }

    public void setPaymentNotes(final String paymentNotes) {
        this.paymentNotes = paymentNotes;
    }

    public String getPaymentNotes() {
        return paymentNotes;
    }

    public void setPickup(final Boolean pickup) {
        this.pickup = pickup;
    }

    public Boolean getPickup() {
        return pickup;
    }

    public void setPrice(final FloatUnit price) {
        this.price = price;
    }

    public FloatUnit getPrice() {
        return price;
    }

    public void setPriceType(final PriceTypeEnumeration priceType) {
        this.priceType = priceType;
    }

    public PriceTypeEnumeration getPriceType() {
        return priceType;
    }

    public void setProcessorSpeed(final FloatUnit processorSpeed) {
        this.processorSpeed = processorSpeed;
    }

    public FloatUnit getProcessorSpeed() {
        return processorSpeed;
    }

    public void setProductTypes(final String[] productTypes) {
        this.productTypes = productTypes;
    }

    public String[] getProductTypes() {
        return productTypes == null ? new String[0] : productTypes;
    }

    public void setProgrammingLanguages(final String[] programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public String[] getProgrammingLanguages() {
        return programmingLanguages == null ? new String[0] : programmingLanguages;
    }

    public void setPropertyTypes(final String[] propertyTypes) {
        this.propertyTypes = propertyTypes;
    }

    public String[] getPropertyTypes() {
        return propertyTypes == null ? new String[0] : propertyTypes;
    }

    public void setPublicationName(final String publicationName) {
        this.publicationName = publicationName;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationVolume(final String publicationVolume) {
        this.publicationVolume = publicationVolume;
    }

    public String getPublicationVolume() {
        return publicationVolume;
    }

    public void setPublishDate(final Date publishDate) {
        if (publishDate != null && !(publishDate instanceof ShortDate)) {
            this.publishDate = new ShortDate(publishDate);
        } else {
            this.publishDate = null;
        }
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setRating(final Float rating) {
        this.rating = rating;
    }

    public Float getRating() {
        return rating;
    }

    public void setRelatedLinks(final URL[] relatedLinks) {
        this.relatedLinks = relatedLinks;
    }

    public URL[] getRelatedLinks() {
        return relatedLinks == null ? new URL[0] : relatedLinks;
    }

    public void setReviewType(final String reviewType) {
        this.reviewType = reviewType;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewerType(final String reviewerType) {
        this.reviewerType = reviewerType;
    }

    public String getReviewerType() {
        return reviewerType;
    }

    public void setSalary(final Float salary) {
        this.salary = salary;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalaryType(final PriceTypeEnumeration salaryType) {
        this.salaryType = salaryType;
    }

    public PriceTypeEnumeration getSalaryType() {
        return salaryType;
    }

    public void setSchoolDistrict(final String schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    public String getSchoolDistrict() {
        return schoolDistrict;
    }

    public void setServiceType(final String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setSexualOrientation(final String SexualOrientation) {
        this.SexualOrientation = SexualOrientation;
    }

    public String getSexualOrientation() {
        return SexualOrientation;
    }

    public void setShipping(final ShippingType[] shipping) {
        this.shipping = shipping;
    }

    public ShippingType[] getShipping() {
        return shipping == null ? new ShippingType[0] : shipping;
    }

    public void setSize(final Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void setSquareFootages(final IntUnit[] squareFootages) {
        this.squareFootages = squareFootages;
    }

    public IntUnit[] getSquareFootages() {
        return squareFootages == null ? new IntUnit[0] : squareFootages;
    }

    public void setSubjectAreas(final String[] subjectAreas) {
        this.subjectAreas = subjectAreas;
    }

    public String[] getSubjectAreas() {
        return subjectAreas == null ? new String[0] : subjectAreas;
    }

    public void setSubjects(final String[] subjects) {
        this.subjects = subjects;
    }

    public String[] getSubjects() {
        return subjects == null ? new String[0] : subjects;
    }

    public void setTaxPercent(final Float taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Float getTaxPercent() {
        return taxPercent;
    }

    public void setTaxRegion(final String taxRegion) {
        this.taxRegion = taxRegion;
    }

    public String getTaxRegion() {
        return taxRegion;
    }

    public void setToLocation(final String toLocation) {
        this.toLocation = toLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setTravelDateRange(final DateTimeRange travelDateRange) {
        this.travelDateRange = travelDateRange;
    }

    public DateTimeRange getTravelDateRange() {
        return travelDateRange;
    }

    public void setUniversity(final String university) {
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }

    public void setUpc(final String upc) {
        this.upc = upc;
    }

    public String getUpc() {
        return upc;
    }

    public String getUri() {
        return GoogleBase.URI;
    }

    public void setUrlOfItemBeingReviewed(final URL urlOfItemBeingReviewed) {
        this.urlOfItemBeingReviewed = urlOfItemBeingReviewed;
    }

    public URL getUrlOfItemBeingReviewed() {
        return urlOfItemBeingReviewed;
    }

    public void setVehicleType(final String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVin(final String vin) {
        this.vin = vin;
    }

    public String getVin() {
        return vin;
    }

    public void setWeight(final FloatUnit weight) {
        this.weight = weight;
    }

    public FloatUnit getWeight() {
        return weight;
    }

    public void setYear(final YearType year) {
        this.year = year;
    }

    public YearType getYear() {
        return year;
    }

    // TODO: Finish
    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            final GoogleBaseImpl gbi = new GoogleBaseImpl();
            gbi.copyFrom(this);

            return gbi;
        } catch (final Exception e) {
            e.printStackTrace();
            throw new CloneNotSupportedException();
        }
    }

    public void copyFrom(final CopyFrom obj) {
        if (!(obj instanceof GoogleBase)) {
            return;
        }

        final GoogleBase source = (GoogleBase) obj;

        setActors((String[]) arrayCopy(source.getActors()));
        setAge(source.getAge());
        setAgents((String[]) arrayCopy(source.getAgents()));
        setApparelType(source.getApparelType());
        setArea(source.getArea());
        setArtists((String[]) arrayCopy(source.getArtists()));
        setAuthors((String[]) arrayCopy(source.getAuthors()));
        setBathrooms(source.getBathrooms());
        setBedrooms(source.getBedrooms());
        setBrand(source.getBrand());
        setColors((String[]) arrayCopy(source.getColors()));
        setCondition(source.getCondition());
        setCourseDateRange((DateTimeRange) cloneOrNull(source.getCourseDateRange()));
        setCourseNumber(source.getCourseNumber());
        setCourseTimes(source.getCourseTimes());
        setDeliveryNotes(source.getDeliveryNotes());
        setDeliveryRadius(source.getDeliveryRadius());
        setEducation(source.getEducation());
        setEmployer(source.getEmployer());
        setEthnicities((String[]) arrayCopy(source.getEthnicities()));
        setEventDateRange((DateTimeRange) cloneOrNull(source.getEventDateRange()));
        setExpirationDate(dateOrNull(source.getExpirationDate()));
        setExpirationDateTime(dateOrNull(source.getExpirationDateTime()));
        setFormat(source.getFormat());
        setFromLocation(source.getFromLocation());
        setGender(source.getGender());
        setHoaDues(source.getHoaDues());
        setId(source.getId());
        setImageLinks((URL[]) arrayCopy(source.getImageLinks()));
        setImmigrationStatus(source.getImmigrationStatus());
        setInterestedIn(source.getInterestedIn());
        setIsbn(source.getIsbn());
        setJobFunctions((String[]) arrayCopy(source.getJobFunctions()));
        setJobIndustries((String[]) arrayCopy(source.getJobIndustries()));
        setJobTypes((String[]) arrayCopy(source.getJobTypes()));
        setLabels((String[]) arrayCopy(source.getLabels()));
        setListingType(source.getListingType());
        setLocation(source.getLocation());
        setMake(source.getMake());
        setManufacturer(source.getManufacturer());
        setManufacturerId(source.getManufacturerId());
        setMaritalStatus(source.getMaritalStatus());
        setMegapixels(source.getMegapixels());
        setMemory(source.getMemory());
        setMileage(source.getMileage());
        setModel(source.getModel());
        setModelNumber(source.getModelNumber());
        setNameOfItemBeingReviewed(source.getNameOfItemBeingReviewed());
        setNewsSource(source.getNewsSource());
        setOccupation(source.getOccupation());
        setPages(source.getPages());
        setPaymentAccepted((PaymentTypeEnumeration[]) arrayCopy(source.getPaymentAccepted()));
        setPaymentNotes(source.getPaymentNotes());
        setPickup(source.getPickup());
        setPrice(source.getPrice());
        setPriceType(source.getPriceType());
        setProcessorSpeed(source.getProcessorSpeed());
        setProductTypes((String[]) arrayCopy(source.getProductTypes()));
        setPropertyTypes((String[]) arrayCopy(source.getPropertyTypes()));
        setPublicationName(source.getPublicationName());
        setPublicationVolume(source.getPublicationVolume());
        setPublishDate(dateOrNull(source.getPublishDate()));
        setQuantity(source.getQuantity());
        setRating(source.getRating());
        setReviewType(source.getReviewType());
        setReviewerType(source.getReviewerType());
        setSalary(source.getSalary());
        setSalaryType(source.getSalaryType());
        setServiceType(source.getServiceType());
        setSexualOrientation(source.getSexualOrientation());
        setShipping((ShippingType[]) arrayCopy(source.getShipping()));
        setSize(source.getSize());
        setSubjects((String[]) arrayCopy(source.getSubjects()));
        setTaxPercent(source.getTaxPercent());
        setTaxRegion(source.getTaxRegion());
        setToLocation(source.getToLocation());
        setTravelDateRange((DateTimeRange) cloneOrNull(source.getTravelDateRange()));
        setUpc(source.getUpc());
        setUrlOfItemBeingReviewed(source.getUrlOfItemBeingReviewed());
        setVehicleType(source.getVehicleType());
        setVin(source.getVin());
        setYear(source.getYear());
        setLicenses((String[]) arrayCopy(source.getLicenses()));
        setRelatedLinks((URL[]) arrayCopy(source.getRelatedLinks()));
        setSubjectAreas((String[]) arrayCopy(source.getSubjectAreas()));
        setProgrammingLanguages((String[]) arrayCopy(source.getProgrammingLanguages()));
        setSquareFootages((IntUnit[]) arrayCopy(source.getSquareFootages()));
        setCurrency(source.getCurrency());
        setSchoolDistrict(source.getSchoolDistrict());
        setUniversity(source.getUniversity());
        setWeight(source.getWeight());
        setOperatingSystems(source.getOperatingSystems());
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(this.getClass(), this);

        return eBean.beanEquals(obj);
    }

    private Object arrayCopy(final Object[] source) {
        if (source == null) {
            return null;
        }

        final Object[] array = (Object[]) Array.newInstance(source.getClass().getComponentType(), source.length);

        for (int i = 0; i < source.length; i++) {
            array[i] = source[i];
        }

        return array;
    }

    private Object cloneOrNull(final CloneableType o) {
        if (o == null) {
            return null;
        } else {
            return o.clone();
        }
    }

    private Date dateOrNull(final Date date) {
        if (date == null) {
            return null;
        } else {
            return new Date(date.getTime());
        }
    }
}
