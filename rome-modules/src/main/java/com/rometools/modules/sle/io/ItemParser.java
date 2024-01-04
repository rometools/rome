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
package com.rometools.modules.sle.io;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.sle.SleEntryImpl;
import com.rometools.modules.sle.types.DateValue;
import com.rometools.modules.sle.types.EntryValue;
import com.rometools.modules.sle.types.NumberValue;
import com.rometools.modules.sle.types.Sort;
import com.rometools.modules.sle.types.StringValue;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.impl.DateParser;

public class ItemParser implements com.rometools.rome.io.ModuleParser {

    public ItemParser() {
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
        return ModuleParser.TEMP.getURI();
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
        final SleEntryImpl sle = new SleEntryImpl();
        ArrayList<EntryValue> values = new ArrayList<EntryValue>();
        final List<Element> groups = element.getChildren("group", ModuleParser.TEMP);

        for (final Element group : groups) {
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

        sle.setGroupValues(values.toArray(new EntryValue[values.size()]));
        values = values.size() == 0 ? values : new ArrayList<EntryValue>();

        final List<Element> sorts = new ArrayList<Element>(element.getChildren("sort", ModuleParser.TEMP));

        for (final Element sort : sorts) {
            final String dataType = sort.getAttributeValue("data-type");
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
                LocalDateTime dateValue = null;

                try {
                    dateValue = DateParser.parseRFC822(sort.getAttributeValue("value"), locale);
                    if (dateValue == null) {
                        dateValue = DateParser.parseW3CDateTime(sort.getAttributeValue("value"), locale);
                    }
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

        sle.setSortValues(values.toArray(new EntryValue[values.size()]));

        return sle;
    }
}
