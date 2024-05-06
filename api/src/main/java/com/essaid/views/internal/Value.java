package com.essaid.views.internal;

import com.essaid.views.View;
import java.util.Set;

public interface Value extends Annotatable {

  FeatureHandler getFeature(String featureName, View owningView);

  FeatureHandler setFeature(String featureName, View targetView, View owningView);

  FeatureHandler unsetFeature(String featureName, View owningView);

  boolean hasFeature(String featureName);

  Set<String> getFeatureNames();


  @Deprecated
  View getFeatureValue(String name);

  @Deprecated
  View setFeatureValue(String name, View value);

  @Deprecated
  View unsetFeatureValue(String name);

  @Deprecated
  Object getValue(Object key);

  @Deprecated
  Object setValue(Object key, Object value);

  @Deprecated
  boolean hasValue(Object key);

  @Deprecated
  Set<Object> getValueKeys();

  ViewsSessionInternal getSession();

  boolean isNullOrEmpty();

  boolean isSimple();

  boolean isComplex();

}
