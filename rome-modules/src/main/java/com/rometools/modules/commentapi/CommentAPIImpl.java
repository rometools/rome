package com.rometools.modules.commentapi;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.rometools.rome.feed.module.ModuleImpl;

public class CommentAPIImpl extends ModuleImpl implements CommentAPI {

    public CommentAPIImpl() {
        super(CommentAPI.class, CommentAPI.URI);
    }

    private String comment;
    private String commentRss;

    @Override
    public String getComment() {
        return this.comment;
    }

    @Override
    public void setComment(final String comment) {
        this.comment = comment;
    }

    @Override
    public String getCommentRss() {
        return this.commentRss;
    }

    @Override
    public void setCommentRss(final String commentRss) {
        this.commentRss = commentRss;
    }

    @Override
    public String getUri() {
        return CommentAPI.URI;
    }

    @Override
    public Class<? extends CopyFrom> getInterface() {
        return CommentAPI.class;
    }

    @Override
    public Object clone() {
        final CommentAPIImpl clone = new CommentAPIImpl();
        clone.setComment(this.getComment());
        clone.setCommentRss(this.getCommentRss());
        return clone;
    }

    @Override
    public void copyFrom(CopyFrom obj) {
        final CommentAPI source = (CommentAPI) obj;
        this.setComment(source.getComment());
        this.setCommentRss(source.getCommentRss());
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBean.beanEquals(CommentAPIImpl.class, this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBean.toString(CommentAPIImpl.class, this);
    }
    
}
