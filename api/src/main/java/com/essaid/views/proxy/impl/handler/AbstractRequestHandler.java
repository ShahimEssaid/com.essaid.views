package com.essaid.views.proxy.impl.handler;

import com.essaid.views.internal.SessionInternal;
import com.essaid.views.internal.State;
import com.essaid.views.internal.ViewInternal;
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

  public static <T> T get(String key, Class<T> neededReturnType, State state) {
    Object value = state.getFeatureValue(key);
    Object adapted = state.getSession().adapt(value, neededReturnType);
    return (T) adapted;

//    if (value == null) {
//      return null;
//    }
//    if (neededReturnType.isAssignableFrom(value.getClass())) {
//      return (T) value;
//    }
//
//    State valueSate = ModelUtils.getState(value);
//
//    if (valueSate != null) {
//      // we have a model object, not just some metadata object.
//
//      // we try to
//      Object externalValue = valueSate.getFeatureValue(StateKeys.EXTERNAL_VALUE);
//      if (externalValue != null && neededReturnType.isAssignableFrom(externalValue.getClass())) {
//        if(neededReturnType.isAssignableFrom(externalValue.getClass())){
//          return (T) externalValue;
//        } else {
//          Object adapted = state.getSession().adapt(value, neededReturnType);
//          valueSate.setFeatureValue(StateKeys.EXTERNAL_VALUE, adapted);
//          return (T) adapted;
//        }
//      }
//    }
//
//    throw new IllegalStateException(
//        "Feature key: " + key + " with value:__ " + value + " __ exists but it can't be "
//            + "converted to requested type: " + neededReturnType);
  }

  public static <T> T set(String key, ViewInternal value, State state) {

    if (value == null) {
      return (T) state.unsetFeatureValue(key);
    }

    return (T) state.setFeatureValue(key, value);
  }
}
