package com.essaid.model;

public interface EqualsToStringHashCodeDefaults {

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
