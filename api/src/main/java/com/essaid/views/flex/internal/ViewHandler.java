package com.essaid.views.flex.internal;

import com.essaid.views.flex.Session;
import java.lang.reflect.InvocationHandler;

public interface ViewHandler extends InvocationHandler {

  SessionInternal getSession();
  ViewState getState();

}
