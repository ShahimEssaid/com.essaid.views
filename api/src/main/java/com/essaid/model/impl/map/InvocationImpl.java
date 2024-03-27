package com.essaid.model.impl.map;

import com.essaid.model.internal.Invocation;
import com.essaid.model.internal.ModelObjectHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

@RequiredArgsConstructor
@Getter
public class InvocationImpl implements Invocation {

    private final Object proxy;
    private  final Method method;
    private final Object[] args;
    private final ModelObjectHandler elementHandler;


}
