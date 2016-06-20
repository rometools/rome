/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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

package com.rometools.modules.slash.io;

import java.util.HashSet;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.slash.Slash;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

/**
 * The ModuleGenerator implementation for the Slash plug in.
 */
public class SlashModuleGenerator implements ModuleGenerator {

    private static final Namespace NAMESPACE = Namespace.getNamespace("slash", Slash.URI);

    public SlashModuleGenerator() {
    }

    @Override
    public void generate(final Module module, final Element element) {
        if (!(module instanceof Slash)) {
            return;
        }
        final Slash slash = (Slash) module;
        if (slash.getComments() != null) {
            element.addContent(generateSimpleElement("comments", slash.getComments().toString()));
        }
        if (slash.getDepartment() != null) {
            element.addContent(generateSimpleElement("department", slash.getDepartment()));
        }
        if (slash.getSection() != null) {
            element.addContent(generateSimpleElement("section", slash.getSection()));
        }
        if (slash.getHitParade() != null && slash.getHitParade().length > 0) {
            final StringBuffer buff = new StringBuffer();
            final Integer[] p = slash.getHitParade();
            for (int i = 0; i < p.length; i++) {
                if (i != 0) {
                    buff.append(",");
                }
                buff.append(p[i]);
            }
            element.addContent(generateSimpleElement("hit_parade", buff.toString()));
        }

    }

    protected Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, SlashModuleGenerator.NAMESPACE);
        element.addContent(value);
        return element;
    }

    @Override
    public Set<Namespace> getNamespaces() {
        final HashSet<Namespace> set = new HashSet<Namespace>();
        set.add(SlashModuleGenerator.NAMESPACE);
        return set;
    }

    @Override
    public String getNamespaceUri() {
        return Slash.URI;
    }

}
