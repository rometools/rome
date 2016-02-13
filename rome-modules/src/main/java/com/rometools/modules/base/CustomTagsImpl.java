/*
 * CustomTagsImpl.java
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2006  Robert Cooper, Temple of the Screaming Penguin
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

package com.rometools.modules.base;

import java.util.ArrayList;
import java.util.List;

import com.rometools.rome.feed.CopyFrom;

/**
 * @version $Revision: 1.1 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class CustomTagsImpl implements CustomTags {

    private static final long serialVersionUID = 1L;

    private List<CustomTag> values;

    @Override
    public List<CustomTag> getValues() {
        values = values == null ? new ArrayList<CustomTag>() : values;
        return values;
    }

    @Override
    public void setValues(final List<CustomTag> values) {
        this.values = values;
    }

    @Override
    public void copyFrom(final CopyFrom object) {
        final CustomTags ct = (CustomTags) object;
        values = new ArrayList<CustomTag>(ct.getValues());
    }

    @Override
    public Object clone() {
        final CustomTagsImpl cti = new CustomTagsImpl();
        cti.values = new ArrayList<CustomTag>(values);
        return cti;
    }

    @Override
    public Class<CustomTags> getInterface() {
        return CustomTags.class;
    }

    @Override
    public String getUri() {
        return CustomTags.URI;
    }

}
