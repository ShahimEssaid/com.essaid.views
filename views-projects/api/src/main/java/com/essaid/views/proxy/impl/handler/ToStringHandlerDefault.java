package com.essaid.views.proxy.impl.handler;

import com.essaid.views.proxy.internal.Request;

public class ToStringHandlerDefault extends AbstractRequestHandler {

  public ToStringHandlerDefault(Request request) {
    super(request);
  }

  @Override
  public void handle(Request request) {
    String string = request.getInvokedMethod() + "";

    request.getResponse().setValue(
        request.getInvokedMethod().getReturnType().getName() + "@" + System.identityHashCode(
            request.getView()) + "@" + System.identityHashCode(request.getView()._getViewHandler().getValue()));
  }
}
