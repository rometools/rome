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
     * @param courseDateRange The timeframe a course is running
     */
    public void setCourseDateRange(DateTimeRange courseDateRange);

    /**
     * The timeframe a course is running.
     *
     * @return The timeframe a course is running
     */
    public DateTimeRange getCourseDateRange();

    /**
     * ID code associated with a course.
     *
     * @param courseNumber ID code associated with a course
     */
    public void setCourseNumber(String courseNumber);

    /**
     * ID code associated with a course.
     *
     * @return ID code associated with a course
     */
    public String getCourseNumber();

    /**
     * Time a class is in session.
     *
     * @param courseTimes Time a class is in session
     */
    public void setCourseTimes(String courseTimes);

    /**
     * Time a class is in session.
     *
     * @return Time a class is in session
     */
    public String getCourseTimes();

    /**
     * Salary for this position.
     *
     * @param salary Salary for this position
     */
    public void setSalary(Float salary);

    /**
     * Salary for this position.
     *
     * @return Salary for this position
     */
    public Float getSalary();

    /**
     * Topics of study for a course.
     *
     * @param subject Topics of study for a course
     */
    public void setSubjects(String[] subject);

    /**
     * Topics of study for a course.
     *
     * @return Topics of study for a course
     */
    public String[] getSubjects();

    /**
     * Name of the school at which a class is offered.
     *
     * @param university Name of the school at which a class is offered.
     */
    public void setUniversity(String university);

    /**
     * Name of the school at which a class is offered.
     *
     * @return Name of the school at which a class is offered.
     */
    public String getUniversity();
}
