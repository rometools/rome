package com.rometools.modules.commentapi;

import com.rometools.rome.feed.module.Module;

public interface CommentAPI extends Module {
    
    static final String URI = "http://wellformedweb.org/CommentAPI/";

    String getComment();

    void setComment(final String comment);

    String getCommentRss();

    void setCommentRss(final String commentRss);

}
