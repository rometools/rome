package com.rometools.modules.thr.io;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.thr.ThreadingModule;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

/**
 * Currently no support for thr:count, thr:updated, thr:total link attributes.
 * 
 * @author <a href="mailto:andreas@feldschmid.com">Andreas Feldschmid</a>
 */
public class ThreadingModuleGenerator implements ModuleGenerator {

    private static final Namespace NAMESPACE = Namespace.getNamespace("thr", ThreadingModule.URI);
    private static final Set<Namespace> NAMESPACES;

    static {
        Set<Namespace> namespaces = new HashSet<Namespace>();
        namespaces.add(NAMESPACE);
        NAMESPACES = Collections.unmodifiableSet(namespaces);
    }

    @Override
    public String getNamespaceUri() {
        return ThreadingModule.URI;
    }

    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(Module module, Element element) {
        if (module != null && module instanceof ThreadingModule) {
            ThreadingModule threadedModule = (ThreadingModule) module;
            Element inReplyTo = new Element("in-reply-to", NAMESPACE);

            if (threadedModule.getHref() != null) {
                inReplyTo.setAttribute("href", threadedModule.getHref());
            }
            if (threadedModule.getRef() != null) {
                inReplyTo.setAttribute("ref", threadedModule.getRef());
            }
            if (threadedModule.getType() != null) {
                inReplyTo.setAttribute("type", threadedModule.getType());
            }
            if (threadedModule.getSource() != null) {
                inReplyTo.setAttribute("source", threadedModule.getSource());
            }

            element.addContent(inReplyTo);
        }
    }
}