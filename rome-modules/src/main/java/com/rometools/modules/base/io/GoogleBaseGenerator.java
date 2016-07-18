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
package com.rometools.modules.base.io;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.base.GoogleBase;
import com.rometools.modules.base.GoogleBaseImpl;
import com.rometools.modules.base.types.CurrencyEnumeration;
import com.rometools.modules.base.types.DateTimeRange;
import com.rometools.modules.base.types.FloatUnit;
import com.rometools.modules.base.types.GenderEnumeration;
import com.rometools.modules.base.types.IntUnit;
import com.rometools.modules.base.types.PaymentTypeEnumeration;
import com.rometools.modules.base.types.PriceTypeEnumeration;
import com.rometools.modules.base.types.ShippingType;
import com.rometools.modules.base.types.ShortDate;
import com.rometools.modules.base.types.Size;
import com.rometools.modules.base.types.YearType;
import com.rometools.rome.feed.impl.PropertyDescriptor;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

public class GoogleBaseGenerator implements ModuleGenerator {
    private static final Namespace NS = Namespace.getNamespace("g-core", GoogleBase.URI);

    private static final Logger LOG = LoggerFactory.getLogger(GoogleBaseGenerator.class);

    public GoogleBaseGenerator() {
        super();
    }

    @Override
    public String getNamespaceUri() {
        return GoogleBase.URI;
    }

    @Override
    public Set<Namespace> getNamespaces() {
        final HashSet<Namespace> set = new HashSet<Namespace>();
        set.add(GoogleBaseGenerator.NS);
        return set;
    }

    @Override
    public void generate(final Module module, final Element element) {
        final GoogleBaseImpl mod = (GoogleBaseImpl) module;
        final HashMap<Object, Object> props2tags = new HashMap<Object, Object>(GoogleBaseParser.PROPS2TAGS);
        final List<PropertyDescriptor> pds = GoogleBaseParser.pds;

        for (final PropertyDescriptor pd : pds) {
            final String tagName = (String) props2tags.get(pd.getName());

            if (tagName == null) {
                continue;
            }

            Object[] values = null;

            try {
                if (pd.getPropertyType().isArray()) {
                    values = (Object[]) pd.getReadMethod().invoke(mod, (Object[]) null);
                } else {
                    values = new Object[] {pd.getReadMethod().invoke(mod, (Object[]) null)};
                }

                for (int j = 0; values != null && j < values.length; j++) {
                    if (values[j] != null) {
                        element.addContent(generateTag(values[j], tagName));
                    }
                }
            } catch (final Exception e) {
                LOG.error("Error", e);
            }
        }
    }

    public Element generateTag(final Object o, final String tagName) {
        if (o instanceof URL || o instanceof Float || o instanceof Boolean || o instanceof Integer || o instanceof String || o instanceof FloatUnit
                || o instanceof IntUnit || o instanceof GenderEnumeration || o instanceof PaymentTypeEnumeration || o instanceof PriceTypeEnumeration
                || o instanceof CurrencyEnumeration || o instanceof Size || o instanceof YearType) {
            return generateSimpleElement(tagName, o.toString());
        } else if (o instanceof ShortDate) {
            return generateSimpleElement(tagName, GoogleBaseParser.SHORT_DT_FMT.format(o));
        } else if (o instanceof Date) {
            return generateSimpleElement(tagName, GoogleBaseParser.LONG_DT_FMT.format(o));
        } else if (o instanceof ShippingType) {
            final ShippingType st = (ShippingType) o;
            final Element element = new Element(tagName, GoogleBaseGenerator.NS);

            element.addContent(generateSimpleElement("country", st.getCountry()));

            element.addContent(generateSimpleElement("service", st.getService().toString()));

            element.addContent(generateSimpleElement("price", st.getPrice().toString()));

            return element;
        } else if (o instanceof DateTimeRange) {
            final DateTimeRange dtr = (DateTimeRange) o;
            final Element element = new Element(tagName, GoogleBaseGenerator.NS);
            element.addContent(generateSimpleElement("start", GoogleBaseParser.LONG_DT_FMT.format(dtr.getStart())));
            element.addContent(generateSimpleElement("end", GoogleBaseParser.LONG_DT_FMT.format(dtr.getEnd())));

            return element;
        }

        throw new RuntimeException("Unknown class type to handle: " + o.getClass().getName());
    }

    protected Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, GoogleBaseGenerator.NS);
        element.addContent(value);

        return element;
    }
}
