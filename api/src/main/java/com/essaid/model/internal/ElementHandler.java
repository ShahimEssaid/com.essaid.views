package com.essaid.model.internal;

import com.essaid.model.EntityManager;

public interface ElementHandler {

    Class<?> getElementType();

    Object getFeatureValue(String featureName);

    Object setFeatureValue(String featureName, Object value);

    Object unsetFeatureValue(String featureName);

    EntityManager getEntityManager();

}
