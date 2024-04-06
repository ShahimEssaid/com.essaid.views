package com.essaid.views.proxy.impl.handler.factory;

import com.essaid.views.proxy.impl.request.RequestType;
import com.essaid.views.proxy.impl.handler.SetRequestHandler;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.proxy.internal.Request;

public class SetHandlerFactory extends AbstractHandlerFactory {


  @Override
  public RequestHandler getHandler(Request request) {
    if (!RequestType.SET.equals(RequestType.getRequestType(request)) || request.getInvokedMethod().isDefault()) {
      return null;
    }
    return new SetRequestHandler(request);
  }
}
