package com.essaid.views.proxy.impl.handler;

import com.essaid.views.proxy.internal.Request;

public abstract class FeatureRequestHandler extends AbstractRequestHandler {

  protected final String featureName;

  public FeatureRequestHandler(Request request) {
    super(request);
    this.featureName = requestType.extractFeatureName(request.getInvokedMethod());
  }


}
