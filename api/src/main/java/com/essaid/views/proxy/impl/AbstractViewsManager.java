package com.essaid.views.proxy.impl;

import com.essaid.views.Session;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.proxy.internal.ImplUtils;
import com.essaid.views.internal.ViewInternal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractViewsManager implements ViewsManagerInternal {


  private Map<Class<?>[], Class<?>[]> interfacesMap = new ConcurrentHashMap<>();


  @Override
  public Session createSession() {
    return new SessionImpl(this);
  }


  @Override
  public Class<?>[] getInterfaces(Class<?> view, Class<?>... customViewDefaults) {

    Class<?>[] requestedInterfaces = Arrays.copyOf(customViewDefaults,
        customViewDefaults.length + 1);

    requestedInterfaces[0] = view;
    return interfacesMap.computeIfAbsent(requestedInterfaces,
        a -> findInterfaces(view, customViewDefaults));
  }


  private Class<?>[] findInterfaces(Class<?> viewIface, Class<?>[] customDefaults) {
    ImplUtils.assertInterfaces(viewIface);
    ImplUtils.assertInterfaces(customDefaults);
    ImplUtils.checkNotViewOrViewInternal(customDefaults);


    Set<Class<?>> interfacesSet = new LinkedHashSet<>();
    if(ViewInternal.class != viewIface){
      interfacesSet.add(ViewInternal.class);
    }
    walkInterfaces(viewIface, interfacesSet);

    List<Class<?>> finalInterfaces = new ArrayList<>();

    for (Class<?> iface : interfacesSet) {
      finalInterfaces.add(iface);
      for (Class<?> defaultIface : getConfig().getProxyInterfaces(iface)) {
        if (finalInterfaces.contains(defaultIface)) {
          // todo: what to do here? deal with when logging is added?
        } else {
          finalInterfaces.add(defaultIface);
        }
      }
    }

    for(Class<?> customDefault: customDefaults){
      if(finalInterfaces.contains(customDefault)){
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
