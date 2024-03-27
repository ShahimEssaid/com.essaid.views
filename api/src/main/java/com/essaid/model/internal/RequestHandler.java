package com.essaid.model.internal;

import com.essaid.model.impl.map.ModelObjectHandler;
import java.lang.reflect.Method;

public interface RequestHandler {

    Object handle(Object proxy, Method method, Object[] args,
        ModelObjectHandler objectHandler);
}
