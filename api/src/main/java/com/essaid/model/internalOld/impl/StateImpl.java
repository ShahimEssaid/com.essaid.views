package com.essaid.model.internalOld.impl;

import com.essaid.model.internal.State;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;

public class StateImpl extends HashMap<Object, Object> implements State {


    @Override
    public Lock readLock() {
        return null;
    }

    @Override
    public Lock writeLock() {
        return null;
    }

    @Override
    public Object get(Object key) {
        return super.get(key);
    }

    @Override
    public Object set(Object key, Object value) {
        return put(key, value);
    }
}
