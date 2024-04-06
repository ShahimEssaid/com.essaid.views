package com.essaid.views.proxy.impl.defaults;

public interface ObjectHelper {

  default boolean _equals(Object other) {
    return this == other;
  }

  default String _toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
  }

  default int _hashCode() {
    return System.identityHashCode(this);
  }

}
