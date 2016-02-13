package com.rometools.modules.atom.modules;

import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.Module;

public interface AtomLinkModule extends Module {

    public static final String URI = "http://www.w3.org/2005/Atom";

    public Link getLink();

    public void setLink(Link link);
}
