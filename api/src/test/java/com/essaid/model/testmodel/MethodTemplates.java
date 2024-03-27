package com.essaid.model.testmodel;

public interface MethodTemplates {

  // value method templates
  // bean methods are the minimal needed
  Object getTemplateMethod();
  Object getTemplateMethod_OrDefault(Object defaultValue);
  Object getTemplateMethod_OrSet(Object defaultValue);

  void setTemplateMethod(Object bean);
  Object setTemplateMethod_Get(Object bean);
  MethodTemplates setTemplateMethod_Chain(Object bean);



}
