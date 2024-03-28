package com.essaid.model.impl;

import com.essaid.model.MapTransformer;
import com.essaid.model.View;
import com.essaid.model.impl.map.ToMap;
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
