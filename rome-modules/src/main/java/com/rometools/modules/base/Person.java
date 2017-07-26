/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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

import com.rometools.modules.base.types.GenderEnumeration;

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for Person (personal
 * ad, resume, profile) entry types.
 */
public interface Person extends GlobalInterface {
    /**
     * Age of the individual.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr bgcolor="#dddddd" valign="top">
     * <td colspan="2" nowrap="nowrap"><font size="-1"><strong><a name="age"></a>age</strong></font>
     * </td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><strong><font size="-1">Details</font></strong></td>
     * <td><font size="-1">Minimum age requirement for the event or the age of the individual in a
     * People profiles bulk upload entry.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td><strong><font size="-1">Example</font></strong></td>
     * <td><font size="-1"><em>Acceptable:</em><br>
     * &lt; g:age&gt;18&lt;/g:age&gt;<br>
     * <em>Not acceptable:</em><br>
     *
     * &lt; g:age&gt;18 and over&lt;/g:age&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td><strong><font size="-1">Attribute of</font></strong></td>
     * <td><font size="-1">Events, People profiles</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td><strong><font size="-1">Content type</font></strong></td>
     * <td><font size="-1">integer</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param age Age of the individual.
     */
    public void setAge(Integer age);

    /**
     * Age of the individual.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr bgcolor="#dddddd" valign="top">
     * <td colspan="2" nowrap="nowrap"><font size="-1"><strong><a name="age"></a>age</strong></font>
     * </td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><strong><font size="-1">Details</font></strong></td>
     * <td><font size="-1">Minimum age requirement for the event or the age of the individual in a
     * People profiles bulk upload entry.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td><strong><font size="-1">Example</font></strong></td>
     * <td><font size="-1"><em>Acceptable:</em><br>
     * &lt; g:age&gt;18&lt;/g:age&gt;<br>
     * <em>Not acceptable:</em><br>
     *
     * &lt; g:age&gt;18 and over&lt;/g:age&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td><strong><font size="-1">Attribute of</font></strong></td>
     * <td><font size="-1">Events, People profiles</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td><strong><font size="-1">Content type</font></strong></td>
     * <td><font size="-1">integer</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Age of the individual.
     */
    public Integer getAge();

    /**
     * Education of the individual.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="education"></a>education</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Level of education required for an employment position.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1">&lt;g:education&gt;PhD&lt;/g:education&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param education Education of the individual.
     */
    public void setEducation(String education);

    /**
     * Education of the individual.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="education"></a>education</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Level of education required for an employment position.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1">&lt;g:education&gt;PhD&lt;/g:education&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Education of the individual.
     */
    public String getEducation();

    /**
     * Individuals employer.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="employer"></a>employer</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1"> Company providing employment.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1">&lt;g:employer&gt;Google, Inc&lt;/g:employer&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param employer Individuals employer.
     */
    public void setEmployer(String employer);

    /**
     * Individuals employer.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="employer"></a>employer</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1"> Company providing employment.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1">&lt;g:employer&gt;Google, Inc&lt;/g:employer&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Individuals employer.
     */
    public String getEmployer();

    /**
     * Ethnicity of the individual in the People profiles bulk upload entry.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="ethnicity"></a>ethnicity</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Ethnicity of the individual in the People profiles bulk upload
     * entry.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1">&lt;g:ethnicity&gt;Latino&lt;/g:ethnicity&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> People profiles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @param ethnicities Ethnicity of the individual in the People profiles bulk upload entry.
     */
    public void setEthnicities(String[] ethnicities);

    /**
     * Ethnicity of the individual in the People profiles bulk upload entry.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="ethnicity"></a>ethnicity</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Ethnicity of the individual in the People profiles bulk upload
     * entry.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1">&lt;g:ethnicity&gt;Latino&lt;/g:ethnicity&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> People profiles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @return Ethnicity of the individual in the People profiles bulk upload entry.
     */
    public String[] getEthnicities();

    /**
     * Gender of an individual in a People profiles bulk upload.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="gender"></a>gender</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Gender of an individual in a People profiles bulk upload item.
     * Acceptable values are �Male�, �M�, �Female�, or �F�.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;gender&gt;Female&lt;/gender&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> People profiles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> genderEnumeration</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param gender Gender of an individual in a People profiles bulk upload.
     */
    public void setGender(GenderEnumeration gender);

    /**
     * Gender of an individual in a People profiles bulk upload.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="gender"></a>gender</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Gender of an individual in a People profiles bulk upload item.
     * Acceptable values are �Male�, �M�, �Female�, or �F�.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;gender&gt;Female&lt;/gender&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> People profiles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> genderEnumeration</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Gender of an individual in a People profiles bulk upload.
     */
    public GenderEnumeration getGender();

