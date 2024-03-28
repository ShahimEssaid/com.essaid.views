package com.essaid.model.internal;

import java.util.Set;

public interface State {

  Object getFeatureValue(String featureName);

  Object setFeatureValue(String featureName, Object value);

  Object unsetFeatureValue(String featureName);

  boolean hasFeature(String featureName);

  Set<String> getFeatureNames();

}
