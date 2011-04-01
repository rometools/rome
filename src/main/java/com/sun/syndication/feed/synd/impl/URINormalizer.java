package com.sun.syndication.feed.synd.impl;

/**
 * Utility class for normalizing an URI as specified in RFC 2396bis.
 * <p>
 * @author Alejandro Abdelnur
 */
public class URINormalizer {

    /**
     * Normalizes an URI as specified in RFC 2396bis.
     * <p>
     * @param uri to normalize.
     * @return the normalized value of the given URI, or <b>null</b> if the given URI was <b>null</b>.
     */
    public static String normalize(String uri) {
        String normalizedUri = null;
        if (uri!=null) {
            normalizedUri = uri; //TODO THIS HAS TO BE IMPLEMENTED
        }
        return normalizedUri;
    }
}
