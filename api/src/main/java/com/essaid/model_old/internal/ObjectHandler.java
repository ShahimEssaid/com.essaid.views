package com.essaid.model_old.internal;

import java.lang.reflect.InvocationHandler;

public interface ObjectHandler extends InvocationHandler {

    String ENTITY_HANDLER_NS = "__EH__";

    Object getFeatureValue(String featureName);
    Object setFeatureValue(String featureName, Object value);

}
