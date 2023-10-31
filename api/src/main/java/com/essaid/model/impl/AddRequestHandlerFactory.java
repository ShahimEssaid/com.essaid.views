package com.essaid.model.impl;

import com.essaid.model.EntityManager;
import com.essaid.model.map.Request;
import com.essaid.model.map.RequestHandler;
import com.essaid.model.map.RequestHandlerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class AddRequestHandlerFactory implements RequestHandlerFactory {


    @Override
    public RequestHandler getHandler(Method method, EntityManager entityManager) {
        if (method.isDefault()) return null;
        RequestHandler handler = null;
        if (method.getName().startsWith(RequestHandler.ADD)) {
            String feature = method.getName().substring(RequestHandler.ADD.length());
            handler = new AddRequestHandler(method, RequestHandler.ADD);
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

            CGetterInfo cGetterInfo = getCGetterInfo(request);
            if (!cGetterInfo.isList()) {
                throw new IllegalStateException("Add method for non list property: " + cGetterInfo.getMethod());
            }
            try {

                Object o = request.getElementHandler().getEntityManager().create(cGetterInfo.getListInstanceType());
                List list = (List) cGetterInfo.getMethod().invoke(request.getProxy());
                list.add(o);
                return o;

            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
