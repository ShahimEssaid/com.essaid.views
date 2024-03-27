package com.essaid.model.internal;

import com.essaid.model.internal.ModelObjectHandler;

import java.lang.reflect.Method;

public interface Invocation {

    Object getProxy();
    Method getMethod();
    Object[] getArgs();
    ModelObjectHandler getElementHandler();
}
