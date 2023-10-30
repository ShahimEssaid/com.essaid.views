package com.essaid.model.internal.map.impl;

import com.essaid.model.EntityManager;
import com.essaid.model.internal.map.Request;
import com.essaid.model.internal.map.RequestHandler;
import com.essaid.model.internal.map.RequestHandlerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class CGetRequestHandlerFactory implements RequestHandlerFactory {


    @Override
    public RequestHandler getHandler(Method method, EntityManager entityManager) {
        if (method.isDefault()) return null;
        RequestHandler handler = null;
        Class<?> returnType = method.getReturnType();
        if (method.getName().startsWith("cget") && method.getParameterCount() == 0 && returnType != void.class) {
            if (returnType.isPrimitive()) {
                throw new IllegalStateException("Can't use cget* for primitives. Method: " + method);
            }

            String featureName = method.getName().substring(4);
            handler = new CGetRequestHandler(featureName, returnType, entityManager);


        }
        return handler;
    }

    @RequiredArgsConstructor
    @Getter
    public static class CGetRequestHandler implements RequestHandler {
        private final String featureName;
        private final Class<?> returnType;
        private final EntityManager entityManager;
        private final WeakHashMap<Class<?>, Method> getters = new WeakHashMap<>();
        private final WeakHashMap<Class<?>, Method> setters = new WeakHashMap<>();


        @Override
        public Object handleRequest(Request request) {
            Method getterMethod = getGetter(request);
            Object value = null;
            try {
                value = getterMethod.invoke(request.getProxy());
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            if (value == null) {
                if (Map.class.isAssignableFrom(returnType)) {
                    value = entityManager.createMap();
                } else if (List.class.isAssignableFrom(returnType)) {
                    value = entityManager.createList();
                } else {
                    value = entityManager.create(returnType);
                }

                Method setterMethod = getSetter(request);
                try {
                    setterMethod.invoke(request.getProxy(), value);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            return value;
        }

        private Method getGetter(Request request) {
            return getters.computeIfAbsent(request.getProxy().getClass(), aClass -> findGetter(request));
        }

        private Method findGetter(Request request) {
            try {
                return request.getProxy().getClass().getMethod("get" + featureName);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        private Method getSetter(Request request) {
            return setters.computeIfAbsent(request.getProxy().getClass(), aClass -> findSetter(request));
        }

        private Method findSetter(Request request) {
            try {
                return request.getProxy().getClass().getMethod("set" + featureName, returnType);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