    /**
     * Interest of a person being profiled.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="interested_in"></a>interested_in</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1"> Interest of a person being profiled.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:interested_in&gt;Long walks on the
     * beach.&lt;/g:interested_in&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1">People profiles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @param interestedIn Interest of a person being profiled.
     */
    public void setInterestedIn(String[] interestedIn);

    /**
     * Interest of a person being profiled.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="interested_in"></a>interested_in</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1"> Interest of a person being profiled.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:interested_in&gt;Long walks on the
     * beach.&lt;/g:interested_in&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     *
     * <td><font size="-1">People profiles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     * @return Interest of a person being profiled.
     */
    public String[] getInterestedIn();

    /**
     * Location of a person.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="location"></a>location</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Location of a property. Should include street, city, state, postal code,
     * and country, in that order. </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:location&gt;<st1:place>123 Main St, <st1:city>Anytown</st1:city>,
     * <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:location&gt;<st1:place><st1:city>123</st1:city> Main St,, <st1:state>CA</st1:state>,
     * <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     * &lt;g:location&gt; <st1:place><st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>,
     * <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, News and Articles, People
     * profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> locationType</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param location Location of a person.
     */
    public void setLocation(String location);

    /**
     * Location of a person.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="location"></a>location</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Location of a property. Should include street, city, state, postal code,
     * and country, in that order. </font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> <em>Acceptable:</em><br>
     * &lt;g:location&gt;<st1:place>123 Main St, <st1:city>Anytown</st1:city>,
     * <st1:state>CA</st1:state>, <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:location&gt;<st1:place><st1:city>123</st1:city> Main St,, <st1:state>CA</st1:state>,
     * <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;<br>
     * &lt;g:location&gt; <st1:place><st1:city>Anytown</st1:city>, <st1:state>CA</st1:state>,
     * <st1:postalcode>12345</st1:postalcode>,
     * <st1:country-region>USA</st1:country-region></st1:place>&lt;/g:location&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Reference Items, Events, Housing, Jobs, News and Articles, People
     * profiles, Products, Reviews, Services, Travel, Vehicles, Wanted Ads.</font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> locationType</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Location of a person.
     */
    public String getLocation();

    /**
     * Marital status of an individual.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd"><font size="-1"> <b><a
     * name="marital_status"></a>marital_status</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1"> Marital status of an individual in a People profiles bulk upload entry.
     * For example -single, divorced, separated, widowed, married, and �in relationship.�</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:marital_status&gt;single&lt;/g:marital_status&gt;<br>
     *
     * </font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> People profiles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1">
     *
     * string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param maritalStatus Marital status of an individual.
     */
    public void setMaritalStatus(String maritalStatus);

    /**
     * Marital status of an individual.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd"><font size="-1"> <b><a
     * name="marital_status"></a>marital_status</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1"> Marital status of an individual in a People profiles bulk upload entry.
     * For example -single, divorced, separated, widowed, married, and �in relationship.�</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:marital_status&gt;single&lt;/g:marital_status&gt;<br>
     *
     * </font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> People profiles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1">
     *
     * string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Marital status of an individual.
     */
    public String getMaritalStatus();

    /**
     * Occupation of an individual.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="occupation"></a>occupation</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1"> Industry the individual in a People profiles bulk upload is employed
     * in.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:occupation&gt;Sales&lt;/g:occupation&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * People profiles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param occupation Occupation of an individual.
     */
    public void setOccupation(String occupation);

    /**
     * Occupation of an individual.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="occupation"></a>occupation</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1"> Industry the individual in a People profiles bulk upload is employed
     * in.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:occupation&gt;Sales&lt;/g:occupation&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * People profiles</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Occupation of an individual.
     */
    public String getOccupation();

    /**
     * Individual's sexual orientation.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="sexual_orientation"></a>sexual_orientation</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     *
     * <td><font size="-1"> Sexual orientation of an individual in a People profiles information
     * type..</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:sexual_orientation&gt;straight&lt;/g:sexual_orientation&gt;</font>
     * </td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Personal</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param sexualOrientation Individual's sexual orientation.
     */
    public void setSexualOrientation(String sexualOrientation);

    /**
     * Individual's sexual orientation.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="sexual_orientation"></a>sexual_orientation</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     *
     * <td><font size="-1"> Sexual orientation of an individual in a People profiles information
     * type..</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:sexual_orientation&gt;straight&lt;/g:sexual_orientation&gt;</font>
     * </td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Personal</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Individual's sexual orientation.
     */
    public String getSexualOrientation();
}
