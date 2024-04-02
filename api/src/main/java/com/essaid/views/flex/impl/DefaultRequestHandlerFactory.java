package com.essaid.views.flex.impl;

import com.essaid.views.flex.impl.handler.request.RequestType;
import com.essaid.views.flex.FlexModel;
import com.essaid.views.flex.internal.FlexModelInternal;
import com.essaid.views.flex.internal.RequestHandler;
import com.essaid.views.flex.internal.RequestHandlerFactory;
import com.essaid.views.flex.internal.ViewHandler;
import com.essaid.views.flex.internal.ViewRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultRequestHandlerFactory implements RequestHandlerFactory, RequestHandler {

//  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, FlexModel flexModel) {
    if (method.isDefault()) {
      return this;
    }
    return null;
  }

//  @Override
  public Object handle(Object proxy, Method method, Object[] args,
      ViewHandler objectHandler) {

    try {
      return InvocationHandler.invokeDefault(proxy, method, args);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

//  @Override
  public RequestHandler getHandler(Class<?> viewType, Method invokedMethod, RequestType requestType,
      FlexModelInternal manager) {
    return null;
  }

  @Override
  public void handle(ViewRequest request) {

  }

  @Override
  public RequestHandler getHandler(ViewRequest request) {
    return null;
  }
}
