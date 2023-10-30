package com.essaid.model.internal.map.impl;

import com.essaid.model.EntityManager;
import com.essaid.model.internal.impl.AbstractRequestHandler;
import com.essaid.model.internal.map.Request;
import com.essaid.model.internal.map.RequestHandler;
import com.essaid.model.internal.map.RequestHandlerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

public class AddRequestHandlerFactory implements RequestHandlerFactory {


    @Override
    public RequestHandler getHandler(Method method, EntityManager entityManager) {
        if (method.isDefault()) return null;
        RequestHandler handler = null;
        if (method.getName().startsWith("add")) {
            String feature = method.getName().substring(3);
            handler = new AddRequestHandler(method, "add");

        }


        return handler;
    }


    public static class AddRequestHandler extends AbstractRequestHandler {
        private final boolean hasInstance;

        public AddRequestHandler(Method method, String prefix) {
            super(method, prefix);
            this.hasInstance = method.getParameterCount() == 1;
        }

        @Override
        public Object handleRequest(Request request) {
//            try {
//                Method cGetter = getCGetter(request);
//                Type genericReturnType = cGetter.getGenericReturnType();
//
//                List list = (List) getCGetter(request).invoke(request.getProxy(), request.getArgs());
//                if (hasInstance) {
//                    list.add(request.getArgs()[0]);
//                    return request.getProxy();
//                } else {
//
//                }
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                throw new RuntimeException(e);
//            }


            return null;
        }
    }


}
