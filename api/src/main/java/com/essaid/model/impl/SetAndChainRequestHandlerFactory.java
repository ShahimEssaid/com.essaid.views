package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.Method;

public class SetAndChainRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {

    if (!requestType.equals(RequestType.SET_AND_CHAIN) ||
        method.isDefault()) {
      return null;
    }

    RequestHandler handler = new SetAndChainRequestHandler(featureName, method, requestType,
        modelManager);

    return handler;
  }

  public static class SetAndChainRequestHandler extends AbstractRequestHandler {

    public SetAndChainRequestHandler(String featureName, Method method, RequestType requestType,
        ModelManager modelManager) {
      super(featureName, method, requestType, null, modelManager);
    }

    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ViewHandler viewHandler) {
      Object newValue = args[0];
      if (newValue == null) {
        viewHandler.getState().unsetFeatureValue(featureName);
      } else {
        viewHandler.getState().setFeatureValue(featureName, newValue);
      }
      return proxy;
    }
  }

}
