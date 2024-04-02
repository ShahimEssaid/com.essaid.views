package com.essaid.views.flex.impl;

import com.essaid.views.flex.hold.MapTransformer;
import com.essaid.views.flex.View;
import java.util.HashMap;
import java.util.Map;

public class DefaultMapTransformer implements MapTransformer {

  @Override
  public Map<String, Object> toMap(Object object) {
    ToMap toMap = new ToMap(object, object1 -> new HashMap<>(), true);
    return toMap.getMap();
  }

  @Override
  public View fromMap(Map<String, Object> map) {
    return null;
  }
}
