package com.essaid.model.internal.map;

import com.essaid.model.*;
import com.essaid.model.internal.InstanceFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MapEntityManager implements  EntityManager.Internal {
    private final Config modelConfig;
    private final Map<Class<?>, InstanceFactory> factories = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Method, RequestHandler> methodHandlers = new ConcurrentHashMap<>();

    private Map<String, Entity> entityMap = new ConcurrentHashMap<>();

    public MapEntityManager(Config modelConfig) {
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
        if (entity instanceof Identifiable) {
            Identifiable identifiable
                    = (Identifiable) entity;
            String id = identifiable.get_id_();
            if (id == null) {
                throw new IllegalStateException("Missing _id_ value for:" + entity);
            }
            Entity currentEntity = entityMap.putIfAbsent(id, entity);
            if(currentEntity!= null && currentEntity != entity){
                IllegalStateException illegalStateException = new IllegalStateException(
                        "Different object with same ID. \tCurrent:" + currentEntity + "\tNew:" + entity);
                throw illegalStateException;
            }

        } else {
            throw new IllegalStateException("Entity is not Identifiable:" + entity);
        }
    }

    @Override
    public void deleteEntity(Entity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Entity> T createEntity(Class<T> entityInterface, String entityId) {
        InstanceFactory instanceFactory = factories.computeIfAbsent(entityInterface, c -> findFactory(c, entityId));
        return instanceFactory.create(entityInterface, entityId, this);
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
        InstanceFactory instanceFactory = factories.computeIfAbsent(interfaze, c -> findFactory(c, null));
        return  instanceFactory.create(interfaze, null, this);
    }

    @Override
    public <T extends Element> T createElement(Class<T> elementInterface) {
        InstanceFactory instanceFactory = factories.computeIfAbsent(elementInterface, c -> findFactory(c, null));
        return instanceFactory.create(elementInterface, null, this);
    }

    @Override
    public <E extends Modelled> List<E> createList() {
        try {
            return (List<E>) modelConfig.getImplementation(List.class).getConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <K, V> Map<K, V> createMap() {
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
        if(handler == null){
            throw new RuntimeException("No handler for method: "+ request.getMethod());
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
