/*
 * Metadata.java
 *
 * Created on April 18, 2006, 7:57 PM
 *
 *
 * This code is currently released under the Mozilla Public License.
 * http://www.mozilla.org/MPL/
 *
 * Alternately you may apply the terms of the Apache Software License
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rometools.feed.module.mediarss.types;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import java.io.Serializable;
import java.net.URI;


/**
 *
 *
 *
 * Optional Elements
 *        <p> The following elements are optional and may appear as sub-elements of &lt;channel&gt;, &lt;item&gt;, &lt;media:content&gt; and/or &lt;media:group&gt;.
 *
 *
 * </p><p>
 *
 * When an element appears at a shallow level, such as &lt;channel&gt; or &lt;item&gt;, it means that the element should be applied to every media object within its scope.</p>
 *
 *
 * <p>
 * Duplicated elements appearing at deeper levels of the document tree have higher priority over other levels.
 *
 * For example, &lt;media:content&gt; level elements are favored over &lt;item&gt; level elements.
 * The priority level is listed from strongest to weakest: &lt;media:content&gt;, &lt;media:group&gt;, &lt;item&gt;, &lt;channel&gt;.</p>
 * @author cooper
 */
public class Metadata implements Cloneable, Serializable {
	private static final long serialVersionUID = 649350950456005250L;
	
	private Hash hash;
    private String copyright;
    private String description;
    private String descriptionType;
    private String title;
    private String titleType;
    private URI copyrightUrl;
    private Category[] categories = new Category[0];
    private Credit[] credits = new Credit[0];
    private String[] keywords = new String[0];
    private Rating[] ratings = new Rating[0];
    private Restriction[] restrictions = new Restriction[0];
    private Text[] text = new Text[0];
    private Thumbnail[] thumbnail = new Thumbnail[0];
    
    /** Creates a new instance of Metadata */
    public Metadata() {
        super();
    }
    
    /**
     * <strong>&lt;media:category&gt;</strong></p>
     *        <p> Allows a taxonomy to be set that gives an indication of the type of media content, and its particular contents.
     * It has 2 optional attributes.  </p>
     * <pre>        &lt;media:category scheme="http://search.yahoo.com/mrss/category_
     *        schema"&gt;music/artist/album/song&lt;/media:category&gt;
     *
     *        &lt;media:category scheme="http://dmoz.org" label="Ace Ventura - Pet
     *        Detective"&gt;Arts/Movies/Titles/A/Ace_Ventura_Series/Ace_Ventura_
     *        -_Pet_Detective&lt;/media:category&gt;
     *
     *        &lt;media:category scheme="urn:flickr:tags"&gt;ycantpark
     *        mobile&lt;/media:category&gt;</pre>
     *
     * <p><em>scheme</em> is the URI that identifies the categorization scheme. It is an optional attribute. If this attribute is not included, the default scheme is 'http://search.yahoo.com/mrss/category_schema'.</p>
     *
     * <p><em>label</em> is the human readable label that can be displayed in end user applications. It is an optional attribute.</p>
     * @param categories categories for the item
     */
    public void setCategories(Category[] categories) {
        this.categories = (categories == null) ? new Category[0] : categories;
    }
    
    /**
     * <strong>&lt;media:category&gt;</strong></p>
     *        <p> Allows a taxonomy to be set that gives an indication of the type of media content, and its particular contents.
     * It has 2 optional attributes.  </p>
     * <pre>        &lt;media:category scheme="http://search.yahoo.com/mrss/category_
     *        schema"&gt;music/artist/album/song&lt;/media:category&gt;
     *
     *        &lt;media:category scheme="http://dmoz.org" label="Ace Ventura - Pet
     *        Detective"&gt;Arts/Movies/Titles/A/Ace_Ventura_Series/Ace_Ventura_
     *        -_Pet_Detective&lt;/media:category&gt;
     *
     *        &lt;media:category scheme="urn:flickr:tags"&gt;ycantpark
     *        mobile&lt;/media:category&gt;</pre>
     *
     * <p><em>scheme</em> is the URI that identifies the categorization scheme. It is an optional attribute. If this attribute is not included, the default scheme is 'http://search.yahoo.com/mrss/category_schema'.</p>
     *
     * <p><em>label</em> is the human readable label that can be displayed in end user applications. It is an optional attribute.</p>
     * @return categories for the item.
     */
    public Category[] getCategories() {
        return categories;
    }
    
