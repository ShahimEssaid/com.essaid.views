package com.essaid.views.proxy.internal;

import com.essaid.views.internal.ViewInternal;
import java.lang.reflect.Method;
import java.util.Map;

public interface Request {

  ViewInternal getView();

  Method getInvokedMethod();

  /**
   * Call this if a client method is really needed.
   * @return the client or the base Object method.
   */
//  Method getClientOrObjectMethod();

  Object[] getInvokedArgs();

  Map<Object, Object> getMetadata(boolean create);

  Response getResponse();
}
