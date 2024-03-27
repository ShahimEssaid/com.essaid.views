package com.essaid.model.impl.map;

import com.essaid.model.ModelManager;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ModelObjectHandler extends ConcurrentHashMap<Object, Object> implements
    com.essaid.model.internal.ModelObjectHandler {

  private final Class<?> elementInterface;
  private final ModelManager modelManager;

  public ModelObjectHandler(Class<?> elementInterface, ModelManager modelManager) {
    this.modelManager = modelManager;
    this.elementInterface = elementInterface;
  }


  @Override
  public Class<?> getObjectType() {
    return elementInterface;
  }

  @Override
  public Object getFeatureValue(String featureName) {
    return get(featureName);
  }

  @Override
  public Object setFeatureValue(String featureName, Object value) {
    return put(featureName, value);
  }

  @Override
  public Object unsetFeatureValue(String featureName) {
    return remove(featureName);
  }

  @Override
  public ModelManager getModelManager() {
    return modelManager;
  }

  @Override
  public Map<String, Object> getMapView() {
    return null;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return modelManager.internal().handle(proxy, method, args, this);
  }

  @Override
  public boolean hasFeature(String featureName) {
    return containsKey(featureName);
  }
}
