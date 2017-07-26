/*
 * Sort.java
 *
 * Created on April 27, 2006, 6:50 PM
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
package com.rometools.modules.sle.types;

import java.io.Serializable;

import org.jdom2.Namespace;

import com.rometools.rome.feed.impl.ObjectBean;

/**
 * <p>
 * The <code>cf:sort</code> element is intended to inform the client that the property to which it
 * refers
 *
 * is one that is “sortable” – that is, that the client should provide a user interface that allows
 * the
 *
 * user to sort on that property.
 * </p>
 *
 * <p>
 * The <code>cf:sort</code> element can also be used to provide a label for the default sort that
 * appears
 *
 * in the list (in this case, only the label attribute should be included).
 * </p>
 *
 * <p>
 * The <code>cf:sort</code> element contains the following attributes:
 * </p>
 *
 * <ul>
 * <li><b>ns</b> - this attribute is the full namespace used in the property element. If the
 * attribute value is an empty string, it is assumed that the&nbsp;property does not live in a
 * namespace. If the ns attribute is omitted, the default value is the empty string. In the example
 * above, the ns attribute would contain "http://www.example.com/book". It would <i>not</i> contain
 * the namespace prefix.</li>
 * <li><b>element</b> - this attribute is the name of the property (without any namespace). In the
 * example above, the element attribute would contain "firstedition" If this attribute is omitted,
 * it is assumed that the label attribute is included and that this <code>cf:sort</code> element
 * refers to the default sort order.</li>
 *
 * <li><b>label</b> - this attribute contains a human-readable name for the property to which this
 * <code>cf:sort</code> element refers. If it is omitted, the client should use the value of the
 * "element" attribute as the human-readable name. The "label" attribute is required if the
 * "element" attribute is omitted.</li>
 * <li><b>data-type</b> - this attribute informs the client about the data-type of the property to
 * which this <code>cf:sort</code> element refers.. It contains one of the following values: date,
 * number, text. If it is omitted, the default value is text.</li>
 * <li><b>default</b> - this attribute indicates whether the property to which this
 * <code>cf:sort</code> element refers is the default sort order in the list. The allowed values are
 * "true" and "false". If omitted, the default value is "false". The items in the list <i>must</i>
 * be already be sorted by the element – this is, the client should not expect to have to resort by
 * this field if it displaying content directly from the list. The client should respect only the
 * first instance of default="true" that it encounters.</li>
 *
 * </ul>
 */
public class Sort implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    /**
     * dataType value for Date information.
     */
    public static final String DATE_TYPE = "date";
    /**
     * dataType value for Text information
     */
    public static final String TEXT_TYPE = "text";
    /**
     * DataType value for Number information.
     */
    public static final String NUMBER_TYPE = "number";
    private Namespace namespace = Namespace.XML_NAMESPACE;
    private final ObjectBean obj = new ObjectBean(Sort.class, this);
    private final String dataType;
    private final String element;
    private final String label;
    private final boolean defaultOrder;

    /**
     * @param namespace Namespace of the element
     * @param element Name of the element
     * @param dataType data-type of the element
     * @param label Label for the sort
     * @param defaultOrder indicates if this is the defaul order of the feed.
     */
    public Sort(final Namespace namespace, final String element, final String dataType, final String label, final boolean defaultOrder) {
        super();
        this.namespace = namespace == null ? Namespace.XML_NAMESPACE : namespace;
        this.element = element;
        this.dataType = dataType;
        this.label = label;
        this.defaultOrder = defaultOrder;
    }

    /**
     * Returns the dataType of the sort
     *
     * @return Returns the dataType of the sort
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Indicates this is the natural order of the feed.
     *
     * @return Indicates this is the natural order of the feed.
     */
    public boolean getDefaultOrder() {
        return defaultOrder;
    }

    /**
     * Name of the element.
     *
     * @return Name of the element.
     */
    public String getElement() {
        return element;
    }

    /**
     * User label for sorting.
     *
     * @return User label for sorting.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Namespace of the element
     *
     * @return Namespace of the element
     */
    public Namespace getNamespace() {
        return namespace;
    }

    @Override
    public Object clone() {
        return new Sort(namespace, element, dataType, label, defaultOrder);
    }

    @Override
    public boolean equals(final Object o) {
        return obj.equals(o);
    }

    @Override
    public int hashCode() {
        return obj.hashCode();
    }

    @Override
    public String toString() {
        return obj.toString();
    }
}
