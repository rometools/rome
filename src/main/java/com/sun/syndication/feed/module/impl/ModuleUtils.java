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
package com.sun.syndication.feed.module.impl;

import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.module.Module;

/**
 */
public class ModuleUtils {

    public static List<Module> cloneModules(final List<Module> modules) {
        List<Module> cModules = null;
        if (modules != null) {
            cModules = new ArrayList<Module>();
            for (final Module module : modules) {
                try {
                    final Module c = (Module) module.clone();
                    cModules.add(c);
                } catch (final Exception ex) {
                    throw new RuntimeException("Cloning modules " + module.getUri(), ex);
                }
            }
        }
        return cModules;
    }

    /**
     * 
     * 
     * @since 1.5 Changed to return the first, not the last.
     * 
     * @param modules
     * @param uri
     * @return
     */
    public static Module getModule(final List<Module> modules, final String uri) {
        Module module = null;
        for (int i = 0; modules != null && i < modules.size(); i++) {
            module = modules.get(i);
            if (module.getUri().equals(uri)) {
                return module;
            }
        }
        return null;
    }

}
