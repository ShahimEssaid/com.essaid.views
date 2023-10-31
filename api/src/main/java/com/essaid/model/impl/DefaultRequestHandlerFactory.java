package com.essaid.model.impl;

import com.essaid.model.EntityManager;
import com.essaid.model.map.Request;
import com.essaid.model.map.RequestHandler;
import com.essaid.model.map.RequestHandlerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultRequestHandlerFactory implements RequestHandlerFactory, RequestHandler {

    @Override
    public RequestHandler getHandler(Method method, EntityManager entityManager) {
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
