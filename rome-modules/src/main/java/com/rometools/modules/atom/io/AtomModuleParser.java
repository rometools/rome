package com.rometools.modules.atom.io;

import com.rometools.modules.atom.modules.AtomLinkModule;
import com.rometools.modules.atom.modules.AtomLinkModuleImpl;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import com.rometools.rome.io.impl.NumberParser;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Locale;

public class AtomModuleParser implements ModuleParser {

    private static final Namespace NS = Namespace.getNamespace(AtomLinkModule.URI);

    @Override
    public String getNamespaceUri() {
        return null;
    }

    @Override
    public Module parse(Element element, Locale locale) {
        AtomLinkModuleImpl mod = new AtomLinkModuleImpl();
        if (element.getName().equals("channel") || element.getName().equals("item")) {
            Element link = element.getChild("link", NS);
            if (link != null) {
                Link l = parseLink(link);
                mod.setLink(l);
                return mod;
            }
        }
        return null;
    }

    private Link parseLink(final Element eLink) {

        final Link link = new Link();

        final String rel = getAttributeValue(eLink, AtomLinkAttribute.REL);
        if (rel != null) {
            link.setRel(rel);
        }

        final String type = getAttributeValue(eLink, AtomLinkAttribute.TYPE);
        if (type != null) {
            link.setType(type);
        }

        final String href = getAttributeValue(eLink, AtomLinkAttribute.HREF);
        if (href != null) {
            link.setHref(href);
        }

        final String title = getAttributeValue(eLink, AtomLinkAttribute.TITLE);
        if (title != null) {
            link.setTitle(title);
        }

        final String hrefLang = getAttributeValue(eLink, AtomLinkAttribute.HREF_LANG);
        if (hrefLang != null) {
            link.setHreflang(hrefLang);
        }

        final String length = getAttributeValue(eLink, AtomLinkAttribute.LENGTH);
        if (length != null) {
            final Long val = NumberParser.parseLong(length);
            if (val != null) {
                link.setLength(val.longValue());
            }
        }

        return link;

    }

    protected String getAttributeValue(final Element e, final String attributeName) {
        Attribute attr = e.getAttribute(attributeName);
        if (attr == null) {
            attr = e.getAttribute(attributeName, NS);
        }
        if (attr != null) {
            return attr.getValue();
        } else {
            return null;
        }
    }
}
