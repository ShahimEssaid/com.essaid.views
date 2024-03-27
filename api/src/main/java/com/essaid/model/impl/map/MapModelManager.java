package com.essaid.model.impl.map;

import com.essaid.model.Config;
import com.essaid.model.impl.AbstractModelManager;
import com.essaid.model.internal.InstanceFactory;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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


  private InstanceFactory findFactory(Class<?> cls, String entityId) {
    return modelConfig.getModelFactories()
        .stream()
        .filter(mf -> mf.canCreate(cls, entityId, this))
        .findFirst()
        .orElse(null);
  }

  @Override
  public <T> T create(Class<T> objectType, Class<?>... interfaces) {
    Class<?> implementation = modelConfig.getImplementation(objectType);
    if (implementation != null) {
      try {
        return (T) implementation.getConstructor().newInstance();
      } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
               IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }

    InstanceFactory instanceFactory = instanceFactories.computeIfAbsent(
        objectType, c -> findFactory(c, null));
    return instanceFactory.create(objectType, null, this, interfaces);
  }

//    @Override
//    public <E extends Modelled> List<E> createList() {
//        try {
//            return (List<E>) modelConfig.getImplementation(List.class).getConstructor().newInstance();
//        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
//                 IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public <K, V> Map<K, V> createMap() {
//        try {
//            return (Map<K, V>) modelConfig.getImplementation(Map.class).getConstructor().newInstance();
//        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
//                 IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }


  @Override
  public Object handle(Object proxy, Method method, Object[] args,
      ModelObjectHandler objectHandler) {
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
