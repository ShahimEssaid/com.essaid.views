package com.essaid.views.flex.impl;

import com.essaid.views.flex.Session;
import com.essaid.views.flex.impl.request.DefaultViewRequest;
import com.essaid.views.flex.internal.FlexModelInternal;
import com.essaid.views.flex.internal.InternalView;
import com.essaid.views.flex.internal.SessionInternal;
import com.essaid.views.flex.internal.ViewHandler;
import com.essaid.views.flex.internal.ViewRequest;
import com.essaid.views.flex.internal.ViewState;
import java.lang.reflect.Method;

public class DefaultViewHandler implements ViewHandler {

  private final ViewState viewState;
  private final SessionInternal sessionInternal;

  public DefaultViewHandler(SessionInternal sessionInternal, ViewState viewState) {
    this.sessionInternal = sessionInternal;
    this.viewState = viewState;
  }

  @Override
  public SessionInternal getSession() {
    return sessionInternal;
  }

  @Override
  public ViewState getState() {
    return viewState;
  }


  @Override
  public Object invoke(Object proxy, Method invokedMethod, Object[] args) throws Throwable {
    Class<?>[] interfaces = proxy.getClass().getInterfaces();
    Class<?> clientViewType = interfaces[interfaces.length - 1];

    // this could be null
//    Method clientMethod = clientViewType.getMethod(invokedMethod.getName(),
//        invokedMethod.getParameterTypes());

    ViewRequest request = new DefaultViewRequest((InternalView) proxy, clientViewType,
        invokedMethod, args, this);
    sessionInternal.process(request);
    return request.getResponse().getValue();
  }
}
