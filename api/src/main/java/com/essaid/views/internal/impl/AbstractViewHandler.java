package com.essaid.views.internal.impl;

import com.essaid.views.value.Value;
import com.essaid.views.internal.ViewHandler;

public abstract  class AbstractViewHandler implements ViewHandler {

  private final Class<?> viewType;
  private final Class<?>[] interfaces;
  private final Value value;

  protected AbstractViewHandler(Class<?> viewType, Class<?>[] interfaces, Value value) {
    this.viewType = viewType;
    this.interfaces = interfaces;
    this.value = value;
  }

  @Override
  public Class<?> getViewType() {
    return viewType;
  }

  @Override
  public Class<?>[] getInterfaces() {
    return interfaces;
  }

  @Override
  public Value getValue() {
    return value;
  }
}
