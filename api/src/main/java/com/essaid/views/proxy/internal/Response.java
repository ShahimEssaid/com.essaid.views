package com.essaid.views.proxy.internal;

import java.util.Map;

public interface Response {

  void setValue(Object value);
  Object getValue();
  Map<Object, Object> getMetadata(boolean create);

}
