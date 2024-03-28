package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultRequestHandlerFactory implements RequestHandlerFactory, RequestHandler {

  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {
    if (method.isDefault()) {
      return this;
    }
    return null;
  }

  @Override
  public Object handle(Object proxy, Method method, Object[] args,
      ViewHandler objectHandler) {

    try {
      return InvocationHandler.invokeDefault(proxy, method, args);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
