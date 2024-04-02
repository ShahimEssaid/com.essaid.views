package com.essaid.views.flex.impl;

import com.essaid.views.flex.impl.handler.request.RequestType;
import com.essaid.views.flex.FlexModel;
import com.essaid.views.flex.internal.FlexModelInternal;
import com.essaid.views.flex.internal.RequestHandler;
import com.essaid.views.flex.internal.RequestHandlerFactory;
import com.essaid.views.flex.internal.SessionInternal;
import com.essaid.views.flex.internal.ViewHandler;
import com.essaid.views.flex.internal.ViewRequest;
import java.lang.reflect.Method;

public class GetOrDefaultRequestHandlerFactory implements RequestHandlerFactory {


//  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, SessionInternal session) {

    if (!requestType.equals(RequestType.GET_OR_DEFAULT) ||
        method.isDefault()) {
      return null;
    }

    RequestHandler handler = null;
    Class<?> returnType = method.getReturnType();

    handler = new GetOrDefaultRequestHandler(featureName, method, requestType, session);

    return handler;
  }

//  @Override
  public RequestHandler getHandler(Class<?> viewType, Method invokedMethod, RequestType requestType,
      FlexModelInternal manager) {
    return null;
  }

  @Override
  public RequestHandler getHandler(ViewRequest request) {
    return null;
  }

  public static class GetOrDefaultRequestHandler extends AbstractRequestHandler {

    public GetOrDefaultRequestHandler(String featureName, Method method, RequestType requestType,
        SessionInternal session) {
      super(featureName, method, requestType, null, session);
    }

//    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ViewHandler viewHandler) {
      Object value = viewHandler.getState().getFeatureValue(featureName);
      return value != null ? value : args[0];
    }

    @Override
    public void handle(ViewRequest request) {

    }
  }
}
