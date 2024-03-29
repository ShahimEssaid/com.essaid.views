package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.ObjectState;
import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.Method;

public class DefaultViewHandler implements ViewHandler {

  private final ObjectState state;
  private final ModelManager modelManager;

  public DefaultViewHandler(ModelManager modelManager, ObjectState state) {
    this.modelManager = modelManager;
    this.state = state;
  }

  @Override
  public ModelManager getModelManager() {
    return modelManager;
  }

  @Override
  public ObjectState getState() {
    return state;
  }


  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return modelManager.internal().handle(proxy, method, args, this);
  }
}
