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
 * The "note" Object type represents short-form text messages. This Object type is intended for use
 * in "micro-blogging" and in systems where users are invited to publish short, often plain-text
 * messages whose useful lifespan is generally shorter than that of an article or weblog entry.
 * </p>
 * <p>
 * A note is similar in structure to an article, but it does not have a title and its body tends to
 * be shorter. Applications will often display the entire content of a note in an activity stream
 * UI, whereas they MAY display only the title or the title and summary for a weblog entry.
 * </p>
 * <p>
 * The "note" Object type is identified by the URI <tt>http://activitystrea.ms/schema/1.0/note</tt>.
 * </p>
 * <p>
 * A note has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Content</dt>
 * <dd>The content of the comment. Represented in JSON as a property called <tt>content</tt> whose
 * value is a JSON string containing a fragment of HTML that represents the content. Publishers
 * SHOULD include any markup necessary to achieve a similar presentation to that on the publisher's
 * own HTML pages, including any links that the service automatically adds. Processors MAY remove
 * all HTML markup and consider the comment to be plain text.</dd>
 * </dl>
 * </blockquote>
 */
public class Note extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/note";
    }

}
