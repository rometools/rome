package com.sun.syndication.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * The XmlReaderException is thrown by the XmlReader constructors if the charset
 * encoding can not be determined according to the XML 1.0 specification and RFC
 * 3023.
 * <p>
 * The exception returns the unconsumed InputStream to allow the application to
 * do an alternate processing with the stream. Note that the original
 * InputStream given to the XmlReader cannot be used as that one has been
 * already read.
 * <p>
 * 
 * @author Alejandro Abdelnur
 * 
 */
public class XmlReaderException extends IOException {
    private final String _bomEncoding;
    private final String _xmlGuessEncoding;
    private final String _xmlEncoding;
    private final String _contentTypeMime;
    private final String _contentTypeEncoding;
    private final InputStream _is;

    /**
     * Creates an exception instance if the charset encoding could not be
     * determined.
     * <p>
     * Instances of this exception are thrown by the XmlReader.
     * <p>
     * 
     * @param msg message describing the reason for the exception.
     * @param bomEnc BOM encoding.
     * @param xmlGuessEnc XML guess encoding.
     * @param xmlEnc XML prolog encoding.
     * @param is the unconsumed InputStream.
     * 
     */
    public XmlReaderException(final String msg, final String bomEnc, final String xmlGuessEnc, final String xmlEnc, final InputStream is) {
        this(msg, null, null, bomEnc, xmlGuessEnc, xmlEnc, is);
    }

    /**
     * Creates an exception instance if the charset encoding could not be
     * determined.
     * <p>
     * Instances of this exception are thrown by the XmlReader.
     * <p>
     * 
     * @param msg message describing the reason for the exception.
     * @param ctMime MIME type in the content-type.
     * @param ctEnc encoding in the content-type.
     * @param bomEnc BOM encoding.
     * @param xmlGuessEnc XML guess encoding.
     * @param xmlEnc XML prolog encoding.
     * @param is the unconsumed InputStream.
     * 
     */
    public XmlReaderException(final String msg, final String ctMime, final String ctEnc, final String bomEnc, final String xmlGuessEnc, final String xmlEnc,
            final InputStream is) {
        super(msg);
        this._contentTypeMime = ctMime;
        this._contentTypeEncoding = ctEnc;
        this._bomEncoding = bomEnc;
        this._xmlGuessEncoding = xmlGuessEnc;
        this._xmlEncoding = xmlEnc;
        this._is = is;
    }

    /**
     * Returns the BOM encoding found in the InputStream.
     * <p>
     * 
     * @return the BOM encoding, null if none.
     * 
     */
    public String getBomEncoding() {
        return this._bomEncoding;
    }

    /**
     * Returns the encoding guess based on the first bytes of the InputStream.
     * <p>
     * 
     * @return the encoding guess, null if it couldn't be guessed.
     * 
     */
    public String getXmlGuessEncoding() {
        return this._xmlGuessEncoding;
    }

    /**
     * Returns the encoding found in the XML prolog of the InputStream.
     * <p>
     * 
     * @return the encoding of the XML prolog, null if none.
     * 
     */
    public String getXmlEncoding() {
        return this._xmlEncoding;
    }

    /**
     * Returns the MIME type in the content-type used to attempt determining the
     * encoding.
     * <p>
     * 
     * @return the MIME type in the content-type, null if there was not
     *         content-type or the encoding detection did not involve HTTP.
     * 
     */
    public String getContentTypeMime() {
        return this._contentTypeMime;
    }

    /**
     * Returns the encoding in the content-type used to attempt determining the
     * encoding.
     * <p>
     * 
     * @return the encoding in the content-type, null if there was not
     *         content-type, no encoding in it or the encoding detection did not
     *         involve HTTP.
     * 
     */
    public String getContentTypeEncoding() {
        return this._contentTypeEncoding;
    }

    /**
     * Returns the unconsumed InputStream to allow the application to do an
     * alternate encoding detection on the InputStream.
     * <p>
     * 
     * @return the unconsumed InputStream.
     * 
     */
    public InputStream getInputStream() {
        return this._is;
    }
}
