package com.essaid.views.proxy.internal;

import com.essaid.views.internal.State;
import java.lang.reflect.InvocationHandler;

public interface ViewHandler extends InvocationHandler {

  Class<?> getViewType();
  Class<?>[] getInterfaces();

  State getState();

}
