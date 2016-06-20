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

/**
 * <p>
 * Location is a concept which can be used inside the atom:entry level, atom:author and
 * activity:object. Location can be specified via geographic coordinates, a street address, a
 * free-form location name or a combination of these. Geographic coordinates should be included as a
 * geo:point element as described by the GeoRSS specification. Addresses should be included using a
 * poco:address element defined at Portable Contacts specification. The entry level location
 * represents the location of the author at the time the activity was performed. The author level
 * location represents the primary location of the author. Finally, the location within other
 * objects represents the physical location of those objects. All location fields are optional. For
 * free form addresses use the poco:formatted field.
 * </p>
 * <p>
 * <a class='info' href='#location-poco'>Figure&nbsp;1<span> (</span><span class='info'>Location
 * Example</span><span>)</span></a>
 * </p>
 * <p>
 * <br />
 * <hr class="insert" />
 * <a name="location-poco"></a>
 * </p>
 * <div style='display: table; width: 0; margin-left: 3em; margin-right: auto'>
 *
 * <pre>
 * &lt;entry&gt;
 *   &lt;id&gt;tag:world:activity:1212121212121&lt;/id&gt;
 *   &lt;title&gt;Rob liked Goldeberg's Deli&lt;/title&gt;
 *   &lt;author&gt;
 *     &lt;name&gt;Rob Dolin&lt;/name&gt;
 *     &lt;id&gt;tag:world:person:1212121212121&lt;/id&gt;
 *     &lt;link rel="alternate" type="text/html" href="http://robdolin.com" /&gt;
 *     &lt;poco:address&gt;
 *       &lt;poco:locality&gt;Marina del Rey&lt;/poco:locality&gt;
 *       &lt;poco:region&gt;CA&lt;/poco:region&gt;
 *       &lt;poco:postalCode&gt;90292&lt;/poco:postalCode&gt;
 *       &lt;poco:country&gt;US&lt;/poco:country&gt;
 *     &lt;/poco:address&gt;
 *   &lt;/author&gt;
 *   &lt;activity:object&gt;
 *     &lt;activity:object-type&gt;http://activitystrea.ms/schema/1.0/place&lt;/object-type&gt;
 *     &lt;id&gt;tag:world:place:1212121212121&lt;/id&gt;
 *     &lt;title&gt;Goldberg's Deli&lt;/title&gt;
 *     &lt;link rel="alternate" type="text/html" href="http://www.deli.com" /&gt;
 *     &lt;geo:point&gt;33.9777 -118.4351&lt;/geo:point&gt;
 *     &lt;poco:address&gt;
 *       &lt;poco:streetAddress&gt;14016 Bora Bora Way&lt;/poco:streetAddress&gt;
 *       &lt;poco:locality&gt;Marina del Rey&lt;/poco:locality&gt;
 *       &lt;poco:region&gt;CA&lt;/poco:region&gt;
 *       &lt;poco:postalCode&gt;90292&lt;/poco:postalCode&gt;
 *       &lt;poco:country&gt;US&lt;/poco:country&gt;
 *     &lt;/poco:address&gt;
 *   &lt;/activity:object&gt;
 *   &lt;geo:point&gt;33.9777 -119.4351&lt;/geo:point&gt;
 *   &lt;activity:verb&gt;http://activitystrea.ms/schema/1.0/like&lt;/activity:verb&gt;
 * &lt;/entry&gt;
 * </pre>
 *
 * </div>
 * <p>
 * <table border="0" cellpadding="0" cellspacing="2" align="center">
 * <tr>
 * <td align="center"><font face="monaco, MS Sans Serif" size="1"><b>&nbsp;Figure&nbsp;1: Location
 * Example&nbsp;</b></font><br />
 * </td>
 * </tr>
 * </table>
 * <hr class="insert" />
 *
 * </p>
 */
public interface HasLocation {

    public GeoRSSModule getLocation();

    public void setLocation(GeoRSSModule location);

    public ContactModule getAddress();

    public void setAddress(ContactModule address);
}
