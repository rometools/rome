/*
 * Copyright 2019 Maximilian Irro
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
package com.rometools.modules.fyyd.modules;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.rometools.rome.feed.module.ModuleImpl;

import java.io.Serializable;

public class FyydModuleImpl
        extends ModuleImpl
        implements FyydModule, Cloneable, Serializable {

    private String verify;

    public FyydModuleImpl() {
        super(FyydModule.class, FyydModule.URI);
    }

    @Override
    public String getVerify() {
        return this.verify;
    }

    @Override
    public void setVerify(String verify) {
        this.verify = verify;
    }

    @Override
    public Class<? extends CopyFrom> getInterface() {
        return FyydModule.class;
    }

    @Override
    public void copyFrom(CopyFrom obj) {
        final FyydModule mod = (FyydModule) obj;
        this.setVerify(mod.getVerify());
    }

    @Override
    public String getUri() {
        return FyydModule.URI;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final FyydModuleImpl mod = new FyydModuleImpl();
        mod.setVerify(this.getVerify());
        return mod;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBean.beanEquals(FyydModuleImpl.class, this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBean.toString(FyydModuleImpl.class, this);
    }

}