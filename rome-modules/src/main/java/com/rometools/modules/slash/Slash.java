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

package com.rometools.modules.slash;

import java.io.Serializable;

import com.rometools.rome.feed.module.Module;

/**
 * This interface represents the Slash RSS extension.
 */
public interface Slash extends Module, Serializable {

    public static final String URI = "http://purl.org/rss/1.0/modules/slash/";

    public String getSection();

    public void setSection(String section);

    public String getDepartment();

    public void setDepartment(String department);

    public Integer getComments();

    public void setComments(Integer comments);

    public Integer[] getHitParade();

    public void setHitParade(Integer[] hitParade);

}
