package com.essaid.views.flex;

import com.essaid.views.flex.internal.FlexModelInternal;

/**
 * Represents an instance of a Model, and handles non-model related services.
 */
public interface FlexModel {


  Session createSession();

  Config getConfig();

  default FlexModelInternal internal() {
    return (FlexModelInternal) this;
  }




}
