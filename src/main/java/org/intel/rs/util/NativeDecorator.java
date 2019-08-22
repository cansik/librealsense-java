package org.intel.rs.util;

public interface NativeDecorator<T> {
    T getInstance();

    void release();
}
