package com.essaid.model;

import com.essaid.model.impl.*;
import com.essaid.model.map.MapEntityManager;
import com.essaid.model.map.ProxyInstanceFactory;

import java.util.ArrayList;
import java.util.List;

public class Configs {

    public static void applyDefaults(Config config){

        config.addInterfaceImplementation(List.class, ArrayList.class);

        config.addInstanceFactory(new ProxyInstanceFactory());

        config.addHandlerFactory(new DefaultRequestHandlerFactory());
        config.addHandlerFactory(new GetRequestHandlerFactory());
        config.addHandlerFactory(new GetOrDefaultRequestHandlerFactory());
        config.addHandlerFactory(new GetOrSetRequestHandlerFactory());
        config.addHandlerFactory(new SetRequestHandlerFactory());
        config.addHandlerFactory(new IsRequestHandlerFactory());
        config.addHandlerFactory(new AcceptRequestHandlerFactory());
        config.addHandlerFactory(new ObjectRequestHandlerFactory());
        config.addHandlerFactory(new CGetRequestHandlerFactory());
        config.addHandlerFactory(new CSetRequestHandlerFactory());
        config.addHandlerFactory(new AddRequestHandlerFactory());
    }

    public static EntityManager createDefaultMapEntityManager(){
        Config config = new Config();
        Configs.applyDefaults(config);
        EntityManager entityManager = new MapEntityManager(config);
        return entityManager;
    }
}
