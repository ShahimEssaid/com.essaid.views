package com.essaid.views.flex.internal;

import com.essaid.views.flex.FlexModel;
import java.lang.reflect.Method;

public interface FlexModelInternal extends FlexModel {

//  <T> T adapt(Object object, Class<T> viewType, Class<?>... customDefaults);
//
//  ViewHandler createViewHandler(ViewState viewState);
//
  Method getClientMethod(ViewRequest viewRequest);
//
//  void process(ViewRequest request);

}
