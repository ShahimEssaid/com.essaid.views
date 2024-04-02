package com.essaid.views.flex.impl.handler.request.feature;

import com.essaid.views.flex.impl.handler.request.AbstractRequestHandler;
import com.essaid.views.flex.internal.ViewRequest;

public abstract class FeatureRequestHandler extends AbstractRequestHandler {

  protected final String featureName;

  public FeatureRequestHandler(ViewRequest request) {
    super(request);
    this.featureName = requestType.extractFeatureName(request.getInvokedMethod());
  }


}
