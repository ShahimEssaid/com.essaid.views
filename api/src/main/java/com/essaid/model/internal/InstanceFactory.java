package com.essaid.model.internal;

import com.essaid.model.ModelManager;

public interface InstanceFactory {

//  boolean canCreate(Class<?> interfaceClass, String instanceId, ModelManager modelManager);

//  default <T> T create(Class<T> objectType, String instanceId, ModelManager modelManager) {
//    return create(objectType, instanceId, modelManager, ImplUtils.EMPTY_DEFAULTS);
//  }
//
//  <T> T create(Class<T> objectType, String instanceId, ModelManager modelManager,
//      Class<?>... interfaces);

  <T> T create(Class<T> viewType, Class<?>[] viewInterfaces, ObjectState objectState,
      ModelManager modelManager);

  boolean canCreate(Class<?> viewType, Class<?>[] viewInterfaces, ObjectState objectState,
      ModelManager modelManager);

}
