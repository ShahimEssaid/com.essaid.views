package com.essaid.views.proxy.impl.handler;

import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ToStringHandler extends AbstractRequestHandler {

  private final Method toStringMethod;

  public ToStringHandler(Request request, Method toStringMethod) {
    super(request);
    this.toStringMethod = toStringMethod;
  }

  @Override
  public void handle(Request request) {
    try {
      request.getResponse()
          .setValue(InvocationHandler.invokeDefault(request.getView(), toStringMethod));
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
