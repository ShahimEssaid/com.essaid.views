package com.essaid.views.adapter.impl;

import com.essaid.views.adapter.AdaptRequest;
import com.essaid.views.adapter.Adapter;

public class IsInstanceOfAdapter implements Adapter {

  @Override
  public boolean adapt(AdaptRequest request) {
    if (!request.getAdaptedType().isAssignableFrom(request.getAdaptee().getClass())) {
      return false;
    }
    request.setAdapted(request.getAdaptee());
    return true;
  }
}
