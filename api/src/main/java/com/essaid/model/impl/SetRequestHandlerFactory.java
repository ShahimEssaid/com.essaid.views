package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.impl.map.ModelObjectHandler;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import java.lang.reflect.Method;

public class SetRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {
    if (!requestType.equals(RequestType.SET) ||
        method.isDefault()) {
      return null;
    }
    return new SetRequestHandler(featureName, method, requestType, modelManager);
  }

  public static class SetRequestHandler extends AbstractRequestHandler {

    public SetRequestHandler(String featureName, Method method, RequestType requestType,
        ModelManager modelManager) {
      super(featureName, method, requestType, null, modelManager);
    }

    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ModelObjectHandler objectHandler) {
      Object value = args[0];
      if (value == null) {
        return objectHandler.unsetFeatureValue(featureName);
      } else {
        return objectHandler.setFeatureValue(featureName, value);
      }
    }
  }

}
