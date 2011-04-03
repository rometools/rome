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
package org.rometools.feed.module.activitystreams;

import org.rometools.feed.module.activitystreams.types.ActivityObject;
import org.rometools.feed.module.activitystreams.types.Mood;
import org.rometools.feed.module.activitystreams.types.Verb;


/**
 *
 * @author robert.cooper
 */
public class ActivityStreamModuleImpl implements ActivityStreamModule {
    private ActivityObject object;
    private Verb verb;

    /**
     * Set the value of object
     *
     * @param newobject new value of object
     */
    public void setObject(ActivityObject newobject) {
        this.object = newobject;
    }

    /**
     * Get the value of object
     *
     * @return the value of object
     */
    public ActivityObject getObject() {
        return this.object;
    }

    /**
     * Set the value of verb
     *
     * @param newverb new value of verb
     */
    public void setVerb(Verb newverb) {
        this.verb = newverb;
    }

    /**
     * Get the value of verb
     *
     * @return the value of verb
     */
    public Verb getVerb() {
        return this.verb;
    }

    public ActivityObject getTarget() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTarget(ActivityObject object) {
        throw new UnsupportedOperationException("Not supported yet.");
    } 

    public Mood getMood() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMood(Mood mood) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
