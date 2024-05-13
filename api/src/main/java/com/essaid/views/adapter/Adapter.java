package com.essaid.views.adapter;

import com.essaid.views.adapter.impl.AdaptRequestImpl;

public interface Adapter {

  /** Adapt based on request
   *
   * @param request
   * @return
   */
  boolean adapt(AdaptRequest request);
}
