package com.essaid.model.internal.map;

import com.essaid.model.Model;
import com.essaid.model.internal.ElementHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MapElementHandler extends ConcurrentHashMap<Object, Object> implements ElementHandler, InvocationHandler {

    private final Class<?> elementInterface;
    private final Model model;

    public MapElementHandler(Class<?> elementInterface, Model model) {
        this.model = model;
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
        if(Objects.isNull(value)){
            return  remove(featureName);
        }
        return put(featureName, value);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestImpl request = new RequestImpl(proxy, method, args, this);
        return model.internal().handle(request);
    }

}
