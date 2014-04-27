package com.rometools.propono.blogclient.metaweblog;

import java.util.Iterator;

class NoOpIterator<T> implements Iterator<T> {

    /** No-op */
    @Override
    public boolean hasNext() {
        return false;
    }

    /** No-op */
    @Override
    public T next() {
        return null;
    }

    /** No-op */
    @Override
    public void remove() {
    }

}