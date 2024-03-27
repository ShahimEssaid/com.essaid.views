package com.essaid.model.impl.map;

import com.essaid.model.ModelManager;
import com.essaid.model.impl.ImplUtils;
import com.essaid.model.internal.InstanceFactory;
import java.lang.reflect.Proxy;

public class ProxyInstanceFactory implements InstanceFactory {

  @Override
  public boolean canCreate(Class<?> interfaceClass, String instanceId, ModelManager modelManager) {
    return interfaceClass.isInterface();
//        return Modelled.class.isAssignableFrom(interfaceClass);
  }

  @Override
  public <T> T create(Class<T> objectType, String instanceId, ModelManager modelManager,
      Class<?>... interfaces) {
    Class<?>[] proxyInterfaces = modelManager.getConfig().getProxyInterfaces(objectType);
    proxyInterfaces = ImplUtils.concatWithArrayCopy(interfaces, proxyInterfaces, objectType);

    ModelObjectHandler handler = new ModelObjectHandler(objectType, modelManager);

    return (T) Proxy.newProxyInstance(objectType.getClassLoader(), proxyInterfaces, handler);
  }
}
