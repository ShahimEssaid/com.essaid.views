package com.essaid.views.proxy.internal;

import com.essaid.views.ViewsManager;
import com.essaid.views.internal.State;

public interface ObjectStateFactory {

  State createState(ViewsManager viewsManager);

}
