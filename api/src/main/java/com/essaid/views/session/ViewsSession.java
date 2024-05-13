package com.essaid.views.session;

import com.essaid.views.value.Value;

public interface ViewsSession {

  /**
   * To create a new view instance. The default implementation creates a
   * {@link java.lang.reflect.Proxy} based instance.
   *
   * @param <T>
   * @param viewType       The client view type/api.
   * @param state
   * @param customDefaults Interfaces that provide any custom default methods for custom behavior.
   * @return
   */
  <T> T createView(Class<T> viewType, Value state, Class<?>... customDefaults);




  default ViewsSessionInternal internal(){
    return (ViewsSessionInternal) this;
  }

}