    /**
     * <strong>&lt;media:copyright&gt;</strong></p>
     * <p>Copyright information for media object.  It has 1 optional attribute.</p>
     *
     * <pre>        &lt;media:copyright url="http://blah.com/additional-info.html"&gt;2005 FooBar Media&lt;/media:copyright&gt;</pre>
     * <p><em>url</em> is the url for a terms of use page or additional copyright information. If the media is operating under a Creative Commons license, the Creative Commons module should be used instead. It is an optional attribute.</p>
     * @param copyright copyright text
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    
    /**
     * <strong>&lt;media:copyright&gt;</strong></p>
     * <p>Copyright information for media object.  It has 1 optional attribute.</p>
     *
     * <pre>        &lt;media:copyright url="http://blah.com/additional-info.html"&gt;2005 FooBar Media&lt;/media:copyright&gt;</pre>
     * <p><em>url</em> is the url for a terms of use page or additional copyright information. If the media is operating under a Creative Commons license, the Creative Commons module should be used instead. It is an optional attribute.</p>
     * @return Copyright text
     */
    public String getCopyright() {
        return copyright;
    }
    
    /**
     * <strong>&lt;media:copyright&gt;</strong></p>
     * <p>Copyright information for media object.  It has 1 optional attribute.</p>
     *
     * <pre>        &lt;media:copyright url="http://blah.com/additional-info.html"&gt;2005 FooBar Media&lt;/media:copyright&gt;</pre>
     * <p><em>url</em> is the url for a terms of use page or additional copyright information. If the media is operating under a Creative Commons license, the Creative Commons module should be used instead. It is an optional attribute.</p>
     * @param copyrightUrl link to more copyright information.
     */
    public void setCopyrightUrl(URI copyrightUrl) {
        this.copyrightUrl = copyrightUrl;
    }
    
    /**
     * <strong>&lt;media:copyright&gt;</strong></p>
     * <p>Copyright information for media object.  It has 1 optional attribute.</p>
     *
     * <pre>        &lt;media:copyright url="http://blah.com/additional-info.html"&gt;2005 FooBar Media&lt;/media:copyright&gt;</pre>
     * <p><em>url</em> is the url for a terms of use page or additional copyright information. If the media is operating under a Creative Commons license, the Creative Commons module should be used instead. It is an optional attribute.</p>
     * @return Link to more copyright information.
     */
    public URI getCopyrightUrl() {
        return copyrightUrl;
    }
    
    /**
     * <strong>&lt;media:credit&gt;</strong></p>
     *
     * <p>Notable entity and the contribution to the creation of the media object. Current entities can include people, companies, locations, etc. Specific entities can have multiple roles, and several entities can have the same role.
     * These should appear as distinct &lt;media:credit&gt; elements.
     * It has 2 optional attributes.</p>
     * <pre>        &lt;media:credit role="producer" scheme="urn:ebu"&gt;entity name&lt;/media:credit&gt;
     * </pre>
     * <p>role specifies the role the entity played. Must be lowercase. It is an optional attribute.</p>
     *
     * <p><em>scheme</em> is the URI that identifies the role scheme. It is an optional attribute. If this attribute is not included, the default scheme is 'urn:ebu'. See: European Broadcasting Union Role Codes.</p>
     *
     *
     * <p>Example roles:</p>
     * <pre>        actor
     *        anchor person
     *        author
     *        choreographer
     *        composer
     *        conductor
     *        director
     *        editor
     *        graphic designer
     *        grip
     *        illustrator
     *        lyricist
     *        music arranger
     *        music group
     *        musician
     *        orchestra
     *        performer
     *        photographer
     *        producer
     *        reporter
     *        vocalist
     * </pre>
     * <p>Additional roles: <a href="http://www.ebu.ch/en/technical/metadata/specifications/role_codes.php">European Broadcasting Union Role Codes</a>
     * @param credits credits for the item.
     */
    public void setCredits(Credit[] credits) {
        this.credits = (credits == null) ? new Credit[0] : credits;
    }
    
