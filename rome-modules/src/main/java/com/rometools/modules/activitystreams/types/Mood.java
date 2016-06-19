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
 *
 * <p>
 * Mood describes the mood of the user when the activity was performed. This is usually collected
 * via an extra field in the user interface used to perform the activity. For the purpose of this
 * schema, a mood is a freeform, short mood keyword or phrase along with an optional mood icon
 * image.
 * </p>
 * <p>
 * Moods are included via a mood element in the namespace <tt>http://activitystrea.ms/context/</tt>.
 * </p>
 * <p>
 * The content of the mood element is the mood keyword or phrase.
 * </p>
 * <p>
 * The mood element may have an optional <tt>icon</tt> attribute which contains the URL of a mood
 * icon image. The mood icon image SHOULD be small and appropriate to be displayed alongside the
 * mood keyword at a normal text size.
 * </p>
 */
public class Mood {
    private String iconUrl;
    private String text;

    /**
     * Set the value of iconUrl
     *
     * @param newiconUrl new value of iconUrl
     */
    public void setIconUrl(final String newiconUrl) {
        iconUrl = newiconUrl;
    }

    /**
     * Get the value of iconUrl
     *
     * @return the value of iconUrl
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * Set the value of text
     *
     * @param newtext new value of text
     */
    public void setText(final String newtext) {
        text = newtext;
    }

    /**
     * Get the value of text
     *
     * @return the value of text
     */
    public String getText() {
        return text;
    }
}
