package com.essaid.views.proxy.impl.handler.factory;


import com.essaid.views.proxy.impl.handler.ToStringHandler;
import com.essaid.views.proxy.impl.handler.ToStringHandlerDefault;
import com.essaid.views.proxy.internal.Request;
import com.essaid.views.proxy.internal.RequestHandler;
import java.lang.reflect.Method;

public class ToStringHandlerFactory extends AbstractHandlerFactory {

  @Override
  public RequestHandler getHandler(Request request) {

    if (request.getInvokedMethod().getName().equals("toString") &&
        request.getInvokedMethod().getDeclaringClass() == Object.class
    ) {
      Method _toStringMethod = null;
      try {
        _toStringMethod = request.getView().getClass().getMethod("_toString");
        return new ToStringHandler(request, _toStringMethod);
      } catch (NoSuchMethodException e) {
        //ignore
      }
      return new ToStringHandlerDefault(request);
    }

    return null;
  }
}
