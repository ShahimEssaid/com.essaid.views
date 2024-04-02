package com.essaid.views.flex.impl;

import com.essaid.views.flex.Session;
import com.essaid.views.flex.internal.FlexModelInternal;
import com.essaid.views.flex.internal.ViewState;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractFlexModel implements FlexModelInternal {



  @Override
  public Session createSession() {
    return new DefaultSession(this);
  }


}
