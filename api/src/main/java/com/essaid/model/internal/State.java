package com.essaid.model.internal;

import java.util.concurrent.locks.ReadWriteLock;

public interface State extends ReadWriteLock {

    Object get(Object key);
    Object set(Object key, Object value);


}
