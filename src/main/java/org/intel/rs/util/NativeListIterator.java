package org.intel.rs.util;

import java.util.Iterator;

public class NativeListIterator<T extends NativeList<T>> implements Iterator<T> {
    private final T list;
    private final int count;

    private volatile int index = 0;

    public NativeListIterator(T list) {
        this.list = list;
        this.count = list.count();
    }

    @Override
    public boolean hasNext() {
        return index < count;
    }

    @Override
    public T next() {
        return (T)list.get(index++);
    }
}
