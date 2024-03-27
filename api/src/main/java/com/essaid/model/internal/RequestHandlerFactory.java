package com.essaid.model.internal;

import com.essaid.model.ModelManager;
import java.lang.reflect.Method;

public interface RequestHandlerFactory {
    RequestHandler getHandler(String featureName, Method method, RequestType requestType,
        ModelManager modelManager);
}
