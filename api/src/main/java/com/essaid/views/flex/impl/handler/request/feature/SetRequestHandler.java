package com.essaid.views.flex.impl.handler.request.feature;

import com.essaid.views.flex.ModelUtils;
import com.essaid.views.flex.View;
import com.essaid.views.flex.internal.ViewRequest;

public class SetRequestHandler extends FeatureRequestHandler {

  public SetRequestHandler(ViewRequest request) {
    super(request);
  }

  @Override
  public void handle(ViewRequest request) {
    Object value = request.getInvokedArgs()[0];
    if (value != null) {
      if (ModelUtils.getViewHandler(value) == null){
        value = session.adapt(value, View.class);
      }

    }

    set(featureName, value, request.getViewHandler().getState());
  }
}
