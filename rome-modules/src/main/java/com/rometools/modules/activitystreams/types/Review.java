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

/**
 * <p>
 * The "review" object type represents a primarily prose-based commentary on another object.
 * </p>
 * <p>
 * The "review" object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/review</tt>.
 * </p>
 * <p>
 * A review has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Title</dt>
 * <dd>The title of the review. Represented by the Name component of the base Object Construct. Many
 * systems do not have the concept of a title for a review; such systems MUST omit the Name
 * component. Processors SHOULD refer to such reviews as simply being "a review", with appropriate
 * localization, if they are to be described in a sentence.</dd>
 * <dt>Content</dt>
 * <dd>The content of the comment. Represented in JSON as a property called <tt>content</tt> whose
 * value is a JSON string containing a fragment of HTML that represents the content. Publishers
 * SHOULD include any markup necessary to achieve a similar presentation to that on the publisher's
 * own HTML pages, including any links that the service automatically adds. Processors MAY remove
 * all HTML markup and consider the comment to be plain text.</dd>
 * <dt>Reviewed Object</dt>
 * <dd>The Object Construct representing the item that this review applies to. Represented as the In
 * Reply To Object component of the base Object Construct.</dd>
 * <dt>Rating</dt>
 * <dd>A rating for the item, given as a number between 1.0 and 5.0 inclusive with one decimal place
 * of precision. Represented in JSON as a property called <tt>rating</tt> whose value is a JSON
 * number giving the rating.</dd>
 * </dl>
 * </blockquote>
 */
public class Review extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/review";
    }

}
