package org.intel.rs.util;

import java.util.Iterator;

public class NativeListIterator<T> implements Iterator<T> {
    private final NativeList<T> list;
    private final int count;

    private volatile int index = 0;

    public NativeListIterator(NativeList<T> list) {
        this.list = list;
        this.count = list.count();
    }

    @Override
    public boolean hasNext() {
        return index < count;
    }

    @Override
    public T next() {
        return list.get(index++);
    }
}
