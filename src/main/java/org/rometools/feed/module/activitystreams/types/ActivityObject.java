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

import com.sun.syndication.feed.atom.Entry;
import org.rometools.feed.module.georss.GeoRSSModule;
import org.rometools.feed.module.portablecontacts.ContactModule;
/**
 *
 * @author robert.cooper
 */
public abstract class ActivityObject extends Entry implements HasLocation {
    
    
    public abstract String getTypeIRI();

    public GeoRSSModule getLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLocation(GeoRSSModule location) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ContactModule getAddress(){
         throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAddress(ContactModule address){
         throw new UnsupportedOperationException("Not supported yet.");
    }

}
