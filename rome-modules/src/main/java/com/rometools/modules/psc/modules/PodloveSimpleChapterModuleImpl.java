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
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.rometools.rome.feed.module.ModuleImpl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * A Module implementation for item level Simple Chapter information.
 */
public class PodloveSimpleChapterModuleImpl
        extends ModuleImpl
        implements PodloveSimpleChapterModule, Cloneable, Serializable {

    private List<SimpleChapter> chapters;

    public PodloveSimpleChapterModuleImpl() {
        super(PodloveSimpleChapterModule.class, PodloveSimpleChapterModule.URI);
    }

    @Override
    public List<SimpleChapter> getChapters() {
        return (chapters==null) ? (chapters = new LinkedList<SimpleChapter>()) : chapters;
    }

    @Override
    public void setChapters(final List<SimpleChapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public Class<? extends CopyFrom> getInterface() {
        return PodloveSimpleChapterModule.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        final PodloveSimpleChapterModule mod = (PodloveSimpleChapterModule) obj;
        final List<SimpleChapter> chapters = new LinkedList<SimpleChapter>();
        for(SimpleChapter c1 : mod.getChapters()) {
            final SimpleChapter c2 = new SimpleChapter();
            c2.copyFrom(c1);
            chapters.add(c2);
        }
        setChapters(chapters);
    }

    @Override
    public String getUri() {
        return PodloveSimpleChapterModule.URI;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final PodloveSimpleChapterModuleImpl mod = new PodloveSimpleChapterModuleImpl();
        final List<SimpleChapter> result = new LinkedList<SimpleChapter>();
        for (SimpleChapter c1 : this.chapters){
            SimpleChapter c2 = new SimpleChapter();
            c2.copyFrom(c1);
            result.add(c2);
        }
        result.subList(0, result.size()); // not sure why I need to do this
        mod.setChapters(result);
        return mod;
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBean.beanEquals(PodloveSimpleChapterModuleImpl.class, this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBean.toString(PodloveSimpleChapterModuleImpl.class, this);
    }
}
