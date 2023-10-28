package com.essaid.model;

import com.essaid.model.internal.ModelFactory;
import com.essaid.model.internal.map.RequestHandlerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Config {
    private final Map<Class<?>, Class<?>[]> proxyInterfacesMap = new ConcurrentHashMap<>();
    private final Map<Class<?>, Class<?>> implementations = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<ModelFactory> modelFactories = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<RequestHandlerFactory> handlerFactories = new CopyOnWriteArrayList<>();

    public CopyOnWriteArrayList<ModelFactory> getModelFactories() {
        return modelFactories;
    }

    public CopyOnWriteArrayList<RequestHandlerFactory> getHandlerFactories() {
        return handlerFactories;
    }

     public Config addEntityInterface(Class<? extends Entity> entityInterface,
                                        List<Class<? extends Entity>> entityDefaults) {
        entityDefaults = new ArrayList<>(entityDefaults);
        if (! entityDefaults.contains(entityInterface)){
            entityDefaults.add(0, entityInterface);
        }
        proxyInterfacesMap.put(entityInterface, entityDefaults.toArray(new Class[]{}));
        return this;
    }

    public Config addElementInterface(Class<? extends Element> elementInterface,
                                      Class<? extends Element>... elementDefaults) {
        Class<? extends Element>[] interfaces = Arrays.copyOf(elementDefaults, elementDefaults.length + 1);
        interfaces[0] = elementInterface;
        System.arraycopy(elementDefaults, 0, interfaces, 1, elementDefaults.length);
        proxyInterfacesMap.put(elementInterface, interfaces);
        return this;
    }

    public Config addSupportInterface(Class<?> supportInterface, Class<?>... supportDefaults) {
        Class<?>[] interfaces = Arrays.copyOf(supportDefaults, supportDefaults.length + 1);
        interfaces[0] = supportInterface;
        System.arraycopy(supportDefaults, 0, interfaces, 1, supportDefaults.length);
        proxyInterfacesMap.put(supportInterface, interfaces);
        return this;
    }

    public <T> Config addInterfaceImplementation(Class<T> iface, Class<? extends T> implementation) {
        implementations.put(iface, implementation);
        return this;
    }

    public Config addModelFactory(ModelFactory modelFactory) {
        modelFactories.add(modelFactory);
        return this;
    }


    public Config addHandlerFactory(RequestHandlerFactory handlerFactory) {
        handlerFactories.add(handlerFactory);
        return this;
    }

    public Class<?>[] getDefaults(Class<?> iface) {
        return proxyInterfacesMap.get(iface);
    }

    public Class<?> getImplementation(Class<?> cls) {
        return implementations.get(cls);
    }

    public Class<?>[] getProxyInterfaces(Class<?> cls){
        return proxyInterfacesMap.get(cls);
    }
}
