package com.essaid.views.proxy.impl;

import com.essaid.views.View;
import com.essaid.views.proxy.Config;
import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ViewsManagerImpl extends AbstractViewsManager {

  private final Config modelConfig;
  @SuppressWarnings("unchecked")

  private Map<Class<?>, Map<Method, Method>> methodsMap = new HashMap<>();

  public ViewsManagerImpl(Config modelConfig) {
    this.modelConfig = modelConfig;
  }

  @Override
  public Config getConfig() {
    return modelConfig;
  }

  @Override
  public Method getClientMethod(Request request) {
    Method invokedMethod = request.getInvokedMethod();
    Class<? extends View> viewClass = request.getView().getClass();
    Method method = methodsMap.computeIfAbsent(viewClass, c -> new HashMap<>())
        .computeIfAbsent(request.getInvokedMethod(), m -> {
          try {
            return viewClass.getMethod(invokedMethod.getName(), invokedMethod.getParameterTypes());
          } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
          }
        });

    return method;
  }


}
