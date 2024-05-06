package com.essaid.views.internal;

public interface Annotatable {

  Object getAnnotation(Object key);

  Object setAnnotation(Object key, Object value);

  default boolean hasAnnotation(Object key) {
    return getAnnotation(key) != null;
  }

}
