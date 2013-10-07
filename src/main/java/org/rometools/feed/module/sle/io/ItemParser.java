/*
 * ItemParser.java
 *
 * Created on April 29, 2006, 4:27 PM
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.rometools.feed.module.sle.SleEntryImpl;
import org.rometools.feed.module.sle.types.DateValue;
import org.rometools.feed.module.sle.types.EntryValue;
import org.rometools.feed.module.sle.types.NumberValue;
import org.rometools.feed.module.sle.types.Sort;
import org.rometools.feed.module.sle.types.StringValue;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.impl.DateParser;

/**
 * 
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ItemParser implements com.sun.syndication.io.ModuleParser {
    /** Creates a new instance of ItemParser */
    public ItemParser() {
        super();
    }

    /**
     * Returns the namespace URI this parser handles.
     * <p>
     * 
     * @return the namespace URI.
     */
    public String getNamespaceUri() {
        return ModuleParser.TEMP.getURI();
    }

    /**
     * Parses the XML node (JDOM element) extracting module information.
     * <p>
     * 
     * @param element the XML node (JDOM element) to extract module information from.
     * @return a module instance, <b>null</b> if the element did not have module information.
     */
    public Module parse(final Element element) {
        final SleEntryImpl sle = new SleEntryImpl();
        ArrayList values = new ArrayList();
        final List groups = element.getChildren("group", ModuleParser.TEMP);

        for (int i = 0; groups != null && i < groups.size(); i++) {
            final Element group = (Element) groups.get(i);
            final StringValue value = new StringValue();
            value.setElement(group.getAttributeValue("element"));
            value.setLabel(group.getAttributeValue("label"));
            value.setValue(group.getAttributeValue("value"));
            if (group.getAttributeValue("ns") != null) {
                value.setNamespace(Namespace.getNamespace(group.getAttributeValue("ns")));
            } else {
                value.setNamespace(element.getDocument().getRootElement().getNamespace());
            }
            values.add(value);
            element.removeContent(group);
        }

        sle.setGroupValues((EntryValue[]) values.toArray(new EntryValue[values.size()]));
        values = values.size() == 0 ? values : new ArrayList();

        final List sorts = new ArrayList(element.getChildren("sort", ModuleParser.TEMP));
        // System.out.println("]]] sorts on element"+sorts.size());
        for (int i = 0; sorts != null && i < sorts.size(); i++) {
            final Element sort = (Element) sorts.get(i);
            final String dataType = sort.getAttributeValue("data-type");
            // System.out.println("Doing datatype "+dataType +" :: "+sorts.size());
            if (dataType == null || dataType.equals(Sort.TEXT_TYPE)) {
                final StringValue value = new StringValue();
                value.setElement(sort.getAttributeValue("element"));
                value.setLabel(sort.getAttributeValue("label"));
                value.setValue(sort.getAttributeValue("value"));
                if (sort.getAttributeValue("ns") != null) {
                    value.setNamespace(Namespace.getNamespace(sort.getAttributeValue("ns")));
                } else {
                    value.setNamespace(element.getDocument().getRootElement().getNamespace());
                }
                values.add(value);

                element.removeContent(sort);

            } else if (dataType.equals(Sort.DATE_TYPE)) {
                final DateValue value = new DateValue();
                value.setElement(sort.getAttributeValue("element"));
                value.setLabel(sort.getAttributeValue("label"));
                if (sort.getAttributeValue("ns") != null) {
                    value.setNamespace(Namespace.getNamespace(sort.getAttributeValue("ns")));
                } else {
                    value.setNamespace(element.getDocument().getRootElement().getNamespace());
                }
                Date dateValue = null;

                try {
                    dateValue = DateParser.parseRFC822(sort.getAttributeValue("value"));
                    dateValue = dateValue == null ? DateParser.parseW3CDateTime(sort.getAttributeValue("value")) : dateValue;
                } catch (final Exception e) {
                    ; // ignore parse exceptions
                }

                value.setValue(dateValue);
                values.add(value);
                element.removeContent(sort);
            } else if (dataType.equals(Sort.NUMBER_TYPE)) {
                final NumberValue value = new NumberValue();
                value.setElement(sort.getAttributeValue("element"));
                value.setLabel(sort.getAttributeValue("label"));
                if (sort.getAttributeValue("ns") != null) {
                    value.setNamespace(Namespace.getNamespace(sort.getAttributeValue("ns")));
                } else {
                    value.setNamespace(element.getDocument().getRootElement().getNamespace());
                }

                try {
                    value.setValue(new BigDecimal(sort.getAttributeValue("value")));
                } catch (final NumberFormatException nfe) {
                    ; // ignore
                    values.add(value);
                    element.removeContent(sort);
                }
            } else {
                throw new RuntimeException("Unknown datatype");
            }
        }
        // System.out.println("Values created "+values.size()+" from sorts" +sorts.size());
        sle.setSortValues((EntryValue[]) values.toArray(new EntryValue[values.size()]));

        return sle;
    }
}
