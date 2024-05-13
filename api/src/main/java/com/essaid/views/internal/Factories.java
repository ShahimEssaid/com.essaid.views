package com.essaid.views.internal;

import com.essaid.views.View;
import com.essaid.views.adapter.AdaptRequest;
import com.essaid.views.session.ViewsSessionInternal;
import com.essaid.views.value.Value;

public interface Factories {

  View createView(ViewHandler viewHandler, ViewsSessionInternal session);

  ViewHandler createViewHandler(Class<?> viewType, Class<?>[] defaultInterfaces, Value value,
      ViewsSessionInternal session);

  Value createValue(ViewsSessionInternal session);

  AdaptRequest createAdaptRequest(Object adaptee,
      Class<?> adaptedType,
      Class<?> adaptedUsageType,
      Class<?>[] defaultInterfaces,
      ViewsSessionInternal session,
      ViewHandler viewHandler
      );


}
