/*
 * Copyright 2018 Maximilian Irro
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
 *
 */
package com.rometools.modules.psc.io;

/**
 * Constant definitions for XML attribute names.
 */
public interface PodloveSimpleChapterAttribute {

    // namespace prefix

    /** "psc". */
    String PREFIX = "psc";


    // name of the list of chapters

    /** "chapters". */
    String CHAPTERS = "chapters";

    /** "version". */
    String VERSION = "version";


    // name of a chapter entry

    /** "chapter". */
    String CHAPTER = "chapter";


    // attributes of single chapter entries

    /** "start". */
    String START = "start";

    /** "title". */
    String TITLE = "title";

    /** "href". */
    String HREF = "href";

    /** "image". */
    String IMAGE = "image";
}