    /**
     * <strong>&lt;media:credit&gt;</strong></p>
     *
     * <p>Notable entity and the contribution to the creation of the media object. Current entities can include people, companies, locations, etc. Specific entities can have multiple roles, and several entities can have the same role.
     * These should appear as distinct &lt;media:credit&gt; elements.
     * It has 2 optional attributes.</p>
     * <pre>        &lt;media:credit role="producer" scheme="urn:ebu"&gt;entity name&lt;/media:credit&gt;
     * </pre>
     * <p>role specifies the role the entity played. Must be lowercase. It is an optional attribute.</p>
     *
     * <p><em>scheme</em> is the URI that identifies the role scheme. It is an optional attribute. If this attribute is not included, the default scheme is 'urn:ebu'. See: European Broadcasting Union Role Codes.</p>
     *
     *
     * <p>Example roles:</p>
     * <pre>        actor
     *        anchor person
     *        author
     *        choreographer
     *        composer
     *        conductor
     *        director
     *        editor
     *        graphic designer
     *        grip
     *        illustrator
     *        lyricist
     *        music arranger
     *        music group
     *        musician
     *        orchestra
     *        performer
     *        photographer
     *        producer
     *        reporter
     *        vocalist
     * </pre>
     * <p>Additional roles: <a href="http://www.ebu.ch/en/technical/metadata/specifications/role_codes.php">European Broadcasting Union Role Codes</a>
     * @return credits for the time.
     */
    public Credit[] getCredits() {
        return credits;
    }
    
    /**
     * <strong>&lt;media:description&gt;</strong></p>
     * <p>Short description describing the media object typically a sentence in length. It has 1 optional attribute.</p>
     * <pre>        &lt;media:description type="plain"&gt;This was some really bizarre band I listened to as a young lad.&lt;/media:description&gt;</pre>
     * <p><em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be entity-encoded. It is an optional attribute.</p>
     * @param description value of the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * <strong>&lt;media:description&gt;</strong></p>
     * <p>Short description describing the media object typically a sentence in length. It has 1 optional attribute.</p>
     * <pre>        &lt;media:description type="plain"&gt;This was some really bizarre band I listened to as a young lad.&lt;/media:description&gt;</pre>
     * <p><em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be entity-encoded. It is an optional attribute.</p>
     * @return value of the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * <strong>&lt;media:description&gt;</strong></p>
     * <p>Short description describing the media object typically a sentence in length. It has 1 optional attribute.</p>
     * <pre>        &lt;media:description type="plain"&gt;This was some really bizarre band I listened to as a young lad.&lt;/media:description&gt;</pre>
     * <p><em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be entity-encoded. It is an optional attribute.</p>
     * @param descriptionType type of the description.
     */
    public void setDescriptionType(String descriptionType) {
        this.descriptionType = descriptionType;
    }
    
    /**
     * <strong>&lt;media:description&gt;</strong></p>
     * <p>Short description describing the media object typically a sentence in length. It has 1 optional attribute.</p>
     * <pre>        &lt;media:description type="plain"&gt;This was some really bizarre band I listened to as a young lad.&lt;/media:description&gt;</pre>
     * <p><em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be entity-encoded. It is an optional attribute.</p>
     * @return type of the description
     */
    public String getDescriptionType() {
        return descriptionType;
    }
    
