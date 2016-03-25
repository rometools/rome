/*
 * CustomTagImpl.java
 *
 * Created on February 6, 2006, 12:06 AM
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.rometools.modules.base;

import java.net.URL;
import java.util.Date;

import com.rometools.modules.base.types.DateTimeRange;
import com.rometools.modules.base.types.FloatUnit;
import com.rometools.modules.base.types.IntUnit;
import com.rometools.modules.base.types.ShortDate;

/**
 * @version $Revision: 1.1 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class CustomTagImpl implements CustomTag {
    private Object value;
    private final String name;

    /** Creates a new instance of CustomTagImpl */
    public CustomTagImpl(final String name, final String value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final Integer value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final Float value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final IntUnit value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final FloatUnit value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final ShortDate value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final Date value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final DateTimeRange value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final URL value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final Boolean value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final Location value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof CustomTag) {
            final CustomTag tag = (CustomTag) o;
            if (name.equals(tag.getName()) && value.equals(tag.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates and returns a copy of this object. The precise meaning of "copy" may depend on the
     * class of the object. The general intent is that, for any object <tt>x</tt>, the expression:
     * <blockquote>
     *
     * <pre>
     * x.clone() != x
     * </pre>
     *
     * </blockquote> will be true, and that the expression: <blockquote>
     *
     * <pre>
     * x.clone().getClass() == x.getClass()
     * </pre>
     *
     * </blockquote> will be <tt>true</tt>, but these are not absolute requirements. While it is
     * typically the case that: <blockquote>
     *
     * <pre>
     * x.clone().equals(x)
     * </pre>
     *
     * </blockquote> will be <tt>true</tt>, this is not an absolute requirement.
     * <p>
     * By convention, the returned object should be obtained by calling <tt>super.clone</tt>. If a
     * class and all of its superclasses (except <tt>Object</tt>) obey this convention, it will be
     * the case that <tt>x.clone().getClass() == x.getClass()</tt>.
     * <p>
     * By convention, the object returned by this method should be independent of this object (which
     * is being cloned). To achieve this independence, it may be necessary to modify one or more
     * fields of the object returned by <tt>super.clone</tt> before returning it. Typically, this
     * means copying any mutable objects that comprise the internal "deep structure" of the object
     * being cloned and replacing the references to these objects with references to the copies. If
     * a class contains only primitive fields or references to immutable objects, then it is usually
     * the case that no fields in the object returned by <tt>super.clone</tt> need to be modified.
     * <p>
     * The method <tt>clone</tt> for class <tt>Object</tt> performs a specific cloning operation.
     * First, if the class of this object does not implement the interface <tt>Cloneable</tt>, then
     * a <tt>CloneNotSupportedException</tt> is thrown. Note that all arrays are considered to
     * implement the interface <tt>Cloneable</tt>. Otherwise, this method creates a new instance of
     * the class of this object and initializes all its fields with exactly the contents of the
     * corresponding fields of this object, as if by assignment; the contents of the fields are not
     * themselves cloned. Thus, this method performs a "shallow copy" of this object, not a
     * "deep copy" operation.
     * <p>
     * The class <tt>Object</tt> does not itself implement the interface <tt>Cloneable</tt>, so
     * calling the <tt>clone</tt> method on an object whose class is <tt>Object</tt> will result in
     * throwing an exception at run time.
     *
     * @return a clone of this instance.
     * @exception CloneNotSupportedException if the object's class does not support the
     *                <code>Cloneable</code> interface. Subclasses that override the
     *                <code>clone</code> method can also throw this exception to indicate that an
     *                instance cannot be cloned.
     * @see java.lang.Cloneable
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        final CustomTagImpl cti = new CustomTagImpl(name, "");
        cti.value = value;
        return cti;
    }

    @Override
    public String toString() {
        return "[custom name=\"" + name + "\" value=\"" + value.toString() + "\"]";
    }

    public static class Location {
        private final String value;

        public Location(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public Object clone() {
            return new Location(value);
        }

        @Override
        public String toString() {
            return value;
        }

        @Override
        public boolean equals(final Object o) {
            if (o instanceof Location && ((Location) o).value.equals(value)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
