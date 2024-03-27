package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.impl.map.ModelObjectHandler;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import java.lang.reflect.Method;

public class SetAndGetRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {
    if (!requestType.equals(RequestType.SET_AND_GET) ||
        method.isDefault()) {
      return null;
    }
    return new SetAndGetRequestHandler(featureName, method, requestType, modelManager);
  }

  public static class SetAndGetRequestHandler extends AbstractRequestHandler {

    public SetAndGetRequestHandler(String featureName, Method method, RequestType requestType,
        ModelManager modelManager) {
      super(featureName, method, requestType, null, modelManager);
    }

    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ModelObjectHandler objectHandler) {
      Object oldValue = objectHandler.getFeatureValue(featureName);
      Object newValue = args[0];

      if (newValue == null) {
        objectHandler.unsetFeatureValue(featureName);
      } else {
        objectHandler.setFeatureValue(featureName, newValue);
      }
      return oldValue;
    }
  }

}
