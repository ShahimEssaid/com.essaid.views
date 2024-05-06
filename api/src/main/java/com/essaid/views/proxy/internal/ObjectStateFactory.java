package com.essaid.views.proxy.internal;

import com.essaid.views.ViewsManager;
import com.essaid.views.internal.Value;

public interface ObjectStateFactory {

  Value createState(ViewsManager viewsManager);

}
