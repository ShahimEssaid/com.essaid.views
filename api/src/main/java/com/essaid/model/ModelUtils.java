package com.essaid.model;

import com.essaid.model.internal.ModelObjectHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.Optional;

public class ModelUtils {

  public static Optional<ModelObjectHandler> getModelObjectHandler(Objects object) {
    ModelObjectHandler handler = null;
    if (Proxy.isProxyClass(object.getClass())) {
      InvocationHandler invocationHandler = Proxy.getInvocationHandler(object);
      if (invocationHandler instanceof ModelObjectHandler) {
        handler = (ModelObjectHandler) invocationHandler;
      }
    }
    return Optional.ofNullable(handler);
  }

}
