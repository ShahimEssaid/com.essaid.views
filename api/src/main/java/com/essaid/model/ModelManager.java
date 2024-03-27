package com.essaid.model;

import com.essaid.model.impl.map.ModelObjectHandler;
import java.lang.reflect.Method;

/**
 * Represents an instance of a Model, and handles non-model related services.
 */
public interface ModelManager {


  <T> T create(Class<T> objectType, Class<?>... defaults);

  Config getConfig();

  default Internal internal() {
    return (Internal) this;
  }

  interface Internal extends ModelManager {

    String getFeatureName(Method method);

    Object handle(Object proxy, Method method, Object[] args, ModelObjectHandler modelObjectHandler);
  }

}
