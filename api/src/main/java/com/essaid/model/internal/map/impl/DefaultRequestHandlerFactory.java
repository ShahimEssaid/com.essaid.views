package com.essaid.model.internal.map.impl;

import com.essaid.model.internal.map.Request;
import com.essaid.model.internal.map.RequestHandler;
import com.essaid.model.internal.map.RequestHandlerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultRequestHandlerFactory implements RequestHandlerFactory, RequestHandler {

    @Override
    public RequestHandler getHandler(Method method) {
        if (method.isDefault()) return this;
        return  null;
    }

    @Override
    public Object handleRequest(Request request) {
        try {
            return InvocationHandler.invokeDefault(request.getProxy(), request.getMethod(), request.getArgs());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
