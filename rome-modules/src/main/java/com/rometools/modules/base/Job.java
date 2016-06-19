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

import com.rometools.modules.base.types.CurrencyEnumeration;
import com.rometools.modules.base.types.PriceTypeEnumeration;

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for Job listing entry
 * types.
 */
public interface Job extends GlobalInterface {
    /**
     * Level of education required for an employment position.
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
     * @param education Level of education required for an employment position.
     */
    public void setEducation(String education);

    /**
     * Level of education required for an employment position.
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
     * @return Level of education required for an employment position.
     */
    public String getEducation();

    /**
     * Company providing employment.
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
     * @param employer Company providing employment.
     */
    public void setEmployer(String employer);

    /**
     * Company providing employment.
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
     * @return Company providing employment.
     */
    public String getEmployer();

    /**
     * Legal residency requirements for an employment position.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="immigration_status"></a>immigration_status</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Legal residency requirements for an employment position.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1">&lt;g:immigration_status&gt;Permanent
     * resident&lt;/g:immigration_status&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param immigrationStatus Legal residency requirements for an employment position.
     */
    public void setImmigrationStatus(String immigrationStatus);

    /**
     * Legal residency requirements for an employment position.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="immigration_status"></a>immigration_status</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1"> Legal residency requirements for an employment position.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1">&lt;g:immigration_status&gt;Permanent
     * resident&lt;/g:immigration_status&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Legal residency requirements for an employment position.
     */
    public String getImmigrationStatus();

    /**
     * The functions of an employment position.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="job_function"></a>job_function</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The function of an employment position.</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:job_function&gt;Product Manager&lt;/g:job_function&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param function The functions of an employment position.
     */
    public void setJobFunctions(String[] function);

    /**
     * The functions of an employment position.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="job_function"></a>job_function</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The function of an employment position.</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:job_function&gt;Product Manager&lt;/g:job_function&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     *
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return The functions of an employment position.
     */
    public String[] getJobFunctions();

    /**
     * The industry of an employment position.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="job_industry"></a>job_industry</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The industry of an employment position.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:job_industry&gt;Government&lt;/g:job_industry&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param jobIndustries The industry of an employment position.
     */
    public void setJobIndustries(String[] jobIndustries);

    /**
     * The industry of an employment position.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1">
     *
     * <b><a name="job_industry"></a>job_industry</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The industry of an employment position.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:job_industry&gt;Government&lt;/g:job_industry&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return The industry of an employment position.
     */
    public String[] getJobIndustries();

    /**
     * Type of employment position. Example: Full-time, part-time, contractor, etc.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="job_type"></a>job_type</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Type of employment position. Example: Full-time, part-time, contractor,
     * etc.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:job_type&gt;contractor&lt;/g:job_type&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param jobTypes Type of employment position. Example: Full-time, part-time, contractor, etc.
     */
    public void setJobTypes(String[] jobTypes);

    /**
     * Type of employment position. Example: Full-time, part-time, contractor, etc.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="job_type"></a>job_type</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     *
     * <td><font size="-1"> Type of employment position. Example: Full-time, part-time, contractor,
     * etc.</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:job_type&gt;contractor&lt;/g:job_type&gt;</font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Type of employment position. Example: Full-time, part-time, contractor, etc.
     */
    public String[] getJobTypes();

    /**
     * Location of the position.
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
     * @param location Location of the position.
     */
    public void setLocation(String location);

    /**
     * Location of the position.
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
     * @return Location of the position.
     */
    public String getLocation();

    /**
     * Salary for this position. Non-numeric values such as "$" symbols are not acceptable.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="salary"></a>salary</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Salary for this position. Non-numeric values such as "$" symbols are not
     * acceptable. </font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:salary&gt;55000&lt;/g:salary&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1">
     *
     * float</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param salary Salary for this position. Non-numeric values such as "$" symbols are not
     *            acceptable.
     */
    public void setSalary(Float salary);

    /**
     * Salary for this position. Non-numeric values such as "$" symbols are not acceptable.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="salary"></a>salary</b></font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Salary for this position. Non-numeric values such as "$" symbols are not
     * acceptable. </font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:salary&gt;55000&lt;/g:salary&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1">
     *
     * float</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Salary for this position. Non-numeric values such as "$" symbols are not acceptable.
     */
    public Float getSalary();

    /**
     * The type of salary included.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="salary_type"></a>salary_type</b></font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The type of salary included. Accepted values are �starting� or
     * �negotiable;� The default is �starting at.�</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:salary_type&gt;negotiable&lt;/g:salary_type&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1">
     *
     * "starting" or "negotiable"</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param salaryType The type of salary included.
     */
    public void setSalaryType(PriceTypeEnumeration salaryType);

    /**
     * The type of salary included.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="salary_type"></a>salary_type</b></font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> The type of salary included. Accepted values are �starting� or
     * �negotiable;� The default is �starting at.�</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:salary_type&gt;negotiable&lt;/g:salary_type&gt;</font></td>
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     *
     * <td><font size="-1"> Jobs</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     * <td><font size="-1">
     *
     * "starting" or "negotiable"</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return The type of salary included.
     */
    public PriceTypeEnumeration getSalaryType();

    /**
     * Currency of the price amount for an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="currency"></a>currency</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">Currency of the price amount for an item. Values must be in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/currencycodeslist.html">ISO 4217</a>
     * currency code format.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"><em>Acceptable:</em><br>
     * &lt;g:currency&gt;USD&lt;/g:currency&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:currency&gt;US Dollars&lt;/g:currency&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1">Events, Housing, Products, Services, Travel, Vehicles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">currencyEnumeration</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param value Currency of the price amount for an item.
     */
    public void setCurrency(CurrencyEnumeration value);

    /**
     * Currency of the price amount for an item.
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="currency"></a>currency</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">Currency of the price amount for an item. Values must be in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/currencycodeslist.html">ISO 4217</a>
     * currency code format.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1"><em>Acceptable:</em><br>
     * &lt;g:currency&gt;USD&lt;/g:currency&gt;<br>
     *
     * <em>Not acceptable:</em><br>
     * &lt;g:currency&gt;US Dollars&lt;/g:currency&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1">Events, Housing, Products, Services, Travel, Vehicles</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     * <td><font size="-1">currencyEnumeration</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Currency of the price amount for an item.
     */
    public CurrencyEnumeration getCurrency();

}
