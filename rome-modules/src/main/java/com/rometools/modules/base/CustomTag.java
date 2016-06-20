/*
 * Copyright 2006 Robert Cooper, Temple of the Screaming Penguin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rometools.modules.base;

/**
 * This class represents a custom tag name and value.
 */
public interface CustomTag {

    /**
     * Returns the tag name used.
     *
     * @return Returns the tag name used.
     */
    public String getName();

    /**
     * The value of the custom tag. <br>
     * <br>
     * <table border="1" cellpadding="6" cellspacing="0">
     * <tbody>
     * <tr valign="top">
     * <td><strong><font size="-1">Type (JavaType)</font></strong></td>
     *
     * <td><strong><font size="-1">Description</font></strong></td>
     * <td><strong><font size="-1">Examples</font></strong></td>
     * </tr>
     * <tr valign="top">
     * <td><font size="-1">string (String)</font></td>
     * <td><font size="-1">Any string</font></td>
     * <td><font size="-1">Blue</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td><font size="-1">int (Integer)</font></td>
     * <td><font size="-1">Whole number values</font></td>
     * <td><font size="-1">1000</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td><font size="-1">float (Float)</font></td>
     * <td><font size="-1">Numbers with decimal digits</font></td>
     * <td><font size="-1">3.5</font></td>
     * </tr>
     * <tr valign="top">
     * <td><font size="-1">intUnit (IntUnit)</font></td>
     *
     * <td><font size="-1">Whole number value and a string</font></td>
     *
     * <td><font size="-1">10 km</font></td>
     * </tr>
     * <tr valign="top">
     * <td><font size="-1">floatUnit (FloatUnit)</font></td>
     * <td><font size="-1">Numbers with decimal digits and a string</font></td>
     *
     * <td><font size="-1">1.5 km</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td><font size="-1">date (ShortDate)</font></td>
     * <td><font size="-1">Date of an event, in format YYYY-MM-DD</font></td>
     * <td><font size="-1">2005-12-12</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td><font size="-1">dateTime (java.util.Date)</font></td>
     *
     * <td><font size="-1">Date and time for an event, in format YYYY-MM-DDThh:mm:ss</font></td>
     * <td><font size="-1">2005-12-12T12:00:00 </font></td>
     * </tr>
     * <tr valign="top">
     * <td><font size="-1">dateTimeRange (DateTimeRange)</font></td>
     *
     * <td><font size="-1">Start and end dates for an event. An attribute of this type will contain
     * two sub-attributes, &lt;start&gt; and &lt;end&gt; in format YYYY-MM-DD</font></td>
     *
     * <td><font size="-1">&lt;start&gt;1975-09-25&lt;/start&gt;<br>
     * &lt;end&gt;1975-09-25&lt;/end&gt;
     *
     * </font></td>
     * </tr>
     * <tr valign="top">
     * <td><font size="-1">url (java.net.URL)</font></td>
     * <td><font size="-1">HTTP URL </font></td>
     *
     * <td><font size="-1">http://www.google.com</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td><font size="-1">boolean (Boolean)</font></td>
     * <td><font size="-1">Value may be either true or false.</font></td>
     * <td><font size="-1">true</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td><font size="-1">location (CustomTagImpl.Location)</font></td>
     *
     * <td><font size="-1">Location</font></td>
     * <td><font size="-1">921 W. Dana Street, Mtn View, CA-94103 </font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @return The value of the tag. The objects class is determined by the "type" attribute on the
     *         tag.
     */
    public Object getValue();

}
