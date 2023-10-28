package com.essaid.model.internal.map;

import java.lang.reflect.Method;

public interface RequestHandlerFactory {
    RequestHandler getHandler(Method method);
}
