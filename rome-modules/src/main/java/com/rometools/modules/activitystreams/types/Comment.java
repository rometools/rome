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
 * The "comment" object type represents a textual response to another object.
 * </p>
 * <p>
 * The comment object type MUST NOT be used for other kinds of replies, such as video replies or
 * reviews.
 * </p>
 * <p>
 * The "comment" object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/comment</tt>.
 * </p>
 * <p>
 * A comment has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Subject</dt>
 * <dd>The subject of the comment. Represented by the Name component of the base Object Construct.
 * Many systems do not have the concept of a title or subject for a comment; such systems MUST omit
 * the Name component. Processors SHOULD refer to such comments as simply being "a comment", with
 * appropriate localization, if they are to be described in a sentence.</dd>
 * <dt>Content</dt>
 * <dd>The content of the comment. Represented in JSON as a property called <tt>content</tt> whose
 * value is a JSON string containing a fragment of HTML that represents the content. Publishers
 * SHOULD include any markup necessary to achieve a similar presentation to that on the publisher's
 * own HTML pages, including any links that the service automatically adds. Processors MAY remove
 * all HTML markup and consider the comment to be plain text.</dd>
 * </dl>
 * </blockquote>
 */
public class Comment extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/comment";
    }

}
