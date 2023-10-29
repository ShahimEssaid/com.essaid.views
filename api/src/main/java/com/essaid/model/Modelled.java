package com.essaid.model;

import java.util.Objects;

public interface Modelled extends ModelObject, Visitable {

    default boolean _equals(Object other) {
        return this == other;
    }

    default String _toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    default int _hashCode() {
        return Objects.hashCode(this);
    }
}
