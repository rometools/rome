package com.rometools.rome.feed.synd;

/**
 * Represents a foreign attribute included on a link associated with an entry.
 */
public interface SyndLinkAttribute {

    /**
     * Returns the name of the link attribute
     *
     * @return the name of the link attribute
     */
    public String getName();

    /**
     * Sets the name of the link attribute
     *
     * @param name the new name of the link attribute
     */
    public void setName(String name);

    /**
     * Returns the value of the link attribute
     *
     * @return the value of the link attribute
     */
    public String getValue();

    /**
     * Sets the value of the link attribute
     *
     * @param value the new value of the link attribute
     */
    public void setValue(String value);

    /**
     * Returns the value of the attribute namespace URI
     *
     * @return the value of the attribute namespace URI
     */
    public String getNamespaceURI();

    /**
     * Sets the value of the attribute namespace URI
     *
     * @param namespaceURI the new value for the attribute namespace URI
     */
    public void setNamespaceURI(String namespaceURI);

    /**
     * Returns the prefix of the attribute namespace
     *
     * @return the prefix of the attribute namespace
     */
    public String getNamespacePrefix();

    /**
     * Sets the prefix of the attribute namespace
     *
     * @param prefix the prefix of the attribute namespace
     */
    public void setNamespacePrefix(String prefix);

    /**
     * Creates a deep 'bean' clone of the object.
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public abstract Object clone() throws CloneNotSupportedException;

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals()
     * method.
     *
     * @param other the object to compare with
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public abstract boolean equals(Object other);

    /**
     * Returns a hashcode value for the object.
     * It follows the contract defined by the Object hashCode() method.
     *
     * @return the hashcode of the bean object.
     *
     */
    @Override
    public abstract int hashCode();

    /**
     * Returns the String representation for the object.
     *
     * @return String representation for the object.
     *
     */
    @Override
    public abstract String toString();
}
