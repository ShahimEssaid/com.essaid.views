package com.essaid.views.flex.impl.handler.request.feature.factory;

import com.essaid.views.flex.impl.handler.request.AbstractRequestHandlerFactory;
import com.essaid.views.flex.impl.handler.request.RequestType;
import com.essaid.views.flex.impl.handler.request.feature.GetRequestHandler;
import com.essaid.views.flex.internal.RequestHandler;
import com.essaid.views.flex.internal.ViewRequest;

public class GetRequestHandlerFactory extends AbstractRequestHandlerFactory {


  @Override
  public RequestHandler getHandler(ViewRequest request) {

    if (!RequestType.GET.equals(RequestType.getRequestType(request)) || request.getInvokedMethod().isDefault()) {
      return null;
    }
    return new GetRequestHandler(request);
  }
}
