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
package com.rometools.modules.fyyd.io;

import com.rometools.modules.fyyd.modules.FyydModule;
import com.rometools.modules.fyyd.modules.FyydModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Locale;

/**
 * The ModuleParser implementation for the Fyyd module.
 */
public class FyydParser implements ModuleParser {

    private static final Namespace NS = Namespace.getNamespace(FyydModule.URI);

    @Override
    public String getNamespaceUri() {
        return FyydModule.URI;
    }

    @Override
    public Module parse(Element element, Locale locale) {
        if (element.getName().equals("channel") || element.getName().equals("feed")) {
            final Element verify = element.getChild(FyydElement.VERIFY, NS);
            if (verify != null && verify.getValue() != null) {
                final FyydModule fyyd = new FyydModuleImpl();
                fyyd.setVerify(verify.getValue().trim());
                return fyyd;
            }
        }

        return null;
    }

}
