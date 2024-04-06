package com.essaid.views.internal;

import com.essaid.views.View;

public interface ViewInternal extends View {

  Class<?> __getType();

  Class<?>[] __getInterfaces();

  State __getState();

}
