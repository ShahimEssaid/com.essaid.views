package com.essaid.views.flex.impl;

import com.essaid.views.flex.impl.handler.request.RequestType;
import com.essaid.views.flex.impl.handler.request.feature.SetRequestHandler;
import com.essaid.views.flex.internal.RequestHandler;
import com.essaid.views.flex.internal.RequestHandlerFactory;
import com.essaid.views.flex.internal.ViewRequest;

public class SetRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(ViewRequest request) {

    RequestType requestType = RequestType.getRequestType(request);

    if (!RequestType.SET.equals(requestType) ||
        request.getInvokedMethod().isDefault()) {
      return null;
    }

    return new SetRequestHandler(request);
  }


}
