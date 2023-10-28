package com.essaid.model.internal.map.impl;

import com.essaid.model.internal.map.RequestHandler;
import com.essaid.model.internal.map.RequestHandlerFactory;

import java.beans.Introspector;
import java.lang.reflect.Method;

public class GetRequestHandlerFactory implements RequestHandlerFactory {


    @Override
    public RequestHandler getHandler(Method method) {
        if (method.isDefault()) return null;
        RequestHandler handler = null;
        if (method.getName().startsWith("get")) {
            String feature = method.getName().substring(3);
            feature = Introspector.decapitalize(feature);
            handler = new GetRequestHandler(feature, method);
        }
        return handler;
    }
}
