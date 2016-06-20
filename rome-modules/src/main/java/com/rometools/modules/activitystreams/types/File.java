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
 * The "file" Object type represents some document or other file with no additional machine-readable
 * semantics.
 * </p>
 * <p>
 * It is intended that this type be used as a base type for other Objects that manifest as files, so
 * that additional semantics can be added while providing a fallback ability for clients that do not
 * support the more specific Object type.
 * </p>
 * <p>
 * The "file" Object type is identified by the URI <tt>http://activitystrea.ms/schema/1.0/file</tt>.
 * </p>
 * <p>
 * A file has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Associated File URL</dt>
 * <dd>The URL of the file described by this Object Construct. Represented in JSON by a property
 * called <tt>fileUrl</tt> whose value is a JSON string containing the URL.</dd>
 * <dt>File MIME Type</dt>
 * <dd>The MIME type of the file described by this Object Construct. Represented in JSON by a
 * property called <tt>mimeType</tt> whose value is a JSON string containing the MIME type.</dd>
 * </dl>
 * </blockquote>
 */
public class File extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/file";
    }

}
