package com.essaid.model;

import com.essaid.model.internal.InstanceFactory;
import com.essaid.model.internal.map.RequestHandlerFactory;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Config {
    private final Map<Class<?>, Class<?>[]> proxyInterfacesMap = new ConcurrentHashMap<>();
    private final Map<Class<?>, Class<?>> implementations = new ConcurrentHashMap<>();
    @Getter
    private final CopyOnWriteArrayList<InstanceFactory> modelFactories = new CopyOnWriteArrayList<>();
    @Getter
    private final CopyOnWriteArrayList<RequestHandlerFactory> handlerFactories = new CopyOnWriteArrayList<>();

    public Config addEntityInterface(Class<? extends Entity> entityInterface, List<Class<?>> entityDefaults) {
        List<Class<?>> interfaces = new ArrayList<>(entityDefaults);
        if (!interfaces.contains(entityInterface)) {
            interfaces.add(entityInterface);
        }
        if (!interfaces.contains(EqualsToStringHashCodeDefaults.class)) {
            interfaces.add(EqualsToStringHashCodeDefaults.class);
        }
        proxyInterfacesMap.put(entityInterface, interfaces.toArray(new Class[]{}));
        return this;
    }

    public Config addElementInterface(Class<? extends Element> elementInterface, List<Class<?>> elementDefaults) {
        List<Class<?>> interfaces = new ArrayList<>(elementDefaults);
        if (!interfaces.contains(elementInterface)) {
            interfaces.add(elementInterface);
        }
        if (!interfaces.contains(EqualsToStringHashCodeDefaults.class)) {
            interfaces.add(EqualsToStringHashCodeDefaults.class);
        }
        proxyInterfacesMap.put(elementInterface, interfaces.toArray(new Class[]{}));
        return this;
    }

    public Config addSupportInterface(Class<?> supportInterface, List<Class<?>> supportDefaults) {
        List<Class<?>> interfaces = new ArrayList<>(supportDefaults);
        if (!interfaces.contains(supportInterface)) {
            interfaces.add(supportInterface);
        }
        proxyInterfacesMap.put(supportInterface, interfaces.toArray(new Class[]{}));
        return this;
    }

    public <T> Config addInterfaceImplementation(Class<T> iface, Class<? extends T> implementation) {
        if (implementation.isInterface()) {
            throw new IllegalArgumentException("Implementation is an interface: " + implementation.getName());
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
        return proxyInterfacesMap.computeIfAbsent(cls,
                                                  aClass -> List.of(cls, EqualsToStringHashCodeDefaults.class)
                                                                .toArray(new Class[]{}));
    }
}
