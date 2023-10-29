package com.essaid.model.internal.map;

import com.essaid.model.Config;
import com.essaid.model.Element;
import com.essaid.model.Entity;
import com.essaid.model.Modelled;
import com.essaid.model.internal.ModelFactory;
import com.essaid.model.internal.impl.AbstractEntityManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MapEntityManager extends AbstractEntityManager {
    private final Config modelConfig;
    private final Map<Class<?>, ModelFactory> factories = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Method, RequestHandler> methodHandlers = new ConcurrentHashMap<>();

    private Map<String, Entity> entityMap = new ConcurrentHashMap<>();

    public MapEntityManager(String modelId, Config modelConfig) {
        super(modelId);
        this.modelConfig = modelConfig;
    }


    @Override
    public Map<String, Entity> getEntities() {
        return entityMap;
    }

    @Override
    public <E extends Entity> E getEntity(String entityId) {
        return (E) entityMap.get(entityId);
    }

    @Override
    public void saveEntity(Entity entity) {
        entityMap.put(entity.getId(), entity);
    }

    @Override
    public void deleteEntity(Entity entity) {
        entityMap.remove(entity.getId());
    }

    @Override
    public <T extends Entity> T createEntity(Class<T> entityInterface, String entityId) {
        ModelFactory factory = factories.computeIfAbsent(entityInterface, c -> findModelFactory(c, entityId));
        return factory.create(entityInterface, entityId, this);
    }

    private ModelFactory findModelFactory(Class<?> cls, String entityId) {
        return modelConfig.getModelFactories()
                          .stream()
                          .filter(mf -> mf.canCreate(cls, entityId, modelConfig))
                          .findFirst()
                          .orElse(null);
    }

    @Override
    public <T extends Element> T createElement(Class<T> elementInterface) {
        ModelFactory factory = factories.computeIfAbsent(elementInterface, c -> findModelFactory(c, null));
        return factory.create(elementInterface, null, this);
    }

    @Override
    public <E extends Modelled> List<E> createList(Class<E> elementInterface) {
        try {
            return (List<E>) modelConfig.getImplementation(List.class).getConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <K, V> Map<K, V> createMap(Class<K> keyType, Class<V> valueType) {
        try {
            return (Map<K, V>) modelConfig.getImplementation(Map.class).getConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Object handle(Request request) {
        RequestHandler handler = methodHandlers.computeIfAbsent(request.getMethod(), this::findRequestHandler);
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
                          .map(requestHandlerFactory -> requestHandlerFactory.getHandler(method))
                          .filter(Objects::nonNull)
                          .findFirst()
                          .orElse(null);
    }
}
