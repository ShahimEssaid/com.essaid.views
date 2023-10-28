package com.essaid.model.internal.map;

import com.essaid.model.Config;
import com.essaid.model.Model;
import com.essaid.model.Modelled;
import com.essaid.model.internal.ModelFactory;

import java.lang.reflect.Proxy;

public class ModelledProxyFactory implements ModelFactory {


    @Override
    public boolean canCreate(Class<?> interfaceClass, String instanceId, Config config) {
        return Modelled.class.isAssignableFrom(interfaceClass);
    }

    @Override
    public <T> T create(Class<T> interfaceClass, String instanceId, Model model) {
        MapElementHandler handler = new MapElementHandler(interfaceClass, model);
        return (T) Proxy.newProxyInstance(Modelled.class.getClassLoader(),
                                          model.internal().getConfig().getProxyInterfaces(interfaceClass),
                                          handler);
    }
}
