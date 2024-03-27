package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.impl.map.ModelObjectHandler;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
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
        ModelObjectHandler objectHandler) {
      Object newValue = args[0];
      if (newValue == null) {
        objectHandler.unsetFeatureValue(featureName);
      } else {
        objectHandler.setFeatureValue(featureName, newValue);
      }
      return proxy;
    }
  }

}
