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

package com.rometools.modules.wfw.io;

import com.rometools.modules.wfw.WfwModule;
import com.rometools.modules.wfw.WfwModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Locale;

public class WfwModuleParser implements ModuleParser {
    private static final Namespace NS = Namespace.getNamespace(WfwModule.URI);

    @Override
    public String getNamespaceUri() {
        return null;
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        final WfwModuleImpl mod = new WfwModuleImpl();
        if (element.getName().equals("item")) {
	    final String commentRss = element.getChildText("commentRss", NS);
	    mod.setCommentRss(commentRss);
	    return mod;
	}
	return null;
    }
}
