package com.essaid.views.flex.impl;

import com.essaid.views.flex.Session;
import com.essaid.views.flex.internal.SessionInternal;
import com.essaid.views.flex.internal.ViewState;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DefaultViewState extends ConcurrentHashMap<Object, Object> implements ViewState {

  private final SessionInternal session;

  public DefaultViewState(SessionInternal session){
    this.session = session;
  }

  @Override
  public Object getFeatureValue(Object key) {
    return get(key);
  }

  @Override
  public Object setFeatureValue(Object key, Object value) {
    return put(key, value);
  }

  @Override
  public Object unsetFeatureValue(Object key) {
    return remove(key);
  }

  @Override
  public boolean hasFeature(Object key) {
    return containsKey(key);
  }

  @Override
  public Set<String> getFeatureNames() {

    return keySet().stream().filter(k -> k instanceof String).map(k -> (String) k)
        .collect(Collectors.toSet());
  }

  @Override
  public StateMode getMode() {
    return null;
  }

  @Override
  public Session getSession() {
    return session;
  }
}
