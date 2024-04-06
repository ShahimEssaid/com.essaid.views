package com.essaid.views.proxy.impl.handler;

import com.essaid.views.internal.SessionInternal;
import com.essaid.views.proxy.impl.request.RequestType;
import com.essaid.views.proxy.internal.Request;
import com.essaid.views.proxy.internal.RequestHandler;

public abstract class AbstractRequestHandler implements RequestHandler {

  protected final RequestType requestType;
  protected final Class<?> returnType;
  protected final SessionInternal session;

  public AbstractRequestHandler(Request request) {
    this.requestType = RequestType.getRequestType(request);
    this.returnType = request.getInvokedMethod().getReturnType();
    this.session = request.getView().__getState().getSession();
  }
}
