package com.essaid.model.internalOld.impl;

import com.essaid.model.internalOld.Stateful;

import java.rmi.AlreadyBoundException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapStateful extends ConcurrentHashMap<Object, Object> implements Stateful {
    private Class<?> boundType;

    @Override
    public Class<?> getType() {
        return null;
    }

    @Override
    public Class<?> getBoundType() {
        return null;
    }

    @Override
    public void setBoundType(Class<?> boundType) throws AlreadyBoundException {
        if(this.boundType != null) throw new AlreadyBoundException();
        this.boundType = boundType;
    }

    @Override
    public Optional<Stateful> getNestedState(Object nestedStateKey, boolean create) {
        Stateful nestedStateful = (Stateful) computeIfAbsent(nestedStateKey, o -> create ? new ConcurrentHashMapStateful() : null);
        return Optional.ofNullable(nestedStateful);
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
