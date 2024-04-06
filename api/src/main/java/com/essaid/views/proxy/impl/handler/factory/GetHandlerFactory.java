package com.essaid.views.proxy.impl.handler.factory;

import com.essaid.views.proxy.impl.request.RequestType;
import com.essaid.views.proxy.impl.handler.GetRequestHandler;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.proxy.internal.Request;

public class GetHandlerFactory extends AbstractHandlerFactory {


  @Override
  public RequestHandler getHandler(Request request) {

    if (!RequestType.GET.equals(RequestType.getRequestType(request)) || request.getInvokedMethod().isDefault()) {
      return null;
    }
    return new GetRequestHandler(request);
  }
}
