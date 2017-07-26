/*
 *  Copyright 2011 robert.cooper.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package com.rometools.modules.activitystreams.types;

import com.rometools.rome.feed.atom.Link;

/**
 * <p>
 * The "bookmark" Object type represents a pointer to some URL -- typically a web page. In most
 * cases, a bookmark is specific to a given user and contains metadata chosen by that user. Bookmark
 * Objects are similar in principle to the concept of bookmarks or favorites in a web browser. A
 * bookmark represents a pointer to the URL, not the URL or the associated resource itself.
 * </p>
 * <p>
 * When dealing with bookmarks it is important to note the distinction between the title,
 * description, and URL of the bookmark itself and the title, content, and URL of the resource that
 * is the target of the bookmark. In some implementations these MAY be the same, but bookmark
 * managers often allow a user to edit the title and description of his or her own bookmarks to
 * differ from the metadata on the target itself.
 * </p>
 * <p>
 * Some implementations refer to this Object type as a "link". This specification uses the term
 * "bookmark" to avoid confusion with the general concept of hyperlinks which apply to all Object
 * types.
 * </p>
 * <p>
 * Since bookmarks are often specific to a particular user, even though multiple users might have
 * bookmarks pointing at the same resource, it is appropriate to use the "post" Verb to describe the
 * publication of such a bookmark. The "mark as favorite" Verb SHOULD be used when a user flags
 * another user's bookmark as being a favorite without creating his own bookmark, or when a user
 * flags his own bookmark as being a favorite as a special classification within his own bookmark
 * collection.
 * </p>
 * <p>
 * The "bookmark" Object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/bookmark</tt>.
 * </p>
 * <p>
 * A bookmark has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Title</dt>
 * <dd>The title of the bookmark, as entered by the user who created the bookmark. Represented by
 * the Name component of the base Object Construct. Publishers MAY use the title of the target
 * resource as a default for this property where a user hasn't entered a customized value.</dd>
 * <dt>Target URL</dt>
 * <dd>The URL of the item that is the target of the bookmark. Represented in JSON by a property
 * called <tt>targetUrl</tt> whose value is a JSON string containing the target URL.</dd>
 * <dt>Thumbnail</dt>
 * <dd>The URL and metadata for a thumbnail version of the page. Represented by the Representative
 * Image component of the base Object Construct. Processors MAY ignore thumbnails that are of an
 * inappropriate size for their user interface.</dd>
 * </dl>
 * </blockquote>
 */
public class Bookmark extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/bookmark";
    }

    /**
     * Get the value of thumbnail
     *
     * @return the value of thumbnail
     */
    public Link getThumbnail() {
        return findRelatedLink("thumbnail");
    }

    /**
     * Set the value of thumbnail
     *
     * @param newthumbnail new value of thumbnail
     */
    public void setThumbnail(final Link newthumbnail) {
        Link old = null;
        for (final Link l : getOtherLinks()) {
            if ("thumbnail".equals(l.getRel())) {
                old = l;
                break;
            }
        }
        if (old != null) {
            getOtherLinks().remove(old);
            newthumbnail.setRel("thumbnail");
        }
        getOtherLinks().add(newthumbnail);
    }

}
