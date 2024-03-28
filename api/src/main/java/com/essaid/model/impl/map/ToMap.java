package com.essaid.model.impl.map;

import com.essaid.model.MapFactory;
import com.essaid.model.View;
import com.essaid.model.internal.State;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.constant.Constable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class ToMap {

  private final Object object;
  private final Map<Object, Object> objectMappings = new IdentityHashMap<>();
  private final MapFactory mapFactory;
  private final boolean includeBeanInfo;

  public ToMap(Object object, MapFactory mapFactory, boolean includeBeanInfo) {
    this.object = object;
    this.mapFactory = mapFactory;
    this.includeBeanInfo = includeBeanInfo;
  }

  public Map<String, Object> getMap() {

    return (Map<String, Object>) getObjectValue(object);

  }

  private Object getObjectValue(Object object)
  //throws IntrospectionException, InvocationTargetException, IllegalAccessException
  {
    if (object == null) {
      return null;
    }
    Object mappedObject = objectMappings.get(object);
    if (mappedObject != null) {
      return mappedObject;
    }

    // for primitive values
    if (object instanceof Constable) {
      final Class<?> c = object.getClass();
      if (String.class.equals(c) || Boolean.class.equals(c) || Integer.class.equals(c)
          || Short.class.equals(c) || Byte.class.equals(c) || Long.class.equals(c)
          || Character.class.equals(c) || Float.class.equals(c) || Double.class.equals(c)) {
        return object;
      } else if (object instanceof Enum<?>) {
        return object.toString();
      } else {
        return null;
      }
    }

    // for View values
    if (object instanceof View) {
      Map<String, Object> map = mapFactory.createMap(object);
      State state = ((View) object)._internal()._getViewHandler().getState();
      for (String featureName : state.getFeatureNames().stream().sorted().toList()) {
        Object featureValue = state.getFeatureValue(featureName);
        Object objectValue = getObjectValue(featureValue);
        if (objectValue != null) {
          map.put(featureName, objectValue);
        }
      }
      objectMappings.put(object, map);
      return map;
    } else if (object instanceof List<?> list) {
      List<Object> newList = list.stream().map(this::getObjectValue)
          .toList();
      objectMappings.put(object, newList);
      return newList;

    }

    // fallback to JavaBeans
    else if (includeBeanInfo) {
      BeanInfo beanInfo = null;
      try {
        beanInfo = Introspector.getBeanInfo(object.getClass());
      } catch (IntrospectionException e) {
        throw new RuntimeException(e);
      }
      Map<String, Object> map = mapFactory.createMap(object);

      for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
        Method readMethod = descriptor.getReadMethod();
        if (readMethod != null) {
          Object propertyValue = null;
          try {
            propertyValue = readMethod.invoke(object);
          } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
          }
          Object value = getObjectValue(propertyValue);
          if (value != null) {
            map.put(descriptor.getName(), value);
          }
        }
      }
      objectMappings.put(object, map);
      return map;
    }
    Map<String, Object> emptyMap = mapFactory.createMap(object);
    objectMappings.put(object, emptyMap);
    return emptyMap;
  }

}
