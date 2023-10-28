package com.essaid.model.internal.map.impl;

import com.essaid.model.internal.map.Request;
import com.essaid.model.internal.map.RequestHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

@RequiredArgsConstructor
@Getter
public class GetRequestHandler implements RequestHandler {
    private final String featureName;
    private final Method method;

    @Override
    public Object handleRequest(Request request) {
        return request.getHandler().getFeatureValue(featureName);
    }
}
