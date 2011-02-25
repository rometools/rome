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
package com.sun.syndication.feed.module.sle.io;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.sle.SimpleListExtension;
import com.sun.syndication.feed.module.sle.SimpleListExtensionImpl;
import com.sun.syndication.feed.module.sle.types.Group;
import com.sun.syndication.feed.module.sle.types.Sort;

import org.jdom.Element;
import org.jdom.Namespace;

import java.util.ArrayList;
import java.util.List;


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
    public Module parse(Element element) {
        if (element.getChild("treatAs", NS) == null) {
            return null;
        }

        SimpleListExtension sle = new SimpleListExtensionImpl();
        sle.setTreatAs(element.getChildText("treatAs", NS));

        Element listInfo = element.getChild("listinfo", NS);
        List groups = listInfo.getChildren("group", NS);
        ArrayList values = new ArrayList();

        for (int i = 0; (groups != null) && (i < groups.size()); i++) {
            Element ge = (Element) groups.get(i);
            Namespace ns = (ge.getAttribute("ns") == null) ? element.getNamespace() : Namespace.getNamespace(ge.getAttributeValue("ns"));
            String elementName = ge.getAttributeValue("element");
            String label = ge.getAttributeValue("label");
            values.add(new Group(ns, elementName, label));
        }

        sle.setGroupFields((Group[]) values.toArray(new Group[values.size()]));
        values = (values.size() == 0) ? values : new ArrayList();

        List sorts = listInfo.getChildren("sort", NS);

        for (int i = 0; (sorts != null) && (i < sorts.size()); i++) {
            Element se = (Element) sorts.get(i);
            System.out.println("Parse cf:sort "+se.getAttributeValue("element")+se.getAttributeValue("data-type"));
            Namespace ns = (se.getAttributeValue("ns") == null) ? element.getNamespace() : Namespace.getNamespace(se.getAttributeValue("ns"));
            String elementName = se.getAttributeValue("element");
            String label = se.getAttributeValue("label");
            String dataType = se.getAttributeValue("data-type");
            boolean defaultOrder = (se.getAttributeValue("default") == null) ? false : new Boolean(se.getAttributeValue("default")).booleanValue();
            values.add(new Sort(ns, elementName, dataType, label, defaultOrder));
        }

        sle.setSortFields((Sort[]) values.toArray(new Sort[values.size()]));
        insertValues(sle, element.getChildren());

        return sle;
    }

    protected void addNotNullAttribute(Element target, String name, Object value) {
        if ((target == null) || (value == null)) {
            return;
        } else {
            target.setAttribute(name, value.toString());
        }
    }

    public void insertValues(SimpleListExtension sle, List elements) {
        for (int i = 0; (elements != null) && (i < elements.size()); i++) {
            Element e = (Element) elements.get(i);
            Group[] groups = sle.getGroupFields();

            for (int g = 0; g < groups.length; g++) {
                Element value = e.getChild(groups[g].getElement(), groups[g].getNamespace());

                if (value == null) {
                    continue;
                }

                Element group = new Element("group", TEMP);
                addNotNullAttribute(group, "element", groups[g].getElement());
                addNotNullAttribute(group, "label", groups[g].getLabel());
                addNotNullAttribute(group, "value", value.getText());
                addNotNullAttribute(group, "ns", groups[g].getNamespace().getURI() );
                
                e.addContent(group);
            }

            Sort[] sorts = sle.getSortFields();

            for (int s = 0; s < sorts.length; s++) {
                System.out.println("Inserting for "+sorts[s].getElement()+" "+sorts[s].getDataType());
                Element sort = new Element("sort", TEMP);
                // this is the default sort order, so I am just going to ignore 
                // the actual values and add a number type. It really shouldn't 
                // work this way. I should be checking to see if any of the elements
                // defined have a value then use that value. This will preserve the
                // sort order, however, if anyone is using the SleEntry to display
                // the value of the field, it will not give the correct value.
                // This, however, would require knowledge in the item parser that I don't 
                // have right now.
                if (sorts[s].getDefaultOrder()) {
                    sort.setAttribute("label", sorts[s].getLabel());
                    sort.setAttribute("value", Integer.toString(i));
                    sort.setAttribute("data-type", Sort.NUMBER_TYPE);
                    e.addContent(sort);

                    continue;
                }
                //System.out.println(e.getName());
                Element value = e.getChild(sorts[s].getElement(), sorts[s].getNamespace());
                if(value == null ){
                    System.out.println("No value for "+sorts[s].getElement()+" : "+sorts[s].getNamespace());
                } else {
                    System.out.println(sorts[s].getElement() +" value: "+value.getText());
                }
                if (value == null) {
                    continue;
                }

                addNotNullAttribute(sort, "label", sorts[s].getLabel());
                addNotNullAttribute(sort, "element", sorts[s].getElement());
                addNotNullAttribute(sort, "value", value.getText());
                addNotNullAttribute(sort, "data-type", sorts[s].getDataType());
                addNotNullAttribute(sort, "ns", sorts[s].getNamespace().getURI() );
                e.addContent(sort);
                System.out.println("Added "+sort+" "+sorts[s].getLabel()+" = "+value.getText());
            }
        }
    }
}
