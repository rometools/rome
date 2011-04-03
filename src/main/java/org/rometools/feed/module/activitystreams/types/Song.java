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
package org.rometools.feed.module.activitystreams.types;


/**
 * <p>The "song" Object type represents a song or a recording of a
 *          song.
 *</p>
 *<p>Objects of type Song might contain information about the song or
 *          recording, or they might contain some representation of the
 *          recording itself. In the latter case, the song SHOULD also be
 *          annotated with the "audio" object type as described in <a class='info' href='#audio'>Section&nbsp;3.2.2<span> (</span><span class='info'>Audio</span><span>)</span></a> and use its properties. This type should only
 *          be used when the publisher can guarantee that the object is a song
 *          rather than merely a generic audio stream.
 *</p>
 *<p>The Object type URL for the "song" Object type is <tt>http://activitystrea.ms/schema/1.0/song</tt>.
 *</p>
 *<p>A song has no additional components.
 *</p>
 * @author robert.cooper
 */
public class Song extends ActivityObject {
    private Audio audio;

    /**
     * Set the value of audio
     *
     * @param newaudio new value of audio
     */
    public void setAudio(Audio newaudio) {
        this.audio = newaudio;
    }

    /**
     * Get the value of audio
     *
     * @return the value of audio
     */
    public Audio getAudio() {
        return this.audio;
    }

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/song";
    }
}
