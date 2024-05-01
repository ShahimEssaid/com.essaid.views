package com.essaid.views.proxy.impl;

import com.essaid.views.View;
import com.essaid.views.internal.AbstractViewHandler;
import com.essaid.views.internal.Value;
import com.essaid.views.proxy.impl.request.RequestImpl;
import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyViewHandler extends AbstractViewHandler implements InvocationHandler {

  private static final String GET_TYPE = "__getType";
  private static final String GET_INTERFACES = "__getInterfaces";
  private static final String GET_STATE = "__getState";
  private static final String AS = "__as";


  public ProxyViewHandler(Class<?> viewType, Class<?>[] interfaces, Value state) {
    super(viewType, interfaces, state);
  }


  @Override
  public Object invoke(Object proxy, Method invokedMethod, Object[] args) throws Throwable {

    if (invokedMethod.getName().startsWith("__")) {
      Object result = handleSpecial(proxy, invokedMethod, args);
      if (result != null) {
        return result;
      }
    }

    Request request = new RequestImpl((View) proxy, invokedMethod, args);
    getValue().getSession().process(request);
    return request.getResponse().getValue();
  }

  private Object handleSpecial(Object proxy, Method invokedMethod, Object[] args) {

    // strings are interned
    if (GET_TYPE == invokedMethod.getName()) {
      return getViewType();
    }

    if (GET_INTERFACES == invokedMethod.getName()) {
      return getInterfaces();
    }

    if (GET_STATE == invokedMethod.getName()) {
      return getValue();
    }

    return null;
  }

  @Override
  public <T> T adaptTo(Class<T> clazz, Class<?>... defaults) {
    return null;
  }
}
