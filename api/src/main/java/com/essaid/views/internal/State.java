package com.essaid.views.internal;

import com.essaid.views.internal.SessionInternal;
import com.essaid.views.internal.ViewInternal;
import java.util.Set;

public interface State {

  ViewInternal getFeatureValue(String name);

  ViewInternal setFeatureValue(String name, ViewInternal value);

  ViewInternal unsetFeatureValue(String name);

  boolean hasFeature(String name);

  Set<String> getFeatureNames();

  Object getValue(Object key);

  Object setValue(Object key, Object value);

  boolean hasValue(Object key);

  Set<Object> getValueKeys();

  SessionInternal getSession();

}
