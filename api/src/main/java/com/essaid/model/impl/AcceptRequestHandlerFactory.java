package com.essaid.model.impl;

import com.essaid.model.ModelManager;
import com.essaid.model.internal.RequestHandler;
import com.essaid.model.internal.RequestHandlerFactory;
import com.essaid.model.internal.RequestType;
import java.lang.reflect.Method;

public class AcceptRequestHandlerFactory implements RequestHandlerFactory {

  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ModelManager modelManager) {
      if (method.isDefault()) {
          return null;
      }
    RequestHandler handler = null;
    if (method.getName().equals("accept") && method.getParameterCount() == 1) {
//            handler = new AcceptRequestHandler();
    }
    return handler;
  }

//    @RequiredArgsConstructor
//    @Getter
//    public static class AcceptRequestHandler implements RequestHandler {
//
//        @Override
//        public Object handleRequest(Invocation invocation) {
//            try {
//
//                Method visitMethod = getVisitMethod(invocation);
//                visitMethod.invoke(invocation.getArgs()[0], invocation.getProxy());
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                throw new RuntimeException(e);
//            }
//            return null;
//        }
//
//        private Method getVisitMethod(Invocation invocation) {
//            Object visitor = invocation.getArgs()[0];
//            List<Method> visitMethods = Arrays.stream(visitor.getClass().getMethods())
//                                              .filter(m -> m.getName().equals("visit") && m.getParameterCount() == 1)
//                                              .toList();
//
//            Class<?> visitedType = invocation.getElementHandler().getObjectType();
//
//            Method visitorMethod = null;
//            Class<?> visitorMethodArgType = Object.class;
//            for (Method method : visitMethods) {
//                Class<?> methodArgType = method.getParameterTypes()[0];
//                if (methodArgType.isAssignableFrom(visitedType) // we have a candidate
//                        && visitorMethodArgType.isAssignableFrom(methodArgType)) {
//                    visitorMethodArgType = methodArgType;
//                    visitorMethod = method;
//                }
//            }
//            return visitorMethod;
//        }
//    }
}
