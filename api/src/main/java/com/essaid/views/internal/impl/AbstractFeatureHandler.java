package com.essaid.views.internal.impl;

import com.essaid.views.View;
import com.essaid.views.internal.FeatureHandler;

public abstract class AbstractFeatureHandler implements FeatureHandler {

  private final String name;
  private final View owningView;
  private View targetView;

  protected AbstractFeatureHandler(String name, View owningView) {
    this.name = name;
    this.owningView = owningView;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public View getOwningView() {
    return owningView;
  }

  @Override
  public View getTargetView() {
    return targetView;
  }

  @Override
  public View setTargetView(View view) {
    return targetView = view;
  }

  @Override
  public Object getAnnotation(Object key) {
    return null;
  }

  @Override
  public Object setAnnotation(Object key, Object value) {
    return null;
  }
}
