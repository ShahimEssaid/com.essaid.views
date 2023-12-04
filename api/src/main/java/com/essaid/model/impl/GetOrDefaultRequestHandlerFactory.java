package com.essaid.model.impl;

import com.essaid.model.EntityManager;
import com.essaid.model.map.Request;
import com.essaid.model.map.RequestHandler;
import com.essaid.model.map.RequestHandlerFactory;
import java.beans.Introspector;
import java.lang.reflect.Method;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class GetOrDefaultRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(Method method, EntityManager entityManager) {
    if (method.isDefault()) {
      return null;
    }
    RequestHandler handler = null;
    Class<?> returnType = method.getReturnType();
    if (method.getName().startsWith("getOrDefault") && method.getParameterCount() == 1
        && returnType != Void.class) {

      String featureName = method.getName().substring(12);
      featureName = Introspector.decapitalize(featureName);

      if (returnType.isPrimitive()) {
        if (returnType == byte.class) {
          handler = new GetOrDefaultRequestHandler(featureName, method, (byte) 0);
        } else if (returnType == short.class) {
          handler = new GetOrDefaultRequestHandler(featureName, method, (short) 0);
        } else if (returnType == int.class) {
          handler = new GetOrDefaultRequestHandler(featureName, method, 0);
        } else if (returnType == long.class) {
          handler = new GetOrDefaultRequestHandler(featureName, method, (long) 0);
        } else if (returnType == float.class) {
          handler = new GetOrDefaultRequestHandler(featureName, method, (float) 0);
        } else if (returnType == double.class) {
          handler = new GetOrDefaultRequestHandler(featureName, method, (double) 0);
        } else if (returnType == char.class) {
          handler = new GetOrDefaultRequestHandler(featureName, method, (char) 0);
        } else if (returnType == boolean.class) {
          handler = new GetOrDefaultRequestHandler(featureName, method, false);
        }
      } else {
        handler = new GetOrDefaultRequestHandler(featureName, method, null);
      }

    }
    return handler;
  }

  @RequiredArgsConstructor
  @Getter
  public static class GetOrDefaultRequestHandler implements RequestHandler {

    private final String featureName;
    private final Method method;
    private final Object defaultValue;

    @Override
    public Object handleRequest(Request request) {
      Object value = request.getElementHandler().getFeatureValue(featureName);
      if (value == null) {
        value = request.getArgs()[0];
      }
      if (value == null) {
        return defaultValue;
      }
      return value;
    }
  }
}
