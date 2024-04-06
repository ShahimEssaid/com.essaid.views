package com.essaid.views.proxy.impl.handler;

import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.InvocationHandler;

public class DefaultRequestHandler extends AbstractRequestHandler {

  public DefaultRequestHandler(Request request) {
    super(request);
  }

  @Override
  public void handle(Request request) {
    try {
      Object o = InvocationHandler.invokeDefault(request.getView(), request.getInvokedMethod(),
          request.getInvokedArgs());
      request.getResponse().setValue(o);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
