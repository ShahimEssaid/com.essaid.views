package com.essaid.model.internal;

public interface ElementHandler {

    Class<?> getElementType();

    Object getFeatureValue(String featureName);

    Object setFeatureValue(String featureName, Object value);

    Object unsetFeatureValue(String featureName);

}
