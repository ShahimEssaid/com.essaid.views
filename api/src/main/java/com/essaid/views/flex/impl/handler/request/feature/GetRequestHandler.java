package com.essaid.views.flex.impl.handler.request.feature;

import com.essaid.views.flex.internal.ViewHandler;
import com.essaid.views.flex.internal.ViewRequest;

public class GetRequestHandler extends FeatureRequestHandler {

  protected Object defaultValue = null;

  public GetRequestHandler(ViewRequest request) {
    super(request);
    Class<?> returnType = request.getClientMethod().getReturnType();
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
  public void handle(ViewRequest request) {
    ViewHandler viewHandler = request.getViewHandler();
    Object value = get(featureName, request.getClientMethod().getReturnType(), viewHandler.getState(),
        viewHandler.getSession());
    request.getResponse().setValue(value);
  }
}
