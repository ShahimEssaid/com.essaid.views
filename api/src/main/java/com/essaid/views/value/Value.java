package com.essaid.views.value;

import com.essaid.views.View;
import com.essaid.views.internal.Annotatable;
import com.essaid.views.internal.FeatureHandler;
import com.essaid.views.session.ViewsSessionInternal;
import java.util.Set;

public interface Value extends Annotatable {

  ViewsSessionInternal getSession();

  FeatureHandler getFeature(String featureName, View owningView);

  FeatureHandler setFeature(String featureName, View targetView, View owningView);

  FeatureHandler unsetFeature(String featureName, View owningView);

  boolean hasFeature(String featureName);

  boolean hasFeatures();

  Set<String> getFeatureNames();

  Object getValue();

  Object setValue(Object value);

  boolean hasValue();


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


}
