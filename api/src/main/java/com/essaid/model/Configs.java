package com.essaid.model;

import com.essaid.model.impl.AcceptRequestHandlerFactory;
import com.essaid.model.impl.DefaultRequestHandlerFactory;
import com.essaid.model.impl.GetAddRequestHandlerFactory;
import com.essaid.model.impl.GetOrCreateRequestHandlerFactory;
import com.essaid.model.impl.GetOrDefaultRequestHandlerFactory;
import com.essaid.model.impl.GetOrSetRequestHandlerFactory;
import com.essaid.model.impl.GetRequestHandlerFactory;
import com.essaid.model.impl.IsRequestHandlerFactory;
import com.essaid.model.impl.ObjectRequestHandlerFactory;
import com.essaid.model.impl.SetAndChainRequestHandlerFactory;
import com.essaid.model.impl.SetAndGetRequestHandlerFactory;
import com.essaid.model.impl.SetRequestHandlerFactory;
import com.essaid.model.impl.map.MapModelManager;
import com.essaid.model.impl.map.ProxyInstanceFactory;
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

    config.addInstanceFactory(new ProxyInstanceFactory());

    config.addHandlerFactory(new DefaultRequestHandlerFactory());

    config.addHandlerFactory(new GetRequestHandlerFactory());
    config.addHandlerFactory(new IsRequestHandlerFactory());
    config.addHandlerFactory(new GetOrDefaultRequestHandlerFactory());
    config.addHandlerFactory(new GetOrSetRequestHandlerFactory());
    config.addHandlerFactory(new GetOrCreateRequestHandlerFactory());
    config.addHandlerFactory(new GetAddRequestHandlerFactory());

    config.addHandlerFactory(new SetRequestHandlerFactory());
    config.addHandlerFactory(new SetAndGetRequestHandlerFactory());
    config.addHandlerFactory(new SetAndChainRequestHandlerFactory());

    config.addHandlerFactory(new AcceptRequestHandlerFactory());
    config.addHandlerFactory(new ObjectRequestHandlerFactory());
  }

  public static ModelManager createDefaultModelManager() {
    Config config = new Config();
    Configs.applyDefaults(config);
    ModelManager modelManager = new MapModelManager(config);
    return modelManager;
  }
}
