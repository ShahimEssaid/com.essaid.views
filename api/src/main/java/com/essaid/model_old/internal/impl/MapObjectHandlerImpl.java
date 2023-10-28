package com.essaid.model_old.internal.impl;

import com.essaid.model_old.internal.ObjectHandler;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class MapObjectHandlerImpl extends ConcurrentHashMap<String, Object> implements ObjectHandler {

    private static final String ENTITY_TYPE_KEY = ObjectHandler.ENTITY_HANDLER_NS + ".entityType";

    public MapObjectHandlerImpl(Class<?> entityType) {
        setFeatureValue(ENTITY_TYPE_KEY, entityType);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

    @Override
    public Object getFeatureValue(String featureName) {
        return get(featureName);
    }

    @Override
    public Object setFeatureValue(String featureName, Object value) {
        return put(featureName, value);
    }
}
