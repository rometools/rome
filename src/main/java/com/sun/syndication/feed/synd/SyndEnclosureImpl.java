package com.sun.syndication.feed.synd;

import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.CopyFromHelper;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * @author Alejandro Abdelnur
 */
public class SyndEnclosureImpl implements Serializable,SyndEnclosure {
    private ObjectBean _objBean;
    private String _url;
    private String _type;
    private long   _length;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public SyndEnclosureImpl() {
        _objBean = new ObjectBean(SyndEnclosure.class,this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException {
        return _objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals() method.
     * <p>
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    public boolean equals(Object other) {
        return _objBean.equals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * @return the hashcode of the bean object.
     *
     */
    public int hashCode() {
        return _objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * @return String representation for the object.
     *
     */
    public String toString() {
        return _objBean.toString();
    }

    /**
     * Returns the enclosure URL.
     * <p/>
     *
     * @return the enclosure URL, <b>null</b> if none.
     */
    public String getUrl() {
        return _url;
    }

    /**
     * Sets the enclosure URL.
     * <p/>
     *
     * @param url the enclosure URL to set, <b>null</b> if none.
     */
    public void setUrl(String url) {
        _url = url;
    }

    /**
     * Returns the enclosure length.
     * <p/>
     *
     * @return the enclosure length, <b>null</b> if none.
     */
    public long getLength() {
        return _length;
    }

    /**
     * Sets the enclosure length.
     * <p/>
     *
     * @param length the enclosure length to set, <b>null</b> if none.
     */
    public void setLength(long length) {
        _length = length;
    }

    /**
     * Returns the enclosure type.
     * <p/>
     *
     * @return the enclosure type, <b>null</b> if none.
     */
    public String getType() {
        return _type;
    }

    /**
     * Sets the enclosure type.
     * <p/>
     *
     * @param type the enclosure type to set, <b>null</b> if none.
     */
    public void setType(String type) {
        _type = type;
    }

    public Class getInterface() {
        return SyndEnclosure.class;
    }

    public void copyFrom(Object obj) {
        COPY_FROM_HELPER.copy(this,obj);
    }

    private static final CopyFromHelper COPY_FROM_HELPER;

    static {
        Map basePropInterfaceMap = new HashMap();
        basePropInterfaceMap.put("url",String.class);
        basePropInterfaceMap.put("type",String.class);
        basePropInterfaceMap.put("length",Long.TYPE);

        Map basePropClassImplMap = Collections.EMPTY_MAP;

        COPY_FROM_HELPER = new CopyFromHelper(SyndEnclosure.class,basePropInterfaceMap,basePropClassImplMap);
    }

}
