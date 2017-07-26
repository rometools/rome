/*
 * Copyright 2007 Apache Software Foundation
 * Copyright 2011 The ROME Teams
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package com.rometools.propono.atom.common.rome;

import java.util.Date;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.module.ModuleImpl;

/**
 * Bean representation of APP module.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class AppModuleImpl extends ModuleImpl implements AppModule {

    private static final long serialVersionUID = 1L;

    private boolean draft = false;
    private Date edited = null;

    public AppModuleImpl() {
        super(AppModule.class, AppModule.URI);
    }

    /** True if entry is draft */
    @Override
    public Boolean getDraft() {
        return draft ? Boolean.TRUE : Boolean.FALSE;
    }

    /** Set to true if entry is draft */
    @Override
    public void setDraft(final Boolean draft) {
        this.draft = draft.booleanValue();
    }

    /** Time of last edit */
    @Override
    public Date getEdited() {
        return edited;
    }

    /** Set time of last edit */
    @Override
    public void setEdited(final Date edited) {
        this.edited = edited;
    }

    /** Get interface class of module */
    @Override
    public Class<AppModule> getInterface() {
        return AppModule.class;
    }

    /** Copy from other module */
    @Override
    public void copyFrom(final CopyFrom obj) {
        final AppModule m = (AppModule) obj;
        setDraft(m.getDraft());
        setEdited(m.getEdited());
    }

}
