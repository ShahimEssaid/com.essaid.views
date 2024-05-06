package com.essaid.views.internal;

import com.essaid.views.View;

public interface FeatureHandler extends Annotatable {

  String getName();

  View getOwningView();

  View getTargetView();

  View setTargetView(View view);

}
