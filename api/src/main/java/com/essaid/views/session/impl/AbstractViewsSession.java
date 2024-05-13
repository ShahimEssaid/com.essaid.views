package com.essaid.views.session.impl;

import com.essaid.views.adapter.AdaptRequest;
import com.essaid.views.session.ViewsSessionInternal;
import com.essaid.views.value.Value;
import com.essaid.views.internal.ViewHandler;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.adapter.impl.AdaptRequestImpl;
import com.essaid.views.adapter.Adapter;
import com.essaid.views.value.impl.ValueImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractViewsSession implements ViewsSessionInternal {

  protected final ViewsManagerInternal viewManager;

  protected <T> Class<?>[] calculateInterfaces(Class<T> viewType, Class<?>[] customDefaults) {
    return getManager().getInterfaces(viewType, customDefaults);
  }

  protected Value createState() {
    return new ValueImpl(this);
  }

  @Override
  public ViewsManagerInternal getManager() {
    return viewManager;
  }

  @Override
  public <T> T adapt(AdaptRequest request) {

    List<Adapter> adapters = getManager().getAdapters();
    for(Adapter adapter : adapters){
      if(adapter.adapt(request)){
        return (T) request.getAdapted().orElse(null);
      }
    }
    return null;
  }


  @Override
  public <T> T adapt(Object adaptee, Class<T> adaptedType,
      Class<?> adaptedUsageType, ViewHandler viewHandler, Class<?>... defaultInterfaces) {

    AdaptRequest request = getObjectFactory().createAdaptRequest(adaptee, adaptedType,
        adaptedUsageType,
        defaultInterfaces, this, viewHandler);

    return adapt(request);
  }
}
