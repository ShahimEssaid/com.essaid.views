package com.essaid.views.proxy.impl.handler;

import com.essaid.views.proxy.internal.Request;

public class SetRequestHandler extends FeatureRequestHandler {

  private final Class<?> paramType;

  public SetRequestHandler(Request request) {
    super(request);
    this.paramType = request.getInvokedMethod().getParameterTypes()[0];
  }

  @Override
  public void handle(Request request) {
    Object setValue = request.getInvokedArgs()[0];

    session.setFeature(request.getView().__viewHandler().getValue(), featureName, setValue, request);


//    if (value != null) {
//      State state = ModelUtils.getState(value);
//      if (state == null) {
//        value = session.adapt(value, View.class);
//      } else {
//        if (state.getSession() != session) {
//          throw new IllegalArgumentException("Can't set cross-sessions. Set request: " + request);
//        }
//      }
//    }

//    set(featureName, value, request.getState());
  }
}
