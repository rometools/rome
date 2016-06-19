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

package com.rometools.modules.cc;

import java.lang.reflect.Array;

import com.rometools.modules.cc.types.License;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

public class CreativeCommonsImpl implements CreativeCommons {

    private static final long serialVersionUID = 1L;

    public static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    public static final String RSS2_URI = "http://backend.userland.com/creativeCommonsRssModule";
    public static final String RSS1_URI = "http://web.resource.org/cc/";

    private License[] allLicenses;
    private License[] licenses;

    protected Object arrayCopy(final Object[] source) {
        if (source == null) {
            return null;
        }

        final Object[] array = (Object[]) Array.newInstance(source.getClass().getComponentType(), source.length);

        for (int i = 0; i < source.length; i++) {
            array[i] = source[i];
        }

        return array;
    }

    @Override
    public License[] getAllLicenses() {
        return allLicenses;
    }

    @Override
    public void setAllLicenses(final License[] allLicenses) {
        this.allLicenses = allLicenses;
    }

    @Override
    public Class<CreativeCommons> getInterface() {
        return CreativeCommons.class;
    }

    @Override
    public String getUri() {
        return CreativeCommons.URI;
    }

    @Override
    public Object clone() {
        final CreativeCommonsImpl clone = new CreativeCommonsImpl();
        clone.copyFrom(this);
        return clone;
    }

    @Override
    public void copyFrom(final CopyFrom object) {
        final CreativeCommons source = (CreativeCommons) object;
        setAllLicenses((License[]) arrayCopy(source.getAllLicenses()));
        setLicenses(source.getLicenses());
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(this.getClass(), this);

        return eBean.beanEquals(obj);
    }

    @Override
    public License[] getLicenses() {
        return licenses;
    }

    @Override
    public void setLicenses(final License[] licenses) {
        this.licenses = licenses;
    }

    @Override
    public String toString() {
        final ToStringBean tsb = new ToStringBean(CreativeCommonsImpl.class, this);
        return tsb.toString();
    }

}
