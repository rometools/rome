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

import org.jdom2.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LabelNamespaceElement {

    private static final Logger LOG = LoggerFactory.getLogger(LabelNamespaceElement.class);

    private String element;
    private String label;
    private Namespace namespace;

    public LabelNamespaceElement(final String label, final Namespace namespace, final String element) {
        this.element = element;
        this.label = label;
        this.namespace = namespace;
    }

    public String getElement() {
        return element;
    }

    public void setElement(final String element) {
        this.element = element;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(final Namespace namespace) {
        this.namespace = namespace;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LabelNamespaceElement other = (LabelNamespaceElement) obj;
        if (element == null ? other.element != null : !element.equals(other.element)) {
            LOG.debug("E {} != {}", element, other.element);
            return false;
        }
        if (label == null ? other.label != null : !label.equals(other.label)) {
            LOG.debug("L");
            return false;
        }
        if (namespace != other.namespace && (namespace == null || !namespace.equals(other.namespace))) {
            LOG.debug("N");
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (element != null ? element.hashCode() : 0);
        hash = 61 * hash + (label != null ? label.hashCode() : 0);
        hash = 61 * hash + (namespace != null ? namespace.hashCode() : 0);
        return hash;
    }

}