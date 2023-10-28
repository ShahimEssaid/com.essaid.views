package com.essaid.model.internal.map.impl;

import com.essaid.model.internal.map.Request;
import com.essaid.model.internal.map.RequestHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

@RequiredArgsConstructor
@Getter
public class SetRequestHandler implements RequestHandler {
    private final String featureName;
    private final Method method;

    @Override
    public Object handleRequest(Request request) {
        request.getHandler().setFeatureValue(featureName, request.getArgs()[0]);
        return null;
    }
}
