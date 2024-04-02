package com.essaid.views.flex.impl;

import com.essaid.views.flex.Config;
import com.essaid.views.flex.ModelUtils;
import com.essaid.views.flex.Session;
import com.essaid.views.flex.impl.AbstractFlexModel;
import com.essaid.views.flex.impl.DefaultViewHandler;
import com.essaid.views.flex.impl.ImplUtils;
import com.essaid.views.flex.impl.DefaultViewState;
import com.essaid.views.flex.internal.InternalView;
import com.essaid.views.flex.internal.RequestHandler;
import com.essaid.views.flex.internal.StateKeys;
import com.essaid.views.flex.internal.ViewHandler;
import com.essaid.views.flex.internal.ViewRequest;
import com.essaid.views.flex.internal.ViewState;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultFlexModel extends AbstractFlexModel {

  private final Config modelConfig;



  public DefaultFlexModel(Config modelConfig) {
    this.modelConfig = modelConfig;
  }

  @Override
  public Config getConfig() {
    return modelConfig;
  }

  @SuppressWarnings("unchecked")








  private Map<Class<?>, Map<Method, Method>> methodsMap = new HashMap<>();



  @Override
  public Method getClientMethod(ViewRequest viewRequest){
    Method invokedMethod = viewRequest.getInvokedMethod();
    Class<? extends InternalView> viewClass = viewRequest.getView().getClass();
    Method method = methodsMap.computeIfAbsent(viewClass, c -> new HashMap<>())
        .computeIfAbsent(viewRequest.getInvokedMethod(),
            m -> {
              try {
                return viewClass.getMethod(invokedMethod.getName(), invokedMethod.getParameterTypes());
              } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
              }
            });

    return method;
  }




}
