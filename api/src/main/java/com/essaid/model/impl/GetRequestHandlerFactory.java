package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.Method;

public class GetRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {
    if (!requestType.equals(RequestType.GET) ||
        method.isDefault()) {
      return null;
    }

    Class<?> returnType = method.getReturnType();
    RequestHandler handler = null;

    if (returnType.isPrimitive()) {
      if (returnType == byte.class) {
        handler = new GetRequestHandler(featureName, method, requestType, (byte) 0, modelManager);
      } else if (returnType == short.class) {
        handler = new GetRequestHandler(featureName, method, requestType, (short) 0, modelManager);
      } else if (returnType == int.class) {
        handler = new GetRequestHandler(featureName, method, requestType, 0, modelManager);
      } else if (returnType == long.class) {
        handler = new GetRequestHandler(featureName, method, requestType, (long) 0, modelManager);
      } else if (returnType == float.class) {
        handler = new GetRequestHandler(featureName, method, requestType, (float) 0, modelManager);
      } else if (returnType == double.class) {
        handler = new GetRequestHandler(featureName, method, requestType, (double) 0, modelManager);
      } else if (returnType == char.class) {
        handler = new GetRequestHandler(featureName, method, requestType, (char) 0, modelManager);
      } else if (returnType == boolean.class) {
        handler = new GetRequestHandler(featureName, method, requestType, false, modelManager);
      }
    } else {
      handler = new GetRequestHandler(featureName, method, requestType, null, modelManager);
    }
    return handler;
  }


  public static class GetRequestHandler extends AbstractRequestHandler {

    public GetRequestHandler(String featureName, Method method, RequestType requestType,
        Object defaultValue, ModelManager modelManager) {
      super(featureName, method, requestType, defaultValue, modelManager);
    }

    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ViewHandler viewHandler) {
      Object featureValue = viewHandler.getState().getFeatureValue(featureName);
      if (featureValue == null) {
        return defaultValue;
      }
      return featureValue;
    }
  }
}
