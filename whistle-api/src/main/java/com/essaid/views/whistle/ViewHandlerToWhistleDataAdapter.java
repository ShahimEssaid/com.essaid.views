package com.essaid.views.whistle;

import com.essaid.views.internal.ViewHandler;
import com.google.cloud.verticals.foundations.dataharmonization.data.Array;
import com.google.cloud.verticals.foundations.dataharmonization.data.Container;
import com.google.cloud.verticals.foundations.dataharmonization.data.Data;
import com.google.cloud.verticals.foundations.dataharmonization.function.Closure;

public interface ViewHandlerToWhistleDataAdapter extends ViewHandler, Data, Array, Closure,
    Container {

  @Override
  default boolean isPrimitive() {
    return !getValue().hasFeatures() && getValue().hasValue();
  }

  @Override
  default boolean isNullOrEmpty() {
    return !getValue().hasFeatures() && !getValue().hasValue();
  }

  //  class S implements WhistleState {
//
//  }
}
