package com.essaid.model.impl;

import com.essaid.model.EntityManager;
import com.essaid.model.map.Request;
import com.essaid.model.map.RequestHandler;
import com.essaid.model.map.RequestHandlerFactory;
import java.beans.Introspector;
import java.lang.reflect.Method;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class GetOrSetRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(Method method, EntityManager entityManager) {
    if (method.isDefault()) {
      return null;
    }
    RequestHandler handler = null;
    Class<?> returnType = method.getReturnType();
    if (method.getName().startsWith("getOrSet") && method.getParameterCount() == 1
        && returnType != Void.class) {

      String featureName = method.getName().substring(8);
      featureName = Introspector.decapitalize(featureName);

      if (returnType.isPrimitive()) {
        if (returnType == byte.class) {
          handler = new GetOrSetRequestHandler(featureName, method, (byte) 0);
        } else if (returnType == short.class) {
          handler = new GetOrSetRequestHandler(featureName, method, (short) 0);
        } else if (returnType == int.class) {
          handler = new GetOrSetRequestHandler(featureName, method, 0);
        } else if (returnType == long.class) {
          handler = new GetOrSetRequestHandler(featureName, method, (long) 0);
        } else if (returnType == float.class) {
          handler = new GetOrSetRequestHandler(featureName, method, (float) 0);
        } else if (returnType == double.class) {
          handler = new GetOrSetRequestHandler(featureName, method, (double) 0);
        } else if (returnType == char.class) {
          handler = new GetOrSetRequestHandler(featureName, method, (char) 0);
        } else if (returnType == boolean.class) {
          handler = new GetOrSetRequestHandler(featureName, method, false);
        }
      } else {
        handler = new GetOrSetRequestHandler(featureName, method, null);
      }

    }
    return handler;
  }

  @RequiredArgsConstructor
  @Getter
  public static class GetOrSetRequestHandler implements RequestHandler {

    private final String featureName;
    private final Method method;
    private final Object defaultValue;

    @Override
    public Object handleRequest(Request request) {
      Object value = request.getElementHandler().getFeatureValue(featureName);
      if (value == null) {
        value = request.getArgs()[0];
        if (value != null){
          request.getElementHandler().setFeatureValue(featureName, value);
        }
      }
      if (value == null) {
        return defaultValue;
      }
      return value;
    }
  }
}
