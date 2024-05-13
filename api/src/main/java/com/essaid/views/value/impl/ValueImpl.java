package com.essaid.views.value.impl;

import com.essaid.views.View;
import com.essaid.views.internal.FeatureHandler;
import com.essaid.views.session.ViewsSessionInternal;
import com.essaid.views.value.Value;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ValueImpl extends ConcurrentHashMap<Object, Object> implements Value {

  private final ViewsSessionInternal session;

  private Object value;

  public ValueImpl(ViewsSessionInternal session) {
    this.session = session;
  }

  @Override
  public View getFeatureValue(String key) {
    return (View) get(key);
  }

  @Override
  public View setFeatureValue(String key, View value) {
    return (View) put(key, value);
  }

  @Override
  public View unsetFeatureValue(String key) {
    return (View) remove(key);
  }

  @Override
  public FeatureHandler getFeature(String featureName, View owningView) {
    return null;
  }

  @Override
  public FeatureHandler setFeature(String featureName, View targetView, View owningView) {
    return null;
  }

  @Override
  public FeatureHandler unsetFeature(String featureName, View owningView) {
    return null;
  }

  @Override
  public boolean hasFeature(String featureName) {
    return containsKey(featureName);
  }

  @Override
  public boolean hasFeatures() {
    return false;
  }

  @Override
  public Set<String> getFeatureNames() {

    return keySet().stream().filter(k -> k instanceof String).map(k -> (String) k)
        .collect(Collectors.toSet());
  }

  @Override
  public Object getValue() {
    return value;
  }

  @Override
  public Object setValue(Object value) {
    this.value = value;
    return this;
  }

  @Override
  public boolean hasValue() {
    return value != null;
  }

  @Override
  public Object getValue(Object key) {
    if (String.class == key.getClass()) {
      throw new IllegalArgumentException("Can't use String key in *Value methods. Key: " + key +
          " and state: " + this);
    }
    return get(key);
  }

  @Override
  public Object setValue(Object key, Object value) {
    if (String.class == key.getClass()) {
      throw new IllegalArgumentException("Can't use String key in *Value methods. Key: " + key +
          " and state: " + this);
    }
    return put(key, value);
  }

  @Override
  public boolean hasValue(Object key) {
    if (String.class == key.getClass()) {
      throw new IllegalArgumentException("Can't use String key in *Value methods. Key: " + key +
          " and state: " + this);
    }
    return containsKey(key);
  }

  @Override
  public Set<Object> getValueKeys() {
    return keySet().stream().filter(object -> String.class != object.getClass())
        .collect(Collectors.toSet());
  }

  @Override
  public ViewsSessionInternal getSession() {
    return session;
  }


  @Override
  public Object getAnnotation(Object key) {
    return null;
  }

  @Override
  public Object setAnnotation(Object key, Object value) {
    return null;
  }
}
