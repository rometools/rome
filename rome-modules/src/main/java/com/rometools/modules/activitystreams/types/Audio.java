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
 * The "audio" Object type represents audio content.
 * </p>
 * <p>
 * The "audio" Object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/audio</tt>.
 * </p>
 * <p>
 * An audio has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Audio Stream Link</dt>
 * <dd>A Media Link Construct linking to the audio content itself. Represented in JSON as a property
 * called <tt>stream</tt> whose value is a JSON object with properties as defined in [TODO: xref the
 * JSON serialization of a Media Link Construct]</dd>
 * <dt>Embed Code</dt>
 * <dd>An HTML fragment that, when embedded in an HTML page, will provide an interactive player UI
 * for the audio stream. Represented in JSON as a property called <tt>embedCode</tt> whose value is
 * a JSON string containing the fragment of HTML.</dd>
 * </dl>
 */
public class Audio extends ActivityObject {

    private static final long serialVersionUID = 1L;
    private String embedCode;
    private String streamLink;

    /**
     * Set the value of embedCode
     *
     * @param newembedCode new value of embedCode
     */
    public void setEmbedCode(final String newembedCode) {
        embedCode = newembedCode;
    }

    /**
     * Get the value of embedCode
     *
     * @return the value of embedCode
     */
    public String getEmbedCode() {
        return embedCode;
    }

    /**
     * Set the value of streamLink
     *
     * @param newstreamLink new value of streamLink
     */
    public void setStreamLink(final String newstreamLink) {
        streamLink = newstreamLink;
    }

    /**
     * Get the value of streamLink
     *
     * @return the value of streamLink
     */
    public String getStreamLink() {
        return streamLink;
    }

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/audio";
    }
}
