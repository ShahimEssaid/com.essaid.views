package com.essaid.model.internal;

import java.lang.reflect.Method;

public interface Invocation {

  Object getProxy();

  Method getMethod();

  Object[] getArgs();

  ModelObjectHandler getElementHandler();
}
