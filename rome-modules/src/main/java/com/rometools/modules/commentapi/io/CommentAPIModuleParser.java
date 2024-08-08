package com.rometools.modules.commentapi.io;

import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.commentapi.CommentAPI;
import com.rometools.modules.commentapi.CommentAPIImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;

public class CommentAPIModuleParser implements ModuleParser {
    private static final Namespace NS = Namespace.getNamespace(CommentAPI.URI);

    @Override
    public String getNamespaceUri() {
        return CommentAPI.URI;
    }

    @Override
    public Module parse(Element element, Locale locale) {
        CommentAPIImpl cai = new CommentAPIImpl();

        Element tag = element.getChild("comment", CommentAPIModuleParser.NS);
        if (tag != null) {
            cai.setComment(tag.getText().trim());
        }

        tag = element.getChild("commentRss", CommentAPIModuleParser.NS);
        if (tag != null) {
            cai.setCommentRss(tag.getText().trim());
        }
        return cai;
    }
    
}
