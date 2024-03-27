package com.essaid.model.internal;

import com.essaid.model.ModelManager;
import java.lang.reflect.InvocationHandler;
import java.util.Map;

public interface
ModelObjectHandler extends InvocationHandler {

    Class<?> getObjectType();

    Object getFeatureValue(String featureName);

    Object setFeatureValue(String featureName, Object value);

    Object unsetFeatureValue(String featureName);

    boolean hasFeature(String featureName);

    ModelManager getModelManager();

    Map<String, Object> getMapView();

}
