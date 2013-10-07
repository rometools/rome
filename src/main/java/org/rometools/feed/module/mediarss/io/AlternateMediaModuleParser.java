/*
 * AlternateMediaModuleParser.java
 *
 * Created on August 20, 2006, 5:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.rometools.feed.module.mediarss.io;

import org.jdom2.Namespace;

/**
 * 
 * @author cooper
 */
public class AlternateMediaModuleParser extends MediaModuleParser {

    private static final Namespace NS = Namespace.getNamespace("http://search.yahoo.com/mrss");

    /** Creates a new instance of AlternateMediaModuleParser */
    public AlternateMediaModuleParser() {
        super();
    }

    @Override
    public String getNamespaceUri() {
        return "http://search.yahoo.com/mrss";
    }

    @Override
    public Namespace getNS() {
        return AlternateMediaModuleParser.NS;
    }
}
