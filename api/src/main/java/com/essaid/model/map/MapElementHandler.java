package com.essaid.model.map;

import com.essaid.model.EntityManager;
import com.essaid.model.internal.ElementHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class MapElementHandler extends ConcurrentHashMap<Object, Object> implements ElementHandler, InvocationHandler {

    private final Class<?> elementInterface;
    private final EntityManager entityManager;

    public MapElementHandler(Class<?> elementInterface, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.elementInterface = elementInterface;
    }


    @Override
    public Class<?> getElementType() {
        return elementInterface;
    }

    @Override
    public Object getFeatureValue(String featureName) {
        return get(featureName);
    }

    @Override
    public Object setFeatureValue(String featureName, Object value) {
        return put(featureName, value);
    }

    @Override
    public Object unsetFeatureValue(String featureName) {
        return remove(featureName);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestImpl request = new RequestImpl(proxy, method, args, this);
        return entityManager.internal().handle(request);
    }



}
