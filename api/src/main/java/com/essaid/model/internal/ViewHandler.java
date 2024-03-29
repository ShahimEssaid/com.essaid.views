package com.essaid.model.internal;

import com.essaid.model.ModelManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public interface ViewHandler extends InvocationHandler {

  ModelManager getModelManager();
  ObjectState getState();

}
