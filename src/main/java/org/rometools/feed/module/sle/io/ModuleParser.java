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
package org.rometools.feed.module.sle.io;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.rometools.feed.module.sle.SimpleListExtension;
import org.rometools.feed.module.sle.SimpleListExtensionImpl;
import org.rometools.feed.module.sle.types.Group;
import org.rometools.feed.module.sle.types.Sort;

import com.sun.syndication.feed.module.Module;

/**
 * 
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ModuleParser implements com.sun.syndication.io.ModuleParser {
    static final Namespace NS = Namespace.getNamespace("cf", SimpleListExtension.URI);
    public static final Namespace TEMP = Namespace.getNamespace("rome-sle", "urn:rome:sle");

    /** Creates a new instance of ModuleParser */
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
    public Module parse(final Element element) {
        if (element.getChild("treatAs", NS) == null) {
            return null;
        }

        final SimpleListExtension sle = new SimpleListExtensionImpl();
        sle.setTreatAs(element.getChildText("treatAs", NS));

        final Element listInfo = element.getChild("listinfo", NS);
        final List groups = listInfo.getChildren("group", NS);
        ArrayList values = new ArrayList();

        for (int i = 0; groups != null && i < groups.size(); i++) {
            final Element ge = (Element) groups.get(i);
            final Namespace ns = ge.getAttribute("ns") == null ? element.getNamespace() : Namespace.getNamespace(ge.getAttributeValue("ns"));
            final String elementName = ge.getAttributeValue("element");
            final String label = ge.getAttributeValue("label");
            values.add(new Group(ns, elementName, label));
        }

        sle.setGroupFields((Group[]) values.toArray(new Group[values.size()]));
        values = values.size() == 0 ? values : new ArrayList();

        final List sorts = listInfo.getChildren("sort", NS);

        for (int i = 0; sorts != null && i < sorts.size(); i++) {
            final Element se = (Element) sorts.get(i);
            System.out.println("Parse cf:sort " + se.getAttributeValue("element") + se.getAttributeValue("data-type"));
            final Namespace ns = se.getAttributeValue("ns") == null ? element.getNamespace() : Namespace.getNamespace(se.getAttributeValue("ns"));
            final String elementName = se.getAttributeValue("element");
            final String label = se.getAttributeValue("label");
            final String dataType = se.getAttributeValue("data-type");
            final boolean defaultOrder = se.getAttributeValue("default") == null ? false : new Boolean(se.getAttributeValue("default")).booleanValue();
            values.add(new Sort(ns, elementName, dataType, label, defaultOrder));
        }

        sle.setSortFields((Sort[]) values.toArray(new Sort[values.size()]));
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

    public void insertValues(final SimpleListExtension sle, final List elements) {
        for (int i = 0; elements != null && i < elements.size(); i++) {
            final Element e = (Element) elements.get(i);
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
                System.out.println("Inserting for " + sort2.getElement() + " " + sort2.getDataType());
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
                // System.out.println(e.getName());
                final Element value = e.getChild(sort2.getElement(), sort2.getNamespace());
                if (value == null) {
                    System.out.println("No value for " + sort2.getElement() + " : " + sort2.getNamespace());
                } else {
                    System.out.println(sort2.getElement() + " value: " + value.getText());
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
                System.out.println("Added " + sort + " " + sort2.getLabel() + " = " + value.getText());
            }
        }
    }
}
