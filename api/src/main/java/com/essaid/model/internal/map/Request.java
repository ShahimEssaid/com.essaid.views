package com.essaid.model.internal.map;

import com.essaid.model.internal.ElementHandler;

import java.lang.reflect.Method;

public interface Request {

    Object getProxy();
    Method getMethod();
    Object[] getArgs();
    ElementHandler getHandler();
}
