package com.rometools.modules.atom.io;

import com.rometools.modules.atom.modules.AtomLinkModule;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AtomModuleGenerator implements ModuleGenerator {

    static final Namespace NS = Namespace.getNamespace("atom", AtomLinkModule.URI);
    private static final Set<Namespace> NAMESPACES;

    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    @Override
    public final String getNamespaceUri() {
        return AtomLinkModule.URI;
    }

    @Override
    public final Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(Module module, Element element) {
        if (module instanceof AtomLinkModule) {
            AtomLinkModule m = (AtomLinkModule) module;
            generateLinc(m.getLink(), element);
        }
    }

    private void generateLinc(Link link, Element element) {
        if (link == null) {
            return;
        }

        Element linkElement = new Element("link", NS);

        if (link.getHref() != null) {
            Attribute href = new Attribute(AtomLinkAttribute.HREF, link.getHref());
            linkElement.setAttribute(href);
        }
        if (link.getType() != null) {
            Attribute type = new Attribute(AtomLinkAttribute.TYPE, link.getType());
            linkElement.setAttribute(type);
        }
        if (link.getRel() != null) {
            Attribute rel = new Attribute(AtomLinkAttribute.REL, link.getRel());
            linkElement.setAttribute(rel);
        }

        if (link.getHreflang() != null) {
            final Attribute hreflangAttribute = new Attribute(AtomLinkAttribute.HREF_LANG, link.getHreflang());
            linkElement.setAttribute(hreflangAttribute);
        }

        if (link.getTitle() != null) {
            final Attribute title = new Attribute(AtomLinkAttribute.TITLE, link.getTitle());
            linkElement.setAttribute(title);
        }

        if (link.getLength() != 0) {
            final Attribute lenght = new Attribute(AtomLinkAttribute.LENGTH, Long.toString(link.getLength()));
            linkElement.setAttribute(lenght);
        }

        element.addContent(linkElement);
    }

}
