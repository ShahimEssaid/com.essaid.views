package com.essaid.views.internal;

public interface ViewHandler {

  Class<?> getViewType();

  Class<?>[] getInterfaces();

  Value getValue();

  <T> T adaptTo(Class<T> clazz, Class<?>... defaults);

}
