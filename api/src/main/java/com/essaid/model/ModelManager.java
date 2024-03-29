package com.essaid.model;

import com.essaid.model.internal.ObjectState;
import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.Method;

/**
 * Represents an instance of a Model, and handles non-model related services.
 */
public interface ModelManager {

  /**
   * To create a new view instance. The default implementation creates a
   * {@link java.lang.reflect.Proxy} based instance.
   *
   * @param viewType       The client view type/api.
   * @param customDefaults Interfaces that provide any custom default methods for custom behavior.
   * @param <T>
   * @return
   */
  default <T> T create(Class<T> viewType, Class<?>... customDefaults) {
    return internal().as(viewType, null, customDefaults);
  }

  Config getConfig();

  default Internal internal() {
    return (Internal) this;
  }

  interface Internal extends ModelManager {

    <T> T as(Class<T> viewType, ObjectState objectState, Class<?>... customDefaults);

    String getFeatureName(Method method);

    Object handle(Object proxy, Method method, Object[] args,
        ViewHandler modelObjectHandler);

    Method getClientMethod(Class<?> clientType, Method invokedProxyMethod);

    ObjectState createState();

    ViewHandler createViewHandler(ObjectState state);

  }

}
