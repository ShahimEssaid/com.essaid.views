package com.essaid.views.proxy.impl.defaults;

import com.essaid.views.internal.State;
import com.essaid.views.proxy.internal.ViewHandler;
import com.essaid.views.internal.ViewInternal;
import java.lang.reflect.Proxy;

public interface ViewInternalHelper extends ViewInternal {

  @Override
  default State __getState() {
    ViewHandler invocationHandler = (ViewHandler) Proxy.getInvocationHandler(this);
    return invocationHandler.getState();


  }
}
