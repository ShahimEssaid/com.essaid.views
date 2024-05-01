package com.essaid.views.proxy.impl;

import com.essaid.views.View;
import com.essaid.views.internal.Value;
import com.essaid.views.internal.ViewsSessionInternal;
import com.essaid.views.proxy.internal.StateKeys;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ValueImpl extends ConcurrentHashMap<Object, Object> implements Value {

  private final ViewsSessionInternal session;

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
  public boolean hasFeature(String key) {
    return containsKey(key);
  }

  @Override
  public Set<String> getFeatureNames() {

    return keySet().stream().filter(k -> k instanceof String).map(k -> (String) k)
        .collect(Collectors.toSet());
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
  public boolean isNullOrEmpty() {
    return getValue(StateKeys.EXTERNAL_VALUE) == null && isEmpty();
  }
}
