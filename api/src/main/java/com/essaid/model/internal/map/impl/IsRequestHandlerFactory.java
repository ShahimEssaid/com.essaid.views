package com.essaid.model.internal.map.impl;

import com.essaid.model.internal.map.Request;
import com.essaid.model.internal.map.RequestHandler;
import com.essaid.model.internal.map.RequestHandlerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.beans.Introspector;
import java.lang.reflect.Method;

public class IsRequestHandlerFactory implements RequestHandlerFactory {


    @Override
    public RequestHandler getHandler(Method method) {
        if (method.isDefault()) return null;
        RequestHandler handler = null;
        if (method.getName().startsWith("is") && method.getParameterCount() == 0 && method.getReturnType() == boolean.class) {
            String feature = method.getName().substring(3);
            feature = Introspector.decapitalize(feature);
            handler = new IsRequestHandler(feature, method);
        }
        return handler;
    }

    @RequiredArgsConstructor
    @Getter
    public static class IsRequestHandler implements RequestHandler {
        private final String featureName;
        private final Method method;

        @Override
        public Object handleRequest(Request request) {
            Object value = request.getElementHandler().getFeatureValue(featureName);
            if(value == null) return false;
            return value;
        }
    }
}
