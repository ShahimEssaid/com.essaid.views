package com.essaid.views.flex.impl;

import com.essaid.views.flex.impl.handler.request.RequestType;
import com.essaid.views.flex.FlexModel;
import com.essaid.views.flex.internal.RequestHandler;
import com.essaid.views.flex.internal.RequestHandlerFactory;
import com.essaid.views.flex.internal.SessionInternal;
import com.essaid.views.flex.internal.ViewRequest;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class GetOrCreateRequestHandlerFactory implements RequestHandlerFactory {


//  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, SessionInternal session) {
    if (!requestType.equals(RequestType.GET_OR_CREATE) ||
        method.isDefault()) {
      return null;
    }

    Class<?> returnType = method.getReturnType();
    RequestHandler handler = null;

    if (returnType.isPrimitive()) {
      if (returnType == byte.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (byte) 0,
            session);
      } else if (returnType == short.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (short) 0,
            session);
      } else if (returnType == int.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, 0, session);
      } else if (returnType == long.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (long) 0,
            session);
      } else if (returnType == float.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (float) 0,
            session);
      } else if (returnType == double.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (double) 0,
            session);
      } else if (returnType == char.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, (char) 0,
            session);
      } else if (returnType == boolean.class) {
        handler = new GetOrCreateRequestHandler(featureName, method, requestType, false,
            session);
      }
    } else {
      handler = new GetOrCreateRequestHandler(featureName, method, requestType, null, session);
    }

    return handler;
  }


  @Override
  public RequestHandler getHandler(ViewRequest request) {
    return null;
  }

  public static class GetOrCreateRequestHandler extends AbstractRequestHandler {

    private final WeakHashMap<Class<?>, Method> getters = new WeakHashMap<>();
    private final WeakHashMap<Class<?>, Method> setters = new WeakHashMap<>();


    public GetOrCreateRequestHandler(String featureName, Method method, RequestType requestType,
        Object defaultValue, SessionInternal session) {
      super(featureName, method, requestType, defaultValue, session);
    }

//    @Override
//    public Object handle(Object proxy, Method method, Object[] args,
//        ViewHandler viewHandler) {
//      Object value = viewHandler.getState().getFeatureValue(featureName);
//      if (value == null) {
//        if (method.getReturnType().isPrimitive()) {
//          value = defaultValue;
//        } else {
//          value = manager.create(method.getReturnType());
//        }
//        viewHandler.getState().setFeatureValue(featureName, (InternalView) value);
//      }
//
//      return value;
//    }

    @Override
    public void handle(ViewRequest request) {

    }
  }
}
