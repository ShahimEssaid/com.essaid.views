package com.essaid.views.internal.impl;

import com.essaid.views.View;
import com.essaid.views.internal.Value;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.internal.ViewsSessionInternal;
import com.essaid.views.proxy.impl.ValueImpl;
import com.essaid.views.proxy.internal.Request;
import com.essaid.views.proxy.internal.RequestHandler;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractViewsSession implements ViewsSessionInternal {

  @Getter
  protected final ViewsManagerInternal viewManager;
  private final ConcurrentHashMap<Method, RequestHandler> requestHandlers = new ConcurrentHashMap<>();

  protected <T> Class<?>[] calculateInterfaces(Class<T> viewType, Class<?>[] customDefaults) {
    return viewManager.getInterfaces(viewType, customDefaults);

  }

  protected RequestHandler getRequestHandler(Request request) {
    Method invokedMethod = request.getInvokedMethod();
    Class<? extends View> viewClass = request.getView().getClass();

    Method method = getViewManager().getClientMethod(request);

    return requestHandlers.computeIfAbsent(method,
        m -> findRequestHandler(request));
  }

  private RequestHandler findRequestHandler(Request request) {
    Optional<RequestHandler> first = viewManager.getConfig().getHandlerFactories().stream()
        .map(f -> f.getHandler(request)).filter(Objects::nonNull).findFirst();

    if (!first.isPresent()) {
      throw new IllegalArgumentException("Can't find request handler for request: " + request);
    }
    return first.get();
  }

  protected Value createState() {
    return new ValueImpl(this);
  }

}
