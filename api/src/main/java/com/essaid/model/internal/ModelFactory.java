package com.essaid.model.internal;

import com.essaid.model.Config;
import com.essaid.model.Model;

public interface ModelFactory {

    boolean canCreate(Class<?> interfaceClass,String instanceId, Config config);

    <T> T create(Class<T> interfaceClass,String instanceId, Model model);
}
