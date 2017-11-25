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
package com.rometools.modules.mediarss.types;

import java.io.Serializable;
import java.net.URI;
import java.net.URL;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

/**
 * 
 * 
 * 
 * Optional Elements
 * <p>
 * The following elements are optional and may appear as sub-elements of &lt;channel&gt;, &lt;item&gt;, &lt;media:content&gt; and/or &lt;media:group&gt;.
 * </p>
 * <p>
 * When an element appears at a shallow level, such as &lt;channel&gt; or &lt;item&gt;, it means that the element should be applied to every media object within
 * its scope.
 * </p>
 * 
 * <p>
 * Duplicated elements appearing at deeper levels of the document tree have higher priority over other levels.
 * 
 * For example, &lt;media:content&gt; level elements are favored over &lt;item&gt; level elements. The priority level is listed from strongest to weakest:
 * &lt;media:content&gt;, &lt;media:group&gt;, &lt;item&gt;, &lt;channel&gt;.
 * </p>
 */
public class Metadata implements Cloneable, Serializable {
    private static final long serialVersionUID = 649350950456005250L;
    /**
     * status is the status of the media object saying whether a media object has been created by the publisher or they have rights to circulate it.
     */
    public enum RightsStatus {
        userCreated, official
    };

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
    private Community community;
    private String[] comments = new String[0];
    private String[] responses = new String[0];
    private URL[] backLinks = new URL[0];
    private Status status;
    private Price[] prices = new Price[0];
    private Embed embed;
    private License[] licenses = new License[0];
    private SubTitle[] subTitles = new SubTitle[0];
    private PeerLink[] peerLinks = new PeerLink[0];
    private Location[] locations = new Location[0];
    private RightsStatus rights;
    private Scene[] scenes = new Scene[0];

    public Metadata() {
        super();
    }

    /**
     * <p>
     * <strong>&lt;media:backLinks&gt;</strong>
     * </p>
     * 
     * <p>
     * Allows inclusion of all the URLs pointing to a media object.
     * </p>
     * 
     * <pre>
     * &lt;media:backLinks&gt;
     *   &lt;media:backLink&gt;http://www.backlink1.com&lt;/media:backLink&gt;
     *   &lt;media:backLink&gt;http://www.backlink2.com&lt;/media:backLink&gt;
     *   &lt;media:backLink&gt;http://www.backlink3.com&lt;/media:backLink&gt;
     * &lt;/media:backLinks&gt;
     * </pre>
     * 
     * @return array of backlink urls
     */
    public URL[] getBackLinks() {
        return backLinks;
    }

