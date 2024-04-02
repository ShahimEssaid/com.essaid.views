package com.essaid.views.flex;

import com.essaid.views.flex.impl.AcceptRequestHandlerFactory;
import com.essaid.views.flex.impl.DefaultFlexModel;
import com.essaid.views.flex.impl.DefaultRequestHandlerFactory;
import com.essaid.views.flex.impl.GetAddRequestHandlerFactory;
import com.essaid.views.flex.impl.GetOrCreateRequestHandlerFactory;
import com.essaid.views.flex.impl.GetOrDefaultRequestHandlerFactory;
import com.essaid.views.flex.impl.GetOrSetRequestHandlerFactory;
import com.essaid.views.flex.impl.GetRequestHandlerFactory;
import com.essaid.views.flex.impl.IsRequestHandlerFactory;
import com.essaid.views.flex.impl.ObjectRequestHandlerFactory;
import com.essaid.views.flex.impl.SetRequestHandlerFactory;
import java.util.ArrayList;
import java.util.List;

public class Configs {

  public static Config getDefaultConfig() {
    Config config = new Config();
    applyDefaults(config);
    return config;
  }

  public static void applyDefaults(Config config) {
    config.addInterfaceImplementation(List.class, ArrayList.class);
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

  public static FlexModel createDefaultModelManager() {
    Config config = new Config();
    Configs.applyDefaults(config);
    FlexModel flexModel = new DefaultFlexModel(config);
    return flexModel;
  }

  public static void applyDefaults2(Config config) {

    config.addHandlerFactory(
        new com.essaid.views.flex.impl.handler.request.feature.factory.SetRequestHandlerFactory(),
        new com.essaid.views.flex.impl.handler.request.feature.factory.GetRequestHandlerFactory());
  }

  public static FlexModel createDefaultModelManager2() {
    Config config = new Config();
    applyDefaults2(config);
    return new DefaultFlexModel(config);
  }
}
