package com.essaid.views.proxy.impl.handler;

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
    Object value = get(featureName, returnType, request.getView().__getState());
    if(value == null ){
      value = defaultValue;
    }

    request.getResponse().setValue(value);
  }
}
