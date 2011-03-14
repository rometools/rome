/*
 * CustomTag.java
 *
 * Created on February 6, 2006, 12:17 AM
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
 */

package org.rometools.feed.module.base;


/**
 * This class represents a custom tag name and value.
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.2 $
 */
public interface CustomTag  {
    
    /**
     * Returns the tag name used.
     * @return Returns the tag name used.
     */
    public String getName();
    
    /**
     * The value of the custom tag.
     * <br>
     * <br>
     * <table border="1" cellpadding="6" cellspacing="0">
     *  <tbody><tr valign="top">
     *    <td><strong><font size="-1">Type (JavaType)</font></strong></td>
     * 
     *    <td><strong><font size="-1">Description</font></strong></td>
     *    <td><strong><font size="-1">Examples</font></strong></td>
     *  </tr>
     *  <tr valign="top">
     *    <td><font size="-1">string (String)</font></td>
     *    <td><font size="-1">Any string</font></td>
     *    <td><font size="-1">Blue</font></td>
     * 
     *  </tr>
     *  <tr valign="top">
     *    <td><font size="-1">int (Integer)</font></td>
     *    <td><font size="-1">Whole number values</font></td>
     *    <td><font size="-1">1000</font></td>
     *  </tr>
     *  <tr valign="top">
     * 
     *    <td><font size="-1">float (Float)</font></td>
     *    <td><font size="-1">Numbers with decimal digits</font></td>
     *    <td><font size="-1">3.5</font></td>
     *  </tr>
     *  <tr valign="top">
     *    <td><font size="-1">intUnit (IntUnit)</font></td>
     * 
     *    <td><font size="-1">Whole number value and a string</font></td>
     * 
     *    <td><font size="-1">10 km</font></td>
     *  </tr>
     *  <tr valign="top">
     *    <td><font size="-1">floatUnit (FloatUnit)</font></td>
     *    <td><font size="-1">Numbers with decimal digits and a string</font></td>
     * 
     *    <td><font size="-1">1.5 km</font></td>
     *  </tr>
     * 
     * <tr valign="top">
     *    <td><font size="-1">date (ShortDate)</font></td>
     *    <td><font size="-1">Date of an event, in format YYYY-MM-DD</font></td>
     *    <td><font size="-1">2005-12-12</font></td>
     *  </tr>
     * 
     *  <tr valign="top">
     *    <td><font size="-1">dateTime (java.util.Date)</font></td>
     * 
     *    <td><font size="-1">Date and time for an event, in format YYYY-MM-DDThh:mm:ss</font></td>
     *    <td><font size="-1">2005-12-12T12:00:00 </font></td>
     *  </tr>
     *  <tr valign="top">
     *    <td><font size="-1">dateTimeRange (DateTimeRange)</font></td>
     * 
     *    <td><font size="-1">Start and end dates for an event. An attribute of this type will contain two sub-attributes, &lt;start&gt; and &lt;end&gt; in format YYYY-MM-DD</font></td>
     * 
     *    <td><font size="-1">&lt;start&gt;1975-09-25&lt;/start&gt;<br>&lt;end&gt;1975-09-25&lt;/end&gt;
     * 
     * </font></td>
     *  </tr>
     *  <tr valign="top">
     *    <td><font size="-1">url (java.net.URL)</font></td>
     *    <td><font size="-1">HTTP URL </font></td>
     * 
     *    <td><font size="-1">http://www.google.com</font></td>
     *  </tr>
     * 
     *  <tr valign="top">
     *    <td><font size="-1">boolean (Boolean)</font></td>
     *    <td><font size="-1">Value may be either true or false.</font></td>
     *    <td><font size="-1">true</font></td>
     *  </tr>
     * 
     *  <tr valign="top">
     *    <td><font size="-1">location (CustomTagImpl.Location)</font></td>
     * 
     *    <td><font size="-1">Location</font></td>
     *    <td><font size="-1">921 W. Dana Street, Mtn View, CA-94103 </font></td>
     *  </tr>
     * 
     * </tbody></table>
     * @return The value of the tag. The objects class is determined by the "type" attribute on the tag.
     */
    public Object getValue();
    
}
