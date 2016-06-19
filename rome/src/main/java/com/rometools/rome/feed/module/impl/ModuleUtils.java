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
package com.rometools.rome.feed.module.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.rome.feed.module.Module;

public class ModuleUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ModuleUtils.class);

    private ModuleUtils() {
    }

    public static List<Module> cloneModules(final List<Module> modules) {
        List<Module> cModules = null;
        if (modules != null) {
            cModules = new ArrayList<Module>();
            for (final Module module : modules) {
                try {
                    final Module c = (Module) module.clone();
                    cModules.add(c);
                } catch (final Exception e) {
                    final String moduleUri = module.getUri();
                    LOG.error("Error while cloning module " + moduleUri, e);
                    throw new RuntimeException("Cloning modules " + moduleUri, e);
                }
            }
        }
        return cModules;
    }

    /**
     *
     *
     * @since 1.5 Changed to return the first, not the last.
     */
    public static Module getModule(final List<Module> modules, final String uri) {
        Module searchedModule = null;
        if (modules != null) {
            for (final Module module : modules) {
                if (module.getUri().equals(uri)) {
                    searchedModule = module;
                    break;
                }
            }
        }
        return searchedModule;
    }

}
