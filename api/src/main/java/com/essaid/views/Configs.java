package com.essaid.views;

import com.essaid.views.adapter.impl.IsInstanceOfAdapter;
import com.essaid.views.adapter.impl.ToViewsAdapter;
import com.essaid.views.proxy.Config;
import com.essaid.views.proxy.impl.ViewsManagerImpl;
import com.essaid.views.proxy.impl.handler.factory.DefaultsHandlerFactory;
import com.essaid.views.proxy.impl.handler.factory.GetHandlerFactory;
import com.essaid.views.proxy.impl.handler.factory.SetHandlerFactory;
import com.essaid.views.proxy.impl.handler.factory.ToStringHandlerFactory;
import java.util.ArrayList;
import java.util.List;
import trash.AcceptRequestHandlerFactory;
import trash.DefaultRequestHandlerFactory;
import trash.GetAddRequestHandlerFactory;
import trash.GetOrCreateRequestHandlerFactory;
import trash.GetOrDefaultRequestHandlerFactory;
import trash.GetOrSetRequestHandlerFactory;
import trash.GetRequestHandlerFactory;
import trash.IsRequestHandlerFactory;
import trash.ObjectRequestHandlerFactory;
import trash.SetRequestHandlerFactory;

public class Configs {

  public static Config getDefaultConfig() {
    Config config = new Config();
    applyDefaults(config);
    return config;
  }

  public static void applyDefaults(Config config) {
    config.addImplementation(List.class, ArrayList.class);
    config.addHandlerFactory(new DefaultRequestHandlerFactory());
    config.addHandlerFactory(new GetRequestHandlerFactory());
    config.addHandlerFactory(new IsRequestHandlerFactory());
    config.addHandlerFactory(new GetOrDefaultRequestHandlerFactory());
    config.addHandlerFactory(new GetOrSetRequestHandlerFactory());
    config.addHandlerFactory(new GetOrCreateRequestHandlerFactory());
    config.addHandlerFactory(new GetAddRequestHandlerFactory());
    config.addHandlerFactory(new SetRequestHandlerFactory());
    config.addHandlerFactory(new AcceptRequestHandlerFactory());
    config.addHandlerFactory(new ObjectRequestHandlerFactory());
  }

  public static ViewsManager createDefaultModelManager() {
    Config config = new Config();
    Configs.applyDefaults(config);
    ViewsManager viewsManager = new ViewsManagerImpl(config);
    return viewsManager;
  }

  public static void applyDefaults2(Config config) {

    config.addHandlerFactory(
        new ToStringHandlerFactory(),
        new DefaultsHandlerFactory(),
        new SetHandlerFactory(),
        new GetHandlerFactory()
    );

//    config.addView(ViewInternal.class, ViewInternalHelper.class);
  }

  public static ViewsManager createDefaultModelManager2() {
    Config config = new Config();
    applyDefaults2(config);
    return new ViewsManagerImpl(config);
  }

  public static void addDefaultAdapters(Config config){
    config.addAdapters(new IsInstanceOfAdapter());
    config.addAdapters(new ToViewsAdapter());
  }
}
