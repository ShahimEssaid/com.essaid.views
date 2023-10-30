package com.essaid.model.internal.map;

import com.essaid.model.EntityManager;

import java.lang.reflect.Method;

public interface RequestHandlerFactory {
    RequestHandler getHandler(Method method, EntityManager entityManager);
}
