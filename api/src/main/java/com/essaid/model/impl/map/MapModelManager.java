package com.essaid.model.impl.map;

import com.essaid.model.Config;
import com.essaid.model.View.InternalView;
import com.essaid.model.impl.AbstractModelManager;
import com.essaid.model.impl.ImplUtils;
import com.essaid.model.internal.InstanceFactory;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestType;
import com.essaid.model.internal.State;
import com.essaid.model.internal.ViewHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MapModelManager extends AbstractModelManager {

  private final Config modelConfig;
  private final Map<Class<?>, InstanceFactory> instanceFactories = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<Method, RequestHandler> methodHandlers = new ConcurrentHashMap<>();


  public MapModelManager(Config modelConfig) {
    this.modelConfig = modelConfig;
  }


  private InstanceFactory findFactory(Class<?> viewType, Class<?>[] viewInterfaces, State state) {
    return modelConfig.getModelFactories()
        .stream()
        .filter(mf -> mf.canCreate(viewType, viewInterfaces, state, this))
        .findFirst()
        .orElse(null);
  }

  @Override
  public <T> T as(Class<T> viewType, State state, Class<?>... customDefaults) {
    Class<?> implementation = modelConfig.getImplementation(viewType);
    if (implementation != null) {
      try {
        return (T) implementation.getConstructor().newInstance();
      } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
               IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }

    Class<?>[] allInterfaces = calculateInterfaces(viewType, customDefaults);

    InstanceFactory instanceFactory = instanceFactories.computeIfAbsent(
        viewType, c -> findFactory(c, allInterfaces, state));

    return instanceFactory.create(viewType, allInterfaces, state, this);
  }

  private <T> Class<?>[] calculateInterfaces(Class<T> viewType, Class<?>[] customDefaults) {
    Class<?>[] modelViewInterfaces = modelConfig.getProxyInterfaces(viewType);
    Class<? extends InternalView> internal = null;
    return ImplUtils.concatWithArrayCopy(customDefaults, modelViewInterfaces,
        InternalView.class, viewType);
  }


  @Override
  public Object handle(Object proxy, Method method, Object[] args,
      DefaultViewHandler objectHandler) {
    RequestHandler handler = methodHandlers.computeIfAbsent(method,
        this::findRequestHandler);

    if (handler == null) {
      throw new RuntimeException("No handler for method: " + method);
    }
    return handler.handle(proxy, method, args, objectHandler);
  }

  @Override
  public String getFeatureName(Method method) {
    return RequestType.getRequestType(method).extractFeatureName(method);
  }

  @Override
  public Config getConfig() {
    return modelConfig;
  }

  private RequestHandler findRequestHandler(Method method) {
    RequestType requestType = RequestType.getRequestType(method);

    // special methods
    if (requestType.equals(RequestType._GET_VIEW_HANDLER)) {
      return new RequestHandler() {
        @Override
        public Object handle(Object proxy, Method method, Object[] args,
            ViewHandler objectHandler) {
          return Proxy.getInvocationHandler(proxy);
        }
      };
    }

    // view features
    String featureName = requestType.extractFeatureName(method);
    return modelConfig.getHandlerFactories()
        .stream()
        .map(
            requestHandlerFactory -> requestHandlerFactory.getHandler(featureName, method,
                requestType, this
            ))
        .filter(Objects::nonNull)
        .findFirst()
        .orElse(null);
  }

}
