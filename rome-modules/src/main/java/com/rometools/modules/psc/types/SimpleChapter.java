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
package com.rometools.modules.psc.types;

import com.rometools.rome.feed.CopyFrom;

/**
 * Simple Chapter entry information.
 */
public class SimpleChapter implements CopyFrom {

    private String start;
    private String title;
    private String href;
    private String image;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public Class<? extends CopyFrom> getInterface() {
        return SimpleChapter.class;
    }

    @Override
    public void copyFrom(CopyFrom obj) {
        final SimpleChapter item = (SimpleChapter) obj;
        setStart(item.getStart());
        setTitle(item.getTitle());
        setHref(item.getHref());
        setImage(item.getImage());
    }

    @Override
    public String toString() {
        return "SimpleChapter{" +
            "start='" + start + '\'' +
            ", title='" + title + '\'' +
            ", href='" + href + '\'' +
            ", image='" + image + '\'' +
            '}';
    }
}
