package com.essaid.model;

import com.essaid.model.impl.ImplUtils;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.EqualsToStringHashCodeDefaults;
import com.essaid.model.internal.InstanceFactory;
import com.essaid.model.internal.Instantiable;
import com.essaid.model.internal.ModelObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;

public class Config {

  private final Map<Class<?>, Class<?>[]> interfaceDefaultsMap = new ConcurrentHashMap<>();
  private final Map<Class<?>, Class<?>> implementations = new ConcurrentHashMap<>();
  @Getter
  private final CopyOnWriteArrayList<InstanceFactory> modelFactories = new CopyOnWriteArrayList<>();
  @Getter
  private final CopyOnWriteArrayList<RequestHandlerFactory> handlerFactories = new CopyOnWriteArrayList<>();

  public Config addInstantiableInterface(Class<? extends Instantiable<?>> modelInterface,
      Class<?>... interfaceDefaults) {
    return addInstantiableInterface(modelInterface, Arrays.asList(interfaceDefaults));
  }

  public Config addInstantiableInterface(Class<? extends Instantiable<?>> modelInterface,
      List<Class<?>> interfaceDefaults) {
    List<Class<?>> interfaces = new ArrayList<>(interfaceDefaults);
    interfaces.add(modelInterface);
    interfaces.add(EqualsToStringHashCodeDefaults.class);
    interfaceDefaultsMap.put(modelInterface, interfaces.toArray(new Class[]{}));
    return this;
  }

  public Config addMixinInterface(Class<? extends Mixin> mixinInterface,
      Class<?>... interfaceDefaults) {
    return addMixinInterface(mixinInterface, Arrays.asList(interfaceDefaults));
  }

  public Config addMixinInterface(Class<? extends Mixin> mixinInterface,
      List<Class<?>> interfaceDefaults) {
    List<Class<?>> interfaces = new ArrayList<>(interfaceDefaults);
    interfaces.add(mixinInterface);
    interfaces.add(EqualsToStringHashCodeDefaults.class);
    interfaceDefaultsMap.put(mixinInterface, interfaces.toArray(new Class[]{}));
    return this;
  }

  public Config addExternalInterface(Class<?> external,
      List<Class<?>> interfaceDefaults) {

    if (ModelObject.class.isAssignableFrom(external)) {
      throw new IllegalArgumentException("Interface is not external: " + external.getName());
    }

    List<Class<?>> interfaces = new ArrayList<>(interfaceDefaults);
    interfaces.add(external);
    interfaces.add(EqualsToStringHashCodeDefaults.class);
    interfaceDefaultsMap.put(external, interfaces.toArray(new Class[]{}));
    return this;
  }

  public <T> Config addInterfaceImplementation(Class<T> iface, Class<? extends T> implementation) {
    if (implementation.isInterface()) {
      throw new IllegalArgumentException(
          "Implementation is an interface: " + implementation.getName());
    }
    implementations.put(iface, implementation);
    return this;
  }

  public Config addInstanceFactory(InstanceFactory instanceFactory) {
    modelFactories.add(instanceFactory);
    return this;
  }

  public Config addHandlerFactory(RequestHandlerFactory handlerFactory) {
    handlerFactories.add(handlerFactory);
    return this;
  }

  public Class<?> getImplementation(Class<?> cls) {
    return implementations.get(cls);
  }

  public Class<?>[] getProxyInterfaces(Class<?> cls) {
    return interfaceDefaultsMap.getOrDefault(cls, ImplUtils.EMPTY_DEFAULTS);
  }
}
