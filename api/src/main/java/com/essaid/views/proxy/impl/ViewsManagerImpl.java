package com.essaid.views.proxy.impl;

import com.essaid.views.adapter.Adapter;
import com.essaid.views.internal.impl.AbstractViewsManager;
import com.essaid.views.proxy.Config;
import java.util.List;

public class ViewsManagerImpl extends AbstractViewsManager {

  @SuppressWarnings("unchecked")

  public ViewsManagerImpl(Config modelConfig) {
    super(modelConfig);
  }


  @Override
  public List<Adapter> getAdapters() {
    return getConfig().getAdapters();
  }
}
