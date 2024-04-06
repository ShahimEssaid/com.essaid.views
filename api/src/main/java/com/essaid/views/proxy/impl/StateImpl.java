package com.essaid.views.proxy.impl;

import com.essaid.views.internal.SessionInternal;
import com.essaid.views.internal.State;
import com.essaid.views.internal.ViewInternal;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StateImpl extends ConcurrentHashMap<Object, Object> implements State {

  private final SessionInternal session;

  public StateImpl(SessionInternal session) {
    this.session = session;
  }

  @Override
  public ViewInternal getFeatureValue(String key) {
    return (ViewInternal) get(key);
  }

  @Override
  public ViewInternal setFeatureValue(String key, ViewInternal value) {
    return (ViewInternal) put(key, value);
  }

  @Override
  public ViewInternal unsetFeatureValue(String key) {
    return (ViewInternal) remove(key);
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
  public SessionInternal getSession() {
    return session;
  }
}
