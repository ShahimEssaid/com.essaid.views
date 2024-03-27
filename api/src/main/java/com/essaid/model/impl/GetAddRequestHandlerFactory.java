package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.impl.map.ModelObjectHandler;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import java.lang.reflect.Method;
import java.util.List;

public class GetAddRequestHandlerFactory implements RequestHandlerFactory {


  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {
    if (!requestType.equals(RequestType.GET_ADD) ||
        method.isDefault()) {
      return null;
    }

    RequestHandler handler = null;
    handler = new GetAddRequestHandler(featureName, method, requestType, null, modelManager);

    return handler;
  }


  public static class GetAddRequestHandler extends AbstractRequestHandler {


    public GetAddRequestHandler(String featureName, Method method, RequestType requestType,
        Object defaultValue, ModelManager modelManager) {
      super(featureName, method, requestType, defaultValue, modelManager);
    }


    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ModelObjectHandler objectHandler) {
      List featureValue = (List) objectHandler.getFeatureValue(featureName);
      if (featureValue == null) {
        featureValue = modelManager.create(List.class);
        objectHandler.setFeatureValue(featureName, featureValue);
      }
      Object addedValue = modelManager.create(method.getReturnType());
      featureValue.add(addedValue);
      return addedValue;
    }

//    @Override
//    public Object handleRequest(Invocation invocation) {
//
//      GetterInfo getterInfo = getCGetterInfo(invocation);
//      if (!getterInfo.isList()) {
//        throw new IllegalStateException(
//            "Add method for non list property: " + getterInfo.getMethod());
//      }
//      try {
//        List list = (List) getterInfo.getMethod().invoke(invocation.getProxy());
//
//        if (hasInstance) {
//          list.add(invocation.getArgs()[0]);
//          return invocation.getProxy();
//        } else {
//          Object o = invocation.getElementHandler().getModelManager()
//              .create(getterInfo.getListInstanceType());
//          list.add(o);
//          return o;
//        }
//
//
//      } catch (InvocationTargetException | IllegalAccessException e) {
//        throw new RuntimeException(e);
//      }
//    }
  }


}