    /**
     * <strong>&lt;media:hash&gt;</strong></p>
     *
     * <p>This is the hash of the binary media file. It can appear multiple times as long as each instance is a different <em>algo</em>. It has 1 optional attribute.</p><p></p>
     *
     * <pre>        &lt;media:hash algo="md5"&gt;dfdec888b72151965a34b4b59031290a&lt;/media:hash&gt;</pre>
     *
     * <p><em>algo</em> indicates the algorithm used to create the hash. Possible values are 'md5' and 'sha-1'. Default value is 'md5'. It is an optional attribute.
     * @param hash sets the hash for the item.
     */
    public void setHash(Hash hash) {
        this.hash = hash;
    }
    
    /**
     * <strong>&lt;media:hash&gt;</strong></p>
     *
     * <p>This is the hash of the binary media file. It can appear multiple times as long as each instance is a different <em>algo</em>. It has 1 optional attribute.</p><p></p>
     *
     * <pre>        &lt;media:hash algo="md5"&gt;dfdec888b72151965a34b4b59031290a&lt;/media:hash&gt;</pre>
     *
     * <p><em>algo</em> indicates the algorithm used to create the hash. Possible values are 'md5' and 'sha-1'. Default value is 'md5'. It is an optional attribute.
     * @return returns a Hash object for the item.
     */
    public Hash getHash() {
        return hash;
    }
    
    /**
     * <strong>&lt;media:keywords&gt;</strong></p>
     * <p>Highly relevant keywords describing the media object with typically a maximum of ten words. The keywords and phrases should be comma delimited.</p>
     *
     * <pre>        &lt;media:keywords&gt;kitty, cat, big dog, yarn, fluffy&lt;/media:keywords&gt;</pre>
     * @param keywords Array of keywords
     */
    public void setKeywords(String[] keywords) {
        this.keywords = (keywords == null) ? new String[0] : keywords;
    }
    
    /**
     * <strong>&lt;media:keywords&gt;</strong></p>
     * <p>Highly relevant keywords describing the media object with typically a maximum of ten words. The keywords and phrases should be comma delimited.</p>
     *
     * <pre>        &lt;media:keywords&gt;kitty, cat, big dog, yarn, fluffy&lt;/media:keywords&gt;</pre>
     * @return Array of keywords
     */
    public String[] getKeywords() {
        return keywords;
    }
    
    /**
     * <strong>&lt;media:rating&gt;</strong></p>
     *
     *
     * <p>This allows the permissible audience to be declared. If this element is not included, it assumes that no restrictions are necessary. It has one optional attribute.</p>
     *
     * <pre>               &lt;media:rating scheme="urn:simple"&gt;adult&lt;/media:rating&gt;
     *               &lt;media:rating scheme="urn:icra"&gt;r (cz 1 lz 1 nz 1 oz 1 vz 1)&lt;/media:rating&gt;
     *               &lt;media:rating scheme="urn:mpaa"&gt;pg&lt;/media:rating&gt;
     *
     *               &lt;media:rating scheme="urn:v-chip"&gt;tv-y7-fv&lt;/media:rating&gt;</pre>
     *
     *
     * <p><em>scheme</em> is the URI that identifies the rating scheme. It is an optional attribute. If this attribute is not included, the default scheme is urn:simple (adult | nonadult).</p>
     *
     * <p>
     * @param ratings Ratings objects
     */
    public void setRatings(Rating[] ratings) {
        this.ratings = (ratings == null) ? new Rating[0] : ratings;
    }
    
    /**
     * <strong>&lt;media:rating&gt;</strong></p>
     *
     *
     * <p>This allows the permissible audience to be declared. If this element is not included, it assumes that no restrictions are necessary. It has one optional attribute.</p>
     *
     * <pre>               &lt;media:rating scheme="urn:simple"&gt;adult&lt;/media:rating&gt;
     *               &lt;media:rating scheme="urn:icra"&gt;r (cz 1 lz 1 nz 1 oz 1 vz 1)&lt;/media:rating&gt;
     *               &lt;media:rating scheme="urn:mpaa"&gt;pg&lt;/media:rating&gt;
     *
     *               &lt;media:rating scheme="urn:v-chip"&gt;tv-y7-fv&lt;/media:rating&gt;</pre>
     *
     *
     * <p><em>scheme</em> is the URI that identifies the rating scheme. It is an optional attribute. If this attribute is not included, the default scheme is urn:simple (adult | nonadult).</p>
     *
     * <p>
     * @return Ratings objects
     */
    public Rating[] getRatings() {
        return ratings;
    }
    
