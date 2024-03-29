package com.essaid.model;

import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.Optional;

public class ModelUtils {

  public static Optional<ViewHandler> getViewHandler(Objects object) {
    ViewHandler handler = null;
    if (Proxy.isProxyClass(object.getClass())) {
      InvocationHandler invocationHandler = Proxy.getInvocationHandler(object);
      if (invocationHandler instanceof ViewHandler) {
        handler = (ViewHandler) invocationHandler;
      }
    }
    return Optional.ofNullable(handler);
  }

}
