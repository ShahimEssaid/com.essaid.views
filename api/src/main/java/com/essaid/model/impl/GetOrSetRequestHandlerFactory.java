package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.Method;


public class GetOrSetRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {
    if (!requestType.equals(RequestType.GET_OR_SET) ||
        method.isDefault()) {
      return null;
    }

    RequestHandler handler = null;
    Class<?> returnType = method.getReturnType();

    handler = new GetOrSetRequestHandler(featureName, method, requestType, modelManager);

    return handler;
  }


  public static class GetOrSetRequestHandler extends AbstractRequestHandler {

    public GetOrSetRequestHandler(String featureName, Method method,
        RequestType requestType, ModelManager modelManager) {
      super(featureName, method, requestType, null, modelManager);
    }

    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ViewHandler viewHandler) {
      Object value = viewHandler.getState().getFeatureValue(featureName);
      if (value == null) {
        value = args[0];
        if (value != null) {
          viewHandler.getState().setFeatureValue(featureName, value);
        }
      }
      return value;
    }
  }
}