    /**
     * <strong>&lt;media:restriction&gt; </strong></p>
     *
     * <p>Allows restrictions to be placed on the aggregator rendering the media in the feed. Currently, restrictions are based on distributor (uri) and country codes.  This element is purely informational and no obligation can be assumed or implied.
     * Only one &lt;media:restriction&gt; element of the same <em>type</em> can be applied to a media object - all others will be ignored.&nbsp;Entities in this element should be space separated. To allow the producer to explicitly declare his/her intentions, two literals are reserved: 'all', 'none'. These literals can only be used once. This element has 1 required attribute, and 1 optional attribute (with strict requirements for its exclusion).</p>
     *
     * <pre>        &lt;media:restriction relationship="allow" type="country"&gt;au us&lt;/media:restriction&gt;</pre>
     *
     * <p><em>relationship</em> indicates the type of relationship that the restriction represents (allow | deny). In the example above, the media object should only be syndicated in Australia and the United States. It is a required attribute.</p>
     * @param restrictions restrictions for the item.
     */
    public void setRestrictions(Restriction[] restrictions) {
        this.restrictions = (restrictions == null) ? new Restriction[0]
                : restrictions;
    }
    
    /**
     * <strong>&lt;media:restriction&gt; </strong></p>
     *
     * <p>Allows restrictions to be placed on the aggregator rendering the media in the feed. Currently, restrictions are based on distributor (uri) and country codes.  This element is purely informational and no obligation can be assumed or implied.
     * Only one &lt;media:restriction&gt; element of the same <em>type</em> can be applied to a media object - all others will be ignored.&nbsp;Entities in this element should be space separated. To allow the producer to explicitly declare his/her intentions, two literals are reserved: 'all', 'none'. These literals can only be used once. This element has 1 required attribute, and 1 optional attribute (with strict requirements for its exclusion).</p>
     *
     * <pre>        &lt;media:restriction relationship="allow" type="country"&gt;au us&lt;/media:restriction&gt;</pre>
     *
     * <p><em>relationship</em> indicates the type of relationship that the restriction represents (allow | deny). In the example above, the media object should only be syndicated in Australia and the United States. It is a required attribute.</p>
     * @return restrictions for the item.
     */
    public Restriction[] getRestrictions() {
        return restrictions;
    }
    
    /**
     * <strong>&lt;media:text&gt;</strong></p>
     * <p>Allows the inclusion of a text transcript, closed captioning, or lyrics of the media content. Many of these elements are permitted to provide a time series of text. In such cases, it is encouraged, but not required, that the elements be grouped by language and appear in time sequence order based on the <em>start</em> time. Elements can have overlapping <em>start</em> and <em>end</em> times. It has 4 optional attributes.</p><pre>        &lt;media:text type="plain" lang="en" start="00:00:03.000"
     *        end="00:00:10.000"&gt; Oh, say, can you see&lt;/media:text&gt;
     *
     *        &lt;media:text type="plain" lang="en" start="00:00:10.000"
     *        end="00:00:17.000"&gt;By the dawn's early light&lt;/media:text&gt;
     * </pre>
     * <p><em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be entity-encoded. It is an optional attribute.</p>
     *
     *
     *
     *
     *
     *
     * <p><em>lang</em> is the primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066. This attribute is used similar to the xml:lang attribute detailed in the XML 1.0 Specification (Third Edition). It is an optional attribute.</p>
     *
     * <p><em>start</em> specifies the start time offset that the text starts being relevant to the media object. An example of this would be for closed captioning.
     * It uses the NTP time code format (see: the time attribute used in &lt;media:thumbnail&gt;).&nbsp;It is an optional attribute.</p>
     *
     * <p><em>end</em> specifies the end time that the text is relevant.
     * If this attribute is not provided, and a <em>start</em> time is used, it is expected that the end time is either the end of the clip or the start of the next &lt;media:text&gt; element.
     * @param text text objects for the item.
     */
    public void setText(Text[] text) {
        this.text = (text == null) ? new Text[0] : text;
    }
    
