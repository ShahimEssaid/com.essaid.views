package com.essaid.model.impl.map;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.InstanceFactory;
import com.essaid.model.internal.ObjectState;
import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.Proxy;

public class ProxyInstanceFactory implements InstanceFactory {

  @Override
  public <T> T create(Class<T> viewType, Class<?>[] viewInterfaces, ObjectState objectState,
      ModelManager modelManager) {

    if (objectState == null) {
      objectState = new DefaultObjectState();
    }
    ViewHandler viewHandler = modelManager.internal()
        .createViewHandler(modelManager.internal().createState());

    return (T) Proxy.newProxyInstance(viewType.getClassLoader(), viewInterfaces, viewHandler);

  }

  @Override
  public boolean canCreate(Class<?> viewType, Class<?>[] viewInterfaces, ObjectState objectState,
      ModelManager modelManager) {
    return viewType.isInterface();
  }

//  @Override
//  public boolean canCreate(Class<?> interfaceClass, String instanceId, ModelManager modelManager) {
//    return interfaceClass.isInterface();
////        return Modelled.class.isAssignableFrom(interfaceClass);
//  }
//
//  @Override
//  public <T> T create(Class<T> viewType, String instanceId, ModelManager modelManager,
//      Class<?>... customDefaults) {
//    Class<?>[] defaults = modelManager.getConfig().getProxyInterfaces(viewType);
//    defaults = ImplUtils.concatWithArrayCopy(customDefaults, defaults, viewType);
//
//    State state = new DefaultState();
//    DefaultViewHandler handler = new DefaultViewHandler(viewType, state, modelManager, customDefaults);
//
//    return (T) Proxy.newProxyInstance(viewType.getClassLoader(), defaults, handler);
//  }


}
