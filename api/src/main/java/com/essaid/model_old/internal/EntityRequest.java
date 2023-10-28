package com.essaid.model_old.internal;

import com.essaid.model.internal.ElementHandler;

import java.lang.reflect.Method;

public interface EntityRequest {

    Object getProxy();
    Method getMethod();
    Object[] getArgs();
    ElementHandler getHandler();
}
