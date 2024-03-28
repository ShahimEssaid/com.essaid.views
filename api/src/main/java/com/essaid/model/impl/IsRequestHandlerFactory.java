package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.Method;

public class IsRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {
    if (!requestType.equals(RequestType.IS) ||
        method.isDefault()) {
      return null;
    }
    RequestHandler handler = new IsRequestHandler(featureName, method, requestType, false,
        modelManager);

    return handler;
  }

  public static class IsRequestHandler extends AbstractRequestHandler {

    public IsRequestHandler(String featureName, Method method, RequestType requestType,
        Object defaultValue, ModelManager modelManager) {
      super(featureName, method, requestType, defaultValue, modelManager);
    }

    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ViewHandler viewHandler) {
      Object featureValue = viewHandler.getState().getFeatureValue(featureName);
      if (featureValue == null) {
        return false;
      }
      return featureValue;
    }

  }
}
