package com.essaid.views.internal;

import com.essaid.views.View;
import com.essaid.views.value.Value;

public interface ViewHandler {

  Class<?> getViewType();

  Class<?>[] getInterfaces();

  Value getValue();

  <T> T adaptTo(Class<T> clazz, Class<?>... defaults);

  View getView();

}
