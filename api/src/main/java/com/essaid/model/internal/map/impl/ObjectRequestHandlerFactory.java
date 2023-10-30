package com.essaid.model.internal.map.impl;

import com.essaid.model.EntityManager;
import com.essaid.model.internal.impl.AbstractRequestHandler;
import com.essaid.model.internal.map.Request;
import com.essaid.model.internal.map.RequestHandler;
import com.essaid.model.internal.map.RequestHandlerFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectRequestHandlerFactory implements RequestHandlerFactory {


    @Override
    public RequestHandler getHandler(Method method, EntityManager entityManager) {
        if (method.isDefault()) return null;
        String methodName = method.getName();
        RequestHandler handler = null;

        if (methodName.equals(RequestHandler.TO_STRING) && method.getParameterCount() == 0) {
            handler = new ToStringRequestHandler(method, RequestHandler.TO_STRING);
        } else if (methodName.equals(RequestHandler.HASH_CODE) && method.getParameterCount() == 0) {
            handler = new HashCodeRequestHandler();
        } else if (methodName.equals(RequestHandler.EQUALS) && method.getParameterCount() == 1) {
            handler = new EqualsRequestHandler();
        }

        return handler;
    }

    public static class ToStringRequestHandler extends AbstractRequestHandler {

        public ToStringRequestHandler(Method handledMethod, String prefix) {
            super(handledMethod, prefix);
        }

        @Override
        public Object handleRequest(Request request) {
            try {
                return getToString(request).invoke(request.getProxy());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static class HashCodeRequestHandler implements RequestHandler {

        @Override
        public Object handleRequest(Request request) {
            Method _hashCode = null;
            try {
                _hashCode = request.getProxy().getClass().getMethod("_hashCode");
                return _hashCode.invoke(request.getProxy());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class EqualsRequestHandler implements RequestHandler {

        @Override
        public Object handleRequest(Request request) {
            Method _equalsMethod = null;
            try {
                _equalsMethod = request.getProxy().getClass().getMethod("_equals", Object.class);
                return _equalsMethod.invoke(request.getProxy(), request.getArgs()[0]);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
