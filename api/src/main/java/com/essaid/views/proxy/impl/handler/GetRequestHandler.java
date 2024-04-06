package com.essaid.views.proxy.impl.handler;

import com.essaid.views.internal.ViewInternal;
import com.essaid.views.proxy.internal.Request;

public class GetRequestHandler extends FeatureRequestHandler {

  protected Object defaultValue = null;

  public GetRequestHandler(Request request) {
    super(request);
    if (returnType.isPrimitive()) {
      if (returnType == byte.class) {
        defaultValue = (byte) 0;
      } else if (returnType == short.class) {
        defaultValue = (short) 0;
      } else if (returnType == int.class) {
        defaultValue = 0;
      } else if (returnType == long.class) {
        defaultValue = (long) 0;
      } else if (returnType == float.class) {
        defaultValue = (float) 0;
      } else if (returnType == double.class) {
        defaultValue = (double) 0;
      } else if (returnType == char.class) {
        defaultValue = (char) 0;
      } else if (returnType == boolean.class) {
        defaultValue = Boolean.FALSE;
      }
    }
  }

  @Override
  public void handle(Request request) {
    ViewInternal feature = session.getFeature(request.getView().__getState(), featureName, request);
    if(feature == null){
      request.getResponse().setValue(defaultValue);
    } else {
      Object adapted = session.adapt(feature, request.getInvokedMethod().getReturnType());
      request.getResponse().setValue(adapted);
    }
  }
}
