package org.rometools.feed.module.sle.io;

import com.sun.syndication.feed.impl.ObjectBean;
import org.jdom.Namespace;


public class LabelNamespaceElement {
    private String element;
    private String label;
    private Namespace namespace;
    
    
    public LabelNamespaceElement(String label, Namespace namespace, String element){
        this.element = element;
        this.label = label;
        this.namespace = namespace;
    }
    
    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LabelNamespaceElement other = (LabelNamespaceElement) obj;
        if ((this.element == null) ? (other.element != null) : !this.element.equals(other.element)) {
            System.out.println("E "+this.element+" != "+other.element);
            return false;
        }
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            System.out.println("L");
            return false;
        }
        if (this.namespace != other.namespace && (this.namespace == null || !this.namespace.equals(other.namespace))) {
            System.out.println("N");
            return false;
        }
       
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.element != null ? this.element.hashCode() : 0);
        hash = 61 * hash + (this.label != null ? this.label.hashCode() : 0);
        hash = 61 * hash + (this.namespace != null ? this.namespace.hashCode() : 0);
        return hash;
    }


    
   
}