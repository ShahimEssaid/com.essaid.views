package com.essaid.model;

import com.essaid.model.internal.map.Request;

import java.util.List;
import java.util.Map;

/**
 * Represents an instance of a Model, and handles non-model related services.
 */
public interface EntityManager {

    String getId();

    Map<String, Entity> getEntities();

    <E extends Entity> E getEntity(String entityId);

    void saveEntity(Entity entity);

    void deleteEntity(Entity entity);

    <T extends Entity> T createEntity(Class<T> entityInterface, String entityId);

    <T extends Element> T createElement(Class<T> elementInterface);

    <E extends Modelled> List<E> createList(Class<E> elementInterface);

    <K, V> Map<K, V> createMap(Class<K> keyType, Class<V> valueType);


    default Internal internal() {
        return (Internal) this;
    }

    interface Internal extends EntityManager {
        Object handle(Request request);

        Config getConfig();
    }

}
