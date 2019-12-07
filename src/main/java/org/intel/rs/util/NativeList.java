package org.intel.rs.util;

public interface NativeList<T> extends Iterable<T> {
    T get(int index);
    int count();
}
