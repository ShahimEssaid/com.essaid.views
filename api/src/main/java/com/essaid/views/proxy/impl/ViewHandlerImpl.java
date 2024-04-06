package com.essaid.views.proxy.impl;

import com.essaid.views.proxy.impl.request.RequestImpl;
import com.essaid.views.proxy.internal.Request;
import com.essaid.views.internal.State;
import com.essaid.views.proxy.internal.ViewHandler;
import com.essaid.views.internal.ViewInternal;
import java.lang.reflect.Method;

public class ViewHandlerImpl implements ViewHandler {

  private static final String GET_TYPE = "__getType";
  private static final String GET_INTERFACES = "__getInterfaces";
  private static final String GET_STATE = "__getState";
  private static final String AS = "__as";

  private final Class<?> viewType;
  private final Class<?>[] interfaces;
  private final State state;


  public ViewHandlerImpl(Class<?> viewType, Class<?>[] interfaces, State state) {

    this.viewType = viewType;
    this.interfaces = interfaces;
    this.state = state;
  }


  @Override
  public State getState() {
    return state;
  }

  @Override
  public Class<?> getViewType() {
    return viewType;
  }

  @Override
  public Class<?>[] getInterfaces() {
    return interfaces;
  }


  @Override
  public Object invoke(Object proxy, Method invokedMethod, Object[] args) throws Throwable {

    if(invokedMethod.getName().startsWith("__")){
      Object result =  handleSpecial(proxy, invokedMethod, args);
      if(result != null){
        return result;
      }
    }

    Request request = new RequestImpl((ViewInternal) proxy,  invokedMethod, args);
    state.getSession().process(request);
    return request.getResponse().getValue();
  }

  private Object handleSpecial(Object proxy, Method invokedMethod, Object[] args) {

    // strings are interned
    if(GET_TYPE == invokedMethod.getName()){
      return viewType;
    }

    if(GET_INTERFACES == invokedMethod.getName()){
      return interfaces;
    }

    if(GET_STATE == invokedMethod.getName()){
      return state;
    }

    return null;
  }
}
