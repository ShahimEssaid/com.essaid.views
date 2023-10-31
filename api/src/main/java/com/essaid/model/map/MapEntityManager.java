package com.essaid.model.map;

import com.essaid.model.Config;
import com.essaid.model.EntityManager;
import com.essaid.model.Modelled;
import com.essaid.model.internal.InstanceFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MapEntityManager implements EntityManager.Internal {
    private final Config modelConfig;
    private final Map<Class<?>, InstanceFactory> factories = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Method, RequestHandler> methodHandlers = new ConcurrentHashMap<>();


    public MapEntityManager(Config modelConfig) {
        this.modelConfig = modelConfig;

    }


    private InstanceFactory findFactory(Class<?> cls, String entityId) {
        return modelConfig.getModelFactories()
                          .stream()
                          .filter(mf -> mf.canCreate(cls, entityId, modelConfig))
                          .findFirst()
                          .orElse(null);
    }

    @Override
    public <T> T create(Class<T> interfaze) {
        try {
            if (List.class.isAssignableFrom(interfaze)) {
                return (T) modelConfig.getImplementation(List.class).getConstructor().newInstance();
            } else if (Map.class.isAssignableFrom(interfaze)) {
                return (T) modelConfig.getImplementation(Map.class).getConstructor().newInstance();
            }
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        InstanceFactory instanceFactory = factories.computeIfAbsent(interfaze, c -> findFactory(c, null));
        return instanceFactory.create(interfaze, null, this);
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
    public Object handle(Request request) {
        RequestHandler handler = methodHandlers.computeIfAbsent(request.getMethod(), this::findRequestHandler);
        if (handler == null) {
            throw new RuntimeException("No handler for method: " + request.getMethod());
        }
        return handler.handleRequest(request);
    }

    @Override
    public Config getConfig() {
        return modelConfig;
    }

    private RequestHandler findRequestHandler(Method method) {
        RequestHandler handler = null;
        return modelConfig.getHandlerFactories()
                          .stream()
                          .map(requestHandlerFactory -> requestHandlerFactory.getHandler(method, MapEntityManager.this))
                          .filter(Objects::nonNull)
                          .findFirst()
                          .orElse(null);
    }
}
