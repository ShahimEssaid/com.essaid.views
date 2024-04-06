package com.essaid.views.proxy.internal;

public interface RequestHandlerFactory {

  RequestHandler getHandler(Request request);
}
