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
 * The "video" Object type represents video content, which usually consists of a motion picture
 * track and an audio track.
 * </p>
 * <p>
 * The "video" Object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/video</tt>.
 * </p>
 * <p>
 * A video has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Video Stream Link</dt>
 * <dd>A Media Link Construct linking to the video content itself. Represented in JSON as a property
 * called <tt>stream</tt> whose value is a JSON object with properties as defined in [TODO: xref the
 * JSON serialization of a Media Link Construct]</dd>
 * <dt>Embed Code</dt>
 * <dd>An HTML fragment that, when embedded in an HTML page, will provide an interactive player UI
 * for the video stream. Represented in JSON as a property called <tt>embedCode</tt> whose value is
 * a JSON string containing the fragment of HTML.</dd>
 * </dl>
 * </blockquote>
 */
public class Video {

}
