/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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
 */
package com.rometools.modules.base;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.base.types.CloneableType;
import com.rometools.modules.base.types.CurrencyEnumeration;
import com.rometools.modules.base.types.DateTimeRange;
import com.rometools.modules.base.types.FloatUnit;
import com.rometools.modules.base.types.GenderEnumeration;
import com.rometools.modules.base.types.IntUnit;
import com.rometools.modules.base.types.PaymentTypeEnumeration;
import com.rometools.modules.base.types.PriceTypeEnumeration;
import com.rometools.modules.base.types.ShippingType;
import com.rometools.modules.base.types.ShortDate;
import com.rometools.modules.base.types.Size;
import com.rometools.modules.base.types.YearType;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;

/**
 * This is the implementation class for the GoogleBase module interface.
 */
public class GoogleBaseImpl implements GoogleBase {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(GoogleBaseImpl.class);

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

    @Override
    public void setActors(final String[] actors) {
        this.actors = actors;
    }

    @Override
    public String[] getActors() {
        return actors == null ? new String[0] : actors;
    }

    @Override
    public void setAge(final Integer age) {
        this.age = age;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void setAgents(final String[] agents) {
        this.agents = agents == null ? new String[0] : agents;
    }

    @Override
    public String[] getAgents() {
        return agents;
    }

    @Override
    public void setApparelType(final String apparelType) {
        this.apparelType = apparelType;
    }

    @Override
    public String getApparelType() {
        return apparelType;
    }

    @Override
    public void setArea(final IntUnit area) {
        this.area = area;
    }

    @Override
    public IntUnit getArea() {
        return area;
    }

    @Override
    public void setArtists(final String[] artists) {
        this.artists = artists;
    }

    @Override
    public String[] getArtists() {
        return artists == null ? new String[0] : artists;
    }

    @Override
    public void setAuthors(final String[] authors) {
        this.authors = authors;
    }

    @Override
    public String[] getAuthors() {
        return authors == null ? new String[0] : authors;
    }

    @Override
    public void setBathrooms(final Float bathrooms) {
        this.bathrooms = bathrooms;
    }

    @Override
    public Float getBathrooms() {
        return bathrooms;
    }

    @Override
    public void setBedrooms(final Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    @Override
    public Integer getBedrooms() {
        return bedrooms;
    }

    @Override
    public void setBrand(final String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setColors(final String[] color) {
        this.color = color;
    }

    @Override
    public String[] getColors() {
        return color == null ? new String[0] : color;
    }

    @Override
    public void setCondition(final String condition) {
        this.condition = condition;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    @Override
    public void setCourseDateRange(final DateTimeRange courseDateRange) {
        this.courseDateRange = courseDateRange;
    }

    @Override
    public DateTimeRange getCourseDateRange() {
        return courseDateRange;
    }

    @Override
    public void setCourseNumber(final String courseNumber) {
        this.courseNumber = courseNumber;
    }

    @Override
    public String getCourseNumber() {
        return courseNumber;
    }

    @Override
    public void setCourseTimes(final String courseTimes) {
        this.courseTimes = courseTimes;
    }

    @Override
    public String getCourseTimes() {
        return courseTimes;
    }

    @Override
    public void setCurrency(final CurrencyEnumeration currency) {
        this.currency = currency;
    }

    @Override
    public CurrencyEnumeration getCurrency() {
        return currency;
    }

    @Override
    public void setDeliveryNotes(final String deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }

    @Override
    public String getDeliveryNotes() {
        return deliveryNotes;
    }

    @Override
    public void setDeliveryRadius(final FloatUnit deliveryRadius) {
        this.deliveryRadius = deliveryRadius;
    }

    @Override
    public FloatUnit getDeliveryRadius() {
        return deliveryRadius;
    }

    @Override
    public void setEducation(final String education) {
        this.education = education;
    }

    @Override
    public String getEducation() {
        return education;
    }

    @Override
    public void setEmployer(final String employer) {
        this.employer = employer;
    }

    @Override
    public String getEmployer() {
        return employer;
    }

    @Override
    public void setEthnicities(final String[] ethnicities) {
        this.ethnicities = ethnicities;
    }

    @Override
    public String[] getEthnicities() {
        return ethnicities == null ? new String[0] : ethnicities;
    }

    @Override
    public void setEventDateRange(final DateTimeRange eventDateRange) {
        this.eventDateRange = eventDateRange;
    }

    @Override
    public DateTimeRange getEventDateRange() {
        return eventDateRange;
    }

    @Override
    public void setExpirationDate(final Date expirationDate) {
        if (expirationDate != null && !(expirationDate instanceof ShortDate)) {
            this.expirationDate = new ShortDate(expirationDate);
        } else {
            this.expirationDate = null;
        }
    }

    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDateTime(final Date expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    @Override
    public Date getExpirationDateTime() {
        return expirationDateTime;
    }

    @Override
    public void setFormat(final String[] format) {
        this.format = format;
    }

    @Override
    public String[] getFormat() {
        return format == null ? new String[0] : format;
    }

    @Override
    public void setFromLocation(final String fromLocation) {
        this.fromLocation = fromLocation;
    }

    @Override
    public String getFromLocation() {
        return fromLocation;
    }

    @Override
    public void setGender(final GenderEnumeration gender) {
        this.gender = gender;
    }

    @Override
    public GenderEnumeration getGender() {
        return gender;
    }

    @Override
    public void setHoaDues(final Float hoaDues) {
        this.hoaDues = hoaDues;
    }

    @Override
    public Float getHoaDues() {
        return hoaDues;
    }

    @Override
    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setImageLinks(final URL[] imageLinks) {
        this.imageLinks = imageLinks;
    }

    @Override
    public URL[] getImageLinks() {
        return imageLinks == null ? new URL[0] : imageLinks;
    }

    @Override
    public void setImmigrationStatus(final String immigrationStatus) {
        this.immigrationStatus = immigrationStatus;
    }

    @Override
    public String getImmigrationStatus() {
        return immigrationStatus;
    }

    @Override
    public void setInterestedIn(final String[] interestedIn) {
        this.interestedIn = interestedIn;
    }

    @Override
    public String[] getInterestedIn() {
        return interestedIn == null ? new String[0] : interestedIn;
    }

    @Override
    public Class<GoogleBase> getInterface() {
        return GoogleBase.class;
    }

    @Override
    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String getIsbn() {
        return isbn;
    }

    @Override
    public void setJobFunctions(final String[] jobFunctions) {
        this.jobFunctions = jobFunctions;
    }

    @Override
    public String[] getJobFunctions() {
        return jobFunctions == null ? new String[0] : jobFunctions;
    }

    @Override
    public void setJobIndustries(final String[] jobIndustries) {
        this.jobIndustries = jobIndustries;
    }

    @Override
    public String[] getJobIndustries() {
        return jobIndustries == null ? new String[0] : jobIndustries;
    }

    @Override
    public void setJobTypes(final String[] jobTypes) {
        this.jobTypes = jobTypes;
    }

    @Override
    public String[] getJobTypes() {
        return jobTypes == null ? new String[0] : jobTypes;
    }

    @Override
    public void setLabels(final String[] labels) {
        this.labels = labels;
    }

    @Override
    public String[] getLabels() {
        return labels == null ? new String[0] : labels;
    }

    @Override
    public void setLicenses(final String[] licenses) {
        this.licenses = licenses;
    }

    @Override
    public String[] getLicenses() {
        return licenses == null ? new String[0] : licenses;
    }

    @Override
    public void setListingType(final Boolean listingType) {
        this.listingType = listingType;
    }

    @Override
    public Boolean getListingType() {
        return listingType;
    }

    @Override
    public void setLocation(final String location) {
        this.location = location;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setMake(final String make) {
        this.make = make;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturerId(final String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @Override
    public String getManufacturerId() {
        return manufacturerId;
    }

    @Override
    public void setMaritalStatus(final String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String getMaritalStatus() {
        return maritalStatus;
    }

    @Override
    public void setMegapixels(final FloatUnit megapixels) {
        this.megapixels = megapixels;
    }

    @Override
    public FloatUnit getMegapixels() {
        return megapixels;
    }

    @Override
    public void setMemory(final FloatUnit memory) {
        this.memory = memory;
    }

    @Override
    public FloatUnit getMemory() {
        return memory;
    }

    @Override
    public void setMileage(final Integer mileage) {
        this.mileage = mileage;
    }

    @Override
    public Integer getMileage() {
        return mileage;
    }

    @Override
    public void setModel(final String model) {
        this.model = model;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModelNumber(final String modelNumber) {
        this.modelNumber = modelNumber;
    }

    @Override
    public String getModelNumber() {
        return modelNumber;
    }

    @Override
    public void setNameOfItemBeingReviewed(final String nameOfItemBeingReviewed) {
        this.nameOfItemBeingReviewed = nameOfItemBeingReviewed;
    }

    @Override
    public String getNameOfItemBeingReviewed() {
        return nameOfItemBeingReviewed;
    }

    @Override
    public void setNewsSource(final String newsSource) {
        this.newsSource = newsSource;
    }

    @Override
    public String getNewsSource() {
        return newsSource;
    }

    @Override
    public void setOccupation(final String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String getOccupation() {
        return occupation;
    }

    @Override
    public void setOperatingSystems(final String operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    @Override
    public String getOperatingSystems() {
        return operatingSystems;
    }

    @Override
    public void setPages(final Integer pages) {
        this.pages = pages;
    }

    @Override
    public Integer getPages() {
        return pages;
    }

    @Override
    public void setPaymentAccepted(final PaymentTypeEnumeration[] paymentAccepted) {
        this.paymentAccepted = paymentAccepted;
    }

    @Override
    public PaymentTypeEnumeration[] getPaymentAccepted() {
        return paymentAccepted == null ? new PaymentTypeEnumeration[0] : paymentAccepted;
    }

    @Override
    public void setPaymentNotes(final String paymentNotes) {
        this.paymentNotes = paymentNotes;
    }

    @Override
    public String getPaymentNotes() {
        return paymentNotes;
    }

    @Override
    public void setPickup(final Boolean pickup) {
        this.pickup = pickup;
    }

    @Override
    public Boolean getPickup() {
        return pickup;
    }

    @Override
    public void setPrice(final FloatUnit price) {
        this.price = price;
    }

    @Override
    public FloatUnit getPrice() {
        return price;
    }

    @Override
    public void setPriceType(final PriceTypeEnumeration priceType) {
        this.priceType = priceType;
    }

    @Override
    public PriceTypeEnumeration getPriceType() {
        return priceType;
    }

    @Override
    public void setProcessorSpeed(final FloatUnit processorSpeed) {
        this.processorSpeed = processorSpeed;
    }

    @Override
    public FloatUnit getProcessorSpeed() {
        return processorSpeed;
    }

    @Override
    public void setProductTypes(final String[] productTypes) {
        this.productTypes = productTypes;
    }

    @Override
    public String[] getProductTypes() {
        return productTypes == null ? new String[0] : productTypes;
    }

    @Override
    public void setProgrammingLanguages(final String[] programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    @Override
    public String[] getProgrammingLanguages() {
        return programmingLanguages == null ? new String[0] : programmingLanguages;
    }

    @Override
    public void setPropertyTypes(final String[] propertyTypes) {
        this.propertyTypes = propertyTypes;
    }

    @Override
    public String[] getPropertyTypes() {
        return propertyTypes == null ? new String[0] : propertyTypes;
    }

    @Override
    public void setPublicationName(final String publicationName) {
        this.publicationName = publicationName;
    }

    @Override
    public String getPublicationName() {
        return publicationName;
    }

    @Override
    public void setPublicationVolume(final String publicationVolume) {
        this.publicationVolume = publicationVolume;
    }

    @Override
    public String getPublicationVolume() {
        return publicationVolume;
    }

    @Override
    public void setPublishDate(final Date publishDate) {
        if (publishDate != null && !(publishDate instanceof ShortDate)) {
            this.publishDate = new ShortDate(publishDate);
        } else {
            this.publishDate = null;
        }
    }

    @Override
    public Date getPublishDate() {
        return publishDate;
    }

    @Override
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public void setRating(final Float rating) {
        this.rating = rating;
    }

    @Override
    public Float getRating() {
        return rating;
    }

    @Override
    public void setRelatedLinks(final URL[] relatedLinks) {
        this.relatedLinks = relatedLinks;
    }

    @Override
    public URL[] getRelatedLinks() {
        return relatedLinks == null ? new URL[0] : relatedLinks;
    }

    @Override
    public void setReviewType(final String reviewType) {
        this.reviewType = reviewType;
    }

    @Override
    public String getReviewType() {
        return reviewType;
    }

    @Override
    public void setReviewerType(final String reviewerType) {
        this.reviewerType = reviewerType;
    }

    @Override
    public String getReviewerType() {
        return reviewerType;
    }

    @Override
    public void setSalary(final Float salary) {
        this.salary = salary;
    }

    @Override
    public Float getSalary() {
        return salary;
    }

    @Override
    public void setSalaryType(final PriceTypeEnumeration salaryType) {
        this.salaryType = salaryType;
    }

    @Override
    public PriceTypeEnumeration getSalaryType() {
        return salaryType;
    }

    @Override
    public void setSchoolDistrict(final String schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    @Override
    public String getSchoolDistrict() {
        return schoolDistrict;
    }

    @Override
    public void setServiceType(final String serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String getServiceType() {
        return serviceType;
    }

    @Override
    public void setSexualOrientation(final String SexualOrientation) {
        this.SexualOrientation = SexualOrientation;
    }

    @Override
    public String getSexualOrientation() {
        return SexualOrientation;
    }

    @Override
    public void setShipping(final ShippingType[] shipping) {
        this.shipping = shipping;
    }

    @Override
    public ShippingType[] getShipping() {
        return shipping == null ? new ShippingType[0] : shipping;
    }

    @Override
    public void setSize(final Size size) {
        this.size = size;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSquareFootages(final IntUnit[] squareFootages) {
        this.squareFootages = squareFootages;
    }

    @Override
    public IntUnit[] getSquareFootages() {
        return squareFootages == null ? new IntUnit[0] : squareFootages;
    }

    @Override
    public void setSubjectAreas(final String[] subjectAreas) {
        this.subjectAreas = subjectAreas;
    }

    @Override
    public String[] getSubjectAreas() {
        return subjectAreas == null ? new String[0] : subjectAreas;
    }

    @Override
    public void setSubjects(final String[] subjects) {
        this.subjects = subjects;
    }

    @Override
    public String[] getSubjects() {
        return subjects == null ? new String[0] : subjects;
    }

    @Override
    public void setTaxPercent(final Float taxPercent) {
        this.taxPercent = taxPercent;
    }

    @Override
    public Float getTaxPercent() {
        return taxPercent;
    }

    @Override
    public void setTaxRegion(final String taxRegion) {
        this.taxRegion = taxRegion;
    }

    @Override
    public String getTaxRegion() {
        return taxRegion;
    }

    @Override
    public void setToLocation(final String toLocation) {
        this.toLocation = toLocation;
    }

    @Override
    public String getToLocation() {
        return toLocation;
    }

    @Override
    public void setTravelDateRange(final DateTimeRange travelDateRange) {
        this.travelDateRange = travelDateRange;
    }

    @Override
    public DateTimeRange getTravelDateRange() {
        return travelDateRange;
    }

    @Override
    public void setUniversity(final String university) {
        this.university = university;
    }

    @Override
    public String getUniversity() {
        return university;
    }

    @Override
    public void setUpc(final String upc) {
        this.upc = upc;
    }

    @Override
    public String getUpc() {
        return upc;
    }

    @Override
    public String getUri() {
        return GoogleBase.URI;
    }

    @Override
    public void setUrlOfItemBeingReviewed(final URL urlOfItemBeingReviewed) {
        this.urlOfItemBeingReviewed = urlOfItemBeingReviewed;
    }

    @Override
    public URL getUrlOfItemBeingReviewed() {
        return urlOfItemBeingReviewed;
    }

    @Override
    public void setVehicleType(final String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String getVehicleType() {
        return vehicleType;
    }

    @Override
    public void setVin(final String vin) {
        this.vin = vin;
    }

    @Override
    public String getVin() {
        return vin;
    }

    @Override
    public void setWeight(final FloatUnit weight) {
        this.weight = weight;
    }

    @Override
    public FloatUnit getWeight() {
        return weight;
    }

    @Override
    public void setYear(final YearType year) {
        this.year = year;
    }

    @Override
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
            LOG.error("Error", e);
            throw new CloneNotSupportedException();
        }
    }

    @Override
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
