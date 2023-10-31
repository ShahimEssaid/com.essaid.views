package com.essaid.model.impl;

import com.essaid.model.EntityManager;
import com.essaid.model.map.Request;
import com.essaid.model.map.RequestHandler;
import com.essaid.model.map.RequestHandlerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.beans.Introspector;
import java.lang.reflect.Method;

public class SetRequestHandlerFactory implements RequestHandlerFactory {


    @Override
    public RequestHandler getHandler(Method method, EntityManager entityManager) {
        if (method.isDefault()) return null;
        RequestHandler handler = null;
        if (method.getName()
                  .startsWith("set") && method.getParameterCount() == 1 && method.getReturnType() == void.class) {
            String feature = method.getName().substring(3);
            feature = Introspector.decapitalize(feature);
            if (feature.equals("_id_")) {
                handler = new Set_id_RequestHandler(feature);
            } else {
                handler = new SetRequestHandler(feature);
            }

        }
        return handler;
    }

    @RequiredArgsConstructor
    @Getter
    public static class SetRequestHandler implements RequestHandler {
        private final String featureName;

        @Override
        public Object handleRequest(Request request) {
            Object value = request.getArgs()[0];
            if (value == null) {
                request.getElementHandler().unsetFeatureValue(featureName);
            } else {
                request.getElementHandler().setFeatureValue(featureName, value);
            }
            return null;
        }
    }

    @RequiredArgsConstructor
    @Getter
    public static class Set_id_RequestHandler implements RequestHandler {
        private final String featureName;

        @Override
        public Object handleRequest(Request request) {
            Object currentId = request.getElementHandler().getFeatureValue("_id_");
            if (currentId != null) {
                throw new IllegalStateException("Object already has _id_:" + currentId + " for object:" + request.getProxy());
            }
            Object value = request.getArgs()[0];
            if (value == null) {
                throw new IllegalStateException("Can't set object's _id_ to null for " + "object:" + request.getProxy());
            }
            request.getElementHandler().setFeatureValue(featureName, value);
            return null;
        }
    }
}
