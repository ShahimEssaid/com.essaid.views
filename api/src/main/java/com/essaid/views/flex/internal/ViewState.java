package com.essaid.views.flex.internal;

import com.essaid.views.flex.Session;
import java.util.Set;

public interface ViewState {

  Object getFeatureValue(Object key);

  Object setFeatureValue(Object key, Object value);

  Object unsetFeatureValue(Object key);

  boolean hasFeature(Object key);

  Set<String> getFeatureNames();

  StateMode getMode();

  enum StateMode {
    INTERNAL, EXTERNAL, MIXED
  }

  Session getSession();

}
