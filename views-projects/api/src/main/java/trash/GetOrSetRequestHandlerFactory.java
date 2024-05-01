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


public class GetOrSetRequestHandlerFactory implements RequestHandlerFactory {


  //  @Override
  public RequestHandler getHandler(String featureName, Method method,
      RequestType requestType, ViewsSessionInternal session) {
    if (!requestType.equals(RequestType.GET_OR_SET) ||
        method.isDefault()) {
      return null;
    }

    RequestHandler handler = null;
    Class<?> returnType = method.getReturnType();

    handler = new GetOrSetRequestHandler(featureName, method, requestType, session);

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


  public static class GetOrSetRequestHandler extends AbstractRequestHandler {

    public GetOrSetRequestHandler(String featureName, Method method,
        RequestType requestType, ViewsSessionInternal session) {
      super(featureName, method, requestType, null, session);
    }

    //    @Override
    public Object handle(Object proxy, Method method, Object[] args,
        ViewHandler viewHandler) {
      Object value = viewHandler.getValue().getFeatureValue(featureName);
      if (value == null) {
        value = args[0];
        if (value != null) {
          viewHandler.getValue().setFeatureValue(featureName, (View) value);
        }
      }
      return value;
    }

    @Override
    public void handle(Request request) {

    }
  }
}
