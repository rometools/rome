/*
 * Copyright 2004 Sun Microsystems, Inc.
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
package com.rometools.rome.feed.synd.impl;

import java.util.List;

import com.rometools.rome.feed.synd.Converter;
import com.rometools.rome.io.impl.PluginManager;

/**
 * Created by IntelliJ IDEA. User: tucu Date: May 21, 2004 Time: 5:26:04 PM To change this template
 * use Options | File Templates.
 */
public class Converters extends PluginManager<Converter> {

    /**
     * Converter.classes= [className] ...
     *
     */
    public static final String CONVERTERS_KEY = "Converter.classes";

    public Converters() {
        super(CONVERTERS_KEY);
    }

    public Converter getConverter(final String feedType) {
        return getPlugin(feedType);
    }

    @Override
    protected String getKey(final Converter obj) {
        return obj.getType();
    }

    public List<String> getSupportedFeedTypes() {
        return getKeys();
    }

}
