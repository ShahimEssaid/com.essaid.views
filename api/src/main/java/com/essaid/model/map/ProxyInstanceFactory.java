package com.essaid.model.map;

import com.essaid.model.Config;
import com.essaid.model.EntityManager;
import com.essaid.model.Modelled;
import com.essaid.model.internal.InstanceFactory;

import java.lang.reflect.Proxy;

public class ProxyInstanceFactory implements InstanceFactory {


    @Override
    public boolean canCreate(Class<?> interfaceClass, String instanceId, Config config) {
        return interfaceClass.isInterface();
//        return Modelled.class.isAssignableFrom(interfaceClass);
    }

    @Override
    public <T> T create(Class<T> interfaceClass, String instanceId, EntityManager entityManager) {
        MapElementHandler handler = new MapElementHandler(interfaceClass, entityManager);
        return (T) Proxy.newProxyInstance(Modelled.class.getClassLoader(),
                                          entityManager.internal().getConfig().getProxyInterfaces(interfaceClass),
                                          handler);
    }
}
