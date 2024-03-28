package com.essaid.model;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class Misc {


  @Test
  void mapPrimitives() {
    Map<String, Object> map = new HashMap<>();

    map.put("one", 1);
    map.put("char", 'c');

    Object o = map.get("one");

    Map<String, Object> map2;

    System.out.println(map);
  }

  @Test
  void primitivesAndJavaBeans() throws IntrospectionException {
    Integer integer = Integer.valueOf(1);

    BeanInfo beanInfo = Introspector.getBeanInfo(integer.getClass());

    System.out.println(beanInfo);
  }

}
