package org.rometools.feed.module.sle.io;

import org.jdom2.Namespace;

public class LabelNamespaceElement {
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
            System.out.println("E " + element + " != " + other.element);
            return false;
        }
        if (label == null ? other.label != null : !label.equals(other.label)) {
            System.out.println("L");
            return false;
        }
        if (namespace != other.namespace && (namespace == null || !namespace.equals(other.namespace))) {
            System.out.println("N");
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