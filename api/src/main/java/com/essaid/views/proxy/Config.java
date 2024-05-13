package com.essaid.views.proxy;

import com.essaid.views.View;
import com.essaid.views.internal.Provider;
import com.essaid.views.adapter.Adapter;
import com.essaid.views.proxy.internal.ImplUtils;
import com.essaid.views.proxy.internal.RequestHandlerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

  private static Logger logger = LoggerFactory.getLogger(Config.class);

  private final Map<Class<? extends View>, Class<?>[]> viewDefaultsMap = new ConcurrentHashMap<>();
  private final Map<Class<?>, Class<?>[]> externalDefaultsMap = new ConcurrentHashMap<>();
  private final Map<Class<?>, Class<?>> implementations = new ConcurrentHashMap<>();

  // adapters
  private final Map<Class<?>, Adapter> adaptersMap = new LinkedHashMap<>();
  private final List<Adapter> adapters = new ArrayList<>();

  // services
  private final Map<Class<?>, Object> servicesMap = new LinkedHashMap<>();

  //todo: remove lombok
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

  public Config addAdapters(Adapter... adapters) {
    for (Adapter adapter : adapters) {
      Class<? extends Adapter> adapterClass = adapter.getClass();
      if (adaptersMap.containsKey(adapterClass)) {
        logger.warn("Adapter type {} exists as: {} but is being replaced.", adapterClass,
            adaptersMap.get(adapterClass));
      }
      logger.debug("Adding adapter type: {} as: {}", adapterClass, adapter);
      adaptersMap.put(adapterClass, adapter);
    }
    this.adapters.clear();
    this.adapters.addAll(adaptersMap.values());
    return this;
  }

  public List<Adapter> getAdapters() {
    return adapters;
  }

  public Config addService(Class<?> serviceType, Object service) {
    if (servicesMap.containsKey(serviceType)) {
      logger.warn("Service type {} exists as: {} but is being replaced.", serviceType,
          servicesMap.get(serviceType));
    }
    logger.debug("Adding service type: {} as: {}", serviceType, service);
    servicesMap.put(serviceType, service);
    return this;
  }

  public <T> T getService(Class<T> serviceType) {
    Object o = servicesMap.get(serviceType);
    if (o == null) {
      return null;
    }

    if (o instanceof Provider<?>) {
      Provider<?> provider = (Provider<?>) o;
      return (T) provider.get();
    }
    return (T) o;
  }
  /////////////////////////////


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
