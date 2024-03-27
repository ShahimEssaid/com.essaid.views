package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import java.lang.reflect.Method;

public class ObjectRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {
      if (method.isDefault()) {
          return null;
      }
    String methodName = method.getName();
    RequestHandler handler = null;

//        if (methodName.equals(RequestHandler.TO_STRING) && method.getParameterCount() == 0) {
//            handler = new ToStringRequestHandler(method, RequestHandler.TO_STRING);
//        } else if (methodName.equals(RequestHandler.HASH_CODE) && method.getParameterCount() == 0) {
//            handler = new HashCodeRequestHandler();
//        } else if (methodName.equals(RequestHandler.EQUALS) && method.getParameterCount() == 1) {
//            handler = new EqualsRequestHandler();
//        }

    return handler;
  }

//    public static class ToStringRequestHandler extends AbstractRequestHandler {
//
//        public ToStringRequestHandler(Method handledMethod, String prefix) {
//            super(handledMethod, prefix);
//        }
//
//        @Override
//        public Object handleRequest(Invocation invocation) {
//            try {
//                return getToString(invocation).invoke(invocation.getProxy());
//            } catch (Throwable e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }
//
//    public static class HashCodeRequestHandler implements RequestHandler {
//
//        @Override
//        public Object handleRequest(Invocation invocation) {
//            Method _hashCode = null;
//            try {
//                _hashCode = invocation.getProxy().getClass().getMethod("_hashCode");
//                return _hashCode.invoke(invocation.getProxy());
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public static class EqualsRequestHandler implements RequestHandler {
//
//        @Override
//        public Object handleRequest(Invocation invocation) {
//            Method _equalsMethod = null;
//            try {
//                _equalsMethod = invocation.getProxy().getClass().getMethod("_equals", Object.class);
//                return _equalsMethod.invoke(invocation.getProxy(), invocation.getArgs()[0]);
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

}
