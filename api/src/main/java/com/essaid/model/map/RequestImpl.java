package com.essaid.model.map;

import com.essaid.model.internal.ElementHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

@RequiredArgsConstructor
@Getter
public class RequestImpl implements  Request{

    private final Object proxy;
    private  final Method method;
    private final Object[] args;
    private final ElementHandler elementHandler;


}
