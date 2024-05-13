package com.essaid.views.proxy.internal;

import com.essaid.views.ViewsManager;
import com.essaid.views.value.Value;

public interface ObjectStateFactory {

  Value createState(ViewsManager viewsManager);

}
