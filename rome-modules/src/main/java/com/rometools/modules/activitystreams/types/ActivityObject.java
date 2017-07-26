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

import com.rometools.modules.georss.GeoRSSModule;
import com.rometools.modules.portablecontacts.ContactModule;
import com.rometools.rome.feed.atom.Entry;

public abstract class ActivityObject extends Entry implements HasLocation {

    private static final long serialVersionUID = 1L;

    public abstract String getTypeIRI();

    @Override
    public GeoRSSModule getLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setLocation(final GeoRSSModule location) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ContactModule getAddress() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setAddress(final ContactModule address) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
