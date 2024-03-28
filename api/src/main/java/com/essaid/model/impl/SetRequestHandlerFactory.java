package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import com.essaid.model.internal.ViewHandler;
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
        ViewHandler viewHandler) {
      Object value = args[0];
      if (value == null) {
        return viewHandler.getState().unsetFeatureValue(featureName);
      } else {
        return viewHandler.getState().setFeatureValue(featureName, value);
      }
    }
  }

}
