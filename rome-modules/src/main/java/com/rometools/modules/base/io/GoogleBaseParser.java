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

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

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
import com.rometools.modules.base.types.Size;
import com.rometools.modules.base.types.YearType;
import com.rometools.rome.feed.impl.BeanIntrospector;
import com.rometools.rome.feed.impl.PropertyDescriptor;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;

public class GoogleBaseParser implements ModuleParser {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleBaseParser.class);

    public static final char[] INTEGER_CHARS = "-1234567890".toCharArray();
    public static final char[] FLOAT_CHARS = "-1234567890.".toCharArray();
    public static final SimpleDateFormat SHORT_DT_FMT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat LONG_DT_FMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    static final Namespace NS = Namespace.getNamespace(GoogleBase.URI);
    static final Properties PROPS2TAGS = new Properties();
    static List<PropertyDescriptor> pds = null;

    static {
        try {
            pds = BeanIntrospector.getPropertyDescriptorsWithGetters(GoogleBaseImpl.class);
        } catch (final IllegalArgumentException e) {
            LOG.error("Failed to get property descriptors for GoogleBaseImpl", e);
        }

        try {
            PROPS2TAGS.load(GoogleBaseParser.class.getResourceAsStream("/com/rometools/modules/base/io/tags.properties"));
        } catch (final IOException e) {
            LOG.error("Unable to read properties file for Google Base tags!", e);
        }
    }

    public GoogleBaseParser() {
        super();
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        final HashMap<String, PropertyDescriptor> tag2pd = new HashMap<String, PropertyDescriptor>();
        final GoogleBaseImpl module = new GoogleBaseImpl();

        try {
            for (final PropertyDescriptor pd : pds) {
                final String tagName = GoogleBaseParser.PROPS2TAGS.getProperty(pd.getName());

                if (tagName == null) {
                    LOG.debug("Property: {} doesn't have a tag mapping.", pd.getName());
                } else {
                    tag2pd.put(tagName, pd);
                }
            }
        } catch (final Exception e) {
            throw new RuntimeException("Exception building tag to property mapping. ", e);
        }

        final List<Element> children = element.getChildren();
        final Iterator<Element> it = children.iterator();

        while (it.hasNext()) {
            final Element child = it.next();

            if (child.getNamespace().equals(GoogleBaseParser.NS)) {
                final PropertyDescriptor pd = tag2pd.get(child.getName());

                if (pd != null) {
                    try {
                        handleTag(child, pd, module);
                    } catch (final Exception e) {
                        LOG.warn("Unable to handle tag: " + child.getName(), e);
                    }
                }
            }
        }

        return module;
    }

    public static String stripNonValidCharacters(final char[] validCharacters, final String input) {
        final StringBuffer newString = new StringBuffer();

        for (int i = 0; i < input.length(); i++) {
            for (final char validCharacter : validCharacters) {
                if (input.charAt(i) == validCharacter) {
                    newString.append(validCharacter);
                }
            }
        }

        return newString.toString();
    }

    @Override
    public String getNamespaceUri() {
        return GoogleBase.URI;
    }

    private void handleTag(final Element tag, final PropertyDescriptor pd, final GoogleBase module) throws Exception {
        Object tagValue = null;

        if (pd.getPropertyType() == Integer.class || pd.getPropertyType().getComponentType() == Integer.class) {
            tagValue = new Integer(GoogleBaseParser.stripNonValidCharacters(GoogleBaseParser.INTEGER_CHARS, tag.getText()));
        } else if (pd.getPropertyType() == Float.class || pd.getPropertyType().getComponentType() == Float.class) {
            tagValue = new Float(GoogleBaseParser.stripNonValidCharacters(GoogleBaseParser.FLOAT_CHARS, tag.getText()));
        } else if (pd.getPropertyType() == String.class || pd.getPropertyType().getComponentType() == String.class) {
            tagValue = tag.getText();
        } else if (pd.getPropertyType() == URL.class || pd.getPropertyType().getComponentType() == URL.class) {
            tagValue = new URL(tag.getText().trim());
        } else if (pd.getPropertyType() == Boolean.class || pd.getPropertyType().getComponentType() == Boolean.class) {
            tagValue = new Boolean(tag.getText().trim());
        } else if (pd.getPropertyType() == Date.class || pd.getPropertyType().getComponentType() == Date.class) {
            final String text = tag.getText().trim();

            if (text.length() > 10) {
                tagValue = GoogleBaseParser.LONG_DT_FMT.parse(text);
            } else {
                tagValue = GoogleBaseParser.SHORT_DT_FMT.parse(text);
            }
        } else if (pd.getPropertyType() == IntUnit.class || pd.getPropertyType().getComponentType() == IntUnit.class) {
            tagValue = new IntUnit(tag.getText());
        } else if (pd.getPropertyType() == FloatUnit.class || pd.getPropertyType().getComponentType() == FloatUnit.class) {
            tagValue = new FloatUnit(tag.getText());
        } else if (pd.getPropertyType() == DateTimeRange.class || pd.getPropertyType().getComponentType() == DateTimeRange.class) {
            tagValue =
                    new DateTimeRange(LONG_DT_FMT.parse(tag.getChild("start", GoogleBaseParser.NS).getText().trim()), LONG_DT_FMT.parse(tag
                            .getChild("end", GoogleBaseParser.NS).getText().trim()));
        } else if (pd.getPropertyType() == ShippingType.class || pd.getPropertyType().getComponentType() == ShippingType.class) {
            final FloatUnit price = new FloatUnit(tag.getChild("price", GoogleBaseParser.NS).getText().trim());
            ShippingType.ServiceEnumeration service =
                    ShippingType.ServiceEnumeration.findByValue(tag.getChild("service", GoogleBaseParser.NS).getText().trim());

            if (service == null) {
                service = ShippingType.ServiceEnumeration.STANDARD;
            }

            final String country = tag.getChild("country", GoogleBaseParser.NS).getText().trim();
            tagValue = new ShippingType(price, service, country);
        } else if (pd.getPropertyType() == PaymentTypeEnumeration.class || pd.getPropertyType().getComponentType() == PaymentTypeEnumeration.class) {
            tagValue = PaymentTypeEnumeration.findByValue(tag.getText().trim());
        } else if (pd.getPropertyType() == PriceTypeEnumeration.class || pd.getPropertyType().getComponentType() == PriceTypeEnumeration.class) {
            tagValue = PriceTypeEnumeration.findByValue(tag.getText().trim());
        } else if (pd.getPropertyType() == CurrencyEnumeration.class || pd.getPropertyType().getComponentType() == CurrencyEnumeration.class) {
            tagValue = CurrencyEnumeration.findByValue(tag.getText().trim());
        } else if (pd.getPropertyType() == GenderEnumeration.class || pd.getPropertyType().getComponentType() == GenderEnumeration.class) {
            tagValue = GenderEnumeration.findByValue(tag.getText().trim());
        } else if (pd.getPropertyType() == YearType.class || pd.getPropertyType().getComponentType() == YearType.class) {
            tagValue = new YearType(tag.getText().trim());
        } else if (pd.getPropertyType() == Size.class || pd.getPropertyType().getComponentType() == Size.class) {
            tagValue = new Size(tag.getText().trim());
        }

        if (!pd.getPropertyType().isArray()) {
            pd.getWriteMethod().invoke(module, new Object[] {tagValue});
        } else {
            final Object[] current = (Object[]) pd.getReadMethod().invoke(module, (Object[]) null);
            final int newSize = current == null ? 1 : current.length + 1;
            final Object setValue = Array.newInstance(pd.getPropertyType().getComponentType(), newSize);

            int i = 0;

            for (; current != null && i < current.length; i++) {
                Array.set(setValue, i, current[i]);
            }

            Array.set(setValue, i, tagValue);
            pd.getWriteMethod().invoke(module, new Object[] {setValue});
        }
    }
}
