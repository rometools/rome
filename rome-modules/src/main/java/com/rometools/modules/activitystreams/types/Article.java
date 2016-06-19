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
 * The "article" Object type indicates that the Object is an article, such as a news article, a
 * knowledge base entry, or other similar construct.
 * </p>
 * <p>
 * Articles generally consist of paragraphs of text, in some cases incorporating embedded media such
 * as photos and inline hyperlinks to other resources.
 * </p>
 * <p>
 * The "Article" Object type is identified by the URL
 * <tt>http://activitystrea.ms/schema/1.0/article</tt>.
 * </p>
 * <p>
 * An article has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Name</dt>
 * <dd>The title of the article. Represented by the Name component of the base Object Construct.</dd>
 * <dt>Content</dt>
 * <dd>The main body content of the article. Represented in JSON as a property called
 * <tt>content</tt> whose value is a JSON string containing a fragment of HTML that represents the
 * content.</dd>
 * </dl>
 * </blockquote>
 */
public class Article extends ActivityObject {
    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/article";
    }
}
