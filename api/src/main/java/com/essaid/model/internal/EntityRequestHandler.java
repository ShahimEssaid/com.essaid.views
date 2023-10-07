package com.essaid.model.internal;

import java.lang.reflect.Method;

public interface EntityRequestHandler {

    Class<?> getEntityInterface();

    boolean canHandelRequest(Class<?> entityInterface, Method method);

    Object getValueKey(Class<?> entityInterface, Method method);

    void handleRequest(EntityRequest request);

}
