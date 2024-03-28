package com.essaid.model.impl.map;

import com.essaid.model.internal.State;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultState extends ConcurrentHashMap<String, Object> implements State {

  @Override
  public Object getFeatureValue(String featureName) {
    return get(featureName);
  }

  @Override
  public Object setFeatureValue(String featureName, Object value) {
    return put(featureName, value);
  }

  @Override
  public Object unsetFeatureValue(String featureName) {
    return remove(featureName);
  }

  @Override
  public boolean hasFeature(String featureName) {
    return containsKey(featureName);
  }

  @Override
  public Set<String> getFeatureNames() {
    return Collections.unmodifiableSet(keySet());
  }
}
