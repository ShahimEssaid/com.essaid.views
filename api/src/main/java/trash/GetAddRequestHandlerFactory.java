package trash;

import com.essaid.views.View;
import com.essaid.views.internal.ViewHandler;
import com.essaid.views.internal.ViewsManagerInternal;
import com.essaid.views.internal.ViewsSessionInternal;
import com.essaid.views.proxy.impl.request.RequestType;
import com.essaid.views.proxy.internal.Request;
import com.essaid.views.proxy.internal.RequestHandler;
import com.essaid.views.proxy.internal.RequestHandlerFactory;
import java.lang.reflect.Method;
import java.util.List;

public class GetAddRequestHandlerFactory implements RequestHandlerFactory {


  //  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ViewsSessionInternal session) {
    if (!requestType.equals(RequestType.GET_ADD) ||
        method.isDefault()) {
      return null;
    }

    RequestHandler handler = null;
    handler = new GetAddRequestHandler(featureName, method, requestType, null, session);

    return handler;
  }

  //  @Override
  public RequestHandler getHandler(Class<?> viewType, Method invokedMethod, RequestType requestType,
      ViewsManagerInternal manager) {
    return null;
  }

  @Override
  public RequestHandler getHandler(Request request) {
    return null;
  }


  public static class GetAddRequestHandler extends AbstractRequestHandler {


    public GetAddRequestHandler(String featureName, Method method, RequestType requestType,
        Object defaultValue, ViewsSessionInternal session) {
      super(featureName, method, requestType, defaultValue, session);
    }


    //    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ViewHandler viewHandler) {
      List featureValue = (List) viewHandler.getValue().getFeatureValue(featureName);
      if (featureValue == null) {
        featureValue = session.createView(List.class, null);
        viewHandler.getValue().setFeatureValue(featureName, (View) featureValue);
      }
      Object addedValue = session.createView(method.getReturnType(), null);
      featureValue.add(addedValue);
      return addedValue;
    }

    @Override
    public void handle(Request request) {

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
