package com.essaid.views.adapter.impl;

import com.essaid.views.adapter.AdaptRequest;
import com.essaid.views.internal.ViewHandler;
import com.essaid.views.session.ViewsSessionInternal;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AdaptRequestImpl implements AdaptRequest {

  private final Object adaptee;
  private final Class<?> adaptedType;
  private final Class<?> adaptedUsageType;
  private final Class<?>[] defaultInterfaces;
  private final ViewsSessionInternal session;
  private final ViewHandler viewHandler;
  private Object adapted;


  public Optional<?> getAdapted() {
    return Optional.ofNullable(adapted);
  }

  @Override
  public void setAdapted(Object adapted) {
    this.adapted = adapted;
  }
}
