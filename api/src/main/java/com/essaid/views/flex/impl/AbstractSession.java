package com.essaid.views.flex.impl;

import com.essaid.views.flex.internal.FlexModelInternal;
import com.essaid.views.flex.internal.InternalView;
import com.essaid.views.flex.internal.RequestHandler;
import com.essaid.views.flex.internal.SessionInternal;
import com.essaid.views.flex.internal.ViewRequest;
import com.essaid.views.flex.internal.ViewState;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractSession  implements SessionInternal {

  @Getter
  protected final FlexModelInternal flexModel;

  protected  <T> Class<?>[] calculateInterfaces(Class<T> viewType, Class<?>[] customDefaults) {
    Class<?>[] modelViewInterfaces = flexModel.getConfig().getProxyInterfaces(viewType);
    Class<? extends InternalView> internal = null;
    return ImplUtils.concatWithArrayCopy(customDefaults, modelViewInterfaces, InternalView.class,
        viewType);
  }

  private final ConcurrentHashMap<Method, RequestHandler> requestHandlers = new ConcurrentHashMap<>();
  protected RequestHandler getRequestHandler(ViewRequest viewRequest) {
    Method invokedMethod = viewRequest.getInvokedMethod();
    Class<? extends InternalView> viewClass = viewRequest.getView().getClass();
    Method method = getClientMethod(viewRequest);

    return requestHandlers.computeIfAbsent(method,
        m -> findRequestHandler(viewRequest));
  }

  private RequestHandler findRequestHandler(ViewRequest request) {
    RequestHandler handler = flexModel.getConfig().getHandlerFactories().stream()
        .map(f -> f.getHandler(request)).filter(Objects::nonNull).findFirst().get();
    return handler;
  }

  protected ViewState createState(){
    return new DefaultViewState(this);
  }

}
