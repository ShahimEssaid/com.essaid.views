package com.essaid.views.flex;

import com.essaid.views.flex.internal.FlexModelInternal;

public interface Session {

  /**
   * To create a new view instance. The default implementation creates a
   * {@link java.lang.reflect.Proxy} based instance.
   *
   * @param viewType       The client view type/api.
   * @param customDefaults Interfaces that provide any custom default methods for custom behavior.
   * @param <T>
   * @return
   */
  <T> T create(Class<T> viewType, Class<?>... customDefaults);

  FlexModelInternal getFlexModel();
}
