package com.essaid.model.internal;

import com.essaid.model.Element;

public interface ElementHandler {

   Class<?> getElementType();

    Object getFeatureValue(String featureName);

    Object setFeatureValue(String featureName, Object value);

}
