package com.essaid.model;

import com.essaid.model.internal.map.Request;

import java.util.List;
import java.util.Map;

/**
 * Represents an instance of a Model, and handles non-model related services.
 */
public interface EntityManager {


    <T> T create(Class<T> entityInterface);

    <E extends Modelled> List<E> createList();

    <K, V> Map<K, V> createMap();


    default Internal internal() {
        return (Internal) this;
    }

    interface Internal extends EntityManager {
        Object handle(Request request);

        Config getConfig();
    }

}
