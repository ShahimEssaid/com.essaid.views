package com.essaid.model.internal;

import java.lang.reflect.Method;

public interface EntityRequest {

    void dispatch();

    Class<?> getEntityInterface();
    Method getMethod();
    Object[] getArgs();
    Object getOldValue();
    Object getNewValue();
    void setNewValue(Object value);
}
