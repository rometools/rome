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

public interface ActivityStreamModule {

    public Verb getVerb();

    public void setVerb(Verb verb);

    public ActivityObject getObject();

    public void setObject(ActivityObject object);

    public ActivityObject getTarget();

    public void setTarget(ActivityObject object);

    public Mood getMood();

    public void setMood(Mood mood);

}
