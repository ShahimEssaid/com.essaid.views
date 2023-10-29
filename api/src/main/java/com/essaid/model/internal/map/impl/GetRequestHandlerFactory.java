package com.essaid.model.internal.map.impl;

import com.essaid.model.internal.map.Request;
import com.essaid.model.internal.map.RequestHandler;
import com.essaid.model.internal.map.RequestHandlerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.beans.Introspector;
import java.lang.reflect.Method;

public class GetRequestHandlerFactory implements RequestHandlerFactory {


    @Override
    public RequestHandler getHandler(Method method) {
        if (method.isDefault()) return null;
        RequestHandler handler = null;
        Class<?> returnType = method.getReturnType();
        if (method.getName().startsWith("get") && method.getParameterCount() == 0 && returnType != Void.class) {
            String feature = method.getName().substring(3);

            if (returnType.isPrimitive()) {
                if (returnType == byte.class) {
                    handler = new GetRequestHandler(feature, method, (byte) 0);
                } else if (returnType == short.class) {
                    handler = new GetRequestHandler(feature, method, (short) 0);
                } else if (returnType == int.class) {
                    handler = new GetRequestHandler(feature, method, 0);
                } else if (returnType == long.class) {
                    handler = new GetRequestHandler(feature, method, (long) 0);
                } else if (returnType == float.class) {
                    handler = new GetRequestHandler(feature, method, (float) 0);
                } else if (returnType == double.class) {
                    handler = new GetRequestHandler(feature, method, (double) 0);
                } else if (returnType == char.class) {
                    handler = new GetRequestHandler(feature, method, (char) 0);
                } else if (returnType == boolean.class) {
                    handler = new GetRequestHandler(feature, method, false);
                }
            } else {
                feature = Introspector.decapitalize(feature);
                handler = new GetRequestHandler(feature, method, null);
            }

        }
        return handler;
    }

    @RequiredArgsConstructor
    @Getter
    public static class GetRequestHandler implements RequestHandler {
        private final String featureName;
        private final Method method;
        private final Object defaultValue;

        @Override
        public Object handleRequest(Request request) {
            Object value = request.getElementHandler().getFeatureValue(featureName);
            if (value == null && defaultValue != null) {
                return defaultValue;
            }
            return value;
        }
    }
}
