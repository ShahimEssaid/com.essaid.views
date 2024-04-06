package com.essaid.views.proxy.impl;

import com.essaid.views.proxy.internal.Feature;
import com.essaid.views.internal.State;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FeatureImpl implements Feature {

  private final String name;
  private final State state;

}
