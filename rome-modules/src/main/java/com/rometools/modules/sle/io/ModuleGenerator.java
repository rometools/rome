/*
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

package com.rometools.modules.sle.io;

import java.util.HashSet;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.sle.SimpleListExtension;
import com.rometools.modules.sle.types.Group;
import com.rometools.modules.sle.types.Sort;
import com.rometools.rome.feed.module.Module;

public class ModuleGenerator implements com.rometools.rome.io.ModuleGenerator {

    private static final Set<Namespace> NAMESPACES = new HashSet<Namespace>();

    static {
        NAMESPACES.add(ModuleParser.NS);
    }

    public ModuleGenerator() {
        super();
    }

    /**
     * Returns the namespace URI this generator handles.
     * <p>
     *
     * @return the namespace URI.
     */
    @Override
    public String getNamespaceUri() {
        return SimpleListExtension.URI;
    }

    /**
     * Returns a set with all the URIs (JDOM Namespace elements) this module generator uses.
     * <p/>
     * It is used by the the feed generators to add their namespace definition in the root element
     * of the generated document (forward-missing of Java 5.0 Generics).
     * <p/>
     *
     * @return a set with all the URIs (JDOM Namespace elements) this module generator uses.
     */
    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    /**
     * Generates and injectts module metadata in a XML node (JDOM element).
     * <p>
     *
     * @param module the module to inject into the XML node (JDOM element).
     * @param element the XML node to inject the module metadata to.
     */
    @Override
    public void generate(final Module module, final Element element) {
        if (!(module instanceof SimpleListExtension)) {
            return;
        }

        final SimpleListExtension sle = (SimpleListExtension) module;
        addNotNullElement(element, "treatAs", sle.getTreatAs());

        final Group[] groups = sle.getGroupFields();
        final Element listInfo = new Element("listinfo", ModuleParser.NS);

        for (int i = 0; groups != null && i < groups.length; i++) {
            final Element group = new Element("group", ModuleParser.NS);

            if (groups[i].getNamespace() != Namespace.NO_NAMESPACE) {
                addNotNullAttribute(group, "ns", groups[i].getNamespace().getURI());
            }

            addNotNullAttribute(group, "element", groups[i].getElement());
            addNotNullAttribute(group, "label", groups[i].getLabel());
            listInfo.addContent(group);
        }

        final Sort[] sorts = sle.getSortFields();

        for (int i = 0; sorts != null && i < sorts.length; i++) {
            final Element sort = new Element("sort", ModuleParser.NS);

            if (sorts[i].getNamespace() != Namespace.NO_NAMESPACE) {
                addNotNullAttribute(sort, "ns", sorts[i].getNamespace().getURI());
            }

            addNotNullAttribute(sort, "element", sorts[i].getElement());
            addNotNullAttribute(sort, "label", sorts[i].getLabel());
            addNotNullAttribute(sort, "data-type", sorts[i].getDataType());

            if (sorts[i].getDefaultOrder()) {
                addNotNullAttribute(sort, "default", "true");
            }

            listInfo.addContent(sort);
        }

        if (!listInfo.getChildren().isEmpty()) {
            element.addContent(listInfo);
        }
    }

    protected void addNotNullAttribute(final Element target, final String name, final Object value) {
        if (target == null || value == null) {
            return;
        } else {
            target.setAttribute(name, value.toString());
        }
    }

    protected Element addNotNullElement(final Element target, final String name, final Object value) {
        if (value == null) {
            return null;
        } else {
            final Element e = generateSimpleElement(name, value.toString());
            target.addContent(e);

            return e;
        }
    }

    protected Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, ModuleParser.NS);
        element.addContent(value);

        return element;
    }
}