    /**
     * <strong>&lt;media:text&gt;</strong></p>
     * <p>Allows the inclusion of a text transcript, closed captioning, or lyrics of the media content. Many of these elements are permitted to provide a time series of text. In such cases, it is encouraged, but not required, that the elements be grouped by language and appear in time sequence order based on the <em>start</em> time. Elements can have overlapping <em>start</em> and <em>end</em> times. It has 4 optional attributes.</p><pre>        &lt;media:text type="plain" lang="en" start="00:00:03.000"
     *        end="00:00:10.000"&gt; Oh, say, can you see&lt;/media:text&gt;
     *
     *        &lt;media:text type="plain" lang="en" start="00:00:10.000"
     *        end="00:00:17.000"&gt;By the dawn's early light&lt;/media:text&gt;
     * </pre>
     * <p><em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be entity-encoded. It is an optional attribute.</p>
     *
     *
     *
     *
     *
     *
     * <p><em>lang</em> is the primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066. This attribute is used similar to the xml:lang attribute detailed in the XML 1.0 Specification (Third Edition). It is an optional attribute.</p>
     *
     * <p><em>start</em> specifies the start time offset that the text starts being relevant to the media object. An example of this would be for closed captioning.
     * It uses the NTP time code format (see: the time attribute used in &lt;media:thumbnail&gt;).&nbsp;It is an optional attribute.</p>
     *
     * <p><em>end</em> specifies the end time that the text is relevant.
     * If this attribute is not provided, and a <em>start</em> time is used, it is expected that the end time is either the end of the clip or the start of the next &lt;media:text&gt; element.
     * @return Text objects for the item.
     */
    public Text[] getText() {
        return text;
    }
    
    /**
     * <strong>&lt;media:thumbnail&gt;</strong></p>
     *
     *
     * <p>Allows particular images to be used as representative images for the media object. If multiple thumbnails are included, and time coding is not at play, it is assumed that the images are in order of importance. It has 1 required attribute and 3 optional attributes.</p>
     *
     *
     *
     *
     *
     *         <pre>        &lt;media:thumbnail url="http://www.foo.com/keyframe.jpg" width="75" height="50" time="12:05:01.123" /&gt;</pre>
     * <p><em>url</em> specifies the url of the thumbnail. It is a required attribute.</p>        <p> <em>height</em> specifies the height of the thumbnail. It is an optional attribute.</p>
     *        <p> <em>width</em> specifies the width of the thumbnail. It is an optional attribute.</p>
     *
     *
     * <p><em>time</em>
     * specifies the time offset in relation to the media object. Typically this is used when creating multiple keyframes within a single video. The format for this attribute should be in the DSM-CC's Normal Play Time (NTP) as used in RTSP [<a href="http://www.ietf.org/rfc/rfc2326.txt">RFC 2326 3.6 Normal Play Time</a>]. It is an optional attribute.</p>
     * @param thumbnail thumbnails for the image
     */
    public void setThumbnail(Thumbnail[] thumbnail) {
        this.thumbnail = (thumbnail == null) ? new Thumbnail[0] : thumbnail;
    }
    
