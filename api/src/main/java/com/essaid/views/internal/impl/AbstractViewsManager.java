package com.essaid.views.internal.impl;

import com.essaid.views.View;
import com.essaid.views.session.ViewsSession;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.session.impl.ViewsSessionImpl;
import com.essaid.views.proxy.Config;
import com.essaid.views.proxy.internal.ImplUtils;
import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractViewsManager implements ViewsManagerInternal {


  private final Config modelConfig;
  private Map<Class<?>[], Class<?>[]> interfacesMap = new ConcurrentHashMap<>();
  private Map<Class<?>, Map<Method, Method>> methodsMap = new HashMap<>();

  @Override
  public ViewsSession createSession() {
    return new ViewsSessionImpl(this);
  }

  @Override
  public Config getConfig() {
    return modelConfig;
  }

  @Override
  public Class<?>[] getInterfaces(Class<?> view, Class<?>... customViewDefaults) {

    Class<?>[] requestedInterfaces = Arrays.copyOf(customViewDefaults,
        customViewDefaults.length + 1);

    requestedInterfaces[0] = view;
    return interfacesMap.computeIfAbsent(requestedInterfaces,
        a -> findInterfaces(view, customViewDefaults));
  }

  @Override
  public Method getClientMethod(Request request) {
    Method invokedMethod = request.getInvokedMethod();
    Class<? extends View> viewClass = request.getView().getClass();
    Method method = methodsMap.computeIfAbsent(viewClass, c -> new HashMap<>())
        .computeIfAbsent(invokedMethod, m -> {
          try {
            return viewClass.getMethod(invokedMethod.getName(), invokedMethod.getParameterTypes());
          } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
          }
        });

    return method;
  }


  private Class<?>[] findInterfaces(Class<?> viewIface, Class<?>[] customDefaults) {
    ImplUtils.assertInterfaces(viewIface);
    ImplUtils.assertInterfaces(customDefaults);
    ImplUtils.checkNotView(customDefaults);

    Set<Class<?>> interfacesSet = new LinkedHashSet<>();
    if (View.class != viewIface) {
      interfacesSet.add(View.class);
    }
    walkInterfaces(viewIface, interfacesSet);

    List<Class<?>> finalInterfaces = new ArrayList<>();

    for (Class<?> iface : interfacesSet) {
      finalInterfaces.add(iface);
      for (Class<?> defaultIface : getConfig().getInterfaceDefaults(iface)) {
        if (finalInterfaces.contains(defaultIface)) {
          // todo: what to do here? deal with when logging is added?
        } else {
          finalInterfaces.add(defaultIface);
        }
      }
    }

    for (Class<?> customDefault : customDefaults) {
      if (finalInterfaces.contains(customDefault)) {
        //todo: log?
      } else {
        finalInterfaces.add(customDefault);
      }
    }

    Collections.reverse(finalInterfaces);
    return finalInterfaces.toArray(new Class<?>[]{});
  }

  private void walkInterfaces(Class<?> iface, Set<Class<?>> collector) {
    if (collector.add(iface)) {
      for (Class<?> parentIface : iface.getInterfaces()) {
        walkInterfaces(parentIface, collector);
      }
    }
  }
}
