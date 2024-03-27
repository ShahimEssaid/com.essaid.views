package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.impl.map.ModelObjectHandler;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import java.lang.reflect.Method;

public class GetOrDefaultRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {

    if (!requestType.equals(RequestType.GET_OR_DEFAULT) ||
        method.isDefault()) {
      return null;
    }

    RequestHandler handler = null;
    Class<?> returnType = method.getReturnType();

    handler = new GetOrDefaultRequestHandler(featureName, method, requestType, modelManager);

    return handler;
  }

  public static class GetOrDefaultRequestHandler extends AbstractRequestHandler {

    public GetOrDefaultRequestHandler(String featureName, Method method, RequestType requestType, ModelManager modelManager) {
      super(featureName, method, requestType, null, modelManager);
    }

    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ModelObjectHandler objectHandler) {
      Object value = objectHandler.getFeatureValue(featureName);
      return value != null ? value : args[0];
    }
  }
}
