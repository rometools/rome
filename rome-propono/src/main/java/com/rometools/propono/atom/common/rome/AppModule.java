/*
 * Copyright 2007 Apache Software Foundation
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

import com.rometools.rome.feed.module.Module;

/**
 * ROME Extension Module to Atom protocol extensions to Atom format.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public interface AppModule extends Module {

    public static final String URI = "http://www.w3.org/2007/app";

    /** True if entry is a draft */
    public Boolean getDraft();

    /** Set to true if entry is a draft */
    public void setDraft(Boolean draft);

    /** Time of last edit */
    public Date getEdited();

    /** Set time of last edit */
    public void setEdited(Date edited);
}
