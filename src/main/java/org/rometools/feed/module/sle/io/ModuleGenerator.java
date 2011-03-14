/*
 * ModuleGenerator.java
 *
 * Created on April 27, 2006, 11:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.rometools.feed.module.sle.io;

import com.sun.syndication.feed.module.Module;
import org.rometools.feed.module.sle.SimpleListExtension;
import org.rometools.feed.module.sle.types.Group;
import org.rometools.feed.module.sle.types.Sort;

import org.jdom.Element;
import org.jdom.Namespace;

import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ModuleGenerator implements com.sun.syndication.io.ModuleGenerator {
    private static final Set NAMESPACES = new HashSet();

    static {
        NAMESPACES.add(ModuleParser.NS);
    }

    /** Creates a new instance of ModuleGenerator */
    public ModuleGenerator() {
        super();
    }

    /**
     * Returns the namespace URI this generator handles.
     * <p>
     *
     * @return the namespace URI.
     */
    public String getNamespaceUri() {
        return SimpleListExtension.URI;
    }

    /**
     * Returns a set with all the URIs (JDOM Namespace elements) this module generator uses.
     * <p/>
     * It is used by the the feed generators to add their namespace definition in
     * the root element of the generated document (forward-missing of Java 5.0 Generics).
     * <p/>
     *
     * @return a set with all the URIs (JDOM Namespace elements) this module generator uses.
     */
    public Set getNamespaces() {
        return NAMESPACES;
    }

    /**
     * Generates and injectts module metadata in a XML node (JDOM element).
     * <p>
     *
     * @param module the module to inject into the XML node (JDOM element).
     * @param element the XML node to inject the module metadata to.
     */
    public void generate(Module module, Element element) {
        if (!(module instanceof SimpleListExtension)) {
            return;
        }

        SimpleListExtension sle = (SimpleListExtension) module;
        addNotNullElement(element, "treatAs", sle.getTreatAs());

        Group[] groups = sle.getGroupFields();
        Element listInfo = new Element("listinfo", ModuleParser.NS);

        for (int i = 0; (groups != null) && (i < groups.length); i++) {
            Element group = new Element("group", ModuleParser.NS);

            if (groups[i].getNamespace() != Namespace.NO_NAMESPACE) {
                addNotNullAttribute(group, "ns", groups[i].getNamespace().getURI());
            }

            addNotNullAttribute(group, "element", groups[i].getElement());
            addNotNullAttribute(group, "label", groups[i].getLabel());
            listInfo.addContent(group);
        }

        Sort[] sorts = sle.getSortFields();

        for (int i = 0; (sorts != null) && (i < sorts.length); i++) {
            Element sort = new Element("sort", ModuleParser.NS);

            if (sorts[i].getNamespace() != Namespace.NO_NAMESPACE) {
                addNotNullAttribute(sort, "ns", sorts[i].getNamespace().getURI());
            }

            addNotNullAttribute(sort, "element", sorts[i].getElement());
            addNotNullAttribute(sort, "label", sorts[i].getLabel());
            addNotNullAttribute(sort, "data-type", sorts[i].getDataType());

            if (sorts[i].getDefaultOrder()) {
                addNotNullAttribute(sort, "default", "true");
            }

            listInfo.addContent(sort);
        }

        if (listInfo.getChildren().size() > 0) {
            element.addContent(listInfo);
        }
    }

    protected void addNotNullAttribute(Element target, String name, Object value) {
        if ((target == null) || (value == null)) {
            return;
        } else {
            target.setAttribute(name, value.toString());
        }
    }

    protected Element addNotNullElement(Element target, String name, Object value) {
        if (value == null) {
            return null;
        } else {
            Element e = generateSimpleElement(name, value.toString());
            target.addContent(e);

            return e;
        }
    }

    protected Element generateSimpleElement(String name, String value) {
        Element element = new Element(name, ModuleParser.NS);
        element.addContent(value);

        return element;
    }
}
