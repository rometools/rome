/*
 * SlashImpl.java
 *
 * Created on November 19, 2005, 8:53 PM
 *
 * 
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.rometools.feed.module.slash;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.impl.EqualsBean;

/**
 * @version $Revision: 1.2 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class SlashImpl implements Slash {

    private String section;

    private String department;

    private Integer comments;

    private Integer[] hitParade;

    /** Creates a new instance of SlashImpl */
    public SlashImpl() {
    }

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
    public Class getInterface() {
        return Slash.class;
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(this.getClass(), this);

        return eBean.beanEquals(obj);
    }
}
