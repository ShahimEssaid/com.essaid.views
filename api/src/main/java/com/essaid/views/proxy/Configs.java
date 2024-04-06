package com.essaid.views.proxy;

import com.essaid.views.ViewsManager;
import trash.AcceptRequestHandlerFactory;
import com.essaid.views.proxy.impl.ViewsManagerImpl;
import trash.DefaultRequestHandlerFactory;
import trash.GetAddRequestHandlerFactory;
import trash.GetOrCreateRequestHandlerFactory;
import trash.GetOrDefaultRequestHandlerFactory;
import trash.GetOrSetRequestHandlerFactory;
import trash.GetRequestHandlerFactory;
import trash.IsRequestHandlerFactory;
import trash.ObjectRequestHandlerFactory;
import trash.SetRequestHandlerFactory;
import com.essaid.views.proxy.impl.defaults.ViewInternalHelper;
import com.essaid.views.proxy.impl.handler.factory.DefaultsHandlerFactory;
import com.essaid.views.proxy.impl.handler.factory.GetHandlerFactory;
import com.essaid.views.proxy.impl.handler.factory.SetHandlerFactory;
import com.essaid.views.proxy.impl.handler.factory.ToStringHandlerFactory;
import com.essaid.views.internal.ViewInternal;
import java.util.ArrayList;
import java.util.List;

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

    config.addView(ViewInternal.class, ViewInternalHelper.class);
  }

  public static ViewsManager createDefaultModelManager2() {
    Config config = new Config();
    applyDefaults2(config);
    return new ViewsManagerImpl(config);
  }
}
