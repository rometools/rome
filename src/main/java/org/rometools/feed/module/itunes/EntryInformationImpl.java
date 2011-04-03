/*
 * EntryInformation.java
 *
 * Created on August 1, 2005, 7:37 PM
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.rometools.feed.module.itunes;

import com.sun.syndication.feed.CopyFrom;
import org.rometools.feed.module.itunes.types.Duration;


/**
 * This class contains information for iTunes podcast feeds that exist at the Item level.
 * @version $Revision: 1.2 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class EntryInformationImpl extends AbstractITunesObject implements EntryInformation {
    private Duration duration;

    /**
     * Creates a new instance of EntryInformationImpl
     */
    public EntryInformationImpl() {
    }

    /**
     * Returns the Duration object for this Item
     * @return Returns the Duration object for this Item
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Sets the Duration object for this Item
     * @param duration Sets the Duration object for this Item
     */
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    /**
     * Defined by the ROME module API
     * @param obj Object to copy from
     */
    public void copyFrom(CopyFrom obj) {
        EntryInformationImpl info = (EntryInformationImpl) obj;
        this.setAuthor(info.getAuthor());
        this.setBlock(info.getBlock());

        if (info.getDuration() != null) {
            this.setDuration(new Duration(info.getDuration().getMilliseconds()));
        }

        this.setExplicit(info.getExplicit());

        if (info.getKeywords() != null) {
            this.setKeywords((String[]) info.getKeywords().clone());
        }

        this.setSubtitle(info.getSubtitle());
        this.setSummary(info.getSummary());
    }

    /**
     * Required by the ROME API
     * @return A clone of this module object
     */
    public Object clone() {
        EntryInformationImpl info = new EntryInformationImpl();
        info.copyFrom(this);

        return info;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        sb.append(" Duration: ");
        sb.append(this.getDuration());
        sb.append("]");
        sb.append(super.toString());

        return sb.toString();
    }
}
