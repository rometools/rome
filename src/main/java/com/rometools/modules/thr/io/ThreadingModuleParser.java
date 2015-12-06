package com.rometools.modules.thr.io;

import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.thr.ThreadingModule;
import com.rometools.modules.thr.ThreadingModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;

/**
 * Currently no support for thr:count, thr:updated, thr:total link attributes.
 * 
 * @author <a href="mailto:andreas@feldschmid.com">Andreas Feldschmid</a>
 */
public class ThreadingModuleParser implements ModuleParser {

    private static final Namespace NS = Namespace.getNamespace(ThreadingModule.URI);

    @Override
    public String getNamespaceUri() {
        return ThreadingModule.URI;
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        final ThreadingModule tm = new ThreadingModuleImpl();
        Element inReplyTo = element.getChild("in-reply-to", ThreadingModuleParser.NS);

        if (inReplyTo != null) {
            tm.setHref(inReplyTo.getAttributeValue("href"));
            tm.setRef(inReplyTo.getAttributeValue("ref"));
            tm.setSource(inReplyTo.getAttributeValue("source"));
            tm.setType(inReplyTo.getAttributeValue("type"));
            return tm;
        }

        return null;
    }
}
