/*
 * Copyright 2018 Tom G., tomgapplicationsdevelopment@gmail.com
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

package com.rometools.modules.wfw;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

public class WfwModuleImpl implements WfwModule {
    private String commentRss;

    @Override
    public String getCommentRss() {
        return commentRss;
    }

    @Override
    public void setCommentRss(final String commentRss) {
        this.commentRss = commentRss;
    }

    @Override
    public Class<WfwModuleImpl> getInterface() {
        return WfwModuleImpl.class;
    }

    @Override
    public String getUri() {
        return WfwModule.URI;
    }

    @Override
    public Object clone() {
        final WfwModuleImpl clone = new WfwModuleImpl();
        clone.copyFrom(this);
        return clone;
    }

    @Override
    public void copyFrom(final CopyFrom object) {
        final WfwModule source = (WfwModule) object;
        setCommentRss(source.getCommentRss());
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(this.getClass(), this);
        return eBean.beanEquals(obj);
    }

    @Override
    public String toString() {
        final ToStringBean tsb = new ToStringBean(WfwModuleImpl.class, this);
        return tsb.toString();
    }
}
