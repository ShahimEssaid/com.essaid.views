package com.essaid.views.proxy;

import com.essaid.views.View;
import com.essaid.views.proxy.internal.ImplUtils;
import com.essaid.views.proxy.internal.RequestHandlerFactory;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;

public class Config {

  private final Map<Class<? extends View>, Class<?>[]> viewDefaultsMap = new ConcurrentHashMap<>();
  private final Map<Class<?>, Class<?>[]> externalDefaultsMap = new ConcurrentHashMap<>();
  private final Map<Class<?>, Class<?>> implementations = new ConcurrentHashMap<>();

  @Getter
  private final CopyOnWriteArrayList<RequestHandlerFactory> handlerFactories = new CopyOnWriteArrayList<>();

  public Config addView(Class<? extends View> viewInterface,
      Class<?>... defaults) {
    checkAddView(viewInterface, defaults);
    viewDefaultsMap.put(viewInterface, defaults);
    return this;
  }

  public Config addExternal(Class<? extends View> externalInterface,
      Class<?>... defaults) {
    checkAddExternal(externalInterface, defaults);
    externalDefaultsMap.put(externalInterface, defaults);
    return this;
  }

  public <T> Config addImplementation(Class<T> iface, Class<? extends T> implementation) {
    checkAddImplementation(iface, implementation);
    implementations.put(iface, implementation);
    return this;
  }

  public Config addHandlerFactory(RequestHandlerFactory... factories) {
    handlerFactories.addAll(Arrays.asList(factories));
    return this;
  }

  public Class<?> getImplementation(Class<?> cls) {
    return implementations.get(cls);
  }

  public Class<?>[] getInterfaceDefaults(Class<?> iface) {
    Class<?>[] ifaces = ImplUtils.EMPTY_DEFAULTS;
    ifaces = viewDefaultsMap.getOrDefault(iface, ifaces);
    ifaces = externalDefaultsMap.getOrDefault(iface, ifaces);

    return ifaces;
  }

  private void checkAddView(Class<? extends View> viewInterface,
      Class<?>... defaults) {
    ImplUtils.assertInterfaces(viewInterface);
    ImplUtils.assertInterfaces(defaults);
    ImplUtils.checkNotView(defaults);
    checkNotAlreadyAdded(viewInterface);
  }

  private void checkAddExternal(Class<? extends View> viewInterface,
      Class<?>... defaults) {

    ImplUtils.checkNotView(viewInterface);
    ImplUtils.checkNotView(defaults);

    ImplUtils.assertInterfaces(viewInterface);
    ImplUtils.assertInterfaces(defaults);
    checkNotAlreadyAdded(viewInterface);
  }

  private void checkAddImplementation(Class<?> iface, Class<?> implementation) {
    ImplUtils.checkNotView(iface);
    ImplUtils.assertInterfaces(iface);
    ImplUtils.assertImplementation(implementation);
    checkNotAlreadyAdded(iface);
  }

  private void checkNotAlreadyAdded(Class<?> iface) {
    if (viewDefaultsMap.containsKey(iface)) {
      throw new IllegalArgumentException("Interface: " + iface + " already added as a View "
          + "interface with defaults: " + Arrays.toString(viewDefaultsMap.get(iface)));
    }

    if (externalDefaultsMap.containsKey(iface)) {
      throw new IllegalArgumentException("Interface: " + iface + " already added as an external "
          + "interface with defaults: " + Arrays.toString(externalDefaultsMap.get(iface)));
    }

    if (implementations.containsKey(iface)) {
      throw new IllegalArgumentException("Interface: " + iface + " already added as an "
          + "implementation interface for implementation: " + implementations.get(iface));
    }
  }

}
