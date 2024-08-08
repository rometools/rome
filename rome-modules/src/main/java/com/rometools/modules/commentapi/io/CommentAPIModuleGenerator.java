package com.rometools.modules.commentapi.io;

import java.util.HashSet;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.commentapi.CommentAPI;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

public class CommentAPIModuleGenerator implements ModuleGenerator {

    private static final Namespace NS = Namespace.getNamespace("wfw", CommentAPI.URI);
    private static final Set<Namespace> NAMESPACES = new HashSet<Namespace>();

    static {
        NAMESPACES.add(NS);
    }

    public CommentAPIModuleGenerator() {
        super();
    }

    @Override
    public String getNamespaceUri() {
        return CommentAPI.URI;
    }

    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(Module module, Element element) {
        if (!(module instanceof CommentAPI)) {
            return;
        }

        final CommentAPI commentAPI = (CommentAPI) module;

        if (null != commentAPI.getComment()) {
            element.addContent(generateSimpleElement("comment", commentAPI.getCommentRss()));
        }

        if (null != commentAPI.getCommentRss()) {
            element.addContent(generateSimpleElement("commentRss", commentAPI.getCommentRss()));
        }
    }
    
    protected Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, CommentAPIModuleGenerator.NS);
        element.addContent(value);

        return element;
    }
}
