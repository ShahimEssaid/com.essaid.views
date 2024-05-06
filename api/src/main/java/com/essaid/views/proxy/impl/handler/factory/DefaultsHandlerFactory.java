package com.essaid.views.proxy.impl.handler.factory;

import com.essaid.views.proxy.impl.handler.DefaultRequestHandler;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.proxy.internal.Request;

public class DefaultsHandlerFactory extends AbstractHandlerFactory {

  @Override
  public RequestHandler getHandler(Request request) {
    if(request.getInvokedMethod().isDefault()){
      return new DefaultRequestHandler(request);
    }
    return null;
  }
}
