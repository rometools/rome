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
package com.rometools.modules.psc.modules;

import com.rometools.modules.psc.types.SimpleChapter;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.module.Module;

import java.util.List;

/**
 * An interface describing the item level data for Podlove Simple Chapters.
 */
public interface PodloveSimpleChapterModule extends Module, CopyFrom {

    /** "http://podlove.org/simple-chapters". */
    String URI = "http://podlove.org/simple-chapters";

    /** "1.2". */
    String VERSION = "1.2";

    /**
     * Returns the SimpleChapters of an item element.
     *
     * @return List of SimpleChapters of an item element
     */
    List<SimpleChapter> getChapters();

    /**
     * Sets the SimpleChapters of an item element.
     *
     * @param chapters List of SimpleChapters of an item element
     */
    void setChapters(List<SimpleChapter> chapters);

}
