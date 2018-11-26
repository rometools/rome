/*
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
package com.rometools.modules.thr;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.rometools.rome.feed.module.ModuleImpl;

/**
 * Currently no support for thr:count, thr:updated, thr:total link attributes.
 */
public class ThreadingModuleImpl extends ModuleImpl implements ThreadingModule {

    private static final long serialVersionUID = 1L;

    private String ref;
    private String href;
    private String type;
    private String source;

    public ThreadingModuleImpl() {
        super(ThreadingModule.class, ThreadingModule.URI);
    }

    @Override
    public Class<? extends CopyFrom> getInterface() {
        return ThreadingModule.class;
    }

    @Override
    public void copyFrom(CopyFrom copyFrom) {
        if (copyFrom instanceof ThreadingModule) {
            ThreadingModule module = (ThreadingModule) copyFrom;
            setHref(module.getHref());
            setRef(module.getRef());
            setType(module.getType());
            setSource(module.getSource());
        }
    }

    @Override
    public String getRef() {
        return ref;
    }

    @Override
    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getHref() {
        return href;
    }

    @Override
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public Object clone() {
        final ThreadingModule m = new ThreadingModuleImpl();
        m.setHref(href);
        m.setRef(ref);
        m.setSource(source);
        m.setType(type);
        return m;
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBean.beanEquals(ThreadingModuleImpl.class, this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBean.toString(ThreadingModuleImpl.class, this);
    }

}
