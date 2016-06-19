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

import com.rometools.modules.base.types.DateTimeRange;

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for Class or Course
 * information entry types.
 */
public interface Course extends GlobalInterface {
    /**
     * The timeframe a course is running.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd"><font size="-1"> <b><a
     * name="course_date_range"></a>course_date_range</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1">Date and time range a class is in session, in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/datesandtime.html">ISO 8601</a>. Two
     * sub-attributes are included in course_date_range attribute.
     * <ul type="disc">
     *
     * <li>start = Start date and time of a trip in format YYYY-MM-DDThh:mm:ss</li>
     * <li>end = End date and time of a trip in format YYYY-MM-DDThh:mm:ss</li>
     * </ul>
     * </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:course_date_range&gt; <br>
     *
     * &lt;g:start&gt;2005-12-20T09:30:01&lt;/g:start&gt; <br>
     * &lt;g:end&gt;2005-12-29T10:30:59&lt;/g:end&gt;<br>
     * &lt;/g:course_date_range&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1"> Course schedules</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     *
     * <td><font size="-1"> dateTimeRange</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param courseDateRange The timeframe a course is running
     */
    public void setCourseDateRange(DateTimeRange courseDateRange);

    /**
     * The timeframe a course is running.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     *
     * <tbody>
     * <tr valign="top">
     *
     * <td colspan="2" bgcolor="#dddddd"><font size="-1"> <b><a
     * name="course_date_range"></a>course_date_range</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1">Date and time range a class is in session, in <a
     * href="http://www.iso.org/iso/en/prods-services/popstds/datesandtime.html">ISO 8601</a>. Two
     * sub-attributes are included in course_date_range attribute.
     * <ul type="disc">
     *
     * <li>start = Start date and time of a trip in format YYYY-MM-DDThh:mm:ss</li>
     * <li>end = End date and time of a trip in format YYYY-MM-DDThh:mm:ss</li>
     * </ul>
     * </font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:course_date_range&gt; <br>
     *
     * &lt;g:start&gt;2005-12-20T09:30:01&lt;/g:start&gt; <br>
     * &lt;g:end&gt;2005-12-29T10:30:59&lt;/g:end&gt;<br>
     * &lt;/g:course_date_range&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1"> Course schedules</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1">
     *
     * <b>Content type</b></font></td>
     *
     * <td><font size="-1"> dateTimeRange</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return The timeframe a course is running
     */
    public DateTimeRange getCourseDateRange();

    /**
     * ID code associated with a course.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="course_number"></a>course_number</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">ID code associated with a course</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1">&lt;g:course_number&gt;HIST-90A&lt;/g:course_number&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1">Course schedules</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1">string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param courseNumber ID code associated with a course
     */
    public void setCourseNumber(String courseNumber);

    /**
     * ID code associated with a course.
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="course_number"></a>course_number</b></font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     *
     * <td><font size="-1">ID code associated with a course</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     * <td><font size="-1">&lt;g:course_number&gt;HIST-90A&lt;/g:course_number&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1">Course schedules</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1">string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return ID code associated with a course
     */
    public String getCourseNumber();

    /**
     * Time a class is in session.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="course_times"></a>course_times</b></font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1">Time a class is in session.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1">&lt;g:course_times&gt;MWF 08:30 - 09:45&lt;/g:course_times&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1">Course schedules</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1">string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param courseTimes Time a class is in session
     */
    public void setCourseTimes(String courseTimes);

    /**
     * Time a class is in session.
     *
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"><b><a
     * name="course_times"></a>course_times</b></font></td>
     *
     * </tr>
     *
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Details</b></font></td>
     * <td><font size="-1">Time a class is in session.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Example</b></font></td>
     *
     * <td><font size="-1">&lt;g:course_times&gt;MWF 08:30 - 09:45&lt;/g:course_times&gt;</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Attribute of</b></font></td>
     * <td><font size="-1">Course schedules</font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"><b>Content type</b></font></td>
     *
     * <td><font size="-1">string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Time a class is in session
     */
    public String getCourseTimes();

    /**
     * Salary for this position.
     *
     *
     *
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
     * @param salary Salary for this position
     */
    public void setSalary(Float salary);

    /**
     * Salary for this position.
     *
     *
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
     * @return Salary for this position
     */
    public Float getSalary();

    /**
     * Topics of study for a course.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="subject"></a>subject</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1"> Topic of study for a course.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:subject&gt;Trigonometry&lt;/g:subject&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Course schedules</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @param subject Topics of study for a course
     */
    public void setSubjects(String[] subject);

    /**
     * Topics of study for a course.
     *
     *
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="subject"></a>subject</b></font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Details</b></font></td>
     * <td><font size="-1"> Topic of study for a course.</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     *
     * <td><font size="-1"> &lt;g:subject&gt;Trigonometry&lt;/g:subject&gt;</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Attribute of</b></font></td>
     * <td><font size="-1">
     *
     * Course schedules</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     * <td><font size="-1"> string</font></td>
     *
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Topics of study for a course
     */
    public String[] getSubjects();

    /**
     * Name of the school at which a class is offered.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="university"></a>university</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Name of the school a class is offered at. </font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:university&gt;Stanford&lt;/g:university&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1"> Course schedules</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1">
     *
     * string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param university Name of the school at which a class is offered.
     */
    public void setUniversity(String university);

    /**
     * Name of the school at which a class is offered.
     * <table border="1" cellpadding="5" cellspacing="0" width="640">
     * <tbody>
     * <tr valign="top">
     * <td colspan="2" bgcolor="#dddddd" valign="top"><font size="-1"> <b><a
     * name="university"></a>university</b></font></td>
     *
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Details</b></font></td>
     * <td><font size="-1"> Name of the school a class is offered at. </font></td>
     * </tr>
     *
     * <tr valign="top">
     *
     * <td width="120"><font size="-1"> <b>Example</b></font></td>
     * <td><font size="-1"> &lt;g:university&gt;Stanford&lt;/g:university&gt;</font></td>
     * </tr>
     * <tr valign="top">
     *
     * <td width="120"><font size="-1">
     *
     * <b>Attribute of</b></font></td>
     * <td><font size="-1"> Course schedules</font></td>
     * </tr>
     * <tr valign="top">
     * <td width="120"><font size="-1"> <b>Content type</b></font></td>
     *
     * <td><font size="-1">
     *
     * string</font></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return Name of the school at which a class is offered.
     */
    public String getUniversity();
}
