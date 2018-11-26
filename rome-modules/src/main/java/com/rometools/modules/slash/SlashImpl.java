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

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;

public class SlashImpl implements Slash {

    private static final long serialVersionUID = 1L;

    private String section;
    private String department;
    private Integer comments;
    private Integer[] hitParade;

    @Override
    public String getSection() {
        return section;
    }

    @Override
    public void setSection(final String section) {
        this.section = section;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public void setDepartment(final String department) {
        this.department = department;
    }

    @Override
    public Integer getComments() {
        return comments;
    }

    @Override
    public void setComments(final Integer comments) {
        this.comments = comments;
    }

    @Override
    public Integer[] getHitParade() {
        return hitParade == null ? new Integer[0] : hitParade;
    }

    @Override
    public void setHitParade(final Integer[] hitParade) {
        this.hitParade = hitParade;
    }

    @Override
    public void copyFrom(final CopyFrom object) {
        final Slash source = (Slash) object;
        setHitParade(arrayCopy(source.getHitParade()));
        setComments(source.getComments());
        setDepartment(source.getDepartment());
        setSection(source.getSection());
    }

    @Override
    public Object clone() {
        final SlashImpl si = new SlashImpl();
        si.copyFrom(this);
        return si;
    }

    @Override
    public String getUri() {
        return Slash.URI;
    }

    private Integer[] arrayCopy(final Integer[] source) {
        if (source == null) {
            return null;
        }

        final Integer[] array = new Integer[source.length];
        for (int i = 0; i < source.length; i++) {
            array[i] = source[i];
        }

        return array;
    }

    @Override
    public Class<Slash> getInterface() {
        return Slash.class;
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBean.beanEquals(this.getClass(), this, obj);
    }

}
