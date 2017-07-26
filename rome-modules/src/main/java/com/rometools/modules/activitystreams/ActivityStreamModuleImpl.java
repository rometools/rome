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
package com.rometools.modules.activitystreams;

import com.rometools.modules.activitystreams.types.ActivityObject;
import com.rometools.modules.activitystreams.types.Mood;
import com.rometools.modules.activitystreams.types.Verb;

public class ActivityStreamModuleImpl implements ActivityStreamModule {
    private ActivityObject object;
    private Verb verb;

    /**
     * Set the value of object
     *
     * @param newobject new value of object
     */
    @Override
    public void setObject(final ActivityObject newobject) {
        object = newobject;
    }

    /**
     * Get the value of object
     *
     * @return the value of object
     */
    @Override
    public ActivityObject getObject() {
        return object;
    }

    /**
     * Set the value of verb
     *
     * @param newverb new value of verb
     */
    @Override
    public void setVerb(final Verb newverb) {
        verb = newverb;
    }

    /**
     * Get the value of verb
     *
     * @return the value of verb
     */
    @Override
    public Verb getVerb() {
        return verb;
    }

    @Override
    public ActivityObject getTarget() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setTarget(final ActivityObject object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mood getMood() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMood(final Mood mood) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
