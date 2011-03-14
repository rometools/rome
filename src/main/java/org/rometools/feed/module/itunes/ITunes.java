/*
 * ITunes.java
 *
 * Created on November 19, 2005, 10:58 PM
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
 */

package org.rometools.feed.module.itunes;

import com.sun.syndication.feed.module.Module;
import org.rometools.feed.module.itunes.types.Category;

/**
 * This interface contains the methods common to all iTunes module points.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.3 $
 */
public interface ITunes extends Module {
    
    public static final String URI = AbstractITunesObject.URI;
    
    /**
     * Returns the author string for this feed or entry
     * @return Returns the author string for this feed or entry
     */
    public String getAuthor();

    /**
     * Sets the author string for this feed or entry
     * @param author Sets the author string for this feed or entry
     */
    public void setAuthor(String author) ;
    
    /**
     * Boolean as to whether to block this feed or entry
     * @return Boolean as to whether to block this feed or entry
     */
    public boolean getBlock();

    /**
     * Boolean as to whether to block this feed or entry
     * @param block Boolean as to whether to block this feed or entry
     */
    public void setBlock(boolean block) ;

    /**
     * Boolean as to whether this feed or entry contains adult content
     * @return Boolean as to whether this feed or entry contains adult content
     */
    public boolean getExplicit() ;

    /**
     * Boolean as to whether this feed or entry contains adult content
     * @param explicit Boolean as to whether this feed or entry contains adult content
     */
    public void setExplicit(boolean explicit) ;

    /**
     * A list of keywords for this feed or entry
     *
     * Must not contain spaces
     * @return A list of keywords for this feed or entry
     */
    public String[] getKeywords() ;

    /**
     * A list of keywords for this feed or entry
     *
     * Must not contain spaces
     * @param keywords A list of keywords for this feed or enty
     */
    public void setKeywords(String[] keywords);
    /**
     * A subtitle for this feed or entry
     * @return A subtitle for this feed or entry
     */
    public String getSubtitle();

    /**
     * A subtitle for this feed or entry
     * @param subtitle A subtitle for this feed or entry
     */
    public void setSubtitle(String subtitle);
    

    /**
     * A subtitle for this feed or entry
     * @return A subtitle for this feed or entry
     */
    public String getSummary() ;

    /**
     * A subtitle for this feed or entry
     * @param summary A subtitle for this feed or entry
     */
    public void setSummary(String summary);
    
}
