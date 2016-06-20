/*
 * ModuleParser.java
 *
 * Created on April 27, 2006, 10:37 PM
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
package com.rometools.modules.sle.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.sle.SimpleListExtension;
import com.rometools.modules.sle.SimpleListExtensionImpl;
import com.rometools.modules.sle.types.Group;
import com.rometools.modules.sle.types.Sort;
import com.rometools.rome.feed.module.Module;

public class ModuleParser implements com.rometools.rome.io.ModuleParser {

    private static final Logger LOG = LoggerFactory.getLogger(ModuleParser.class);

    static final Namespace NS = Namespace.getNamespace("cf", SimpleListExtension.URI);
    public static final Namespace TEMP = Namespace.getNamespace("rome-sle", "urn:rome:sle");

    public ModuleParser() {
        super();
    }

    /**
     * Returns the namespace URI this parser handles.
     * <p>
     *
     * @return the namespace URI.
     */
    @Override
    public String getNamespaceUri() {
        return SimpleListExtension.URI;
    }

    /**
     * Parses the XML node (JDOM element) extracting module information.
     * <p>
     *
     * @param element the XML node (JDOM element) to extract module information from.
     * @return a module instance, <b>null</b> if the element did not have module information.
     */
    @Override
    public Module parse(final Element element, final Locale locale) {
        if (element.getChild("treatAs", NS) == null) {
            return null;
        }

        final SimpleListExtension sle = new SimpleListExtensionImpl();
        sle.setTreatAs(element.getChildText("treatAs", NS));

        final Element listInfo = element.getChild("listinfo", NS);
        ArrayList<Object> values = new ArrayList<Object>();
        for (final Element ge : listInfo.getChildren("group", NS)) {
            final Namespace ns = ge.getAttribute("ns") == null ? element.getNamespace() : Namespace.getNamespace(ge.getAttributeValue("ns"));
            final String elementName = ge.getAttributeValue("element");
            final String label = ge.getAttributeValue("label");
            values.add(new Group(ns, elementName, label));
        }

        sle.setGroupFields(values.toArray(new Group[values.size()]));
        values = values.size() == 0 ? values : new ArrayList<Object>();

        for (final Element se : listInfo.getChildren("sort", NS)) {
            LOG.debug("Parse cf:sort {}{}", se.getAttributeValue("element"), se.getAttributeValue("data-type"));
            final Namespace ns = se.getAttributeValue("ns") == null ? element.getNamespace() : Namespace.getNamespace(se.getAttributeValue("ns"));
            final String elementName = se.getAttributeValue("element");
            final String label = se.getAttributeValue("label");
            final String dataType = se.getAttributeValue("data-type");
            final boolean defaultOrder = se.getAttributeValue("default") == null ? false : new Boolean(se.getAttributeValue("default")).booleanValue();
            values.add(new Sort(ns, elementName, dataType, label, defaultOrder));
        }

        sle.setSortFields(values.toArray(new Sort[values.size()]));
        insertValues(sle, element.getChildren());

        return sle;
    }

    protected void addNotNullAttribute(final Element target, final String name, final Object value) {
        if (target == null || value == null) {
            return;
        } else {
            target.setAttribute(name, value.toString());
        }
    }

    public void insertValues(final SimpleListExtension sle, final List<Element> elements) {
        for (int i = 0; elements != null && i < elements.size(); i++) {
            final Element e = elements.get(i);
            final Group[] groups = sle.getGroupFields();

            for (final Group group2 : groups) {
                final Element value = e.getChild(group2.getElement(), group2.getNamespace());

                if (value == null) {
                    continue;
                }

                final Element group = new Element("group", TEMP);
                addNotNullAttribute(group, "element", group2.getElement());
                addNotNullAttribute(group, "label", group2.getLabel());
                addNotNullAttribute(group, "value", value.getText());
                addNotNullAttribute(group, "ns", group2.getNamespace().getURI());

                e.addContent(group);
            }

            final Sort[] sorts = sle.getSortFields();

            for (final Sort sort2 : sorts) {
                LOG.debug("Inserting for {} {}", sort2.getElement(), sort2.getDataType());
                final Element sort = new Element("sort", TEMP);
                // this is the default sort order, so I am just going to ignore
                // the actual values and add a number type. It really shouldn't
                // work this way. I should be checking to see if any of the elements
                // defined have a value then use that value. This will preserve the
                // sort order, however, if anyone is using the SleEntry to display
                // the value of the field, it will not give the correct value.
                // This, however, would require knowledge in the item parser that I don't
                // have right now.
                if (sort2.getDefaultOrder()) {
                    sort.setAttribute("label", sort2.getLabel());
                    sort.setAttribute("value", Integer.toString(i));
                    sort.setAttribute("data-type", Sort.NUMBER_TYPE);
                    e.addContent(sort);

                    continue;
                }

                final Element value = e.getChild(sort2.getElement(), sort2.getNamespace());
                if (value == null) {
                    LOG.debug("No value for {} : {}", sort2.getElement(), sort2.getNamespace());
                } else {
                    LOG.debug("{} value: {}", sort2.getElement(), value.getText());
                }
                if (value == null) {
                    continue;
                }

                addNotNullAttribute(sort, "label", sort2.getLabel());
                addNotNullAttribute(sort, "element", sort2.getElement());
                addNotNullAttribute(sort, "value", value.getText());
                addNotNullAttribute(sort, "data-type", sort2.getDataType());
                addNotNullAttribute(sort, "ns", sort2.getNamespace().getURI());
                e.addContent(sort);
                LOG.debug("Added {} {} = {}", sort, sort2.getLabel(), value.getText());
            }
        }
    }
}
