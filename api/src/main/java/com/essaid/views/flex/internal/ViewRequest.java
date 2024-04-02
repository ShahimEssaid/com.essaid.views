package com.essaid.views.flex.internal;

import java.lang.reflect.Method;
import java.util.Map;

public interface ViewRequest {

  InternalView getView();

  Method getInvokedMethod();

  /**
   * this will be null if {@link Object#equals(Object)},  {@link Object#hashCode()} or
   * {@link Object#toString()} was called by the client.  See {@link FlexModelInternal#getClientOrObjectMethod()}.
   * @return the client method
   */
  void setClientMethod(Method clientMethod);
  Method getClientMethod();

  /**
   * Call this if a client method is really needed.
   * @return the client or the base Object method.
   */
//  Method getClientOrObjectMethod();

  Object[] getInvokedArgs();

  ViewHandler getViewHandler();
  Map<Object, Object> getMetadata(boolean create);

  ViewResponse getResponse();
}
