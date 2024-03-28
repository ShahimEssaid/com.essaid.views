package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class GetOrCreateRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {
    if (!requestType.equals(RequestType.GET_OR_CREATE) ||
        method.isDefault()) {
      return null;
    }

    Class<?> returnType = method.getReturnType();
    RequestHandler handler = null;

    if (returnType.isPrimitive()) {
      if (returnType == byte.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (byte) 0,
            modelManager);
      } else if (returnType == short.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (short) 0,
            modelManager);
      } else if (returnType == int.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, 0, modelManager);
      } else if (returnType == long.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (long) 0,
            modelManager);
      } else if (returnType == float.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (float) 0,
            modelManager);
      } else if (returnType == double.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (double) 0,
            modelManager);
      } else if (returnType == char.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (char) 0,
            modelManager);
      } else if (returnType == boolean.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, false,
            modelManager);
      }
    } else {
      handler = new GetOrCreateRequestHandler(featureName, method, requestType, null, modelManager);
    }

    return handler;
  }

  public static class GetOrCreateRequestHandler extends AbstractRequestHandler {

    private final WeakHashMap<Class<?>, Method> getters = new WeakHashMap<>();
    private final WeakHashMap<Class<?>, Method> setters = new WeakHashMap<>();


    public GetOrCreateRequestHandler(String featureName, Method method, RequestType requestType,
        Object defaultValue, ModelManager modelManager) {
      super(featureName, method, requestType, defaultValue, modelManager);
    }

    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ViewHandler viewHandler) {
      Object value = viewHandler.getState().getFeatureValue(featureName);
      if (value == null) {
        if (method.getReturnType().isPrimitive()) {
          value = defaultValue;
        } else {
          value = modelManager.create(method.getReturnType());
        }
        viewHandler.getState().setFeatureValue(featureName, value);
      }

      return value;
    }
  }
}
