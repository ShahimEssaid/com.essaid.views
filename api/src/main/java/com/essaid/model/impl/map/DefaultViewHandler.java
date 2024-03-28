package com.essaid.model.impl.map;

import com.essaid.model.ModelManager;
import com.essaid.model.impl.DefaultMapTransformer;
import com.essaid.model.internal.State;
import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class DefaultViewHandler implements
    ViewHandler {

  private final Class<?> viewType;
  private final State state;
  private final Class<?>[] viewInterfaces;
  private final ModelManager modelManager;

  public DefaultViewHandler(Class<?> viewType, State state, ModelManager modelManager,
      Class<?>... viewInterfaces) {
    this.viewType = viewType;
    this.state = state;
    this.modelManager = modelManager;
    this.viewInterfaces = viewInterfaces;
  }


  @Override
  public State getState() {
    return state;
  }

  @Override
  public Class<?> getViewType() {
    return viewType;
  }


  @Override
  public ModelManager getModelManager() {
    return modelManager;
  }


  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return modelManager.internal().handle(proxy, method, args, this);
  }


}
