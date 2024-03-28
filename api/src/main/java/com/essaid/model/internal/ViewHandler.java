package com.essaid.model.internal;

import com.essaid.model.ModelManager;
import java.lang.reflect.InvocationHandler;
import java.util.Map;

public interface ViewHandler extends InvocationHandler {

  State getState();

  Class<?> getViewType();

  ModelManager getModelManager();


}
