package com.sun.syndication.io;

import org.jdom.JDOMException;
import org.xml.sax.XMLReader;

/*
 *  This code is needed to fix the security problem outlined in http://www.securityfocus.com/archive/1/297714
 * 
 * Unfortunately there isn't an easy way to check if an XML parser supports a particular feature, so
 * we need to set it and catch the exception if it fails. We also need to subclass the JDom SAXBuilder 
 * class in order to get access to the underlying SAX parser - otherwise the features don't get set until
 * we are already building the document, by which time it's too late to fix the problem.
 * 
 * Crimson is one parser which is known not to support these features.
 * 
 */
public class SAXBuilder extends org.jdom.input.SAXBuilder {

	public SAXBuilder(boolean _validate) {
		super(_validate);
	}

	public XMLReader createParser() throws JDOMException {
		return super.createParser();
	}
	
}
