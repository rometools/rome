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
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The ModuleGenerator implementation for the Fyyd module.
 */
public class FyydGenerator implements ModuleGenerator {

    private static final Namespace NS = Namespace.getNamespace(FyydElement.PREFIX, FyydModule.URI);
    private static final Set<Namespace> NAMESPACES;

    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }


    @Override
    public String getNamespaceUri() {
        return FyydModule.URI;
    }

    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(Module module, Element element) {
        if (module instanceof FyydModule) {
            final FyydModule fyyd = (FyydModule) module;
            generateVerify(fyyd.getVerify(), element);
        }
    }

    private void generateVerify(String verify, Element parent) {
        final Element child = new Element(FyydElement.VERIFY, NS);
        child.setText(verify);
        parent.addContent(child);
    }

}
