package org.intel.rs.util;

public interface NativeDecorator<T> extends AutoCloseable {
    T getInstance();

    void release();

    @Override
    default void close() {
        release();
    }
}
