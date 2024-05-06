package com.essaid.views.proxy.impl;

import com.essaid.views.View;
import com.essaid.views.internal.impl.AbstractViewHandler;
import com.essaid.views.internal.Value;
import com.essaid.views.proxy.impl.request.RequestImpl;
import com.essaid.views.proxy.internal.Request;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyViewHandler extends AbstractViewHandler implements InvocationHandler {

//  private static final String GET_TYPE = "__getType";
//  private static final String GET_INTERFACES = "__getInterfaces";
  private static final String GET_VIEW_HANDLER = "_getViewHandler";
  private static final String AS = "__as";


  public ProxyViewHandler(Class<?> viewType, Class<?>[] interfaces, Value state) {
    super(viewType, interfaces, state);
  }


  @Override
  public Object invoke(Object proxy, Method invokedMethod, Object[] args) throws Throwable {

    if (invokedMethod.getName().startsWith("_")) {
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

    String methodName = invokedMethod.getName();
    // strings are interned
//    if (GET_TYPE == methodName) {
//      return getViewType();
//    }
//
//    if (GET_INTERFACES == methodName) {
//      return getInterfaces();
//    }

    if (GET_VIEW_HANDLER == methodName) {
      return this;
    }

    return null;
  }

  @Override
  public <T> T adaptTo(Class<T> clazz, Class<?>... defaults) {
    return null;
  }
}