    /**
     * <strong>&lt;media:thumbnail&gt;</strong></p>
     *
     *
     * <p>Allows particular images to be used as representative images for the media object. If multiple thumbnails are included, and time coding is not at play, it is assumed that the images are in order of importance. It has 1 required attribute and 3 optional attributes.</p>
     *
     *
     *
     *
     *
     *         <pre>        &lt;media:thumbnail url="http://www.foo.com/keyframe.jpg" width="75" height="50" time="12:05:01.123" /&gt;</pre>
     * <p><em>url</em> specifies the url of the thumbnail. It is a required attribute.</p>        <p> <em>height</em> specifies the height of the thumbnail. It is an optional attribute.</p>
     *        <p> <em>width</em> specifies the width of the thumbnail. It is an optional attribute.</p>
     *
     *
     * <p><em>time</em>
     * specifies the time offset in relation to the media object. Typically this is used when creating multiple keyframes within a single video. The format for this attribute should be in the DSM-CC's Normal Play Time (NTP) as used in RTSP [<a href="http://www.ietf.org/rfc/rfc2326.txt">RFC 2326 3.6 Normal Play Time</a>]. It is an optional attribute.</p>
     * @return Thumbnails for the image
     */
    public Thumbnail[] getThumbnail() {
        return thumbnail;
    }
    
    /**
     * <strong>&lt;media:title&gt;</strong></p>
     * <p>The title of the particular media object.  It has 1 optional attribute.</p>
     * <pre>        &lt;media:title type="plain"&gt;The Judy's - The Moo Song&lt;/media:title&gt;</pre>
     *
     * <p><em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be entity-encoded. It is an optional attribute.</p>
     * @param title Value of the title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * <strong>&lt;media:title&gt;</strong></p>
     * <p>The title of the particular media object.  It has 1 optional attribute.</p>
     * <pre>        &lt;media:title type="plain"&gt;The Judy's - The Moo Song&lt;/media:title&gt;</pre>
     *
     * <p><em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be entity-encoded. It is an optional attribute.</p>
     * @return value of the title.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * <strong>&lt;media:title&gt;</strong></p>
     * <p>The title of the particular media object.  It has 1 optional attribute.</p>
     * <pre>        &lt;media:title type="plain"&gt;The Judy's - The Moo Song&lt;/media:title&gt;</pre>
     *
     * <p><em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be entity-encoded. It is an optional attribute.</p>
     * @param titleType type of the title.
     */
    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }
    
    /**
     * <strong>&lt;media:title&gt;</strong></p>
     * <p>The title of the particular media object.  It has 1 optional attribute.</p>
     * <pre>        &lt;media:title type="plain"&gt;The Judy's - The Moo Song&lt;/media:title&gt;</pre>
     *
     * <p><em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be entity-encoded. It is an optional attribute.</p>
     * @return type of the title
     */
    public String getTitleType() {
        return titleType;
    }
    
    /**
     * <strong>&lt;media:copyright&gt;</strong></p>
     * <p>Copyright information for media object.  It has 1 optional attribute.</p>
     *
     * <pre>        &lt;media:copyright url="http://blah.com/additional-info.html"&gt;2005 FooBar Media&lt;/media:copyright&gt;</pre>
     * <p><em>url</em> is the url for a terms of use page or additional copyright information. If the media is operating under a Creative Commons license, the Creative Commons module should be used instead. It is an optional attribute.</p>
     * @return Link to more copyright information.
     */
    public Object clone() {
        Metadata md = new Metadata();
        md.setCategories(getCategories());
        md.setCopyright(getCopyright());
        md.setCopyrightUrl(getCopyrightUrl());
        md.setCredits(getCredits());
        md.setDescription(getDescription());
        md.setDescriptionType(getDescriptionType());
        md.setHash(getHash());
        md.setKeywords(getKeywords());
        md.setRatings(getRatings());
        md.setText(getText());
        md.setThumbnail(getThumbnail());
        md.setTitle(getTitle());
        md.setTitleType(getTitleType());
        md.setRestrictions(getRestrictions());
        
        return md;
    }
    
    public boolean equals(Object obj) {
        EqualsBean eBean = new EqualsBean(Metadata.class, this);
        
        return eBean.beanEquals(obj);
    }
    
    public int hashCode() {
        EqualsBean equals = new EqualsBean(Metadata.class, this);
        
        return equals.beanHashCode();
    }
    
    public String toString() {
        ToStringBean tsBean = new ToStringBean(Metadata.class, this);
        
        return tsBean.toString();
    }
}
