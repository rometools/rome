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
 *<p>The "list" object type represents a collection of related
 *          objects.
 *</p>
 *<p>The "list" object type is identified by the URI <tt>http://activitystrea.ms/schema/1.0/list</tt>.
 *</p>
 *<p>A list has no additional components.
 *</p>
 
 * @author robert.cooper
 */
public class List extends ActivityObject
{

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/list";
    }

}
