package com.essaid.model.internal.map.impl;

import com.essaid.model.EntityManager;
import com.essaid.model.internal.map.Request;
import com.essaid.model.internal.map.RequestHandler;
import com.essaid.model.internal.map.RequestHandlerFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectRequestHandlerFactory implements RequestHandlerFactory {


    @Override
    public RequestHandler getHandler(Method method, EntityManager entityManager) {
        if (method.isDefault()) return null;
        String methodName = method.getName();
        RequestHandler handler = null;

        if(methodName.equals("toString") && method.getParameterCount()==0){
            handler =  new ToStringRequestHandler();
        } else if(methodName.equals("hashCode") && method.getParameterCount()==0){
            handler =  new HashCodeRequestHandler();
        }else if(methodName.equals("equals") && method.getParameterCount()==1){
            handler =  new EqualsRequestHandler();
        }

        return handler;
    }

    public static class ToStringRequestHandler implements RequestHandler {

        @Override
        public Object handleRequest(Request request) {
            Method _toStringMethod = null;
            try {
                _toStringMethod =  request.getProxy().getClass().getMethod("_toString" );
                return _toStringMethod.invoke(request.getProxy() );
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class HashCodeRequestHandler implements RequestHandler {

        @Override
        public Object handleRequest(Request request) {
            Method _hashCode = null;
            try {
                _hashCode =  request.getProxy().getClass().getMethod("_hashCode" );
                return _hashCode.invoke(request.getProxy() );
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
                _equalsMethod =  request.getProxy().getClass().getMethod("_equals", Object.class );
                return _equalsMethod.invoke(request.getProxy(), request.getArgs()[0] );
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
