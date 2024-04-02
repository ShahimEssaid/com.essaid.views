package com.essaid.views.flex.internal;

import java.util.Map;

public interface ViewResponse {

  void setValue(Object value);
  Object getValue();
  Map<Object, Object> getMetadata(boolean create);

}
