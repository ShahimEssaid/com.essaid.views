package com.essaid.views.internal;

import com.essaid.views.View;
import java.util.Set;

public interface Value {

  View getFeatureValue(String name);

  View setFeatureValue(String name, View value);

  View unsetFeatureValue(String name);

  boolean hasFeature(String name);

  Set<String> getFeatureNames();

  Object getValue(Object key);

  Object setValue(Object key, Object value);

  boolean hasValue(Object key);

  Set<Object> getValueKeys();

  ViewsSessionInternal getSession();

  boolean isNullOrEmpty();

}
