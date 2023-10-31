package com.essaid.model.impl;

import com.essaid.model.EntityManager;
import com.essaid.model.map.Request;
import com.essaid.model.map.RequestHandler;
import com.essaid.model.map.RequestHandlerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class AcceptRequestHandlerFactory implements RequestHandlerFactory {

    @Override
    public RequestHandler getHandler(Method method, EntityManager entityManager) {
        if (method.isDefault()) return null;
        RequestHandler handler = null;
        if (method.getName().equals("accept") && method.getParameterCount() ==1) {
            handler = new AcceptRequestHandler();
        }
        return handler;
    }

    @RequiredArgsConstructor
    @Getter
    public static class AcceptRequestHandler implements RequestHandler {

        @Override
        public Object handleRequest(Request request) {
            try {

                Method visitMethod = getVisitMethod(request);
                visitMethod.invoke(request.getArgs()[0], request.getProxy());
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        private Method getVisitMethod(Request request) {
            Object visitor = request.getArgs()[0];
            List<Method> visitMethods = Arrays.stream(visitor.getClass().getMethods())
                                              .filter(m -> m.getName().equals("visit") && m.getParameterCount() == 1)
                                              .toList();

            Class<?> visitedType = request.getElementHandler().getElementType();

            Method visitorMethod = null;
            Class<?> visitorMethodArgType = Object.class;
            for (Method method : visitMethods) {
                Class<?> methodArgType = method.getParameterTypes()[0];
                if (methodArgType.isAssignableFrom(visitedType) // we have a candidate
                        && visitorMethodArgType.isAssignableFrom(methodArgType)) {
                    visitorMethodArgType = methodArgType;
                    visitorMethod = method;
                }
            }
            return visitorMethod;
        }
    }
}