    /**
     * <p>
     * <strong>&lt;media:backLinks&gt;</strong>
     * </p>
     * 
     * <p>
     * Allows inclusion of all the URLs pointing to a media object.
     * </p>
     * 
     * <pre>
     * &lt;media:backLinks&gt;
     *   &lt;media:backLink&gt;http://www.backlink1.com&lt;/media:backLink&gt;
     *   &lt;media:backLink&gt;http://www.backlink2.com&lt;/media:backLink&gt;
     *   &lt;media:backLink&gt;http://www.backlink3.com&lt;/media:backLink&gt;
     * &lt;/media:backLinks&gt;
     * </pre>
     * 
     * @param backLinks array of backlink urls
     */
    public void setBackLinks(final URL[] backLinks) {
        if (backLinks == null) {
            this.backLinks = new URL[0];
        } else {
            this.backLinks = backLinks;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:category&gt;</strong>
     * </p>
     * <p>
     * Allows a taxonomy to be set that gives an indication of the type of media content, and its particular contents. It has 2 optional attributes.
     * </p>
     * 
     * <pre>
     * &lt;media:category scheme="http://search.yahoo.com/mrss/category_
     *        schema"&gt;music/artist/album/song&lt;/media:category&gt;
     * 
     *        &lt;media:category scheme="http://dmoz.org" label="Ace Ventura - Pet
     *        Detective"&gt;Arts/Movies/Titles/A/Ace_Ventura_Series/Ace_Ventura_
     *        -_Pet_Detective&lt;/media:category&gt;
     * 
     *        &lt;media:category scheme="urn:flickr:tags"&gt;ycantpark
     *        mobile&lt;/media:category&gt;
     * </pre>
     * 
     * <p>
     * <em>scheme</em> is the URI that identifies the categorization scheme. It is an optional attribute. If this attribute is not included, the default scheme
     * is 'http://search.yahoo.com/mrss/category_schema'.
     * </p>
     * 
     * <p>
     * <em>label</em> is the human readable label that can be displayed in end user applications. It is an optional attribute.
     * </p>
     * 
     * @param categories categories for the item
     */
    public void setCategories(final Category[] categories) {
        if (categories == null) {
            this.categories = new Category[0];
        } else {
            this.categories = categories;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:category&gt;</strong>
     * </p>
     * <p>
     * Allows a taxonomy to be set that gives an indication of the type of media content, and its particular contents. It has 2 optional attributes.
     * </p>
     * 
     * <pre>
     * &lt;media:category scheme="http://search.yahoo.com/mrss/category_
     *        schema"&gt;music/artist/album/song&lt;/media:category&gt;
     * 
     *        &lt;media:category scheme="http://dmoz.org" label="Ace Ventura - Pet
     *        Detective"&gt;Arts/Movies/Titles/A/Ace_Ventura_Series/Ace_Ventura_
     *        -_Pet_Detective&lt;/media:category&gt;
     * 
     *        &lt;media:category scheme="urn:flickr:tags"&gt;ycantpark
     *        mobile&lt;/media:category&gt;
     * </pre>
     * 
     * <p>
     * <em>scheme</em> is the URI that identifies the categorization scheme. It is an optional attribute. If this attribute is not included, the default scheme
     * is 'http://search.yahoo.com/mrss/category_schema'.
     * </p>
     * 
     * <p>
     * <em>label</em> is the human readable label that can be displayed in end user applications. It is an optional attribute.
     * </p>
     * 
     * @return categories for the item.
     */
    public Category[] getCategories() {
        return categories;
    }

    /**
     * <p>
     * <strong>&lt;media:comments&gt;</strong>
     * </p>
     * 
     * <p>
     * Allows inclusion of all the comments a media object has received.
     * </p>
     * 
     * <pre>
     * &lt;media:comments&gt;
     *   &lt;media:comment&gt;comment1&lt;/media:comment&gt;
     *   &lt;media:comment&gt;comment2&lt;/media:comment&gt;
     *   &lt;media:comment&gt;comment3&lt;/media:comment&gt;
     * &lt;/media:comments&gt;
     * </pre>
     * 
     * @return array of comments
     */
    public String[] getComments() {
        return comments;
    }

    /**
     * <p>
     * <strong>&lt;media:comments&gt;</strong>
     * </p>
     * 
     * <p>
     * Allows inclusion of all the comments a media object has received.
     * </p>
     * 
     * <pre>
     * &lt;media:comments&gt;
     *   &lt;media:comment&gt;comment1&lt;/media:comment&gt;
     *   &lt;media:comment&gt;comment2&lt;/media:comment&gt;
     *   &lt;media:comment&gt;comment3&lt;/media:comment&gt;
     * &lt;/media:comments&gt;
     * </pre>
     * 
     * @param comments array of comments
     */
    public void setComments(final String[] comments) {
        if (comments == null) {
            this.comments = new String[0];
        } else {
            this.comments = comments;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:community&gt;</strong>
     * </p>
     * 
     * <p>
     * This element stands for the community related content. This allows inclusion of the user perception about a media object in the form of view count,
     * ratings and tags.
     * </p>
     * 
     * <pre>
     * &lt;media:community&gt;
     *   &lt;media:starRating average="3.5" count="20" min="1" max="10" /&gt;
     *   &lt;media:statistics views="5" favorites="5" /&gt;
     *   &lt;media:tags&gt;news: 5, abc:3, reuters&lt;/media:tags&gt;
     * &lt;/media:community&gt;
     * </pre>
     * 
     * <p>
     * starRating This element specifies the rating-related information about a media object. Valid attributes are average, count, min and max.
     * </p>
     * 
     * <p>
     * statistics This element specifies various statistics about a media object like the view count and the favorite count. Valid attributes are views and
     * favorites.
     * </p>
     * 
     * <p>
     * tags This element contains user-generated tags separated by commas in the decreasing order of each tag's weight. Each tag can be assigned an integer
     * weight in tag_name:weight format. It's up to the provider to choose the way weight is determined for a tag; for example, number of occurences can be one
     * way to decide weight of a particular tag. Default weight is 1.
     * </p>
     * 
     * @return Community element
     */
    public Community getCommunity() {
        return community;
    }

    /**
     * <p>
     * <strong>&lt;media:community&gt;</strong>
     * </p>
     * 
     * <p>
     * This element stands for the community related content. This allows inclusion of the user perception about a media object in the form of view count,
     * ratings and tags.
     * </p>
     * 
     * <pre>
     * &lt;media:community&gt;
     *   &lt;media:starRating average="3.5" count="20" min="1" max="10" /&gt;
     *   &lt;media:statistics views="5" favorites="5" /&gt;
     *   &lt;media:tags&gt;news: 5, abc:3, reuters&lt;/media:tags&gt;
     * &lt;/media:community&gt;
     * </pre>
     * 
     * <p>
     * starRating This element specifies the rating-related information about a media object. Valid attributes are average, count, min and max.
     * </p>
     * 
     * <p>
     * statistics This element specifies various statistics about a media object like the view count and the favorite count. Valid attributes are views and
     * favorites.
     * </p>
     * 
     * <p>
     * tags This element contains user-generated tags separated by commas in the decreasing order of each tag's weight. Each tag can be assigned an integer
     * weight in tag_name:weight format. It's up to the provider to choose the way weight is determined for a tag; for example, number of occurences can be one
     * way to decide weight of a particular tag. Default weight is 1.
     * </p>
     * 
     * @param community Community element
     */
    public void setCommunity(final Community community) {
        this.community = community;
    }

    /**
     * <p>
     * <strong>&lt;media:copyright&gt;</strong>
     * </p>
     * <p>
     * Copyright information for media object. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:copyright url="http://blah.com/additional-info.html"&gt;2005 FooBar Media&lt;/media:copyright&gt;
     * </pre>
     * <p>
     * <em>url</em> is the url for a terms of use page or additional copyright information. If the media is operating under a Creative Commons license, the
     * Creative Commons module should be used instead. It is an optional attribute.
     * </p>
     * 
     * @param copyright copyright text
     */
    public void setCopyright(final String copyright) {
        this.copyright = copyright;
    }

    /**
     * <p>
     * <strong>&lt;media:copyright&gt;</strong>
     * </p>
     * <p>
     * Copyright information for media object. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:copyright url="http://blah.com/additional-info.html"&gt;2005 FooBar Media&lt;/media:copyright&gt;
     * </pre>
     * <p>
     * <em>url</em> is the url for a terms of use page or additional copyright information. If the media is operating under a Creative Commons license, the
     * Creative Commons module should be used instead. It is an optional attribute.
     * </p>
     * 
     * @return Copyright text
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * <p>
     * <strong>&lt;media:copyright&gt;</strong>
     * </p>
     * <p>
     * Copyright information for media object. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:copyright url="http://blah.com/additional-info.html"&gt;2005 FooBar Media&lt;/media:copyright&gt;
     * </pre>
     * <p>
     * <em>url</em> is the url for a terms of use page or additional copyright information. If the media is operating under a Creative Commons license, the
     * Creative Commons module should be used instead. It is an optional attribute.
     * </p>
     * 
     * @param copyrightUrl link to more copyright information.
     */
    public void setCopyrightUrl(final URI copyrightUrl) {
        this.copyrightUrl = copyrightUrl;
    }

    /**
     * <p>
     * <strong>&lt;media:copyright&gt;</strong>
     * </p>
     * <p>
     * Copyright information for media object. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:copyright url="http://blah.com/additional-info.html"&gt;2005 FooBar Media&lt;/media:copyright&gt;
     * </pre>
     * <p>
     * <em>url</em> is the url for a terms of use page or additional copyright information. If the media is operating under a Creative Commons license, the
     * Creative Commons module should be used instead. It is an optional attribute.
     * </p>
     * 
     * @return Link to more copyright information.
     */
    public URI getCopyrightUrl() {
        return copyrightUrl;
    }

    /**
     * <p>
     * <strong>&lt;media:credit&gt;</strong>
     * </p>
     * 
     * <p>
     * Notable entity and the contribution to the creation of the media object. Current entities can include people, companies, locations, etc. Specific
     * entities can have multiple roles, and several entities can have the same role. These should appear as distinct &lt;media:credit&gt; elements. It has 2
     * optional attributes.
     * </p>
     * 
     * <pre>
     * &lt;media:credit role="producer" scheme="urn:ebu"&gt;entity name&lt;/media:credit&gt;
     * </pre>
     * <p>
     * role specifies the role the entity played. Must be lowercase. It is an optional attribute.
     * </p>
     * 
     * <p>
     * <em>scheme</em> is the URI that identifies the role scheme. It is an optional attribute. If this attribute is not included, the default scheme is
     * 'urn:ebu'. See: European Broadcasting Union Role Codes.
     * </p>
     * 
     * 
     * <p>
     * Example roles:
     * </p>
     * 
     * <pre>
     * actor
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
     * <p>
     * Additional roles: <a href="http://www.ebu.ch/en/technical/metadata/specifications/role_codes.php">European Broadcasting Union Role Codes</a>
     * 
     * @param credits credits for the item.
     */
    public void setCredits(final Credit[] credits) {
        if (credits == null) {
            this.credits = new Credit[0];
        } else {
            this.credits = credits;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:credit&gt;</strong>
     * </p>
     * 
     * <p>
     * Notable entity and the contribution to the creation of the media object. Current entities can include people, companies, locations, etc. Specific
     * entities can have multiple roles, and several entities can have the same role. These should appear as distinct &lt;media:credit&gt; elements. It has 2
     * optional attributes.
     * </p>
     * 
     * <pre>
     * &lt;media:credit role="producer" scheme="urn:ebu"&gt;entity name&lt;/media:credit&gt;
     * </pre>
     * <p>
     * role specifies the role the entity played. Must be lowercase. It is an optional attribute.
     * </p>
     * 
     * <p>
     * <em>scheme</em> is the URI that identifies the role scheme. It is an optional attribute. If this attribute is not included, the default scheme is
     * 'urn:ebu'. See: European Broadcasting Union Role Codes.
     * </p>
     * 
     * 
     * <p>
     * Example roles:
     * </p>
     * 
     * <pre>
     * actor
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
     * <p>
     * Additional roles: <a href="http://www.ebu.ch/en/technical/metadata/specifications/role_codes.php">European Broadcasting Union Role Codes</a>
     * 
     * @return credits for the time.
     */
    public Credit[] getCredits() {
        return credits;
    }

    /**
     * <p>
     * <strong>&lt;media:description&gt;</strong>
     * </p>
     * <p>
     * Short description describing the media object typically a sentence in length. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:description type="plain"&gt;This was some really bizarre band I listened to as a young lad.&lt;/media:description&gt;
     * </pre>
     * <p>
     * <em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be
     * entity-encoded. It is an optional attribute.
     * </p>
     * 
     * @param description value of the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * <p>
     * <strong>&lt;media:description&gt;</strong>
     * </p>
     * <p>
     * Short description describing the media object typically a sentence in length. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:description type="plain"&gt;This was some really bizarre band I listened to as a young lad.&lt;/media:description&gt;
     * </pre>
     * <p>
     * <em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be
     * entity-encoded. It is an optional attribute.
     * </p>
     * 
     * @return value of the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * <p>
     * <strong>&lt;media:description&gt;</strong>
     * </p>
     * <p>
     * Short description describing the media object typically a sentence in length. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:description type="plain"&gt;This was some really bizarre band I listened to as a young lad.&lt;/media:description&gt;
     * </pre>
     * <p>
     * <em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be
     * entity-encoded. It is an optional attribute.
     * </p>
     * 
     * @param descriptionType type of the description.
     */
    public void setDescriptionType(final String descriptionType) {
        this.descriptionType = descriptionType;
    }

    /**
     * <p>
     * <strong>&lt;media:description&gt;</strong>
     * </p>
     * <p>
     * Short description describing the media object typically a sentence in length. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:description type="plain"&gt;This was some really bizarre band I listened to as a young lad.&lt;/media:description&gt;
     * </pre>
     * <p>
     * <em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be
     * entity-encoded. It is an optional attribute.
     * </p>
     * 
     * @return type of the description
     */
    public String getDescriptionType() {
        return descriptionType;
    }

    /**
     * <p><strong>&lt;media:embed&gt;</strong>
     * 
     * <p>Sometimes player-specific embed code is needed for a player to play any video. &lt;media:embed&gt; allows
     * inclusion of such information in the form of key-value pairs.</p>
     * 
     * <pre>&lt;media:embed url="http://d.yimg.com/static.video.yahoo.com/yep/YV_YEP.swf?ver=2.2.2" width="512" height="323"&gt;
     *   &lt;media:param name="type"&gt;application/x-shockwave-flash&lt;/media:param&gt;
     *   &lt;media:param name="width"&gt;512&lt;/media:param&gt;
     *   &lt;media:param name="height"&gt;323&lt;/media:param&gt;
     *   &lt;media:param name="allowFullScreen"&gt;true&lt;/media:param&gt;
     *   &lt;media:param name="flashVars"&gt;
     *     id=7809705&amp;vid=2666306&amp;lang=en-us&amp;intl=us&amp;thumbUrl=http%3A//us.i1.yimg.com/us.yimg.com/i/us/sch/cn/video06/2666306_rndf1e4205b_19.jpg
     *   &lt;/media:param&gt;
     * &lt;/media:embed&gt;</pre>
     * 
     * @return embed information
     */
    public Embed getEmbed() {
        return embed;
    }

    /**
     * <p><strong>&lt;media:embed&gt;</strong>
     * 
     * <p>Sometimes player-specific embed code is needed for a player to play any video. &lt;media:embed&gt; allows
     * inclusion of such information in the form of key-value pairs.</p>
     * 
     * <pre>&lt;media:embed url="http://d.yimg.com/static.video.yahoo.com/yep/YV_YEP.swf?ver=2.2.2" width="512" height="323"&gt;
     *   &lt;media:param name="type"&gt;application/x-shockwave-flash&lt;/media:param&gt;
     *   &lt;media:param name="width"&gt;512&lt;/media:param&gt;
     *   &lt;media:param name="height"&gt;323&lt;/media:param&gt;
     *   &lt;media:param name="allowFullScreen"&gt;true&lt;/media:param&gt;
     *   &lt;media:param name="flashVars"&gt;
     *     id=7809705&amp;vid=2666306&amp;lang=en-us&amp;intl=us&amp;thumbUrl=http%3A//us.i1.yimg.com/us.yimg.com/i/us/sch/cn/video06/2666306_rndf1e4205b_19.jpg
     *   &lt;/media:param&gt;
     * &lt;/media:embed&gt;</pre>
     * 
     * @param embed embed information
     */
    public void setEmbed(final Embed embed) {
        this.embed = embed;
    }

    /**
     * <p>
     * <strong>&lt;media:hash&gt;</strong>
     * </p>
     * 
     * <p>
     * This is the hash of the binary media file. It can appear multiple times as long as each instance is a different <em>algo</em>. It has 1 optional
     * attribute.
     * </p>
     * <p>
     * </p>
     * 
     * <pre>
     * &lt;media:hash algo="md5"&gt;dfdec888b72151965a34b4b59031290a&lt;/media:hash&gt;
     * </pre>
     * 
     * <p>
     * <em>algo</em> indicates the algorithm used to create the hash. Possible values are 'md5' and 'sha-1'. Default value is 'md5'. It is an optional
     * attribute.
     * 
     * @param hash sets the hash for the item.
     */
    public void setHash(final Hash hash) {
        this.hash = hash;
    }

    /**
     * <p>
     * <strong>&lt;media:hash&gt;</strong>
     * </p>
     * 
     * <p>
     * This is the hash of the binary media file. It can appear multiple times as long as each instance is a different <em>algo</em>. It has 1 optional
     * attribute.
     * </p>
     * <p>
     * </p>
     * 
     * <pre>
     * &lt;media:hash algo="md5"&gt;dfdec888b72151965a34b4b59031290a&lt;/media:hash&gt;
     * </pre>
     * 
     * <p>
     * <em>algo</em> indicates the algorithm used to create the hash. Possible values are 'md5' and 'sha-1'. Default value is 'md5'. It is an optional
     * attribute.
     * 
     * @return returns a Hash object for the item.
     */
    public Hash getHash() {
        return hash;
    }

    /**
     * <p>
     * <strong>&lt;media:keywords&gt;</strong>
     * </p>
     * <p>
     * Highly relevant keywords describing the media object with typically a maximum of ten words. The keywords and phrases should be comma delimited.
     * </p>
     * 
     * <pre>
     * &lt;media:keywords&gt;kitty, cat, big dog, yarn, fluffy&lt;/media:keywords&gt;
     * </pre>
     * 
     * @param keywords Array of keywords
     */
    public void setKeywords(final String[] keywords) {
        if (keywords == null) {
            this.keywords = new String[0];
        } else {
            this.keywords = keywords;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:keywords&gt;</strong>
     * </p>
     * <p>
     * Highly relevant keywords describing the media object with typically a maximum of ten words. The keywords and phrases should be comma delimited.
     * </p>
     * 
     * <pre>
     * &lt;media:keywords&gt;kitty, cat, big dog, yarn, fluffy&lt;/media:keywords&gt;
     * </pre>
     * 
     * @return Array of keywords
     */
    public String[] getKeywords() {
        return keywords;
    }

    /**
     * <p><strong>&lt;media:license&gt;</strong></p>
     * 
     * <p>Optional link to specify the machine-readable license associated with the content.</p>
     * 
     * <pre>&lt;media:license type="text/html" href="http://creativecommons.org/licenses/by/3.0/us/"&gt;Creative Commons
     * Attribution 3.0 United States License&lt;/media:license&gt;</pre>
     * 
     * @return the licenses
     */
    public License[] getLicenses() {
        return licenses;
    }

    /**
     * <p><strong>&lt;media:license&gt;</strong></p>
     * 
     * <p>Optional link to specify the machine-readable license associated with the content.</p>
     * 
     * <pre>&lt;media:license type="text/html" href="http://creativecommons.org/licenses/by/3.0/us/"&gt;Creative Commons
     * Attribution 3.0 United States License&lt;/media:license&gt;</pre>
     * 
     * @param licenses the licenses
     */
    public void setLicenses(final License[] licenses) {
        if (licenses == null) {
            this.licenses = new License[0];
        } else {
            this.licenses = licenses;
        }
    }

    /**
     * <p><strong>&lt;media:location&gt;</strong></p>
     * 
     * <p>Optional element to specify geographical information about various locations captured in the content of a media object.
     * The format conforms to geoRSS.</p>
     * 
     * <pre>&lt;media:location description="My house" start="00:01" end="01:00"&gt;
     *   &lt;georss:where&gt;
     *     &lt;gml:Point&gt;
     *       &lt;gml:pos&gt;35.669998 139.770004&lt;/gml:pos&gt;
     *     &lt;/gml:Point&gt;
     *   &lt;/georss:where&gt;
     * &lt;/media:location&gt;</pre>
     * 
     * <p>description description of the place whose location is being specified.</p>
     * 
     * <p>start time at which the reference to a particular location starts in the media object.</p>
     * 
     * <p>end time at which the reference to a particular location ends in the media object.</p>
     * 
     * @return the locations
     */
    public Location[] getLocations() {
        return locations;
    }

    /**
     * <p><strong>&lt;media:location&gt;</strong></p>
     * 
     * <p>Optional element to specify geographical information about various locations captured in the content of a media object.
     * The format conforms to geoRSS.</p>
     * 
     * <pre>&lt;media:location description="My house" start="00:01" end="01:00"&gt;
     *   &lt;georss:where&gt;
     *     &lt;gml:Point&gt;
     *       &lt;gml:pos&gt;35.669998 139.770004&lt;/gml:pos&gt;
     *     &lt;/gml:Point&gt;
     *   &lt;/georss:where&gt;
     * &lt;/media:location&gt;</pre>
     * 
     * <p>description description of the place whose location is being specified.</p>
     * 
     * <p>start time at which the reference to a particular location starts in the media object.</p>
     * 
     * <p>end time at which the reference to a particular location ends in the media object.</p>
     * 
     * @param locations the locations
     */
    public void setLocations(final Location[] locations) {
        if (locations == null) {
            this.locations = new Location[0];
        } else {
            this.locations = locations;
        }
    }

    /**
     * <p><strong>&lt;media:peerLink&gt;</strong></p>
     * 
     * <p>Optional element for P2P link.</p>
     * 
     * <pre>&lt;media:peerLink type="application/x-bittorrent" href="http://www.example.org/sampleFile.torrent" /&gt;</pre>
     * 
     * @return the peer links
     */
    public PeerLink[] getPeerLinks() {
        return peerLinks;
    }

    /**
     * <p><strong>&lt;media:peerLink&gt;</strong></p>
     * 
     * <p>Optional element for P2P link.</p>
     * 
     * <pre>&lt;media:peerLink type="application/x-bittorrent" href="http://www.example.org/sampleFile.torrent" /&gt;</pre>
     * 
     * @param peerLinks the peer links
     */
    public void setPeerLinks(final PeerLink[] peerLinks) {
        if (peerLinks == null) {
            this.peerLinks = new PeerLink[0];
        } else {
            this.peerLinks = peerLinks;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:price&gt;</strong>
     * </p>
     * 
     * <p>
     * Optional tag to include pricing information about a media object. If this tag is not present, the media object is supposed to be free. One media object
     * can have multiple instances of this tag for including different pricing structures. The presence of this tag would mean that media object is not free.
     * </p>
     * 
     * <pre>
     * &lt;media:price type="rent" price="19.99" currency="EUR" /&gt;
     * &lt;media:price type="package" info="http://www.dummy.jp/package_info.html" price="19.99" currency="EUR" /&gt;
     * &lt;media:price type="subscription" info="http://www.dummy.jp/subscription_info" price="19.99" currency="EUR" /&gt;
     * </pre>
     * 
     * <p>
     * type Valid values are "rent", "purchase", "package" or "subscription". If nothing is specified, then the media is free.
     * </p>
     * 
     * <p>
     * info if the type is "package" or "subscription", then info is a URL pointing to package or subscription information. This is an optional attribute.
     * </p>
     * 
     * <p>
     * price is the price of the media object. This is an optional attribute.
     * </p>
     * 
     * <p>
     * currency -- use <a href="http://en.wikipedia.org/wiki/ISO_4217">ISO 4217</a> for currency codes. This is an optional attribute.
     * </p>
     * 
     * @return the prices
     */
    public Price[] getPrices() {
        return prices;
    }

    /**
     * <p>
     * <strong>&lt;media:price&gt;</strong>
     * </p>
     * 
     * <p>
     * Optional tag to include pricing information about a media object. If this tag is not present, the media object is supposed to be free. One media object
     * can have multiple instances of this tag for including different pricing structures. The presence of this tag would mean that media object is not free.
     * </p>
     * 
     * <pre>
     * &lt;media:price type="rent" price="19.99" currency="EUR" /&gt;
     * &lt;media:price type="package" info="http://www.dummy.jp/package_info.html" price="19.99" currency="EUR" /&gt;
     * &lt;media:price type="subscription" info="http://www.dummy.jp/subscription_info" price="19.99" currency="EUR" /&gt;
     * </pre>
     * 
     * <p>
     * type Valid values are "rent", "purchase", "package" or "subscription". If nothing is specified, then the media is free.
     * </p>
     * 
     * <p>
     * info if the type is "package" or "subscription", then info is a URL pointing to package or subscription information. This is an optional attribute.
     * </p>
     * 
     * <p>
     * price is the price of the media object. This is an optional attribute.
     * </p>
     * 
     * <p>
     * currency -- use <a href="http://en.wikipedia.org/wiki/ISO_4217">ISO 4217</a> for currency codes. This is an optional attribute.
     * </p>
     * 
     * @param prices the prices
     */
    public void setPrices(final Price[] prices) {
        if (prices == null) {
            this.prices = new Price[0];
        } else {
            this.prices = prices;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:rating&gt;</strong>
     * </p>
     * 
     * 
     * <p>
     * This allows the permissible audience to be declared. If this element is not included, it assumes that no restrictions are necessary. It has one optional
     * attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:rating scheme="urn:simple"&gt;adult&lt;/media:rating&gt;
     *               &lt;media:rating scheme="urn:icra"&gt;r (cz 1 lz 1 nz 1 oz 1 vz 1)&lt;/media:rating&gt;
     *               &lt;media:rating scheme="urn:mpaa"&gt;pg&lt;/media:rating&gt;
     * 
     *               &lt;media:rating scheme="urn:v-chip"&gt;tv-y7-fv&lt;/media:rating&gt;
     * </pre>
     * 
     * 
     * <p>
     * <em>scheme</em> is the URI that identifies the rating scheme. It is an optional attribute. If this attribute is not included, the default scheme is
     * urn:simple (adult | nonadult).
     * </p>
     * 
     * <p>
     * 
     * @param ratings Ratings objects
     */
    public void setRatings(final Rating[] ratings) {
        if (ratings == null) {
            this.ratings = new Rating[0];
        } else {
            this.ratings = ratings;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:rating&gt;</strong>
     * </p>
     * 
     * 
     * <p>
     * This allows the permissible audience to be declared. If this element is not included, it assumes that no restrictions are necessary. It has one optional
     * attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:rating scheme="urn:simple"&gt;adult&lt;/media:rating&gt;
     *               &lt;media:rating scheme="urn:icra"&gt;r (cz 1 lz 1 nz 1 oz 1 vz 1)&lt;/media:rating&gt;
     *               &lt;media:rating scheme="urn:mpaa"&gt;pg&lt;/media:rating&gt;
     * 
     *               &lt;media:rating scheme="urn:v-chip"&gt;tv-y7-fv&lt;/media:rating&gt;
     * </pre>
     * 
     * 
     * <p>
     * <em>scheme</em> is the URI that identifies the rating scheme. It is an optional attribute. If this attribute is not included, the default scheme is
     * urn:simple (adult | nonadult).
     * </p>
     * 
     * <p>
     * 
     * @return Ratings objects
     */
    public Rating[] getRatings() {
        return ratings;
    }

    /**
     * <p>
     * <strong>&lt;media:responses&gt;</strong>
     * </p>
     * 
     * <p>
     * Allows inclusion of a list of all media responses a media object has received.
     * </p>
     * 
     * <pre>
     * &lt;media:responses&gt;
     *   &lt;media:response&gt;response1&lt;/media:response&gt;
     *   &lt;media:response&gt;response2&lt;/media:response&gt;
     *   &lt;media:response&gt;response3&lt;/media:response&gt;
     * &lt;/media:responses&gt;
     * </pre>
     * 
     * @return array of responses
     */
    public String[] getResponses() {
        return responses;
    }

    /**
     * <p>
     * <strong>&lt;media:responses&gt;</strong>
     * </p>
     * 
     * <p>
     * Allows inclusion of a list of all media responses a media object has received.
     * </p>
     * 
     * <pre>
     * &lt;media:responses&gt;
     *   &lt;media:response&gt;response1&lt;/media:response&gt;
     *   &lt;media:response&gt;response2&lt;/media:response&gt;
     *   &lt;media:response&gt;response3&lt;/media:response&gt;
     * &lt;/media:responses&gt;
     * </pre>
     * 
     * @param responses array of responses
     */
    public void setResponses(final String[] responses) {
        if (responses == null) {
            this.responses = new String[0];
        } else {
            this.responses = responses;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:restriction&gt; </strong>
     * </p>
     * 
     * <p>
     * Allows restrictions to be placed on the aggregator rendering the media in the feed. Currently, restrictions are based on distributor (uri) and country
     * codes. This element is purely informational and no obligation can be assumed or implied. Only one &lt;media:restriction&gt; element of the same
     * <em>type</em> can be applied to a media object - all others will be ignored.&nbsp;Entities in this element should be space separated. To allow the
     * producer to explicitly declare his/her intentions, two literals are reserved: 'all', 'none'. These literals can only be used once. This element has 1
     * required attribute, and 1 optional attribute (with strict requirements for its exclusion).
     * </p>
     * 
     * <pre>
     * &lt;media:restriction relationship="allow" type="country"&gt;au us&lt;/media:restriction&gt;
     * </pre>
     * 
     * <p>
     * <em>relationship</em> indicates the type of relationship that the restriction represents (allow | deny). In the example above, the media object should
     * only be syndicated in Australia and the United States. It is a required attribute.
     * </p>
     * 
     * @param restrictions restrictions for the item.
     */
    public void setRestrictions(final Restriction[] restrictions) {
        if (restrictions == null) {
            this.restrictions = new Restriction[0];
        } else {
            this.restrictions = restrictions;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:restriction&gt; </strong>
     * </p>
     * 
     * <p>
     * Allows restrictions to be placed on the aggregator rendering the media in the feed. Currently, restrictions are based on distributor (uri) and country
     * codes. This element is purely informational and no obligation can be assumed or implied. Only one &lt;media:restriction&gt; element of the same
     * <em>type</em> can be applied to a media object - all others will be ignored.&nbsp;Entities in this element should be space separated. To allow the
     * producer to explicitly declare his/her intentions, two literals are reserved: 'all', 'none'. These literals can only be used once. This element has 1
     * required attribute, and 1 optional attribute (with strict requirements for its exclusion).
     * </p>
     * 
     * <pre>
     * &lt;media:restriction relationship="allow" type="country"&gt;au us&lt;/media:restriction&gt;
     * </pre>
     * 
     * <p>
     * <em>relationship</em> indicates the type of relationship that the restriction represents (allow | deny). In the example above, the media object should
     * only be syndicated in Australia and the United States. It is a required attribute.
     * </p>
     * 
     * @return restrictions for the item.
     */
    public Restriction[] getRestrictions() {
        return restrictions;
    }

    /**
     * <p><strong>&lt;media:rights&gt;</strong></p>
     * 
     * <p>Optional element to specify the rights information of a media object.</p>
     * 
     * <pre>&lt;media:rights status="userCreated" /&gt;
     * &lt;media:rights status="official" /&gt;</pre>
     * 
     * @return the rights
     */
    public RightsStatus getRights() {
        return rights;
    }

    /**
     * <p><strong>&lt;media:rights&gt;</strong></p>
     * 
     * <p>Optional element to specify the rights information of a media object.</p>
     * 
     * <pre>&lt;media:rights status="userCreated" /&gt;
     * &lt;media:rights status="official" /&gt;</pre>
     * 
     * @param rights the rights
     */
    public void setRights(final RightsStatus rights) {
        this.rights = rights;
    }

    /**
     * <p><strong>&lt;media:scene&gt;</strong></p>
     * 
     * <p>Optional element to specify various scenes within a media object. It can have multiple child &lt;media:scene&gt;
     * elements, where each &lt;media:scene&gt; element contains information about a particular scene. &lt;media:scene&gt;
     * has the optional sub-elements &lt;sceneTitle&gt;, &lt;sceneDescription&gt;, &lt;sceneStartTime&gt; and &lt;sceneEndTime&gt;,
     * which contains title, description, start and end time of a particular scene in the media, respectively.</p>
     * 
     * <pre>&lt;media:scenes&gt;
     *   &lt;media:scene&gt;
     *     &lt;sceneTitle&gt;sceneTitle1&lt;/sceneTitle&gt;
     *     &lt;sceneDescription&gt;sceneDesc1&lt;/sceneDescription&gt;
     *     &lt;sceneStartTime&gt;00:15&lt;/sceneStartTime&gt;
     *     &lt;sceneEndTime&gt;00:45&lt;/sceneEndTime&gt;
     *   &lt;/media:scene&gt;
     *   &lt;media:scene&gt;
     *     &lt;sceneTitle&gt;sceneTitle2&lt;/sceneTitle&gt;
     *     &lt;sceneDescription&gt;sceneDesc2&lt;/sceneDescription&gt;
     *     &lt;sceneStartTime&gt;00:57&lt;/sceneStartTime&gt;
     *     &lt;sceneEndTime&gt;01:45&lt;/sceneEndTime&gt;
     *     &lt;/media:scene&gt;
     * &lt;/media:scenes&gt;</pre>
     * 
     * @return the scenes
     */
    public Scene[] getScenes() {
        return scenes;
    }

    /**
     * <p><strong>&lt;media:scene&gt;</strong></p>
     * 
     * <p>Optional element to specify various scenes within a media object. It can have multiple child &lt;media:scene&gt;
     * elements, where each &lt;media:scene&gt; element contains information about a particular scene. &lt;media:scene&gt;
     * has the optional sub-elements &lt;sceneTitle&gt;, &lt;sceneDescription&gt;, &lt;sceneStartTime&gt; and &lt;sceneEndTime&gt;,
     * which contains title, description, start and end time of a particular scene in the media, respectively.</p>
     * 
     * <pre>&lt;media:scenes&gt;
     *   &lt;media:scene&gt;
     *     &lt;sceneTitle&gt;sceneTitle1&lt;/sceneTitle&gt;
     *     &lt;sceneDescription&gt;sceneDesc1&lt;/sceneDescription&gt;
     *     &lt;sceneStartTime&gt;00:15&lt;/sceneStartTime&gt;
     *     &lt;sceneEndTime&gt;00:45&lt;/sceneEndTime&gt;
     *   &lt;/media:scene&gt;
     *   &lt;media:scene&gt;
     *     &lt;sceneTitle&gt;sceneTitle2&lt;/sceneTitle&gt;
     *     &lt;sceneDescription&gt;sceneDesc2&lt;/sceneDescription&gt;
     *     &lt;sceneStartTime&gt;00:57&lt;/sceneStartTime&gt;
     *     &lt;sceneEndTime&gt;01:45&lt;/sceneEndTime&gt;
     *     &lt;/media:scene&gt;
     * &lt;/media:scenes&gt;</pre>
     * 
     * @param scenes the scenes
     */
    public void setScenes(final Scene[] scenes) {
        if (scenes == null) {
            this.scenes = new Scene[0];
        } else {
            this.scenes = scenes;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:status&gt;</strong>
     * </p>
     * 
     * <p>
     * Optional tag to specify the status of a media object -- whether it's still active or it has been blocked/deleted.
     * </p>
     * 
     * <pre>
     * &lt;media:status state="blocked" reason="http://www.reasonforblocking.com" /&gt;
     * </pre>
     * 
     * <p>
     * state can have values "active", "blocked" or "deleted". "active" means a media object is active in the system, "blocked" means a media object is blocked
     * by the publisher, "deleted" means a media object has been deleted by the publisher.
     * 
     * <p>
     * reason is a reason explaining why a media object has been blocked/deleted. It can be plain text or a URL.
     * </p>
     * 
     * @return status of media
     */
    public Status getStatus() {
        return status;
    }

    /**
     * <p>
     * <strong>&lt;media:status&gt;</strong>
     * </p>
     * 
     * <p>
     * Optional tag to specify the status of a media object -- whether it's still active or it has been blocked/deleted.
     * </p>
     * 
     * <pre>
     * &lt;media:status state="blocked" reason="http://www.reasonforblocking.com" /&gt;
     * </pre>
     * 
     * <p>
     * state can have values "active", "blocked" or "deleted". "active" means a media object is active in the system, "blocked" means a media object is blocked
     * by the publisher, "deleted" means a media object has been deleted by the publisher.
     * 
     * <p>
     * reason is a reason explaining why a media object has been blocked/deleted. It can be plain text or a URL.
     * </p>
     * 
     * @param status status of media
     */
    public void setStatus(final Status status) {
        this.status = status;
    }

    /**
     * <p><strong>&lt;media:subTitle&gt;</strong></p>
     * 
     * <p>Optional element for subtitle/CC link. It contains type and language attributes. Language is based on RFC 3066.
     * There can be more than one such tag per media element, for example one per language. Please refer to
     * <a href="http://www.w3.org/AudioVideo/TT">Timed Text spec - W3C</a> for more information on Timed Text and Real Time Subtitling.</p>
     * 
     * <pre>&lt;media:subTitle type="application/smil" lang="en-us" href="http://www.example.org/subtitle.smil" /&gt;</pre>
     * 
     * @return the subtitles
     */
    public SubTitle[] getSubTitles() {
        return subTitles;
    }

    /**
     * <p><strong>&lt;media:subTitle&gt;</strong></p>
     * 
     * <p>Optional element for subtitle/CC link. It contains type and language attributes. Language is based on RFC 3066.
     * There can be more than one such tag per media element, for example one per language. Please refer to
     * <a href="http://www.w3.org/AudioVideo/TT">Timed Text spec - W3C</a> for more information on Timed Text and Real Time Subtitling.</p>
     * 
     * <pre>&lt;media:subTitle type="application/smil" lang="en-us" href="http://www.example.org/subtitle.smil" /&gt;</pre>
     * 
     * @param subTitles the subtitles
     */
    public void setSubTitles(final SubTitle[] subTitles) {
        if (subTitles == null) {
            this.subTitles = new SubTitle[0];
        } else {
            this.subTitles = subTitles;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:text&gt;</strong>
     * </p>
     * <p>
     * Allows the inclusion of a text transcript, closed captioning, or lyrics of the media content. Many of these elements are permitted to provide a time
     * series of text. In such cases, it is encouraged, but not required, that the elements be grouped by language and appear in time sequence order based on
     * the <em>start</em> time. Elements can have overlapping <em>start</em> and <em>end</em> times. It has 4 optional attributes.
     * </p>
     * 
     * <pre>
     * &lt;media:text type="plain" lang="en" start="00:00:03.000"
     *        end="00:00:10.000"&gt; Oh, say, can you see&lt;/media:text&gt;
     * 
     *        &lt;media:text type="plain" lang="en" start="00:00:10.000"
     *        end="00:00:17.000"&gt;By the dawn's early light&lt;/media:text&gt;
     * </pre>
     * <p>
     * <em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be
     * entity-encoded. It is an optional attribute.
     * </p>
     * 
     * 
     * 
     * 
     * 
     * 
     * <p>
     * <em>lang</em> is the primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066. This attribute is used similar
     * to the xml:lang attribute detailed in the XML 1.0 Specification (Third Edition). It is an optional attribute.
     * </p>
     * 
     * <p>
     * <em>start</em> specifies the start time offset that the text starts being relevant to the media object. An example of this would be for closed
     * captioning. It uses the NTP time code format (see: the time attribute used in &lt;media:thumbnail&gt;).&nbsp;It is an optional attribute.
     * </p>
     * 
     * <p>
     * <em>end</em> specifies the end time that the text is relevant. If this attribute is not provided, and a <em>start</em> time is used, it is expected that
     * the end time is either the end of the clip or the start of the next &lt;media:text&gt; element.
     * 
     * @param text text objects for the item.
     */
    public void setText(final Text[] text) {
        if (text == null) {
            this.text = new Text[0];
        } else {
            this.text = text;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:text&gt;</strong>
     * </p>
     * <p>
     * Allows the inclusion of a text transcript, closed captioning, or lyrics of the media content. Many of these elements are permitted to provide a time
     * series of text. In such cases, it is encouraged, but not required, that the elements be grouped by language and appear in time sequence order based on
     * the <em>start</em> time. Elements can have overlapping <em>start</em> and <em>end</em> times. It has 4 optional attributes.
     * </p>
     * 
     * <pre>
     * &lt;media:text type="plain" lang="en" start="00:00:03.000"
     *        end="00:00:10.000"&gt; Oh, say, can you see&lt;/media:text&gt;
     * 
     *        &lt;media:text type="plain" lang="en" start="00:00:10.000"
     *        end="00:00:17.000"&gt;By the dawn's early light&lt;/media:text&gt;
     * </pre>
     * <p>
     * <em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be
     * entity-encoded. It is an optional attribute.
     * </p>
     * 
     * 
     * 
     * 
     * 
     * 
     * <p>
     * <em>lang</em> is the primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066. This attribute is used similar
     * to the xml:lang attribute detailed in the XML 1.0 Specification (Third Edition). It is an optional attribute.
     * </p>
     * 
     * <p>
     * <em>start</em> specifies the start time offset that the text starts being relevant to the media object. An example of this would be for closed
     * captioning. It uses the NTP time code format (see: the time attribute used in &lt;media:thumbnail&gt;).&nbsp;It is an optional attribute.
     * </p>
     * 
     * <p>
     * <em>end</em> specifies the end time that the text is relevant. If this attribute is not provided, and a <em>start</em> time is used, it is expected that
     * the end time is either the end of the clip or the start of the next &lt;media:text&gt; element.
     * 
     * @return Text objects for the item.
     */
    public Text[] getText() {
        return text;
    }

    /**
     * <p>
     * <strong>&lt;media:thumbnail&gt;</strong>
     * </p>
     * 
     * 
     * <p>
     * Allows particular images to be used as representative images for the media object. If multiple thumbnails are included, and time coding is not at play,
     * it is assumed that the images are in order of importance. It has 1 required attribute and 3 optional attributes.
     * </p>
     * 
     * 
     * 
     * 
     * 
     * <pre>
     * &lt;media:thumbnail url="http://www.foo.com/keyframe.jpg" width="75" height="50" time="12:05:01.123" /&gt;
     * </pre>
     * <p>
     * <em>url</em> specifies the url of the thumbnail. It is a required attribute.
     * </p>
     * <p>
     * <em>height</em> specifies the height of the thumbnail. It is an optional attribute.
     * </p>
     * <p>
     * <em>width</em> specifies the width of the thumbnail. It is an optional attribute.
     * </p>
     * 
     * 
     * <p>
     * <em>time</em> specifies the time offset in relation to the media object. Typically this is used when creating multiple keyframes within a single video.
     * The format for this attribute should be in the DSM-CC's Normal Play Time (NTP) as used in RTSP [<a href="http://www.ietf.org/rfc/rfc2326.txt">RFC 2326
     * 3.6 Normal Play Time</a>]. It is an optional attribute.
     * </p>
     * 
     * @param thumbnail thumbnails for the image
     */
    public void setThumbnail(final Thumbnail[] thumbnail) {
        if (thumbnail == null) {
            this.thumbnail = new Thumbnail[0];
        } else {
            this.thumbnail = thumbnail;
        }
    }

    /**
     * <p>
     * <strong>&lt;media:thumbnail&gt;</strong>
     * </p>
     * 
     * 
     * <p>
     * Allows particular images to be used as representative images for the media object. If multiple thumbnails are included, and time coding is not at play,
     * it is assumed that the images are in order of importance. It has 1 required attribute and 3 optional attributes.
     * </p>
     * 
     * 
     * 
     * 
     * 
     * <pre>
     * &lt;media:thumbnail url="http://www.foo.com/keyframe.jpg" width="75" height="50" time="12:05:01.123" /&gt;
     * </pre>
     * <p>
     * <em>url</em> specifies the url of the thumbnail. It is a required attribute.
     * </p>
     * <p>
     * <em>height</em> specifies the height of the thumbnail. It is an optional attribute.
     * </p>
     * <p>
     * <em>width</em> specifies the width of the thumbnail. It is an optional attribute.
     * </p>
     * 
     * 
     * <p>
     * <em>time</em> specifies the time offset in relation to the media object. Typically this is used when creating multiple keyframes within a single video.
     * The format for this attribute should be in the DSM-CC's Normal Play Time (NTP) as used in RTSP [<a href="http://www.ietf.org/rfc/rfc2326.txt">RFC 2326
     * 3.6 Normal Play Time</a>]. It is an optional attribute.
     * </p>
     * 
     * @return Thumbnails for the image
     */
    public Thumbnail[] getThumbnail() {
        return thumbnail;
    }

    /**
     * <p>
     * <strong>&lt;media:title&gt;</strong>
     * </p>
     * <p>
     * The title of the particular media object. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:title type="plain"&gt;The Judy's - The Moo Song&lt;/media:title&gt;
     * </pre>
     * 
     * <p>
     * <em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be
     * entity-encoded. It is an optional attribute.
     * </p>
     * 
     * @param title Value of the title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * <p>
     * <strong>&lt;media:title&gt;</strong>
     * </p>
     * <p>
     * The title of the particular media object. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:title type="plain"&gt;The Judy's - The Moo Song&lt;/media:title&gt;
     * </pre>
     * 
     * <p>
     * <em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be
     * entity-encoded. It is an optional attribute.
     * </p>
     * 
     * @return value of the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>
     * <strong>&lt;media:title&gt;</strong>
     * </p>
     * <p>
     * The title of the particular media object. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:title type="plain"&gt;The Judy's - The Moo Song&lt;/media:title&gt;
     * </pre>
     * 
     * <p>
     * <em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be
     * entity-encoded. It is an optional attribute.
     * </p>
     * 
     * @param titleType type of the title.
     */
    public void setTitleType(final String titleType) {
        this.titleType = titleType;
    }

    /**
     * <p>
     * <strong>&lt;media:title&gt;</strong>
     * </p>
     * <p>
     * The title of the particular media object. It has 1 optional attribute.
     * </p>
     * 
     * <pre>
     * &lt;media:title type="plain"&gt;The Judy's - The Moo Song&lt;/media:title&gt;
     * </pre>
     * 
     * <p>
     * <em>type</em> specifies the type of text embedded. Possible values are either 'plain' or 'html'. Default value is 'plain'. All html must be
     * entity-encoded. It is an optional attribute.
     * </p>
     * 
     * @return type of the title
     */
    public String getTitleType() {
        return titleType;
    }

    @Override
    public Object clone() {
        final Metadata md = new Metadata();
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
        md.setBackLinks(getBackLinks());
        md.setCommunity(getCommunity());
        md.setComments(getComments());
        md.setResponses(getResponses());
        md.setStatus(getStatus());
        md.setPrices(getPrices());
        md.setEmbed(getEmbed());
        md.setLicenses(getLicenses());
        md.setSubTitles(getSubTitles());
        md.setPeerLinks(getPeerLinks());
        md.setLocations(getLocations());
        md.setRights(getRights());
        md.setScenes(getScenes());
        return md;
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(Metadata.class, this);

        return eBean.beanEquals(obj);
    }

    @Override
    public int hashCode() {
        final EqualsBean equals = new EqualsBean(Metadata.class, this);

        return equals.beanHashCode();
    }

    @Override
    public String toString() {
        final ToStringBean tsBean = new ToStringBean(Metadata.class, this);

        return tsBean.toString();
    }
}
