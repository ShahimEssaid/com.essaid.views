package com.essaid.views.proxy.impl;

import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.internal.ViewInternal;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.internal.SessionInternal;
import com.essaid.views.proxy.internal.Request;
import com.essaid.views.internal.State;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractSession implements SessionInternal {

  @Getter
  protected final ViewsManagerInternal viewManager;

  protected  <T> Class<?>[] calculateInterfaces(Class<T> viewType, Class<?>[] customDefaults) {
    return  viewManager.getInterfaces(viewType, customDefaults);

  }

  private final ConcurrentHashMap<Method, RequestHandler> requestHandlers = new ConcurrentHashMap<>();
  protected RequestHandler getRequestHandler(Request request) {
    Method invokedMethod = request.getInvokedMethod();
    Class<? extends ViewInternal> viewClass = request.getView().getClass();
    Method method = getClientMethod(request);

    return requestHandlers.computeIfAbsent(method,
        m -> findRequestHandler(request));
  }

  private RequestHandler findRequestHandler(Request request) {
    Optional<RequestHandler> first = viewManager.getConfig().getHandlerFactories().stream()
        .map(f -> f.getHandler(request)).filter(Objects::nonNull).findFirst();

    if (!first.isPresent()){
      throw new IllegalArgumentException("Can't find request handler for request: "+  request);
    }
    return first.get();
  }

  protected State createState(){
    return new StateImpl(this);
  }

}
