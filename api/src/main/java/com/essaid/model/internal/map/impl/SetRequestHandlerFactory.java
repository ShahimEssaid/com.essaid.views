package com.essaid.model.internal.map.impl;

import com.essaid.model.internal.map.RequestHandler;
import com.essaid.model.internal.map.RequestHandlerFactory;

import java.beans.Introspector;
import java.lang.reflect.Method;

public class SetRequestHandlerFactory implements RequestHandlerFactory {


    @Override
    public RequestHandler getHandler(Method method) {
        if (method.isDefault()) return null;
        RequestHandler handler = null;
        if (method.getName()
                  .startsWith("set") && method.getParameterCount() == 1 && method.getReturnType() == void.class) {
            String feature = method.getName().substring(3);
            feature = Introspector.decapitalize(feature);
            handler = new SetRequestHandler(feature, method);
        }
        return handler;
    }
}
